Université d'Avignon et des Pays de Vaucluse
Architectures Distribuées
Master 1 - 2016-17

Auteur : Ricardo Rodríguez

----------------------------------
SERVEUR ET LECTEUR DE FICHIERS MP3
----------------------------------

Introduction
------------
Pour ce projet on devait réaliser un serveur Ice qui stocke des fichiers MP3 et un client qui communique avec lui
pour les jouer.

Windows
-------
Le système a été dévéloppé sous Windows 10 64 bits.
Je l'ai testé sur les machines Linux de la faculté et il fonctionne sans problèmes.

VLC
---
Pour jouer les chansons j'utilise les bilbiothèques de VLC. La manière la plus simple de les utiliser est d'installer
VLC dans l'ordinateur. Néanmoins, je fournis les fichiers libvlc.dll et libvlccore.dll pour le système Windows. Ils
se trouvent dans le répertoire serveur/lib/libvlc.

Je n'ai pas testé le fichier vlc.py (binding en Python pour accéder à VLC) sans avoir VLC installé sur mon PC. Je ne
sais donc pas si le client peut être utilisé même quand VLC n'est pas installé.

J'ai décidé de réaliser le streaming à travers libVLC parce que cette bibliothèque offre une manière simple d'exécuter
une grande quantité de fichiers audio et vidéo.

Ice
---
Le middleware Ice est utilisé pour contrôler le serveur à distance. Pour compiler et exécuter les sources il faut avoir
installés les executables : slice2java et slice2py.
En plus, le serveur a besoin des .jar pour Ice, le fichier Ice.jar donné en cours se trouve dans les sources du serveur
et il est ajouté au CLASSPATH au moment de l'exécution. J'envoie aussi le fichier ice-3.6.3.jar, une version différente
de celle donnée en cours, qui est nécessaire dans mon environnement de développement.

Python
------
Le client a été développé avec Python 2.7.12 32 bits.

Java
----
Le serveur a été développé avec Java 1.8.0_111 32 bits.
Il faut que Java soit en 32 bits sinon on ne peut pas utiliser les bibliothèques de VLC pour Java.

Améliorations
-------------
- Il faudrait améliorer le contrôle des chansons (avancer, pause, etc.)
- Ce serait bien d'avoir une interface GUI du côté client et de séparer le contrôle du serveur du player MP3. Pour l'instant, le client
propose les options d'ajout et suppression de fichiers, une possible amélioration serait de déplacer ces options dans une interface
côté serveur.
- La taille des chansons à envoyer au serveur est limité à 20 MB.

Client
------
Le client a été implémenté en Python. Il s'agit d'une interface en console qui permet d'accéder à toutes les fonctions
du serveur. Pour simplicité, je n'ai pas développé deux clients, un pour gérer les fichiers du serveur et un autre
pour lister et jouer la musique.

    Compilation
    -----------
    Pour exécuter le client il faut ouvrir une console et aller dans le répertoire avec les sources :
        <path vers le contenu du ZIP>/src/client/

    Pour compiler il faut exécuter la commande qui va créer le code Python à partir de l'interface Ice :
        make build

    Ensuite, pour exécuter le client faire :
        make run
    
    Pour le faire en une seule ligne :
        make

    Utilisation de l'interface client
    ---------------------------------
    L'interface est très simple. Un menu affiche les options, on écrit le numéro de la fonctionnalité à utiliser
    (de 1 à 6, 0 pour finir l'exécution) et on fait [Entrer]
    Ensuite, si la fonction nécéssite des arguments, l'interface va demander les valeurs une par une. Les arguments
    exigés par chaque fonction sont notés à droite.
    Ainsi, on à accès aux fonctionnalités :
        1 - Ajouter une chanson.
                Il faut donner l titre, artiste et le path vers le fichier mp3.
        2 - Supprimer une chanson.
                Il faudra donner l'id.
        3 - Lister les chansons.
                On peut ainsi connaître l'id des chansons.
        4 - Chercher des chansons par expressions regulières.
                On doit donner au serveur une expression regulière pour le titre et une autre pour l'artiste.
                Si on veut chercher tous les artistes et toutes les titres on peut utiliser l'expression ".*"
                dans les deux arguments ou insérer des valeurs vides pour ces arguments.
        5 - Jouer une chansons.
                Il faut donner l'id de la chanson.
        6 - Arrêter la chanson en cours.
        0 - Fermer le client.
    
    Le path des fichiers peut être relatif à la racine du client. Le client va lire le fichier, le transformer en tableau de bytes et
    l'envoyer vers le serveur. Celui-ci va recevoir les bytes et les stocker dans un répertoire local.
    Pour simplifier les tests, dans <path vers le contenu du ZIP>/src/client/mp3 il y a deux fichiers mp3 appelés "test1.mp3" et
    "test2.mp3", qui peuvent être utilisés dans l'option 1 du menu client. On peut donc donner comme path la valeur "mp3/test1.mp3".
    
    Pour simplifier le code, le client et le serveur ont été configurés pour accepter l'envoi de requêtes avec une taille maximale
    de 20MB (c'est 1MB par défaut). En faisant ainsi, on peut envoyer des fichiers MP3 avec une taille maximale proche de 20 MB
    d'un seul coup, sans gérer l'envoi par petits paquets.
    
    Reproduction de chansons
    ------------------------
    Le client utilise les bibliothèques VLC pour jouer les fichiers mp3. Quand on demande la reproduction d'un fichier,
    le serveur crée un streaming avec le protocole RTP que le client va consommer.

Serveur
-------
Le serveur a été implémenté en Java. Il expose toutes les fonctions définies dans l'interface Ice.
Les chansons sont stockés dans des structures List et Map pour permettre un accès facile par ID et la recherche
par titre et artiste.

    Compilation
    -----------
    Pour exécuter le client il faut ouvrir une console et aller dans le répertoire avec les sources :
        <path vers le contenu du ZIP>/src/serveur/

    Pour compiler il faut exécuter la commande qui va créer le code java à partir de l'interface Ice et les .class :
        make build

    Ensuite, pour exécuter le client faire :
        make run
    
    Pour le faire en une seule ligne :
        make

    Détails d'implémentation
    ---------------------------------
    Le serveur recoit les requêtes pour chaque fonction et affiche en console un log des données recues.
    Les objets contenant les données des chansons sont stockés dans des listes et des hash-map qui simplifient les recherches.
    
    Reproduction de chansons
    ------------------------
    Quand on demande la reproduction d'un fichier, le serveur crée un streaming avec le protocole RTP grâce aux bibliothèques
    de VLC. Dans les sources on peut trouver les bibliothèques nécessaires (serveur/lib/). Quand le serveur démarre, il va chercher
    les bibliothèques libvlc et libvlccore dans le répertoire d'installation de VLC. Si VLC n'est pas installé, il va les chercher
    dans serveur/lib/libvlc/.
