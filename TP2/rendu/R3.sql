/*
[3] Désactivez le booléen enable_hashagg et générez à nouveau le plan d’exécution de la requête R2.
Comparez le plan obtenu avec celui de la question [2]. Que pouvez-vous en déduire ?

Réactivez le booléen enable_hashagg.
*/

SET enable_hashagg = false;

EXPLAIN SELECT COUNT(naissance.nb)
FROM naissance
WHERE naissance.nb BETWEEN 100 AND 500
    AND (naissance.code_departement, naissance.code_commune) IN
        (SELECT code_departement, code_commune
         FROM commune
         WHERE chef_lieu = 1);

SET enable_hashagg = true;
