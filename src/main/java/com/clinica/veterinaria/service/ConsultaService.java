package com.clinica.veterinaria.service;

import com.clinica.veterinaria.dto.request.ConsultaDTO;
import com.clinica.veterinaria.dto.response.ConsultaResponseDTO;
import com.clinica.veterinaria.entity.ConsultaEntity;
import com.clinica.veterinaria.repository.ConsultaRepository;
import com.clinica.veterinaria.repository.PetRepository;
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
    public ConsultaResponseDTO save(ConsultaDTO consultaDTO) {

        if (!veterinarioRepository.existsById(consultaDTO.idVeterinario())){
            throw new IllegalArgumentException("Veterinário não encontrado.");
        }
        if (!petRepository.existsById(consultaDTO.idPet())){
            throw new IllegalArgumentException("Pet não encontrado.");
        }
        ConsultaEntity consultaEntity = ConsultaEntity.builder()
                .tipoConsulta(consultaDTO.tipoConsulta())
                .dataConsulta(consultaDTO.dataConsulta())
                .horarioConsulta(consultaDTO.horarioConsulta())
                .veterinario(veterinarioRepository.getReferenceById(consultaDTO.idVeterinario()))
                .pet(petRepository.getReferenceById(consultaDTO.idPet()))
                .build();

        return new ConsultaResponseDTO(consultaRepository.save(consultaEntity));
    }
    public void delete(Long idConsulta) {
        if(!consultaRepository.existsById(idConsulta)){
            throw  new IllegalArgumentException("A consulta com o ID informado não existe.");
        }
        consultaRepository.deleteById(idConsulta);
    }
}
