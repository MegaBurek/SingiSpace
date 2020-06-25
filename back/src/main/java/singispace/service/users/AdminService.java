package singispace.service.users;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import singispace.domain.User;
import singispace.dto.UserViewDTO;
import singispace.service.PermissionService;
import singispace.service.UserAccService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    PermissionService permissionService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserAccService userAccService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    public AdminService(){}


    public Iterable<UserViewDTO> getAllAdmins() {
        List<UserViewDTO> adminsView = new ArrayList();
        Query query = new Query();
        query.addCriteria(Criteria.where("permission.authority").is("ROLE_ADMIN"));
        List<User> users = mongoTemplate.find(query, User.class);
        for(User user : users){
            adminsView.add(convertToDto(user));
        }
        return adminsView;
    }

    public Iterable<UserViewDTO> getAllLearners() {
        List<UserViewDTO> adminsView = new ArrayList();
        Query query = new Query();
        query.addCriteria(Criteria.where("permission.authority").is("ROLE_LEARNER"));
        List<User> users = mongoTemplate.find(query, User.class);
        for(User user : users){
            adminsView.add(convertToDto(user));
        }
        return adminsView;
    }

    public Iterable<UserViewDTO> getAllTutors() {
        List<UserViewDTO> adminsView = new ArrayList();
        Query query = new Query();
        query.addCriteria(Criteria.where("permission.authority").is("ROLE_TUTOR"));
        List<User> users = mongoTemplate.find(query, User.class);
        for(User user : users){
            adminsView.add(convertToDto(user));
        }
        return adminsView;
    }

    private UserViewDTO convertToDto(User user) {
        UserViewDTO userViewDTO = modelMapper.map(user, UserViewDTO.class);
        return userViewDTO;
    }
}
