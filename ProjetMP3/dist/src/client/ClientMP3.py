import sys, traceback, Ice
import ArchDistrib
import CLI
#import GUI


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
        client = CLI.App(serveur)
        #client = GUI.App(serveur)

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
