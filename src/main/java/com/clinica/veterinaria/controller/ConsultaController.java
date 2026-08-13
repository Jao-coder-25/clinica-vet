package com.clinica.veterinaria.controller;

import com.clinica.veterinaria.dto.request.ConsultaDTO;
import com.clinica.veterinaria.dto.response.ConsultaResponseDTO;
import com.clinica.veterinaria.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
