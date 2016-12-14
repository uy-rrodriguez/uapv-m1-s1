/*
Transformez la requête SQL (R1) en une requête équivalente (R2) écrite avec :
    1. une projection simple du nombre de déclarations annuelles sur la table naissance
    2. une restriction sur le nombre de naissances entre [100, 500]
    3. une restriction avec l’opérateur IN appliqué sur une sous-requête sélectionnant les communes « chef-lieu » de canton

Analysez le plan d’exécution de la requête R2.
*/

EXPLAIN SELECT COUNT(naissance.nb)
FROM naissance
WHERE naissance.nb BETWEEN 100 AND 500
    AND (naissance.code_departement, naissance.code_commune) IN
        (SELECT code_departement, code_commune
         FROM commune
         WHERE chef_lieu = 1);
