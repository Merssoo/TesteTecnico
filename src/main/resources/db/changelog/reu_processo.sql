--liquibase formatted sql
--changeset emerson.trindade:1 disablePermissionsGeneration: true
--Tabela intermediária para relação muitos-para-muitos
CREATE TABLE reu_processo (
    id BIGSERIAL PRIMARY KEY,
    reu_id BIGINT NOT NULL,
    processo_id BIGINT NOT NULL,
    CONSTRAINT cku_reu_processo UNIQUE (reu_id, processo_id),
    CONSTRAINT fk_reu FOREIGN KEY (reu_id) REFERENCES reu (id) ON DELETE CASCADE,
    CONSTRAINT fk_processo FOREIGN KEY (processo_id) REFERENCES processo (id) ON DELETE CASCADE
);
--rollback DROP TABLE reu_processo;

--changeset emerson.trindade:2 disablePermissionsGeneration: true
--Criando índices para melhorar a performance
CREATE INDEX idx_reu_processo_reu ON reu_processo (reu_id);
CREATE INDEX idx_reu_processo_processo ON reu_processo (processo_id);
--rollback DROP INDEX idx_reu_processo_reu;
--rollback DROP INDEX idx_reu_processo_processo;