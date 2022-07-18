PACKAGE = zad5
SRC = src/
BIN = bin/
JFLAGS = -g -d ./$(BIN) -cp $(BIN)
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	$(SRC)ProblemGenerator.java 	\
	$(SRC)MainApp.java 			\

default: $(BIN) classes

$(BIN):
	@if [ ! -d "./$(BIN)" ];then	\
		mkdir ./$(BIN);				\
	fi

classes: $(CLASSES:.java=.class)
	@echo "\n"
	cd ./$(BIN) && java $(PACKAGE).MainApp deadlock
	@echo "\n"
	cd ./$(BIN) && java $(PACKAGE).MainApp livelock
	@echo "\n"
	cd ./$(BIN) && java $(PACKAGE).MainApp starvation
	@echo "\n"
	cd ./$(BIN) && java $(PACKAGE).MainApp wrongargument

clean:
		$(RM) $(BIN)$(PACKAGE)/*.class
