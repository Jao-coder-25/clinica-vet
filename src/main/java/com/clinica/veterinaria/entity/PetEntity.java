package com.clinica.veterinaria.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table (name = "tb_pet")
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet")
    private Long idPet;

    @Column (name = "nome_pet", nullable = false)
    private String nomePet;

    @Column (name = "especie", nullable = false)
    private String especie;

    @Column (name = "raca")
    private String raca;

    @Column (name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column (name = "sexo", nullable = false)
    private String sexo;

    // um tutor pode ter vários pets, mas cada pet pertence a um único tutor
    @ManyToOne(fetch = FetchType.LAZY)  // busca somente o que for necessário, neste caso ele vai buscar o id do tutor, mas não vai buscar os dados do tutor, a menos que seja necessário
    @JoinColumn(name = "id_tutor", nullable = false)
    private TutorEntity tutor;
}