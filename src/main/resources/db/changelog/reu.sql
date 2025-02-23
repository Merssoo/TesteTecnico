--liquibase formatted sql
--changeset emerson.trindade:1 disablePermissionsGeneration: true
--Criação tabela de reu
CREATE TABLE reu (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL
);
--rollback DROP TABLE reu
