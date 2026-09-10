package com.clinica.veterinaria.controller;

import com.clinica.veterinaria.config.PetOpenAPI;
import com.clinica.veterinaria.dto.request.PetDTO;
import com.clinica.veterinaria.dto.response.PetResponseDTO;
import com.clinica.veterinaria.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetController implements PetOpenAPI {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    @Override
    public ResponseEntity<PetResponseDTO> save(@RequestBody @Valid PetDTO petDTO) {
        PetResponseDTO petSalvo = petService.save(petDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(petSalvo);
    }

    @DeleteMapping("/{idPet}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long idPet) {
        petService.delete(idPet);
        return ResponseEntity.noContent().build();
    }
}
