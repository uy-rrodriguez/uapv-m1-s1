import sys, traceback, Ice
import ArchDistrib

class ClientMP3:
    def __init__(self, serveur):
        self.serveur = serveur

    def askForParams(self, params):
        values = {}
        for p in params:
            values[p] = raw_input("Inserez la valeur pour " + p + " : ")
        return values

    def printSongList(self, songs):
        print "\n", "-"*60
        print "Liste de chansons :"
        print "\t\t".join(["ID", "Nom", "Artiste", "Tags"])
        for s in songs:
            print "\t\t".join([str(s.id), s.name, s.artist, "[" + ", ".join(s.tags) + "]"])

        print "\n"


    def addSong(self):
        #print("addSong name=" + name + "; artist=" + artist + "; path=" + path)
        values = self.askForParams(["nom", "artiste", "path"])
        self.serveur.addSong(values["nom"], values["artiste"], values["path"])

    def removeSong(self):
        #print("removeSong id=" + str(id))
        values = self.askForParams(["id"])
        self.serveur.removeSong(int(values["id"]))

    def listSongs(self):
        #print("listSongs")
        songs = self.serveur.listSongs()
        self.printSongList(songs)

    def searchSongs(self):
        #print("searchSongs nameRegex=" + nameRegex + "; artistRegex=" + artistRegex)
        values = self.askForParams(["nom", "artiste"])
        songs = self.serveur.searchSongs(values["nom"], values["artiste"])
        self.printSongList(songs)

    def playSong(self):
        #print("playSong id=" + str(id))
        values = self.askForParams(["id"])
        self.serveur.playSong(int(values["id"]))

    def addTagSong(self):
        #print("addTagSong id=" + str(id) + "; name=" + name)
        values = self.askForParams(["id", "name"])
        self.serveur.addTagSong(int(values["id"]), values["name"])

    def removeTagSong(self):
        #print("removeTagSong id=" + str(id) + "; name=" + name)
        values = self.askForParams(["id", "name"])
        self.serveur.removeTagSong(int(values["id"]), values["name"])

    def shutdown(self):
        #print("shutdown")
        self.serveur.shutdown()


def menu():
    print("Options :")
    print("1 - addSong \t\t : name artist path")
    print("2 - removeSong \t\t : id")
    print("3 - listSongs")
    print("4 - searchSongs \t : nameRegex artistRegex")
    print("5 - playSong \t\t : id")
    print("6 - addTagSong \t\t : id name")
    print("7 - removeTagSong \t : id name")
    print("8 - shutdown")
    print("0 - Fermer le programme")
    print("")

def main_loop(client):
    # Affichage menu d'options
    menu()

    # Boucle pour lire commandes utilisateur
    cmd = raw_input("Choisir une option : ")
    while cmd != "0":
        try:
            if cmd == "1":
                client.addSong()

            elif cmd == "2":
                client.removeSong()

            elif cmd == "3":
                client.listSongs()

            elif cmd == "4":
                client.searchSongs()

            elif cmd == "5":
                client.playSong()

            elif cmd == "6":
                client.addTagSong()

            elif cmd == "7":
                client.removeTagSong()

            elif cmd == "8":
                client.shutdown()

            else:
                print("Commande non reconnue")
            # Fin if-elif-else

        except:
            traceback.print_exc()
        # Fin try-catch

        #raw_input()
        print("="*80 + "\n")
        menu()
        cmd = raw_input("Choisir une option : ")

    # Fin while


def main():
    status = 0
    ic = None
    try:
        ic = Ice.initialize(sys.argv)
        base = ic.stringToProxy("InterfaceMP3:default -p 10000")
        serveur = ArchDistrib.InterfaceMP3Prx.checkedCast(base)
        if not serveur:
            raise RuntimeError("Invalid proxy")

        # Client
        client = ClientMP3(serveur)

        # Boucle principale
        main_loop(client)

    except:
        traceback.print_exc()
        status = 1

    if ic:
        # Clean up
        try:
            ic.destroy()
        except:
            traceback.print_exc()
            status = 1

    sys.exit(status)


if __name__ == "__main__":
  main()
