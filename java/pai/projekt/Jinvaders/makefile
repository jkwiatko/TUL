PACAGE = Jinvaders
SRC = src/
BIN = bin/
LIB = lib/
JFLAGS = -g -d ./$(BIN) -cp $(LIB)*:$(BIN)
JC = javac
ET = sakura


.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	$(SRC)Globals.java 			\
	$(SRC)View.java 			\
	$(SRC)MainApp.java 			\
	# $(SRC)Player.java 		\
	# $(SRC)Aliens.java 		\
	# $(SRC)UFO.java 			\
	# $(SRC)Highscore.java 		\
	# $(SRC)Crap.java 			\
	# $(SRC)Crap2.java 			\

default: classes

classes: $(CLASSES:.java=.class)
	@echo "Starting program....\n*********************" &&\
	$(ET) -x 'sh -c "cd ./$(BIN) && java -cp ../lib/*:. $(PACAGE).MainApp 86 26"'
	@echo "\nEnding program....\n*********************"

clean:
	$(RM) bin/$(PACAGE)/*.class



## SOME HELP
#
# & -> If a command is terminated by the control operator ‘&’,
#	the shell executes the command asynchronously in a subshell.
#	This is known as executing the command in the background. 
#	The shell does not wait for the command to finish, and the return status is 0 (true).
#	When job control is not active (see Job Control), the standard input for asynchronous commands,
#	in the absence of any explicit redirections, is redirected from /dev/null.
# 
# && -> An AND list has the form
# 	command1 && command2
# 	command2 is executed if, and only if, command1 returns an exit status of zero.
#
# || -> An OR list has the form
#	command1 || command2
# 	command2 is executed if, and only if, command1 returns a non-zero exit status.
#
# @ -> quiet invocation
#
# \ -> not breaking the line
#
# 