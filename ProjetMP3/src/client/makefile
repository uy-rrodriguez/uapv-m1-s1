build:
	slice2py ../interface/InterfaceMP3.ice

run:
	python ClientMP3.py

run-pyglet:
	# set PYTHONPATH ~/Donnees_itinerantes/FAC/ArchDistrib/TPs/ProjetMP3/src/client/lib/pyglet/pyglet-1.2.4/:$(PYTHONPATH)
	# export PYTHONPATH=lib/pyglet/pyglet-1.2.4/:$(PYTHONPATH)
	# echo $$PYTHONPATH
	python GUI_pyglet.py

clean:
	rm -r "./ArchDistrib/"
	rm *.pyc
