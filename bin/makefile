J = java
JFLAG = -g
JC = javac

default:
	$(JC) -d . Blackjack.java
	$(JC) -d . JUnitTests.java

clean: 
	rm *.class

run:
	@ $(J) Blackjack

test:
	javac -d . Blackjack.java
	javac -d . -cp junit-platform-console-standalone-1.3.1.jar JUnitTests.java
	java -jar junit-platform-console-standalone-1.3.1.jar --class-path . --scan-class-path
