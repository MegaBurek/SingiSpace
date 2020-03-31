package singispace.repositories.users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import singispace.domain.Admin;

import java.security.Permission;
import java.util.Optional;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {

//    @Query("{'name : ?0'}")
//    Admin findAdminByName(String name);

//    Optional<Admin> findAdminByUsername(String username);
}
