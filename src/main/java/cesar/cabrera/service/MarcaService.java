package cesar.cabrera.service;

import cesar.cabrera.domain.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Marca.
 */
public interface MarcaService {

    /**
     * Save a marca.
     *
     * @param marca the entity to save
     * @return the persisted entity
     */
    Marca save(Marca marca);

    /**
     *  Get all the marcas.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Marca> findAll(Pageable pageable);

    /**
     *  Get the "id" marca.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Marca findOne(String id);

    /**
     *  Delete the "id" marca.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
