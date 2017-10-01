package cesar.cabrera.service.impl;

import cesar.cabrera.service.AtributoService;
import cesar.cabrera.domain.Atributo;
import cesar.cabrera.repository.AtributoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing Atributo.
 */
@Service
public class AtributoServiceImpl implements AtributoService{

    private final Logger log = LoggerFactory.getLogger(AtributoServiceImpl.class);

    private final AtributoRepository atributoRepository;

    public AtributoServiceImpl(AtributoRepository atributoRepository) {
        this.atributoRepository = atributoRepository;
    }

    /**
     * Save a atributo.
     *
     * @param atributo the entity to save
     * @return the persisted entity
     */
    @Override
    public Atributo save(Atributo atributo) {
        log.debug("Request to save Atributo : {}", atributo);
        return atributoRepository.save(atributo);
    }

    /**
     *  Get all the atributos.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    public Page<Atributo> findAll(Pageable pageable) {
        log.debug("Request to get all Atributos");
        return atributoRepository.findAll(pageable);
    }

    /**
     *  Get one atributo by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    public Atributo findOne(String id) {
        log.debug("Request to get Atributo : {}", id);
        return atributoRepository.findOne(id);
    }

    /**
     *  Delete the  atributo by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Atributo : {}", id);
        atributoRepository.delete(id);
    }
}
