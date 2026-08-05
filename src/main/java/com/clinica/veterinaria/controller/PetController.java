package com.clinica.veterinaria.controller;

import com.clinica.veterinaria.PetService;
import com.clinica.veterinaria.entity.PetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<PetEntity> save(@RequestBody PetEntity petEntity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.save(petEntity));
    }
}
