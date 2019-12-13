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

import singispace.domain.Tutor;
import singispace.service.CRUD.Tutor_CRUD_Service;
import singispace.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/tutor")
public class Tutor_CRUD_Controller {

    @Autowired
    Tutor_CRUD_Service tutorCRUDService;

    @JsonView(HideOptionalProperties.class)
    @RequestMapping()
    public ResponseEntity<Iterable<Tutor>> getTutors() {
        return new ResponseEntity<Iterable<Tutor>>(tutorCRUDService.getTutors(), HttpStatus.OK);
    }

    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Tutor> getTutorById(@PathVariable Long id) {
        Optional<Tutor> tutor = tutorCRUDService.getTutorById(id);
        if(tutor.isPresent()) {
            return new ResponseEntity<Tutor>(tutor.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Tutor>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<Tutor> addTutor(@RequestBody Tutor tutor) {
        tutorCRUDService.addTutor(tutor);
        return new ResponseEntity<Tutor>(tutor, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Tutor> updateTutor(@PathVariable Long id, @RequestBody Tutor tutor) {
        tutorCRUDService.updateTutor(id, tutor);
        return new ResponseEntity<Tutor>(tutor, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Tutor> removeTutor(@PathVariable Long id) {
        try {
            tutorCRUDService.removeTutor(id);
        }catch (Exception e) {
            return new ResponseEntity<Tutor>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Tutor>(HttpStatus.NO_CONTENT);
    }
	
}
