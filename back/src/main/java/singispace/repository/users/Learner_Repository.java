package singispace.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import singispace.domain.Learner;


@Repository
public interface Learner_Repository extends JpaRepository<Learner, Long> {

}
