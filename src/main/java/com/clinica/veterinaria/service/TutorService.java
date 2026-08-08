package com.clinica.veterinaria.service;

import com.clinica.veterinaria.dto.TutorDTO;
import com.clinica.veterinaria.entity.TutorEntity;
import com.clinica.veterinaria.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService (TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public TutorEntity save(TutorDTO tutorDTO) {
        boolean cpfExistente = tutorRepository.existsByCpfTutor(tutorDTO.cpfTutor());

        if (cpfExistente) {
            throw new IllegalArgumentException("O CPF informado já está cadastrado.");
        }

    TutorEntity novaEntity = new TutorEntity();
    novaEntity.setNomeTutor(tutorDTO.nomeTutor());
    novaEntity.setCpfTutor(tutorDTO.cpfTutor());
    novaEntity.setTelefoneTutor(tutorDTO.telefoneTutor());

    return tutorRepository.save(novaEntity);
    }
}
