package enums;

public enum ProfileFieldType {

	DATI_ACCESSO("Dati di accesso all'Area Clienti"),
	EMAIL("Username / Email:"),
	PASSWORD("Password:"),
	CELLULARE("Cellulare:"),
	DATI_CONTATTO("I tuoi dati di contatto"),
	CONSENSI("Consensi"),
	INFORMATIVA_AREA_CLIENTI("Informativa utilizzo Area Clienti"),
	INFORMATIVA_PROTEZIONE_DATI_AAI("Informativa Protezione Dati Personali AXA"),
	INFORMATIVA_PROTEZIONE_DATI_AMPS("Informativa Protezione Dati Personali AXA MPS"),
	INFORMATIVA_PROTEZIONE_DATI_AMF("Informativa protezione dati personali AXA MPS Financial");
	
	ProfileFieldType(String value){
		this.value = value;
	}
	
	private String value;
	
	public String getValue(){
		
		return value;
	}
}
