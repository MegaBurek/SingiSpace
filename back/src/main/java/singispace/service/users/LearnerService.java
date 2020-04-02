package singispace.service.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import singispace.domain.AccountData;
import singispace.domain.AuthProvider;
import singispace.domain.Learner;
import singispace.repositories.users.LearnerRepository;

import java.util.Optional;

@Service
public class LearnerService {

    @Autowired
    LearnerRepository learnerRepository;

    @Autowired
    PermissionService permissionService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountDataService accountDataService;

    public LearnerService(){}

    public Iterable<Learner> getLearners(){
        return learnerRepository.findAll();
    }

    public Optional<Learner> getLearnerById(String id){
        return learnerRepository.findById(id);
    }

    public HttpStatus addLearner(Learner learner){
        Optional<AccountData> accountData = accountDataService.getAccountByUsername(learner.getAccountData().getUsername());

        if(accountData.isPresent()){
            return HttpStatus.IM_USED;
        }
        else {
            learner.getAccountData().setPassword(passwordEncoder.encode(learner.getAccountData().getPassword()));
            learner.getAccountData().setProvider(AuthProvider.local);
            accountDataService.addLearnerAccountData(learner.getAccountData());
            learnerRepository.save(learner);
            return HttpStatus.CREATED;
        }
    }

    public void removeLearner(String id) {
        Optional<Learner> learner = learnerRepository.findById(id);
        if(learner.isPresent()) {
            learnerRepository.delete(learner.get());
            accountDataService.removeAccountData(learner.get().getAccountData().getId());
            permissionService.removePermission(learner.get().getAccountData().getPermission().getId());
        }
    }

    public void updateLearner(String id, Learner learner) {

        Optional<Learner> a = learnerRepository.findById(id);

        if(a.isPresent()) {
            learner.setId(a.get().getId());
            accountDataService.updateAccountData(a.get().getAccountData().getId(), a.get().getAccountData());

            learnerRepository.save(learner);
        }
    }


}
