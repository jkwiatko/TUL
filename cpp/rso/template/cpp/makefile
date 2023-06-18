all: a.out

main.o Test.o : main.cpp Test.cpp
	g++ -c main.cpp Test.cpp -I header -std=c++11

a.out : main.o Test.o
	g++ main.o Test.o -lpthread

run: a.out
	./a.out

clean:
	rm -rf a.out main.o Test.o
