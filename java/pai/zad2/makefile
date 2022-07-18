PACKAGE = zad2
SRC = src/
BIN = bin/
JFLAGS = -g -d ./$(BIN) -cp $(BIN)
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	$(SRC)Save.java \
	$(SRC)HTMLExtractor.java \
	$(SRC)MainApp.java 	\

default: $(BIN) classes

$(BIN):
		@if [ ! -d "./$(BIN)" ];then     \
        		mkdir $(BIN);           \
		fi

classes: $(CLASSES:.java=.class)
	@echo "\nruning program without arugments..."
	@cd ./$(BIN) && java $(PACKAGE).MainApp
	@echo "\nrunning program on https://en.wikipedia.org/wiki/Email_address"
	@cd ./$(BIN) && java $(PACKAGE).MainApp https://en.wikipedia.org/wiki/Email_address

clean:
	$(RM) $(BIN)$(PACKAGE)/*.class
