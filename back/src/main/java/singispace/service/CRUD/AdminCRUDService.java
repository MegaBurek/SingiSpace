package singispace.service.CRUD;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import singispace.domain.Admin;
import singispace.repository.users.AdminRepository;

@Service
public class AdminCRUDService {

	@Autowired
	private AdminRepository adminRepository;

	public AdminCRUDService() {
	}

	public Iterable<Admin> getAdmins() {
		return adminRepository.findAll();
	}

	public Optional<Admin> getAdminById(Long id) {
		return adminRepository.findById(id);
	}

	public void addAdmin(Admin admin) {
		adminRepository.save(admin);
	}

	public void removeAdmin(Long id) {
		Optional<Admin> admin = adminRepository.findById(id);
		adminRepository.delete(admin.get());
	}

	public void updateAdmin(Long id, Admin admin) {
		Optional<Admin> old_admin = adminRepository.findById(id);
		if (old_admin.isPresent()) {
			admin.setId(old_admin.get().getId());
			adminRepository.save(admin);
		}
	}
	
	
}
