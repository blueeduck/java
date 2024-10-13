package org.jboss.eap.quickstarts.kitchensink.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.jboss.eap.quickstarts.kitchensink.entity.MemberEntity;
import org.jboss.eap.quickstarts.kitchensink.repository.MemberH2Repository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

	@Mock
	MemberH2Repository memberH2Repository;

	@InjectMocks
	private MemberService memberService;

	@Test
	public void testFindAllOrderedByName() {
		when(memberH2Repository.findByOrderByNameAsc())
				.thenReturn(Arrays.asList(new MemberEntity(1L, "Ram", "ram@abc.com", "9540441228"),
						new MemberEntity(2L, "Shyam", "shyam@abc.com", "9540441229")));

		List<MemberEntity> memberEntities = memberService.findAllOrderedByName();

		verify(memberH2Repository, times(1)).findByOrderByNameAsc();
	}

	@Test
	public void testFindById() {

		long userId = 1L;
		when(memberH2Repository.findById(userId))
				.thenReturn(java.util.Optional.of(new MemberEntity(1L, "Ram", "ram@abc.com", "9540441228")));

		MemberEntity mEntity = memberService.findById(userId);

		verify(memberH2Repository, times(1)).findById(userId);
	}

	@Test
	public void testFindByEmail() {
		String emailId = "ram@abc.com";
		when(memberH2Repository.findByEmail(emailId))
				.thenReturn(new MemberEntity(1L, "Ram", "ram@abc.com", "9540441228"));

		MemberEntity mEntity = memberService.findByEmail(emailId);

		verify(memberH2Repository, times(1)).findByEmail(emailId);
	}

	@Test
	public void testRegister() {
		MemberEntity newMemberEntity = new MemberEntity(1L, "Ram", "ram@abc.com", "9540441228");
		when(memberH2Repository.save(newMemberEntity))
				.thenReturn(new MemberEntity(1L, "Ram", "ram@abc.com", "9540441228"));

		memberService.register(newMemberEntity);

		verify(memberH2Repository, times(1)).save(newMemberEntity);
	}

}
