CREATE TABLE micro_regiao
(
    id_micro_regiao INTEGER,
    nome            VARCHAR(255) NOT NULL,
    id_mesorregiao  INTEGER      NOT NULL,
    id_uf           INTEGER      NOT NULL,
    CONSTRAINT pk_micro_regiao PRIMARY KEY (id_micro_regiao)
);