package com.clinica.veterinaria.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;


public record PetDTO(

        @JsonAlias({"nome", "nomePet"})
        String nomePet,
        @JsonAlias({"especie", "especiePet"})
        String especiePet,
        @JsonAlias({"raca", "racaPet"})
        String racaPet,
        @JsonAlias({"sexo", "sexoPet"})
        String sexoPet,
        @JsonAlias({"dataNascimento", "dataNascimentoPet"})
        LocalDate dataNascimentoPet,
        @JsonAlias({"tutor", "tutorPet"})
        Long idTutor
) {}
