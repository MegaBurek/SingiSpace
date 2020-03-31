package singispace.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import singispace.domain.Administrator;
import singispace.repositories.users.AdministratorRepository;

import java.util.Optional;

@Service
public class AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    public AdministratorService() {
    }

    public Iterable<Administrator> getAdministrators() {
        return administratorRepository.findAll();
    }

    public Optional<Administrator> getAdministratorById(String id) {
        return administratorRepository.findById(id);
    }

}
