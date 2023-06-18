#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <errno.h>

int
main ()
{
	pid_t res;
	int i;
	res = fork ();
	if (res == 0)
	{
		for (i = 0; i < 200; ++i)
		{
			printf ("Hello #%d from child\n", i);
		}
		abort ();
	};
	if (res == -1)
		perror ("fork");
	for (i = 0; i < 200; ++i)
	{
		printf ("Hello #%d from parent\n", i);
		sleep (1);
	};
	exit (0);
}
