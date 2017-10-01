package cesar.cabrera.repository;

import cesar.cabrera.domain.Recambio;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Recambio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecambioRepository extends MongoRepository<Recambio, String> {

}
