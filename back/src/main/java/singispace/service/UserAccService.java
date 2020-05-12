package singispace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import singispace.domain.*;
import singispace.repositories.users.UserAccRepository;

import java.util.Optional;

@Service
public class UserAccService {

    @Autowired
    PermissionService permissionService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserAccRepository userAccRepository;

    public Iterable<User> getAll() {
        return userAccRepository.findAll();
    }

    public Optional<User> getById(String id) {
        return userAccRepository.findById(id);
    }

    public void addUser(User user){
        userAccRepository.save(user);
    }

    public void removeUserAccData(String id) {
        Optional<User> user = userAccRepository.findById(id);
        userAccRepository.delete(user.get());
    }

    public void updateUserAccData(String id, User user) {

        Optional<User> a = userAccRepository.findById(id);

        if(a.isPresent()) {
            user.setId(a.get().getId());
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userAccRepository.save(user);
        }

    }

    public HttpStatus addAdmin(User user){

        Optional<User> accountData = userAccRepository.findByUsername(user.getUsername());
        if(accountData.isPresent()){
            return HttpStatus.IM_USED;
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setProvider(AuthProvider.local);
            permissionService.addAdminPermission(user.getPermission());
            userAccRepository.save(user);
            return HttpStatus.CREATED;
        }
    }

    public HttpStatus addLearner(User user){
        Optional<User> accountData = userAccRepository.findByUsername(user.getUsername());
        if(accountData.isPresent()){
            return HttpStatus.IM_USED;
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setProvider(AuthProvider.local);
            permissionService.addLearnerPermission(user.getPermission());
            userAccRepository.save(user);
            return HttpStatus.CREATED;
        }
    }

    public HttpStatus addTutor(User user){
        Optional<User> accountData = userAccRepository.findByUsername(user.getUsername());
        if(accountData.isPresent()){
            return HttpStatus.IM_USED;
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setProvider(AuthProvider.local);
            permissionService.addTutorPermission(user.getPermission());
            userAccRepository.save(user);
            return HttpStatus.CREATED;
        }
    }

//    public void addLearnerAccountData(AccountData accountData) {
//        permissionService.addLearnerPermission(accountData.getPermission());
//        userAccRepository.save(accountData);
//    }

//    public Optional<AccountData> getAccountByUsername(String username){
//        return userAccRepository.findByUsername(username);
//    }

//    public void updateAccountData(String id, AccountData accountData) {
//
//        Optional<AccountData> a = userAccRepository.findById(id);
//
//        if(a.isPresent()) {
//            accountData.setId(a.get().getId());
//            accountData.setPassword(passwordEncoder.encode(accountData.getPassword()));
//
//            userAccRepository.save(accountData);
//        }
//
//    }
//
//    public void removeAccountData(String id) {
//        Optional<AccountData> accountData = userAccRepository.findById(id);
//        userAccRepository.delete(accountData.get());
//    }


}
