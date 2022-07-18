package Database;

abstract class MyExceptions extends Exception{}


class WrongSyntaxException extends MyExceptions{
	String faultySyntax;

	WrongSyntaxException(String wrongQuery){
	faultySyntax = wrongQuery;
	}

	void tell(){
		System.out.println(faultySyntax + " is wrong syntax");
	}
}

class WrongNameException extends MyExceptions{
	String faultyName;

	WrongNameException(String wrongName){
		faultyName = wrongName;
	}

	void tell(){
		System.out.println(faultyName + " is wrong name");
	}
}

class NameTakenException extends MyExceptions{
	String faultyName;

	NameTakenException(String wrongName){
		faultyName = wrongName;
	}

	void tell(){
		System.out.println(faultyName + " is taken name");
	}
}

class WrongDataTypesException extends MyExceptions{
	String faultyType;
	String expectedType;

	WrongDataTypesException(String wrongType, String goodType){
		faultyType 		= wrongType;
		expectedType 	= goodType; 
	}

	void tell(){
		System.out.println("Wrong value type entered ->" + faultyType + " ,should be " + expectedType);
	}
}

class NoSelectionException extends MyExceptions{
	void tell(){
		System.out.println("No database or table selected for operation");
	}
}