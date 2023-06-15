package enums;

public enum ClaimType {

	AUTO("Sinistro Auto"),
	CASA("Sinistro Casa"),
	INFORTUNI("Sinistro Infortuni"),
	ALTRI_SINISTRI("Altri Sinistri"),
	KASCO("kasco"),
	RCA("RCA");
	
	ClaimType(String claimType){
		
		this.claimType = claimType;
	}
	
	String claimType;
	
	public String getClaimType(){
		
		return claimType;
	}
}
