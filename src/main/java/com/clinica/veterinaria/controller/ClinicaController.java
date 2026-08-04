package com.clinica.veterinaria.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class ClinicaController {

    @GetMapping
    @ResponseBody
    public String mensagem() {
        return "Bem-vindo ao Dashboard da Clínica Veterinária!";
    }
}
