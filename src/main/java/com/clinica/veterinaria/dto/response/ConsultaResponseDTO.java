package com.clinica.veterinaria.dto.response;

import com.clinica.veterinaria.entity.ConsultaEntity;
import java.time.LocalDate;
import java.time.LocalTime;

public record ConsultaResponseDTO(
        Long idConsulta,
        String tipoConsulta,
        LocalDate dataConsulta,
        LocalTime horarioConsulta,
        Long idPet,
        String nomePet,
        Long idVeterinario,
        String nomeVeterinario
) {
    // CONSTRUTOR PERSONALIZADO (O Java identifica como construtor porque tem o mesmo nome do arquivo/Record e não possui tipo de retorno).
    // Ele funciona como um tradutor automático: extrai apenas os campos necessários da entidade do banco de dados,
    // injeta nas variáveis do Record (respeitando estritamente a ordem declarada no topo) e descarta os dados internos redundantes
    public ConsultaResponseDTO(ConsultaEntity consulta) {
        this(
                consulta.getIdConsulta(),
                consulta.getTipoConsulta(),
                consulta.getDataConsulta(),
                consulta.getHorarioConsulta(),
                consulta.getPet().getIdPet(),
                consulta.getPet().getNomePet(),
                consulta.getVeterinario().getIdVeterinario(),
                consulta.getVeterinario().getNomeVeterinario()
        );
    }
}
