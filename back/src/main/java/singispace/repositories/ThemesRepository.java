package singispace.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import singispace.domain.Theme;

import java.util.Optional;


@Repository
public interface ThemesRepository  extends MongoRepository<Theme, String> {


//    @Query("{'name : ?0'}")
    Optional<Theme> findByName(String name);


}
