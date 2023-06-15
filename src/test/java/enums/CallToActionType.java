package enums;

public enum CallToActionType {

	APRI_SINISTRO("Apri un sinistro"),
	BLACKBOX("La tua BlackBox"),	
	CONSULTA_POLIZZA("Consulta la tua polizza"),
	CONTATTA_AGENZIA("Contatta la tua Agenzia"),
	RISCATTA_POLIZZA("Riscatta Polizza"),
	RINNOVA("Rinnova"),
	SCARICA_ATTESTATO_RISCHIO("Scarica il tuo attestato rischio"),
	STATO_RICHIESTA_RISCATTO("Stato Richiesta Riscatto"),
	VISUALIZZA_SINISTRI("Visualizza i tuoi sinistri");
	
	CallToActionType(String value){
		
		this.value = value;
	}
	
	private String value;
	
	public String getValue(){
		return value;
	}
	
	
}
