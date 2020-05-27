package singispace.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import singispace.domain.Page;

@Repository
public interface PagesRepository extends MongoRepository<Page, String> {
}
