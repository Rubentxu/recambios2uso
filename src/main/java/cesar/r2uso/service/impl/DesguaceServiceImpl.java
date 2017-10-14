package cesar.r2uso.service.impl;

import cesar.r2uso.service.DesguaceService;
import cesar.r2uso.domain.Desguace;
import cesar.r2uso.repository.DesguaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing Desguace.
 */
@Service
public class DesguaceServiceImpl implements DesguaceService{

    private final Logger log = LoggerFactory.getLogger(DesguaceServiceImpl.class);

    private final DesguaceRepository desguaceRepository;

    public DesguaceServiceImpl(DesguaceRepository desguaceRepository) {
        this.desguaceRepository = desguaceRepository;
    }

    /**
     * Save a desguace.
     *
     * @param desguace the entity to save
     * @return the persisted entity
     */
    @Override
    public Desguace save(Desguace desguace) {
        log.debug("Request to save Desguace : {}", desguace);
        return desguaceRepository.save(desguace);
    }

    /**
     *  Get all the desguaces.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    public Page<Desguace> findAll(Pageable pageable) {
        log.debug("Request to get all Desguaces");
        return desguaceRepository.findAll(pageable);
    }

    /**
     *  Get one desguace by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Desguace findOne(String id) {
        log.debug("Request to get Desguace : {}", id);
        return desguaceRepository.findOne(id);
    }

    /**
     *  Delete the  desguace by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Desguace : {}", id);
        desguaceRepository.delete(id);
    }
}
