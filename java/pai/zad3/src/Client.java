package zad3;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Client extends Thread {

    static private String hostname = "localhost";
    static private int portNumber = 8001;
    static private String userInput, serverRespone;
    static private Socket socket;
    static private PrintWriter out;
    static private BufferedReader in, stdIn;
    static private Boolean runned = false;
    
    public static void main(String[] args){
         
        Connect();
         try{
                 out = new PrintWriter(socket.getOutputStream(), true);
                 in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 stdIn = new BufferedReader(new InputStreamReader(System.in));

                (new Client()).start();

                while(!runned){
          out.println(stdIn.readLine());
                }   
            }

            catch(IOException exc){exc.printStackTrace();}
    }    

    //Reading 
    public void run(){
        String responseLine;
        try {
          while ((responseLine = in.readLine()) != null) {
            System.out.println(responseLine);
            if (responseLine.indexOf("*** Bye") != -1)
              break;
          }
          runned = true;
        } catch (IOException e) {
          System.err.println("IOException:  " + e);
        }
    }

    private static void Connect(){
       boolean shouldTry = true; 
       String yesOrNo;
       BufferedReader reconnect = null;

        while(shouldTry){
            try{
                socket = new Socket(hostname,portNumber);
                shouldTry = false;
            }
            catch(IOException e){
            System.out.println("Nie moge sie polaczyc z serverem. Sprobowac zreconnectowac? (Y/N)");
            reconnect = new BufferedReader (new InputStreamReader(System.in));
                try{
                    while(true){
                        yesOrNo = reconnect.readLine();
                        if(yesOrNo.equals("Y") || yesOrNo.equals("y")) {shouldTry = true; break;}
                        else if (yesOrNo.equals("N") || yesOrNo.equals("n")) {shouldTry = false; System.exit(0);}
                        else {System.out.println("Its yeas or no question...");}
                    }    
                }
                catch(IOException ex){ex.printStackTrace();}
            }
        }
        try{
            if(reconnect != null ){reconnect.close();}
        }
        catch(IOException ex){ex.printStackTrace();}
    }
}
