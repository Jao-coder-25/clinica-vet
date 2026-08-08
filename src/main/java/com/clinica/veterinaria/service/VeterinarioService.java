package com.clinica.veterinaria.service;

import com.clinica.veterinaria.dto.VeterinarioDTO;
import com.clinica.veterinaria.entity.VeterinarioEntity;
import com.clinica.veterinaria.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

@Service
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioService(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }
    public VeterinarioEntity save(VeterinarioDTO veterinarioDTO){
        if(veterinarioDTO.nomeVeterinario() == null || veterinarioDTO.nomeVeterinario().isBlank() || veterinarioDTO.especialidadeVeterinario() == null || veterinarioDTO.telefoneVeterinario() == null){
            throw new IllegalArgumentException("Todos os campos são obrigatórios");
        }
        VeterinarioEntity veterinarioEntity = new VeterinarioEntity();
        veterinarioEntity.setNomeVeterinario(veterinarioDTO.nomeVeterinario());
        veterinarioEntity.setEspecialidade(veterinarioDTO.especialidadeVeterinario());
        veterinarioEntity.setTelefoneVeterinario(veterinarioDTO.telefoneVeterinario());

        return veterinarioRepository.save(veterinarioEntity);
    }
}
