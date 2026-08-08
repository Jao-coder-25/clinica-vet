package com.clinica.veterinaria.repository;

import com.clinica.veterinaria.entity.TutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<TutorEntity, Long>{

    boolean existsByCpfTutor(String cpfTutor);
}
