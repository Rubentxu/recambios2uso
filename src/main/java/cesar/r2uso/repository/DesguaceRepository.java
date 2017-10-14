package cesar.r2uso.repository;

import cesar.r2uso.domain.Desguace;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Desguace entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DesguaceRepository extends MongoRepository<Desguace, String> {

}
