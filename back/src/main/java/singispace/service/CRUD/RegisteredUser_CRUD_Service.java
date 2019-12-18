package singispace.service.CRUD;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import singispace.DTO.RegisteredUserDTO;
import singispace.domain.RegisteredUser;
import singispace.repository.users.RegisteredUser_Repository;


@Service
public class RegisteredUser_CRUD_Service {

	@Autowired
	private RegisteredUser_Repository registeredUserRepository;
	
	@Autowired
	private ModelMapper modelMapper;

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
	
	public Iterable<RegisteredUserDTO> getUsersDTO() {
		Iterable<RegisteredUser> users_back = registeredUserRepository.findAll();
		Set<RegisteredUserDTO> users_front = new HashSet<>();
		for(RegisteredUser ru: users_back)
			users_front.add(convertToDTO(ru));
		return users_front;
	}
	
	public RegisteredUserDTO convertToDTO(RegisteredUser registeredUser)
	{
		RegisteredUserDTO rdto = modelMapper.map(registeredUser, RegisteredUserDTO.class);
		rdto.setSubmissionDate(registeredUser.getDoB());
		return rdto;
	}
	
	public void removeUserSoft(Long id) {
		Optional<RegisteredUser> u = registeredUserRepository.findById(id);
		if(u.isPresent()) {
			u.get().setDeleted(true);
			registeredUserRepository.save(u.get());
		}
	}
	
	
}
