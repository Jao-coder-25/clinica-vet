# 🐾 Clínica Veterinária API

API REST desenvolvida com o objetivo de consolidar e aprofundar conhecimentos em **Java, Spring Boot, Spring Data JPA, modelagem de dados relacionais, persistência, validação e testes automatizados**.

O projeto simula o backend de uma clínica veterinária, aplicando uma arquitetura organizada em camadas e regras de negócio para o gerenciamento de tutores, pets e demais recursos da aplicação.

---

## 🎯 Objetivos do Projeto

Este projeto tem como objetivo aplicar na prática conceitos importantes do desenvolvimento backend com Java e Spring:

* Desenvolvimento de **APIs REST** com Spring Boot.
* Implementação de regras de negócio utilizando a camada **Service**.
* Persistência de dados utilizando **Spring Data JPA** e Hibernate.
* Modelagem de banco de dados relacional.
* Gerenciamento e versionamento do banco de dados com **Flyway**.
* Validação de dados recebidos pela API.
* Utilização de **DTOs** para entrada e saída de dados.
* Desenvolvimento de **testes automatizados** para as regras de negócio.
* Organização do projeto seguindo uma arquitetura em camadas.

---

## 🛠️ Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 4.1.0**
* **Spring Web MVC** — desenvolvimento dos endpoints REST
* **Spring Data JPA** — persistência e acesso aos dados
* **MySQL** — banco de dados relacional
* **Flyway** — versionamento e gerenciamento das migrações do banco
* **Spring Boot Validation** — validação dos dados recebidos pela API
* **Lombok** — redução de código repetitivo
* **Spring Boot DevTools** — facilitação do desenvolvimento local
* **JUnit** — testes automatizados
* **Mockito** — criação de mocks e isolamento das dependências nos testes
* **Maven** — gerenciamento de dependências e build do projeto

---

## 🏗️ Arquitetura e Estrutura do Projeto

A aplicação utiliza uma arquitetura organizada em camadas, separando as responsabilidades de cada parte do sistema.

```text
src/main/java/com/clinica/veterinaria
│
├── configuration
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── exception
├── repository
└── service
```

### Controller

Responsável por expor os endpoints da API e receber as requisições HTTP.

É nessa camada que são definidos os recursos disponibilizados pela aplicação, além do recebimento e retorno dos dados.

### Service

Responsável pela implementação das **regras de negócio** da aplicação.

Por exemplo, antes de cadastrar um tutor, o Service verifica se o CPF informado já está cadastrado:

```java
boolean cpfExistente =
        tutorRepository.existsByCpfTutor(tutorDTO.cpfTutor());

if (cpfExistente) {
    throw new IllegalArgumentException(
            "O CPF informado já está cadastrado."
    );
}
```

Dessa forma, a regra de negócio fica isolada da camada responsável pelos endpoints.

### Repository

Responsável pelo acesso aos dados utilizando **Spring Data JPA**.

Os repositories permitem realizar operações de persistência sem a necessidade de implementar manualmente toda a comunicação com o banco de dados.

### Entity

Representa as entidades persistidas no banco de dados.

As classes dessa camada utilizam o mapeamento fornecido pelo JPA para estabelecer a relação entre os objetos Java e as tabelas do banco.

### DTO

Os **Data Transfer Objects (DTOs)** são utilizados para transportar dados entre as camadas da aplicação.

O projeto utiliza diferentes DTOs de acordo com a finalidade da operação, como:

```text
dto
├── request
└── response
```

Isso evita expor diretamente as entidades da aplicação através da API e permite controlar quais dados entram e saem de cada operação.

### Configuration

Contém configurações específicas utilizadas pela aplicação.

### Exception

Responsável pela organização e tratamento das exceções da aplicação.

---

# 📚 Documentação da API

A API é estruturada seguindo os princípios de uma **API REST**, utilizando requisições HTTP para manipulação dos recursos da aplicação.

Os dados são recebidos e retornados em formato **JSON**.

## 👤 Tutores

O recurso de tutores permite realizar operações relacionadas ao cadastro e gerenciamento dos responsáveis pelos pets.

### Cadastro de Tutor

Para cadastrar um novo tutor, a API recebe os dados necessários através de um DTO de requisição.

Exemplo de dados:

```json
{
  "nomeTutor": "João",
  "cpfTutor": "12345678901",
  "telefoneTutor": "11999999999"
}
```

Durante o cadastro, uma regra de negócio verifica se o CPF informado já está cadastrado.

Caso o CPF já exista, o cadastro é interrompido e uma exceção é lançada.

Caso contrário, o tutor é convertido para uma entidade e enviado ao repository para persistência.

### Regra de CPF

Um mesmo CPF não pode ser cadastrado para mais de um tutor.

Fluxo da operação:

```text
Requisição
    ↓
Controller
    ↓
TutorService
    ↓
Verifica CPF
    ↓
┌───────────────────┐
│ CPF já cadastrado?│
└─────────┬─────────┘
          │
      ┌───┴───┐
     SIM     NÃO
      ↓        ↓
   Exceção   Salva
      ↓        ↓
   Bloqueia  Banco
```

---

# 🧪 Testes Automatizados

O projeto possui testes automatizados para validar principalmente as regras de negócio implementadas na camada `Service`.

Os testes utilizam **JUnit** e **Mockito**, permitindo testar os serviços de forma isolada, sem depender diretamente do banco de dados.

### Testes do TutorService

O método de cadastro de tutores possui cenários para diferentes comportamentos.

#### CPF não cadastrado

Verifica se um tutor é salvo corretamente quando não existe outro tutor utilizando o mesmo CPF.

Também é verificado se os dados enviados ao repository correspondem aos dados recebidos pelo Service.

#### CPF já cadastrado

Verifica se o sistema impede o cadastro quando o CPF informado já existe.

Nesse cenário, o teste verifica:

* Se uma `IllegalArgumentException` é lançada.
* Se a consulta de existência do CPF é realizada.
* Se o método `save()` do repository **não é chamado**.

Exemplo da verificação:

```java
Mockito.verify(
        tutorRepository,
        Mockito.never()
).save(Mockito.any(TutorEntity.class));
```

Os testes têm como objetivo proteger as regras de negócio contra alterações futuras que possam introduzir comportamentos incorretos.

---

# 🗄️ Banco de Dados

A aplicação utiliza **MySQL** como banco de dados relacional.

A evolução da estrutura do banco é controlada pelo **Flyway**, permitindo versionar as alterações realizadas através de migrations SQL.

As migrations ficam organizadas em:

```text
src/main/resources/db/migration/
```

Cada migration representa uma alteração versionada na estrutura do banco de dados.

Exemplo:

```text
V1__create_table_tutor.sql
V2__create_table_pet.sql
```

O Flyway executa as migrations de acordo com sua versão, mantendo o histórico das alterações realizadas no banco.

---

# 🚀 Como Executar o Projeto Localmente

## 1. Pré-requisitos

Antes de executar o projeto, é necessário ter:

* **JDK 21** instalado e configurado.
* **MySQL Server** instalado e em execução.
* **Git** instalado.
* Uma IDE compatível com projetos Maven, como IntelliJ IDEA.

## 2. Configuração do Banco de Dados

Crie o banco de dados MySQL utilizado pela aplicação.

As credenciais de acesso são configuradas através de variáveis de ambiente:

```bash
DB_USERNAME=seu_usuario_do_mysql
DB_PASSWORD=sua_senha_do_mysql
```

## 3. Executando a Aplicação

Na raiz do projeto, execute o Maven Wrapper.

### Windows

```bash
mvnw.cmd spring-boot:run
```

### Linux/macOS

```bash
./mvnw spring-boot:run
```

## 4. Executando os Testes

Para executar todos os testes automatizados:

### Windows

```bash
mvnw.cmd test
```

### Linux/macOS

```bash
./mvnw test
```

---

# 📌 Status do Projeto

🚧 **Em desenvolvimento**

O projeto continua sendo expandido com novos recursos, regras de negócio, validações e testes automatizados.

O objetivo é utilizar a aplicação como um projeto prático para aprofundar conhecimentos em **desenvolvimento backend com Java e Spring Boot**, aplicando conceitos utilizados no desenvolvimento de APIs profissionais.

---
