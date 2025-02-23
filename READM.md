# Microserviço de Gestão de Réus e Processos

Este projeto é um microserviço desenvolvido com **Spring Boot**, **Maven**, **Liquibase** e **PostgreSQL**, que tem como objetivo realizar o cadastro e vinculação de réus e processos jurídicos. O serviço oferece operações de **CRUD** para a gestão de processos e possibilita o relacionamento entre réus e processos.

## Tecnologias Utilizadas

- **Spring Boot**: Framework utilizado para criação do microserviço.
- **Maven**: Gerenciador de dependências e construção do projeto.
- **Liquibase**: Ferramenta para gerenciamento de versionamento de banco de dados.
- **PostgreSQL**: Banco de dados relacional utilizado para persistência dos dados.
- **MapStruct**: Para mapeamento de objetos entre DTOs e entidades.
- **H2**: Como banco de dados para testes durante o desenvolvimento e execução de testes automatizados.
- **JUnit**: Framework de testes para a aplicação.

## Funcionalidades

1. **Cadastro de Réus**: O sistema permite o cadastro de réus, com as informações necessárias como nome, CPF.
2. **Cadastro de Processos**: O sistema permite o cadastro de processos jurídicos, com informações como número do processo.
3. **Vinculação de Réus aos Processos**: O sistema permite vincular réus a processos jurídicos.
4. **CRUD de Processos**: Operações para criar, ler, atualizar e excluir processos.
5. **CRUD de Réus**: Operações para criar, ler, atualizar e excluir réus.
6. **CRUD de vinculos Réus e Processo**: Operações para criar, ler, atualizar e excluir vinculos de réus e processos.

## Como Rodar o Projeto

### Pré-requisitos

1. **Java 17** ou superior
2. **PostgreSQL** configurado e rodando localmente ou em um servidor.
3. **Maven** para construção do projeto.
4. Configuração do banco de dados no arquivo `application.properties`.

### Passos para Execução

1. Clone o repositório
2. Configure o banco de dados no arquivo src/main/resources/application.properties:
```
propertiesspring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```
    Subistitua: nome_do_banco pelo nome do seu banco de dados PostgreSQL.
    seu_usuario e sua_senha pelas credenciais do seu banco.
3. Rode o Liquibase para aplicar as migrações no banco de dados:
```mvn liquibase:update```
4. Para rodar o projeto localmente, execute o seguinte comando:
```mvn spring-boot:run```
5. O microserviço estará rodando em http://localhost:8080


## Endpoints Disponíveis

### RÉUS
 - POST /reu: Cria um novo réu.
 - GET /reu: Retorna todos os réus cadastrados.
 - GET /reu/{id}: Retorna um réu pelo seu ID.
 - PUT /reu/{id}: Atualiza os dados de um réu.
 - DELETE /reu/{id}: Deleta um réu pelo seu ID.

### Processos
 - POST /processos: Cria um novo processo.
 - GET /processos: Retorna todos os processos cadastrados.
 - GET /processos/{id}: Retorna um processo pelo seu ID.
 - PUT /processos/{id}: Atualiza os dados de um processo.
 - DELETE /processos/{id}: Deleta um processo pelo seu ID.

### ReusProcessos
 - POST /reu-processo: Cria um novo vinculo de processo e réu.
 - GET /reu-processo: Retorna todos os vinculos de processos e réu cadastrados.
 - GET /reu-processo/{id}: Retorna um vinculo de processo e réu pelo seu ID.
 - PUT /reu-processo/{id}: Atualiza os dados do vinculo de processo e réu.
 - DELETE /reu-processo/{id}: Deleta um vinculo de processo e réu pelo seu ID.

## Testes

O projeto já inclui testes utilizando o framework JUnit para garantir a qualidade e funcionamento correto do microserviço. Para rodar os testes, execute:
    ```mvn test```
