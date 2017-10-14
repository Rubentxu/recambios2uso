package cesar.r2uso.web.rest;

import cesar.r2uso.Recambios2UsoApp;

import cesar.r2uso.domain.Marca;
import cesar.r2uso.repository.MarcaRepository;
import cesar.r2uso.service.MarcaService;
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
 * Test class for the MarcaResource REST controller.
 *
 * @see MarcaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Recambios2UsoApp.class)
public class MarcaResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_MODELO = "AAAAAAAAAA";
    private static final String UPDATED_MODELO = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_MOTOR = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_MOTOR = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private MarcaService marcaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restMarcaMockMvc;

    private Marca marca;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MarcaResource marcaResource = new MarcaResource(marcaService);
        this.restMarcaMockMvc = MockMvcBuilders.standaloneSetup(marcaResource)
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
    public static Marca createEntity() {
        Marca marca = new Marca()
            .nombre(DEFAULT_NOMBRE)
            .modelo(DEFAULT_MODELO)
            .tipoMotor(DEFAULT_TIPO_MOTOR)
            .descripcion(DEFAULT_DESCRIPCION);
        return marca;
    }

    @Before
    public void initTest() {
        marcaRepository.deleteAll();
        marca = createEntity();
    }

    @Test
    public void createMarca() throws Exception {
        int databaseSizeBeforeCreate = marcaRepository.findAll().size();

        // Create the Marca
        restMarcaMockMvc.perform(post("/api/marcas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marca)))
            .andExpect(status().isCreated());

        // Validate the Marca in the database
        List<Marca> marcaList = marcaRepository.findAll();
        assertThat(marcaList).hasSize(databaseSizeBeforeCreate + 1);
        Marca testMarca = marcaList.get(marcaList.size() - 1);
        assertThat(testMarca.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testMarca.getModelo()).isEqualTo(DEFAULT_MODELO);
        assertThat(testMarca.getTipoMotor()).isEqualTo(DEFAULT_TIPO_MOTOR);
        assertThat(testMarca.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

    @Test
    public void createMarcaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = marcaRepository.findAll().size();

        // Create the Marca with an existing ID
        marca.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restMarcaMockMvc.perform(post("/api/marcas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marca)))
            .andExpect(status().isBadRequest());

        // Validate the Marca in the database
        List<Marca> marcaList = marcaRepository.findAll();
        assertThat(marcaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = marcaRepository.findAll().size();
        // set the field null
        marca.setNombre(null);

        // Create the Marca, which fails.

        restMarcaMockMvc.perform(post("/api/marcas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marca)))
            .andExpect(status().isBadRequest());

        List<Marca> marcaList = marcaRepository.findAll();
        assertThat(marcaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkModeloIsRequired() throws Exception {
        int databaseSizeBeforeTest = marcaRepository.findAll().size();
        // set the field null
        marca.setModelo(null);

        // Create the Marca, which fails.

        restMarcaMockMvc.perform(post("/api/marcas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marca)))
            .andExpect(status().isBadRequest());

        List<Marca> marcaList = marcaRepository.findAll();
        assertThat(marcaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTipoMotorIsRequired() throws Exception {
        int databaseSizeBeforeTest = marcaRepository.findAll().size();
        // set the field null
        marca.setTipoMotor(null);

        // Create the Marca, which fails.

        restMarcaMockMvc.perform(post("/api/marcas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marca)))
            .andExpect(status().isBadRequest());

        List<Marca> marcaList = marcaRepository.findAll();
        assertThat(marcaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllMarcas() throws Exception {
        // Initialize the database
        marcaRepository.save(marca);

        // Get all the marcaList
        restMarcaMockMvc.perform(get("/api/marcas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(marca.getId())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].modelo").value(hasItem(DEFAULT_MODELO.toString())))
            .andExpect(jsonPath("$.[*].tipoMotor").value(hasItem(DEFAULT_TIPO_MOTOR.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())));
    }

    @Test
    public void getMarca() throws Exception {
        // Initialize the database
        marcaRepository.save(marca);

        // Get the marca
        restMarcaMockMvc.perform(get("/api/marcas/{id}", marca.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(marca.getId()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.modelo").value(DEFAULT_MODELO.toString()))
            .andExpect(jsonPath("$.tipoMotor").value(DEFAULT_TIPO_MOTOR.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()));
    }

    @Test
    public void getNonExistingMarca() throws Exception {
        // Get the marca
        restMarcaMockMvc.perform(get("/api/marcas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateMarca() throws Exception {
        // Initialize the database
        marcaService.save(marca);

        int databaseSizeBeforeUpdate = marcaRepository.findAll().size();

        // Update the marca
        Marca updatedMarca = marcaRepository.findOne(marca.getId());
        updatedMarca
            .nombre(UPDATED_NOMBRE)
            .modelo(UPDATED_MODELO)
            .tipoMotor(UPDATED_TIPO_MOTOR)
            .descripcion(UPDATED_DESCRIPCION);

        restMarcaMockMvc.perform(put("/api/marcas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMarca)))
            .andExpect(status().isOk());

        // Validate the Marca in the database
        List<Marca> marcaList = marcaRepository.findAll();
        assertThat(marcaList).hasSize(databaseSizeBeforeUpdate);
        Marca testMarca = marcaList.get(marcaList.size() - 1);
        assertThat(testMarca.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testMarca.getModelo()).isEqualTo(UPDATED_MODELO);
        assertThat(testMarca.getTipoMotor()).isEqualTo(UPDATED_TIPO_MOTOR);
        assertThat(testMarca.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }

    @Test
    public void updateNonExistingMarca() throws Exception {
        int databaseSizeBeforeUpdate = marcaRepository.findAll().size();

        // Create the Marca

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMarcaMockMvc.perform(put("/api/marcas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(marca)))
            .andExpect(status().isCreated());

        // Validate the Marca in the database
        List<Marca> marcaList = marcaRepository.findAll();
        assertThat(marcaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteMarca() throws Exception {
        // Initialize the database
        marcaService.save(marca);

        int databaseSizeBeforeDelete = marcaRepository.findAll().size();

        // Get the marca
        restMarcaMockMvc.perform(delete("/api/marcas/{id}", marca.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Marca> marcaList = marcaRepository.findAll();
        assertThat(marcaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Marca.class);
        Marca marca1 = new Marca();
        marca1.setId("id1");
        Marca marca2 = new Marca();
        marca2.setId(marca1.getId());
        assertThat(marca1).isEqualTo(marca2);
        marca2.setId("id2");
        assertThat(marca1).isNotEqualTo(marca2);
        marca1.setId(null);
        assertThat(marca1).isNotEqualTo(marca2);
    }
}
