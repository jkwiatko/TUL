#include <iostream>
using namespace std;

#include "book.h"			//defines class Employee
#include "map_template.h"		//defines template map_template<Key,Value>

int main(void)
{
							//Identification number of Employee
	map_template<string,book> Database;					//Database of employees

	Database.Add("Przygody Tomka" ,book("Jan","Kowalski","Przygody Tomka",true)); 	//Add first employee: name: Jan Kowalski, position: salseman, age: 28,
	
	Database.Add("Straszydla z zamku" ,book("Adam","Nowak","Straszydla z zamku",true)); 	//Add second employee: name: Adam Nowak, position: storekeeper, age: 54
	
	Database.Add("Emertura nie jest taka straszna",book("Anna","Zaradna","Emertura nie jest taka straszna",true)); 	//Add third employee: name: Anna Zaradna, position: secretary, age: 32

	cout << Database << endl;							//Print databese

	map_template<string,book> NewDatabase = Database;	//Make a copy of database
	
	book* pE;
	pE = NewDatabase.Find("Przygody Tomka");					//Find employee using its ID
	pE-> Tytul = "salesman";							//Modify the position of employee
	pE = NewDatabase.Find("Straszydla z zamku");					//Find employee using its ID
	pE-> Dostepnosc = 0;										//Modify the age of employee

	Database = NewDatabase;								//Update original database
	
	cout << Database << endl;							//Print original databese
	return 0;
}

//pamietaj o tym ze przy obsludze bledu trzeba go zwrocic na koniec!

