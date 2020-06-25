package singispace.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import singispace.controllers.UserAccController;
import singispace.dto.UserViewDTO;
import singispace.service.users.AdminService;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserAccController userAccController;

    @GetMapping(value = "/getAdmins")
    public ResponseEntity<Iterable<UserViewDTO>> getAllAdmins() {
        return new ResponseEntity<Iterable<UserViewDTO>>(adminService.getAllAdmins(), HttpStatus.OK);
    }

    @GetMapping(value = "/getLearners")
    public ResponseEntity<Iterable<UserViewDTO>> getAllLearners() {
        return new ResponseEntity<Iterable<UserViewDTO>>(adminService.getAllLearners(), HttpStatus.OK);
    }

    @GetMapping(value = "/getTutors")
    public ResponseEntity<Iterable<UserViewDTO>> getAllTutors() {
        return new ResponseEntity<Iterable<UserViewDTO>>(adminService.getAllTutors(), HttpStatus.OK);
    }

//    @GetMapping(value="/all")
//    public ResponseEntity<Iterable<Admin>> getAll() {
//        return new ResponseEntity<Iterable<Admin>>(adminService.getAdmins(), HttpStatus.OK);
//    }
//
//
//    @GetMapping(value="/{id}")
//    public ResponseEntity<Admin> getById(@PathVariable String id) {
//        Optional<Admin> administrator = adminService.getAdminById(id);
//        if(administrator.isPresent()) {
//            return new ResponseEntity<Admin>(administrator.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<Admin>(HttpStatus.NOT_FOUND);
//    }
//
//
//    @PostMapping(value="/register")
//    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin){
//        adminService.addAdmin(admin);
//        return new ResponseEntity<Admin>(admin, HttpStatus.CREATED);
//    }
//
//
//    @DeleteMapping(value="/remove/{id}")
//    public ResponseEntity<Admin> removeAdmin(@PathVariable String id){
//        try {
//            adminService.removeAdmin(id);
//        }catch(Exception e){
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
//
//    @PutMapping(value="/update/{id}")
//    public ResponseEntity<?> updateAdmin(@PathVariable String id, @RequestBody Admin admin) {
//        adminService.updateAdmin(id,admin);
//        userAccController.updateAccountData(admin.getAccountData().getId(), admin.getAccountData());
//        return new ResponseEntity<Admin>(admin,HttpStatus.OK);
//    }
}
