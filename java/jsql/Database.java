package Database;
import java.util.ArrayList;
import java.util.LinkedList;

class Database{
	String 				name;
	ArrayList<Table> 	avalibleTables = new ArrayList<Table>();
	Table 			 	selectedTable;
	int 				indexOfCurrentTable = -1;

	Database(String givenName){
		name = givenName;
	}
		
	void createTable(LinkedList<String> command) throws NameTakenException, WrongSyntaxException{
		System.out.println("craeting table...");
		if(lookForTable(command.peek()) == -1)
			avalibleTables.add(new Table(command.poll(),command));
		else throw new NameTakenException(command.poll());
	}
	
	void deleteTable(LinkedList<String> command) throws WrongNameException{
			String query = command.poll();
			System.out.println("deleting table " + query + "...");
			indexOfCurrentTable = lookForTable(query);
			if(indexOfCurrentTable == -1) throw new WrongNameException(query);
			avalibleTables.remove(selectedTable);
		}

	void insertIntoTable(LinkedList<String> command) throws MyExceptions{
		String query = command.poll();
		System.out.println("inserting into table..." + query);
		indexOfCurrentTable = lookForTable(query);
		if(indexOfCurrentTable == -1) throw new WrongNameException(query);
		selectedTable.insert(command);
		avalibleTables.set(indexOfCurrentTable, selectedTable);
	}
	
	void updateTable(LinkedList<String> command) throws MyExceptions{
		LinkedList<String> 	columnsToUpdate 	= new LinkedList<>();
		LinkedList<String> 	values 				= new LinkedList<>();
		String 				query 				= command.poll();
		int 				counter				= 0;

		System.out.println("updating table..." + query);
		indexOfCurrentTable = lookForTable(query);
		if(indexOfCurrentTable == -1) throw new WrongNameException(query);
		query = command.poll();
		if(!(query.equals("set"))) throw new WrongSyntaxException(query);

		while((query = command.poll()) != null){
			if(counter % 3 == 0)  columnsToUpdate.add(query);
			if(counter % 3 == 1)
				if(!(query.equals("="))) throw new WrongSyntaxException(query);
			if(counter % 3 == 2) values.add(query);
			++counter;
		}
		selectedTable.update(columnsToUpdate, values);
		avalibleTables.set(indexOfCurrentTable, selectedTable);		
	}

	void selectFromTable(LinkedList<String> command)throws MyExceptions{
		LinkedList<String> 	selectedColumns = new LinkedList<>();
		LinkedList<String> 	selecetedWhere = new LinkedList<>();
		String 				query;
		int 				counter = 0;
		int 				limit = 0;		

		for(int i = 0; i < command.size(); ++i){
			if(command.get(i).equals("from")) break;
			if(i == command.size() - 1) throw new WrongSyntaxException("no from");
		}
		
		while(!((query = command.poll()).equals("from")))
			selectedColumns.add(query);
		query = command.poll();
		System.out.println("selecting from table..." + query);
		indexOfCurrentTable = lookForTable(query);
		if(indexOfCurrentTable == -1) throw new WrongNameException(query);

		if((query = command.peek()) != null)
			if(query.equals("limit")){
				command.poll();
				limit = Integer.parseInt(command.poll());
			}
		
		if((query = command.poll()) != null)
			if(query.equals("where")){
				while(((query = command.poll())!= null)){
					if(counter % 3 == 0){
								if(query.equals("limit")){
									limit = Integer.parseInt(command.poll());
									break;
								}
						else selecetedWhere.add(query);
					}
					if(counter % 3 == 1)
						if(!(query.equals("="))) throw new WrongSyntaxException(query);
					if(counter % 3 == 2)
						selecetedWhere.add(query);
					++counter;
				}
			}
			else throw new WrongSyntaxException(query);
		selectedTable.select(selectedColumns, selecetedWhere, limit);
	}
	
	public boolean equals(Object givenObject){
		if(givenObject == this) return true;

		if (!(givenObject instanceof Database)) {
            return false;
        }

        Database givenDatabase = (Database)givenObject;
       return givenDatabase.name.equals(this.name);
	}

	
 	int lookForTable(String name){
		Table 	lookedTable = new Table(name);
		int 	indexOflookedTable;
		if((indexOflookedTable = avalibleTables.indexOf(lookedTable)) == -1)
			return -1;
		else 	
			selectedTable = avalibleTables.get(indexOflookedTable);
		return indexOflookedTable;
  	}

  	void checkIndex(String query)throws WrongNameException{
  		if(indexOfCurrentTable == -1) throw new WrongNameException(query);
  	}

  	void saveTable(){
  		avalibleTables.set(indexOfCurrentTable, selectedTable);
  	}
}
