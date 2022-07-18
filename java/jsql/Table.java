package Database;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.regex.*;

class Table{
	ArrayList<Column> 	storedData 	= new ArrayList<Column>();
	LinkedList<String>	storedTypes = new LinkedList<String>();
	String				nameOfField;
	String				createdType;
	String				name;
	Column 				selectedColumn;
	int 				indexOfCurrentColumn = -1;
	
	
	Table(String givenName, LinkedList<String> types) throws WrongSyntaxException, NameTakenException{
		name = givenName;
		//!brak zabezpieczenia!
		removeSyntaxDelimeters(types);

		while((createdType = types.poll()) != null){ //dodawanie kolumn  
			if(lookForColumn(types.peek(),storedData) != -1) throw new NameTakenException(types.peek());
			switch(createdType){
				case "String"	:
					nameOfField = types.poll();
					storedData.add(new Column<String>(nameOfField));
					storedTypes.add("String");
				break;
				case "Double"	:
					nameOfField = types.poll();
					storedData.add(new Column<Double>(nameOfField));
					storedTypes.add("Double");
				break;
				case "Boolean"	:
					nameOfField = types.poll();
					storedData.add(new Column<Boolean>(nameOfField));
					storedTypes.add("Boolean");
				break;
				case "Integer"	:
					nameOfField = types.poll();
					storedData.add(new Column<Integer>(nameOfField));
					storedTypes.add("Integer");
				break;
				default			:
					throw new WrongSyntaxException(createdType);
			}
		}
		System.out.println("creating table complete");

	}

	Table(String givenName){
		name = givenName;
	}	

	@SuppressWarnings("unchecked") //blokowanie warrningow ostrzegajacych o (mozlisowsci) niezgodnosci danych
	void insert(LinkedList<String> insertCommand) throws WrongSyntaxException, WrongDataTypesException{
		LinkedList<String> values  = new LinkedList<String>();
		String query;
		String dataType;

		if(!((query = insertCommand.poll()).equals("values"))) 
			throw new WrongSyntaxException(query);

		else{
			removeSyntaxDelimeters(insertCommand);
			for(int i = 0; i < storedTypes.size(); ++i){
				query = insertCommand.poll();
				dataType = checkString(query);
				if(dataType.equals(storedTypes.get(i)))
					switch(dataType){
						case "String"	:
							storedData.get(i).addEntry(query);
						break;
						case "Double"	:
							storedData.get(i).addEntry(Double.parseDouble(query));
						break;
						case "Boolean"	:
							storedData.get(i).addEntry(Boolean.parseBoolean(query));
						break;
						case "Integer"	:
							storedData.get(i).addEntry(Integer.parseInt(query));
						break;
						default			:
							throw new WrongSyntaxException(createdType);
					}
				else throw new WrongDataTypesException(dataType,storedTypes.get(i));
			}
		}
	}

	@SuppressWarnings("unchecked") //blokowanie warrningow ostrzegajacych o (mozlisowsci) niezgodnosci danych
	void update(LinkedList<String> columnsToUpdate, LinkedList<String> values) throws MyExceptions{
		String value, column, dataType;
	removeSyntaxDelimeters(values);

		System.out.println(columnsToUpdate);
		System.out.println(values);
		if(columnsToUpdate.size() != values.size()) throw new WrongSyntaxException("size");
		
		//sprwadzanie typow
		for(int i = 0; i < values.size(); i++){
			dataType = checkString(values.get(i));
			if(!(dataType.equals(storedTypes.get(i)))) throw new WrongDataTypesException(dataType, storedTypes.get(i));
		}
		
		while((column = columnsToUpdate.poll()) != null){
			indexOfCurrentColumn = lookForColumn(column, storedData);
			if(indexOfCurrentColumn == -1) throw new WrongNameException(column);
			storedData.get(indexOfCurrentColumn).replaceEverything(values.poll());
		}
	}

	void select(LinkedList<String> selectCommand, LinkedList<String> where, int limit) throws MyExceptions{
		String query = selectCommand.peek();
		ArrayList<Column> viewOfColumns = new ArrayList<>(); 

		if(query.equals("*")){
			selectChosen(storedData, where, limit);
		}


		else{
			removeSyntaxDelimeters(selectCommand);
			while((query = selectCommand.poll()) != null){
				indexOfCurrentColumn = lookForColumn(query, storedData);
				if (indexOfCurrentColumn == -1) throw new WrongNameException(query);
				viewOfColumns.add(selectedColumn);
			}
			selectChosen(viewOfColumns, where, limit);
		}

	}

	public boolean equals(Object givenObject){
		if(givenObject == this) return true;
		if (!(givenObject instanceof Table)){
            return false;
        }
        Table givenTable = (Table)givenObject;
        return givenTable.name.equals(this.name);
	}



/*
 #     #                                        
 #     # ##### # #      # ##### # ######  ####  
 #     #   #   # #      #   #   # #      #      
 #     #   #   # #      #   #   # #####   ####  
 #     #   #   # #      #   #   # #           # 
 #     #   #   # #      #   #   # #      #    # 
  #####    #   # ###### #   #   # ######  ####  
 */                                              


	void removeSyntaxDelimeters(LinkedList<String> command){
		//usuwanie nawiasow zawartch w syntaxie
		String 	temp;
		temp = command.getLast().replace(")","");;
		command.set((command.size()-1), temp.replace(")",""));
		temp = command.getFirst();
		command.set(0, temp.replace("(",""));

		for(int i = 0; i < command.size(); ++i){ //usuwanie przecinkow zawartch w syntaxie
			temp = command.get(i);
			command.set(i,temp.replace(",",""));
		}
	}


	String checkString(String stringToChek){
		String 	matchDobule 	= "\\d+.\\d+|\\d+";
		String 	matchInteger 	= "\\d+";
		String 	matchBoolean	= "true|false";

		if(stringToChek.matches(matchDobule)){
			if(stringToChek.matches(matchInteger)){
				return "Integer";
			}
				
			else{
				return "Double";
			}
				
		}
		else if(stringToChek.matches(matchBoolean)){
			return "Boolean";
		}
		else{
			return "String";
		}
	}

	int lookForColumn(String name, ArrayList<Column> storedData){
		Column 	lookedColumn = new Column(name);
		int 	indexOflookedColumn;
		if((indexOflookedColumn = storedData.indexOf(lookedColumn)) == -1)
			return -1;
		else 	
			selectedColumn = storedData.get(indexOflookedColumn);
		return indexOflookedColumn;
	}

	@SuppressWarnings("unchecked") //blokowanie warrningow ostrzegajacych o (mozlisowsci) niezgodnosci danych
	void selectChosen(ArrayList<Column> what, LinkedList<String> whereCommand,int limit) throws MyExceptions{
		//System.out.println(whereCommand);

		ArrayList<Integer> filter;
		String columnName, columnKey, keyType;
		if((whereCommand.peekFirst()) != null){

			columnName = whereCommand.poll();
			columnKey = whereCommand.poll();

			indexOfCurrentColumn = lookForColumn(columnName, what);
			if(indexOfCurrentColumn == -1) throw new WrongNameException(columnName);
			keyType = checkString(columnKey);
			if(!(keyType.equals(storedTypes.get(indexOfCurrentColumn))))	throw new  WrongDataTypesException(columnKey, storedTypes.get(indexOfCurrentColumn));
				switch(keyType){
						case "String"	:
							filter = selectedColumn.giveFiltr(columnKey, limit);
						break;
						case "Double"	:
							filter = storedData.get(indexOfCurrentColumn).giveFiltr(Double.parseDouble(columnKey), limit);
						break;
						case "Boolean"	:
							filter = storedData.get(indexOfCurrentColumn).giveFiltr(Boolean.parseBoolean(columnKey), limit);
						break;
						case "Integer"	:
							filter = storedData.get(indexOfCurrentColumn).giveFiltr(Integer.parseInt(columnKey), limit);
						break;
						default			:
							throw new WrongSyntaxException(createdType);
					}

					for(int i = 0; i < what.size(); ++i)
						what.get(i).select(filter);

		}

		else	for(int i = 0; i < what.size(); ++i) 
					what.get(i).selectEverything(limit); //zoptymalizowac
	}
}
	