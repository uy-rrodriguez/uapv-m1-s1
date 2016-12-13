public class ServeurMP3 {
    public static void main(String[] args) {
        int status = 0;
        Ice.Communicator ic = null;
        try {
            // Configuration de taille maximale pour les requetes
            //     20480 KB permet d'envoyer une grande chanson de 20 MB
            Ice.StringSeqHolder argsH = new Ice.StringSeqHolder(args);
            Ice.Properties properties = Ice.Util.createProperties(argsH);
            properties.setProperty("Ice.MessageSizeMax", "20480");
            Ice.InitializationData iniData = new Ice.InitializationData();
            iniData.properties = properties;

            // Initialisation du serveur
            ic = Ice.Util.initialize(iniData);
            Ice.ObjectAdapter adapter =
                ic.createObjectAdapterWithEndpoints("InterfaceMP3Adapter", "default -p 10000");
            Ice.Object object = new ServeurMP3I();
            adapter.add(object, ic.stringToIdentity("InterfaceMP3"));
            adapter.activate();

            System.out.println("");
            System.out.println("Serveur demarre");
            System.out.println("===============");
            System.out.println("");

            ic.waitForShutdown();
        }
        catch (Ice.LocalException e) {
            e.printStackTrace();
            status = 1;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            status = 1;
        }

        if (ic != null) {
            // Clean up
            //
            try {
                ic.destroy();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                status = 1;
            }
        }

        System.exit(status);
    }
}
