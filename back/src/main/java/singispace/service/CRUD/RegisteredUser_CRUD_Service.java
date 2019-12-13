package singispace.service.CRUD;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import singispace.domain.RegisteredUser;
import singispace.repository.users.RegisteredUser_Repository;


@Service
public class RegisteredUser_CRUD_Service {

	@Autowired
	private RegisteredUser_Repository registeredUserRepository;

	public RegisteredUser_CRUD_Service() {
	}

	public Iterable<RegisteredUser> getRegisteredUsers() {
		return registeredUserRepository.findAll();
	}

	public Optional<RegisteredUser> getRegisteredUserById(Long id) {
		return registeredUserRepository.findById(id);
	}

	public void addRegisteredUser(RegisteredUser registeredUser) {
		registeredUserRepository.save(registeredUser);
	}

	public void removeRegisteredUser(Long id) {
		Optional<RegisteredUser> registeredUser = registeredUserRepository.findById(id);
		registeredUserRepository.delete(registeredUser.get());
	}

	public void updateRegisteredUser(Long id, RegisteredUser registeredUser) {
		Optional<RegisteredUser> oldRegisteredUser = registeredUserRepository.findById(id);
		if (oldRegisteredUser.isPresent()) {
			registeredUser.setId(oldRegisteredUser.get().getId());
			registeredUserRepository.save(registeredUser);
		}
	}
	
	
}
