package enums;

public enum RedemptionStatusType {

	TC("Taking Charge", "Il giorno la tua richiesta di riscatto è stata presa in carico."),
	GE("Getting Evaluated", "La tua richiesta di riscatto è in valutazione.Serve aiuto? Contattaci"),
	AP("Approved", "La tua richiesta di riscatto è andata a buon fine."),
	NA("Not Approved", "Abbiamo riscontrato dei problemi con la tua richiesta di riscatto.Serve aiuto? Contattaci"),
	GP("Getting Paid", "Stiamo disponendo il pagamento verso la tua banca."),
	PO("Paymento OK", "Pagamento effettuato con successo!"),
	PK("Paymento KO", "Abbiamo riscontrato un problema durante il pagamento.Serve aiuto? Contattaci");
	
	
	RedemptionStatusType(String value, String message){
		
		this.value = value;
		this.message = message;
	}
	
	String value;
	String message;
	
	public String getValue(){
		
		return value;
	}
	
	public String getMessage(){
		
		return message;
	}
	
}
