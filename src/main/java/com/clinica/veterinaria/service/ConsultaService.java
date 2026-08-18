package com.clinica.veterinaria.service;

import com.clinica.veterinaria.dto.request.ConsultaDTO;
import com.clinica.veterinaria.dto.response.ConsultaResponseDTO;
import com.clinica.veterinaria.dto.response.HorarioDisponivelResponseDTO;
import com.clinica.veterinaria.entity.ConsultaEntity;
import com.clinica.veterinaria.repository.ConsultaRepository;
import com.clinica.veterinaria.repository.PetRepository;
import com.clinica.veterinaria.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    public List<HorarioDisponivelResponseDTO> listarHorarioDisponiveis(LocalDate dataConsulta) {

        List<HorarioDisponivelResponseDTO> horariosLivres = new ArrayList<>();

        List<LocalTime> gradeDeFuncionamento = List.of(
                LocalTime.of(8,0),
                LocalTime.of(9,0),
                LocalTime.of(10,0),
                LocalTime.of(11,0),
                LocalTime.of(13,0),
                LocalTime.of(14,0),
                LocalTime.of(15,0),
                LocalTime.of(16,0),
                LocalTime.of(17,0)
        );
        List<LocalTime> horarioAgendados = consultaRepository.findHorariosOcupadosPorData(dataConsulta);

        for(LocalTime horario : gradeDeFuncionamento) {
            if(!horarioAgendados.contains(horario)) {
                HorarioDisponivelResponseDTO dto = new HorarioDisponivelResponseDTO(dataConsulta, horario);
                horariosLivres.add(dto);
            }
        }
        return horariosLivres;
    }
}
