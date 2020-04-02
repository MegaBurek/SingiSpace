package singispace.controllers.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import singispace.domain.Admin;
import singispace.domain.Administrator;
import singispace.domain.Learner;
import singispace.service.users.LearnerService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/learner")
public class LearnerController {

    @Autowired
    public LearnerService learnerService;

    @Autowired
    AccountDataController accountDataController;

    @GetMapping(value="/all")
    public ResponseEntity<Iterable<Learner>> getAll() {
        return new ResponseEntity<Iterable<Learner>>(learnerService.getLearners(), HttpStatus.OK);
    }


    @GetMapping(value="/{id}")
    public ResponseEntity<Learner> getById(@PathVariable String id) {
        Optional<Learner> administrator = learnerService.getLearnerById(id);
        if(administrator.isPresent()) {
            return new ResponseEntity<Learner>(administrator.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Learner>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register")
    public ResponseEntity<Learner> registerLearner(@RequestBody Learner learner){
        learnerService.addLearner(learner);
        return new ResponseEntity<Learner>(learner, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Learner> removeLearner(@PathVariable String id){
        try {
            learnerService.removeLearner(id);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<?> updateLearner(@PathVariable String id, @RequestBody Learner learner) {
        learnerService.updateLearner(id, learner);
        accountDataController.updateAccountData(learner.getAccountData().getId(), learner.getAccountData());
        return new ResponseEntity<Learner>(learner, HttpStatus.OK);
    }

}
