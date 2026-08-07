package com.clinica.veterinaria.controller;

import com.clinica.veterinaria.dto.PetDTO;
import com.clinica.veterinaria.service.PetService;
import com.clinica.veterinaria.entity.PetEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }
    @PostMapping
    public ResponseEntity<PetEntity> save(@RequestBody PetDTO petDTO) {
        PetEntity petSalvo = petService.save(petDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(petSalvo);
    }
}
