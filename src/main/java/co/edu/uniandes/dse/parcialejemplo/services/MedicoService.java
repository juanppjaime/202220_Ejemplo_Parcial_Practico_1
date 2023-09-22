package co.edu.uniandes.dse.parcialejemplo.services;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoService {

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
	public MedicoEntity createMedico(MedicoEntity medico) throws IllegalOperationException {
		log.info("Inicia proceso de creación del médico");
        String registroMedico = medico.getRegistro();
        if (!registroMedico.startsWith("RM")) {
            throw new IllegalOperationException("El registro médico debe comenzar con 'RM'");
        }
		return medicoRepository.save(medico);
	}
}