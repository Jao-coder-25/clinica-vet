package com.clinica.veterinaria.config;

import com.clinica.veterinaria.dto.request.VeterinarioDTO;
import com.clinica.veterinaria.dto.response.VeterinarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Veterinario", description = "Métodos de gerenciamento de veterinários")
public interface VeterinarioOpenAPI {

    @Operation(summary = "Criar um novo veterinário", description = "Método que salva um novo veterinário")
    ResponseEntity<VeterinarioResponseDTO> save(VeterinarioDTO veterinarioDTO);

    @Operation(summary = "Deletar um veterinário", description = "Método que deleta um veterinário")
    ResponseEntity<Void> delete(Long idVeterinario);
}
