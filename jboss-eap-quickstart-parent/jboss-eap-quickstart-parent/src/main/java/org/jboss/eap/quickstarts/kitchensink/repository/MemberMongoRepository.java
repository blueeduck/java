package org.jboss.eap.quickstarts.kitchensink.repository;

import java.util.List;

import org.jboss.eap.quickstarts.kitchensink.document.MemberDocument;
//import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberMongoRepository /* extends MongoRepository<MemberDocument, Long> */{
	

	public List<MemberDocument> findAllOrderedByName();
	public MemberDocument findByEmail(String email);

}
