package singispace.singispace;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import singispace.singispaceApp;
import singispace.domain.RegisteredUser;
import singispace.repository.users.RegisteredUser_Repository;
import singispace.service.CRUD.RegisteredUser_CRUD_Service;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = singispaceApp.class)
@AutoConfigureMockMvc
public class TestRegisteredUserController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	RegisteredUser_CRUD_Service registeredUserService;

	@Autowired
	RegisteredUser_Repository registeredUserRepository;

	@Before
	public void setupRegisteredUser() throws ParseException {
		SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
		registeredUserService.addRegisteredUser(new RegisteredUser(false, 1, "user111", "pass111", "email111", "ime111",
				"prezime111", dt.parse("11-10-2015"), "role1", "bio111", null, null, null));
		registeredUserService.addRegisteredUser(new RegisteredUser(false, 1, "user222", "pass222", "email222", "ime222",
				"prezime222", dt.parse("10-12-2017"), "role2", "bio222", null, null, null));
		registeredUserService.addRegisteredUser(new RegisteredUser(false, 1, "user333", "pass333", "email333", "ime333",
				"prezime333", dt.parse("09-03-2011"), "role3", "bio333", null, null, null));

	}

	@After
	public void afterRegisteredUser() throws SQLException {
		registeredUserRepository.deleteAll();
	}

	@Test
	public void getRegisteredUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/registeredUser").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)));
	}

	@Test
	public void getRegisteredUserId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/registeredUser/1").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.username", equalTo("user111")))
				.andExpect(jsonPath("$.firstName", equalTo("ime111"))).andReturn().getResponse().getContentAsString();

	}

}