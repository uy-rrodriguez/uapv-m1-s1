/*
[1] Ecrivez une requête SQL (R1) qui affiche le nombre de déclarations annuelles effectuées par les chefs-lieux de
canton dont le nombre des naissances est compris entre 100 et 500. Cette requête devra afficher la valeur de 745
et être écrite avec :
    1. une jointure interne entre les tables commune et naissance
    2. une restriction sur le nombre de naissances entre [100, 500]
    3. une restriction sur les communes « chef-lieu » de canton

Analysez le plan d’exécution de la requête R1
*/

EXPLAIN SELECT COUNT(*)
FROM naissance
    INNER JOIN commune USING (code_departement, code_commune)
WHERE naissance.nb BETWEEN 100 AND 500
    AND commune.chef_lieu = 1;
