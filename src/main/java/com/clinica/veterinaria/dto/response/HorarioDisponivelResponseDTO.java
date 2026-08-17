package com.clinica.veterinaria.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public record HorarioDisponivelResponseDTO(

        LocalDate dataConsulta,
        LocalTime horarioConsulta
) {
}
