package com.clinica.veterinaria.config;

import com.clinica.veterinaria.dto.request.TutorDTO;
import com.clinica.veterinaria.dto.response.TutorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Tutor", description = "Métodos de gerenciamento de tutores")
public interface TutorOpenAPI {

    @Operation(summary = "Criar um novo Tutor", description = "Método que salva um novo tutor")
    ResponseEntity<TutorResponseDTO> save(TutorDTO tutorDTO);

    @Operation(summary = "Deletar tutor", description = "Método que deleta o tutor")
    ResponseEntity<Void> delete(Long idTutor);
}
