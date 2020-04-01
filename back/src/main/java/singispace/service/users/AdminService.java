package singispace.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import singispace.domain.AccountData;
import singispace.domain.Admin;
import singispace.domain.AuthProvider;
import singispace.repositories.users.AdminRepository;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountDataService accountDataService;

    public AdminService(){}

    public Iterable<Admin> getAdmins(){
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(String id){
        return adminRepository.findById(id);
    }

    public HttpStatus addAdmin(Admin admin){

        Optional<AccountData> accountData = accountDataService.getAccountByUsername(admin.getAccountData().getUsername());

        if(accountData.isPresent()){
            return HttpStatus.IM_USED;
        }
        else {
            admin.getAccountData().setPassword(passwordEncoder.encode(admin.getAccountData().getPassword()));
            admin.getAccountData().setProvider(AuthProvider.local);
            accountDataService.addAdminAccountData(admin.getAccountData());
            adminRepository.save(admin);
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

        Optional<Admin> a = adminRepository.findById(id);

        if(a.isPresent()) {
            admin.setId(a.get().getId());
            accountDataService.updateAccountData(a.get().getAccountData().getId(), a.get().getAccountData());

            adminRepository.save(admin);
        }
    }


}
