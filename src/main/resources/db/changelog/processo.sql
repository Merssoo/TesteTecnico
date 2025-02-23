--liquibase formatted sql
--changeset emerson.trindade:1 disablePermissionsGeneration: true
--Criação tabela de processos
CREATE TABLE processo (
    id BIGSERIAL PRIMARY KEY,
    numero_processo VARCHAR(50) UNIQUE NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT NOW()
);
--rollback DROP TABLE processo