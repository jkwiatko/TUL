#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <errno.h>

void
signal_handler (int what)
{
	printf ("Signal number: %d\n", what);
	printf ("Waiting for signal ... [send SIGKILL to abort]\n");
}

int
main ()
{
	struct sigaction sig;
	sig.sa_handler = signal_handler;
	sigemptyset (&sig.sa_mask);
	sig.sa_flags = SA_NOCLDSTOP;
	sigaction (SIGHUP, &sig, NULL);
	sigaction (SIGINT, &sig, NULL);
	sigaction (SIGQUIT, &sig, NULL);
	sigaction (SIGABRT, &sig, NULL);
	sigaction (SIGALRM, &sig, NULL);
	sigaction (SIGTERM, &sig, NULL);
	sigaction (SIGUSR1, &sig, NULL);
	sigaction (SIGUSR2, &sig, NULL);

	printf ("PID: %d\n", getpid ());
	printf ("Waiting for signal ... [send SIGKILL to abort]\n");

	while (1)
		sleep (1);

	exit (0);
}
