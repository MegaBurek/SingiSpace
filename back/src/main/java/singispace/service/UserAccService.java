package singispace.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import singispace.domain.*;
import singispace.dto.FriendDTO;
import singispace.dto.UserViewDTO;
import singispace.repositories.users.UserAccRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccService {

    @Autowired
    PermissionService permissionService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserAccRepository userAccRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Iterable<User> getAll() {
        return userAccRepository.findAll();
    }

    public Optional<User> getById(String id) {
        return userAccRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userAccRepository.findByUsername(username);
    }

    public Optional<UserViewDTO> findUserViewByUsername(String username) {
        Optional<User> user = findByUsername(username);
        return Optional.of(convertToDto(user.get()));
    }

    public void addUser(User user) {
        userAccRepository.save(user);
    }

    public void removeUserAccData(String id) {
        Optional<User> user = userAccRepository.findById(id);
        userAccRepository.delete(user.get());
    }

    public void updateUserAccData(String id, User user) {

        Optional<User> a = userAccRepository.findById(id);

        if (a.isPresent()) {
            user.setId(a.get().getId());
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userAccRepository.save(user);
        }

    }

    public HttpStatus addAdmin(User user) {

        Optional<User> accountData = userAccRepository.findByUsername(user.getUsername());
        if (accountData.isPresent()) {
            return HttpStatus.IM_USED;
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setProvider(AuthProvider.local);
            permissionService.addAdminPermission(user.getPermission());
            userAccRepository.save(user);
            return HttpStatus.CREATED;
        }
    }

    public HttpStatus addLearner(User user) {
        Optional<User> accountData = userAccRepository.findByUsername(user.getUsername());
        if (accountData.isPresent()) {
            return HttpStatus.IM_USED;
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setProvider(AuthProvider.local);
            permissionService.addLearnerPermission(user.getPermission());
            userAccRepository.save(user);
            return HttpStatus.CREATED;
        }
    }

    public HttpStatus addTutor(User user) {
        Optional<User> accountData = userAccRepository.findByUsername(user.getUsername());
        if (accountData.isPresent()) {
            return HttpStatus.IM_USED;
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setProvider(AuthProvider.local);
            permissionService.addTutorPermission(user.getPermission());
            userAccRepository.save(user);
            return HttpStatus.CREATED;
        }
    }

    private UserViewDTO convertToDto(User user) {
        UserViewDTO userViewDTO = modelMapper.map(user, UserViewDTO.class);
        return userViewDTO;
    }


}
