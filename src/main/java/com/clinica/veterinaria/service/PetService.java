package com.clinica.veterinaria.service;

import com.clinica.veterinaria.dto.PetDTO;
import com.clinica.veterinaria.entity.PetEntity;
import com.clinica.veterinaria.repository.PetRepository;
import com.clinica.veterinaria.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final TutorRepository tutorRepository;

    public PetService(PetRepository petRepository, TutorRepository tutorRepository) {
        this.petRepository = petRepository;
        this.tutorRepository = tutorRepository;
    }

    public PetEntity save(PetDTO petDTO) {

        Long IdTutor = petDTO.idTutor();
        boolean tutorExists = tutorRepository.existsById(IdTutor);

        if(!tutorExists) {
            throw new IllegalArgumentException("O tutor com ID " + IdTutor + " não existe.");
        }

        PetEntity petEntity = new PetEntity();
        petEntity.setNomePet(petDTO.nomePet());
        petEntity.setEspecie(petDTO.especiePet());
        petEntity.setRaca(petDTO.racaPet());
        petEntity.setSexo(petDTO.sexoPet());
        petEntity.setDataNascimento(petDTO.dataNascimentoPet());
        petEntity.setTutor(tutorRepository.getReferenceById(IdTutor));

        return petRepository.save(petEntity);
    }
}
