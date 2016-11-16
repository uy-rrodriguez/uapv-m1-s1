#ICE_LIBS=$ICE_HOME/lib/*
ICE_LIBS=G:/Dev/Ice-3.6.3/lib/*

#DEL=rm
#DELTREE=rm -R
DEL=del
DELTREE=rmdir /S /Q

build:
	slice2java --output-dir generated ../interface/InterfaceMP3.ice
	javac -d classes -classpath "$(ICE_LIBS)" ServeurMP3.java ServeurMP3I.java generated/ArchDistrib/*.java

dossiers:
	mkdir generated

run:
	java -classpath "$(ICE_LIBS)";classes ServeurMP3

clean:
	$(DELTREE) generated
	$(DELTREE) classes
