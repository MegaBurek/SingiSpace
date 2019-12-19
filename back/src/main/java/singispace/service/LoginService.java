package singispace.service;

import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import singispace.domain.RegisteredUser;
import singispace.service.RegisteredUserService;
import singispace.DTO.LoginDTO;
import singispace.utils.TokenUtils;

@Service
public class LoginService {


	@Autowired
	RegisteredUserService registereduserService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	public ResponseEntity<HashMap<String, String>> login(LoginDTO set) {
		
			
			
			RegisteredUser user = registereduserService.getUser(set.getUsername()).get();
			
			try {
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(set.getUsername(),
						set.getPassword());
	
				Authentication authentication = authenticationManager.authenticate(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
	
				UserDetails details = userDetailsService.loadUserByUsername(user.getUsername());
				String userToken = tokenUtils.generateToken(details);
	
				HashMap<String, String> data = new HashMap<String, String>();
				data.put("token", userToken);
				
				String role = registereduserService.getUser(user.getUsername()).get().getRole();
				
				data.put("role", role);
				data.put("username", set.getUsername());
	
				return new ResponseEntity<HashMap<String, String>>(data, HttpStatus.OK);
	
			} catch (Exception e) {
				return new ResponseEntity<HashMap<String, String>>(HttpStatus.UNAUTHORIZED);
			}
		}

}
