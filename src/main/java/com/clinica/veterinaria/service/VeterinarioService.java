package com.clinica.veterinaria.service;

import com.clinica.veterinaria.dto.request.VeterinarioDTO;
import com.clinica.veterinaria.dto.response.VeterinarioResponseDTO;
import com.clinica.veterinaria.entity.VeterinarioEntity;
import com.clinica.veterinaria.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

@Service
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioService(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }
    public VeterinarioResponseDTO save(VeterinarioDTO veterinarioDTO){
        boolean cpfExistente = veterinarioRepository.existsByCpfVeterinario(veterinarioDTO.cpfVeterinario());

        if (cpfExistente) {
            throw new IllegalArgumentException("O CPF informado já está cadastrado.");
        }
        VeterinarioEntity veterinarioEntity = VeterinarioEntity.builder()
                .nomeVeterinario(veterinarioDTO.nomeVeterinario())
                .especialidade(veterinarioDTO.especialidadeVeterinario())
                .telefoneVeterinario(veterinarioDTO.telefoneVeterinario())
                .cpfVeterinario(veterinarioDTO.cpfVeterinario())
                .build();

        return new VeterinarioResponseDTO(veterinarioRepository.save(veterinarioEntity));
    }
}
