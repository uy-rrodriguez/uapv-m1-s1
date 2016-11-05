all: compile test
	@echo DONE: Compilation et test

compile:
	@echo Compilation
	@javac *.java -extdirs ../lib/

test:
	@echo Test JUnit
	@java -cp .:../lib/junit-4.12.jar:../lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore ABRTest

testABR:
	@echo Test main ABR
	@java ABR

clean:
	rm *.class

