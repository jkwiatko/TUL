PACKAGE = zad3
SRC = src/
BIN = bin/
JFLAGS = -g -d ./$(BIN) -cp $(BIN)
JC = javac
ET = sakura

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	$(SRC)Client.java \
	$(SRC)ServerThread.java \
	$(SRC)ServerApp.java 	\

default: $(BIN) classes

$(BIN):
		@if [ ! -d "./$(BIN)" ];then     \
        		mkdir $(BIN);           \
		fi

classes: $(CLASSES:.java=.class)
	$(ET) -x 'sh -c "cd ./$(BIN) && java $(PACKAGE).Client; exec /bin/bash"'   \
	& $(ET) -x 'sh -c "cd ./$(BIN) && java $(PACKAGE).Client; exec /bin/bash"' \
	& $(ET) -x 'sh -c "cd ./$(BIN) && java $(PACKAGE).Client; exec /bin/bash"' \
	& $(ET) -x 'sh -c "cd ./$(BIN) && java $(PACKAGE).Client; exec /bin/bash"' \
	& cd ./$(BIN) && java $(PACKAGE).ServerApp

clean:
	$(RM) $(BIN)$(PACKAGE)/*.class
