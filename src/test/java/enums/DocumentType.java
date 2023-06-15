package enums;

public enum DocumentType {

	MODULO_CAI("modulo_cai"),
	PATENTE("patente"),
	CARTA_IDENTITA("carta_identita"),
	DOCUMENT_PATH("documentPathList");
	
	DocumentType(String value){
		
		this.value = value;
	}
	
	String value;
	
	public String getValue(){
		
		return value;
	}
	
}
