#include "rcstring.h"

int main()
{	
	rcstring a,b,c;
	a="10";
	cout<< "atoi(a):"<<a.atoi()<<endl;
	b="ALA MA KOTA";
	b.toLower();
	c=b.Left(3);
	cout<<"metoda b.left:" << c <<endl;
	cout << a << " "<<"b.toLower()=" << b << endl; // 10 ala ma ma kota
	c=a+b;
	cout << c << endl; //10 ala ma kota
	rcstring d("a");
	cout << d <<endl; //a
	d+="ula";
	cout << d << endl; //aula
	d+="15";
	cout << d << endl; //aula15
	cout << d[3]<<endl; //a
	d[3]='b';
	cout << d << endl; //aulb15
	d[2]=d[1]=d[0];
	cout << d << endl; //aaab15

	return 0;
}
