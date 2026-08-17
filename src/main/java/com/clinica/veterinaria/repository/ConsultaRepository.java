package com.clinica.veterinaria.repository;

import com.clinica.veterinaria.entity.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

    boolean existsByVeterinarioIdVeterinario(Long idVeterinario);
    boolean existsByPetIdPet(Long idPet);
    List<ConsultaEntity> findAllByDataConsulta(LocalDate dataConsulta);
}
