package cesar.r2uso.web.rest;

import com.codahale.metrics.annotation.Timed;
import cesar.r2uso.domain.Recambio;
import cesar.r2uso.service.RecambioService;
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
 * REST controller for managing Recambio.
 */
@RestController
@RequestMapping("/api")
public class RecambioResource {

    private final Logger log = LoggerFactory.getLogger(RecambioResource.class);

    private static final String ENTITY_NAME = "recambio";

    private final RecambioService recambioService;

    public RecambioResource(RecambioService recambioService) {
        this.recambioService = recambioService;
    }

    /**
     * POST  /recambios : Create a new recambio.
     *
     * @param recambio the recambio to create
     * @return the ResponseEntity with status 201 (Created) and with body the new recambio, or with status 400 (Bad Request) if the recambio has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/recambios")
    @Timed
    public ResponseEntity<Recambio> createRecambio(@Valid @RequestBody Recambio recambio) throws URISyntaxException {
        log.debug("REST request to save Recambio : {}", recambio);
        if (recambio.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new recambio cannot already have an ID")).body(null);
        }
        Recambio result = recambioService.save(recambio);
        return ResponseEntity.created(new URI("/api/recambios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /recambios : Updates an existing recambio.
     *
     * @param recambio the recambio to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated recambio,
     * or with status 400 (Bad Request) if the recambio is not valid,
     * or with status 500 (Internal Server Error) if the recambio couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/recambios")
    @Timed
    public ResponseEntity<Recambio> updateRecambio(@Valid @RequestBody Recambio recambio) throws URISyntaxException {
        log.debug("REST request to update Recambio : {}", recambio);
        if (recambio.getId() == null) {
            return createRecambio(recambio);
        }
        Recambio result = recambioService.save(recambio);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, recambio.getId().toString()))
            .body(result);
    }

    /**
     * GET  /recambios : get all the recambios.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of recambios in body
     */
    @GetMapping("/recambios")
    @Timed
    public ResponseEntity<List<Recambio>> getAllRecambios(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Recambios");
        Page<Recambio> page = recambioService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/recambios");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /recambios/:id : get the "id" recambio.
     *
     * @param id the id of the recambio to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recambio, or with status 404 (Not Found)
     */
    @GetMapping("/recambios/{id}")
    @Timed
    public ResponseEntity<Recambio> getRecambio(@PathVariable String id) {
        log.debug("REST request to get Recambio : {}", id);
        Recambio recambio = recambioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(recambio));
    }

    /**
     * DELETE  /recambios/:id : delete the "id" recambio.
     *
     * @param id the id of the recambio to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/recambios/{id}")
    @Timed
    public ResponseEntity<Void> deleteRecambio(@PathVariable String id) {
        log.debug("REST request to delete Recambio : {}", id);
        recambioService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
