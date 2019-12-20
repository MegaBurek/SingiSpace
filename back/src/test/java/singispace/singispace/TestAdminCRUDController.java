package singispace.singispace;

import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;
import java.text.ParseException;

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
import singispace.domain.Admin;
import singispace.repository.users.Admin_Repository;
import singispace.service.CRUD.Admin_CRUD_Service;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = singispaceApp.class)
@AutoConfigureMockMvc
public class TestAdminCRUDController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	Admin_CRUD_Service adminService;

	@Autowired
	Admin_Repository adminRepository;

	@Before
	public void setupRegisteredUser() throws ParseException {
		adminService.addAdmin(new Admin(false, 1, null));
		adminService.addAdmin(new Admin(false, 3, null));
		adminService.addAdmin(new Admin(false, 5, null));

	}

	@After
	public void afterAdmin() throws SQLException {
		adminRepository.deleteAll();
	}

	@Test
	public void getAdmin() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/admin").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)));
	}

	@Test
	public void removeRegisteredUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/admin/2").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNoContent());
	}

}