package singispace.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import singispace.domain.Admin;
import singispace.domain.Administrator;
import singispace.domain.Learner;
import singispace.service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    //---------ADMIN--------//
    @PostMapping("/admin")
    public ResponseEntity<?> registerAdmin(Admin admin){
        try {
            registerService.addAdmin(admin);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

//    @DeleteMapping("/remove-admin")
//    public ResponseEntity<?> removeAdmin(Admin admin){
//        try {
//            registerService.removeAdmin(admin.getId());
//        }catch(Exception e){
//            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
//        }
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
//
//    @PutMapping(value="/update-admin/{id}")
//    public ResponseEntity<?> updateAccountData(@PathVariable String id, @RequestBody Admin admin) {
//        try {
//            registerService.updateAdmin(id, admin);
//        }catch(Exception e){
//            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
//        }
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }



    //---------ADMINISTRATOR--------//
    @PostMapping("/administrator")
    public ResponseEntity<?> registerAdministrator(Administrator administrator){
        try {
            registerService.addAdministrator(administrator);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

//    @DeleteMapping("/remove-administrator")
//    public ResponseEntity<?> removeAdministrator(Administrator administrator){
//        try {
//            registerService.removeAdministrator(administrator.getId());
//        }catch(Exception e){
//            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
//        }
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
//
//    @PutMapping(value="/update-administrator/{id}")
//    public ResponseEntity<?> updateAdministrator(@PathVariable String id, @RequestBody Administrator administrator) {
//        try {
//            registerService.updateAdministrator(id, administrator);
//        }catch(Exception e){
//            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
//        }
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }

    //---------LEARNER--------//
    @PostMapping("/learner")
    public ResponseEntity<?> registerLearner(Learner learner){
        try {
            registerService.addLearner(learner);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

//    @DeleteMapping("/remove-learner")
//    public ResponseEntity<?> removeLearner(Learner learner){
//        try {
//            registerService.removeAdministrator(learner.getId());
//        }catch(Exception e){
//            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
//        }
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
//
//    @PutMapping(value="/update-learner/{id}")
//    public ResponseEntity<?> updateLearner(@PathVariable String id, @RequestBody Learner learner) {
//        try {
//            registerService.updateLearner(id, learner);
//        }catch(Exception e){
//            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
//        }
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
}
