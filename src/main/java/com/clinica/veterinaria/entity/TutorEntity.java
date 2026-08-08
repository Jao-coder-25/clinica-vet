package com.clinica.veterinaria.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tb_tutor")
public class TutorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tutor")
    private Long idTutor;

    @Column(name = "nome_tutor", nullable = false)
    private String nomeTutor;

    @Column(name = "cpf_tutor", nullable = false, unique = true)
    private String cpfTutor;

    @Column(name = "telefone_tutor", nullable = false)
    private String telefoneTutor;
}
