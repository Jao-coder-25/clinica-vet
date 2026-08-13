package com.clinica.veterinaria.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record TutorDTO(

        @NotBlank(message = "O nome do tutor é obrigatório.")
        @JsonAlias({"nome_tutor", "nomeTutor"})
        String nomeTutor,

        @NotBlank(message = "O CPF do tutor é obrigatório.")
        @JsonAlias({"cpf_tutor", "cpfTutor"})
        String cpfTutor,

        @NotBlank(message = "O telefone do tutor é obrigatório.")
        @JsonAlias({"telefone_tutor", "telefoneTutor"})
        String telefoneTutor) {}
