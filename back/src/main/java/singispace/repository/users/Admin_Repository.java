package singispace.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import singispace.domain.Admin;


@Repository
public interface Admin_Repository extends JpaRepository<Admin, Long> {

}

