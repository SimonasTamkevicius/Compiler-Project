JAVA=java
JAVAC=javac
CLASSPATH=-cp /usr/share/java/java-cup-0.11b.jar:/usr/share/java/java-cup-0.11b-runtime.jar:.
JFLEX=jflex
CUP=$(JAVA) $(CLASSPATH) java_cup.Main

all: Main.class

Main.class: absyn/*.java parser.java sym.java Lexer.java Scanner.java Main.java

%.class: %.java
	$(JAVAC) $(CLASSPATH) $^

Lexer.java: cm.flex
	$(JFLEX) cm.flex

parser.java: cm.cup
#$(CUP) -dump -expect 3 cm.cup
#$(CUP) -expect 1 cm.cup
	$(CUP) -expect 3 cm.cup

clean:
	rm -f parser.java Lexer.java sym.java *.class absyn/*.class *~
