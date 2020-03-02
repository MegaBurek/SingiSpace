package singispace.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import singispace.domain.AccountData;

@Repository
public interface AccountDataRepository extends JpaRepository<AccountData, Long> {

	
	
}
