package org.jboss.eap.quickstarts.kitchensink.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testLookupMemberById() throws Exception {

		long memberId = 1L;

		ResultActions result = mockMvc.perform(get("/kitchensink/member/{id}", memberId));

		result.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(memberId)).andExpect(jsonPath("$.name").value("Subodh"))
				.andExpect(jsonPath("$.email").value("abc@gmail.com"))
				.andExpect(jsonPath("$.phoneNumber").value("7982151513"));
	}

	@Test
	public void testCreateMember() throws Exception {

		String userJson = "{\"id\" :1,\r\n" + "\"email\" : \"abc@gmail.com\",\r\n" + "\"name\" : \"Subodh\",\r\n"
				+ "\"phoneNumber\" : \"7982151513\"}";

		ResultActions result = mockMvc
				.perform(post("/kitchensink/member").contentType(MediaType.APPLICATION_JSON).content(userJson));

		result.andExpect(status().is2xxSuccessful());

	}
}
