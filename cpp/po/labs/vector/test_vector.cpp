#include <stdio.h>
#include "vector.h"



int main()
{
	Vector v1(3);
	cout << v1 << endl; // wypisuje (0, 0, 0)
	v1[2] = 3;
	
	Vector v2(3);
	v2[2] = 5;
	v2[1] = 4;
	cout << v2 << endl; // wypisuje (0, 4, 5)

	Vector v3 = v1 + v2;
	cout << v3 << endl; // wypisuje (0, 4, 8)

	v3 += v2;
	cout << v3 << endl; // wypisuje (0, 8, 13)
	return 0;
}
