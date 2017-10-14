package cesar.r2uso.web.rest;

import com.codahale.metrics.annotation.Timed;
import cesar.r2uso.domain.Atributo;
import cesar.r2uso.service.AtributoService;
import cesar.r2uso.web.rest.util.HeaderUtil;
import cesar.r2uso.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Atributo.
 */
@RestController
@RequestMapping("/api")
public class AtributoResource {

    private final Logger log = LoggerFactory.getLogger(AtributoResource.class);

    private static final String ENTITY_NAME = "atributo";

    private final AtributoService atributoService;

    public AtributoResource(AtributoService atributoService) {
        this.atributoService = atributoService;
    }

    /**
     * POST  /atributos : Create a new atributo.
     *
     * @param atributo the atributo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new atributo, or with status 400 (Bad Request) if the atributo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/atributos")
    @Timed
    public ResponseEntity<Atributo> createAtributo(@Valid @RequestBody Atributo atributo) throws URISyntaxException {
        log.debug("REST request to save Atributo : {}", atributo);
        if (atributo.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new atributo cannot already have an ID")).body(null);
        }
        Atributo result = atributoService.save(atributo);
        return ResponseEntity.created(new URI("/api/atributos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /atributos : Updates an existing atributo.
     *
     * @param atributo the atributo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated atributo,
     * or with status 400 (Bad Request) if the atributo is not valid,
     * or with status 500 (Internal Server Error) if the atributo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/atributos")
    @Timed
    public ResponseEntity<Atributo> updateAtributo(@Valid @RequestBody Atributo atributo) throws URISyntaxException {
        log.debug("REST request to update Atributo : {}", atributo);
        if (atributo.getId() == null) {
            return createAtributo(atributo);
        }
        Atributo result = atributoService.save(atributo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, atributo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /atributos : get all the atributos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of atributos in body
     */
    @GetMapping("/atributos")
    @Timed
    public ResponseEntity<List<Atributo>> getAllAtributos(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Atributos");
        Page<Atributo> page = atributoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/atributos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /atributos/:id : get the "id" atributo.
     *
     * @param id the id of the atributo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the atributo, or with status 404 (Not Found)
     */
    @GetMapping("/atributos/{id}")
    @Timed
    public ResponseEntity<Atributo> getAtributo(@PathVariable String id) {
        log.debug("REST request to get Atributo : {}", id);
        Atributo atributo = atributoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(atributo));
    }

    /**
     * DELETE  /atributos/:id : delete the "id" atributo.
     *
     * @param id the id of the atributo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/atributos/{id}")
    @Timed
    public ResponseEntity<Void> deleteAtributo(@PathVariable String id) {
        log.debug("REST request to delete Atributo : {}", id);
        atributoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
