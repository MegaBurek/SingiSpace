package singispace.repositories.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import singispace.domain.Administrator;

import java.util.Optional;

public interface AdministratorRepository extends MongoRepository<Administrator, String> {


}
