package singispace.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import singispace.controllers.UserAccController;
import singispace.service.users.TutorService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    public TutorService tutorService;

    @Autowired
    UserAccController userAccController;

//    @GetMapping(value="/all")
//    public ResponseEntity<Iterable<Administrator>> getAll() {
//        return new ResponseEntity<Iterable<Administrator>>(administratorService.getAdministrators(), HttpStatus.OK);
//    }
//
//
//    @GetMapping(value="/{id}")
//    public ResponseEntity<Administrator> getById(@PathVariable String id) {
//        Optional<Administrator> administrator = administratorService.getAdministratorById(id);
//        if(administrator.isPresent()) {
//            return new ResponseEntity<Administrator>(administrator.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<Administrator>(HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<Administrator> registerAdministrator(@RequestBody Administrator administrator){
//        administratorService.addAdministrator(administrator);
//        return new ResponseEntity<Administrator>(administrator, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/remove/{id}")
//    public ResponseEntity<Administrator> removeAdministrator(@PathVariable String id){
//        try {
//            administratorService.removeAdministrator(id);
//        }catch(Exception e){
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
//
//    @PutMapping(value="/update/{id}")
//    public ResponseEntity<?> updateAdministrator(@PathVariable String id, @RequestBody Administrator administrator) {
//        administratorService.updateAdministrator(id, administrator);
//        userAccController.updateAccountData(administrator.getAccountData().getId(),administrator.getAccountData());
//        return new ResponseEntity<Administrator>(administrator,HttpStatus.OK);
//    }
}
