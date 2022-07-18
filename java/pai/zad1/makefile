PACKAGE = zad1
SRC = src/
BIN = bin/
JFLAGS = -g -d ./$(BIN) -cp $(BIN)
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	$(SRC)Reader.java 		\
	$(SRC)MainApp.java 		\

default: $(BIN) classes

$(BIN):
		@if [ ! -d "./$(BIN)" ];then     \
        		mkdir $(BIN);           \
		fi

classes: $(CLASSES:.java=.class)
	@echo "\n"
	@cd ./$(BIN) && java $(PACKAGE).MainApp 


clean:
	$(RM) $(BIN)$(PACKAGE)/*.class
