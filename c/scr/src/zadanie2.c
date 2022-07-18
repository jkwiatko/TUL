#include <stdio.h>
#include <sys/neutrino.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <errno.h>

pid_t child_pid;
pid_t parentId;

void sortNumbers(int numbers[], int n);


int main ()
{
       int channel_id = 0;
       int rcvid = 0;
       struct _msg_info info;
       int coid = 0;			//value returned by connect attached

       struct map_info {
    	   char name[120];
		   int byte_size;
       };

       struct numbers {
    	   int numbers[4];
       };

//-------------------------------------------------------------------

  // create channel
  if ( (channel_id = ChannelCreate(_NTO_CHF_DISCONNECT )) == -1)
  {
     printf("Kurza twarz nie udalo sie utworzyc kanalu.\n");
     return 0;
  }

  printf("Id utworzonego kanalu: %d\n", channel_id);
  printf("PID procesu glownego (serwera): %d\n", getpid() );

  parentId = getpid();

   child_pid = fork ();

//-------------------------------------------------------------------  server
   if (child_pid != 0)
   {
	   	struct numbers* rptr;
	    struct map_info server_map_info;

	    //recevie info about mapped object
		rcvid = MsgReceive(channel_id, &server_map_info, sizeof(server_map_info), &info);
		printf("rcvid id: %d \n", rcvid);

		//open object
		int fd = shm_open(server_map_info.name, O_RDWR | O_RDWR, S_IRUSR | S_IWUSR );
		if(fd == -1)printf("kurza twarz nie udalo sie otworzyc wspodzielonego obiektu\n");
		ftruncate(fd, server_map_info.byte_size);
		rptr = mmap(NULL, server_map_info.byte_size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);

		//sort and signal
       	sortNumbers(rptr->numbers, server_map_info.byte_size/sizeof(int));
		MsgReply(rcvid, EOK, NULL, 0);

		//close  unused reference
		shm_unlink(server_map_info.name);
		close(fd);
   }

//------------------------------------------------------------------- client
   else
   {

	   int fd;
	   char name[] = "numbers";
	   struct numbers* rptr;
	   struct map_info client_map_info;

	   //connect to chanel
       printf ("PID procesu potomnego(clienta): %d\n",(int)getpid());
       coid = ConnectAttach(0, parentId, channel_id, 0, 0);
       printf("coid : %d\n", coid );

       // create object in shared memory
       fd = shm_open(name, O_CREAT | O_RDWR, S_IRUSR | S_IWUSR );
       if(fd == -1)printf("kurza twarz nie udalo sie stworzyc wspodzielonego obiektu\n");
       ftruncate(fd, sizeof(struct numbers));
       rptr = mmap(NULL, sizeof(struct numbers), PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);

       // add unordered numbers to object
       rptr->numbers[0] = 11;
       rptr->numbers[1] = 110;
       rptr->numbers[2] = 5;
       rptr->numbers[3] = 3;

       // send info about object
       client_map_info.byte_size = sizeof(struct numbers);
       strcpy(&client_map_info.name, &name);
       rcvid = MsgSend(coid, &client_map_info, sizeof(client_map_info), NULL, NULL);


       printf("1 element: %d \n", rptr->numbers[0]);
       printf("2 element: %d \n", rptr->numbers[1]);
       printf("3 element: %d \n", rptr->numbers[2]);
       printf("4 element: %d \n", rptr->numbers[3]);


       //remove object form shared memory
       printf("\nclosing...");
       munmap(rptr, sizeof(struct numbers));
       close(fd);
       shm_unlink(name);
       exit(1);
   }
   return 0;
}


// sorting function
void sortNumbers(int numbers[], int n) {
	int temp;
	for(int i=0; i < n - 1; ++i) {
		for(int j=0; j < n-i-1; ++j){
			if(numbers[j] > numbers[j+1]) {
				temp = numbers[j];
				numbers[j] = numbers[j+1];
				numbers[j+1] = temp;
			}
		}

	}
}
