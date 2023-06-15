package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.util.Integers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enums.PolicyDetailType;
import enums.RedemptionStatusType;
import utilities.WebElementUtil;

public abstract class LifeAndSavingsDetailPolicy extends GenericDetailPolicy{

	public LifeAndSavingsDetailPolicy(WebDriver driver, DetailPolicy policy) {
		
		super(driver, policy);
	}
	
	@Override
	public List<String> getBeneficiariValueDetails() {

		WebElement policyBox = policy.getBox(PolicyDetailType.BENEFICIARI.getValue());
		List<String> beneficiariDetails = WebElementUtil.getValueList(policyBox);
		return beneficiariDetails;
	}

	// solo AMPS x le savings
	public boolean checkBeneficiari(String numBeneficiari) {

		if (getBeneficiariValueDetails().size() != Integers.parseInt(numBeneficiari))
			return false;
		return true;
	}

	@Override
	public List<String> getMovementsHistoryDetails() {

		WebElement movementsBox = getBox(PolicyDetailType.MOVIMENTI_RECENTI.getValue());
		List<String> movementsHistoryList = WebElementUtil.getAccordionElementList(movementsBox);
		return movementsHistoryList;
	}
	
	private WebElement getRedemptionBox(){
		WebElement mainBox = policy.getPolicyDetailContainer();
		WebElement redemptionBox = mainBox.findElement(By.className("storico_riscatti"));
		return redemptionBox;
	}
	
	
	private List<WebElement> getRedemptionRequestElements(){

		WebElement redemptionHistoryBox = getRedemptionBox();
		List<WebElement> redemptionRequestList = redemptionHistoryBox.findElements(By.className("box-richiesta"));		
		return redemptionRequestList;				
	};
	
	private List<String> getRedemptionRequest(){
		
		List<WebElement> redemptionRequestList = getRedemptionRequestElements();
		List<String> redemptionRequest = new ArrayList<String>();
		for (WebElement request: redemptionRequestList){
			List<WebElement> rowList = request.findElements(By.className("row_riscatti"));
			for(WebElement row :rowList){
				if (("Stato richiesta:").equals(row.findElement(By.className("label_riscatti")).getText())){
					redemptionRequest.add((row.findElement(By.className("value")).getText()));
				}
			}
		}
		return redemptionRequest;				
	};
	
	public List<String> geRedemptionRequestHistoryDetails() {
		
		List<String>  redemptionHistoryList = getRedemptionRequest();
		return redemptionHistoryList;
	}
	
	
	/**
	 * @param redeemStatus it must be a RedeemStatusType
	 * @return
	 */
	public boolean checkReedemStatusMessage(String redeemStatus){
		
		WebElement boxTracking = policy.getPolicyDetailContainer().findElement(By.className("alert_riscatti"));
		WebElement description = boxTracking.findElement(By.className("description"));
		List<WebElement> helpList = boxTracking.findElements(By.className("help"));
		String help = (helpList.size() != 0) ? helpList.get(0).getText() : "";
		String message = description.getText() + help;
		// se stato Taking Charge
		String regexDate = "(\\d{1,2}\\/\\d{1,2}\\/\\d{4}) ";
		String messageNoDate = message.replaceAll(regexDate, "");
	
		return RedemptionStatusType.valueOf(redeemStatus).getMessage().equals(messageNoDate);
	}



}
