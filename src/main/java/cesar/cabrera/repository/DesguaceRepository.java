package cesar.cabrera.repository;

import cesar.cabrera.domain.Desguace;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Desguace entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DesguaceRepository extends MongoRepository<Desguace, String> {

}
