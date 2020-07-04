package singispace.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import singispace.domain.Page;

import java.util.Optional;

@Repository
public interface PagesRepository extends MongoRepository<Page, String> {

    Optional<Page> findByName(String name);
}
