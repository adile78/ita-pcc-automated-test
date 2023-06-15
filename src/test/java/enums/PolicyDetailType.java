package enums;

public enum PolicyDetailType {

	BENEFICIARI("Beneficiari"),
	DATI_VEICOLO("Dati veicolo"),
	DETTAGLI("Dettagli"),
	GARANZIE("Garanzie"),
	MOVIMENTI("Ultimi movimenti"),
	MOVIMENTI_RECENTI("Ultimi movimenti recenti"),
	PERFORMANCE_INVESTIMENTO("Performance investimento"),
	POLIZZA_IN_BREVE("Polizza in breve"),
	PROSSIMO_PREMIO("Prossimo premio"),
	RIPARTIZIONE_INVESTIMENTO("Ripartizione investimento"),
	SALDO_POSIZIONE("Saldo posizione"),
	STIMA_GAP_PREVIDENZA("Stima del tuo gap previdenziale"),
	STORICO_RISCATTI("Storico Riscatti"),
	ULTIMI_VERSAMENTI("Ultimi versamenti recenti");
	
	private PolicyDetailType(String value) {
		this.value = value;
	}
	
	private String value;
	
	public String getValue(){
		return value;
	}
}
