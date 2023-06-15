package gluecode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import driverManager.WebDriverContainer;
import enums.PolicyCategoryType;
import factories.PccPolicyFactory;
import pageobjects.GenericLoginPage;
import pageobjects.ListPolicy;
import pccpageobjects.PccDashboardPage;
import pccpageobjects.PccListPolicy;
import pccpageobjects.PccLoginPage;
import utilities.PropsUtil;

/**
 * @author barbetti_be
 *
 */
public class DashboardPolicyListScenario extends PccScenario implements En {

	private static Logger logger = LogManager.getLogger();

	private PccDashboardPage dashboardPage;
	private GenericLoginPage loginPage;
	private List<ListPolicy> categoryPolicyList;

	public DashboardPolicyListScenario(WebDriverContainer webDriverContainer) {
		super(webDriverContainer);
		loginPage = new PccLoginPage(webDriverContainer.getWebDriver());
		dashboardPage = new PccDashboardPage(webDriverContainer.getWebDriver());
		categoryPolicyList = new ArrayList<ListPolicy>();

		/**
		 * Scenario: User consults his policies
		 */

		When("user consults his {string} policies on dashboard policy list", (String categoryType) -> {

			categoryType = PropsUtil.getPropertyValue(categoryType);
			Thread.sleep(9000);
			List<PccListPolicy> policyList = dashboardPage.getPolicies();
			for (PccListPolicy policy : policyList) {
				if (PolicyCategoryType.valueOf(categoryType).getCategory().equals(policy.getCategoryType().getCategory())) {
					ListPolicy pol = null;
					pol = PccPolicyFactory.getCategorizedListPolicy(webDriverContainer.getWebDriver(), policy,
							PolicyCategoryType.valueOf(categoryType));
					categoryPolicyList.add(pol);
				}
			}
			Assert.assertTrue(categoryPolicyList.size() > 0);
		});

		Then("user sees all required {string} policy fields", (String categoryType) -> {

			categoryType = PropsUtil.getPropertyValue(categoryType);

			for (ListPolicy policy : categoryPolicyList) {

				Assert.assertTrue(PolicyCategoryType.valueOf(categoryType).getCategory().equals(policy.getCategoryType().getCategory()));

				Assert.assertTrue(policy.checkListLabelsAndValues());
				Assert.assertTrue(policy.checkDashboardListCtas());
			}
		});
	}

	@After("@DashboardPolicyList")
	public void endTest(Scenario scenario) throws IOException {

		logger.debug("in " + "DashboardPolicyList.after");

		afterScenario(scenario);

		logger.debug("out " + "DashboardPolicyList.after");

	}

}
