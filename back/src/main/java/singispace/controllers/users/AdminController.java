package singispace.controllers.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import singispace.domain.Admin;
import singispace.service.users.AdminService;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    AccountDataController accountDataController;

    @GetMapping(value="/all")
    public ResponseEntity<Iterable<Admin>> getAll() {
        return new ResponseEntity<Iterable<Admin>>(adminService.getAdmins(), HttpStatus.OK);
    }


    @GetMapping(value="/{id}")
    public ResponseEntity<Admin> getById(@PathVariable String id) {
        Optional<Admin> administrator = adminService.getAdminById(id);
        if(administrator.isPresent()) {
            return new ResponseEntity<Admin>(administrator.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Admin>(HttpStatus.NOT_FOUND);
    }


    @PostMapping(value="/register")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin){
        adminService.addAdmin(admin);
        return new ResponseEntity<Admin>(admin, HttpStatus.CREATED);
    }


    @DeleteMapping(value="/{id}")
    public ResponseEntity<Admin> removeAdmin(@PathVariable String id){
        try {
            adminService.removeAdmin(id);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value="/update/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable String id, @RequestBody Admin admin) {
        adminService.updateAdmin(id,admin);
        accountDataController.updateAccountData(admin.getAccountData().getId(), admin.getAccountData());
        return new ResponseEntity<Admin>(admin,HttpStatus.OK);
    }
}
