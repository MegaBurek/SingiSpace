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
import singispace.domain.Learner;
import singispace.repository.users.Learner_Repository;
import singispace.service.CRUD.Learner_CRUD_Service;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = singispaceApp.class)
@AutoConfigureMockMvc
public class TestLearnerCRUDController {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	Learner_CRUD_Service learnerService;

	@Autowired
	Learner_Repository learnerRepository;

	@Before
	public void setupLearner() throws ParseException {
		learnerService.addLearner(new Learner(false, 1, null));
		learnerService.addLearner(new Learner(false, 6, null));
		learnerService.addLearner(new Learner(false, 12, null));

	}

	@After
	public void afterLearner() throws SQLException {
		learnerRepository.deleteAll();
	}

	@Test
	public void getLearners() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/learner").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)));
	}

}