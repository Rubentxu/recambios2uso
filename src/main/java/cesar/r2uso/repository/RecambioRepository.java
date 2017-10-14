package cesar.r2uso.repository;

import cesar.r2uso.domain.Recambio;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Recambio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecambioRepository extends MongoRepository<Recambio, String> {

}
