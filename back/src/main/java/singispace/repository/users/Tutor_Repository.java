package singispace.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import singispace.domain.Tutor;


@Repository
public interface Tutor_Repository extends JpaRepository<Tutor, Long> {

}
