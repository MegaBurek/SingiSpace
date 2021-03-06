package singispace.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import singispace.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
