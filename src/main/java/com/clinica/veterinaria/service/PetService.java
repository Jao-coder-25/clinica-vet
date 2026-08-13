package com.clinica.veterinaria.service;

import com.clinica.veterinaria.dto.request.PetDTO;
import com.clinica.veterinaria.dto.response.PetResponseDTO;
import com.clinica.veterinaria.entity.PetEntity;
import com.clinica.veterinaria.repository.ConsultaRepository;
import com.clinica.veterinaria.repository.PetRepository;
import com.clinica.veterinaria.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final TutorRepository tutorRepository;
    private final ConsultaRepository consultaRepository;

    public PetService(PetRepository petRepository, TutorRepository tutorRepository, ConsultaRepository consultaRepository) {
        this.petRepository = petRepository;
        this.tutorRepository = tutorRepository;
        this.consultaRepository = consultaRepository;
    }

    public PetResponseDTO save(PetDTO petDTO) {
        Long IdTutor = petDTO.idTutor();
        boolean tutorExists = tutorRepository.existsById(IdTutor);

        if(!tutorExists) {
            throw new IllegalArgumentException("O tutor com ID " + IdTutor + " não existe.");
        }

        PetEntity petEntity = PetEntity.builder()
                .nomePet(petDTO.nomePet())
                .especie(petDTO.especiePet())
                .raca(petDTO.racaPet())
                .sexo(petDTO.sexoPet())
                .dataNascimento(petDTO.dataNascimentoPet())
                .tutor(tutorRepository.getReferenceById(IdTutor))
                .build();

        return new PetResponseDTO(petRepository.save(petEntity));
    }
    public void delete(Long idPet) {
        if(!petRepository.existsById(idPet)) {
            throw new IllegalArgumentException("O pet informado não existe");
        }
        if(consultaRepository.existsByPetIdPet(idPet)) {
            throw new IllegalArgumentException("Não é possível excluir o pet, pois ele possui consultas associadas.");
        }
        petRepository.deleteById(idPet);
    }
}
