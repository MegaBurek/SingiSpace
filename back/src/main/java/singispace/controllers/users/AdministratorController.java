package singispace.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import singispace.domain.Administrator;
import singispace.repositories.users.AdministratorRepository;

import java.util.List;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    public AdministratorRepository administratorRepository;

    @GetMapping("/all")
    public List<Administrator> getAll(){
        List<Administrator> users = this.administratorRepository.findAll();

        return users;
    }

    @PutMapping
    public void insert(@RequestBody Administrator administrator){
        this.administratorRepository.insert(administrator);
    }

    @PostMapping
    public void update(@RequestBody Administrator administrator){
        this.administratorRepository.save(administrator);
    }
}
