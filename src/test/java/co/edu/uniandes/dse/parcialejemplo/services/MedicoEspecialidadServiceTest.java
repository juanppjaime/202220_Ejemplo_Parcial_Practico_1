package co.edu.uniandes.dse.parcialejemplo.services;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialejemplo.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de logica de la relacion Book - Author
 *
 * @author ISIS2603
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(MedicoEspecialidadService.class)
class MedicoEspecialidadServiceTest {
	
	@Autowired
	private MedicoEspecialidadService medicoEspecialidadService;
	
	@Autowired
	private TestEntityManager entityManager;

	private PodamFactory factory = new PodamFactoryImpl();

	private MedicoEntity medico = new MedicoEntity();
	private EspecialidadEntity especialidad = new EspecialidadEntity();
	private List<MedicoEntity> medicoList = new ArrayList<>();

	
	@BeforeEach
	void setUp() {
		clearData();
		insertData();
	}
	
	/**
	 * Limpia las tablas que están implicadas en la prueba.
	 */
	private void clearData() {
		entityManager.getEntityManager().createQuery("delete from MedicoEntity").executeUpdate();
		entityManager.getEntityManager().createQuery("delete from EspecilidadEntity").executeUpdate();
	}

	/**
	 * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
	 */
	private void insertData() {

		for (int i = 0; i < 3; i++) {
			MedicoEntity entity = factory.manufacturePojo(MedicoEntity.class);
			entityManager.persist(entity);
			entity.getEspecialidades().add(especialidad);
			medicoList.add(entity);
			especialidad.getMedicos().add(entity);	
		}
	}

	/**
	 * Prueba para asociar un autor a un libro.
	 *
	 */
	@Test
	void testAddEspecialidad() throws EntityNotFoundException, IllegalOperationException {
		MedicoEntity newMedico = factory.manufacturePojo(MedicoEntity.class);
		entityManager.persist(newMedico);
		
		EspecialidadEntity especialidad = factory.manufacturePojo(EspecialidadEntity.class);
		entityManager.persist(especialidad);
		MedicoEntity uff = medicoEspecialidadService.addEspecialidad(newMedico.getId(), especialidad.getId());
        List <EspecialidadEntity> ListaPrueba = new ArrayList<>();
        ListaPrueba.add(especialidad); 
        assertEquals(uff.getEspecialidades(), ListaPrueba);

	}

}