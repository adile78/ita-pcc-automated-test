package gluecode;

import java.io.IOException;

import org.junit.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import driverManager.WebDriverContainer;
import pccpageobjects.PccLoginPage;
import utilities.InputPropsUtil;

public class LoginValidationScenario extends PccScenario implements En {

	private PccLoginPage loginPage;

	public LoginValidationScenario(WebDriverContainer webDriverContainer) {
		super(webDriverContainer);
		loginPage = new PccLoginPage(webDriverContainer.getWebDriver());

		/**
		 * Scenario: User does not login to PCC Area Clienti because he enters
		 * partial required data
		 */

		When("user enters partial required login fields {string} and {string}",
				(String username, String password) -> {
					loginPage.loginAction(InputPropsUtil.getPropertyValue(username),
							InputPropsUtil.getPropertyValue(password));
				});

		Then("a username and password warning message is displayed", () -> {
			Assert.assertTrue(loginPage.isPasswordError());
			Assert.assertTrue(loginPage.isUsernameError());
		});

		/**
		 * Scenario: User does not login to PCC Area Clienti because he enters
		 * wrong data
		 * 
		 */

		When("user enters {string} that is not a valid email", (String username) -> {
			loginPage.setUsername(username);
		});

		Then("a username warning message is displayed", () -> {
			Assert.assertTrue(loginPage.isUsernameError());
		});
	}

	@After("@ValidationLogin")
	public void endTest(Scenario scenario) throws IOException {

		afterScenario(scenario);

	}
}
