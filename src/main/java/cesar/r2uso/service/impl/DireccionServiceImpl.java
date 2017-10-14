package cesar.r2uso.service.impl;

import cesar.r2uso.service.DireccionService;
import cesar.r2uso.domain.Direccion;
import cesar.r2uso.repository.DireccionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing Direccion.
 */
@Service
public class DireccionServiceImpl implements DireccionService{

    private final Logger log = LoggerFactory.getLogger(DireccionServiceImpl.class);

    private final DireccionRepository direccionRepository;

    public DireccionServiceImpl(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    /**
     * Save a direccion.
     *
     * @param direccion the entity to save
     * @return the persisted entity
     */
    @Override
    public Direccion save(Direccion direccion) {
        log.debug("Request to save Direccion : {}", direccion);
        return direccionRepository.save(direccion);
    }

    /**
     *  Get all the direccions.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    public Page<Direccion> findAll(Pageable pageable) {
        log.debug("Request to get all Direccions");
        return direccionRepository.findAll(pageable);
    }

    /**
     *  Get one direccion by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Direccion findOne(String id) {
        log.debug("Request to get Direccion : {}", id);
        return direccionRepository.findOne(id);
    }

    /**
     *  Delete the  direccion by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Direccion : {}", id);
        direccionRepository.delete(id);
    }
}
