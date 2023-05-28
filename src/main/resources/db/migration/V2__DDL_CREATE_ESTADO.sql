CREATE TABLE estados
(
    id_estado INTEGER,
    sigla     VARCHAR(255) NOT NULL,
    nome      VARCHAR(255) NOT NULL,
    id_regiao INTEGER,
    CONSTRAINT pk_estados PRIMARY KEY (id_estado)
);