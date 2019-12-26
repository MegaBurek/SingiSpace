package singispace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import singispace.domain.RegisteredUser;

//Promenio sam u CrudRepository interface
@Repository
public interface RegisteredUserRepository extends CrudRepository<RegisteredUser, Long> {
	Optional<RegisteredUser> getByUsername(String username);
	Optional<RegisteredUser> getByUsernameAndPassword(String username, String password);
}
