package singispace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import singispace.domain.Admin;
import singispace.domain.Administrator;
import singispace.domain.Learner;
import singispace.repositories.users.AdminRepository;
import singispace.repositories.users.AdministratorRepository;
import singispace.repositories.users.LearnerRepository;
import singispace.service.users.AccountDataService;

import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    LoginService loginService;

    @Autowired
    AccountDataService accountDataService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdministratorRepository administratorRepository;

    @Autowired
    LearnerRepository learnerRepository;

    //---------ADMIN---------------//
    public void addAdmin(Admin admin){
        loginService.addPermssion(admin.getAccountData(), "ROLE_ADMIN");
        accountDataService.addAccountData(admin.getAccountData());
        admin.getAccountData().setPassword(passwordEncoder.encode(admin.getAccountData().getPassword()));
        adminRepository.save(admin);
    }

    public void removeAdmin(String id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if(admin.isPresent()) {
            adminRepository.delete(admin.get());
        }
    }

    public void updateAdmin(String id, Admin admin) {
        Optional<Admin> current_admin = adminRepository.findById(id);
        if(current_admin.isPresent()) {
            admin.setId(current_admin.get().getId());
            admin.getAccountData().setPassword(passwordEncoder.encode(admin.getAccountData().getPassword()));
            accountDataService.updateAccountData(admin.getAccountData().getId(), admin.getAccountData());
            adminRepository.save(admin);
        }
    }


    //---------ADMINISTRATOR---------------//
    public void addAdministrator(Administrator administrator) {
        loginService.addPermssion(administrator.getAccountData(), "ROLE_ADMINISTRATOR");
        accountDataService.addAccountData(administrator.getAccountData());
        administrator.getAccountData().setPassword(passwordEncoder.encode(administrator.getAccountData().getPassword()));
        administratorRepository.save(administrator);
    }

    public void removeAdministrator(String id) {
        Optional<Administrator> admin = administratorRepository.findById(id);
        if(admin.isPresent()) {
            administratorRepository.delete(admin.get());
        }
    }

    public void updateAdministrator(String id, Administrator administrator) {
        Optional<Administrator> admin = administratorRepository.findById(id);
        if(admin.isPresent()) {
            administrator.setId(admin.get().getId());
            administrator.getAccountData().setPassword(passwordEncoder.encode(administrator.getAccountData().getPassword()));
            accountDataService.updateAccountData(administrator.getAccountData().getId(), administrator.getAccountData());
            administratorRepository.save(administrator);
        }
    }

    //---------LEARNER---------------//
    public void addLearner(Learner learner){
        loginService.addPermssion(learner.getAccountData(), "ROLE_LEARNER");
        accountDataService.addAccountData(learner.getAccountData());
        learner.getAccountData().setPassword(passwordEncoder.encode(learner.getAccountData().getPassword()));
        learnerRepository.save(learner);
    }

    public void removeLearner(String id) {
        Optional<Learner> learner = learnerRepository.findById(id);
        if(learner.isPresent()) {
            learnerRepository.delete(learner.get());
        }
    }

    public void updateLearner(String id, Learner learner) {
        Optional<Learner> current_learner = learnerRepository.findById(id);
        if(current_learner.isPresent()) {
            learner.setId(current_learner.get().getId());
            learner.getAccountData().setPassword(passwordEncoder.encode(learner.getAccountData().getPassword()));
            accountDataService.updateAccountData(learner.getAccountData().getId(), learner.getAccountData());
            learnerRepository.save(learner);
        }
    }
}
