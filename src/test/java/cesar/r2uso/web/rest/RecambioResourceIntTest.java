package cesar.r2uso.web.rest;

import cesar.r2uso.Recambios2UsoApp;

import cesar.r2uso.domain.Recambio;
import cesar.r2uso.repository.RecambioRepository;
import cesar.r2uso.service.RecambioService;
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

import cesar.r2uso.domain.enumeration.Tipo;
/**
 * Test class for the RecambioResource REST controller.
 *
 * @see RecambioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Recambios2UsoApp.class)
public class RecambioResourceIntTest {

    private static final String DEFAULT_REFERENCIA = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCIA = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final Tipo DEFAULT_TIPO = Tipo.FRENADO;
    private static final Tipo UPDATED_TIPO = Tipo.FILTROS;

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final Double DEFAULT_PRECIO = 1D;
    private static final Double UPDATED_PRECIO = 2D;

    private static final String DEFAULT_RECLAMO_PUBLICITARIO = "AAAAAAAAAA";
    private static final String UPDATED_RECLAMO_PUBLICITARIO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DISPONIBILIDAD = false;
    private static final Boolean UPDATED_DISPONIBILIDAD = true;

    private static final Boolean DEFAULT_EXPOSICION = false;
    private static final Boolean UPDATED_EXPOSICION = true;

    @Autowired
    private RecambioRepository recambioRepository;

    @Autowired
    private RecambioService recambioService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restRecambioMockMvc;

    private Recambio recambio;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RecambioResource recambioResource = new RecambioResource(recambioService);
        this.restRecambioMockMvc = MockMvcBuilders.standaloneSetup(recambioResource)
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
    public static Recambio createEntity() {
        Recambio recambio = new Recambio()
            .referencia(DEFAULT_REFERENCIA)
            .nombre(DEFAULT_NOMBRE)
            .tipo(DEFAULT_TIPO)
            .descripcion(DEFAULT_DESCRIPCION)
            .precio(DEFAULT_PRECIO)
            .reclamoPublicitario(DEFAULT_RECLAMO_PUBLICITARIO)
            .disponibilidad(DEFAULT_DISPONIBILIDAD)
            .exposicion(DEFAULT_EXPOSICION);
        return recambio;
    }

    @Before
    public void initTest() {
        recambioRepository.deleteAll();
        recambio = createEntity();
    }

    @Test
    public void createRecambio() throws Exception {
        int databaseSizeBeforeCreate = recambioRepository.findAll().size();

        // Create the Recambio
        restRecambioMockMvc.perform(post("/api/recambios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recambio)))
            .andExpect(status().isCreated());

        // Validate the Recambio in the database
        List<Recambio> recambioList = recambioRepository.findAll();
        assertThat(recambioList).hasSize(databaseSizeBeforeCreate + 1);
        Recambio testRecambio = recambioList.get(recambioList.size() - 1);
        assertThat(testRecambio.getReferencia()).isEqualTo(DEFAULT_REFERENCIA);
        assertThat(testRecambio.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testRecambio.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testRecambio.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testRecambio.getPrecio()).isEqualTo(DEFAULT_PRECIO);
        assertThat(testRecambio.getReclamoPublicitario()).isEqualTo(DEFAULT_RECLAMO_PUBLICITARIO);
        assertThat(testRecambio.isDisponibilidad()).isEqualTo(DEFAULT_DISPONIBILIDAD);
        assertThat(testRecambio.isExposicion()).isEqualTo(DEFAULT_EXPOSICION);
    }

    @Test
    public void createRecambioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = recambioRepository.findAll().size();

        // Create the Recambio with an existing ID
        recambio.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecambioMockMvc.perform(post("/api/recambios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recambio)))
            .andExpect(status().isBadRequest());

        // Validate the Recambio in the database
        List<Recambio> recambioList = recambioRepository.findAll();
        assertThat(recambioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkReferenciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = recambioRepository.findAll().size();
        // set the field null
        recambio.setReferencia(null);

        // Create the Recambio, which fails.

        restRecambioMockMvc.perform(post("/api/recambios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recambio)))
            .andExpect(status().isBadRequest());

        List<Recambio> recambioList = recambioRepository.findAll();
        assertThat(recambioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = recambioRepository.findAll().size();
        // set the field null
        recambio.setNombre(null);

        // Create the Recambio, which fails.

        restRecambioMockMvc.perform(post("/api/recambios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recambio)))
            .andExpect(status().isBadRequest());

        List<Recambio> recambioList = recambioRepository.findAll();
        assertThat(recambioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTipoIsRequired() throws Exception {
        int databaseSizeBeforeTest = recambioRepository.findAll().size();
        // set the field null
        recambio.setTipo(null);

        // Create the Recambio, which fails.

        restRecambioMockMvc.perform(post("/api/recambios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recambio)))
            .andExpect(status().isBadRequest());

        List<Recambio> recambioList = recambioRepository.findAll();
        assertThat(recambioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkPrecioIsRequired() throws Exception {
        int databaseSizeBeforeTest = recambioRepository.findAll().size();
        // set the field null
        recambio.setPrecio(null);

        // Create the Recambio, which fails.

        restRecambioMockMvc.perform(post("/api/recambios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recambio)))
            .andExpect(status().isBadRequest());

        List<Recambio> recambioList = recambioRepository.findAll();
        assertThat(recambioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDisponibilidadIsRequired() throws Exception {
        int databaseSizeBeforeTest = recambioRepository.findAll().size();
        // set the field null
        recambio.setDisponibilidad(null);

        // Create the Recambio, which fails.

        restRecambioMockMvc.perform(post("/api/recambios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recambio)))
            .andExpect(status().isBadRequest());

        List<Recambio> recambioList = recambioRepository.findAll();
        assertThat(recambioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkExposicionIsRequired() throws Exception {
        int databaseSizeBeforeTest = recambioRepository.findAll().size();
        // set the field null
        recambio.setExposicion(null);

        // Create the Recambio, which fails.

        restRecambioMockMvc.perform(post("/api/recambios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recambio)))
            .andExpect(status().isBadRequest());

        List<Recambio> recambioList = recambioRepository.findAll();
        assertThat(recambioList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllRecambios() throws Exception {
        // Initialize the database
        recambioRepository.save(recambio);

        // Get all the recambioList
        restRecambioMockMvc.perform(get("/api/recambios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recambio.getId())))
            .andExpect(jsonPath("$.[*].referencia").value(hasItem(DEFAULT_REFERENCIA.toString())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].precio").value(hasItem(DEFAULT_PRECIO.doubleValue())))
            .andExpect(jsonPath("$.[*].reclamoPublicitario").value(hasItem(DEFAULT_RECLAMO_PUBLICITARIO.toString())))
            .andExpect(jsonPath("$.[*].disponibilidad").value(hasItem(DEFAULT_DISPONIBILIDAD.booleanValue())))
            .andExpect(jsonPath("$.[*].exposicion").value(hasItem(DEFAULT_EXPOSICION.booleanValue())));
    }

    @Test
    public void getRecambio() throws Exception {
        // Initialize the database
        recambioRepository.save(recambio);

        // Get the recambio
        restRecambioMockMvc.perform(get("/api/recambios/{id}", recambio.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(recambio.getId()))
            .andExpect(jsonPath("$.referencia").value(DEFAULT_REFERENCIA.toString()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.precio").value(DEFAULT_PRECIO.doubleValue()))
            .andExpect(jsonPath("$.reclamoPublicitario").value(DEFAULT_RECLAMO_PUBLICITARIO.toString()))
            .andExpect(jsonPath("$.disponibilidad").value(DEFAULT_DISPONIBILIDAD.booleanValue()))
            .andExpect(jsonPath("$.exposicion").value(DEFAULT_EXPOSICION.booleanValue()));
    }

    @Test
    public void getNonExistingRecambio() throws Exception {
        // Get the recambio
        restRecambioMockMvc.perform(get("/api/recambios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateRecambio() throws Exception {
        // Initialize the database
        recambioService.save(recambio);

        int databaseSizeBeforeUpdate = recambioRepository.findAll().size();

        // Update the recambio
        Recambio updatedRecambio = recambioRepository.findOne(recambio.getId());
        updatedRecambio
            .referencia(UPDATED_REFERENCIA)
            .nombre(UPDATED_NOMBRE)
            .tipo(UPDATED_TIPO)
            .descripcion(UPDATED_DESCRIPCION)
            .precio(UPDATED_PRECIO)
            .reclamoPublicitario(UPDATED_RECLAMO_PUBLICITARIO)
            .disponibilidad(UPDATED_DISPONIBILIDAD)
            .exposicion(UPDATED_EXPOSICION);

        restRecambioMockMvc.perform(put("/api/recambios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRecambio)))
            .andExpect(status().isOk());

        // Validate the Recambio in the database
        List<Recambio> recambioList = recambioRepository.findAll();
        assertThat(recambioList).hasSize(databaseSizeBeforeUpdate);
        Recambio testRecambio = recambioList.get(recambioList.size() - 1);
        assertThat(testRecambio.getReferencia()).isEqualTo(UPDATED_REFERENCIA);
        assertThat(testRecambio.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testRecambio.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testRecambio.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testRecambio.getPrecio()).isEqualTo(UPDATED_PRECIO);
        assertThat(testRecambio.getReclamoPublicitario()).isEqualTo(UPDATED_RECLAMO_PUBLICITARIO);
        assertThat(testRecambio.isDisponibilidad()).isEqualTo(UPDATED_DISPONIBILIDAD);
        assertThat(testRecambio.isExposicion()).isEqualTo(UPDATED_EXPOSICION);
    }

    @Test
    public void updateNonExistingRecambio() throws Exception {
        int databaseSizeBeforeUpdate = recambioRepository.findAll().size();

        // Create the Recambio

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRecambioMockMvc.perform(put("/api/recambios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(recambio)))
            .andExpect(status().isCreated());

        // Validate the Recambio in the database
        List<Recambio> recambioList = recambioRepository.findAll();
        assertThat(recambioList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteRecambio() throws Exception {
        // Initialize the database
        recambioService.save(recambio);

        int databaseSizeBeforeDelete = recambioRepository.findAll().size();

        // Get the recambio
        restRecambioMockMvc.perform(delete("/api/recambios/{id}", recambio.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Recambio> recambioList = recambioRepository.findAll();
        assertThat(recambioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Recambio.class);
        Recambio recambio1 = new Recambio();
        recambio1.setId("id1");
        Recambio recambio2 = new Recambio();
        recambio2.setId(recambio1.getId());
        assertThat(recambio1).isEqualTo(recambio2);
        recambio2.setId("id2");
        assertThat(recambio1).isNotEqualTo(recambio2);
        recambio1.setId(null);
        assertThat(recambio1).isNotEqualTo(recambio2);
    }
}
