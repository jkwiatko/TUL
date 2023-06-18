#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

char *
unsafe_itoa (int number)
{
	static char buf[16];
	int i = 0, j;
	do
	{
		buf[i++] = number % 10 + '0';
		number /= 10;
	}
	while (number);
	buf[i] = 0;
	for (j = 0; j < i / 2; j++)
	{
		char c = buf[j];
		buf[j] = buf[i - j - 1];
		buf[i - j - 1] = c;
	};
	return buf;
}


void *
thread (void *param)
{
	int start = *((int *) (param));
	int i;
	printf("Hello from thread %d\n",start);
	for (i = 0; i < 10000; i++)
		printf ("%d %s\n", i + start, unsafe_itoa (i + start));
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
	exit (0);
}
