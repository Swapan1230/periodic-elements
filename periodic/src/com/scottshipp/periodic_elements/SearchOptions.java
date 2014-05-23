package com.scottshipp.periodic_elements;

public enum SearchOptions {
	NAME("Element"), ATOMIC_NUMBER("AtomicNumber"), SYMBOL("Symbol"), METAL_GROUP("MetalGroup");
	
	private String databaseColumnName;
	
	private SearchOptions(String databaseColumnName) {
		this.databaseColumnName = databaseColumnName;
	}
	
	public String getColumnName() {
		return this.databaseColumnName;
	}
	
	public static SearchOptions getOptionFromString(String value) {
		for(SearchOptions s : SearchOptions.values()) {
			String item = s.getColumnName();
			if(item.equalsIgnoreCase(value)) {
				return s;
			}
		}
		return NAME;
	}
	
	public static boolean contains(SearchOptions value) {
		for(SearchOptions s : SearchOptions.values()) {
			if(s.equals(value)) {
				return true;
			}
		}
		return false;
	}
}
