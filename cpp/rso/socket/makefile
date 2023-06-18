### Directory Variables ###
SRC 	:= src/
BIN 	:= bin/
OBJ		:= $(BIN)obj/
TEMP 	:= template/
CC 		:= g++
ET 		:= sakura
HEADERS := $(shell find $(SRC)headers/ -name '*.h')
CFLAGS 	:= -g -Wall -pedantic -c -std=c++11 -I $(SRC)headers

### Utils ###
.SECONDARY: $(OBJ)%.o
.PRECIOUS: $(OBJ)%.o 
.PHONY: clean tree

### Run Recipe ###
run: $(BIN)server.out $(BIN)client.out
	@echo "===Compilation successful===\nRunning program...\n"
	$(ET) -x 'sh -c "cd $(BIN) && ./server.out; exec /bin/bash"' &  \
	sleep 1 &&														\
	$(ET) -x 'sh -c "cd $(BIN) && ./client.out; exec /bin/bash"'

### .out=.o ###
%.out: $(addprefix $(OBJ), %.o) $(addprefix $(OBJ), $(notdir $(HEADERS:.h=.o)))
	g++ $^ -o $@

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
# no comments this time