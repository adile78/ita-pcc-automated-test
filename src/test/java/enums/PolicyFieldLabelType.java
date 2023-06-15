package enums;

public enum PolicyFieldLabelType {

	ASSICURATO("Assicurato:"),
	ASSICURATI("Assicurati:"),
	CONTRAENTI("Contraenti:"),
	CONTRAENTE("Contraente:"),
	CONTRATTA_IL("Contratta il:"),
//	COPERTA_FINO("Copertura valida fino al:"),
	DATA_AGGIORNAMENTO("Data di aggiornamento:"),
	DATA_DECORRENZA("Data decorrenza:"),
//	DATA_FINE_COPERTURA("Data fine copertura:"),
	DATA_ISCRIZIONE_FONDO("Data di iscrizione al fondo:"),
	DATA_PRIMA_ISCRIZIONE("Data di prima iscrizione alla previdenza complementare:"),
	DATA_SCADENZA("Data scadenza:"),
	DATA_ULTIMO_MOVIMENTO("Data ultimo movimento:"),
	DATA_ULTIMO_VERSAMENTO("Data ultimo versamento:"),
	DATA_VALORIZZAZIONE("Data valorizzazione:"),
	FRAZIONAMENTO("Frazionamento:"),
	IMMATRICOLATO_IL("Immatricolato il:"),
	IMPORTO("Importo:"),
	IMPORTO_RATA("Importo rata:"),
	NUMERO_POLIZZA("Polizza n:"),	
	NUMERO_POLIZZA_LISTA("Polizza n.:"),	
	//PERIODICITA_PREMIO("Periodicità premio:"),	
	PERIDICITA_VERSAMENTO("Periodicità versamento:"),
	PIANO_INVESTIMENTO("Piano investimento: % investita in fondi:"),	
	PREMIO("Premio:"),	
	PREMIO_ANNUO("Premio annuo:"),	
	PREMIO_TOTALE_PAGATO("Premio totale pagato:"),	
	PREMI_VERSATI("Premi versati al netto dei riscatti:"),	
	PREMIO_VERSATO("Premio versato:"),	
	SALDO_POSIZIONE("Saldo posizione:"),	
	STATO_VERSAMENTO("Stato del versamento:"),
	TARGA("Targa:"),
	TIPO_PREMIO("Tipo premio:"),
	TOTALE_CONTRIBUTI_VERSATI("Totale contributi versati:"),
	TOTALE_VERSAMENTI("Totale versamenti:"),
	ULTIMO_VERSAMENTO("Ultimo versamento:"),
	VALIDITA("Polizza valida dal"),
	VALORE_LORDO_POLIZZA("Valore lordo di Polizza:"),
	VEICOLO("Veicolo:");
	
	private PolicyFieldLabelType(String value) {
		 this.value = value;
	}
	
	private String value;
	
	public String getValue(){
		
		return value;
	}
	
/*	public List<String> getDatiVeicoloFields(){
		
		List<String> datiVeicoloField = new ArrayList<String>();
		datiVeicoloField.add(VEICOLO.value);
		datiVeicoloField.add(TARGA.value);
		datiVeicoloField.add(IMMATRICOLATO_IL.value);
		return datiVeicoloField;
	}
	
	public List<String> getUltimiMovimentiFields(){
		
		List<String> ultimiMovimentiField = new ArrayList<String>();
		ultimiMovimentiField.add(DATA_ULTIMO_MOVIMENTO.value);
		ultimiMovimentiField.add(IMPORTO.value);
		return ultimiMovimentiField;
	}*/
		
	
	
}
