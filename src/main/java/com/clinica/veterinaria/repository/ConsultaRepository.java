package com.clinica.veterinaria.repository;

import com.clinica.veterinaria.entity.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

    boolean existsByVeterinarioIdVeterinario(Long idVeterinario);
    boolean existsByPetIdPet(Long idPet);
    @Query("SELECT c.horarioConsulta FROM ConsultaEntity c WHERE c.dataConsulta = :dataConsulta")
    List<LocalTime> findHorariosOcupadosPorData(@Param("dataConsulta") LocalDate dataConsulta);
}
