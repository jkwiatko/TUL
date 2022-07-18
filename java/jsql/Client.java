package Database;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;

class Client{
	LinkedList  <Database> avalibleDatabases 	= new LinkedList<>();
	LinkedList 	<String> commands 				= new LinkedList<>();
	LinkedList 	<String> commandToDo;
	Scanner 	input;
	String 		query;
	Database 	currentDatabase;
	int 		currentDatabaseIndex = -1; // -1 means currentDatabase is unavalibe


	Client(){
		//Przkladowa baza 
		enter("create database bazakontaktow");
		enter("create table kontakty (String name, ,String lastname, Integer Tel)");
		enter("insert into kontakty values (Zennon, Krzy, 501128315)");
		enter("insert into kontakty values (Zennon, Bialy, 501128316)");
		enter("insert into kontakty values (Zennon, Czarny, 501128314)");
		enter("insert into kontakty values (Zofia, Czynny, 501128317)");
		enter("insert into kontakty values (Krzysztof, Znawca, 501128318)");
		enter("insert into kontakty values (Zygmunt, Krok, 501128319)");
		
		while(true){
			refresh();
		}	
	}

	void refresh(){
		commandPrompt();
		read();
		try{
			doIt(commandToDo);
		}
		catch(WrongSyntaxException e){
			e.tell();
		}
		catch(WrongNameException ee){
			ee.tell();
		}
		catch(NameTakenException eee){
			eee.tell();
		}
		catch(WrongDataTypesException eeee){
			eeee.tell();
		}

		catch(NoSelectionException eeeee){
			eeeee.tell();
		}
		catch(MyExceptions eeeeee){
		}
	}
			
	void commandPrompt(){
		System.out.print("javasql>");
	}
	
	void read(){	
			input = new Scanner(System.in);
			commands.add(input.nextLine());
			commandToDo	= new LinkedList<String>(Arrays.asList(commands.getLast().split(" ")));
	}

	void enter(String command){
		commands.add(command);
		commandToDo	= new LinkedList<String>(Arrays.asList(commands.getLast().split(" ")));
		try{
			doIt(commandToDo);
		}
		catch(WrongSyntaxException e){
			e.tell();
		}
		catch(WrongNameException ee){
			ee.tell();
		}
		catch(NameTakenException eee){
			eee.tell();
		}
		catch(WrongDataTypesException eeee){
			eeee.tell();
		}
		catch(NoSelectionException eeeee){
			eeeee.tell();
		}
		catch(MyExceptions eeeeee){
		}
	}

	void doIt(LinkedList<String> command) throws MyExceptions{
		switch(query = command.poll()){
			case "use" 		:
				currentDatabaseIndex = switchToDatabase(command.poll());
			break;
			case "create"	:  
				query = command.poll();
				if(query.equals("database")) createDatabase(command.poll());
			 	else if(query.equals("table")){
			 		if(currentDatabaseIndex == -1) throw new NoSelectionException();
					currentDatabase.createTable(command);
			 	}
				else throw new WrongSyntaxException(query);
			break;
			case "delete"	:
				query = command.poll();
				if(query.equals("table")) currentDatabase.deleteTable(command);
				else if(query.equals("database")) 
					System.out.println("i will remove db...soon");
				else throw new WrongSyntaxException(query);
			break;
			case "insert"	:
				if(currentDatabaseIndex == -1) throw new NoSelectionException();
				query = command.poll();
				if(query.equals("into")) currentDatabase.insertIntoTable(command);
				else throw new WrongSyntaxException(query);
			break;
			case "update"	:
				currentDatabase.updateTable(command);
			break;
			case "select"	:
				if(currentDatabaseIndex == -1) throw new NoSelectionException();
				currentDatabase.selectFromTable(command);
			break;
			default			:
				throw new WrongSyntaxException(query);
		}
		save();
	}

	void createDatabase(String name) throws NameTakenException{
		System.out.println("creating database " + name + "...");
		Database newDatabase = new Database(name);
		if(avalibleDatabases.contains(newDatabase)) throw new NameTakenException(name);
		else{
			currentDatabase = newDatabase;
			avalibleDatabases.add(currentDatabase);
			currentDatabaseIndex = avalibleDatabases.size() - 1;
			System.out.println(name + "Database craeted sucesfull");
		}
	}

	int switchToDatabase(String name) throws WrongNameException{
		Database 	lookedDB = new Database(name);
		int 		indexOflooekdDB;

		if((indexOflooekdDB = avalibleDatabases.indexOf(lookedDB)) == -1)
			throw new WrongNameException(name);
		else currentDatabase = avalibleDatabases.get(indexOflooekdDB);
		System.out.println("using database " + currentDatabase.name + "...");
		return indexOflooekdDB;
	}

	void save(){
		avalibleDatabases.set(currentDatabaseIndex, currentDatabase);
	}

	public static void main(String[] args){
		new Client();
	}
}
