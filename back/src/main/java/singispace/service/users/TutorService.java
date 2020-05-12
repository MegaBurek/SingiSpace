package singispace.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import singispace.domain.AuthProvider;
import singispace.service.PermissionService;
import singispace.service.UserAccService;

import java.util.Optional;

@Service
public class TutorService {


    @Autowired
    PermissionService permissionService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserAccService userAccService;

    public TutorService() {
    }


}
