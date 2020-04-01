package singispace.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import singispace.domain.AccountData;
import singispace.repositories.users.AccountDataRepository;

import java.util.Optional;

@Service
public class AccountDataService {

    @Autowired
    AccountDataRepository accountDataRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Iterable<AccountData> getAllAccoutDatas() {
        return accountDataRepository.findAll();
    }

    public Optional<AccountData> getAccountByUsername(String username){
        return accountDataRepository.findByUsername(username);
    }

    public Optional<AccountData> getAccountDataById(String id) {
        return accountDataRepository.findById(id);
    }

    public void addAccountData(AccountData accountData) {
        accountDataRepository.save(accountData);

    }

    public void removeAccountData(String id) {
        Optional<AccountData> accountData = accountDataRepository.findById(id);
        accountDataRepository.delete(accountData.get());
    }

    public void updateAccountData(String id, AccountData accountData) {
        Optional<AccountData> Acc = accountDataRepository.findById(id);
        if(Acc.isPresent()) {
            accountData.setId(Acc.get().getId());
            accountDataRepository.save(accountData);
        }
    }
}

