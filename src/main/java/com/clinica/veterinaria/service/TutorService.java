package com.clinica.veterinaria.service;

import com.clinica.veterinaria.dto.request.TutorDTO;
import com.clinica.veterinaria.dto.response.TutorResponseDTO;
import com.clinica.veterinaria.entity.TutorEntity;
import com.clinica.veterinaria.repository.PetRepository;
import com.clinica.veterinaria.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;
    private final PetRepository petRepository;

    public TutorService (TutorRepository tutorRepository, PetRepository petRepository) {
        this.tutorRepository = tutorRepository;
        this.petRepository = petRepository;
    }

    public TutorResponseDTO save(TutorDTO tutorDTO) {
        boolean cpfExistente = tutorRepository.existsByCpfTutor(tutorDTO.cpfTutor());

        if (cpfExistente) {
            throw new IllegalArgumentException("O CPF informado já está cadastrado.");
        }

    TutorEntity tutorEntity = TutorEntity.builder()
            .nomeTutor(tutorDTO.nomeTutor())
            .cpfTutor(tutorDTO.cpfTutor())
            .telefoneTutor(tutorDTO.telefoneTutor())
            .build();

    return new TutorResponseDTO(tutorRepository.save(tutorEntity));
    }
    public void delete(Long idTutor) {
        if(!tutorRepository.existsById(idTutor)) {
            throw new IllegalArgumentException("O tutor com o ID informado não existe.");
        }
        if(petRepository.existsByTutorIdTutor(idTutor)) {
            throw new IllegalArgumentException("Não é possível excluir o tutor, pois ele possui pets associados.");
        }
        tutorRepository.deleteById(idTutor);
    }
}
