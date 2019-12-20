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
import singispace.domain.Tutor;
import singispace.repository.users.Tutor_Repository;
import singispace.service.CRUD.Tutor_CRUD_Service;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = singispaceApp.class)
@AutoConfigureMockMvc
public class TestTutorCRUDController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	Tutor_CRUD_Service tutorService;

	@Autowired
	Tutor_Repository tutorRepository;

	@Before
	public void setupTutor() throws ParseException {
		tutorService.addTutor(new Tutor(false, 1, "Docent", null));
		tutorService.addTutor(new Tutor(false, 1, "Redovan Profesor", null));
		tutorService.addTutor(new Tutor(false, 1, "Asistent", null));

	}

	@After
	public void afterTutor() throws SQLException {
		tutorRepository.deleteAll();
	}

	@Test
	public void getTutors() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/tutor").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)));
	}

	@Test
	public void removeTutor() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/tutor/3").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNoContent());
	}

}