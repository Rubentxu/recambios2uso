package cesar.cabrera.web.rest;

import cesar.cabrera.Recambios2UsoApp;

import cesar.cabrera.domain.Atributo;
import cesar.cabrera.repository.AtributoRepository;
import cesar.cabrera.service.AtributoService;
import cesar.cabrera.web.rest.errors.ExceptionTranslator;

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
 * Test class for the AtributoResource REST controller.
 *
 * @see AtributoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Recambios2UsoApp.class)
public class AtributoResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_VALOR = "AAAAAAAAAA";
    private static final String UPDATED_VALOR = "BBBBBBBBBB";

    @Autowired
    private AtributoRepository atributoRepository;

    @Autowired
    private AtributoService atributoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restAtributoMockMvc;

    private Atributo atributo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AtributoResource atributoResource = new AtributoResource(atributoService);
        this.restAtributoMockMvc = MockMvcBuilders.standaloneSetup(atributoResource)
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
    public static Atributo createEntity() {
        Atributo atributo = new Atributo()
            .nombre(DEFAULT_NOMBRE)
            .valor(DEFAULT_VALOR);
        return atributo;
    }

    @Before
    public void initTest() {
        atributoRepository.deleteAll();
        atributo = createEntity();
    }

    @Test
    public void createAtributo() throws Exception {
        int databaseSizeBeforeCreate = atributoRepository.findAll().size();

        // Create the Atributo
        restAtributoMockMvc.perform(post("/api/atributos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atributo)))
            .andExpect(status().isCreated());

        // Validate the Atributo in the database
        List<Atributo> atributoList = atributoRepository.findAll();
        assertThat(atributoList).hasSize(databaseSizeBeforeCreate + 1);
        Atributo testAtributo = atributoList.get(atributoList.size() - 1);
        assertThat(testAtributo.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testAtributo.getValor()).isEqualTo(DEFAULT_VALOR);
    }

    @Test
    public void createAtributoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = atributoRepository.findAll().size();

        // Create the Atributo with an existing ID
        atributo.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restAtributoMockMvc.perform(post("/api/atributos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atributo)))
            .andExpect(status().isBadRequest());

        // Validate the Atributo in the database
        List<Atributo> atributoList = atributoRepository.findAll();
        assertThat(atributoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = atributoRepository.findAll().size();
        // set the field null
        atributo.setNombre(null);

        // Create the Atributo, which fails.

        restAtributoMockMvc.perform(post("/api/atributos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atributo)))
            .andExpect(status().isBadRequest());

        List<Atributo> atributoList = atributoRepository.findAll();
        assertThat(atributoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkValorIsRequired() throws Exception {
        int databaseSizeBeforeTest = atributoRepository.findAll().size();
        // set the field null
        atributo.setValor(null);

        // Create the Atributo, which fails.

        restAtributoMockMvc.perform(post("/api/atributos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atributo)))
            .andExpect(status().isBadRequest());

        List<Atributo> atributoList = atributoRepository.findAll();
        assertThat(atributoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllAtributos() throws Exception {
        // Initialize the database
        atributoRepository.save(atributo);

        // Get all the atributoList
        restAtributoMockMvc.perform(get("/api/atributos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(atributo.getId())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.toString())));
    }

    @Test
    public void getAtributo() throws Exception {
        // Initialize the database
        atributoRepository.save(atributo);

        // Get the atributo
        restAtributoMockMvc.perform(get("/api/atributos/{id}", atributo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(atributo.getId()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.toString()));
    }

    @Test
    public void getNonExistingAtributo() throws Exception {
        // Get the atributo
        restAtributoMockMvc.perform(get("/api/atributos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAtributo() throws Exception {
        // Initialize the database
        atributoService.save(atributo);

        int databaseSizeBeforeUpdate = atributoRepository.findAll().size();

        // Update the atributo
        Atributo updatedAtributo = atributoRepository.findOne(atributo.getId());
        updatedAtributo
            .nombre(UPDATED_NOMBRE)
            .valor(UPDATED_VALOR);

        restAtributoMockMvc.perform(put("/api/atributos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAtributo)))
            .andExpect(status().isOk());

        // Validate the Atributo in the database
        List<Atributo> atributoList = atributoRepository.findAll();
        assertThat(atributoList).hasSize(databaseSizeBeforeUpdate);
        Atributo testAtributo = atributoList.get(atributoList.size() - 1);
        assertThat(testAtributo.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testAtributo.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    public void updateNonExistingAtributo() throws Exception {
        int databaseSizeBeforeUpdate = atributoRepository.findAll().size();

        // Create the Atributo

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAtributoMockMvc.perform(put("/api/atributos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atributo)))
            .andExpect(status().isCreated());

        // Validate the Atributo in the database
        List<Atributo> atributoList = atributoRepository.findAll();
        assertThat(atributoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    public void deleteAtributo() throws Exception {
        // Initialize the database
        atributoService.save(atributo);

        int databaseSizeBeforeDelete = atributoRepository.findAll().size();

        // Get the atributo
        restAtributoMockMvc.perform(delete("/api/atributos/{id}", atributo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Atributo> atributoList = atributoRepository.findAll();
        assertThat(atributoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Atributo.class);
        Atributo atributo1 = new Atributo();
        atributo1.setId("id1");
        Atributo atributo2 = new Atributo();
        atributo2.setId(atributo1.getId());
        assertThat(atributo1).isEqualTo(atributo2);
        atributo2.setId("id2");
        assertThat(atributo1).isNotEqualTo(atributo2);
        atributo1.setId(null);
        assertThat(atributo1).isNotEqualTo(atributo2);
    }
}
