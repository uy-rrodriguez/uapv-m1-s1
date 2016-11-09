CC=g++
COPTS=-I.


clientmp3: ClientMP3.cpp InterfaceMP3.o
	$(CC) $(COPTS) ClientMP3.cpp -o clientmp3 InterfaceMP3.o -lIce -lIceUtil -pthread

InterfaceMP3.cpp InterfaceMP3.h: ../interface/InterfaceMP3.ice
	slice2cpp ../interface/InterfaceMP3.ice

InterfaceMP3.o: InterfaceMP3.cpp InterfaceMP3.h
	$(CC) $(COPTS) -c InterfaceMP3.cpp

clean:
	rm *.o clientmp3
