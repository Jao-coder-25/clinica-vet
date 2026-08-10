package com.clinica.veterinaria.dto.response;

import com.clinica.veterinaria.entity.VeterinarioEntity;

public record VeterinarioResponseDTO(
        Long idVeterinario,
        String nomeVeterinario,
        String especialidadeVeterinario,
        String telefoneVeterinario
) {
    public VeterinarioResponseDTO(VeterinarioEntity veterinario) {
        this (
                veterinario.getIdVeterinario(),
                veterinario.getNomeVeterinario(),
                veterinario.getEspecialidade(),
                veterinario.getTelefoneVeterinario()
        );
    }
}
