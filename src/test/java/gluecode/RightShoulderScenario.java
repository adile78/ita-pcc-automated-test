package gluecode;

import java.io.IOException;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import driverManager.WebDriverContainer;
import pccpageobjects.PccDashboardPage;
import pccpageobjects.PccLoginPage;

public class RightShoulderScenario extends PccScenario implements En {

	private PccLoginPage loginPage;
	private PccDashboardPage dashboardPage;

	public RightShoulderScenario(WebDriverContainer webDriverContainer) {
		super(webDriverContainer);
		loginPage = new PccLoginPage(webDriverContainer.getWebDriver());
		dashboardPage = new PccDashboardPage(webDriverContainer.getWebDriver());

		/**
		 * Scenario: User sees the agency box on dashboard page
		 */

		Then("user sees the agency box on the right shoulder of dashboard page", () -> {

			dashboardPage.checkRightShoulder();
		});
	}

	@After("@RightShoulder")
	public void endTest(Scenario scenario) throws IOException {

		logger.debug("in " + "RightShoulder.after");

		afterScenario(scenario);

		logger.debug("out " + "RightShoulder.after");

	}

}
