package com.clinica.veterinaria.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;

public record ConsultaDTO(
        @JsonAlias({"tipo_consulta"})
        String tipoConsulta,
        @JsonAlias({"data_consulta"})
        LocalDate dataConsulta,
        @JsonAlias({"id_veterinario"})
        Long idVeterinario,
        @JsonAlias({"id_pet"})
        Long idPet
) {}
