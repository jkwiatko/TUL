#include <iostream>
using namespace std;
#include "complex.h"

int main()
{	
	double z;
  Complex a(0.0,15),b(0,13),c, e,d(-3,-3);
  c=a;
  c=c/b;
  cout << c <<endl;
  c=a;
  c/=b;
  cout << c <<endl;
  c = 10;
  cout << c <<endl;
  c = -a;
  cout << c <<endl;  
  c = a + b;
  c = c - Complex(10);
  cout << c <<endl;  
  c = 10 - a;
  (c +=b) +=10;
  cout << c <<endl;
  z=d.phase();
  cout << z/PI*180 <<endl;
  e=c.abs();
  cout << e <<endl;
  e=c.conj();
  cout << e <<endl;
}
