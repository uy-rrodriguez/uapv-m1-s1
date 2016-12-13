import sys, traceback, Ice
import ArchDistrib
import CLI
#import GUI


SERVER_IP = "localhost"

def main():
    status = 0
    ic = None
    try:
        # Configuration pour augmenter la taille des requetes,
        # afin de pouvoir envoyer une chanson entiere
        #     20480 KB permet d'envoyer une grande chanson de 20 MB
        props = Ice.createProperties(sys.argv)
        props.setProperty("Ice.MessageSizeMax", "20480")
        iniData = Ice.InitializationData()
        iniData.properties = props

        # Initialisation du serveur et Ice
        ic = Ice.initialize(iniData)
        base = ic.stringToProxy("InterfaceMP3:default -h " + SERVER_IP + " -p 10000")
        serveur = ArchDistrib.InterfaceMP3Prx.checkedCast(base)
        if not serveur:
            raise RuntimeError("Invalid proxy")

        # Client
        client = CLI.App(serveur)
        #client = GUI.App(serveur)
        #client = GUI.App(None)

        # Boucle principale
        client.mainloop()

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
