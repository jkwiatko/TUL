#include <assert.h>
#include <stdlib.h>
#include <stdio.h>
#include "stack.h"

stack::stack()
{
	this->top=0;
	this->size=0;
	this->dane=0;
}

stack::~stack()
{
	if(size>0)
		free(this->dane);
}

void stack::clear()
{
	this->top=0;
}

void stack::push(int a)
{
	
	if(top>=size)
	{
		 int newsize=(this->size+1);
		 int* ndane=(int*)realloc((this->dane),newsize*sizeof(int));
		 if(ndane)
			this->dane=ndane;
		 else
		 {
			free(this->dane);
			abort();
		 }
		printf("\n\nRozmiar stosu  %d -> %d\n",size,newsize);
		this->size=newsize;
	}
	this->dane[this->top++]=a;
}

int stack::pop()
{
	assert(top>0);
	int popdane=dane[--this->top];
	if(this->top < this->size)
	{
		int newsize=(size-1);
		int* ndane=(int*)realloc(this->dane,newsize*sizeof(int));
		if(ndane)
			dane=ndane;
		printf("\n\nRozmiar stosu %d -> %d\n",this->size,newsize);
		size=newsize;
	 }
	 return popdane;	
}

