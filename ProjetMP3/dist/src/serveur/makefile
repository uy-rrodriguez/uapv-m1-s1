# Config. Linux
#ICE_LIBS=$ICE_HOME/lib/*

# Config Windows (PowerShell)
ICE_LIBS=$(ICE_HOME)/lib/*

# Bibliotheques "binding" de VLC pour Java
VLC_LIBS=lib/vlcj-3.10.1/vlcj-3.10.1.jar;lib/vlcj-3.10.1/jna-4.1.0.jar;lib/vlcj-3.10.1/jna-platform-4.1.0.jar;lib/slf4j-1.7.21/slf4j-api-1.7.21.jar;lib/slf4j-1.7.21/slf4j-simple-1.7.21.jar

# Dossier des bilbiotheques libvlc et libvlccore
# Dans le rendu il y a les dll pour Windows (j'ai du developper sur Windows a cause de problemes avec Linux)
# Le serveur va acceder a ce dossier s'il ne trouve pas automatiquement VLC dans la machine
VLC_MAIN_LIB=lib/libvlc

# Ensemble de bibliotheques
LIBS=$(ICE_LIBS);$(VLC_LIBS);$(VLC_MAIN_LIB)


build:
	slice2java --output-dir generated ../interface/InterfaceMP3.ice
	javac -d classes -classpath "$(LIBS)" ServeurMP3.java ServeurMP3I.java Player.java generated/ArchDistrib/*.java

run:
	java -classpath "$(LIBS);classes" ServeurMP3
