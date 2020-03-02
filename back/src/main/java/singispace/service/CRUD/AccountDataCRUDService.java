package singispace.service.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import singispace.repository.users.AccountDataRepository;

@Service
public class AccountDataCRUDService {
		
	@Autowired
	AccountDataRepository accountDataRepository;
	
	
}
