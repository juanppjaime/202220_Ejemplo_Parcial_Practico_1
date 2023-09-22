package co.edu.uniandes.dse.parcialejemplo.entities;

import java.util.ArrayList;

import javax.persistence.Entity;

import javax.persistence.ManyToMany;


import java.util.List;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Entidad genérica de la que heredan todas las entidades. Contiene la
 * referencia al atributo id
 *
 * @author ISIS2603
 */
@Data
@Entity
public class EspecialidadEntity extends BaseEntity {

	private String nombre;
    private String descripcion;

	@PodamExclude
	@ManyToMany(mappedBy = "especialidades")
	private List <MedicoEntity> medicos = new ArrayList<>();

}
