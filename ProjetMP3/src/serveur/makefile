#ICE_LIBS=$ICE_HOME/lib/*
ICE_LIBS=G:/Dev/Ice-3.6.3/lib/*

VLC_LIBS=lib/vlcj-3.10.1/vlcj-3.10.1.jar;lib/vlcj-3.10.1/jna-4.1.0.jar;lib/vlcj-3.10.1/jna-platform-4.1.0.jar;lib/slf4j-1.7.21/slf4j-api-1.7.21.jar;lib/slf4j-1.7.21/slf4j-simple-1.7.21.jar
VLC_MAIN_LIB=lib/libvlc

LIBS=$(ICE_LIBS);$(VLC_LIBS);$(VLC_MAIN_LIB)


build:
	slice2java --output-dir generated ../interface/InterfaceMP3.ice
	javac -d classes -classpath "$(LIBS)" ServeurMP3.java ServeurMP3I.java Player.java generated/ArchDistrib/*.java

run:
	java -classpath "$(LIBS);classes" ServeurMP3

clean:
	cd classes; rm -r *; cd ..
	cd generated; rm -r *; cd ..
