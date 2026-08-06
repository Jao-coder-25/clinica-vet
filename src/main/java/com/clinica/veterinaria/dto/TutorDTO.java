package com.clinica.veterinaria.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record TutorDTO(

        @JsonAlias({"nome_tutor", "nomeTutor"})
        String nomeTutor,
        @JsonAlias({"cpf_tutor", "cpfTutor"})
        String cpfTutor,
        @JsonAlias({"telefone_tutor", "telefoneTutor"})
        String telefoneTutor) {}
