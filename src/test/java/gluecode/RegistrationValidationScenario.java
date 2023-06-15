package gluecode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import driverManager.WebDriverContainer;
import enums.RegistrationFieldType;
import pccpageobjects.PccRegistrationPage;
import utilities.InputPropsUtil;

public class RegistrationValidationScenario extends PccScenario implements En {

	private PccRegistrationPage registrationPage;

	public RegistrationValidationScenario(WebDriverContainer webDriverContainer) {
		super(webDriverContainer);
		registrationPage = new PccRegistrationPage(webDriverContainer.getWebDriver());

		/**
		 * Scenario: User gets blocked when he does not insert a correct fiscal
		 * code
		 */

		When("user inserts an incorrect fiscal code {string} in the registration page", (String fiscalCode) -> {
			registrationPage.setPerson(RegistrationFieldType.PERSON.getValue());
			registrationPage.setFiscalCode(InputPropsUtil.getPropertyValue(fiscalCode));
			registrationPage.setPolicyNumber("");
		});

		Then("a fiscal code not correct warning message appears", () -> {
			Assert.assertTrue(registrationPage.isFiscalCodeError());
		});

		/**
		 * Scenario: User gets blocked when he does not insert a correct vat
		 * number
		 */

		When("user inserts an incorrect vat number {string} in the registration page", (String vatNumber) -> {
			registrationPage.setPerson(RegistrationFieldType.ORGANIZATION.getValue());
			registrationPage.setVatNumber(InputPropsUtil.getPropertyValue(vatNumber));
			registrationPage.setPolicyNumber("");
		});

		Then("a vat number not correct warning message appears", () -> {
			Assert.assertTrue(registrationPage.isVatNumberError());
		});

		/**
		 * Scenario: User gets blocked beacuse he inserts a not valid value for
		 * username
		 */

		When("user inserts a not valid value for {string}", (String username) -> {
			registrationPage.setUsername(InputPropsUtil.getPropertyValue(username));
			registrationPage.setPolicyNumber("");
		});

		Then("a not valid email addresses warning message appears", () -> {
			Assert.assertTrue(registrationPage.isUsernameError());
		});

		/**
		 * Scenario: User gets blocked when he inserts different value for
		 * username and username confermation fields
		 */
		
		When("user inserts different value for {string} and {string}",
				(String username, String usernameConfirmation) -> {
					registrationPage.setUsername(InputPropsUtil.getPropertyValue(username));
					registrationPage.setUsernameConfirmation(InputPropsUtil.getPropertyValue(usernameConfirmation));
					registrationPage.setPolicyNumber("");
				});

		Then("a not equal email addresses warning message appears", () -> {
			Assert.assertTrue(registrationPage.isUsernameConfirmationError());
		});

		/**
		 * Scenario: User can not register to PCC as a person because he
		 * did not set info privacy
		 */

		When("user enters all required person registration fields {string} {string} {string} {string} in the registration page",
				(String vatNumber, String policyNumber, String userName, String reUserName) -> {

					Map<RegistrationFieldType, String> registrationParams = new HashMap<RegistrationFieldType, String>();
					registrationParams.put(RegistrationFieldType.FISCAL_CODE,
							InputPropsUtil.getPropertyValue(vatNumber));
					registrationParams.put(RegistrationFieldType.POLICY_NUMBER,
							InputPropsUtil.getPropertyValue(policyNumber));
					registrationParams.put(RegistrationFieldType.USERNAME, InputPropsUtil.getPropertyValue(userName));
					registrationParams.put(RegistrationFieldType.REUSERNAME,
							InputPropsUtil.getPropertyValue(reUserName));

					registrationPage.setPersonalInformationPerson(registrationParams);
					
				});

		Then("an info privacy requirement settings warning message appears", () -> {
			registrationPage.isPersonalDataPrivacyModaleVisible();
		});

		/**
		 * Scenario: User can not register to PCC as a person because he
		 * did not set info about usage
		 */

		When("user enters all required person registration fields {string} {string} {string} {string} and privacy info in the registration page",
				(String vatNumber, String policyNumber, String userName, String reUserName) -> {

					Map<RegistrationFieldType, String> registrationParams = new HashMap<RegistrationFieldType, String>();
					registrationParams.put(RegistrationFieldType.FISCAL_CODE,
							InputPropsUtil.getPropertyValue(vatNumber));
					registrationParams.put(RegistrationFieldType.POLICY_NUMBER,
							InputPropsUtil.getPropertyValue(policyNumber));
					registrationParams.put(RegistrationFieldType.USERNAME, InputPropsUtil.getPropertyValue(userName));
					registrationParams.put(RegistrationFieldType.REUSERNAME,
							InputPropsUtil.getPropertyValue(reUserName));

					registrationPage.setPersonalInformationPerson(registrationParams);
					registrationPage.setPersonalDataCheckBox();

				});

		Then("an usage info requirement settings warning message appears", () -> {
			registrationPage.isSecurityPrivacyModaleVisible();
		});
		
		Then("user enters different values for {string} and {string} in registration process", (String password, String passwordConfirmation) -> {
			
			password = InputPropsUtil.getPropertyValue(password);
			passwordConfirmation = InputPropsUtil.getPropertyValue(passwordConfirmation);
			
			registrationPage.setPassword(password);
			registrationPage.setPasswordConfirmation(passwordConfirmation);
		});

		Then("user cannot complete his registration", () -> {
		    // Write code here that turns the phrase above into concrete actions
			Assert.assertTrue(registrationPage.isPasswordConfirmationError());
			registrationPage.clickBtnConfirm();
			Assert.assertTrue(registrationPage.isPasswordConfirmationError());
		});
	}

	@After("@ValidationRegistration")
	public void endTest(Scenario scenario) throws IOException {

		logger.debug("in " + "ValidationRegistration.after");

		afterScenario(scenario);

		logger.debug("out " + "ValidationRegistration.after");
	}

}
