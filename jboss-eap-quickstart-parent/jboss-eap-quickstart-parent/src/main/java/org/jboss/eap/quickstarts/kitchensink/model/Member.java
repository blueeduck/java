package org.jboss.eap.quickstarts.kitchensink.model;

import java.io.Serializable;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Member implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5455498734883359008L;
	
    private Long id;

    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    private String name;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @Size(min = 10, max = 12)
    @Digits(fraction = 0, integer = 12)
    private String phoneNumber;

    public Member() {}
    
    
	public Member(Long id,
			@NotNull @Size(min = 1, max = 25) @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers") String name,
			@NotNull @NotEmpty @Email String email,
			@NotNull @Size(min = 10, max = 12) @Digits(fraction = 0, integer = 12) String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

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
