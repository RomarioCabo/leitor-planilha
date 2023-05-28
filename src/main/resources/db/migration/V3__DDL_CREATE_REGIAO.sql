CREATE TABLE regioes
(
    id_regiao INTEGER,
    sigla     VARCHAR(255) NOT NULL,
    nome      VARCHAR(255) NOT NULL,
    CONSTRAINT pk_regioes PRIMARY KEY (id_regiao)
);