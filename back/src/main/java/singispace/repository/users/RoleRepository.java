package singispace.repository.users;

import org.springframework.data.mongodb.repository.MongoRepository;
import singispace.domain.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);
}
