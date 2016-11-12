CC=g++

COPTS=-I.
#COPTS=-I. -I G:/Dev/Ice-3.6.3/include -v

LIBS=-lIce -lIceUtil
#LIBS=-L G:/Dev/Ice-3.6.3/lib -l:"G:/Dev/Ice-3.6.3/lib/ice.lib" -l:"G:/Dev/Ice-3.6.3/lib/iceutil.lib" -Wl,-rpath=G:/Dev/Ice-3.6.3/lib,-LG:/Dev/Ice-3.6.3/lib


all: clientmp3

clientmp3: ClientMP3.cpp InterfaceMP3.o
	$(CC) $(COPTS) ClientMP3.cpp -o clientmp3 InterfaceMP3.o $(LIBS) -pthread

InterfaceMP3.cpp InterfaceMP3.h: ../interface/InterfaceMP3.ice
	slice2cpp ../interface/InterfaceMP3.ice

InterfaceMP3.o: InterfaceMP3.cpp InterfaceMP3.h
	$(CC) $(COPTS) -c InterfaceMP3.cpp

clean:
	rm *.o clientmp3
