package com.clinica.veterinaria.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tb_consulta")
public class ConsultaEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_consulta")
    private Long idConsulta;

    @Column (name = "tipo_consulta", nullable = false)
    private String tipoConsulta;

    @Column (name = "data_consulta", nullable = false)
    private LocalDate dataConsulta;

    @Column (name = "horario_consulta", nullable = false)
    private LocalTime horarioConsulta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "id_pet", nullable = false)
    private PetEntity pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "id_veterinario", nullable = false)
    private VeterinarioEntity veterinario;
}
