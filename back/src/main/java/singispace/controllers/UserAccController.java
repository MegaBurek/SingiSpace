package singispace.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import singispace.domain.User;
import singispace.service.UserAccService;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserAccController {

    @Autowired
    UserAccService userAccService;

    @GetMapping(value="/all")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return new ResponseEntity<Iterable<User>>(userAccService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> accountData = userAccService.getById(id);
        if(accountData.isPresent()) {
            return new ResponseEntity<User>(accountData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/register/admin")
    public ResponseEntity<User> addAdmin(@RequestBody User user){
        userAccService.addAdmin(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @PostMapping(value="/register/tutor")
    public ResponseEntity<User> addTutor(@RequestBody User user){
        userAccService.addTutor(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @PostMapping(value="/register/learner")
    public ResponseEntity<User> addLearner(@RequestBody User user){
        userAccService.addLearner(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/remove/{id}")
    public ResponseEntity<User> removeUser(@PathVariable String id){
        try {
            userAccService.removeUserAccData(id);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable String id, @RequestBody User user) {
        userAccService.updateUserAccData(id,user);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }


}
