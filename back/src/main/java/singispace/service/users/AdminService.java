package singispace.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import singispace.service.PermissionService;
import singispace.service.UserAccService;

@Service
public class AdminService {

    @Autowired
    PermissionService permissionService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserAccService userAccService;

    public AdminService(){}




}
