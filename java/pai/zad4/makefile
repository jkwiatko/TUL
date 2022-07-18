PACKAGE = zad4
SRC = src/
BIN = bin/
JFLAGS = -g -d ./$(BIN) -cp $(BIN)
JC = javac
ET = sakura


.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	$(SRC)Exceptions.java		\
	$(SRC)User.java				\
	$(SRC)Service.java 			\
	$(SRC)TimeBank.java 		\
	$(SRC)ClientApp.java 		\
	$(SRC)ServerApp.java 		\

default: $(BIN) classes

$(BIN):
		@if [ ! -d "./$(BIN)" ];then     \
        		mkdir $(BIN);           \
		fi
		

classes: $(CLASSES:.java=.class)
	$(ET) -x 'sh -c "cd ./$(BIN) && java $(PACKAGE).ClientApp; exec /bin/bash"' &\
	$(ET) -x 'sh -c "cd ./$(BIN) && java $(PACKAGE).ClientApp; exec /bin/bash"' &\
	cd ./$(BIN) && java $(PACKAGE).ServerApp

clean:
	$(RM) $(BIN)$(PACKAGE)/*.class
