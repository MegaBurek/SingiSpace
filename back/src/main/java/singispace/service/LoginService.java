package singispace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import singispace.domain.AccountData;
import singispace.domain.Role;
import singispace.domain.UserPermission;
import singispace.payload.AuthResponse;
import singispace.repositories.users.AccountDataRepository;
import singispace.repositories.PermissionRepository;
import singispace.repositories.RoleRepository;
import singispace.utils.TokenProvider;

import java.util.*;


@Component
public class LoginService implements UserDetailsService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private AccountDataRepository accountDataRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    //save separate users
    public void saveUser(AccountData user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        accountDataRepository.save(user);
    }

    public ResponseEntity<?> login(UserDetails userDetails) {



        Optional<AccountData> accountData = accountDataRepository.findByUsername(userDetails.getUsername());
        if(accountData.isPresent()){
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userDetails.getUsername(),
                                userDetails.getPassword()
                        )
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

                String token = tokenProvider.createToken(authentication);
                return ResponseEntity.ok(new AuthResponse(token));


            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    public void addPermssion(AccountData accountData, String authority) {
        accountData = accountDataRepository.save(accountData);
        accountData.setUserPermissions(new HashSet<UserPermission>());
        accountData.getUserPermissions().add(new UserPermission(accountData, permissionRepository.findByAuthority(authority)));
        accountDataRepository.save(accountData);
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user = userRepository.findByUsername(username);
//        if(user != null) {
//            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
//            return buildUserForAuthentication(user, authorities);
//        } else {
//            throw new UsernameNotFoundException("username not found");
//        }
//    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(AccountData accountData, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(accountData.getUsername(), accountData.getPassword(), authorities);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
