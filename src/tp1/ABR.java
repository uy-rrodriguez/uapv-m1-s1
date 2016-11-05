package tp1;

import java.util.Stack;

/**
 *   La classe ABR  represente un noeud d'un Arbre Binaire de Recherche, contenant une donnee avec une valeur et un cle.
 *   Les methodes implementees sont iteratives.
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
    public boolean isEmpty() {
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
    	//		> Le pire des cas c'est quand les arbres sont equivalents ou ils different dans le dernier
    	//		  element de l'arbre le plus petit.
    	//
    	// Complexite O(1) dans le meilleur des cas :
    	//		> On est dans le meilleur des cas quand les elements les plus petits se trouvent a la racine des
    	// 		  deux arbres quand ces elements sont differentes entre eux.
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
        
        
        // On retourne false si un arbre est plus grand que l'autre (ca on le voit par rapport
        //		a l'etat du stack et du noeud en cours).
        // Sinon, on retourne la derniere valeur stockee dans "egaux".
        return (stack_A.isEmpty() && stack_B.isEmpty()
        		&& noeud_A == null && noeud_B == null
        		&& egaux);
    }
    
    
    /**
     * Arbre contenu dans un autre. Un ABR A est contenu dans un ABR B si et seulement si tous les
     * éléments de A sont dans B. Cette fonction renvoie true si l’arbre est contenu dans abr.
     * 
     * @param autre
     * @return
     */
    public boolean contenuDans(ABR<T> autre) {
    	// Methode iterative
    	// -----------------------------------------------------------------------
    	// Complexite O(n) dans le pire des cas (n le nombre de noeuds de l'arbre "autre") :
    	//		> Si "this" a un element qui n'est pas dans "autre", mais qui est superieur au maximum dans "autre",
    	//		  on est obligé de parcourir tout B pour se rendre compte que "this" n'est pas contenu dans "autre".
    	//		> La complexité est la même si "this" est equivalent à "autre" ou s'il est contenu et leur maximums
    	//		  sont egaux.  
    	//
    	// Complexite O(1) dans le meilleur des cas :
    	//		> Vu que l'on commence a comparer les arbres depuis les elements les plus petits dans chacun,
    	//		  si le plus petit dans "this" est inférieur au plus petit dans "autre", on sait que ca sert
    	//		  à rien de continuer à parcourir "autre". L'arbre "this" n'est pas contenu dans "autre".
    	
    	
    	// Si l'arbre "this" est vide, il est donc contenu dans tout arbre.
    	// On retourne tout de suite true.
    	if (this.isEmpty()) {
    		return true;
    	}
    	
    	// Sinon, on execute l'algorithme
    	
    	Stack<ABR> stack_A = new Stack<ABR>();
        Stack<ABR> stack_B = new Stack<ABR>();

        ABR noeud_A = this;
        ABR noeud_B = autre;
        
        while ((! stack_A.isEmpty() || noeud_A != null)				// Tant qu'il y a d'elements dans l'arbre A 
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
		        
		        // On compare
		        int comparaison = noeud_A.donnee.compareTo(noeud_B.donnee);
		        
		        // Si le noeud dans A est inférieur à celui dans B, cela veut dire
		        // que l'on ne va jamais trouver cette valeur dans B.
		        // Du coup on retourne tout de suite false.
		        if (comparaison < 0) {
		        	return false;
		        }
		        
		        // Sinon, si le noeud dans A est superieur a B, il faut continuer le parcourt dans B tant
		        // que l'on ne trouve pas un element egal ou superieur au noeud de A.
		        else if (comparaison > 0) {
		        	stack_A.push(noeud_A);
		        	noeud_A = null;
		        }
		        
		        // Sinon on avance dans les deux arbres
		        else {
		        	noeud_A = noeud_A.filsDroit;
		        }
		        
		        noeud_B = noeud_B.filsDroit;
	        }
        }
    	
        // On retourne true si l'arbre A a pas ete parcouru completement.
        // Sinon, soit il est plus grand que B, soit il y a au moins un element dans
        // A qui n'est pas dans B. On retourne false dans ce cas.
        return (stack_A.isEmpty() && noeud_A == null);
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
