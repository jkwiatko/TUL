#ifndef __EMPLOYEE_H__
#define __EMPLOYEE_H__
#include <string>
#include <iostream>


using namespace std;

class book
{
public:
	string FirstName,LastName,Tytul;
	bool Dostepnosc;


	book (const string & fn="no name", const string & p="no last name", const string & t="no titile", bool d=false):FirstName (fn), LastName (p), Tytul (t) , Dostepnosc (d)
	{};
  
	friend ostream& operator<< (ostream & o, const book & e)
	{
		o << "Imie: "<<e.FirstName << endl << "Nazwisko: " << e.LastName << endl << "Tytul: " << e.Tytul << endl << "Dostepnosc: "<<e.Dostepnosc<< endl;
		return o;
	};
  
  
};

#endif	/* __EMPLOYEE_H__ */
