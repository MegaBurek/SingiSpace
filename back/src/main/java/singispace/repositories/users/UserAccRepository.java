package singispace.repositories.users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import singispace.domain.User;

import java.util.Optional;

@Repository
public interface UserAccRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername (String username);


}
