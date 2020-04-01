package singispace.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import singispace.domain.*;
import singispace.repositories.users.AccountDataRepository;
import singispace.service.PermissionService;

import java.util.Optional;

@Service
public class AccountDataService {

    @Autowired
    PermissionService permissionService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountDataRepository accountDataRepository;

    public Iterable<AccountData> getAll() {
        return accountDataRepository.findAll();
    }

    public Optional<AccountData> getById(String id) {
        return accountDataRepository.findById(id);
    }

    public void addAdminAccountData(AccountData accountData) {
        System.out.println(accountData);
        permissionService.addAdminPermission(accountData.getPermission());
        accountDataRepository.save(accountData);

    }

    public void addAdministratorAccountData(AccountData accountData) {
        permissionService.addAdministratorPermission(accountData.getPermission());
        accountDataRepository.save(accountData);
    }

    public void addLearnerAccountData(AccountData accountData) {
        permissionService.addLearnerPermission(accountData.getPermission());
        accountDataRepository.save(accountData);
    }

    public Optional<AccountData> getAccountByUsername(String username){
        return accountDataRepository.findByUsername(username);
    }

    public void updateAccountData(String id, AccountData accountData) {

        Optional<AccountData> a = accountDataRepository.findById(id);

        if(a.isPresent()) {
            accountData.setId(a.get().getId());
            accountData.setPassword(passwordEncoder.encode(accountData.getPassword()));

            accountDataRepository.save(accountData);
        }
    }

    public void removeAccountData(String id) {
        Optional<AccountData> accountData = accountDataRepository.findById(id);
        accountDataRepository.delete(accountData.get());
    }


}
