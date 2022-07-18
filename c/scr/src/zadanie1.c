#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <signal.h>

void* function(void* args);
void* function2(void* args);

int main( void )
{
	pthread_t tid1;
	pthread_t tid2;
	sigset_t signals;

	//block all signals
	sigfillset(&signals);
	pthread_sigmask(SIG_BLOCK, &signals, NULL);

	//create threads
	pthread_create( &tid1, NULL, &function, NULL );
	pthread_create( &tid2, NULL, &function2, NULL );

	//wait for threads
	pthread_join(tid1,NULL);
	pthread_join(tid2, NULL);

	return EXIT_SUCCESS;
}

void*  function( void*  arg )
{
	siginfo_t info;
	sigset_t signals;

	// only SIGRTMIN allowed
	sigfillset(&signals);
	sigdelset(&signals, SIGRTMIN);
	pthread_sigmask(SIG_BLOCK, &signals, NULL);

	// waiting for SIGRTMIN signal
	sigemptyset(&signals);
	sigaddset(&signals, SIGRTMIN);
	sigwaitinfo(&signals, &info);
	printf( "This thread (%d) was signaled with signal 41 and int_data: %d \n", pthread_self(), info.si_value.sival_int);
	return( 0 );
}

void*  function2( void*  arg )
{
	siginfo_t info;
	sigset_t signals;

	// only SIGRTMIN allowed
	sigfillset(&signals);
	sigdelset(&signals, SIGRTMAX);
	pthread_sigmask(SIG_BLOCK, &signals, NULL);

	// waiting for SIGRTMIN signal
	sigemptyset(&signals);
	sigaddset(&signals, SIGRTMAX);
	sigwaitinfo(&signals, &info);
	printf( "This thread (%d) was signaled with signal 56 and int_data: %d \n", pthread_self(), info.si_value.sival_int);
	return( 0 );
}
