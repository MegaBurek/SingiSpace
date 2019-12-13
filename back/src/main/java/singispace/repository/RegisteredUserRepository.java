package singispace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import singispace.domain.RegisteredUser;


@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {

}
