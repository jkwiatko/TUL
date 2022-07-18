package zad2;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

class Save{
	
	static void Store(String args){
		String fileDir = "../files/params.txt";
		PrintWriter writer;

		try{
			writer = new PrintWriter(fileDir, "UTF-8");
		}catch(FileNotFoundException e){System.out.println("File Not Found"); return;}
		catch(UnsupportedEncodingException ex){System.out.println("Unsupported Encoding"); return;}

		writer.print(args);
   		writer.close();
	}
}
