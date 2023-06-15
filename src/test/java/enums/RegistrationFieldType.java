package enums;

public enum RegistrationFieldType {
	
	USERNAME("Nome Utente"), 
	REUSERNAME(""), 
	PASSWORD(""), 
	FISCAL_CODE(""),  
	POLICY_NUMBER(""), 
	VAT_NUMBER(""),
	PERSON("Persona Fisica"),
	ORGANIZATION("Persona Giuridica");
	
	RegistrationFieldType(String value){
		this.value = value;
	}
	
	private String value;
	
	public String getValue(){
		return value;
	}
}
