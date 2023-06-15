package pccpageobjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enums.RegistrationFieldType;
import pageobjects.GenericRegistrationPage;
import pageobjects.Modale;
import pccelementobjects.PccModale;
import utilities.PropsUtil;
import utilities.WebElementUtil;

public class PccRegistrationPage extends GenericRegistrationPage {

	private static String portletName = "_com_axa_portal_ita_pcc_LoginWebPortlet_";

	private By userNameConfirmationBy;
	private By personalDataPrivacyChkbk;
	private By securityChkbk;
	private By attivationLink;
	private By expLinkBy;
	private By personTypeChkbx;

	private By errorCfFieldBy;
	private By errorEmailFieldBy;
	private By errorReEmailFieldBy;

	private Modale alreadyRegisteredModale;
	private Modale badCredentialsModale;
	private Modale badCredentialsRegistrationModale;
	private Modale personalDataPrivacyModale;
	private Modale securityPrivacyModale;

	private By userToRetrievePswdBy;

	public PccRegistrationPage(WebDriver driver) {

		super(driver);

		userNameBy = By.id(portletName + "email");
		passwordBy = By.id(portletName + "pwd");
		fiscalCodeBy = By.id(portletName + "customerCode");
		vatNumberBy = By.id(portletName + "customerCode");
		policyNumberBy = By.id(portletName + "contractCode");
		personalDataPrivacyChkbk = By.id(portletName + "personalDataPrivacy");
		securityChkbk = By.id(portletName + "securityText");

		confirmBtnBy = By.id(portletName + "confirm");
		
		attivationLink = By.id(portletName + "confirmToken");

		passwordConfirmationBy = By.id(portletName + "repwd");
		userNameConfirmationBy = By.id(portletName + "reemail");

		// expLinkBy = By.id("axa_error_module");
		expLinkBy = By.className("alert-danger");
		personTypeChkbx = By.cssSelector(".radio-label");

		errorCfFieldBy = By.id(portletName + "customerCodeHelper");
		errorEmailFieldBy = By.id(portletName + "emailHelper");
		errorReEmailFieldBy = By.id(portletName + "reemailHelper");

		// modale
		String modaleId_alrReg = portletName + "user-exists-registration";
		alreadyRegisteredModale = new PccModale(driver, modaleId_alrReg, false);
		// modale
		String modaleId_badCred = portletName + "bad-credentials-view";
		badCredentialsModale = new PccModale(driver, modaleId_badCred, false);
		// modale
		String modaleId_badCredReg = portletName + "bad-credentials-registration";
		badCredentialsRegistrationModale = new PccModale(driver, modaleId_badCredReg, false);
		// TODO
		// modale
		String modaleId_personalDataPrivacy = portletName + "privacy-policy-not-checked";
		personalDataPrivacyModale = new PccModale(driver, modaleId_personalDataPrivacy, false);
		// modale
		String modaleId_securityPrivacy = portletName + "acnkowledgment-error";
		securityPrivacyModale = new PccModale(driver, modaleId_securityPrivacy, false);

		userToRetrievePswdBy = By.id(portletName + "username");
	}

	@Override
	public void setPerson(String personType) {
		List<WebElement> personTypeOptions = driver.findElements(personTypeChkbx);
		for (WebElement pt : personTypeOptions) {
			if (personType.equals(pt.getText()))
				pt.click();
		}
	}

	public void setUsernameConfirmation(String userNameConfirmation) {

		WebElement txbxUsernNameConfirmation = driver.findElement(userNameConfirmationBy);
		txbxUsernNameConfirmation.sendKeys(userNameConfirmation);
	}

	public void setPersonalDataCheckBox() {

		WebElement chkbxPersonalData = driver.findElement(personalDataPrivacyChkbk);
		chkbxPersonalData.click();
	}

	public void setSecurityCheckBox() {

		WebElement chkbxSecurity = driver.findElement(securityChkbk);
		chkbxSecurity.click();
	}

	public void clickAttivationLink() {

		WebElementUtil.waitForPresenceOf(driver, attivationLink);
		WebElement lnkAttivation = driver.findElement(attivationLink);
		lnkAttivation.click();
	}

	@Override
	public void setPrivacy() {

		setPersonalDataCheckBox();
		setSecurityCheckBox();
	}

	@Override
	public void setPersonalInformationPerson(Map<RegistrationFieldType, String> registrationParams) {

		setPerson(RegistrationFieldType.PERSON.getValue());

		setFiscalCode(registrationParams.get(RegistrationFieldType.FISCAL_CODE));
		setPolicyNumber(registrationParams.get(RegistrationFieldType.POLICY_NUMBER));
		setUsername(registrationParams.get(RegistrationFieldType.USERNAME));
		setUsernameConfirmation(registrationParams.get(RegistrationFieldType.REUSERNAME));
	}

	@Override
	public void firstStepRegistrationPerson(Map<RegistrationFieldType, String> registrationParams) {

		setPersonalInformationPerson(registrationParams);
		setPrivacy();
	}

	@Override
	public void secondStepRegistrationPerson(Map<RegistrationFieldType, String> registrationParams) {

		setPassword(registrationParams.get(RegistrationFieldType.PASSWORD));
		setPasswordConfirmation(registrationParams.get(RegistrationFieldType.PASSWORD));
		clickBtnConfirm();
	}

	@Override
	public void setPersonalInformationOrganization(Map<RegistrationFieldType, String> registrationParams) {

		setPerson(RegistrationFieldType.ORGANIZATION.getValue());

		setVatNumber(registrationParams.get(RegistrationFieldType.VAT_NUMBER));
		setPolicyNumber(registrationParams.get(RegistrationFieldType.POLICY_NUMBER));
		setUsername(registrationParams.get(RegistrationFieldType.USERNAME));
		setUsernameConfirmation(registrationParams.get(RegistrationFieldType.REUSERNAME));
	}

	@Override
	public void firstStepRegistrationOrganization(Map<RegistrationFieldType, String> registrationParams) {

		setPersonalInformationOrganization(registrationParams);
		setPrivacy();
	}

	@Override
	public void secondStepRegistrationOrganization(Map<RegistrationFieldType, String> registrationParams) {

		setPassword(registrationParams.get(RegistrationFieldType.PASSWORD));
		setPasswordConfirmation(registrationParams.get(RegistrationFieldType.PASSWORD));
		clickBtnConfirm();
	}

	public boolean isAlreadyRegisteredModaleVisible() {

		return alreadyRegisteredModale.isModaleVisible();
	}

	public boolean isBadCredentialsModaleVisible() {

		return badCredentialsModale.isModaleVisible();
	}

	public boolean isBadCredentialsRegistrationModaleVisible() {

		return badCredentialsRegistrationModale.isModaleVisible();
	}

	public boolean isPersonalDataPrivacyModaleVisible() {

		return personalDataPrivacyModale.isModaleVisible();
	}

	public boolean isSecurityPrivacyModaleVisible() {

		return securityPrivacyModale.isModaleVisible();
	}

	public boolean isExpiredLinkErrorVisible() {
		WebElement lnkExpired = driver.findElement(expLinkBy);
		return (lnkExpired != null ? lnkExpired.isDisplayed() : false);
	}

	public boolean isFiscalCodeError() {
		WebElement fiscalCode = driver.findElement(fiscalCodeBy);
		WebElement errorField = driver.findElement(errorCfFieldBy);
		return errorField.isEnabled() && WebElementUtil.hasClass(fiscalCode, PropsUtil.getPropertyValue("error.field"));
	}

	public boolean isVatNumberError() {

		WebElement vatNumber = driver.findElement(vatNumberBy);
		WebElement errorField = driver.findElement(errorCfFieldBy);
		return errorField.isEnabled() && WebElementUtil.hasClass(vatNumber, PropsUtil.getPropertyValue("error.field"));
	}

	public boolean isUsernameError() {

		WebElement username = driver.findElement(userNameBy);
		WebElement errorField = driver.findElement(errorEmailFieldBy);
		return errorField.isEnabled() && WebElementUtil.hasClass(username, PropsUtil.getPropertyValue("error.field"));
	}

	public boolean isReUsernameError() {

		WebElement reUsername = driver.findElement(userNameConfirmationBy);
		WebElement errorField = driver.findElement(errorReEmailFieldBy);
		return errorField.isEnabled() && WebElementUtil.hasClass(reUsername, PropsUtil.getPropertyValue("error.field"));
	}

	public boolean isUsernameConfirmationError() {

		WebElement usernameConfirmation = driver.findElement(userNameConfirmationBy);
		return WebElementUtil.hasClass(usernameConfirmation, PropsUtil.getPropertyValue("error.field"));
	}

	public boolean isPasswordConfirmationError() {

		WebElement passwordConfirmation = driver.findElement(passwordConfirmationBy);
		return WebElementUtil.hasClass(passwordConfirmation, PropsUtil.getPropertyValue("error.field"));
	}

}
