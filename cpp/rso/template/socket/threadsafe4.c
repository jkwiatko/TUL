#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

int c;

void *
thread (void *param)
{
	int i;
	for (i = 0; i < 10000; i++)
	{
		c=c*123413123+7;;
	}
	pthread_exit (param);
}

int
main (int argc, char *argv[])
{
	pthread_t t1, t2;
	pthread_attr_t attr;
	int a, b;
	void *retval;
	a = 0;
	pthread_attr_init (&attr);
	pthread_create (&t1, &attr, thread, &a);
	if (argc > 1)
	{
		b = 10000;
		pthread_create (&t2, &attr, thread, &b);
		pthread_join (t2, &retval);
	};
	pthread_join (t1, &retval);
	printf("c=%d\n",c);
	exit (0);
}
