#include<stdio.h>
#include<malloc.h>
#include<iostream>
#include<stdlib.h>
#include<fstream>
using namespace std;



class Vector
{

	//Dane:
	unsigned size;	
	int* skalary;

  public:
	//Konstruktory i Desktruktory:

	Vector(unsigned);
	Vector(const Vector&);
	Vector();
	~Vector();

	//Operatory:

	Vector& operator=(const Vector&);	
	Vector& operator+=(const Vector);
	Vector operator+(const Vector) const;
	friend ostream& operator<<(ostream&, const Vector&);
	int& operator[](unsigned);

	//Wyjatki:
class	IndexoutofRange{};
class	WrongSize{};
};


Vector::Vector(unsigned i=1)
{
	size=i;
	skalary = new int[size];
	for(unsigned j=0;j<i;++j)
	skalary[j]=0; 
}

Vector::~Vector()
{
	delete[] skalary;
}

Vector::Vector(const Vector& a)
{
	size=a.size;
	skalary= new int[size];
	for(unsigned i=0;i<size;++i)
		skalary[i]=a.skalary[i];	
}

Vector& Vector::operator=(const Vector& a)
{
	skalary= new int[a.size];
	size=a.size;
	for(unsigned i=0;i<size;++i)
		skalary[i]=a.skalary[i];
	 return *this;	
}



Vector& Vector::operator+=(const Vector a)
{
	if(size!=a.size) throw WrongSize();	
	for(unsigned i=0;i<size;++i)
		skalary[i]+=a.skalary[i];
	return *this;	
}

Vector Vector::operator+(const Vector a) const
{
	if(size!=a.size) throw WrongSize();
	Vector suma(a.size);	
	for(unsigned i=0;i<size;++i)
		suma.skalary[i]=skalary[i]+a.skalary[i];
	return suma;	
}

int& Vector::operator[](unsigned i)
{
	int* temp=skalary;
	if(size<i)throw IndexoutofRange();
	temp+=i;
	return 	*temp;
}

ostream& operator<<(ostream& stream, const Vector& a)
{

	for(unsigned i=0;i<a.size;++i)
		stream << a.skalary[i]<<" ";
	stream << endl;
	stream << endl << endl;
	return stream;
}


