package org.jboss.eap.quickstarts.kitchensink.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jboss.eap.quickstarts.kitchensink.model.Member;
import org.jboss.eap.quickstarts.kitchensink.service.MemberService;
import org.jboss.eap.quickstarts.kitchensink.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.NoResultException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;

@RestController()
public class MemberRestController {

	private Validator validator;
	private MemberService memberService;

	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	@GetMapping(path = "/kitchensink/members", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Member> listAllMembers() {
		return Utility.getMemberModels(memberService.findAllOrderedByName());
	}

	@GetMapping(path = "/kitchensink/members/{id:[0-9][0-9]*}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Member lookupMemberById(@PathVariable("id") long id) {
		Member member = Utility.getMemberModel(memberService.findById(id));
		if (member == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member Not Found");
		}
		return member;
	}

	/**
	 * Creates a new member from the values provided. Performs validation, and will
	 * return a response with either 200 ok, or with a map of fields, and
	 * related errors.
	 */
	@PostMapping(path = "/kitchensink/member", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String createMember( @RequestBody Member member) {

		// Validates member using bean validation
		validateMember(member);
		memberService.register(Utility.getMemberEntity(member));
		return "Member is created.";
	}

	/**
	 * <p>
	 * Validates the given Member variable and throws validation exceptions based on
	 * the type of error. If the error is standard bean validation errors then it
	 * will throw a ConstraintValidationException with the set of the constraints
	 * violated.
	 * </p>
	 * <p>
	 * If the error is caused because an existing member with the same email is
	 * registered it throws a regular validation exception so that it can be
	 * interpreted separately.
	 * </p>
	 *
	 * @param member Member to be validated
	 * @throws ConstraintViolationException If Bean Validation errors exist
	 * @throws ValidationException          If member with the same email already
	 *                                      exists
	 */
	private void validateMember(Member member) throws ConstraintViolationException, ValidationException {
		// Create a bean validator and check for issues.
		Set<ConstraintViolation<Member>> violations = validator.validate(member);

		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(new HashSet<>(violations));
		}

		// Check the uniqueness of the email address
		if (emailAlreadyExists(member.getEmail())) {
			throw new ValidationException("Unique Email Violation");
		}
	}

	/**
	 * Checks if a member with the same email address is already registered. This is
	 * the only way to easily capture the "@UniqueConstraint(columnNames = "email")"
	 * constraint from the Member class.
	 *
	 * @param email The email to check
	 * @return True if the email already exists, and false otherwise
	 */
	public boolean emailAlreadyExists(String email) {
		Member member = null;
		try {
			member = Utility.getMemberModel(memberService.findByEmail(email));
		} catch (NoResultException e) {
			System.err.println(e);
		}
		return member != null;
	}

}
