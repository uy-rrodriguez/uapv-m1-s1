ifeq ($(OS),Windows_NT)
	# Config Windows (PowerShell)
	PATH_SEPARATOR=;
else
	# Config. Linux (faculté)
	PATH_SEPARATOR=:
endif

# Ice
ICE_LIBS=lib/ice/*
#lib/ice/Ice.jar

# Bibliotheques "binding" de VLC pour Java
VLC_LIBS=lib/vlcj-3.10.1/*$(PATH_SEPARATOR)lib/slf4j-1.7.21/*

# Dossier des bilbiotheques libvlc et libvlccore
# Dans le rendu il y a les dll pour Windows (j'ai du developper sur Windows a cause de problemes avec Linux)
# Le serveur va acceder a ce dossier s'il ne trouve pas automatiquement VLC dans la machine
VLC_MAIN_LIB=lib/libvlc

# Ensemble de bibliotheques
LIBS=$(ICE_LIBS)$(PATH_SEPARATOR)$(VLC_LIBS)$(PATH_SEPARATOR)$(VLC_MAIN_LIB)


all: build run

build:
	slice2java --output-dir generated ../interface/InterfaceMP3.ice
	javac -d classes -classpath "$(LIBS)" ServeurMP3.java ServeurMP3I.java Player.java generated/ArchDistrib/*.java

run:
	java -classpath "$(LIBS)$(PATH_SEPARATOR)classes" ServeurMP3

clean:
	cd classes; rm -r *.class; cd ..
	cd generated/ArchDistrib; rm -r *.java; cd ../..

dist:
	cp "makefile" "../../dist/src/serveur/"
	cp "Player.java" "../../dist/src/serveur/"
	cp "ServeurMP3.java" "../../dist/src/serveur/"
	cp "ServeurMP3I.java" "../../dist/src/serveur/"
