package com.clinica.veterinaria.service;

import com.clinica.veterinaria.dto.request.VeterinarioDTO;
import com.clinica.veterinaria.dto.response.VeterinarioResponseDTO;
import com.clinica.veterinaria.entity.VeterinarioEntity;
import com.clinica.veterinaria.repository.ConsultaRepository;
import com.clinica.veterinaria.repository.VeterinarioRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;
    private final ConsultaRepository consultaRepository;

    public VeterinarioService(VeterinarioRepository veterinarioRepository, ConsultaRepository consultaRepository) {
        this.veterinarioRepository = veterinarioRepository;
        this.consultaRepository = consultaRepository;
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
    public void delete(Long idVeterinario) {

        if(!veterinarioRepository.existsById(idVeterinario)){
            throw new IllegalArgumentException("O veterinário com o ID informado não existe.");
        }
        boolean possuiConsultas = consultaRepository.existsByVeterinarioIdVeterinario(idVeterinario);

        if (possuiConsultas) {
            throw new IllegalArgumentException("Não é possível excluir o veterinário, pois ele possui consultas associadas.");
        }
        veterinarioRepository.deleteById(idVeterinario);
    }
}
