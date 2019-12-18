package singispace.controller.CRUD;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import singispace.domain.RegisteredUser;
import singispace.service.CRUD.RegisteredUser_CRUD_Service;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/registeredUser")
public class RegisteredUserController {

    @Autowired
    RegisteredUser_CRUD_Service registeredUserService;

    @RequestMapping()
    public ResponseEntity<Iterable<RegisteredUser>> getRegisteredUser() {
        return new ResponseEntity<Iterable<RegisteredUser>>(registeredUserService.getRegisteredUsers(), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<RegisteredUser> getRegisteredUserById(@PathVariable Long id) {
        Optional<RegisteredUser> registeredUser = registeredUserService.getRegisteredUserById(id);
        if(registeredUser.isPresent()) {
            return new ResponseEntity<RegisteredUser>(registeredUser.get(), HttpStatus.OK);
        }
        return new ResponseEntity<RegisteredUser>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<RegisteredUser> addRegisteredUser(@RequestBody RegisteredUser RegisteredUser) {
        registeredUserService.addRegisteredUser(RegisteredUser);
        return new ResponseEntity<RegisteredUser>(RegisteredUser, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<RegisteredUser> updateRegisteredUser(@PathVariable Long id, @RequestBody RegisteredUser RegisteredUser) {
        registeredUserService.updateRegisteredUser(id, RegisteredUser);
        return new ResponseEntity<RegisteredUser>(RegisteredUser, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<RegisteredUser> removeRegisteredUser(@PathVariable Long id) {
        try {
            registeredUserService.removeRegisteredUser(id);
        }catch (Exception e) {
            return new ResponseEntity<RegisteredUser>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<RegisteredUser>(HttpStatus.NO_CONTENT);
    }
	
}