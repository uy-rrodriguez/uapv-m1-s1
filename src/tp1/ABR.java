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

    /**
     * Methode auxiliare pour connaitre si un arbre est vide.
     * Un arbre sera vide si la racine etait le seul noeud et elle a ete supprime.
     * Dans ce cas, la donnee sera null.
     * 
     * @return
     */
    private boolean isEmpty() {
        return (this.donnee == null);
    }

    /**
     * Insère un nouveau noeud contenant une donnée d en feuille de l’arbre ;
     * 
     * @param d
     */
    public void insertionFeuille(DonneeGenerique d) {
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


    /**
     * Renvoie une chaîne de caractères qui contient l’ensemble des données de l’arbre dans l’ordre
     * lexicographique des clés et séparés par une virgule.
     */
    public String toString() {
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

    /**
     * Renvoie la référence de l’objet de clé "cle" s’il existe, ou "null" sinon.
     * 
     * @param cle
     * @return
     */
    public ABR<T> recherche(String cle) {
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

    /**
     * Renvoie la référence du noeud contenant l’objet de clé minimale.
     * 
     * @return
     */
    public ABR<T> minimum() {
        /* *** Methode iterative *** */

        ABR noeud = this;
        while (noeud.filsGauche != null) {
            noeud = noeud.filsGauche;
        }

        return noeud;
    }

    /**
     * Renvoie la référence du noeud contenant l’objet de clé maximale.
     * 
     * @return
     */
    public ABR<T> maximum() {
        /* *** Methode iterative *** */

        ABR noeud = this;
        while (noeud.filsDroit != null) {
            noeud = noeud.filsDroit;
        }

        return noeud;
    }

    /**
     * Renvoie la hauteur de l’arbre.
     * 
     * @return
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
    /**
     * Méthode auxiliaire pour deplacer un noued.
     * 
     * @param actuel
     * @param remplacement
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

    /**
     * Supprime l’objet de clé "cle" tout en maintenant la structure d’un ABR.
     * 
     * <pre>
     * SUPPRESSION DE LA RACINE :
     *       La suppression de la racine represente un probleme particulier.
     *       - Si l'arbre a un seul element, on va mettre une valeur null dans la donnee.
     *       - Sinon, si par exemple le noeud a supprimer a un fils gauche non null,
     *           on va aller chercher le maximum a gauche, on remplacera la donnee du noeud a supprimer par celle
     *           du maximum, et puis un supprimera la reference au maximum
     * </pre>
     * 
     * @param cle
     */
    public void suppression(String cle) {
        ABR<T> aSupprimer = this.recherche(cle);

        // Dans cette implementation d'arbres binaires, la suppression de la racine est plus complexe
        // On ne peut pas changer la reference au noeud racine dans le programme
        // qui fait appel a la methode de suppression.
        // Du coup, il faut changer les references de fils gauche, fils droit et donnee.

        // Suppression de la racine
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


        // Suppression d'un noeud différent à la racine
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


    /* ************************************************************************ */
    /* 		Implémentation méthodes avancées									*/
    /* ************************************************************************ */

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
     * <pre>
     * Le resultat final contiendra :
     * 	- egaux > la derniere valeur de la variable egaux
     * 	- stackVide_A > true si le stack pour l'arbre A (this) est vide, false dans le cas contraire
     * 	- stackVide_B > true si le stack pour l'arbre B (autre) est vide, false dans le cas contraire
     * 	- noeudNull_A > true si le dernier noeud recupere de l'arbre A (this) est null, false dans le cas contraire
     * 	- noeudNull_B > true si le dernier noeud recupere de l'arbre B (autre) est null, false dans le cas contraire
     * </pre>
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
        		&& (! stack_A.isEmpty() || noeud_A != null)			// Tant qu'il y a d'elements dans l'arbre A 
				&& (! stack_B.isEmpty() || noeud_B != null)) {		// Tant qu'il y a d'elements dans l'arbre B
        	
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
    
    /**
     * Arbres equivalents. Deux arbres binaires sont équivalents s’ils contiennent les mêmes éléments.
	 *
     * Cette méthode renvoie true si l’arbre est équivalent à abr et false sinon.
     *       
     * @param autre
     * @return
     */
    public boolean equivalent(ABR<T> autre) {    	
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
        
        // On retourne false si un arbre est plus grand que l'autre (ca on le voit par rapport
        //		a l'etat du stack et du noeud en cours).
        // Sinon, on retourne la derniere valeur stockee dans "egaux".
        return (resultat.stackVide_A && resultat.stackVide_B
        		&& resultat.noeudNull_A && resultat.noeudNull_B
        		&& resultat.egaux);
    }
    
    
    /**
     * Arbre contenu dans un autre. Un ABR A est contenu dans un ABR B si et seulement si tous les
     * éléments de A sont dans B. Cette fonction renvoie true si l’arbre est contenu dans abr.
     * 
     * @param autre
     * @return
     */
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
        
        // On retourne false si l'arbre "this" est plus grand que "autre" (ca on le voit par rapport
        //		a l'etat du stack et du noeud en cours).
        // Sinon, on retourne la derniere valeur stockee dans "egaux".
        return (resultat.stackVide_A && resultat.noeudNull_A
        		&& resultat.egaux);
    }
    
    
    /**
     * Fusion d’arbres. L’ABR C est dit fusion des arbres A et B si et seulement si U contient tous
     * les noeuds de A et tous ceux de B. Cette procédure fusionne deux arbres, en évitant de recréer
     * un troisième arbre par recopies des données de A et B.
     * 
     * @param autre
     */
    public void fusion(ABR<T> autre) {
    	Stack<ABR> stack_B = new Stack<ABR>();
        ABR noeud_B = autre;
        
        // Iteration Racine-Gauche-Droite
        while (! stack_B.isEmpty() || noeud_B != null) {	// Tant qu'il y a d'elements dans l'arbre B (autre)
        	if (noeud_B != null) {
        		this.insertionFeuille(noeud_B.donnee);
        		
	            stack_B.push(noeud_B.filsGauche);
	            stack_B.push(noeud_B.filsDroit);
	        }
        	
        	noeud_B = stack_B.pop();
        }
    }

}
