import traceback

class App:
  def __init__(self, srvProxy):
    self.srv = srvProxy


  def mainloop(self):
    # Affichage menu d'options
    self.menu()

    # Boucle pour lire commandes utilisateur
    cmd = raw_input("Choisir une option : ")
    while cmd != "0":
      try:
        if cmd == "1":
          self.addSong()

        elif cmd == "2":
          self.removeSong()

        elif cmd == "3":
          self.listSongs()

        elif cmd == "4":
          self.searchSongs()

        elif cmd == "5":
          self.playSong()

        elif cmd == "6":
          self.addTagSong()

        elif cmd == "7":
          self.removeTagSong()

        elif cmd == "8":
          self.shutdown()

        else:
          print("Commande non reconnue")
        # Fin if-elif-else

      except:
        traceback.print_exc()
      # Fin try-catch

      #raw_input()
      print("="*80 + "\n")
      self.menu()
      cmd = raw_input("Choisir une option : ")

    # Fin while


  def menu(self):
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
    self.srv.addSong(values["nom"], values["artiste"], values["path"])


  def removeSong(self):
    #print("removeSong id=" + str(id))
    values = self.askForParams(["id"])
    self.srv.removeSong(int(values["id"]))


  def listSongs(self):
    #print("listSongs")
    songs = self.srv.listSongs()
    self.printSongList(songs)


  def searchSongs(self):
    #print("searchSongs nameRegex=" + nameRegex + "; artistRegex=" + artistRegex)
    values = self.askForParams(["nom", "artiste"])
    songs = self.srv.searchSongs(values["nom"], values["artiste"])
    self.printSongList(songs)


  def playSong(self):
    #print("playSong id=" + str(id))
    values = self.askForParams(["id"])
    self.srv.playSong(int(values["id"]))


  def addTagSong(self):
    #print("addTagSong id=" + str(id) + "; name=" + name)
    values = self.askForParams(["id", "name"])
    self.srv.addTagSong(int(values["id"]), values["name"])


  def removeTagSong(self):
    #print("removeTagSong id=" + str(id) + "; name=" + name)
    values = self.askForParams(["id", "name"])
    self.srv.removeTagSong(int(values["id"]), values["name"])


  def shutdown(self):
    #print("shutdown")
    self.srv.shutdown()

