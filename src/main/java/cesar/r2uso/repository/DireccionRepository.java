package cesar.r2uso.repository;

import cesar.r2uso.domain.Direccion;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Direccion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DireccionRepository extends MongoRepository<Direccion, String> {

}
