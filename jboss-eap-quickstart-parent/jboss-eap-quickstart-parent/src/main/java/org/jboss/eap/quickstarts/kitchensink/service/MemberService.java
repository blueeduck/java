package org.jboss.eap.quickstarts.kitchensink.service;

import java.util.List;

import org.jboss.eap.quickstarts.kitchensink.entity.MemberEntity;
import org.jboss.eap.quickstarts.kitchensink.repository.MemberH2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@org.springframework.transaction.annotation.Transactional
public class MemberService {

	private MemberH2Repository memberRepository;
	
	@Autowired
	public void setMemberRepository(MemberH2Repository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public List<MemberEntity> findAllOrderedByName() {
        return memberRepository.findByOrderByNameAsc();
    }
	
	public MemberEntity findById(Long id) {
        return memberRepository.findById(id).get();
    }

    public MemberEntity findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
    
    public void register(MemberEntity memberEntity) {
    		memberRepository.save(memberEntity);
    }
}
