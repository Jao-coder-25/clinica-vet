package com.clinica.veterinaria.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ConsultaDTO(

        @NotBlank(message = "O tipo de consulta é obrigatório.")
        @JsonAlias({"tipo_consulta"})
        String tipoConsulta,

        @NotNull(message = "A data da consulta é obrigatória.")
        @JsonAlias({"data_consulta"})
        LocalDate dataConsulta,

        @NotNull(message = "O horário da consulta é obrigatório.")
        @JsonAlias({"horario_consulta"})
        LocalDate horarioConsulta,

        @NotNull(message = "O ID do veterinário é obrigatório.")
        @JsonAlias({"id_veterinario"})
        Long idVeterinario,

        @NotNull(message = "O ID do pet é obrigatório.")
        @JsonAlias({"id_pet"})
        Long idPet
) {}
