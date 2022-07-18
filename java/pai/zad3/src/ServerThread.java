package zad3;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ServerThread extends Thread{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private int portNumber;
    private int maxClientNumber;
    private ServerThread[] serverThreds;
    private String inputLine;

    ServerThread(Socket theClientSocket, ServerThread[] theServersThreds){    
        clientSocket    = theClientSocket;
        serverThreds    = theServersThreds;
        maxClientNumber = theServersThreds.length; 
    }

    public void run(){
        int maxClientNumber = this.maxClientNumber;
        ServerThread[] serverThreds = this.serverThreds;

        try{
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Enter your name:");
            String userName = in.readLine().trim();
            out.println("Hi " + userName);

            synchronized (this){
                for (int i = 0; i < maxClientNumber; i++) {
                    if (serverThreds[i] != null && serverThreds[i] != this) {
                        serverThreds[i].out.println("*** A new user " + userName + " entered the chat room !!! ***");
                    }
                }
            }
        
            while(true) {
                    inputLine = in.readLine();
                if (inputLine.equals("exit"))
                    break;

                synchronized (this){
                    for (int i = 0; i < maxClientNumber; i++) {
                        if (serverThreds[i] != null && serverThreds[i] != this) {
                            serverThreds[i].out.println("<" + userName +">: " + inputLine);
                        }
                    }
                }
            }
            
            synchronized (this){
                for (int i = 0; i < maxClientNumber; i++) {
                    if (serverThreds[i] != null && serverThreds[i] != this) {
                        serverThreds[i].out.println("*** User " + userName + "left the chatroom!!! ***");
                    }
                }
            }

            //cleaning
            synchronized (this){
                for (int i = 0; i < maxClientNumber; i++) {
                     if (serverThreds[i] == this) {
                        serverThreds[i] = null;
                    }
                }    
            }
            
            in.close();
            out.close();
            clientSocket.close();
        }
        catch (IOException e) {}
        catch (NullPointerException ex){
            synchronized (this){
                for (int i = 0; i < maxClientNumber; i++) {
                     if (serverThreds[i] == this) {
                        serverThreds[i] = null;
                    }
                }    
            }
        }
    }
}   
