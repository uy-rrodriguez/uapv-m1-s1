--
-- PostgreSQL database Tp3 (SGBD Master 1)
--

-- AJOUT PROF
ALTER TABLE naissance DROP CONSTRAINT "FK1naissance";
ALTER TABLE commune DROP CONSTRAINT "PKcommune";


--
-- Name: departement_idx
--

CREATE INDEX departement_idx ON departement USING btree (code_region);

--
-- Name: commune_idx
--

-- Inutile car PK (code_departement, code_commune)
--CREATE INDEX commune_idx ON commune USING btree (code_departement);

--
-- Name: naissance_idx
--

CREATE INDEX naissance_idx ON naissance USING btree (code_departement, code_commune);
