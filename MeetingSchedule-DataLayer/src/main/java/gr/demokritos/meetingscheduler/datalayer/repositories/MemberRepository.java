package gr.demokritos.meetingscheduler.datalayer.repositories;

import gr.demokritos.meetingscheduler.datalayer.persistence.entities.Member;
import gr.demokritos.meetingscheduler.datalayer.utils.DbConstants;
import org.apache.commons.collections4.CollectionUtils;

import javax.enterprise.context.Dependent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JpaRepo
@Dependent
public class MemberRepository extends AbstractRepository<Member> {

    public MemberRepository() {
        super(DbConstants.MEMBER_TABLE);
    }

    public List<Member> findAllMembers() {
        return namedQuery(DbConstants.MEMBER_FIND_ALL, null);
    }

    public List<Member> findAllMembers(String sortString) {
        return jpqlQuery("SELECT m FROM Member m" + sortString);
    }

    public Member findMemberById(Long id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        List<Member> members = namedQuery(DbConstants.MEMBER_FIND_BY_ID, parameters);
        if (!CollectionUtils.isEmpty(members)) {
            return members.get(0);
        }
        return null;
    }

    public List<Member> findMemberByName(String name) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        return namedQuery(DbConstants.MEMBER_FIND_BY_NAME, parameters);
    }

    public List<Member> findMemberByLastName(String lastName) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("lastName", lastName);
        return namedQuery(DbConstants.MEMBER_FIND_BY_LAST_NAME, parameters);
    }

    public Member findMemberByNameAndLastName(String name, String lastName) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("lastName", lastName);
        List<Member> members = namedQuery(DbConstants.MEMBER_FIND_BY_NAME_AND_LAST_NAME, parameters);
        if (!CollectionUtils.isEmpty(members)) {
            return members.get(0);
        }
        return null;
    }

    public Member findMemberByEmail(String email) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", email);
        List<Member> members = namedQuery(DbConstants.MEMBER_FIND_BY_EMAIL, parameters);
        if (!CollectionUtils.isEmpty(members)) {
            return members.get(0);
        }
        return null;
    }
}
