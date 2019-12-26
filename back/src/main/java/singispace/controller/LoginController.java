package singispace.controller;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import singispace.DTO.LoginDTO;
import singispace.domain.RegisteredUser;
import singispace.service.RegisteredUserService;
import singispace.service.LoginService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
public class LoginController {
	
	private RegisteredUserService registeredUserService;
	private LoginService loginService;
	
	@PostMapping(value = "/login")
	ResponseEntity<HashMap<String, String>> login(@RequestBody LoginDTO set) {
		
		Optional<RegisteredUser> user = registeredUserService.getUser(set.getUsername());
		
		if(user.isPresent())
		{
			return loginService.login(set);
		}
		
		return new ResponseEntity<HashMap<String,String>>(HttpStatus.NOT_FOUND);
		
	}

}
