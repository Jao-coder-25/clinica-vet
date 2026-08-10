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
        VeterinarioEntity veterinarioEntity = new VeterinarioEntity();
        veterinarioEntity.setNomeVeterinario(veterinarioDTO.nomeVeterinario());
        veterinarioEntity.setEspecialidade(veterinarioDTO.especialidadeVeterinario());
        veterinarioEntity.setTelefoneVeterinario(veterinarioDTO.telefoneVeterinario());
        veterinarioEntity.setCpfVeterinario(veterinarioDTO.cpfVeterinario());

        VeterinarioEntity veterinarioSalvo = veterinarioRepository.save(veterinarioEntity);
        return new VeterinarioResponseDTO(veterinarioSalvo);
    }
}
