CREATE TABLE tb_tutor (
    id_tutor BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome_tutor VARCHAR(100) NOT NULL,
    cpf_tutor VARCHAR(11) NOT NULL UNIQUE,
    telefone_tutor VARCHAR(15) NOT NULL
);

CREATE TABLE tb_veterinario (
    id_veterinario BIGINT PRIMARY KEY AUTO_INCREMENT,
    especialidade VARCHAR(50) NOT NULL,
    nome_veterinario VARCHAR(100) NOT NULL,
    telefone_veterinario VARCHAR(15) NOT NULL
);

CREATE TABLE tb_pet (
    id_pet BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome_pet VARCHAR(100) NOT NULL,
    especie VARCHAR(50) NOT NULL,
    raca VARCHAR(50) NULL,
    sexo VARCHAR(9) NOT NULL,
    data_nascimento DATE NOT NULL,
    id_tutor BIGINT NOT NULL,

    CONSTRAINT fk_pet_tutor FOREIGN KEY (id_tutor) REFERENCES tb_tutor(id_tutor)
);

CREATE TABLE tb_consulta (
    id_consulta BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo_consulta VARCHAR(50) NOT NULL,
    data_consulta DATETIME NOT NULL,
    id_pet BIGINT NOT NULL,
    id_veterinario BIGINT NOT NULL,

    CONSTRAINT fk_consulta_pet FOREIGN KEY (id_pet) REFERENCES tb_pet(id_pet),
    CONSTRAINT fk_consulta_veterinario FOREIGN KEY (id_veterinario) REFERENCES tb_veterinario(id_veterinario)
);