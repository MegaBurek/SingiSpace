package singispace.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import singispace.domain.AccountData;
import singispace.exception.ResourceNotFoundException;
import singispace.repositories.users.AccountDataRepository;
import singispace.utils.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountDataRepository accountDataRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AccountData accountData = accountDataRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));

        return UserPrincipal.create(accountData);
    }


    @Transactional
    public UserDetails loadUserById(String id) {
        AccountData accountData = accountDataRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        System.out.println("id");
        return UserPrincipal.create(accountData);
    }
}

