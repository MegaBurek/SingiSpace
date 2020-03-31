package singispace.repositories.users;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import singispace.domain.AccountData;

import java.security.Permission;
import java.util.Optional;

@Repository
public interface AccountDataRepository extends MongoRepository<AccountData, String> {

    Optional<AccountData> findByUsername (String username);

}
