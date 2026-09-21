package com.clinica.veterinaria.service;

import com.clinica.veterinaria.dto.request.TutorDTO;
import com.clinica.veterinaria.dto.response.TutorResponseDTO;
import com.clinica.veterinaria.dto.update.TutorUpdateDTO;
import com.clinica.veterinaria.entity.TutorEntity;
import com.clinica.veterinaria.repository.PetRepository;
import com.clinica.veterinaria.repository.TutorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TutorServiceTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private TutorRepository tutorRepository;

    @InjectMocks
    private TutorService tutorService;

    @Test
    @DisplayName("Deve salvar um tutor com sucesso caso não exista outro tutor com o mesmo CPF")
    void saveCase1() {
        TutorDTO tutorDTO = new TutorDTO("João", "12345678901", "11999999999");

        TutorEntity tutorEntity = TutorEntity.builder()
                .nomeTutor(tutorDTO.nomeTutor())
                .cpfTutor(tutorDTO.cpfTutor())
                .telefoneTutor(tutorDTO.telefoneTutor())
                .build();

        Mockito.when(tutorRepository.existsByCpfTutor(tutorDTO.cpfTutor())).thenReturn(false);
        Mockito.when(tutorRepository.save(Mockito.any(TutorEntity.class))).thenReturn(tutorEntity);

        tutorService.save(tutorDTO);

        // Captura o objeto enviado ao repository
        ArgumentCaptor<TutorEntity> captor = ArgumentCaptor.forClass(TutorEntity.class);

        Mockito.verify(tutorRepository).save(captor.capture());
        Mockito.verify(tutorRepository).existsByCpfTutor(tutorDTO.cpfTutor());

        TutorEntity tutorSalvo = captor.getValue();

        // Verifica se o objeto enviado estava correto
        assertEquals(tutorDTO.nomeTutor(), tutorSalvo.getNomeTutor());
        assertEquals(tutorDTO.cpfTutor(), tutorSalvo.getCpfTutor());
        assertEquals(tutorDTO.telefoneTutor(), tutorSalvo.getTelefoneTutor());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar salvar um tutor com CPF já existente")
    void saveCase2() {
        TutorDTO tutorDTO = new TutorDTO("João", "12345678901", "11999999999");

        Mockito.when(tutorRepository.existsByCpfTutor(tutorDTO.cpfTutor())).thenReturn(true);

        assertThrows(
                IllegalArgumentException.class,
                () -> tutorService.save(tutorDTO)
        );

        Mockito.verify(tutorRepository).existsByCpfTutor(tutorDTO.cpfTutor());

        // Verifica se o save do repositório não foi chamado
        Mockito.verify(
                tutorRepository,
                Mockito.never()
        ).save(Mockito.any(TutorEntity.class));
    }

    @Test
    @DisplayName("Deve remover o tutor caso o ID exista no banco e pertença a um tutor")
    void deleteCase1() {
        TutorEntity tutorEntity = TutorEntity.builder()
                .idTutor(1L)
                .build();

        Mockito.when(tutorRepository.existsById(tutorEntity.getIdTutor())).thenReturn(true);
        Mockito.when(petRepository.existsByTutorIdTutor(tutorEntity.getIdTutor())).thenReturn(false);

        tutorService.delete(tutorEntity.getIdTutor());

        Mockito.verify(tutorRepository).existsById(tutorEntity.getIdTutor());
        Mockito.verify(petRepository).existsByTutorIdTutor(tutorEntity.getIdTutor());
        Mockito.verify(tutorRepository).deleteById(tutorEntity.getIdTutor());
    }

    @Test
    @DisplayName("Não permitir a exclusão do Tutor caso o ID solicitado não pertença a um tutor")
    void deleteCase2() {
        Long idTutor = 2L;

        Mockito.when(tutorRepository.existsById(idTutor)).thenReturn(false);


        assertThrows(
                IllegalArgumentException.class, () -> tutorService.delete(idTutor)
        );

        Mockito.verify(tutorRepository).existsById(idTutor);
        Mockito.verify(tutorRepository, Mockito.never()).deleteById(idTutor);
    }

    @Test
    @DisplayName("Não permitir a exclusão do Tutor caso haja um pet vinculado a ele")
    void deleteCase3 () {
        Long idTutor = 3L;

        Mockito.when(tutorRepository.existsById(idTutor)).thenReturn(true);
        Mockito.when(petRepository.existsByTutorIdTutor(idTutor)).thenReturn(true);

        assertThrows(
                IllegalArgumentException.class, () -> tutorService.delete(idTutor)
        );

        Mockito.verify(tutorRepository).existsById(idTutor);
        Mockito.verify(petRepository).existsByTutorIdTutor(idTutor);
        Mockito.verify(tutorRepository, Mockito.never()).deleteById(idTutor);
    }

    @Test
    @DisplayName("Deve atualizar o telefone do tutor com sucesso")
    void updateCase1() {
        TutorEntity tutorEntity = new TutorEntity(
                1L,
                "Osvaldo",
                "12354689111",
                "11987654321"
        );

        TutorUpdateDTO tutorUpdateDTO = new TutorUpdateDTO("78945612311");

        Mockito.when(tutorRepository.findById(tutorEntity.getIdTutor()))
                .thenReturn(Optional.of(tutorEntity));

        TutorResponseDTO tutorAlterado =
                tutorService.update(tutorEntity.getIdTutor(), tutorUpdateDTO);

        assertEquals(
                tutorAlterado.telefoneTutor(),
                tutorUpdateDTO.telefoneTutor()
        );

        Mockito.verify(tutorRepository).findById(tutorEntity.getIdTutor());
    }
}