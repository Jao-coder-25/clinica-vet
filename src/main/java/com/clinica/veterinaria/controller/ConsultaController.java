package com.clinica.veterinaria.controller;

import com.clinica.veterinaria.dto.ConsultaDTO;
import com.clinica.veterinaria.entity.ConsultaEntity;
import com.clinica.veterinaria.service.ConsultaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<ConsultaEntity> save(@RequestBody ConsultaDTO consultaDTO) {
        ConsultaEntity salvarConsulta = consultaService.save(consultaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvarConsulta);
    }
}
