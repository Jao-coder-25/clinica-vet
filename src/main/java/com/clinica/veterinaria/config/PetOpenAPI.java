package com.clinica.veterinaria.config;

import com.clinica.veterinaria.dto.request.PetDTO;
import com.clinica.veterinaria.dto.response.PetResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Tutor", description = "Métodos de gereciamento de Pets")
public interface PetOpenAPI {

    @Operation(summary = "Criar um novo Pet", description = "Método que salva um novo pet")
    ResponseEntity<PetResponseDTO> save(PetDTO petDTO);

    @Operation(summary = "Deleta um Pet", description = "Método que deleta um pet")
    ResponseEntity<Void> delete(Long idPet);
}
