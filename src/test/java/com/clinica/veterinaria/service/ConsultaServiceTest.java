package com.clinica.veterinaria.service;

import com.clinica.veterinaria.dto.request.ConsultaDTO;
import com.clinica.veterinaria.dto.response.ConsultaResponseDTO;
import com.clinica.veterinaria.entity.ConsultaEntity;
import com.clinica.veterinaria.entity.PetEntity;
import com.clinica.veterinaria.entity.VeterinarioEntity;
import com.clinica.veterinaria.repository.ConsultaRepository;
import com.clinica.veterinaria.repository.PetRepository;
import com.clinica.veterinaria.repository.VeterinarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceTest {

    @Mock
    private ConsultaRepository consultaRepository;
    @Mock
    private VeterinarioRepository veterinarioRepository;
    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private ConsultaService consultaService;

    @Test
    @DisplayName("Deve salvar uma consulta com sucesso")
    void saveConsultaCase1 () {
        ConsultaDTO consultaDTO = new ConsultaDTO("Tosa", LocalDate.of(2026,9,26), LocalTime.of(17,0), 3L, 2L);

        PetEntity petEntity = new PetEntity();
        VeterinarioEntity veterinarioEntity = new VeterinarioEntity();

        Mockito.when(veterinarioRepository.existsById(3L)).thenReturn(true);
        Mockito.when(petRepository.existsById(2L)).thenReturn(true);
        Mockito.when(consultaRepository.existsByDataConsultaAndHorarioConsulta(consultaDTO.dataConsulta(), consultaDTO.horarioConsulta())).thenReturn(false);
        Mockito.when(veterinarioRepository.getReferenceById(3L)).thenReturn(veterinarioEntity);
        Mockito.when(petRepository.getReferenceById(2L)).thenReturn(petEntity);

        ConsultaEntity consultaSalva = ConsultaEntity.builder()
                .idConsulta(4L)
                .tipoConsulta(consultaDTO.tipoConsulta())
                .dataConsulta(consultaDTO.dataConsulta())
                .horarioConsulta(consultaDTO.horarioConsulta())
                .veterinario(veterinarioEntity)
                .pet(petEntity)
                .build();

        Mockito.when(consultaRepository.save(Mockito.any(ConsultaEntity.class))).thenReturn(consultaSalva);

        ConsultaResponseDTO resultado = consultaService.save(consultaDTO);

        ArgumentCaptor<ConsultaEntity> captor = ArgumentCaptor.forClass(ConsultaEntity.class);

        Mockito.verify(consultaRepository).save(captor.capture());

        ConsultaEntity consultaCapturada = captor.getValue();

        assertEquals(4L, resultado.idConsulta());
        assertEquals(consultaDTO.tipoConsulta(), consultaCapturada.getTipoConsulta());
        assertEquals(consultaDTO.dataConsulta(), consultaCapturada.getDataConsulta());
        assertEquals(consultaDTO.horarioConsulta(), consultaCapturada.getHorarioConsulta());
        assertEquals(veterinarioEntity, consultaCapturada.getVeterinario());
        assertEquals(petEntity, consultaCapturada.getPet());

        Mockito.verify(consultaRepository).existsByDataConsultaAndHorarioConsulta(consultaDTO.dataConsulta(), consultaDTO.horarioConsulta());
        Mockito.verify(petRepository).existsById(consultaDTO.idPet());
        Mockito.verify(veterinarioRepository).existsById(consultaDTO.idVeterinario());
    }
}