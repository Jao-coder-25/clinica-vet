package com.clinica.veterinaria.controller;

import com.clinica.veterinaria.dto.TutorDTO;
import com.clinica.veterinaria.entity.TutorEntity;
import com.clinica.veterinaria.service.TutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @PostMapping
    public ResponseEntity<TutorEntity> save(@RequestBody TutorDTO tutorDTO) {
        TutorEntity tutorSalvo = tutorService.save(tutorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(tutorSalvo);
    }
}
