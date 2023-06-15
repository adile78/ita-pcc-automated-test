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
import pccpageobjects.PccLoginPage;
import pccpageobjects.PccRetrieveUsernamePage;
import utilities.InputPropsUtil;
import utilities.PropsUtil;

public class RetrieveUsernameScenario extends PccScenario implements En {

	private PccLoginPage loginPage;
	private PccRetrieveUsernamePage retrieveUsernamePage;

	public RetrieveUsernameScenario(WebDriverContainer webDriverContainer) {
		super(webDriverContainer);

		loginPage = new PccLoginPage(webDriverContainer.getWebDriver());
		retrieveUsernamePage = new PccRetrieveUsernamePage(webDriverContainer.getWebDriver());

		/**
		 * Scenario: User succesfully retrieves his username as a person
		 */

		Given("user wants to retrieve his username", () -> {

			webDriverContainer.getWebDriver().get(PropsUtil.getPropertyValue(testEnvironment));
			loginPage.clickBtnRetrieveUsername();
		});

		When("user inserts his person data {string} and {string}", (String fiscalCode, String policy) -> {

			fiscalCode = InputPropsUtil.getPropertyValue(fiscalCode);
			policy = InputPropsUtil.getPropertyValue(policy);

			Map<RegistrationFieldType, String> registrationParams = new HashMap<RegistrationFieldType, String>();
			registrationParams.put(RegistrationFieldType.FISCAL_CODE, fiscalCode);
			registrationParams.put(RegistrationFieldType.POLICY_NUMBER, policy);
			retrieveUsernamePage.firstStepRegistrationPerson(registrationParams);
		});

		When("user successfully validates them accessing the next username retrievement step", () -> {

			retrieveUsernamePage.clickBtnConfirm();
		});
		     
		Then("user successfully complete the username retrieving process as a person selecting his {string}",
				(String username) -> {

					username = InputPropsUtil.getPropertyValue(username);
					Map<RegistrationFieldType, String> registrationParams = new HashMap<RegistrationFieldType, String>();
					registrationParams.put(RegistrationFieldType.USERNAME, username);
					retrieveUsernamePage.secondStepRegistrationPerson(registrationParams);
					Assert.assertTrue(retrieveUsernamePage.checkEmail());
				});

		/**
		 * Scenario: User succesfully retrieves his username as organization
		 */
		
		When("user inserts his organization data {string} and {string}", (String vatNumber, String policy) -> {

			vatNumber = InputPropsUtil.getPropertyValue(vatNumber);
			policy = InputPropsUtil.getPropertyValue(policy);

			Map<RegistrationFieldType, String> registrationParams = new HashMap<RegistrationFieldType, String>();
			registrationParams.put(RegistrationFieldType.VAT_NUMBER, vatNumber);
			registrationParams.put(RegistrationFieldType.POLICY_NUMBER, policy);
			retrieveUsernamePage.firstStepRegistrationOrganization(registrationParams);
		});

		Then("user successfully complete the username retrieving process as organization entering the new {string}",
				(String username) -> {

					username = InputPropsUtil.getPropertyValue(username);
					Map<RegistrationFieldType, String> registrationParams = new HashMap<RegistrationFieldType, String>();
					registrationParams.put(RegistrationFieldType.USERNAME, username);
					retrieveUsernamePage.secondStepRegistrationOrganization(registrationParams);
					Assert.assertTrue(retrieveUsernamePage.checkEmail());
				});
		
		/**
		 * Scenario: User can not retrieve his username as a person because his data are not valid
		 */
		
		When("user inserts his person data {string} and {string} and validates them", (String fiscalCode, String policy) -> {

			fiscalCode = InputPropsUtil.getPropertyValue(fiscalCode);
			policy = InputPropsUtil.getPropertyValue(policy);

			Map<RegistrationFieldType, String> registrationParams = new HashMap<RegistrationFieldType, String>();
			registrationParams.put(RegistrationFieldType.FISCAL_CODE, fiscalCode);
			registrationParams.put(RegistrationFieldType.POLICY_NUMBER, policy);
			retrieveUsernamePage.firstStepRegistrationPerson(registrationParams);
			retrieveUsernamePage.clickBtnConfirm();
		});
		
		Then("a data not valid message appears", () -> {
			//TODO retrieve username
		});

		/**
		 * Scenario: User can not retrieve his username as organization because his data are not valid
		 */
		
		
		When("user inserts his organization data {string} and {string} and validates them", (String vatNumber, String policy) -> {

			vatNumber = InputPropsUtil.getPropertyValue(vatNumber);
			policy = InputPropsUtil.getPropertyValue(policy);

			Map<RegistrationFieldType, String> registrationParams = new HashMap<RegistrationFieldType, String>();
			registrationParams.put(RegistrationFieldType.VAT_NUMBER, vatNumber);
			registrationParams.put(RegistrationFieldType.POLICY_NUMBER, policy);
			retrieveUsernamePage.firstStepRegistrationOrganization(registrationParams);
			retrieveUsernamePage.clickBtnConfirm();
		});
	}
	
	@After("@RetrieveUsername")
	public void endTest(Scenario scenario) throws IOException {

		afterScenario(scenario);

	}

}
