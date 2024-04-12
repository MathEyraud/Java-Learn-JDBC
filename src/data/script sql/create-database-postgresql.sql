-- Création de la table JOUEUR
CREATE TABLE joueur (
    id BIGSERIAL PRIMARY KEY,
    nom VARCHAR(20) NOT NULL,
    prenom VARCHAR(20),
    sexe CHAR(1) CHECK (sexe IN ('H', 'F')) -- H pour homme, F pour femme
);

-- Création de la table TOURNOI
CREATE TABLE tournoi (
    id BIGSERIAL PRIMARY KEY,
    nom VARCHAR(20) NOT NULL,
    code VARCHAR(2) NOT NULL
);

-- Création de la table EPREUVE
CREATE TABLE epreuve (
    id BIGSERIAL PRIMARY KEY,
    annee SMALLINT NOT NULL,
    type_epreuve CHAR(1) CHECK (type_epreuve IN ('H', 'F')), -- H pour hommes, F pour femmes
    id_tournoi BIGINT NOT NULL REFERENCES tournoi(id)
);

-- Création de la table MATCH_TENNIS
CREATE TABLE match_tennis (
    id BIGSERIAL PRIMARY KEY,
    id_epreuve BIGINT NOT NULL REFERENCES epreuve(id),
    id_vainqueur BIGINT NOT NULL REFERENCES joueur(id),
    id_finaliste BIGINT NOT NULL REFERENCES joueur(id)
);

-- Création de la table SCORE_VAINQUEUR
CREATE TABLE score_vainqueur (
    id BIGSERIAL PRIMARY KEY,
    id_match BIGINT NOT NULL REFERENCES match_tennis(id),
    set_1 SMALLINT, -- SMALLINT est utilisé ici car il n'y a pas de TINYINT en PostgreSQL
    set_2 SMALLINT,
    set_3 SMALLINT,
    set_4 SMALLINT,
    set_5 SMALLINT
);