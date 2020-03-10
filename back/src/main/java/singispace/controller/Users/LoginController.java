package singispace.controller.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import singispace.domain.LoginAttempt;
import singispace.service.login.LoginService;

import java.util.HashMap;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, String>> login(@RequestBody LoginAttempt loginAttempt) {
        return loginService.login(loginAttempt);
    }

}
