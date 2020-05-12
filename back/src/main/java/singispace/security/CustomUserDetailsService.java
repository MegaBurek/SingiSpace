package singispace.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import singispace.domain.User;
import singispace.exception.ResourceNotFoundException;
import singispace.repositories.users.UserAccRepository;
import singispace.utils.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAccRepository userAccRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userAccRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));

        return UserPrincipal.create(user);
    }


    @Transactional
    public UserDetails loadUserById(String id) {
        User user = userAccRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        System.out.println("id");
        return UserPrincipal.create(user);
    }
}

