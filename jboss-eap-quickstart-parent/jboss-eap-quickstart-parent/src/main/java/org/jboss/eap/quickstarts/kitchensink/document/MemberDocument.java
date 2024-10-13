package org.jboss.eap.quickstarts.kitchensink.document;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

//@Document
public class MemberDocument implements Serializable{

	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3015313185567785400L;
	
	@Id
	private Long id;
    private String name;
    private String email;
    private String phoneNumber;
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
    

}
