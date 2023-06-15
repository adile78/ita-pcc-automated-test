package enums;

public enum PrivacyType {

	PROMOZIONI("consensoComunicazioniPromoz", 1), 
	RICERCHE_MERCATO("consensoRicercheMercatoRilevStat", 3), 
	VENDITA_RICERCHE_MERCATO("consensoVenditaRicercheMercat", 5), 
	FIRMA_GRAFOMETRICA("consensoFirmaGrafometrica", 7);

	PrivacyType(String idPrivacy, Integer index) {
		
		this.idPrivacy = idPrivacy;
		this.index = index;
	}

	private String idPrivacy;
	private Integer index;

	public String getIdPrivacy() {
		return idPrivacy;
	}

	public Integer getIndex() {
		return index;
	}
}
