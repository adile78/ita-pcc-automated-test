package enums;

public enum EventCategoryType {

	EVENT_DESCRIPTION("eventDescription"),
	IBAN("iban");
	
	EventCategoryType(String value){
		
		this.value = value;
	}
	
	String value;
	
	public String getValue(){
		
		return value;
	}
}
