package com.clinica.veterinaria;

import com.clinica.veterinaria.entity.TutorEntity;
import com.clinica.veterinaria.repository.TutorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ClinicaVeterinariaApplicationTests {

    @Autowired
    private TutorRepository tutorRepository;

    @Test
    void deveSalvarTutorNoBancoEDeixarLa() {
        TutorEntity tutor = new TutorEntity();
        tutor.setNomeTutor("Tutor Teste Oficial");
        tutor.setCpfTutor("99999999999");
        tutor.setTelefoneTutor("11988888888");

        // Salva e commita no banco de dados de verdade
        TutorEntity tutorSalvo = tutorRepository.save(tutor);

        assertNotNull(tutorSalvo.getIdTutor());
    }
}
