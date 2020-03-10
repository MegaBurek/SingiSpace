package singispace.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import singispace.domain.LoginAttempt;
import singispace.domain.Users;
import singispace.repository.users.UserAccountRepository;
import singispace.utils.TokenUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Component
public class LoginService implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private TokenUtils tokenUtils;


    public ResponseEntity<HashMap<String, String>> login(LoginAttempt loginAttempt) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginAttempt.getUsername(),
                    loginAttempt.getPassword());

            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails details = userDetailsService.loadUserByUsername(loginAttempt.getUsername());
            String userToken = tokenUtils.generateToken(details);

            HashMap<String, String> data = new HashMap<String, String>();
            data.put("token", userToken);

            return new ResponseEntity<HashMap<String, String>>(data, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<HashMap<String, String>>(HttpStatus.UNAUTHORIZED);
        }
    }

//    public void addPermsion(LoginAttempt loginAttempt, String role) {
//        accountData = accountRepository.save(accountData);
//        accountData.setUserPermission(new HashSet<UserPermission>());
//        accountData.getUserPermission().add(new UserPermission(accountData, permissionRepository.findByRoleType(role).get()));
//        accountRepository.save(accountData);
//    }



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
