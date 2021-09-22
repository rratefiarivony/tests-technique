package adeo.leroymerlin.cdp;

import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(fetch=FetchType.EAGER)
    private Set<Member> members;

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasMemberWithNameContaining(String query) {
        return !CollectionUtils.isEmpty(members) && members.stream().anyMatch(member -> member.nameContains(query));
    }
}
