package enums;

public enum RedemptionValueType {

	TIPOLOGIA_POLIZZA("Tipologia Polizza:"),
	N_POLIZZA("N° Polizza:"),
	IMPORTO("Importo:"),
	TIPO_RISCATTO("Tipo Riscatto:"),
	IBAN("Iban:");
	
	RedemptionValueType(String value){
		this.value = value;
	}
	
	
	private String value;
	
	public String getValue(){
		return value;
	}
}
