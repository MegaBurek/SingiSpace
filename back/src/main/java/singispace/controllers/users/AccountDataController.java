package singispace.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import singispace.domain.AccountData;
import singispace.repositories.users.AccountDataRepository;

import java.util.List;

@RestController
@RequestMapping("/account-data")
public class AccountDataController {

    @Autowired
    public AccountDataRepository accountDataRepository;

    @GetMapping("/all")
    public List<AccountData> getAll(){
        List<AccountData> users = this.accountDataRepository.findAll();

        return users;
    }

    @PutMapping
    public void insert(@RequestBody AccountData accountData){
        this.accountDataRepository.insert(accountData);
    }

    @PostMapping
    public void update(@RequestBody AccountData accountData){
        this.accountDataRepository.save(accountData);
    }

}
