package singispace.service.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import singispace.domain.Learner;
import singispace.repositories.users.LearnerRepository;

import java.util.Optional;

@Service
public class LearnerService {

    @Autowired
    LearnerRepository learnerRepository;

    public LearnerService(){}

    public Iterable<Learner> getLearners(){
        return learnerRepository.findAll();
    }

    public Optional<Learner> getLearnerById(String id){
        return learnerRepository.findById(id);
    }


}
