package enums;

public enum RedemptionType {

	PARTIAL("partial", "Riscatto parziale"),
	TOTAL("total", "Riscatto totale");
	
	RedemptionType(String value, String longValue) {

		this.value = value;
		this.longValue = longValue;
	}
	
	String value;
	String longValue;
	
	public String getValue(){
		
		return value;
	}
	
	public String getLongValue(){
		
		return longValue;
	}
	
}
