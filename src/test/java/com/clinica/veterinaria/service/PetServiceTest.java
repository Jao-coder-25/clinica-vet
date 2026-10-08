package com.clinica.veterinaria.service;

import com.clinica.veterinaria.dto.request.PetDTO;
import com.clinica.veterinaria.entity.PetEntity;
import com.clinica.veterinaria.entity.TutorEntity;
import com.clinica.veterinaria.repository.ConsultaRepository;
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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private ConsultaRepository consultaRepository;

    @InjectMocks
    private PetService petService;

    @Test
    @DisplayName("Deve salvar um pet com sucesso")
    void saveCase1 () {
        TutorEntity tutorEntity = TutorEntity.builder()
                .idTutor(1L)
                .build();
        PetDTO petDTO = new PetDTO( "Rex", "Cachorro", "Macho", LocalDate.of(2024, 12, 15), tutorEntity.getIdTutor());

        PetEntity petEntity = PetEntity.builder()
                .idPet(2L)
                .nomePet(petDTO.nomePet())
                .raca(petDTO.racaPet())
                .sexo(petDTO.sexoPet())
                .dataNascimento(petDTO.dataNascimentoPet())
                .tutor(tutorEntity)
                .build();

        Mockito.when(tutorRepository.existsById(tutorEntity.getIdTutor())).thenReturn(true);
        Mockito.when(petRepository.save(Mockito.any(PetEntity.class))).thenReturn(petEntity);

        petService.save(petDTO);

        ArgumentCaptor<PetEntity> captor = ArgumentCaptor.forClass(PetEntity.class);

        Mockito.verify(petRepository).save(captor.capture()); // captura o argumento passado para o método save do petRepository

        PetEntity petSalvo = captor.getValue();

        assertEquals(petDTO.nomePet(), petSalvo.getNomePet());
        assertEquals(petDTO.racaPet(), petSalvo.getRaca());
        assertEquals(petDTO.sexoPet(), petSalvo.getSexo());
        assertEquals(petDTO.dataNascimentoPet(), petSalvo.getDataNascimento());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar salvar um pet com tutor inexistente")
    void saveCase2() {
        TutorEntity tutorEntity = TutorEntity.builder()
                .idTutor(1L)
                .build();
        PetDTO petDTO = new PetDTO( "Rex", "Cachorro", "Macho", LocalDate.of(2024, 12, 15), tutorEntity.getIdTutor());

        Mockito.when(tutorRepository.existsById(tutorEntity.getIdTutor())).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            petService.save(petDTO);
        });

        System.out.println("Exceção lançada: " + exception.getMessage());

        Mockito.verify(tutorRepository).existsById(tutorEntity.getIdTutor());
        Mockito.verify(petRepository, Mockito.never()).save(Mockito.any(PetEntity.class));
    }

    @Test
    @DisplayName("Deve excluir um pet com sucesso")
    void deleteCase1() {
        Long idPet = 1L;

        Mockito.when(petRepository.existsById(idPet)).thenReturn(true);
        Mockito.when(consultaRepository.existsByPetIdPet(idPet)).thenReturn(false);

        petService.delete(idPet);

        Mockito.verify(petRepository).existsById(idPet);
        Mockito.verify(consultaRepository).existsByPetIdPet(idPet);
        Mockito.verify(petRepository).deleteById(idPet);
    }
}