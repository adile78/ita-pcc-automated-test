package gluecode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import driverManager.WebDriverContainer;
import enums.PolicyCategoryType;
import factories.PccPolicyFactory;
import pageobjects.DetailPolicy;
import pageobjects.GenericLoginPage;
import pageobjects.ListPolicy;
import pccpageobjects.PccDashboardPage;
import pccpageobjects.PccLoginPage;
import utilities.PropsUtil;
import pccpageobjects.PccListPolicy;

/**
 * @author barbetti_be
 *
 */
public class DashboardPolicyDetailScenario extends PccScenario implements En {

	private static Logger logger = LogManager.getLogger();

	private PccDashboardPage dashboardPage;
	private GenericLoginPage loginPage;

	public DashboardPolicyDetailScenario(WebDriverContainer webDriverContainer) {

		super(webDriverContainer);

		loginPage = new PccLoginPage(webDriverContainer.getWebDriver());
		dashboardPage = new PccDashboardPage(webDriverContainer.getWebDriver());
		
		/**
		 * Scenario: User consults his <category> policies details
		 */

		When("user consults his {string} policy detail on dashboard page", (String categoryType) -> {

			categoryType = PropsUtil.getPropertyValue(categoryType);

			Thread.sleep(6000);
			List<PccListPolicy> policyList = dashboardPage.getPolicies();
			List<ListPolicy> categoryPolicyList = new ArrayList<ListPolicy>();
			Assert.assertTrue(policyList.size() > 0);
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

		Then("user sees all required {string} policy detail fields", (String categoryType) -> {

			categoryType = PropsUtil.getPropertyValue(categoryType);
			int i = 0;
			while (i < dashboardPage.getPolicies().size()) {

				dashboardPage = new PccDashboardPage(webDriverContainer.getWebDriver());
				PccListPolicy policy = dashboardPage.getPolicies().get(i);

				if (PolicyCategoryType.valueOf(categoryType).getCategory().equals(policy.getCategoryType().getCategory())) {

					DetailPolicy detailPolicy = policy.clickPolicyDetail();
					DetailPolicy categoriedPolicy = PccPolicyFactory
							.getCategorizedDetailPolicy(webDriverContainer.getWebDriver(), detailPolicy, PolicyCategoryType.valueOf(categoryType));
					Map<String, String> parameters = new HashMap<String, String>();

					Assert.assertTrue(categoriedPolicy.checkGenericInfo(parameters));

					Assert.assertTrue(categoriedPolicy.checkCategoryInfo(parameters));

					dashboardPage.clickDashboardLinkPolicyDetail();
				}
				i++;
			}
		});

	}

	@After("@DashoardPolicyDetail")
	public void endTest(Scenario scenario) throws IOException {

		logger.debug("in " + "DashboardPolicyDetail.after");
		afterScenario(scenario);

		logger.debug("out " + "DashboardPolicyDetail.after");

	}

}
