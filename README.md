# 🐾 Clínica Veterinária API

Projeto desenvolvido com o objetivo de consolidar e dominar o desenvolvimento de aplicações com **Spring Boot**, **Spring Data JPA** e **Modelagem de Dados** relacional.

## 🎯 Foco do Projeto
Este repositório serve para aplicar de forma prática e aprofundada os seguintes pilares:
- **Spring Boot 3**: Criação, configuração e gerenciamento do ciclo de vida da aplicação.
- **Spring Data JPA**: Mapeamento objeto-relacional eficiente e gerenciamento de persistência de dados.
- **Modelagem de Dados**: Estruturação de tabelas, chaves, índices e relacionamentos integrados ao MySQL.

## 🛠️ Tecnologias Utilizadas
- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA** (Persistência e consultas)
- **MySQL Driver** (Conector do banco de dados)
- **Flyway Migration** (Gerenciamento e evolução de scripts SQL)
- **Lombok** (Produtividade e código limpo)
- **Spring Boot DevTools** (Agilidade no desenvolvimento local)

## 🏗️ Estrutura de Pacotes
O projeto é estruturado seguindo as boas práticas do ecossistema Spring:
- `entity`: Classes Java que representam as tabelas e relacionamentos mapeados via JPA.
- `repository`: Interfaces que estendem o JpaRepository para operações de banco de dados.
- `service`: Camada isolada para implementação das regras e fluxos de negócio.
- `controller`: Exposição dos endpoints da API e controle de requisições.

## 🚀 Como Executar o Projeto Localmente

### 1. Pré-requisitos
- Ter o **Java JDK 21** configurado.
- Ter o **MySQL Server** ativo localmente.

### 2. Configuração de Credenciais
A aplicação utiliza variáveis de ambiente no arquivo `application.yaml` para proteger o acesso ao banco de dados:
```bash
DB_USERNAME=seu_usuario_do_mysql
DB_PASSWORD=sua_senha_do_mysql
```

### 3. Inicialização pelo Terminal
Para rodar a aplicação via linha de comando na raiz do projeto, utilize o Maven Wrapper:
```bash
./mvnw spring-boot:run
```
