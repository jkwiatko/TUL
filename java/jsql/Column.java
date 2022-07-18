package Database;
import java.util.ArrayList;


class Column <T>{
	String name;
	ArrayList<T> data = new ArrayList<>();
	int serchedIndex  = -1;

	Column(String givneName){
		name = givneName;
	}

	void addEntry(T entry){
		data.add(entry);
	}

	void selectEverything(int limit){
		ArrayList<Integer> filter = new ArrayList<Integer>();
		filter = giveFiltr(limit);
		for(int i = 0; i < filter.size(); ++i)
				System.out.printf("%-15.15s", data.get(filter.get(i)));
		System.out.print("\n");
	}
	
	void replaceEverything(T entry){
		int size = data.size();
		for(int i=0; i < size; ++i)
			data.set(i, entry);
	}

	void select(ArrayList<Integer> filter){
		for(int i = 0; i < filter.size(); ++i)
				System.out.printf("%-15.15s", data.get(filter.get(i)));
		System.out.print("\n");
	}

	void replace(T entry, T selectionKey){
	}

	ArrayList<Integer> giveFiltr(T key, int limit){
		ArrayList<Integer> filter = new ArrayList<Integer>();
		
		for(int i = 0; i < data.size(); ++i)
			if(key.equals(data.get(i)))
				filter.add(i);

		int indexRange = filter.size() - 1;
		if(limit != 0 || limit > filter.size())
			for(int i = indexRange; i >= limit; --i)
				filter.remove(i);
			
		return filter;
	}


	ArrayList<Integer> giveFiltr(int limit){
		ArrayList<Integer> filter = new ArrayList<Integer>();

		for(int i = 0; i < data.size() ; ++i)
				filter.add(i);

		int indexRange = filter.size() - 1;
		if(limit != 0 || limit > filter.size())
			for(int i = indexRange; i >= limit; --i)
				filter.remove(i);
			
		return filter;
	}


	public boolean equals(Object givenObject){
		if(givenObject == this) return true;
		if (!(givenObject instanceof Column)){
            return false;
        }
        Column givenColumn = (Column)givenObject;
        return givenColumn.name.equals(this.name);
	}
}


