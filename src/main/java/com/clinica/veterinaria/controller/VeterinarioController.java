package com.clinica.veterinaria.controller;

import com.clinica.veterinaria.dto.request.VeterinarioDTO;
import com.clinica.veterinaria.dto.response.VeterinarioResponseDTO;
import com.clinica.veterinaria.entity.VeterinarioEntity;
import com.clinica.veterinaria.service.VeterinarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    @PostMapping
    public ResponseEntity<VeterinarioResponseDTO> save(@RequestBody @Valid VeterinarioDTO veterinarioDTO) {
        VeterinarioResponseDTO salvarVeterinario = veterinarioService.save(veterinarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvarVeterinario);
    }
}
