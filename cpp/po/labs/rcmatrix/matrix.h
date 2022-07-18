#include <stdio.h>
#include <malloc.h>
#include <iostream>
#include <stdlib.h>
#include <fstream>
using namespace std;



class CMatrix
{		
//	Struktury i dane:
	struct matrix;
	matrix* data;
	
	public:
	
//	Podklasy:
	class element;
	class array;
		
//	Konstruktory i destrutktory:
	CMatrix(unsigned, unsigned, double, double); 
	CMatrix(const CMatrix&); 
	CMatrix(fstream&);
	~CMatrix();
	
//	Operatory:
	array operator[](unsigned);
	CMatrix& operator=(const CMatrix&);
	CMatrix operator*(const CMatrix) const;
	friend ostream& operator<<(ostream&, const CMatrix&);

//	Metody:
	double read(unsigned,unsigned)const;
	void write(unsigned,unsigned,double);
	
//	Wyjatki:
	class IndexOutOfRange{};
	class WrongDim{};
};
	
struct CMatrix::matrix
{		
//	dane:
	double* s;
	unsigned int size,n,w,k;
				
//	Konstuktory i destruktory:
	matrix(unsigned msize=3, unsigned nsize=3, float diagonal=1, float rest=0)
	{
		n=1;
		w=msize;
		k=nsize;
		size=nsize*msize;

		if(size==0)throw WrongDim();

		s=new double[size];
		for(unsigned i=0;i<msize;i++)
			for(unsigned j=0;j<nsize;j++)
			{
				if(i==j)
					s[i*k+j]=diagonal;
				else
					s[i*k+j]=rest; 
			}
	};
	
	matrix(double* wsk, unsigned msize, unsigned nsize)
	{
		n=1;
		w=msize;
		k=nsize;
		size=nsize*msize;

		if(size==0)throw WrongDim();

		s=new double[size];
		for(unsigned i=0;i<msize;i++)
			for(unsigned j=0;j<nsize;j++)	
					s[i*k+j]=wsk[i*k+j];
	};	
	
	~matrix()
	{
		delete[]s;
	};	

//	Metody:			
	matrix* detach()
	{
		if(n==1)
			return this;
		matrix* t=new matrix(s,w,k);
		n--;
		return t;
	};

};

class CMatrix::element
{
	friend class CMatrix;

//	Dane:
	CMatrix& s;
	unsigned i,j;
	
public:

//	Konstruktory i destkruktory:
	element(CMatrix& ss, unsigned ii, unsigned jj): s(ss), i(ii), j(jj){};
	
//	Operatory:
	//konwersja elemntu na double
	operator double() const
	{
		return s.read(i,j);
	}
	//przypisanie double do komorki
	element& operator = (double c)
	{
		s.write(i,j,c);
		return *this;
	}
	//przypisanie wartosci komorki do komorki
	element& operator = (const element& ref)
	{
		return operator=((double)ref);
	}
	
};

class CMatrix::array
{	
//	dane:
	unsigned col,row,thei;
	double **thearray;
	CMatrix &s; 
	
	public:
	
//	konstruktory i destruktory:
	array(CMatrix& a, unsigned which):s(a){	
		if(which>a.data->w)throw WrongDim();
		thei=which;
		col=a.data->k;
		row=a.data->w;
		thearray= new double*[col];
		for(unsigned j=0;j<col;j++)
			thearray[j]=a.data->s+(thei*col+j);
	}
	~array()
	{
		 delete[] thearray;
	}

//	Operatory:	
	element operator[](unsigned thej)
	{
		return element(s,thei,thej);
	}
	
};


CMatrix::CMatrix(unsigned m=3, unsigned n=3, double diagonal=1, double rest=0)
{

	 if(m<=0 || n <= 0)throw WrongDim();

	 data = new matrix(m,n,diagonal,rest);
}
	
CMatrix::CMatrix(const CMatrix& x)
{
	x.data->n++;
	data=x.data;
}
	
CMatrix::CMatrix(fstream& a)
{
	 int m;
	 a >> m;
	 int n;
	 a >> n; 
	 data = new matrix(m,n);
	 
	 for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
				a >> data->s[i*n+j];		
				 
}

CMatrix::~CMatrix()
{
	if(--data->n==0)
	delete data;
}

CMatrix::array CMatrix::operator[](unsigned i)
{
	if(i>data->w)throw IndexOutOfRange();
	return array(*this, i);
}

CMatrix&  CMatrix::operator=(const CMatrix& x)
{
	 if(x.data->w!=data->w || x.data->k!=data->k)throw WrongDim();
	 x.data->n++;
	 if(--data->n == 0)
	 	delete data;
	 data=x.data;
	 return *this;
}


CMatrix CMatrix::operator*(const CMatrix B) const
{

	if(data->k!=B.data->w)throw WrongDim();
	CMatrix C(data->w,B.data->k,0,0);
		for(unsigned i = 0; i < data->w; ++i)
        for(unsigned j = 0; j < B.data->k; ++j)
            for(unsigned h = 0; h < data->k; ++h)
            {
                C.data->s[i*B.data->k+j] += data->s[i*data->k+h] * B.data->s[h*B.data->k+j];
            }
	return C;
}

ostream& operator<<(ostream& stream, const CMatrix& a)
{
	for(unsigned i=0;i<a.data->w;i++)
	{
		for(unsigned j=0;j<a.data->k;j++)
			stream << a.data->s[i*a.data->k+j]<<' ';
		stream << endl;
	}
	stream << endl << endl;
	return stream;
}

inline double CMatrix::read(unsigned i, unsigned j) const
{
 	return data->s[i*data->k+j];
}

inline void CMatrix::write(unsigned int i, unsigned j, double c)
{
	  data = data->detach();
	  data->s[i*data->k+j] = c;
}

