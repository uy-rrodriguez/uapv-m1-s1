/*
    La classe ABR  represente un noeud d'un Arbre Binaire de Recherche, contenant une donnee avec une valeur et un cle.

    Les methodes implementees sont recursives
*/

class ABR<T> {
    DonneeGenerique<T> donnee;
    ABR<T> pere;
    ABR<T> filsGauche;
    ABR<T> filsDroit;

    public ABR(DonneeGenerique<T> donnee) {
        this.donnee     = donnee;
        this.pere       = null;
        this.filsGauche = null;
        this.filsDroit  = null;
    }

    /*
        insertionFeuille(d) : ins`ere un nouveau noeud contenant une donn ́ee d en feuille de l’arbre ;
    */
    public void insertionFeuille(DonneeGenerique<T> d) {
        int comparation = this.donnee.compareTo(d);

        // Cas nouvelle donnee plus petite
        if (comparation < 0) {
            if (filsGauche == null) {
                filsGauche = new ABR<T>(d);
                filsGauche.pere = this;
            }
            else {
                filsGauche.insertionFeuille(d);
            }
        }

        // Cas nouvelle donnee plus grande
        else if (comparation > 0) {
            if (filsDroit == null) {
                filsDroit = new ABR<T>(d);
                filsDroit.pere = this;
            }
            else {
                filsDroit.insertionFeuille(d);
            }
        }

        // Cas nouvelle donnee egale
        else {}
    }

    /*
        toString() : renvoie une chaˆıne de caract`eres qui contient l’ensemble des donn ́ees de l’arbre dans l’ordre
                     lexicographique des cl ́es et s ́epar ́es par une virgule ;
    */
    public String toString() {
        String res = donnee.cle + ", ";

        if (filsGauche != null) {
            //res = filsGauche.toString() + ", " + res;
            res = filsGauche.toString() + res;
        }

        if (filsDroit != null) {
            //res = res + ", " + filsDroit.toString();
            res = res + filsDroit.toString();
        }

        return res;
    }

    /*
        recherche(String cle) : renvoie la r ́ef ́erence de l’objet de cl ́e “cle” s’il existe, ou “null” sinon ;\
    */
    public DonneeGenerique<T> recherche(String cle) {
        int comparation = this.donnee.cle.compareTo(cle);

        if (comparation == 0) {
            return this.donnee;
        }
        else if (comparation < 0) {
            if (filsGauche != null)
                return filsGauche.recherche(cle);
        }
        else {
            if (filsDroit != null)
                return filsDroit.recherche(cle);
        }

        // Si on arrive la c'est parce que la cle n'existe pas
        return null;
    }

    /*
        minimum() : renvoie la r ́ef ́erence du noeud contenant l’objet de cl ́e minimale ;
    */
    public DonneeGenerique<T> minimum() {
        if (filsGauche == null)
            return donnee;
        else
            return filsGauche.minimum();
    }

    /*
        maximum() : renvoie la r ́ef ́erence du noeud contenant l’objet de cl ́e maximale
    */
    public DonneeGenerique<T> maximum() {

        if (filsDroit == null)
            return donnee;
        else
            return filsDroit.maximum();
    }

    /*
        hauteur() : renvoie la hauteur de l’arbre
    */
    public int hauteur() {
        int hGauche = 0;
        int hDroit = 0;

        if (filsGauche != null)
            hGauche = filsGauche.hauteur();

        if (filsDroit != null)
            hDroit = filsDroit.hauteur();

        if (hGauche > hDroit)
            return hGauche + 1;
        else
            return hDroit + 1;
    }


    /*
    */
    private static void insererFilsAGauche(ABR<T> pere, ABR<T> fils) {
        pere.filsGauche = fils;
        fils.pere = pere;
    }

    private static void insererFilsADroite(ABR<T> pere, ABR<T> fils) {
        pere.filsDroit = fils;
        fils.pere = pere;
    }

    /*
        supprimer(cle) : supprime l’objet de cl ́e “cle” tout en maintenant la structure d’un ABR.

        SUPPRESSION DE LA RACINE :
            La suppression de la racine represente un probleme particulier.
            - Si l'arbre a un seul element, on va mettre une valeur null dans la donnee.
            - Sinon, si par exemple le noeud a supprimer a un fils gauche non null,
                on va aller chercher le maximum a gauche, on remplacera la donnee du noeud a supprimer par celle
                du maximum, et puis un supprimera la reference au maximum
    */
    public void supprimer(String cle) {
        int comparation = this.donnee.cle.compareTo(cle);

        if (comparation == 0) {

            // CAS 1 : Suppression d'une feuille
            // ----------------------------------------------------------------------------------------------------
            if (filsGauche == null && filsDroit == null) {

                // Cas d'arbre avec un seul element
                if (pere == null) {
                    donne = null;
                }
                else {
                    if (pere.filsGauche == this)
                        pere.filsGauche = null;

                    else // (pere.filsDroit == this)
                        pere.filsDroit = null;
                }
            }


            // CAS 2 : Suppression avec un fils gauche non null
            // ----------------------------------------------------------------------------------------------------
            else if (filsGauche != null) {
                // On va chercher le maximum a gauche
                ABR<T> max = filsGauche.maximum();

                // Cas de suppression de la racine
                if (pere == null) {
                    // Dans ce cas, on utilise la valeur du maximum comme la valeur de la racine.
                    this.donnee = max.donnee;

                    // Si le maximum est le fils gauche
                    if (max == filsGauche) {
                        insererFilsAGauche(this, max.filsGauche);
                    }

                    // Il y a rien a faire avec la droite, elle reste telle quelle est
                }

                // Autres cas, deplacement du noeud maximum
                else {
                    // Association avec le pere
                    if (pere.filsGauche == this)
                        pere.filsGauche = max;
                    else
                        pere.filsDroit = max;


                    // On met le fils droit du noeud a supprimer comme fils droit du maximum
                    insererFilsADroite(max, this.filsDroit);


                    // Si le maximum est le fils gauche, il n'y a rien de plus a faire

                    // Sinon
                    if (max != filsGauche) {
                        // On met le fils gauche du maximum dans sa place
                        insererFilsADroite(max.pere, max.filsGauche);

                        // Et le fils gauche du maximum doit etre le fils gauche du noeud a supprimer
                        insererFilsAGauche(max, this.filsGauche);
                    }
                }
            }


            // CAS 3 : Suppression avec un fils gauche egale a null. On utilise la droite.
            // ----------------------------------------------------------------------------------------------------
            else { // filsDroit != null

                // On va chercher le minimum a droite
                ABR<T> min = filsDroit.minimum();

                // Cas de suppression de la racine
                if (pere == null) {
                    // Dans ce cas, on utilise la valeur du minimum comme la valeur de la racine.
                    this.donnee = min.donnee;

                    // Si le minimum est le fils droit
                    if (min == filsDroit) {
                        insererFilsADroite(this, min.filsDroit);
                    }
                }

                // Autres cas, deplacement du noeud minimum
                else {
                    // Association avec le pere
                    if (pere.filsGauche == this)
                        pere.filsGauche = min;
                    else
                        pere.filsDroit = min;


                    // On met le fils droit du noeud a supprimer comme fils droit du maximum
                    //max.filsDroit = this.filsDroit;
                    insererFilsADroite();


                    // Si le maximum est le fils gauche, il n'y a rien de plus a faire

                    // Sinon
                    if (max != filsGauche) {
                        // On met le fils gauche du maximum dans sa place
                        max.pere.filsDroit = max.filsGauche;

                        // Et le fils gauche du maximum doit etre le fils gauche du noeud a supprimer
                        max.filsGauche = this.filsGauche
                    }
                }

                // Il y a rien a faire avec la gauche, elle reste null
            }

        }

    }

}
