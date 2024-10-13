package org.jboss.eap.quickstarts.kitchensink.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.jboss.eap.quickstarts.kitchensink.entity.MemberEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MemberH2RepositoryTest {

	
	@Autowired
    private MemberH2Repository memberH2Repository;
	
	@Test
    public void testFindByOrderByNameAsc() {
      
        MemberEntity member1 = new MemberEntity(1L, "Ram", "ram@abc.com", "9540441228");
        MemberEntity member2 = new MemberEntity(2L, "Shyam", "shyam@abc.com", "9540441229");
        memberH2Repository.saveAll(List.of(member1, member2));

        List<MemberEntity> memberEntitys = memberH2Repository.findByOrderByNameAsc();

        assertEquals(2, memberEntitys.size());
        //assertTrue(memberEntitys.contains(member1));
        //assertTrue(memberEntitys.contains(member2));
    }
	
	@Test
    public void testFindByEmail() {
      
        MemberEntity member1 = new MemberEntity(1L, "Ram", "ram@abc.com", "9540441228");
        MemberEntity member2 = new MemberEntity(2L, "Shyam", "shyam@abc.com", "9540441229");
        memberH2Repository.saveAll(List.of(member1, member2));

        MemberEntity memberEntity = memberH2Repository.findByEmail("ram@abc.com");

        assertEquals("ram@abc.com", memberEntity.getEmail());
    }
}
