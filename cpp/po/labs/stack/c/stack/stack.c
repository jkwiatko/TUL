#include"stack.h"

static int top; /* pierwsze wolne miejsce na stosie */
static int *dane;
static int size;

void init()
{
	top=0;
	size=0;
	dane=0;
}

void finalize()
{	
	if(size>0)
		free(dane);
}

void clear()
{
	top=0;
}

void push(int a)
{
	if(top>=size)
	{
	  int newsize=(size+1);
	  int* ndane=(int*)realloc(dane,newsize*sizeof(int));
	  if(ndane)
	    dane=ndane;
	  else
	  {
	    free(dane);
	    abort();
	  }
	  size=newsize;
	}
	dane[top++]=a;
}

int pop()
{
	assert(top>0);
	return dane[--top];
}


