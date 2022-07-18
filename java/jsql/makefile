
JFLAGS = -g -d
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) . $*.java

CLASSES = \
	Exceptions.java \
	Column.java \
	Table.java \
	Database.java \
	Client.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
