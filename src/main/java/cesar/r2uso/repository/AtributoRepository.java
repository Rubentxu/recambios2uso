package cesar.r2uso.repository;

import cesar.r2uso.domain.Atributo;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Atributo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AtributoRepository extends MongoRepository<Atributo, String> {

}
