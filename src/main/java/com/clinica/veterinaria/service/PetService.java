package com.clinica.veterinaria.service;

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

    public PetEntity save(PetEntity petEntity) {
        if(petEntity.getTutor() == null || petEntity.getTutor().getIdTutor() == null) {
            throw new IllegalArgumentException("O tutor do pet não pode ser nulo.");
        }

        Long IdTutor = petEntity.getTutor().getIdTutor();
        boolean tutorExists = tutorRepository.existsById(IdTutor);

        if(!tutorExists) {
            throw new IllegalArgumentException("O tutor com ID " + IdTutor + " não existe.");
        }
        return petRepository.save(petEntity);
    }
}
