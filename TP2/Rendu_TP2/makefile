PROJET = Rendu_TP2
CLASSES = src/Affiche.java


all: compile package redeploy

compile:
	@echo Compilation des classes Java
	@echo ----------------------------
	javac -d . $(CLASSES)
	@echo

package:
	@echo Création du WAR
	@echo ----------------------------
	jar cvf $(PROJET).war *.jsp WEB-INF
	@echo

redeploy:
	@echo Redeploy de l''application
	@echo ----------------------------
	asadmin redeploy --name $(PROJET) $(PROJET).war
	@echo

clean:
	rm $(PROJET).war
