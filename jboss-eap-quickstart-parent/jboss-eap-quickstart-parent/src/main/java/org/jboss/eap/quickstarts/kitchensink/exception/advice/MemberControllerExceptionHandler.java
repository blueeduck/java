package org.jboss.eap.quickstarts.kitchensink.exception.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;

@ControllerAdvice
public class MemberControllerExceptionHandler {

	private Logger log = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, String>> constraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		
		// Handle bean validation issues
		return new ResponseEntity<Map<String, String>>(createViolationResponse(ex.getConstraintViolations()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> validationException(ValidationException ex, WebRequest request) {

		// Handle the unique constrain violation
		Map<String, String> responseObj = new HashMap<>();
		responseObj.put("email", "Email taken");
		return new ResponseEntity<Map<String, String>>(responseObj, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> exception(Exception ex, WebRequest request) {

		// Handle generic exceptions
		Map<String, String> responseObj = new HashMap<>();
		responseObj.put("error", ex.getMessage());
		return new ResponseEntity<Map<String, String>>(responseObj, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Creates a "Bad Request" response including a map of all violation
	 * fields, and their message. This can then be used by clients to show
	 * violations.
	 *
	 * @param violations A set of violations that needs to be reported
	 * @return response containing all violations
	 */
	private Map<String, String> createViolationResponse(Set<ConstraintViolation<?>> violations) {
		log.info("Validation completed. violations found: " + violations.size());

		Map<String, String> responseObj = new HashMap<String, String>();

		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
		}

		return responseObj;
	}
}
