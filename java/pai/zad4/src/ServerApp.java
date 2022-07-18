package zad4;
import java.util.LinkedList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;


class ServerApp{
public static void main(String[] args) {
	    ServerSocket 	serverSocket 	   = null;
	    Socket 			clientSocket 	   = null;
	    PrintWriter 	out 			   = null;
	    int 			portNumber 		   = 8001;
	    final int 		MAXCLIENTCOUNT     = 10;
		ServerThread[] 	serverThreads 	   = new ServerThread[MAXCLIENTCOUNT];
 
	    try{ 
	        serverSocket = new ServerSocket(portNumber);
	        System.out.println("Server running on port " + portNumber + "...");


		    while(true){	          
	            clientSocket = serverSocket.accept();
	            int i=0;
	           	for(i = 0; i < MAXCLIENTCOUNT; ++i){
	           		if(serverThreads[i] == null){
	           			(serverThreads[i] = new ServerThread(clientSocket, serverThreads)).start();
	           			break;
	           		}
	           		if(i == MAXCLIENTCOUNT - 1){
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

class ServerThread extends Thread{
    
    //Server variables
    private Socket          clientSocket;
    private PrintWriter     out;
    private BufferedReader  in;
    private String          inputLine = "null";
    private int             maxClientCount;
    private ServerThread[]  serverThreds; //TODO delete table
    
    //users variables
    private User            user;
    private String          answer;
    private String          userName;
    
    //TimeBank
    static              TimeBank            timeBank;
    static              LinkedList<User>    UserList     = new LinkedList<User>();
    final   static      String              numberPatern = new String("-?\\d+");
    final   static      SimpleDateFormat    df           = new SimpleDateFormat("yyyy.MM.dd 'at' h:mm a");

    ServerThread(Socket theClientSocket, ServerThread[] theServersThreds){    
        timeBank        = new TimeBank();
        clientSocket    = theClientSocket;
        serverThreds    = theServersThreds;
        serverThreds    = theServersThreds;
        maxClientCount  = theServersThreds.length;
    }

    private boolean usernameInUse(User user){
       return UserList.contains(user);
    }

    private void userQuits(){
        UserList.remove(user);
    }

    public void run(){
        int maxClientCount = this.maxClientCount;
        ServerThread[] serverThreds = this.serverThreds;

        try{
            //init
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            userName = in.readLine().trim();
            user = new User(userName);
            
            //checking if user name is availble
            while(usernameInUse(user)){
                out.println("Username taken, try another one:");
                userName = in.readLine().trim();
                user = new User(userName);
            }
            
            UserList.addFirst(user);
            out.println("\nHi " + userName); //greet user

            while(true) {
                int option = -1;
                out.println("1. Create Service\n2. Cancel Service\n3. Reserve Service\n4. Show My Services\n5. Exit\n");
                answer = in.readLine();
                if(answer.matches(numberPatern))
                    option = Integer.parseInt(answer);
                switch(option){
                    case 1:	
                        createService();
                    break;
                    case 2:
                        cancelService();
                    break;
                    case 3:
                        reserveService();
                    break;
                    case 4:
                        myServices();
                    break;
                    case 5:
                        out.println("Exiting...");
                        userQuits();
                    break;
                    default:
                        out.println("Wrong value there is no \'" + answer + "\' option");
                    break;
                }
                if (inputLine.equals("exit"))
                    break;
            }
            
            //cleaning
            synchronized (this){
                for (int i = 0; i < maxClientCount; i++) {
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

        // handless forcequiting client
        catch (NullPointerException ex){
            synchronized (this){
                for (int i = 0; i < maxClientCount; i++) {
                     if (serverThreds[i] == this) {
                        serverThreds[i] = null;
                        userQuits();
                    }
                }    
            }
        }
    }

    /*
    ##     ## ######## ##    ## ##     ## 
    ###   ### ##       ###   ## ##     ## 
    #### #### ##       ####  ## ##     ## 
    ## ### ## ######   ## ## ## ##     ## 
    ##     ## ##       ##  #### ##     ## 
    ##     ## ##       ##   ### ##     ## 
    ##     ## ######## ##    ##  #######  
    */

    private void createService() throws IOException{
        out.println("\nName service:");
        String name = in.readLine();
        try{
            out.println("Enter starting date: (yyyy.MM.dd 'at' h:mm a:)");
            Date startDate = df.parse(in.readLine());
            out.println("Enter ending date: (yyyy.MM.dd 'at' h:mm a) ");
            Date endDate = df.parse(in.readLine());
            timeBank.createService(name, user, startDate, endDate);
            out.println("Creating service...\n");
        }catch(ParseException e){ out.println("Wrong date format... aborting!\n");}
        catch(WrongDateAndTimeExcpetion e){ out.println("starting date cannot be after ending date!\n");}
    }

    private void cancelService() throws IOException{
        try{
            out.println("\n*******\n"+timeBank.getUserServices(user)+"*******\n");
            out.println("Enter name of service you would like to cancel:");
            timeBank.cancelService(user, Integer.parseInt(in.readLine()));
            out.println("Canceling Service...\n");
        }catch(TimeBankExceptions e){out.println("You have no services!\n");}
        catch(NumberFormatException e){out.println("Need a number!\n");}
    }

    private void reserveService() throws IOException{
        try{
            out.println("\n*******\n"+timeBank.getAvaibleServices()+"*******\n");
            timeBank.reserveService(user, Integer.parseInt(in.readLine()));
        }catch(TimeBankExceptions e){
            if(e instanceof NoServicesException) out.println("No avaible services!");
            else out.println("No such a services!\n");}
        catch(NumberFormatException e){out.println("Need a number!\n");}
    }

    private void myServices(){
        try{
            out.println("\nMy services\n*******\n"+timeBank.getUserServices(user)+"*******\n");
        }catch(NoServicesException e){out.println("My services services\n*******\n" + "NO SERVICES" +"\n*******\n");}
        try{
            out.println("Reserved services\n*******\n"+timeBank.getUserReservedServices(user)+"*******\n");
        }catch(NoServicesException e){out.println("Reserved services\n*******\n" + "NO SERVICES" +"\n*******\n");}
    }


}