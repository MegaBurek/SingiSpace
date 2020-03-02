package singispace.controller.CRUD;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import singispace.domain.Learner;
import singispace.service.CRUD.LearnerCRUDService;
import singispace.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/learner")
public class Learner_CRUD_Controller {

    @Autowired
    LearnerCRUDService learnerCRUDService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<Learner>> getLearners() {
        return new ResponseEntity<Iterable<Learner>>(learnerCRUDService.getLearners(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Learner> getLearnerById(@PathVariable Long id) {
        Optional<Learner> learner = learnerCRUDService.getLearnerById(id);
        if(learner.isPresent()) {
            return new ResponseEntity<Learner>(learner.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Learner>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<Learner> addLearner(@RequestBody Learner learner) {
    	learnerCRUDService.addLearner(learner);
        return new ResponseEntity<Learner>(learner, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Learner> updateRegisteredUser(@PathVariable Long id, @RequestBody Learner learner) {
    	learnerCRUDService.updateLearner(id, learner);
        return new ResponseEntity<Learner>(learner, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Learner> removeRegisteredUser(@PathVariable Long id) {
        try {
        	learnerCRUDService.removeLearner(id);
        }catch (Exception e) {
            return new ResponseEntity<Learner>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Learner>(HttpStatus.NO_CONTENT);
    }
	
}
