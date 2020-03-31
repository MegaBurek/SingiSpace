package singispace.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import singispace.domain.AccountData;
import singispace.domain.Admin;
import singispace.repositories.users.AccountDataRepository;
import singispace.repositories.users.AdminRepository;

import java.util.Optional;

public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AccountDataRepository accountDataRepository;

    public AdminService(){}

    public Iterable<Admin> getAdmins(){
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(String id){
        return adminRepository.findById(id);
    }

}
