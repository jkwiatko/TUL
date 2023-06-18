/*  Make the necessary includes and set up the variables.  */

#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <signal.h>

void
alarmhandler (int s)
{
	signal (SIGALRM, alarmhandler);
	alarm (1);
}

int
main ()
{
	int sockfd;
	socklen_t len;
	struct sockaddr_in address;
	int result;
	ssize_t count;
	char buffer[1024 * 1024];
	signal (SIGALRM, alarmhandler);
	alarm (1);

	/*  Create a socket for the client.  */

	sockfd = socket (AF_INET, SOCK_STREAM, 0);

	/*  Name the socket, as agreed with the server.  */

	address.sin_family = AF_INET;
	address.sin_addr.s_addr = inet_addr ("127.0.0.1");
	address.sin_port = htons (9735);
	len = sizeof (address);

	/*  Now connect our socket to the server's socket.  */

	result = connect (sockfd, (struct sockaddr *) &address, len);

	if (result == -1)
	{
		perror ("oops: eintrc");
		exit (1);
	}

	/*  We can now read/write via sockfd.  */
	while (1)
	{

		count = write (sockfd, buffer, 1024 * 1024);
		if (count == -1)
			perror ("write");
		else
			printf ("Wrote %d bytes\n", count);
	}
}
