#include "Test.h"
#include "Test2.h"
#include "Test22.h"
#include <iostream>
#include <thread>

#define INITIALVAL 10

struct someStruct {
	unsigned int total;
};

void changeStruct(someStruct& value) {
	value.total = 22;
}

int main() {
	
	int testvalue = INITIALVAL;
	int parameter = 500;
	int* testptr = new int(parameter);
	Test testObject;

	testObject.fun(testvalue);
	std::cout << "passed by value testvalue: " << testvalue << std::endl;
	
	testObject.reference(testvalue, testvalue);
	std::cout << "passed by reference testvalue: " << testvalue << std::endl;
	
	testObject.pointer(testptr);
	std::cout << "passed by ptr testptr: " << *testptr << std::endl;
	
	testObject.pointer(&testvalue);
	std::cout << "passed by ptr testvalue: " << testvalue << std::endl;
	
	someStruct structVar;
	structVar.total = 10;
	changeStruct(structVar);
	std::cout << structVar.total << std::endl;
	
	char* letter;
	int n = 10;
	letter = (char*)&n;
	
	std::cout << n << std::endl;
	
	
	return 0;
}
