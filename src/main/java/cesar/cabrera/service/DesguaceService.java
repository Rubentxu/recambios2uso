package cesar.cabrera.service;

import cesar.cabrera.domain.Desguace;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Desguace.
 */
public interface DesguaceService {

    /**
     * Save a desguace.
     *
     * @param desguace the entity to save
     * @return the persisted entity
     */
    Desguace save(Desguace desguace);

    /**
     *  Get all the desguaces.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Desguace> findAll(Pageable pageable);

    /**
     *  Get the "id" desguace.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Desguace findOne(String id);

    /**
     *  Delete the "id" desguace.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
