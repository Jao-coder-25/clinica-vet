package com.clinica.veterinaria.repository;

import com.clinica.veterinaria.entity.VeterinarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioRepository extends JpaRepository<VeterinarioEntity, Long> {

    boolean existsByCpfVeterinario(String cpfVeterinario);
}
