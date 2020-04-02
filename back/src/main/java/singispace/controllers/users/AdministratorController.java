package singispace.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import singispace.domain.Administrator;
import singispace.service.users.AdministratorService;

import java.util.Optional;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    public AdministratorService administratorService;

    @GetMapping(value="/all")
    public ResponseEntity<Iterable<Administrator>> getAll() {
        return new ResponseEntity<Iterable<Administrator>>(administratorService.getAdministrators(), HttpStatus.OK);
    }


    @GetMapping(value="/{id}")
    public ResponseEntity<Administrator> getById(@PathVariable String id) {
        Optional<Administrator> administrator = administratorService.getAdministratorById(id);
        if(administrator.isPresent()) {
            return new ResponseEntity<Administrator>(administrator.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Administrator>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register")
    public ResponseEntity<Administrator> registerAdministrator(@RequestBody Administrator administrator){
        administratorService.addAdministrator(administrator);
        return new ResponseEntity<Administrator>(administrator, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Administrator> removeAdministrator(@PathVariable String id){
        try {
            administratorService.removeAdministrator(id);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<?> updateAccountData(@PathVariable String id, @RequestBody Administrator administrator) {

        administratorService.updateAdministrator(id, administrator);

    }
}
