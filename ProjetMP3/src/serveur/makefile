CC=g++
COPTS=-I.
#ICE_LIBS=$ICE_HOME/lib/*
ICE_LIBS=G:/Dev/Ice-3.6.3/lib/*


serveurmp3: ServeurMP3.cpp InterfaceMP3.o
	$(CC) $(COPTS) ServeurMP3.cpp -o serveurmp3 InterfaceMP3.o -lIce -lIceUtil -pthread

InterfaceMP3.cpp InterfaceMP3.h:  ../interface/InterfaceMP3.ice
	slice2cpp ../interface/InterfaceMP3.ice

InterfaceMP3.o: InterfaceMP3.cpp InterfaceMP3.h
	$(CC) $(COPTS) -c InterfaceMP3.cpp

clean:
	rm *.o serveurmp3

test:
	slice2java --output-dir generated ../interface/InterfaceMP3.ice
	javac -d classes -classpath "$(ICE_LIBS)" ServeurMP3.java ServeurMP3I.java generated/ArchDistrib/*.java

run:
	java -classpath "$(ICE_LIBS)";classes ServeurMP3


