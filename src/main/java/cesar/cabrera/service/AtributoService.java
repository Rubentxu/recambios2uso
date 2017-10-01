package cesar.cabrera.service;

import cesar.cabrera.domain.Atributo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Atributo.
 */
public interface AtributoService {

    /**
     * Save a atributo.
     *
     * @param atributo the entity to save
     * @return the persisted entity
     */
    Atributo save(Atributo atributo);

    /**
     *  Get all the atributos.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Atributo> findAll(Pageable pageable);

    /**
     *  Get the "id" atributo.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Atributo findOne(String id);

    /**
     *  Delete the "id" atributo.
     *
     *  @param id the id of the entity
     */
    void delete(String id);
}
