package singispace.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import singispace.domain.RegisteredUser;
import singispace.repository.RegisteredUserRepository;


@Service
public class RegisteredUserService {

	@Autowired
	private RegisteredUserRepository registeredUserRepository;

	public RegisteredUserService() {
	}
	
	public Optional<RegisteredUser> getUser(String username) {
		return registeredUserRepository.getByUsername(username);
	}

	public Optional<RegisteredUser> getUser(String username, String password) {
		return registeredUserRepository.getByUsernameAndPassword(username, password);
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
		Optional<RegisteredUser> upRegisteredUser = registeredUserRepository.findById(id);
		if (upRegisteredUser.isPresent()) {
			registeredUser.setId(upRegisteredUser.get().getId());
			registeredUserRepository.save(registeredUser);
		}
	}
	
	
}
