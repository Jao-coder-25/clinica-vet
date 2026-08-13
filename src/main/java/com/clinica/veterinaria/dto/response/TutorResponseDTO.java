package com.clinica.veterinaria.exceptions.dto.response;

import com.clinica.veterinaria.entity.TutorEntity;

public record TutorResponseDTO(
        Long idTutor,
        String nomeTutor,
        String telefoneTutor
) {
    // Construtor que transforma a Entity em DTO de forma automática
    public TutorResponseDTO(TutorEntity tutor) {
        this(tutor.getIdTutor(), tutor.getNomeTutor(), tutor.getTelefoneTutor());
    }
}

