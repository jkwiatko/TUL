### Directory Variables ###
SRC 	:= src/
BIN 	:= bin/
OBJ		:= $(BIN)obj/
TEMP 	:= template/
CC 		:= g++
ET 		:= sakura
HEADERS := $(shell find $(SRC)headers/ -name '*.h')
CFLAGS 	:= -g -Wall -pedantic -c -std=c++11 -I $(SRC)headers -DICE_CPP11_MAPPING -lIce++11

### Utils ###
.SECONDARY: $(OBJ)%.o
.PRECIOUS: $(OBJ)%.o 
.PHONY: clean tree

### Run Recipe ###
run: $(BIN)Server.out $(BIN)Client.out
	@echo "===Compilation successful===\nRunning program...\n"
	$(ET) -x 'sh -c "cd $(BIN) && ./Server.out; exec /bin/bash"' &  \
	sleep 1 &&														\
	$(ET) -x 'sh -c "cd $(BIN) && ./Client.out; exec /bin/bash"'

### .out=.o ###
%.out: $(addprefix $(OBJ), %.o) $(addprefix $(OBJ), $(notdir $(HEADERS:.h=.o)))
	g++ $^ -o $@ -pthread -lIce++11

### .o=.cpp ###
$(OBJ)%.o: $(SRC)%.cpp $(HEADERS)
	$(CC) $(CFLAGS) $< -o $@

### Creating Tree ###
tree:
	@if [ ! -d "./$(SRC)" ];then		\
		mkdir ./$(SRC);					\
	fi
	@if [ ! -d "./$(SRC)headers" ];then	\
		mkdir ./$(SRC)headers;			\
	fi
	@if [ ! -d "./$(BIN)" ];then		\
		mkdir ./$(BIN);					\
	fi
	@if [ ! -d "./$(OBJ)" ];then		\
		mkdir ./$(OBJ);					\
	fi
	@echo "Ready for building!"

### Cleaning ###
clean:
		$(RM) $(BIN)*.*
		$(RM) $(OBJ)*.*

### Comments ###
# c++ -o client Printer.o Client.o -lIce++11 -pthread
# c++ -o server Printer.o Server.o -lIce++11 -pthread


