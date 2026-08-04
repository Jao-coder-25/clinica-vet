package com.clinica.veterinaria.repository;

import com.clinica.veterinaria.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<PetEntity, Long> {
}
