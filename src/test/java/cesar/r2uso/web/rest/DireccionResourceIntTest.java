package cesar.r2uso.web.rest;

import cesar.r2uso.Recambios2UsoApp;

import cesar.r2uso.domain.Direccion;
import cesar.r2uso.repository.DireccionRepository;
import cesar.r2uso.service.DireccionService;
import cesar.r2uso.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DireccionResource REST controller.
 *
 * @see DireccionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Recambios2UsoApp.class)
public class DireccionResourceIntTest {

    private static final String DEFAULT_CALLE = "AAAAAAAAAA";
    private static final String UPDATED_CALLE = "BBBBBBBBBB";

    private static final Long DEFAULT_CODIGO_POSTAL = 1L;
    private static final Long UPDATED_CODIGO_POSTAL = 2L;

    private static final String DEFAULT_CIUDAD = "AAAAAAAAAA";
    private static final String UPDATED_CIUDAD = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCIA = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCIA = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO = 1;
    private static final Integer UPDATED_NUMERO = 2;

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private DireccionService direccionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restDireccionMockMvc;

    private Direccion direccion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DireccionResource direccionResource = new DireccionResource(direccionService);
        this.restDireccionMockMvc = MockMvcBuilders.standaloneSetup(direccionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Direccion createEntity() {
        Direccion direccion = new Direccion()
            .calle(DEFAULT_CALLE)
            .codigoPostal(DEFAULT_CODIGO_POSTAL)
            .ciudad(DEFAULT_CIUDAD)
            .provincia(DEFAULT_PROVINCIA)
            .numero(DEFAULT_NUMERO);
        return direccion;
    }

    @Before
    public void initTest() {
        direccionRepository.deleteAll();
        direccion = createEntity();
    }

    @Test
    public void createDireccion() throws Exception {
        int databaseSizeBeforeCreate = direccionRepository.findAll().size();

        // Create the Direccion
        restDireccionMockMvc.perform(post("/api/direccions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(direccion)))
            .andExpect(status().isCreated());

        // Validate the Direccion in the database
        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeCreate + 1);
        Direccion testDireccion = direccionList.get(direccionList.size() - 1);
        assertThat(testDireccion.getCalle()).isEqualTo(DEFAULT_CALLE);
        assertThat(testDireccion.getCodigoPostal()).isEqualTo(DEFAULT_CODIGO_POSTAL);
        assertThat(testDireccion.getCiudad()).isEqualTo(DEFAULT_CIUDAD);
        assertThat(testDireccion.getProvincia()).isEqualTo(DEFAULT_PROVINCIA);
        assertThat(testDireccion.getNumero()).isEqualTo(DEFAULT_NUMERO);
    }

    @Test
    public void createDireccionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = direccionRepository.findAll().size();

        // Create the Direccion with an existing ID
        direccion.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restDireccionMockMvc.perform(post("/api/direccions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(direccion)))
            .andExpect(status().isBadRequest());

        // Validate the Direccion in the database
        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkCalleIsRequired() throws Exception {
        int databaseSizeBeforeTest = direccionRepository.findAll().size();
        // set the field null
        direccion.setCalle(null);

        // Create the Direccion, which fails.

        restDireccionMockMvc.perform(post("/api/direccions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(direccion)))
            .andExpect(status().isBadRequest());

        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkCiudadIsRequired() throws Exception {
        int databaseSizeBeforeTest = direccionRepository.findAll().size();
        // set the field null
        direccion.setCiudad(null);

        // Create the Direccion, which fails.

        restDireccionMockMvc.perform(post("/api/direccions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(direccion)))
            .andExpect(status().isBadRequest());

        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllDireccions() throws Exception {
        // Initialize the database
        direccionRepository.save(direccion);

        // Get all the direccionList
        restDireccionMockMvc.perform(get("/api/direccions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(direccion.getId())))
            .andExpect(jsonPath("$.[*].calle").value(hasItem(DEFAULT_CALLE.toString())))
            .andExpect(jsonPath("$.[*].codigoPostal").value(hasItem(DEFAULT_CODIGO_POSTAL.intValue())))
            .andExpect(jsonPath("$.[*].ciudad").value(hasItem(DEFAULT_CIUDAD.toString())))
            .andExpect(jsonPath("$.[*].provincia").value(hasItem(DEFAULT_PROVINCIA.toString())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO)));
    }

    @Test
    public void getDireccion() throws Exception {
        // Initialize the database
        direccionRepository.save(direccion);

        // Get the direccion
        restDireccionMockMvc.perform(get("/api/direccions/{id}", direccion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(direccion.getId()))
            .andExpect(jsonPath("$.calle").value(DEFAULT_CALLE.toString()))
            .andExpect(jsonPath("$.codigoPostal").value(DEFAULT_CODIGO_POSTAL.intValue()))
            .andExpect(jsonPath("$.ciudad").value(DEFAULT_CIUDAD.toString()))
            .andExpect(jsonPath("$.provincia").value(DEFAULT_PROVINCIA.toString()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO));
    }

    @Test
    public void getNonExistingDireccion() throws Exception {
        // Get the direccion
        restDireccionMockMvc.perform(get("/api/direccions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDireccion() throws Exception {
        // Initialize the database
        direccionService.save(direccion);

        int databaseSizeBeforeUpdate = direccionRepository.findAll().size();

        // Update the direccion
        Direccion updatedDireccion = direccionRepository.findOne(direccion.getId());
        updatedDireccion
            .calle(UPDATED_CALLE)
            .codigoPostal(UPDATED_CODIGO_POSTAL)
            .ciudad(UPDATED_CIUDAD)
            .provincia(UPDATED_PROVINCIA)
            .numero(UPDATED_NUMERO);

        restDireccionMockMvc.perform(put("/api/direccions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDireccion)))
            .andExpect(status().isOk());

        // Validate the Direccion in the database
        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeUpdate);
        Direccion testDireccion = direccionList.get(direccionList.size() - 1);
        assertThat(testDireccion.getCalle()).isEqualTo(UPDATED_CALLE);
        assertThat(testDireccion.getCodigoPostal()).isEqualTo(UPDATED_CODIGO_POSTAL);
        assertThat(testDireccion.getCiudad()).isEqualTo(UPDATED_CIUDAD);
        assertThat(testDireccion.getProvincia()).isEqualTo(UPDATED_PROVINCIA);
        assertThat(testDireccion.getNumero()).isEqualTo(UPDATED_NUMERO);
    }

    @Test
    public void updateNonExistingDireccion() throws Exception {
        int databaseSizeBeforeUpdate = direccionRepository.findAll().size();

        // Create the Direccion

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDireccionMockMvc.perform(put("/api/direccions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(direccion)))
            .andExpect(status().isCreated());

        // Validate the Direccion in the database
        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteDireccion() throws Exception {
        // Initialize the database
        direccionService.save(direccion);

        int databaseSizeBeforeDelete = direccionRepository.findAll().size();

        // Get the direccion
        restDireccionMockMvc.perform(delete("/api/direccions/{id}", direccion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Direccion> direccionList = direccionRepository.findAll();
        assertThat(direccionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Direccion.class);
        Direccion direccion1 = new Direccion();
        direccion1.setId("id1");
        Direccion direccion2 = new Direccion();
        direccion2.setId(direccion1.getId());
        assertThat(direccion1).isEqualTo(direccion2);
        direccion2.setId("id2");
        assertThat(direccion1).isNotEqualTo(direccion2);
        direccion1.setId(null);
        assertThat(direccion1).isNotEqualTo(direccion2);
    }
}
