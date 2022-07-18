#ifndef __EMPLOYEE_H__
#define __EMPLOYEE_H__
#include <string>
#include <iostream>


using namespace std;

class Employee
{
public:
	string FirstName, Position;
	unsigned Age;


	Employee (const string & fn="no name", const string & p="no position", const int & i=0):FirstName (fn), Position (p), Age (i)
	{};
  
	friend ostream& operator<< (ostream & o, const Employee & e)
	{
		o << "Imie i Nazwisko: " <<e.FirstName << endl<< "Stanowisko: " << e.Position << endl <<"Wiek: " << e.Age;
		return o;
	};
  
  
};

#endif	/* __EMPLOYEE_H__ */
