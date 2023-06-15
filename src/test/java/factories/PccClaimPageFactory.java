package factories;

import org.openqa.selenium.WebDriver;

import enums.ClaimType;
import pageobjects.ClaimPage;
import pccpageobjects.PccKaskoClaimPage;

public class PccClaimPageFactory {

	public static ClaimPage getCategorizedListPolicy(WebDriver driver, String claimType) {

		ClaimPage categorizedPolicy = null;
		
		if (ClaimType.KASCO.getClaimType().equals(claimType))
			categorizedPolicy =  new PccKaskoClaimPage(driver);
		if (ClaimType.RCA.getClaimType().equals(claimType))
			categorizedPolicy =  new PccKaskoClaimPage(driver);
		
		return categorizedPolicy;

	}
	
}
