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
	int i;
	signal (SIGCHLD, SIG_IGN);
	for (i = 0; i < 200; i++)
	{
		pid_t res;
		printf ("Starting child #%d\n", i);
		res = fork ();
		if (res == 0)
		{
			sleep (5);
			exit (i + 1);
		};
		if (res == -1)
			perror ("fork");
	}
	for (i = 0; i < 360; i++)
		sleep (1);
	exit (0);
}
