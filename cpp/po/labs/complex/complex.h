#ifndef __Complex_H__
#define __Complex_H__
#include <iostream>
#include <math.h>
#define PI 3.14159

using namespace std;

class Complex
{
//	Dane
	double Real, Imag;

public:
//	Konstruktory i destruktory
	Complex (double R=0, double I=0)
	{
		Real = R;
		Imag = I;
	};

	Complex & operator= (const Complex & s)
	{
		Real = s.Real;
		Imag = s.Imag;
		return *this;
	};

	Complex operator- () const
	{
		if(Real==0 && Imag==0)return Complex();
		if(Imag==0) return Complex(-Real);
		if(Real==0) return Complex(Real,-Imag);
		else
			return Complex(-Real,-Imag);    
	};

	Complex & operator= (double co)
	{
		Real = co;
		Imag = 0;
		return *this;
	};

	Complex operator+ (const Complex& co) const
	{
		Complex n;
		n.Real = Real + co.Real;
		n.Imag = Imag + co.Imag;
		return n;
	};


  Complex & operator+= (Complex co)
	{
		Real += co.Real;
		Imag += co.Imag;
		return *this;
	};

	Complex & operator-= (Complex co)
	{
		Real -= co.Real;
		Imag -= co.Imag;
		return *this;
	};
  
	Complex & operator/=(Complex co)
	{  
		double a = Real;
		Real= (Real*co.Real+Imag*co.Imag)/(co.Real*co.Real + co.Imag*co.Imag);
		Imag= (Imag*co.Real-a*co.Imag)/(co.Real*co.Real + co.Imag*co.Imag);
		return *this;
	}
  

	Complex conj()
	{
		Complex c;
		c.Real=Real;
		c.Imag=-Imag;
		return c;
	};
  
	inline double abs()
	{
		return sqrt(Real*Real+Imag*Imag);
	};
  
	double phase()//atan2 mozliwy
	{
		if(Real>0 && Imag>0) return atan(Imag/Real);
		if(Real<0 && Imag>0) return PI-atan(Imag/-Real);
		if(Real<0 && Imag<0) return PI+atan(-Imag/-Real);
		else return 2*PI-atan(-Imag/Real);
	};
  
  
// nie jest potrzebne "friend", bo funkcja nie wchodzi do private Complex operator- (Complex, Complex);takie odejmowanie jest dozwolone dzieki wbudowaniu operatora odejmownia w zaprzyjazniona funckje.
	  

	friend Complex operator/ (Complex co, Complex przezco)
	{
		Complex n;
		n.Real=(co.Real*przezco.Real)+(co.Imag*przezco.Imag)/(przezco.Real*przezco.Real+przezco.Imag*przezco.Imag);
		n.Imag=(co.Imag*przezco.Real)-(co.Real*przezco.Imag)/(przezco.Real*przezco.Real+przezco.Imag*przezco.Imag);
		return n;
	}
	friend ostream & operator << (ostream & s, const Complex & c)
	{
		s << "(" << c.Real << "," << c.Imag << ")";
		return s;
	};
};


	inline Complex
	operator - (Complex s1, Complex s2)
	{
		 Complex n (s1);
		 return n -= s2;
	};


#endif /* __Complex_H__ */
