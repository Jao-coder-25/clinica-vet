package com.clinica.veterinaria.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record VeterinarioDTO(
        @JsonAlias({"especialidade_veterinario"})
        String especialidadeVeterinario,
        @JsonAlias({"nome_veterinario"})
        String nomeVeterinario,
        @JsonAlias({"telefone_veterinario"})
        String telefoneVeterinario
) {}
