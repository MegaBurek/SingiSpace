package singispace.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import singispace.domain.Tutor;


@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {

}
