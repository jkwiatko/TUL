#include"./headers/socket.h"

void makeMeassage(const protocol* data, char* message){
    protocol    temp;

    if(ifBigEndian){
        temp.request    = data->request;
        temp.requestID  = data->requestID;
        if(temp.request == 1 || temp.request == 1001) temp.number = data->number;
        else if(temp.request == 1002) strcpy(temp.date, data->date);
    }

    else{
        temp.request    = reverseEndianness(data->request);  
        temp.requestID  = reverseEndianness(data->requestID);
         if(temp.request == 1 || temp.request == 1001) temp.number = reverseEndianness(data->number);
         else if(temp.request == 1002) strcpy(temp.date, data->date);
    }

    memcpy(message, (char*)&temp.request, sizeof(int));
    memcpy(message+4, (char*)&temp.requestID, sizeof(int));
    if(data -> request == 1 || data -> request == 1001)
         memcpy(message+8, (char*)&temp.number, sizeof(double));
    else if(data -> request == 2 || data -> request == 1002)
         memcpy(message+8, temp.date, sizeof(temp.date));
}


void readMessage(protocol* data, char* message){
    if(ifBigEndian){
        data->request = *(int*)message;
        data->requestID = *(int*)(message+4);
        if(data -> request == 1 || data -> request == 1001) data -> number = *(double*)(message+8);
        else if (data -> request == 1002){
            strcpy(data -> date, (message+8)); 
            data -> date[24] = '\0';  
        } 
    }

    else{
        data->request = reverseEndianness(*(int*)message);
        data->requestID = reverseEndianness(*(int*)(message+4));
        if(data -> request == 1 || data -> request == 1001) data -> number = reverseEndianness(*(double*)(message+8));
        else if (data -> request == 1002){
          strcpy(data -> date, (message+8));
          data -> date[24] = '\0';  
        }  
    }    
}

void makeReplay(const protocol* clientRequest, protocol* serverResponse){
    time_t curtime;
    static int requestID = 1000;
    ++requestID;
    
    if(clientRequest->request == 1){
        printProtocol(clientRequest);
        serverResponse->request     = 1001;
        serverResponse->requestID   = requestID;
        serverResponse->number      = sqrt(clientRequest->number);
    }

    else if(clientRequest->request == 2){
        printProtocol(clientRequest);
        time(&curtime);
        serverResponse->request = 1002;
        serverResponse->requestID = requestID;
        printf("%s\n", ctime(&curtime));
        strcpy(serverResponse->date, ctime(&curtime));
    }
}

void dateProtocol(protocol* data, bool ifResponse){
    static int requestID = 1;
    ++requestID;    

    data -> requestID   = 2;
    if(ifResponse) data -> request = 1002;
    else data -> request    = 2; 
}

void rootProtocol(protocol* data, double number, bool ifResponse){
    static int requestID = 1;
    ++requestID;    

    if(ifResponse) data -> request = 1001;
    else data -> request    = 1; 
    data -> requestID       = requestID;
    data -> number          = number;
}


bool ifBigEndian(){
    int checkNumber = 1;
    unsigned char* startingAddress = (unsigned char*)&checkNumber;  
    return *startingAddress == 1 ? false : true;
}

double reverseEndianness(const double value){
    double result;
    char *dest = (char*) &result;
    const char* data = (char*) &value; 

    for(unsigned i=0; i<sizeof(double) ; i++){
        dest[i] = data[sizeof(double)-i-1];
    }
    return result;
}

int reverseEndianness(const int value){
    int result;
    char *dest = (char*) &result;
    const char* data = (char*) &value; 

    for(unsigned i=0; i<sizeof(int) ; i++){
        dest[i] = data[sizeof(int)-i-1];
    }
    return result;
}

void printProtocol(const protocol* data){
    if(data -> request == 1 || data -> request == 1001){
        printf("Request:%d\n",data ->request);
        printf("RequestID:%d\n",data ->requestID);
        printf("Root:%f\n\n",data ->number);
    }
    else if(data -> request == 1002){
        printf("Request:%d\n",data->request);
        printf("RequestID:%d\n",data->requestID);
        printf("Date:%s\n\n",data->date);
    }
}