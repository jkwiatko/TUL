#include "headers/socket.h"

int main(){
    char response[MESSAGE_SIZE] = {0}, request[MESSAGE_SIZE] = {0};
    protocol serverResponse, clientRequest;

    //creating socket
    int servSocket;
    servSocket = socket(AF_INET, SOCK_STREAM, 0);
    
    //specifing address
    struct sockaddr_in serverAddress;
    serverAddress.sin_family = AF_INET;
    serverAddress.sin_port = htons(9002);
    serverAddress.sin_addr.s_addr = INADDR_ANY;

    //connecting
    int connectionStatus = bind(servSocket, (struct sockaddr*) &serverAddress, sizeof(serverAddress));
    if(connectionStatus == -1)printf("There was en error while making connection to the remote socket");
    listen(servSocket, 5); //(socket, backlog (how many connctions can wait in one moment in time))
    int clientSocket;
    clientSocket = accept(servSocket, NULL, NULL);
    
    //responding
    for(unsigned i=0; i<2; ++i){
        recv(clientSocket, &request, sizeof(request), 0);
        readMessage(&clientRequest, request);
        makeReplay(&clientRequest, &serverResponse);
        makeMeassage(&serverResponse, response);
        send(clientSocket, response, sizeof(response), 0);
    }

    //closing
    close(servSocket);
    return 0;
}