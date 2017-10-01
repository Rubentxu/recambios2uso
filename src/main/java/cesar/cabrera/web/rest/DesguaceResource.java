package cesar.cabrera.web.rest;

import com.codahale.metrics.annotation.Timed;
import cesar.cabrera.domain.Desguace;
import cesar.cabrera.service.DesguaceService;
import cesar.cabrera.web.rest.util.HeaderUtil;
import cesar.cabrera.web.rest.util.PaginationUtil;
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
 * REST controller for managing Desguace.
 */
@RestController
@RequestMapping("/api")
public class DesguaceResource {

    private final Logger log = LoggerFactory.getLogger(DesguaceResource.class);

    private static final String ENTITY_NAME = "desguace";

    private final DesguaceService desguaceService;

    public DesguaceResource(DesguaceService desguaceService) {
        this.desguaceService = desguaceService;
    }

    /**
     * POST  /desguaces : Create a new desguace.
     *
     * @param desguace the desguace to create
     * @return the ResponseEntity with status 201 (Created) and with body the new desguace, or with status 400 (Bad Request) if the desguace has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/desguaces")
    @Timed
    public ResponseEntity<Desguace> createDesguace(@Valid @RequestBody Desguace desguace) throws URISyntaxException {
        log.debug("REST request to save Desguace : {}", desguace);
        if (desguace.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new desguace cannot already have an ID")).body(null);
        }
        Desguace result = desguaceService.save(desguace);
        return ResponseEntity.created(new URI("/api/desguaces/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /desguaces : Updates an existing desguace.
     *
     * @param desguace the desguace to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated desguace,
     * or with status 400 (Bad Request) if the desguace is not valid,
     * or with status 500 (Internal Server Error) if the desguace couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/desguaces")
    @Timed
    public ResponseEntity<Desguace> updateDesguace(@Valid @RequestBody Desguace desguace) throws URISyntaxException {
        log.debug("REST request to update Desguace : {}", desguace);
        if (desguace.getId() == null) {
            return createDesguace(desguace);
        }
        Desguace result = desguaceService.save(desguace);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, desguace.getId().toString()))
            .body(result);
    }

    /**
     * GET  /desguaces : get all the desguaces.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of desguaces in body
     */
    @GetMapping("/desguaces")
    @Timed
    public ResponseEntity<List<Desguace>> getAllDesguaces(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Desguaces");
        Page<Desguace> page = desguaceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/desguaces");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /desguaces/:id : get the "id" desguace.
     *
     * @param id the id of the desguace to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the desguace, or with status 404 (Not Found)
     */
    @GetMapping("/desguaces/{id}")
    @Timed
    public ResponseEntity<Desguace> getDesguace(@PathVariable String id) {
        log.debug("REST request to get Desguace : {}", id);
        Desguace desguace = desguaceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(desguace));
    }

    /**
     * DELETE  /desguaces/:id : delete the "id" desguace.
     *
     * @param id the id of the desguace to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/desguaces/{id}")
    @Timed
    public ResponseEntity<Void> deleteDesguace(@PathVariable String id) {
        log.debug("REST request to delete Desguace : {}", id);
        desguaceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
