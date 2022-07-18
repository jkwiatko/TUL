package zad3;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.PrintWriter;
import java.io.IOException;

class ServerApp{
public static void main(String[] args) {
	    ServerSocket 	serverSocket 	= null;
	    Socket 			clientSocket 	= null;
	    PrintWriter 	out 			= null;
	    int 			portNumber 		= 8001;
	    final int 		MAXCLIENTNUMBER = 3;
		ServerThread[] 	serverThreads 	= new ServerThread[MAXCLIENTNUMBER];
 
	    try{ 
	        serverSocket = new ServerSocket(portNumber);
	        System.out.println("Server running on port " + portNumber + "...");

		    while(true){
		          
		        String inputLine, outputLine; 
	            clientSocket = serverSocket.accept();
	            int i=0;
	           	for(i = 0; i < MAXCLIENTNUMBER; ++i){
	           		if(serverThreads[i] == null){
	           			(serverThreads[i] = new ServerThread(clientSocket, serverThreads)).start();
	           			break;
	           		}
	           		if(i == MAXCLIENTNUMBER - 1){
	           			out = new PrintWriter(clientSocket.getOutputStream(), true);
	            		out.println("Server is busy, please try again later...");
	            		out.close();
          				clientSocket.close();
	           		}
	           	}
		    }
		}
	    catch(IOException ex){ex.printStackTrace();}
        finally{

            try{
                if(out          != null) out.close();
                if(clientSocket != null) clientSocket.close();
                if(serverSocket != null) serverSocket.close();
            }
            catch(IOException exc){exc.printStackTrace();}
        }
    }
}
