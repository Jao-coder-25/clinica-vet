package com.clinica.veterinaria.dto.response;

import com.clinica.veterinaria.entity.ConsultaEntity;
import java.time.LocalDateTime;

public record ConsultaResponseDTO(
        Long idConsulta,
        String tipoConsulta,
        LocalDateTime dataConsulta,
        Long idPet,
        String nomePet,
        Long idVeterinario,
        String nomeVeterinario
) {
    public ConsultaResponseDTO(ConsultaEntity consulta) {
        this(
                consulta.getIdConsulta(),
                consulta.getTipoConsulta(),
                consulta.getDataConsulta(),
                consulta.getPet().getIdPet(),
                consulta.getPet().getNomePet(),
                consulta.getVeterinario().getIdVeterinario(),
                consulta.getVeterinario().getNomeVeterinario()
        );
    }
}
