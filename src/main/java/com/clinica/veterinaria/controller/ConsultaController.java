package com.clinica.veterinaria.controller;

import com.clinica.veterinaria.dto.request.ConsultaDTO;
import com.clinica.veterinaria.dto.response.ConsultaResponseDTO;
import com.clinica.veterinaria.dto.response.HorarioDisponivelResponseDTO;
import com.clinica.veterinaria.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> save(@RequestBody @Valid ConsultaDTO consultaDTO) {
        ConsultaResponseDTO salvarConsulta = consultaService.save(consultaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvarConsulta);
    }

    @DeleteMapping("/{idConsulta}")
    public ResponseEntity<Void> delete(@PathVariable Long idConsulta) {
        consultaService.delete(idConsulta);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/horarios-disponiveis")
    public ResponseEntity<List<HorarioDisponivelResponseDTO>> buscarHorariosDisponiveis (
            @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data){
        List<HorarioDisponivelResponseDTO> resposta = consultaService.listarHorarioDisponiveis(data);
        return ResponseEntity.ok(resposta);
    }
}
