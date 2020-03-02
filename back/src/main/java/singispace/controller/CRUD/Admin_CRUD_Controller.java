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

import com.fasterxml.jackson.annotation.JsonView;

import singispace.domain.Admin;
import singispace.service.CRUD.AdminCRUDService;
import singispace.utils.View.HideOptionalProperties;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/admin")
public class Admin_CRUD_Controller {

	@Autowired
	AdminCRUDService adminCRUDService;

	@JsonView(HideOptionalProperties.class)
	@RequestMapping()
	public ResponseEntity<Iterable<Admin>> getAdmins() {
		return new ResponseEntity<Iterable<Admin>>(adminCRUDService.getAdmins(), HttpStatus.OK);
	}

	@JsonView(HideOptionalProperties.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
		Optional<Admin> admin = adminCRUDService.getAdminById(id);
		if (admin.isPresent()) {
			return new ResponseEntity<Admin>(admin.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Admin>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
		adminCRUDService.addAdmin(admin);
		return new ResponseEntity<Admin>(admin, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
		adminCRUDService.updateAdmin(id, admin);
		return new ResponseEntity<Admin>(admin, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Admin> removeAdmin(@PathVariable Long id) {
		try {
			adminCRUDService.removeAdmin(id);
		} catch (Exception e) {
			return new ResponseEntity<Admin>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Admin>(HttpStatus.NO_CONTENT);
	}

}
