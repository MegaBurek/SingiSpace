package singispace.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import singispace.domain.RegisteredUser;


@Repository
public interface RegisteredUser_Repository extends JpaRepository<RegisteredUser, Long> {

}
