package cesar.cabrera.repository;

import cesar.cabrera.domain.Direccion;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Direccion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DireccionRepository extends MongoRepository<Direccion, String> {

}
