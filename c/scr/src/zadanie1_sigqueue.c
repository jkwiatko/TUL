#include <stdio.h>
#include <signal.h>
#include <errno.h>
#include <string.h>

#define BUFFOR 100

int main () {

	char string_pid	   [BUFFOR];
	char string_signal [BUFFOR];
	char string_data   [BUFFOR];
	int  int_pid;
	int  int_signal;
	int  int_data;
	union sigval sig_value;

//	 get pid
	printf("Give pid of process: \n");
	fgets(string_pid,BUFFOR,stdin);
	sscanf(string_pid, "%d", &int_pid);

	//get signal
	printf("Give signal for process: \n");
	fgets(string_signal,BUFFOR,stdin);
	sscanf(string_signal, "%d", &int_signal);

//	get data for process
	printf("Give data for process: \n");
	fgets(string_data,BUFFOR,stdin);
	sscanf(string_data, "%d", &int_data);
	sig_value.sival_int = int_data;

	//send signal
	if((sigqueue( int_pid, int_signal, sig_value)) == -1) {
		printf("ERROR: %s\n", strerror(errno));
	}

return 0;
}
