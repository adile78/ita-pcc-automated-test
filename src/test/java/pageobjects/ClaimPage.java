package pageobjects;

import java.util.Map;

public interface ClaimPage {

	void startClaimOpening();

	void setClaimFirstDetails(Map<String, String> claimValueMap);

	boolean isPlateBoxVisibile() ;

	void setPolicyAndCoverageType(Map<String, String> claimValueMap, String claimType);

	void secondStepClaimOpening(Map<String, String> claimValueMap) ;

	void setLastDetailsClaimOpening(Map<String, String> claimValueMap);

	void saveFinalClaim();

	boolean isClaimOpeningSucceded();
	
}
