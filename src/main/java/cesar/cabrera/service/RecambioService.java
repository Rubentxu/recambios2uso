package cesar.cabrera.service;

import cesar.cabrera.domain.Recambio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Recambio.
 */
public interface RecambioService {

    /**
     * Save a recambio.
     *
     * @param recambio the entity to save
     * @return the persisted entity
     */
    Recambio save(Recambio recambio);

    /**
     *  Get all the recambios.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Recambio> findAll(Pageable pageable);

    /**
     *  Get the "id" recambio.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Recambio findOne(String id);

    /**
     *  Delete the "id" recambio.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
