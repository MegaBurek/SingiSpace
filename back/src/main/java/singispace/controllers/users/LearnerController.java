package singispace.controllers.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import singispace.domain.Learner;
import singispace.repositories.users.AccountDataRepository;
import singispace.repositories.users.LearnerRepository;

import java.util.List;

@Controller
@RequestMapping("/learner")
public class LearnerController {

    @Autowired
    public AccountDataRepository accountDataRepository;

    @Autowired
    public LearnerRepository learnerRepository;

    @Autowired


    @GetMapping("/all")
    public List<Learner> getAll(){
        List<Learner> learners = this.learnerRepository.findAll();

        return learners;
    }

    @PutMapping
    public void insert(@RequestBody Learner learner){
        this.learnerRepository.insert(learner);
    }

    @PostMapping
    public void update(@RequestBody Learner learner){
        this.learnerRepository.save(learner);
    }

}
