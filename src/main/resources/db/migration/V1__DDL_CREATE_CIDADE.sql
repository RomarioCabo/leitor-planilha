CREATE TABLE cidade
(
    id_cidade          SERIAL,
    id_cidade_planilha INT          NOT NULL,
    nome               VARCHAR(255) NOT NULL,
    nome_abreviado     VARCHAR(255) NOT NULL,
    latitude           VARCHAR(255) NOT NULL,
    longitude          VARCHAR(255) NOT NULL,
    CONSTRAINT pk_cidade PRIMARY KEY (id_cidade)
);