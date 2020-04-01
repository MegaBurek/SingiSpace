package singispace.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import singispace.domain.AccountData;
import singispace.domain.Administrator;
import singispace.domain.AuthProvider;
import singispace.repositories.users.AdministratorRepository;

import java.util.Optional;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountDataService accountDataService;

    public AdministratorService() {
    }

    public Iterable<Administrator> getAdministrators() {
        return administratorRepository.findAll();
    }

    public Optional<Administrator> getAdministratorById(String id) {
        return administratorRepository.findById(id);
    }

    public HttpStatus addAdministrator(Administrator administrator){
        Optional<AccountData> accountData = accountDataService.getAccountByUsername(administrator.getAccountData().getUsername());

        if(accountData.isPresent()){
            return HttpStatus.IM_USED;
        }
        else {
            administrator.getAccountData().setPassword(passwordEncoder.encode(administrator.getAccountData().getPassword()));
            administrator.getAccountData().setProvider(AuthProvider.local);
            accountDataService.addAdministratorAccountData(administrator.getAccountData());
            administratorRepository.save(administrator);
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

        Optional<Administrator> a = administratorRepository.findById(id);

        if(a.isPresent()) {
            administrator.setId(a.get().getId());
            accountDataService.updateAccountData(a.get().getAccountData().getId(), a.get().getAccountData());

            administratorRepository.save(administrator);
        }
    }

}
