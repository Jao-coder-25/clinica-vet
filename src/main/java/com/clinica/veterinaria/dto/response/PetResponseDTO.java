package com.clinica.veterinaria.dto.response;

import com.clinica.veterinaria.entity.PetEntity;

import java.time.LocalDate;

public record PetResponseDTO(
    Long idPet,
    String nomePet,
    String especiePet,
    String racaPet,
    String sexoPet,
    LocalDate dataNascimentoPet,
    Long idTutor,
    String nomeTutor
) {
    public PetResponseDTO(PetEntity pet) {
        this (
                pet.getIdPet(),
                pet.getNomePet(),
                pet.getEspecie(),
                pet.getRaca(),
                pet.getSexo(),
                pet.getDataNascimento(),
                pet.getTutor().getIdTutor(),
                pet.getTutor().getNomeTutor()
        );
    }
}
