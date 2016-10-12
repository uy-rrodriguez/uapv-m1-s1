all: compile package redeploy

compile:
	javac -d WEB-INF/classes user/UserData.java

package:
	jar cvf Rendu_TP1.war *

redeploy:
	asadmin redeploy --name Rendu_TP1 Rendu_TP1.war

clean:
	rm Rendu_TP1.war

