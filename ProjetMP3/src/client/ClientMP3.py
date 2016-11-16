import sys, traceback, Ice
import ArchDistrib

class ClientMP3:
  def __init__(self, serveur):
    self.serveur = serveur

  def addSong(self, name, artist, path):
    print("addSong name=" + name + "; artist=" + artist + "; path=" + path)

  def removeSong(self, id):
    print("removeSong id=" + str(id))

  def listSongs(self):
    print("listSongs")

  def searchSongs(self, nameRegex, artistRegex):
    print("searchSongs nameRegex=" + nameRegex + "; artistRegex=" + artistRegex)

  def playSong(self, id):
    print("playSong id=" + str(id))

  def addTagSong(self, id, name):
    print("addTagSong id=" + str(id) + "; name=" + name)

  def removeTagSong(self, id, name):
    print("removeTagSong id=" + str(id) + "; name=" + name)

  def shutdown(self):
    print("shutdown")


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
    print("exit - Fermer le programme")
    print("")

def main_loop(client):
    # Affichage menu d'options
    menu()

    # Boucle pour lire commandes utilisateur
    cmd = raw_input(str("Choisir une option : "))
    while cmd != "exit":
        args = cmd.split()

        if len(args) > 0:
            if args[0] == "1" and len(args) >= 4:
                client.addSong(args[1], args[2], args[3])

            elif args[0] == "2" and len(args) >= 2:
                client.removeSong(int(args[1]))

            elif args[0] == "3":
                client.listSongs()

            elif args[0] == "4" and len(args) >= 3:
                client.searchSongs(args[1], args[2])

            elif args[0] == "5" and len(args) >= 2:
                client.playSong(int(args[1]))

            elif args[0] == "6" and len(args) >= 3:
                client.addTagSong(args[1], args[2])

            elif args[0] == "7" and len(args) >= 3:
                client.removeTagSong(args[1], args[2])

            else:
                print("Commande non reconnue")
        else:
            print("Commande non reconnue")


        raw_input()
        print("---------------------------------")
        menu()
        cmd = input(str("Choisir une option : "))

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
