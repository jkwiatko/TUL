#include "headers/socket.h"

int main(){
    char requestMessage[MESSAGE_SIZE] = {0}, responseMessage[MESSAGE_SIZE] = {0};
    protocol clientRequest1, clientRequest2, serverResponse;

    //Requests
    rootProtocol(&clientRequest1, 4.0, false);
    dateProtocol(&clientRequest2, false);

    //creating socket
    int cliSocket;
    cliSocket = socket(AF_INET, SOCK_STREAM, 0);
    
    //specifing address
    struct sockaddr_in serverAddress;
    serverAddress.sin_family = AF_INET;
    serverAddress.sin_port = htons(9002);
    serverAddress.sin_addr.s_addr = INADDR_ANY;

    //connecting
    int connectionStatus = connect(cliSocket, (struct sockaddr*) &serverAddress, sizeof(serverAddress));
    if(connectionStatus == -1)printf("There was en error while making connection to the remote socket");
    
    //sending message
    makeMeassage(&clientRequest1, requestMessage);
    send(cliSocket, requestMessage, sizeof(requestMessage), 0);
    recv(cliSocket, responseMessage, sizeof(responseMessage), 0);
    readMessage(&serverResponse ,responseMessage);
    printProtocol(&serverResponse);

    makeMeassage(&clientRequest2, requestMessage);
    send(cliSocket, requestMessage, sizeof(requestMessage), 0);
    recv(cliSocket, responseMessage, sizeof(requestMessage), 0);
    readMessage(&serverResponse, responseMessage);
    printProtocol(&serverResponse);

    //closing
    close(cliSocket);
    return 0;
}