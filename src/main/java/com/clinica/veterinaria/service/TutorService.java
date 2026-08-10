package com.clinica.veterinaria.service;

import com.clinica.veterinaria.dto.request.TutorDTO;
import com.clinica.veterinaria.dto.response.TutorResponseDTO;
import com.clinica.veterinaria.entity.TutorEntity;
import com.clinica.veterinaria.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService (TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public TutorResponseDTO save(TutorDTO tutorDTO) {
        boolean cpfExistente = tutorRepository.existsByCpfTutor(tutorDTO.cpfTutor());

        if (cpfExistente) {
            throw new IllegalArgumentException("O CPF informado já está cadastrado.");
        }

    TutorEntity tutorEntity = new TutorEntity();
    tutorEntity.setNomeTutor(tutorDTO.nomeTutor());
    tutorEntity.setCpfTutor(tutorDTO.cpfTutor());
    tutorEntity.setTelefoneTutor(tutorDTO.telefoneTutor());

    TutorEntity tutorSalvo = tutorRepository.save(tutorEntity);

    return new TutorResponseDTO(tutorSalvo);
    }
}
