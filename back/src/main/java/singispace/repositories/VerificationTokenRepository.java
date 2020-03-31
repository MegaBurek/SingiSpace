package singispace.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import singispace.domain.VerificationToken;

import java.util.Date;

public interface VerificationTokenRepository extends MongoRepository<VerificationToken, String> {

    VerificationToken findByToken(String token);

//    @Query("DELETE from VerificationToken vt WHERE vt.expiryDate <= ?1")
//    void deleteAllExpiredSince(Date now);



}
