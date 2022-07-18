package zad1;
import java.util.concurrent.TimeUnit;;

class MainApp{
public static void main(String[] args) {
        long time;
		String fileDir = "../files/plik";
        String[] files = new String[5];
        files[0] = fileDir + "A.txt";
        files[1] = fileDir + "B.txt";
        files[2] = fileDir + "C.txt";
        files[3] = fileDir + "D.txt";
        files[4] = fileDir + "E.txt";
        
       //Single thread
       System.out.println("Single thread:");
        time = System.nanoTime();
        for(int i=0; i < 5; ++i){
            Reader.read(files[i]);
        }
        time = System.nanoTime() - time;
        System.out.println(TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS) + " miliseconds" + "\n");
        

        //Multi Thread
        System.out.println("Multi thread:");
        Reader[] watki = new Reader[5];
        for(int i =0 ; i<5; ++i)
            watki[i] = new Reader(files[i]);
        time = System.nanoTime();
        try{
	        for(int i=0; i < 5; ++i)
	            watki[i].run();
	        for(int i=0; i < 5; ++i)
	        	watki[i].join();
    	}
    	catch(InterruptedException e){System.out.println("InterruptedException");}
        time = System.nanoTime() - time;
        System.out.println(TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS) + " miliseconds" + "\n");
    }


}
