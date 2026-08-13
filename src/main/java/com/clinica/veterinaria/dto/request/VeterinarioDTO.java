package com.clinica.veterinaria.exceptions.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;

public record VeterinarioDTO(

        @NotBlank(message = "A especialidade do veterinário é obrigatório.")
        @JsonAlias({"especialidade_veterinario"})
        String especialidadeVeterinario,

        @NotBlank(message = "O nome do veterinário é obrigatório.")
        @JsonAlias({"nome_veterinario"})
        String nomeVeterinario,

        @NotBlank(message = "O telefone do veterinário é obrigatório.")
        @JsonAlias({"telefone_veterinario"})
        String telefoneVeterinario,

        @NotBlank(message = "O CPF é obrigatório")
        @JsonAlias({"cpf_veterinario"})
        String cpfVeterinario
) {}
