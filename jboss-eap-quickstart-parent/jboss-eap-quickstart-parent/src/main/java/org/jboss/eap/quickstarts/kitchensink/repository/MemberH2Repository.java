package org.jboss.eap.quickstarts.kitchensink.repository;

import java.util.List;

import org.jboss.eap.quickstarts.kitchensink.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberH2Repository extends JpaRepository<MemberEntity, Long>{

	public List<MemberEntity> findByOrderByNameAsc();
	public MemberEntity findByEmail(String email);

}
