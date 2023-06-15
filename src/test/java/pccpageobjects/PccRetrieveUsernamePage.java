package pccpageobjects;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enums.RegistrationFieldType;
import pageobjects.GenericRegistrationPage;
import utilities.WebElementUtil;

public class PccRetrieveUsernamePage extends GenericRegistrationPage {

	protected static Logger logger = LogManager.getLogger();
	private static String portletName = "_com_axa_portal_ita_pcc_LoginWebPortlet_";

	private By switchBtnBy;
	private By emailListBy;
	private By boxEmailBy;

	public PccRetrieveUsernamePage(WebDriver driver) {

		super(driver);

		fiscalCodeBy = By.id(portletName + "customerCode");
		vatNumberBy = By.id(portletName + "customerCode");
		policyNumberBy = By.id(portletName + "contractCode");

		switchBtnBy = By.className("slider");
		emailListBy = By.className("spanEmail");
		confirmBtnBy = By.id(portletName + "confirm");
		boxEmailBy = By.tagName("b");

	}

	@Override
	public void setPerson(String personType) {

		WebElement switchBtn = driver.findElement(switchBtnBy);
		switchBtn.click();
	}

	@Override
	public void setPersonalInformationPerson(Map<RegistrationFieldType, String> registrationParams) {

		setFiscalCode(registrationParams.get(RegistrationFieldType.FISCAL_CODE));
		setPolicyNumber(registrationParams.get(RegistrationFieldType.POLICY_NUMBER));
	}

	@Override
	public void setPersonalInformationOrganization(Map<RegistrationFieldType, String> registrationParams) {

		setPerson(RegistrationFieldType.ORGANIZATION.getValue());
		setVatNumber(registrationParams.get(RegistrationFieldType.VAT_NUMBER));
		setPolicyNumber(registrationParams.get(RegistrationFieldType.POLICY_NUMBER));
	}

	public boolean checkEmail() {

		WebElementUtil.waitForPresenceOf(driver, boxEmailBy);
		WebElement boxEmail = driver.findElement(boxEmailBy);
		return boxEmail == null ? false : true;
	}

	@Override
	public void firstStepRegistrationPerson(Map<RegistrationFieldType, String> registrationParams) {

		setPersonalInformationPerson(registrationParams);
	}

	@Override
	public void secondStepRegistrationPerson(Map<RegistrationFieldType, String> registrationParams) {

		WebElementUtil.waitForPresenceOf(driver,
				By.cssSelector("span[emailvalue=\"" + registrationParams.get(RegistrationFieldType.USERNAME) + "\"]"));
		List<WebElement> emailList = driver.findElements(
				By.cssSelector("span[emailvalue=\"" + registrationParams.get(RegistrationFieldType.USERNAME) + "\"]"));
		if (emailList.get(0) != null)
			emailList.get(0).click();
	}

	@Override
	public void firstStepRegistrationOrganization(Map<RegistrationFieldType, String> registrationParams) {

		setPersonalInformationOrganization(registrationParams);
	}

	@Override
	public void secondStepRegistrationOrganization(Map<RegistrationFieldType, String> registrationParams) {

		WebElementUtil.waitForPresenceOf(driver,emailListBy);
		List<WebElement> emailList = driver.findElements(emailListBy);
		// By.cssSelector("span[emailvalue=\"" +
		// registrationParams.get(RegistrationFieldType.USERNAME) + "\"]"));
		if (emailList.get(0) != null)
			emailList.get(0).click();
	}

	@Override
	public void setPrivacy() {
		// doNothing
	}
}
