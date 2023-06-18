#include "Test.h"
#include <iostream>

void Test::fun(int f) {
	
	std::cout << "in fun " << f << std::endl;
}


void Test::reference(int in, int& out) {
	
	in -= 20;
	out+=11;
}

void Test::pointer(int* i) {
	
	*i += 1;
}