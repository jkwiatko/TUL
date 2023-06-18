#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <errno.h>

void
childsignal (int what)
{
	pid_t v;
	int old_errno = errno;
	printf ("New signal\n");
	while ((v = waitpid (-1, NULL, WNOHANG)) != 0
			&& !(v == -1 && errno != EINTR))
		printf ("%d\n", v);
	if (v == -1)
		perror ("waitpid");
	printf ("Signal exit\n");
	errno = old_errno;
}

int
main ()
{
	int i;
	struct sigaction sig;
	sig.sa_handler = childsignal;
	sigemptyset (&sig.sa_mask);
	sig.sa_flags = SA_NOCLDSTOP;
	sigaction (SIGCHLD, &sig, NULL);
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
	};
	for (i = 0; i < 360; i++)
		sleep (1);
	exit (0);
}
