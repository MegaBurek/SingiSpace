package singispace.repositories.users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import singispace.domain.Learner;

import java.util.Optional;

public interface LearnerRepository extends MongoRepository<Learner, String> {

}
