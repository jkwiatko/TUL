#include <iostream>
#include "headers/socket.h"
#include <arpa/inet.h>

using namespace std;

int main(){
    double x = 1;
    if (ifBigEndian()) printf("Big endian no conversion required");
    else x = htonl(x);
    printf("x: %f\n" ,x);
    return 0;
}