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
import pccpageobjects.PccRetrievePasswordPage;
import utilities.InputPropsUtil;
import utilities.PropsUtil;

public class RetrievePasswordScenario extends PccScenario implements En {

	private PccLoginPage loginPage;
	private PccDashboardPage dashboardPage;
	private PccRetrievePasswordPage retrievePasswordPage;

	public RetrievePasswordScenario(WebDriverContainer webDriverContainer) {
		super(webDriverContainer);

		loginPage = new PccLoginPage(webDriverContainer.getWebDriver());
		dashboardPage = new PccDashboardPage(webDriverContainer.getWebDriver());
		retrievePasswordPage = new PccRetrievePasswordPage(webDriverContainer.getWebDriver());

		/**
		 * Scenario: User succesfully retrieves his password
		 * */
		
		Given("user wants to retrieve his password", () -> {

			webDriverContainer.getWebDriver().get(PropsUtil.getPropertyValue(testEnvironment));
			loginPage.clickBtnRetrievePassword();
		});

		When("user inserts his {string}", (String username) -> {

			username = InputPropsUtil.getPropertyValue(username);
			Map<RegistrationFieldType, String> registrationParams = new HashMap<RegistrationFieldType, String>();
			registrationParams.put(RegistrationFieldType.USERNAME, username);
			retrievePasswordPage.firstStepRegistrationPerson(registrationParams);
		});

		When("user successfully validates it accessing the next password retrievement step", () -> {

			retrievePasswordPage.clickBtnConfirm();
			retrievePasswordPage.clickAttivationLink();
		});

		Then("user successfully complete the password retrieving process entering the new {string}",
				(String password) -> {

					password = InputPropsUtil.getPropertyValue(password);
					Map<RegistrationFieldType, String> registrationParams = new HashMap<RegistrationFieldType, String>();
					registrationParams.put(RegistrationFieldType.PASSWORD, password);
					retrievePasswordPage.secondStepRegistrationPerson(registrationParams);		
					
					Assert.assertTrue(dashboardPage.isDashboardPage());
				});
		
		/**
		 * Scenario: User cannot retrieve his password because the username is not found
		 * */
		
		
		When("user inserts his {string} and validates it", (String username) -> {

			username = InputPropsUtil.getPropertyValue(username);
			Map<RegistrationFieldType, String> registrationParams = new HashMap<RegistrationFieldType, String>();
			registrationParams.put(RegistrationFieldType.USERNAME, username);
			retrievePasswordPage.firstStepRegistrationPerson(registrationParams);
			retrievePasswordPage.clickBtnConfirm();
			
		});
		
		Then("a not found username message appears", () -> {

			Assert.assertTrue(retrievePasswordPage.isNotExistingMailModaleVisible());
		});
		 
	}

	@After("@RetrievePassword")
	public void endTest(Scenario scenario) throws IOException {

		afterScenario(scenario);

	}

}
