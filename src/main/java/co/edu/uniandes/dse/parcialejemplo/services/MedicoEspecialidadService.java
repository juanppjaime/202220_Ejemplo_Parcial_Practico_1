package co.edu.uniandes.dse.parcialejemplo.services;

import java.util.Calendar;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialejemplo.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoEspecialidadService {

	@Autowired
	EspecialidadRepository especialidadRepository;

    @Autowired
	MedicoRepository medicoRepository;
	
	/**
	 * Se encarga de crear un Author en la base de datos.
	 *
	 * @param author Objeto de AuthorEntity con los datos nuevos
	 * @return Objeto de AuthorEntity con los datos nuevos y su ID.
	 * @throws IllegalOperationException 
	 */
	@Transactional
	public MedicoEntity addEspecialidad(Long medicoId, Long especialidadId) throws EntityNotFoundException {
		log.info("Inicia proceso de asociarle un autor al libro con id = {0}", medicoId);
		Optional<EspecialidadEntity> especialidadEntity = especialidadRepository.findById(especialidadId);
		if (especialidadEntity.isEmpty())
			throw new EntityNotFoundException("ESPECIALIDAD_NOT_FOUND");
		Optional<MedicoEntity> medicoEntity = medicoRepository.findById(medicoId);
		if (medicoEntity.isEmpty())
			throw new EntityNotFoundException("MEDICO_NOT_FOUND");

		medicoEntity.get().getEspecialidades().add(especialidadEntity.get());
		log.info("Termina proceso de asociarle un autor al libro con id = {0}", medicoId);
		return medicoEntity.get();
	}
}
