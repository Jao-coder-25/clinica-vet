package com.clinica.veterinaria.repository;

import com.clinica.veterinaria.entity.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

    boolean existsByVeterinarioIdVeterinario(Long idVeterinario);
    boolean existsByPetIdPet(Long idPet);

}
