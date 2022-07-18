package zad2;

class MainApp{
    public final static String FILENAME = "../filies/params.txt";

    public static void main(String[] args) {
	    if(args.length == 0){
	    	 System.out.println ("Command arguments not detected, please do run program with arguments\n");
	    	 return;	
	    }
		
		//Storing head
	    HTMLExtractor html = new HTMLExtractor(args[0]); 
		Save.Store(html.extractIp() +"\n"+ html.extractHead());

		//Priting rest
		System.out.println("Emails:");
		for (String email : html.extractEmails()) {
			System.out.println(email);
		}
		System.out.println("Links:");
		for (String link : html.extractLinks()) {
			System.out.println(link);
			
		}
		System.out.println("HTML");
	    System.out.println(html.showHTML());
	}

}
