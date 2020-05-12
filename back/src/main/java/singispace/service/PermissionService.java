package singispace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import singispace.domain.Permission;
import singispace.repositories.users.PermissionRepository;

import java.util.Optional;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public void addAdminPermission(Permission permission) {
        permission.setAuthority("ROLE_ADMIN");
        permissionRepository.save(permission);
    }

    public void addTutorPermission(Permission permission) {
        permission.setAuthority("ROLE_TUTOR");
        permissionRepository.save(permission);
    }

    public void addLearnerPermission(Permission permission) {
        permission.setAuthority("ROLE_LEARNER");
        permissionRepository.save(permission);
    }

    public void removePermission(String id){

        Optional<Permission> p = permissionRepository.findById(id);

        if(p.isPresent()){
            permissionRepository.deleteById(id);
        }
    }
}

