package singispace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import singispace.domain.LoginAttempt;
import singispace.domain.User;
import singispace.payload.AuthResponse;
import singispace.repositories.users.PermissionRepository;
import singispace.repositories.users.UserAccRepository;
import singispace.utils.TokenProvider;

import javax.validation.Valid;


@Service
public class LoginService{

    @Autowired
    private UserAccRepository accountRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private PermissionRepository permissionRepository;


    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginAttempt loginAttempt) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginAttempt.getUsername(),
                        loginAttempt.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);
        System.out.println("here"+token);
        return ResponseEntity.ok(new AuthResponse(token));
    }


}
