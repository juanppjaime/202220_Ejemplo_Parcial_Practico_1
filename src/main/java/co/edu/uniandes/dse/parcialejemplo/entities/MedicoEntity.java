package co.edu.uniandes.dse.parcialejemplo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa una editorial en la persistencia
 *
 * @author ISIS2603
 */

@Data
@Entity
public class MedicoEntity extends BaseEntity {

	private String name;
	private String apellido;
	private String registro;

	@PodamExclude
	@ManyToMany(mappedBy = "medicos")
	private List<EspecialidadEntity> especialidades = new ArrayList<>();
}
