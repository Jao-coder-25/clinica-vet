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
    if(tutorDTO.nomeTutor() == null || tutorDTO.nomeTutor().isBlank()) {
        throw new IllegalArgumentException("O nome do tutor não pode ser nulo ou vazio.");
    }

    if(tutorDTO.cpfTutor() == null || tutorDTO.cpfTutor().isBlank()) {
        throw new IllegalArgumentException("O CPF do tutor não pode ser nulo ou vazio.");
    }

    if (tutorDTO.telefoneTutor() == null || tutorDTO.telefoneTutor().isBlank()) {
        throw new IllegalArgumentException("O telefone do tutor não pode ser nulo ou vazio.");
    }
    TutorEntity novaEntity = new TutorEntity();
    novaEntity.setNomeTutor(tutorDTO.nomeTutor());
    novaEntity.setCpfTutor(tutorDTO.cpfTutor());
    novaEntity.setTelefoneTutor(tutorDTO.telefoneTutor());

    return tutorRepository.save(novaEntity);
    }
}
