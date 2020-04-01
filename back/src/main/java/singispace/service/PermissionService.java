package singispace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import singispace.domain.Permission;
import singispace.repositories.PermissionRepository;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public void addAdminPermission(Permission permission) {
        permission.setAuthority("ROLE_ADMIN");
        permissionRepository.save(permission);
    }

    public void addAdministratorPermission(Permission permission) {
        permission.setAuthority("ROLE_ADMINISTRATOR");
        permissionRepository.save(permission);
    }

    public void addLearnerPermission(Permission permission) {
        permission.setAuthority("ROLE_LEARNER");
        permissionRepository.save(permission);
    }
}

