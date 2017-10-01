package cesar.cabrera.repository;

import cesar.cabrera.domain.Marca;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Marca entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MarcaRepository extends MongoRepository<Marca, String> {

}
