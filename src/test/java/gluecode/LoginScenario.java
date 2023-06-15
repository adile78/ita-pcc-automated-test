package gluecode;

import java.io.IOException;

import org.junit.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import driverManager.WebDriverContainer;
import pccpageobjects.PccDashboardPage;
import pccpageobjects.PccLoginPage;
import utilities.InputPropsUtil;
import utilities.PropsUtil;

public class LoginScenario extends PccScenario implements En {

	private PccLoginPage loginPage;
	private PccDashboardPage dashboardPage;

	
	public LoginScenario(WebDriverContainer webDriverContainer) {
		super(webDriverContainer);
		loginPage = new PccLoginPage(webDriverContainer.getWebDriver());
		dashboardPage = new PccDashboardPage(webDriverContainer.getWebDriver());
		
		/**
		 * Scenario: User successfully logs into PCC Area Clienti
		 */

		Given("user is on PCC login page", () -> {			

			webDriverContainer.getWebDriver().get(PropsUtil.getPropertyValue(testEnvironment));

			Assert.assertTrue(loginPage.isLoginPage());
		});

		When("user enters all required login fields {string} and {string}",
				(String username, String password) -> {

					loginPage.loginAction(InputPropsUtil.getPropertyValue(username),
							InputPropsUtil.getPropertyValue(password));

				});

		Then("user is logged in", () -> {
			// TODO: check if modale updateContatti is on
			Assert.assertTrue(dashboardPage.isDashboardPage());
		});

		/**
		 * Scenario: User does not login to PCC Area Clienti because he enters
		 * wrong data
		 */

		When("user enters wrong required login fields {string} and {string}",
				(String username, String password) -> {

					loginPage.loginAction(InputPropsUtil.getPropertyValue(username),
							InputPropsUtil.getPropertyValue(password));
				});

		Then("a {string} error message appears", (String wrongDataType) -> {
			// insert check on red field if wanted, based on wrongdatatype
			// (username or password)
			Assert.assertTrue(loginPage.isBadCredentialsModaleVisible());

		});

		/**
		 * GENERIC STEPS
		 * 
		 * log-in on dashboard page
		 * 
		 */

		Given("user is logged in {string} and {string}", (String username, String password) -> {

			webDriverContainer.getWebDriver().get(PropsUtil.getPropertyValue(testEnvironment));
			loginPage.loginAction(InputPropsUtil.getPropertyValue(username), InputPropsUtil.getPropertyValue(password));
			Assert.assertTrue(dashboardPage.isDashboardPage());
		});
	}

	@After({ "@Login" })
	public void endTest(Scenario scenario) throws IOException {

		afterScenario(scenario);
	}

}
