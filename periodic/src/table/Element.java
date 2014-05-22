package table;

public class Element {
	private String name;
	private String atomicNumber;
	private String symbol;
	private String metalGroup;
	
	
	Element() {
		this.name = "Bad Query";
		this.atomicNumber = "-1";
		this.symbol = "--";
		this.metalGroup = "BadQuery";
	}
	
	Element(String name, String atomicNumber, String symbol, String metalGroup) {
		this.name = name;
		this.atomicNumber = atomicNumber;
		this.symbol = symbol;
		this.metalGroup = metalGroup;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getAtomicNumber() {
		return atomicNumber;
	}

	public void setAtomicNumber(String atomicNumber) {
		this.atomicNumber = atomicNumber;
	}

	public String getMetalGroup() {
		return metalGroup;
	}

	public void setMetalGroup(String metalGroup) {
		this.metalGroup = metalGroup;
	}

}
