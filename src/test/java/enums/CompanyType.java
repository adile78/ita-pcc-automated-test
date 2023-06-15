package enums;

public enum CompanyType {

	AAI("AAI", "AXA Assicurazioni S.p.A."), 
	AMPS("AMPS", "AXA MPS"), 
	AMPS_VITA("AMPS", "AXA MPS Vita S.p.A."), 
	AMPS_DANNI("AMPS", "AXA MPS Danni S.p.A."), 
	AMPS_PERSONE_FISICHE("AMPS", "AXA MPS - Persone Fisiche"), 
	AMPS_PERSONE_GIURIDICHE("AMPS", "AXA MPS - Persone Giuridiche"), 
	AMF("AMF", "AXA AMF");

	CompanyType(String companyType, String companyName) {
		this.companyType = companyType;
		this.companyName = companyName;
	}

	private String companyType;
	private String companyName;

	public String getCompanyType() {
		
		return companyType;
	}

	public String getCompanyName() {
		
		return companyName;
	}
}
