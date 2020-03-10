package singispace.repository.users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import singispace.domain.Users;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends MongoRepository<Users,String>{
    Optional<Users> findById(String id);

    Users findByUsername(String username);
}
