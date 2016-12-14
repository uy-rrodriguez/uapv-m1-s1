--
-- PostgreSQL database Tp (SGBD Master 1)
--

DROP TABLE IF EXISTS naissance CASCADE;
DROP TABLE IF EXISTS commune CASCADE;
DROP TABLE IF EXISTS departement CASCADE;
DROP TABLE IF EXISTS region CASCADE;

--
-- Name: region
--

CREATE TABLE region (
    code_region character varying(2) NOT NULL,
    nom_region character varying(50) NOT NULL
);

--
-- Name: departement
--

CREATE TABLE departement (
    code_region character varying(2) NOT NULL,
    code_departement character varying(3) NOT NULL,
    nom_departement character varying(50) NOT NULL
);

--
-- Name: commune
--

CREATE TABLE commune (
    code_departement character varying(3) NOT NULL,
    code_commune character varying(3) NOT NULL,
    nom_commune character varying(50) NOT NULL,
    chef_lieu integer NOT NULL
);

--
-- Name: naissance
--

CREATE TABLE naissance (
    annee integer NOT NULL,
    code_departement character varying(3) NOT NULL,
    code_commune character varying(3) NOT NULL,
    nb integer NOT NULL
);
