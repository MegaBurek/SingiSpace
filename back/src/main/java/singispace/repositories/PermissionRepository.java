package singispace.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import singispace.domain.Permission;


@Repository
public interface PermissionRepository extends MongoRepository<Permission, String> {

}

