package singispace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import singispace.domain.AccountData;
import singispace.domain.LoginAttempt;
import singispace.service.LoginService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(value = "")
    public ResponseEntity<?> login(@RequestBody LoginAttempt loginAttempt) {
        return loginService.authenticateUser(loginAttempt);
    }


}
