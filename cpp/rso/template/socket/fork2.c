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
	int status;
	res = fork ();
	if (res == 0)
	{
		printf ("Hello from child\n");
		sleep (1);
		abort ();
	};
	if (res == -1)
		perror ("fork");
	wait (&status);
	if (WIFEXITED (status))
		printf ("Child exited normally with exit code %d\n",
				WEXITSTATUS (status));
	else
		printf ("Child exited abnormally\n");
	exit (0);
}
