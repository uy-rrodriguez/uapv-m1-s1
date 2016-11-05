package tp1;

import java.util.Stack;

/**
 *   La classe ABR  represente un noeud d'un Arbre Binaire de Recherche, contenant une donnee avec une valeur et un cle.
 *   Les methodes implementees sont it�ratives.
 */
class ABR<T> {
    DonneeGenerique donnee;
    ABR<T> pere;
    ABR<T> filsGauche;
    ABR<T> filsDroit;

    public ABR(DonneeGenerique donnee) {
        this.donnee     = donnee;
        this.pere       = null;
        this.filsGauche = null;
        this.filsDroit  = null;
    }


    /*
        Methode auxiliare pour connaitre si un arbre est vide.
        Un arbre sera vide si la racine etait le seul noeud et elle a ete supprime.
        Dans ce cas, la donnee sera null.
    */
    private boolean isEmpty() {
        return (this.donnee == null);
    }


    /*
        insertionFeuille(d) : insère un nouveau noeud contenant une donnée d en feuille de l’arbre ;
    */
    public void insertionFeuille(DonneeGenerique d) {
        /* *** Methode recursive *** */
        /*
            int comparation = this.donnee.compareTo(d);

            // Cas nouvelle donnee plus petite
            if (comparation > 0) {
                if (filsGauche == null) {
                    filsGauche = new ABR<T>(d);
                    filsGauche.pere = this;
                }
                else {
                    filsGauche.insertionFeuille(d);
                }
            }

            // Cas nouvelle donnee plus grande
            else if (comparation < 0) {
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
        */

        /* *** Methode iterative *** */
        // Cas d'arbre vide
        if (this.isEmpty()) {
            this.donnee = d;
        }

        // Autres cas
        else {
            ABR noeud = this;
            ABR noeudPere = this;
            boolean aGauche = false;

            while (noeud != null) {
                int comparation = noeud.donnee.compareTo(d);

                // Cas nouvelle donnee plus petite
                if (comparation > 0) {
                    noeudPere = noeud;
                    noeud = noeud.filsGauche;
                    aGauche = true;
                }

                // Cas nouvelle donnee plus grande
                else if (comparation < 0) {
                    noeudPere = noeud;
                    noeud = noeud.filsDroit;
                    aGauche = false;
                }

                // Cas l'element existe deja
                else {
                    return;
                }
            }

            // Insertion
            noeud = new ABR<T>(d);
            noeud.pere = noeudPere;
            if (aGauche)
                noeud.pere.filsGauche = noeud;
            else
                noeud.pere.filsDroit = noeud;
        }
    }


    /*
        toString() : renvoie une chaˆıne de caractères qui contient l’ensemble des données de l’arbre dans l’ordre
                     lexicographique des clés et séparés par une virgule ;
    */
    public String toString() {
        /* *** Methode recursive *** */
        /*
            String res = donnee + ", ";

            if (filsGauche != null) {
                res = filsGauche + res;
            }

            if (filsDroit != null) {
                res = res + filsDroit;
            }

            return res;
        */

        /* *** Methode iterative *** */

        // On utilise l'aide d'un stack
        Stack<ABR> stack = new Stack<ABR>();

        // Le StringBuffer  va stocker le resultat
        StringBuffer buff = new StringBuffer();

        ABR noeud = this;

        // Iteration infixe
        while (! stack.isEmpty() || noeud != null) {
            if (noeud != null) {
                stack.push(noeud);
                noeud = noeud.filsGauche;
            }
            else {
                noeud = stack.pop();

                // On ajoute la donnee au buffer
                buff.append(noeud.donnee);
                buff.append(", ");

                noeud = noeud.filsDroit;
            }
        }

        // On retourne le resultat
        return buff.toString();
    }


    /*
        recherche(String cle) : renvoie la référence de l’objet de clé “cle�? s’il existe, ou “null�? sinon ;\
    */
    public ABR<T> recherche(String cle) {
        /* *** Methode recursive *** */
        /*
            int comparation = this.donnee.cle.compareTo(cle);

            if (comparation == 0) {
                return this;
            }
            else if (comparation > 0) {
                if (filsGauche != null)
                    return filsGauche.recherche(cle);
            }
            else {
                if (filsDroit != null)
                    return filsDroit.recherche(cle);
            }

            // Si on arrive la c'est parce que la cle n'existe pas
            return null;
        */


        /* *** Methode iterative *** */

        ABR noeud = this;

        while (noeud != null) {
            int comparation = noeud.donnee.cle.compareTo(cle);

            if (comparation == 0) {
                return noeud;
            }
            else if (comparation > 0) {
                noeud = noeud.filsGauche;
            }
            else {
                noeud = noeud.filsDroit;
            }
        }

        // Si on arrive ici c'est parce que la cle n'existe pas
        return null;
    }


    /*
        minimum() : renvoie la référence du noeud contenant l’objet de clé minimale ;
    */
    public ABR<T> minimum() {
        /* *** Methode recursive *** */
        /*
            if (filsGauche == null)
                return this;
            else
                return filsGauche.minimum();
        */


        /* *** Methode iterative *** */

        ABR noeud = this;
        while (noeud.filsGauche != null) {
            noeud = noeud.filsGauche;
        }

        return noeud;
    }

    /*
        maximum() : renvoie la référence du noeud contenant l’objet de clé maximale
    */
    public ABR<T> maximum() {
        /* *** Methode recursive *** */
        /*
            if (filsDroit == null)
                return this;
            else
                return filsDroit.maximum();
        */


        /* *** Methode iterative *** */

        ABR noeud = this;
        while (noeud.filsDroit != null) {
            noeud = noeud.filsDroit;
        }

        return noeud;
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
        supprimer(cle) : supprime l’objet de clé “cle�? tout en maintenant la structure d’un ABR.

        SUPPRESSION DE LA RACINE :
            La suppression de la racine represente un probleme particulier.
            - Si l'arbre a un seul element, on va mettre une valeur null dans la donnee.
            - Sinon, si par exemple le noeud a supprimer a un fils gauche non null,
                on va aller chercher le maximum a gauche, on remplacera la donnee du noeud a supprimer par celle
                du maximum, et puis un supprimera la reference au maximum
    */
    private void deplacer(ABR<T> actuel, ABR<T> remplacement) {
        // Le cas ou il faut remplacer la racine n'est pas pris en compte
        // Cette fonction ne sera jamais appele dans ce cas-la.

        if (actuel.pere.filsGauche == actuel)
            actuel.pere.filsGauche = remplacement;
        else
            actuel.pere.filsDroit = remplacement;

        if (remplacement != null)
            remplacement.pere = actuel.pere;
    }

    public void suppression(String cle) {
        ABR<T> aSupprimer = this.recherche(cle);

        // Dans cette implementation d'arbres binaires, la suppression de la racine est plus complexe
        // On ne peut pas changer la reference au noeud racine dans le programme
        // qui fait appel a la methode de suppression.
        // Du coup, il faut changer les references de fils gauche, fils droit et donnee.

        if (this == aSupprimer) {
            if (this.filsGauche == null) {
                this.donnee = this.filsDroit.donnee;
                this.filsGauche = this.filsDroit.filsGauche;
                this.filsDroit = this.filsDroit.filsDroit;
            }
            else {
                if (aSupprimer.filsDroit == null) {
                    this.donnee = this.filsGauche.donnee;
                    this.filsGauche = this.filsGauche.filsGauche;
                    this.filsDroit = this.filsGauche.filsDroit;
                }
                else {
                    ABR<T> max = this.filsGauche.maximum();

                    this.donnee = max.donnee;

                    if (max == this.filsGauche)
                        this.filsGauche = max.filsGauche;
                    else
                        this.deplacer(max, max.filsGauche);
                }
            }
        }


        // Cas de suppression d'un noeud different a la racine

        else {
            if (aSupprimer.filsGauche == null) {
                this.deplacer(aSupprimer, aSupprimer.filsDroit);
            }
            else {
                if (aSupprimer.filsDroit == null) {
                    this.deplacer(aSupprimer, aSupprimer.filsGauche);
                }
                else {
                    ABR<T> max = aSupprimer.filsGauche.maximum();

                    if (max.pere != aSupprimer) {
                        this.deplacer(max, max.filsGauche);
                        max.filsGauche = aSupprimer.filsGauche;
                        max.filsGauche.pere = max;
                    }

                    this.deplacer(aSupprimer, max);
                    max.filsDroit = aSupprimer.filsDroit;
                    max.filsDroit.pere = max;
                }
            }
        }
    }


    /* *********************************************************************** */
    /*
        3 - Implementation methodes avancees
        Exercice 3 : Arbres equivalents
            Definition Deux arbres binaires sont équivalents s’ils contiennent les mêmes éléments.

            Ecrire la méthode equivalent(abr) qui renvoie true si l’arbre est équivalent à abr et false sinon.
            Vous mentionnerez en commentaire de cette méthode les complexités dans le pire et dans le meilleur des
            cas de l’algorithme que vous proposez.

        Exercice 4 : Arbre contenu dans un autre
            Définition Un ABR A est contenu dans un ABR B si et seulement si tous les éléments de A sont dans B.

            Ecrire la méthode contenuDans(abr) renvoyant true si l’arbre est contenu dans abr.
            Vous mentionnerez en commentaire de cette méthode les complexités dans le pire et dans le meilleur des
            cas de l’algorithme que vous proposez.

        Exercice 5 : Fusion d’arbres
            Définition L’ABR C est dit fusion des arbres A et B si et seulement si U contient tous les noeuds de A et
            tous ceux de B.

            Ecrire une procédure fusionnant deux arbres, enévitant de recréer un troisième arbre par recopies des
            données de A et B.
    */
    /* *********************************************************************** */

    private class ResultatEquivalentContenu {
    	boolean egaux;
    	boolean stackVide_A;
    	boolean stackVide_B;
    	boolean noeudNull_A;
    	boolean noeudNull_B;
    }
    
    /**
     * Cette methode execute la boucle pour savoir si un arbre est contenu dans un autre
     * ou si deux arbres sont equivalents. La boucle est la meme pour les deux cas, la
     * seule difference est que, dans le cas de la methode equivalents, a la fin de la
     * boucle les deux arbres doivent avoir ete completement parcourus. Ce n'est pas le cas
     * de la methode contenu.
     * Du coup, on retourne un classe avec dse boolean contenant les valeurs finales de chaque
     * variable qui nous interesse.
     * 
     * Le resultat final contiendra :
     * 	- egaux > la derniere valeur de la variable egaux
     * 	- stackVide_A > true si le stack pour l'arbre A (this) est vide, false dans le cas contraire
     * 	- stackVide_B > true si le stack pour l'arbre B (autre) est vide, false dans le cas contraire
     * 	- noeudNull_A > true si le dernier noeud recupere de l'arbre A (this) est null, false dans le cas contraire
     * 	- noeudNull_B > true si le dernier noeud recupere de l'arbre B (autre) est null, false dans le cas contraire
     * 
     * Toutes ces valeurs seront utilisees par equivalent et contenu.
     * 
     * @param autre
     * @return
     */
    private ResultatEquivalentContenu auxiliaireEquivalentContenu(ABR<T> autre) {
    	ResultatEquivalentContenu resultat = new ResultatEquivalentContenu();
    	
    	Stack<ABR> stack_A = new Stack<ABR>();
        Stack<ABR> stack_B = new Stack<ABR>();

        ABR noeud_A = this;
        ABR noeud_B = autre;
        
        boolean egaux = true;
        
        while (egaux												// Condition d'egalite
        		&& (! stack_A.isEmpty() || noeud_A != null)			// Tant qu'il n'y a d'elements dans l'arbre 1 
				&& (! stack_B.isEmpty() || noeud_B != null)) {		// Tant qu'il n'y a d'elements dans l'arbre 2
        	
	        // Iteration jusqu'au plus petit
	        if (noeud_A != null || noeud_B != null) {
	            if (noeud_A != null) {
	                stack_A.push(noeud_A);
	                noeud_A = noeud_A.filsGauche;
	            }
	            
	            if (noeud_B != null) {
	                stack_B.push(noeud_B);
	                noeud_B = noeud_B.filsGauche;
	            }
	        }
	        
	        // La on est au plus petits des deux
	        else {
		        // On extrait le premier de chaque stack
		        noeud_A = stack_A.pop();
		        noeud_B = stack_B.pop();
		        
		        egaux = (noeud_A.donnee.compareTo(noeud_B.donnee) == 0);
		        // System.out.println("Comparaison : A = " + noeud_A.donnee + "; B = " + noeud_B.donnee);
		        
		        // Puis on passe a la droite du plus petit et on retourne a la boucle
		        noeud_A = noeud_A.filsDroit;
		        noeud_B = noeud_B.filsDroit;
	        }
        }
        
        resultat.egaux = egaux;
        resultat.stackVide_A = stack_A.isEmpty();
        resultat.stackVide_B = stack_B.isEmpty();
        resultat.noeudNull_A = (noeud_A == null);
        resultat.noeudNull_B = (noeud_B == null);
        
        return resultat;
    }
    
    public boolean equivalent(ABR<T> autre) {
        // Methode bete : utiliser toString
    	// --------------------------------
    	// Complexite O(3n) dans le pire des cas :
    	//		> On parcourt les deux arbres, ca fait 2*O(n), puis on parcourt les strings une fois pour les comparer.
    	//
    	// Complexite O(2n+1) dans le meilleur des cas :
    	//		> On parcourt une fois les deux arbres et puis on compare. Si le premier element est different,
    	//		  on retourne false (ca fait le +1 dans la complexite).
    	//
    	// return this.toString().equals(autre.toString());
    	
    	// Methode iterative, on retourne des que l'on trouve un element different
    	// -----------------------------------------------------------------------
    	// Complexite O(n) dans le pire des cas (n le nombre de noeuds de l'arbre le plus petit) :
    	//		> Les arbres sont equivalents ou ils different dans le dernier element de l'arbre le plus petit.
    	//
    	// Complexite O(1) dans le meilleur des cas, quand l'element le plus petit se trouve a la racine des
    	// deux arbres et quand cet element est different entre les deux arbres :
    	//		> Vu que l'on commence a comparer les arbres depuis les elements les plus petits dans chacun,
    	//		  si ceux-ci se trouvent a la racine, on les comparera tout de suite apres le premier tour de la boucle.
    	
    	
    	// Si les deux arbres sont vides, on retourne tout de suite true
    	// On dit que l'arbre recu en parametre est vide s'il est null ou vraiment vide.
    	if (this.isEmpty() && (autre == null || autre.isEmpty())) {
    		return true;
    	}
    	
    	// Si l'un est vide est l'autre non, on retourne tout de suite false
    	if (this.isEmpty() != (autre == null || autre.isEmpty())) {
    		return false;
    	}
    	
    	
    	// Sinon, on execute l'algorithme
    	ResultatEquivalentContenu resultat = auxiliaireEquivalentContenu(autre);

        /*
        System.out.println("Condition sortie :");
        System.out.println("\t Egaux = " + egaux);
        System.out.println("\t Arbre A stack vide = " + resultat.stackVide_A);
        System.out.println("\t Arbre B stack vide = " + resultat.stackVide_B);
        System.out.println("\t Arbre A noeud null = " + (resultat.noeudNull_A));
        System.out.println("\t Arbre B noeud null = " + (resultat.noeudNull_B));
        */
        
        // On retourne false si un arbre est plus grand que l'autre (ca on le voit par rapport
        //		a l'etat du stack et du noeud en cours).
        // Sinon, on retourne la derniere valeur stockee dans "egaux".
        return (resultat.stackVide_A && resultat.stackVide_B
        		&& resultat.noeudNull_A && resultat.noeudNull_B
        		&& resultat.egaux);
    }
    
    
    public boolean contenuDans(ABR<T> autre) {
    	// Methode iterative, on retourne des que l'on trouve un element different
    	// -----------------------------------------------------------------------
    	// Complexite O(n) dans le pire des cas (n le nombre de noeuds de l'arbre le plus petit) :
    	//		> Les arbres sont equivalents, ils different dans le dernier element de l'arbre le plus petit,
    	//		  l'arbre "this" est contenu dans "autre", ou "this" a plus d'elements que "autre".
    	//
    	// Complexite O(1) dans le meilleur des cas, quand l'element le plus petit se trouve a la racine des
    	// deux arbres et quand cet element est different pour chaque arbre :
    	//		> Vu que l'on commence a comparer les arbres depuis les elements les plus petits dans chacun,
    	//		  si ceux-ci se trouvent a la racine, on les comparera tout de suite apres le premier tour de la boucle.
    	
    	
    	// Si l'arbre "this" est vide, il est donc contenu dans tout arbre.
    	// On retourne tout de suite true.
    	if (this.isEmpty()) {
    		return true;
    	}
    	
    	
    	// Sinon, on execute l'algorithme
    	ResultatEquivalentContenu resultat = auxiliaireEquivalentContenu(autre);

        /*
        System.out.println("Condition sortie :");
        System.out.println("\t Egaux = " + egaux);
        System.out.println("\t Arbre A stack vide = " + resultat.stackVide_A);
        System.out.println("\t Arbre B stack vide = " + resultat.stackVide_B);
        System.out.println("\t Arbre A noeud null = " + (resultat.noeudNull_A));
        System.out.println("\t Arbre B noeud null = " + (resultat.noeudNull_B));
        */
        
        // On retourne false si l'arbre "this" est plus grand que "autre" (ca on le voit par rapport
        //		a l'etat du stack et du noeud en cours).
        // Sinon, on retourne la derniere valeur stockee dans "egaux".
        return (resultat.stackVide_A && resultat.noeudNull_A
        		&& resultat.egaux);
    }




    /* *********************************************************************** */
    /*     TEST PERSO                                                          */
    /* *********************************************************************** */

    private static void printIteratif(ABR a) {
        ABR noeud = a;
        Stack<ABR> stack = new Stack<ABR>();

        while (! stack.isEmpty() || noeud != null) {
            if (noeud != null) {
                stack.push(noeud);
                noeud = noeud.filsGauche;
            }
            else {
                noeud = stack.pop();
                System.out.println(noeud.donnee);
                noeud = noeud.filsDroit;
            }
        }
    }

    private static void print(ABR a) {
        System.out.println("-- Arbre --");
        System.out.println("Racine > " + a.donnee);

        if (a.filsGauche != null)
            System.out.println("Racine Gauche > " + a.filsGauche.donnee);

        System.out.println("Gauche > " + a.filsGauche);

        if (a.filsDroit != null)
            System.out.println("Racine Droite > " + a.filsDroit.donnee);

        System.out.println("Droite > " + a.filsDroit);
        System.out.println("-------------------------------------------\n");
    }

    public static void main(String args[]) {

        ABR a2 = new ABR<String>(new DonneeGenerique<String>("test"));
        print(a2);

        a2.insertionFeuille(new DonneeGenerique<String>("CERI"));
        print(a2);

		a2.insertionFeuille(new DonneeGenerique<String>("alternance"));
        print(a2);

		a2.insertionFeuille(new DonneeGenerique<String>("M1"));
        print(a2);

		a2.insertionFeuille(new DonneeGenerique<String>("CERI"));
        print(a2);

		a2.insertionFeuille(new DonneeGenerique<String>("algorithmique"));
        print(a2);

		a2.insertionFeuille(new DonneeGenerique<String>("avancée"));
        print(a2);

        /*
        ABR a2 = new ABR<String>(new DonneeGenerique<String>("D"));
        print(a2);

        a2.insertionFeuille(new DonneeGenerique<String>("B"));
        print(a2);

		a2.insertionFeuille(new DonneeGenerique<String>("A"));
        print(a2);

		a2.insertionFeuille(new DonneeGenerique<String>("C"));
        print(a2);

		a2.insertionFeuille(new DonneeGenerique<String>("E"));
        print(a2);

		a2.insertionFeuille(new DonneeGenerique<String>("G"));
        print(a2);

		a2.insertionFeuille(new DonneeGenerique<String>("F"));
        print(a2);
        */

        System.out.println("ARBRE FINAL > " + a2);

        System.out.println("TEST ITERATIF");
        printIteratif(a2);

    }

}
