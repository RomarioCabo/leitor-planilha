CREATE TABLE distritos
(
    id_distrito            INTEGER,
    nome                    VARCHAR(255) NOT NULL,
    id_microrregiao         INTEGER      NOT NULL,
    id_mesorregiao          INTEGER      NOT NULL,
    id_uf                   INTEGER      NOT NULL,
    id_regiao_imediata      INTEGER      NOT NULL,
    id_regiao_intermediaria INTEGER      NOT NULL,
    CONSTRAINT pk_distritos PRIMARY KEY (id_distrito)
);