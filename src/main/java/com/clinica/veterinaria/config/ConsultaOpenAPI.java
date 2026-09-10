package com.clinica.veterinaria.config;

import com.clinica.veterinaria.dto.request.ConsultaDTO;
import com.clinica.veterinaria.dto.response.ConsultaResponseDTO;
import com.clinica.veterinaria.dto.response.HorarioDisponivelResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "Consulta", description = "Métodos de gerenciamento das Consultas")
public interface ConsultaOpenAPI {

    @Operation(summary = "Criar uma nova consulta", description = "Método que salva uma nova consulta")
    ResponseEntity<ConsultaResponseDTO> save(ConsultaDTO consultaDTO);

    @Operation(summary = "Deletar uma consulta", description = "Método que deleta uma consulta")
    ResponseEntity<Void> delete(@PathVariable Long idConsulta);

    @Operation(summary = "Listar horários de consultas disponíveis", description = "Método que lista os horários disponíveis para consulta")
    ResponseEntity<List<HorarioDisponivelResponseDTO>> buscarHorariosDisponiveis (LocalDate data);

    @Operation(summary = "Listar todas as consultas", description = "Método que lista todas as consultas")
    ResponseEntity<List<ConsultaResponseDTO>> ListarConsultas();
}
