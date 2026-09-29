package com.clinica.veterinaria.service;

import com.clinica.veterinaria.dto.request.VeterinarioDTO;
import com.clinica.veterinaria.entity.VeterinarioEntity;
import com.clinica.veterinaria.repository.ConsultaRepository;
import com.clinica.veterinaria.repository.VeterinarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VeterinarioServiceTest {

    @Mock
    private VeterinarioRepository veterinarioRepository;

    @Mock
    private ConsultaRepository consultaRepository;

    @InjectMocks
    private VeterinarioService veterinarioService;

    @Test
    @DisplayName("Deve salvar um veterinário com sucesso caso não exista outro veterinário com o mesmo CPF")
    void saveCase1() {
        VeterinarioDTO veterinarioDTO = new VeterinarioDTO("cirurgião", "Clebson", "7740028922", "12345678911");
        VeterinarioEntity veterinarioEntity = VeterinarioEntity.builder()
                .nomeVeterinario(veterinarioDTO.nomeVeterinario())
                .cpfVeterinario(veterinarioDTO.cpfVeterinario())
                .build();

        Mockito.when(veterinarioRepository.existsByCpfVeterinario(veterinarioDTO.cpfVeterinario())).thenReturn(false);
        Mockito.when(veterinarioRepository.save(Mockito.any(VeterinarioEntity.class))).thenReturn(veterinarioEntity);

        veterinarioService.save(veterinarioDTO);

        ArgumentCaptor<VeterinarioEntity> captor = ArgumentCaptor.forClass(VeterinarioEntity.class);

        Mockito.verify(veterinarioRepository).save(captor.capture());
        Mockito.verify(veterinarioRepository).existsByCpfVeterinario(veterinarioDTO.cpfVeterinario());

        VeterinarioEntity veterinarioSalvo = captor.getValue();

        assertEquals(veterinarioDTO.cpfVeterinario(), veterinarioSalvo.getCpfVeterinario());
    }
}