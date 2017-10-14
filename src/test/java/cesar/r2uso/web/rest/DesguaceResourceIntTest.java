package cesar.r2uso.web.rest;

import cesar.r2uso.Recambios2UsoApp;

import cesar.r2uso.domain.Desguace;
import cesar.r2uso.repository.DesguaceRepository;
import cesar.r2uso.service.DesguaceService;
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
 * Test class for the DesguaceResource REST controller.
 *
 * @see DesguaceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Recambios2UsoApp.class)
public class DesguaceResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String DEFAULT_MOVIL = "AAAAAAAAAA";
    private static final String UPDATED_MOVIL = "BBBBBBBBBB";

    @Autowired
    private DesguaceRepository desguaceRepository;

    @Autowired
    private DesguaceService desguaceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restDesguaceMockMvc;

    private Desguace desguace;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DesguaceResource desguaceResource = new DesguaceResource(desguaceService);
        this.restDesguaceMockMvc = MockMvcBuilders.standaloneSetup(desguaceResource)
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
    public static Desguace createEntity() {
        Desguace desguace = new Desguace()
            .nombre(DEFAULT_NOMBRE)
            .email(DEFAULT_EMAIL)
            .telefono(DEFAULT_TELEFONO)
            .movil(DEFAULT_MOVIL);
        return desguace;
    }

    @Before
    public void initTest() {
        desguaceRepository.deleteAll();
        desguace = createEntity();
    }

    @Test
    public void createDesguace() throws Exception {
        int databaseSizeBeforeCreate = desguaceRepository.findAll().size();

        // Create the Desguace
        restDesguaceMockMvc.perform(post("/api/desguaces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(desguace)))
            .andExpect(status().isCreated());

        // Validate the Desguace in the database
        List<Desguace> desguaceList = desguaceRepository.findAll();
        assertThat(desguaceList).hasSize(databaseSizeBeforeCreate + 1);
        Desguace testDesguace = desguaceList.get(desguaceList.size() - 1);
        assertThat(testDesguace.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testDesguace.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDesguace.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testDesguace.getMovil()).isEqualTo(DEFAULT_MOVIL);
    }

    @Test
    public void createDesguaceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = desguaceRepository.findAll().size();

        // Create the Desguace with an existing ID
        desguace.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restDesguaceMockMvc.perform(post("/api/desguaces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(desguace)))
            .andExpect(status().isBadRequest());

        // Validate the Desguace in the database
        List<Desguace> desguaceList = desguaceRepository.findAll();
        assertThat(desguaceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = desguaceRepository.findAll().size();
        // set the field null
        desguace.setNombre(null);

        // Create the Desguace, which fails.

        restDesguaceMockMvc.perform(post("/api/desguaces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(desguace)))
            .andExpect(status().isBadRequest());

        List<Desguace> desguaceList = desguaceRepository.findAll();
        assertThat(desguaceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = desguaceRepository.findAll().size();
        // set the field null
        desguace.setEmail(null);

        // Create the Desguace, which fails.

        restDesguaceMockMvc.perform(post("/api/desguaces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(desguace)))
            .andExpect(status().isBadRequest());

        List<Desguace> desguaceList = desguaceRepository.findAll();
        assertThat(desguaceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllDesguaces() throws Exception {
        // Initialize the database
        desguaceRepository.save(desguace);

        // Get all the desguaceList
        restDesguaceMockMvc.perform(get("/api/desguaces?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(desguace.getId())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO.toString())))
            .andExpect(jsonPath("$.[*].movil").value(hasItem(DEFAULT_MOVIL.toString())));
    }

    @Test
    public void getDesguace() throws Exception {
        // Initialize the database
        desguaceRepository.save(desguace);

        // Get the desguace
        restDesguaceMockMvc.perform(get("/api/desguaces/{id}", desguace.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(desguace.getId()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO.toString()))
            .andExpect(jsonPath("$.movil").value(DEFAULT_MOVIL.toString()));
    }

    @Test
    public void getNonExistingDesguace() throws Exception {
        // Get the desguace
        restDesguaceMockMvc.perform(get("/api/desguaces/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDesguace() throws Exception {
        // Initialize the database
        desguaceService.save(desguace);

        int databaseSizeBeforeUpdate = desguaceRepository.findAll().size();

        // Update the desguace
        Desguace updatedDesguace = desguaceRepository.findOne(desguace.getId());
        updatedDesguace
            .nombre(UPDATED_NOMBRE)
            .email(UPDATED_EMAIL)
            .telefono(UPDATED_TELEFONO)
            .movil(UPDATED_MOVIL);

        restDesguaceMockMvc.perform(put("/api/desguaces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDesguace)))
            .andExpect(status().isOk());

        // Validate the Desguace in the database
        List<Desguace> desguaceList = desguaceRepository.findAll();
        assertThat(desguaceList).hasSize(databaseSizeBeforeUpdate);
        Desguace testDesguace = desguaceList.get(desguaceList.size() - 1);
        assertThat(testDesguace.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testDesguace.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDesguace.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testDesguace.getMovil()).isEqualTo(UPDATED_MOVIL);
    }

    @Test
    public void updateNonExistingDesguace() throws Exception {
        int databaseSizeBeforeUpdate = desguaceRepository.findAll().size();

        // Create the Desguace

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDesguaceMockMvc.perform(put("/api/desguaces")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(desguace)))
            .andExpect(status().isCreated());

        // Validate the Desguace in the database
        List<Desguace> desguaceList = desguaceRepository.findAll();
        assertThat(desguaceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteDesguace() throws Exception {
        // Initialize the database
        desguaceService.save(desguace);

        int databaseSizeBeforeDelete = desguaceRepository.findAll().size();

        // Get the desguace
        restDesguaceMockMvc.perform(delete("/api/desguaces/{id}", desguace.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Desguace> desguaceList = desguaceRepository.findAll();
        assertThat(desguaceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Desguace.class);
        Desguace desguace1 = new Desguace();
        desguace1.setId("id1");
        Desguace desguace2 = new Desguace();
        desguace2.setId(desguace1.getId());
        assertThat(desguace1).isEqualTo(desguace2);
        desguace2.setId("id2");
        assertThat(desguace1).isNotEqualTo(desguace2);
        desguace1.setId(null);
        assertThat(desguace1).isNotEqualTo(desguace2);
    }
}
