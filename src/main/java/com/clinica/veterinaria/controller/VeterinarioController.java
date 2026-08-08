package com.clinica.veterinaria.controller;

import com.clinica.veterinaria.dto.VeterinarioDTO;
import com.clinica.veterinaria.entity.VeterinarioEntity;
import com.clinica.veterinaria.repository.VeterinarioRepository;
import com.clinica.veterinaria.service.VeterinarioService;
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
    public ResponseEntity<VeterinarioEntity> save(@RequestBody VeterinarioDTO veterinarioDTO) {
        VeterinarioEntity salvarVeterinario = veterinarioService.save(veterinarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvarVeterinario);
    }
}
