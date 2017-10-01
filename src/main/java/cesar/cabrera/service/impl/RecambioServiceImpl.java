package cesar.cabrera.service.impl;

import cesar.cabrera.service.RecambioService;
import cesar.cabrera.domain.Recambio;
import cesar.cabrera.repository.RecambioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing Recambio.
 */
@Service
public class RecambioServiceImpl implements RecambioService{

    private final Logger log = LoggerFactory.getLogger(RecambioServiceImpl.class);

    private final RecambioRepository recambioRepository;

    public RecambioServiceImpl(RecambioRepository recambioRepository) {
        this.recambioRepository = recambioRepository;
    }

    /**
     * Save a recambio.
     *
     * @param recambio the entity to save
     * @return the persisted entity
     */
    @Override
    public Recambio save(Recambio recambio) {
        log.debug("Request to save Recambio : {}", recambio);
        return recambioRepository.save(recambio);
    }

    /**
     *  Get all the recambios.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    public Page<Recambio> findAll(Pageable pageable) {
        log.debug("Request to get all Recambios");
        return recambioRepository.findAll(pageable);
    }

    /**
     *  Get one recambio by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Recambio findOne(String id) {
        log.debug("Request to get Recambio : {}", id);
        return recambioRepository.findOne(id);
    }

    /**
     *  Delete the  recambio by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Recambio : {}", id);
        recambioRepository.delete(id);
    }
}
