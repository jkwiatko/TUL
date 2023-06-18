#ifndef __SOCKET_H_INCLUDED__
#define __SOCKET_H_INCLUDED__

#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

//for sockets
#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>
#include <netinet/in.h>
#define MESSAGE_SIZE 32

using namespace std;
bool    ifBigEndian();
double  reverseEndianness(const double);
int     reverseEndianness(const int);
void    makeMeassage(const struct protocol* data, char* message);
void    readMessage(struct protocol* data, char* message);
void    makeReplay(const protocol* clientRequest, protocol* serverResponse);
void    dateProtocol(struct protocol* data, bool ifResponse);
void    rootProtocol(struct protocol* data, double number, bool ifResponse);
void    printProtocol(const struct protocol* data);


typedef struct protocol{
    int     request;        //0001 root requests
    int     requestID;      //0002 date and time request
    double  number;         //1001 root answer
    char    date[25];       //1002 date answer
};
#endif