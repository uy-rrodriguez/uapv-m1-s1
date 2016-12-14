--
-- PostgreSQL database Tp3 (SGBD Master 1)
--

--
-- Name: PKregion
--

ALTER TABLE ONLY region
    ADD CONSTRAINT "PKregion" PRIMARY KEY (code_region);

--
-- Name: PKdepartement
--

ALTER TABLE ONLY departement
    ADD CONSTRAINT "PKdepartement" PRIMARY KEY (code_departement);

--
-- Name: FK1departement
--

ALTER TABLE ONLY departement
    ADD CONSTRAINT "FK1departement" FOREIGN KEY (code_region) REFERENCES region(code_region);

--
-- Name: PKcommune
--

ALTER TABLE ONLY commune
    ADD CONSTRAINT "PKcommune" PRIMARY KEY (code_departement,code_commune);

--
-- Name: FK1commune
--

ALTER TABLE ONLY commune
    ADD CONSTRAINT "FK1commune" FOREIGN KEY (code_departement) REFERENCES departement(code_departement);
--
-- Name: PKnaissance
--

ALTER TABLE ONLY naissance
    ADD CONSTRAINT "PKnaissance" PRIMARY KEY (annee, code_departement, code_commune);

--
-- Name: FK1naissance
--

ALTER TABLE ONLY naissance
    ADD CONSTRAINT "FK1naissance" FOREIGN KEY (code_departement, code_commune) REFERENCES commune(code_departement, code_commune);
