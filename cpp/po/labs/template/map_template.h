
#include<string.h>
#include<iostream>
using namespace std;

template <class key_class ,class data_class> class map_template
{
private:
//	Wyjatki:		
	class copyerror{};
	
	struct node
	{
//		dane:
		node *next;
		key_class key;
		data_class val;
		
//		konstruktor:
		node (const key_class& k, data_class& a):next (NULL), key(k), val(a) {}; // lista inicjalizacyjna jest
																				// bardziej optymalnym 
																				// rozwiazaniem i pozwala uniknac
																				// bledow czesciowej incjalizacji,
																				// pozwala tez pozostac zmiennym
																				// w data_class pozostac prywatnymi
		
		//destruktor
		~node () {}
		
//		konstruktor kopiujacy:
		node (const node & s):next (NULL)
		{
			key = key_class(s.key);
			val=s.val;
		};
		

		
				
private:								
		
		node & operator= (const node &); //assignment not allowed (deklaracja bez definicji blokuje operator) 
	};
			  
	node *head;
	void insert (const key_class *key, data_class value);
	void clear ()
	{
		while (head)
		{
			node *t = head->next;
			delete head;
			head = t;
		};
	}
		
	void
	swap (map_template<key_class , data_class> & l)
	{
		node *t = head;
		head = l.head;
		l.head = t;
	}	  
public:
	void Add (const key_class & key, data_class value)
	{
		node *nowy = new node (key, value); // zmiana konstruktora wezla dala mozliwosc ominiecia bledow
											// zwiazanych z alokacja pamieci dla data_class(ktora "wbrew pozorom" 
											// moze wystapic).
		nowy->next = head;
		head = nowy;
		
	}
	map_template ()
	{
		head = NULL;
	}
		
	map_template (const map_template<key_class, data_class> & l)
	{
		node *src, **dst;										// wskaznik do wskaznika umozliwia alokacje pod
																// konkretnym juz adresem.
		head = NULL;
		src = l.head;
		dst = &head;
		try
		{
			while (src)
			{
				*dst = new node (*src);
				src = src->next;
				dst = &((*dst)->next);
			}
		}
		catch (...)
		{
			clear ();
			throw copyerror();
		};
	}
			  
	map_template & operator= (const map_template<key_class, data_class> & l)
	{
		map_template t (l);
		swap (t);
		return *this;
	}
		 	
	~map_template ()
	{
		clear ();
	};
			  
	data_class& operator[] (const key_class *key)
	{
		node *c = Find (key);
		if (!c)
		{
			insert (key, 0);
			c = head;
		};
		return c->val;
	}




	data_class* Find (const key_class &akey) const
	{
		node *
		c =	head;
		while (c)
		{
			if (c->key == akey)
			return &c->val;
			c = c->next;
		};
		return NULL;
	}	
	
	
	friend ostream& operator<< (ostream& o, const map_template<key_class, data_class>& v) 
	{
		node *
		c =	v.head;
		while (c)
		{
			o <<"key: "<< c->key << endl;
			o << c->val <<endl<<endl;
			c = c->next;
		};               
		return o;
}
		
};
