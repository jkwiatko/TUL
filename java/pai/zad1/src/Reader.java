package zad1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;	

class Reader extends Thread{
	int lines;
	String fileDir;

	Reader(String thefileDir){
		lines = 0;
		fileDir = thefileDir;
	}
	
	public void run(){
		try{
			BufferedReader reader = new BufferedReader(new FileReader(fileDir));
			while(reader.readLine() != null) ++lines;
			reader.close();
		}
		catch(FileNotFoundException e){System.out.println("FileNotFoundException");}
		catch(IOException ee){System.out.println("IOException");}
		System.out.println(lines + "\t"  + fileDir);
	}

	static void read(String fileDir){
		int staticReadedLines = 0;
		try{
			BufferedReader reader = new BufferedReader(new FileReader(fileDir));
			while(reader.readLine() != null) ++staticReadedLines;
			reader.close();
		}
		catch(FileNotFoundException e){System.out.println("FileNotFoundException");}
		catch(IOException ee){System.out.println("IOException");}
		System.out.println(staticReadedLines + "\t"  + fileDir);


	}
}