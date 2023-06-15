package pccpageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enums.CallToActionType;
import enums.PolicyCategoryType;
import enums.PolicyFieldLabelType;
import pageelementobjects.PolicyActionBox;
import pageelementobjects.PolicyInfoBox;
import pageobjects.DetailPolicy;
import pageobjects.ListPolicy;
import pccelementobjects.PccPolicyActionBox;
import pccelementobjects.PccPolicyInfoBox;
import utilities.WebElementUtil;

/**
 * @author barbetti_be
 *
 */
public class PccListPolicy implements ListPolicy {

	private WebDriver driver;
	protected static Logger logger = LogManager.getLogger();

	/**
	 * box with all policy info
	 */
	private WebElement policyListBox;

	protected PolicyInfoBox infoBox;
	protected PolicyActionBox actionBox;

	public PccListPolicy(WebDriver driver, WebElement policyListBox) {

		this.driver = driver;
		this.policyListBox = policyListBox;
		WebElementUtil.waitForPresenceOf(driver, By.className("box-main__element-list"));
		infoBox = new PccPolicyInfoBox(driver, policyListBox.findElement(By.className("box-main__element-list")));
		actionBox = new PccPolicyActionBox(driver, policyListBox.findElement(By.className("box-main__buttonbar")));
	}

	public boolean isRenewablePolicy() {

		List<WebElement> renewalBox = policyListBox.findElements(By.className("dashboard-alert-box-policy"));
		if (renewalBox.size() == 1)
			return true;
		return false;
	}

	@Override
	public PolicyActionBox getActionBox() {

		return actionBox;
	}

	@Override
	public PolicyInfoBox getInfoBox() {

		return infoBox;
	}

	@Override
	public PolicyCategoryType getCategoryType() {

		return infoBox.getCategoryType();
	}

	@Override
	public List<String> getRequiredFieldList() {

		List<String> requiredFieldList = new ArrayList<String>();
		requiredFieldList.add(PolicyFieldLabelType.NUMERO_POLIZZA.getValue());
		return requiredFieldList;
	}

	@Override
	public boolean checkListLabelsAndValues() {

		Map<String, String> policyFieldValues = infoBox.getPolicyFieldLabelValueList();
		List<String> requiredFieldList = getRequiredFieldList();
		for (String value : requiredFieldList) {
			if (!policyFieldValues.containsKey(value) || policyFieldValues.get(value) == null){
				logger.error("For product " + getInfoBox().getProductName() + " Field " + value + " not found");
				return false;
			}
		}

		return true;
	}

	@Override
	public List<String> getRequiredActionList() {

		List<String> requiredActionList = new ArrayList<String>();
		requiredActionList.add(CallToActionType.CONSULTA_POLIZZA.getValue());

		return requiredActionList;
	}

	@Override
	public boolean checkDashboardListCtas() {

		List<WebElement> policyActionValues = actionBox.getCtaList();
		List<String> actionTextList = WebElementUtil.getTextList(policyActionValues);
		List<String> requiredActionList = getRequiredActionList();
		for (String cta : requiredActionList) {
			if (!actionTextList.contains(cta)) {
				logger.error("For product " + getInfoBox().getProductName() + " CTA " + cta + " not found");
				return false;
			}
		}
		return true;
	}

	public DetailPolicy clickPolicyDetail() {
	
		String companyName = infoBox.getCompanyName();
		String productName = infoBox.getProductName();
		infoBox.clickPolicyDetail();
		return new PccDetailPolicy(driver, companyName, productName);
	}

	@Override
	public DetailPolicy clickCtaByType(String ctaType) {

		List<WebElement> ctaList = actionBox.getCtaList();
		for (WebElement cta : ctaList)
			if (ctaType.equals(cta.getText())){
				String companyName = infoBox.getCompanyName();
				String productName = infoBox.getProductName();
				cta.click();
				if (ctaType.equals(CallToActionType.STATO_RICHIESTA_RISCATTO.getValue())){
					return new PccDetailPolicy(driver, companyName, productName);					
				}
			}

		return null;
	}
	
	public boolean hasCta(String ctaType){
		List<WebElement> ctaList = getActionBox().getCtaList();
		for (WebElement cta: ctaList){
			if (ctaType.equals(cta.getText()))
				return true;
		}
		return false;
	}
	
	
	public boolean hasRedeemablePOBox(){

		//TODO
		return false;
	}
	
}
