package singispace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import singispace.domain.*;
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
    PermissionService permissionService;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdministratorRepository administratorRepository;

    @Autowired
    LearnerRepository learnerRepository;

    //---------ADMIN---------------//
    public HttpStatus addAdmin(Admin admin){

        Optional<AccountData> accountData = accountDataService.getAccountByUsername(admin.getAccountData().getUsername());

        if(accountData.isPresent()){
            return HttpStatus.IM_USED;
        }
        else {
            admin.getAccountData().setPassword(passwordEncoder.encode(admin.getAccountData().getPassword()));
            admin.getAccountData().setProvider(AuthProvider.local);
            permissionService.addAdminPermission(admin.getAccountData().getPermission());
            accountDataService.addAccountData(admin.getAccountData());
            return HttpStatus.CREATED;
        }
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
    public HttpStatus addAdministrator(Administrator administrator){
        Optional<AccountData> accountData = accountDataService.getAccountByUsername(administrator.getAccountData().getUsername());

        if(accountData.isPresent()){
            return HttpStatus.IM_USED;
        }
        else {
            administrator.getAccountData().setPassword(passwordEncoder.encode(administrator.getAccountData().getPassword()));
            administrator.getAccountData().setProvider(AuthProvider.local);
            permissionService.addAdminPermission(administrator.getAccountData().getPermission());
            accountDataService.addAccountData(administrator.getAccountData());
            return HttpStatus.CREATED;
        }
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
    public HttpStatus addLearner(Learner learner){
        Optional<AccountData> accountData = accountDataService.getAccountByUsername(learner.getAccountData().getUsername());

        if(accountData.isPresent()){
            return HttpStatus.IM_USED;
        }
        else {
            learner.getAccountData().setPassword(passwordEncoder.encode(learner.getAccountData().getPassword()));
            learner.getAccountData().setProvider(AuthProvider.local);
            permissionService.addAdminPermission(learner.getAccountData().getPermission());
            accountDataService.addAccountData(learner.getAccountData());
            return HttpStatus.CREATED;
        }
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
