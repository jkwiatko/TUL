#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

int c;

pthread_mutex_t itoa_mutex;

void *
thread (void *param)
{
	int i;
	for (i = 0; i < 10000; i++)
	{
		pthread_mutex_lock(&itoa_mutex);
		c=c*123413123+7;;
		pthread_mutex_unlock(&itoa_mutex);
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
	pthread_mutex_init(&itoa_mutex,NULL);
	pthread_attr_init (&attr);
	pthread_create (&t1, &attr, thread, &a);
	if (argc > 1)
	{
		b = 10000;
		pthread_create (&t2, &attr, thread, &b);
		pthread_join (t2, &retval);
	};
	pthread_join (t1, &retval);
	pthread_mutex_destroy(&itoa_mutex);
	printf("c=%d\n",c);
	exit (0);
}
