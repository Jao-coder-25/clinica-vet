package com.clinica.veterinaria.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.*;

import java.time.LocalDate;


public record PetDTO(

        @NotBlank(message = "O nome do pet é obrigatório.")
        @JsonAlias({"nome", "nomePet"})
        String nomePet,

        @NotBlank(message = "A espécie do pet é obrigatória.")
        @JsonAlias({"especie", "especiePet"})
        String especiePet,

        @NotBlank(message = "A raça do pet é obrigatória.")
        @JsonAlias({"raca", "racaPet"})
        String racaPet,

        @NotBlank(message = "O sexo do pet é obrigatório.")
        @JsonAlias({"sexo", "sexoPet"})
        String sexoPet,

        @NotNull(message = "A data de nascimento do pet é obrigatória.")
        @JsonAlias({"dataNascimento", "dataNascimentoPet"})
        LocalDate dataNascimentoPet,

        @NotNull(message = "O ID do tutor é obrigatório.")
        @JsonAlias({"tutor", "tutorPet"})
        Long idTutor
) {}
