package com.clinica.veterinaria.controller;

import com.clinica.veterinaria.dto.request.TutorDTO;
import com.clinica.veterinaria.dto.response.TutorResponseDTO;
import com.clinica.veterinaria.entity.TutorEntity;
import com.clinica.veterinaria.service.TutorService;
import jakarta.validation.Valid;
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
    public ResponseEntity<TutorResponseDTO> save(@RequestBody @Valid TutorDTO tutorDTO) {
        TutorResponseDTO tutorSalvo = tutorService.save(tutorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(tutorSalvo);
    }
}
