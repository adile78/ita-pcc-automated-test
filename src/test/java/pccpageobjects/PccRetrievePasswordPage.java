package pccpageobjects;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enums.RegistrationFieldType;
import pageobjects.GenericRegistrationPage;
import pccelementobjects.PccModale;
import utilities.WebElementUtil;

public class PccRetrievePasswordPage extends GenericRegistrationPage{

	private static String portletName = "_com_axa_portal_ita_pcc_LoginWebPortlet_";

	protected static Logger logger = LogManager.getLogger();

	private PccModale notExistingMailModale;
	private By attivationLink;
	
	public PccRetrievePasswordPage(WebDriver driver) {
		
		super(driver);
		
		userNameBy = By.id(portletName + "username");
		passwordBy = By.id(portletName + "pwd");
		passwordConfirmationBy = By.id(portletName + "repwd");

		confirmBtnBy = By.id(portletName + "confirm");
		attivationLink = By.id(portletName + "confirmToken");
				
		// modale
		String modaleId_notExistMail = portletName + "recover-user-insert-username";
		notExistingMailModale = new PccModale(driver, modaleId_notExistMail, false);
	}
	
	public void clickAttivationLink() {

		WebElementUtil.waitForPresenceOf(driver, attivationLink);
		WebElement lnkAttivation = driver.findElement(attivationLink);
		lnkAttivation.click();
	}

	@Override
	public void setPersonalInformationPerson(Map<RegistrationFieldType, String> registrationParams) {
		
		setUsername(registrationParams.get(RegistrationFieldType.USERNAME));
	}

	@Override
	public void setPersonalInformationOrganization(Map<RegistrationFieldType, String> registrationParams) {

		setUsername(registrationParams.get(RegistrationFieldType.USERNAME));
	}

	@Override
	public void firstStepRegistrationPerson(Map<RegistrationFieldType, String> registrationParams) {

		setPersonalInformationPerson(registrationParams);
	}

	@Override
	public void secondStepRegistrationPerson(Map<RegistrationFieldType, String> registrationParams) {

		setPassword(registrationParams.get(RegistrationFieldType.PASSWORD));
		setPasswordConfirmation(registrationParams.get(RegistrationFieldType.PASSWORD));
		clickBtnConfirm();
	}

	@Override
	public void firstStepRegistrationOrganization(Map<RegistrationFieldType, String> registrationParams) {
		
		setPersonalInformationOrganization(registrationParams);
	}

	@Override
	public void secondStepRegistrationOrganization(Map<RegistrationFieldType, String> registrationParams) {

		setPassword(registrationParams.get(RegistrationFieldType.PASSWORD));
		setPasswordConfirmation(registrationParams.get(RegistrationFieldType.PASSWORD));
		clickBtnConfirm();
	}
	
	@Override
	public void setPerson(String personType) {
		// doNothing		
	}
	
	@Override
	public void setPrivacy() {
		//doNothing
	}
	
	public boolean isNotExistingMailModaleVisible() {

		return notExistingMailModale.isModaleVisible();
	}

	
}
