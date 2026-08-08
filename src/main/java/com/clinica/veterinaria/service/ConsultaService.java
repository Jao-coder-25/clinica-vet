package com.clinica.veterinaria.service;

import com.clinica.veterinaria.dto.ConsultaDTO;
import com.clinica.veterinaria.entity.ConsultaEntity;
import com.clinica.veterinaria.repository.ConsultaRepository;
import com.clinica.veterinaria.repository.PetRepository;
import com.clinica.veterinaria.repository.TutorRepository;
import com.clinica.veterinaria.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final PetRepository petRepository;

    public ConsultaService(ConsultaRepository consultaRepository, VeterinarioRepository veterinarioRepository, PetRepository petRepository) {
        this.consultaRepository = consultaRepository;
        this.veterinarioRepository = veterinarioRepository;
        this.petRepository = petRepository;
    }
    public ConsultaEntity save(ConsultaDTO consultaDTO) {
        if (consultaDTO.tipoConsulta() == null || consultaDTO.tipoConsulta().isBlank() || consultaDTO.dataConsulta() == null || consultaDTO.idVeterinario() == null || consultaDTO.idPet() == null) {
            throw new IllegalArgumentException("Todos os campos são obrigatórios.");
        }
        if (!veterinarioRepository.existsById(consultaDTO.idVeterinario())){
            throw new IllegalArgumentException("Veterinário não encontrado.");
        }
        if (!petRepository.existsById(consultaDTO.idPet())){
            throw new IllegalArgumentException("Pet não encontrado.");
        }
        ConsultaEntity consultaEntity = new ConsultaEntity();
        consultaEntity.setTipoConsulta(consultaDTO.tipoConsulta());
        consultaEntity.setDataConsulta(consultaDTO.dataConsulta());
        consultaEntity.setVeterinario(veterinarioRepository.getReferenceById(consultaDTO.idVeterinario()));
        consultaEntity.setPet(petRepository.getReferenceById(consultaDTO.idPet()));
        return consultaRepository.save(consultaEntity);
    }
}
