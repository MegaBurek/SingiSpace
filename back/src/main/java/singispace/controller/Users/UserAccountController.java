package singispace.controller.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import singispace.domain.Users;
import singispace.repository.users.UserAccountRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-account")
public class UserAccountController {

    @Autowired
    public UserAccountRepository userAccountRepository;

    @GetMapping("/all")
    public List<Users> getAll(){
        List<Users> users = this.userAccountRepository.findAll();

        return users;
    }

    @PutMapping
    public void insert(@RequestBody Users user){
        this.userAccountRepository.insert(user);
    }

    @PostMapping
    public void update(@RequestBody Users user){
        this.userAccountRepository.save(user);
    }

    @GetMapping("/{id}")
    public Optional<Users> getById(@PathVariable("id") String id){
        Optional<Users> user = this.userAccountRepository.findById(id);

        return user;
    }
}
