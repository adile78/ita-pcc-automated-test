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
import pccpageobjects.PccDashboardPage;
import pccpageobjects.PccLoginPage;
import pccpageobjects.PccRegistrationPage;
import utilities.InputPropsUtil;
import utilities.PropsUtil;

public class RegistrationScenario extends PccScenario implements En {

	private PccLoginPage loginPage;
	private PccRegistrationPage registrationPage;
	private PccDashboardPage dashboardPage;

	public RegistrationScenario(WebDriverContainer webDriverContainer) {
		super(webDriverContainer);
		loginPage = new PccLoginPage(webDriverContainer.getWebDriver());
		registrationPage = new PccRegistrationPage(webDriverContainer.getWebDriver());
		dashboardPage = new PccDashboardPage(webDriverContainer.getWebDriver());

		/**
		 * Scenario: User successfully registers to PCC private area as a person 
		 */

		Given("user wants to register to PCC private area as a person", () -> {

			logger.debug("in " + "RegistrationScenario.user wants to register to PCC private area as a person");

			webDriverContainer.getWebDriver().get(PropsUtil.getPropertyValue(testEnvironment));
			loginPage.clickBtnRegister();

			logger.debug("out " + "RegistrationScenario.user wants to register to PCC private area as a person");
		});

		When("user enters all required person registration fields {string} {string} {string} {string} and privacy permissions in the registration page",
				(String fiscalCode, String policyNumber, String userName, String reUserName) -> {

					logger.debug(
							"in " + "RegistrationScenario.user enters all required person registration fields");

					Map<RegistrationFieldType, String> registrationParams = new HashMap<RegistrationFieldType, String>();
					registrationParams.put(RegistrationFieldType.FISCAL_CODE,
							InputPropsUtil.getPropertyValue(fiscalCode));
					registrationParams.put(RegistrationFieldType.POLICY_NUMBER,
							InputPropsUtil.getPropertyValue(policyNumber));
					registrationParams.put(RegistrationFieldType.USERNAME, InputPropsUtil.getPropertyValue(userName));
					registrationParams.put(RegistrationFieldType.REUSERNAME, InputPropsUtil.getPropertyValue(reUserName));

					registrationPage.firstStepRegistrationPerson(registrationParams);

					logger.debug("out "
							+ "RegistrationScenario.user enters all required person registration fields");
				});

		When("user successfully validates them accessing the next registration step", () -> {

			logger.debug("in " + "RegistrationScenario.user successfully validates them accessing the next step");

			registrationPage.clickBtnConfirm();
			registrationPage.clickAttivationLink();

			logger.debug("out " + "RegistrationScenario.user successfully validates them accessing the next step");
		});

		Then("user successfully complete the person registration entering the {string}",
				(String password) -> {

					Map<RegistrationFieldType, String> registrationParams = new HashMap<RegistrationFieldType, String>();
					registrationParams.put(RegistrationFieldType.PASSWORD, InputPropsUtil.getPropertyValue(password));

					registrationPage.secondStepRegistrationPerson(registrationParams);
					Assert.assertTrue(dashboardPage.isDashboardPage());

				});

		/**
		 * Scenario: User can not register to PCC as a person because user
		 * is already registered
		 */

		Given("user has already registered to PCC private area as a person", () -> {

			webDriverContainer.getWebDriver().get(PropsUtil.getPropertyValue(testEnvironment));
			loginPage.clickBtnRegister();
		});

		When("user validates registration fields", () -> {

			registrationPage.clickBtnConfirm();
		});

		Then("an already registered warning message appears", () -> {

			Assert.assertTrue(registrationPage.isAlreadyRegisteredModaleVisible());
		});

		/**
		 * Scenario: User can not register to PCC because user enters a not
		 * found fiscal code
		 */

		Then("a fiscal code not found warning message appears", () -> {

			Assert.assertTrue(registrationPage.isBadCredentialsRegistrationModaleVisible());
		});

		/**
		 * Scenario: User does not register to PCC because of expired link
		 */

		Given("user opens the {string} he received some time ago", (String registrationLink) -> {

			webDriverContainer.getWebDriver()
					.get(PropsUtil.getPropertyValue(testEnvironment) + PropsUtil.getPropertyValue(registrationLink));
		});

		Then("an expired link warning message appears", () -> {

			Assert.assertTrue(registrationPage.isExpiredLinkErrorVisible());
		});

		/**
		 * Scenario: User successfully registers to PCC private area as organization
		 */

		Given("user wants to register to PCC private area as organization", () -> {

			webDriverContainer.getWebDriver().get(PropsUtil.getPropertyValue(testEnvironment));
			loginPage.clickBtnRegister();
		});

		When("user enters all required organization registration fields {string} {string} {string} {string} and privacy permissions in the registration page",
				(String vatNumber, String policyNumber, String userName, String reUserName) -> {

					Map<RegistrationFieldType, String> registrationParams = new HashMap<RegistrationFieldType, String>();
					registrationParams.put(RegistrationFieldType.VAT_NUMBER,
							InputPropsUtil.getPropertyValue(vatNumber));
					registrationParams.put(RegistrationFieldType.POLICY_NUMBER,
							InputPropsUtil.getPropertyValue(policyNumber));
					registrationParams.put(RegistrationFieldType.USERNAME, InputPropsUtil.getPropertyValue(userName));
					registrationParams.put(RegistrationFieldType.REUSERNAME, InputPropsUtil.getPropertyValue(reUserName));

					registrationPage.firstStepRegistrationOrganization(registrationParams);

				});

		Then("user successfully complete the organization registration entering the {string}",
				(String password) -> {

					Map<RegistrationFieldType, String> registrationParams = new HashMap<RegistrationFieldType, String>();
					registrationParams.put(RegistrationFieldType.PASSWORD, InputPropsUtil.getPropertyValue(password));

					registrationPage.secondStepRegistrationOrganization(registrationParams);
					Assert.assertTrue(dashboardPage.isDashboardPage());
				});

		/**
		 * Scenario: User can not register to PCC as organization because user is
		 * already registered
		 */
		
		Given("user has already registered to PCC private area as organization", () -> {

			webDriverContainer.getWebDriver().get(PropsUtil.getPropertyValue(testEnvironment));
			loginPage.clickBtnRegister();
		});

		/**
		 * Scenario: User can not register to PCC because user enters a not found
		 * vat number
		 */

		Then("a vat number not found warning message appears", () -> {

			Assert.assertTrue(registrationPage.isBadCredentialsRegistrationModaleVisible());
		});
	}

	@After("@Registration")
	public void endTest(Scenario scenario) throws IOException {

		afterScenario(scenario);
	}

}
