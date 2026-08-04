package com.clinica.veterinaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_veterinario")
public class VeterinarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_veterinario")
    private Long idVeterinario;

    @Column(name = "nome_veterinario", nullable = false)
    private String nomeVeterinario;

    @Column(name = "especialidade", nullable = false)
    private String especialidade;

    @Column(name = "telefone_veterinario", nullable = false)
    private String telefoneVeterinario;
}
