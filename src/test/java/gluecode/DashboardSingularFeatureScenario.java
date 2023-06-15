package gluecode;

import java.util.List;

import org.junit.Assert;

import cucumber.api.java8.En;
import driverManager.WebDriverContainer;
import enums.PolicyCategoryType;
import factories.PccPolicyFactory;
import pageobjects.DetailPolicy;
import pageobjects.ListPolicy;
import pccpageobjects.PccDashboardPage;
import pccpageobjects.PccListPolicy;
import pccpageobjects.PccSrtDetailPolicy;
import utilities.PropsUtil;
import utilities.InputPropsUtil;

public class DashboardSingularFeatureScenario extends PccScenario implements En {

	public PccDashboardPage dashboardPage;
	public ListPolicy srtPolicy;
	
	public DashboardSingularFeatureScenario(WebDriverContainer webDriverContainer) {
		super(webDriverContainer);
		dashboardPage = new PccDashboardPage(webDriverContainer.getWebDriver());
		
		
		/**
		 * Scenario: User consults his AAI <category> policy detail with SRT
		 * tool
		 */

		When("user consults his AAI {string} {string} policy detail with SRT tool", (String categoryType, String policyNumber) -> {

			categoryType = PropsUtil.getPropertyValue(categoryType);
			policyNumber = InputPropsUtil.getPropertyValue(policyNumber);			
			Thread.sleep(6000);
			
			List<PccListPolicy> policyList = dashboardPage.getPolicies();
			Assert.assertTrue(policyList.size() > 0);
			for (PccListPolicy policy : policyList) {
				if (PolicyCategoryType.valueOf(categoryType).getCategory().equals(policy.getCategoryType().getCategory()) && policyNumber.equals(policy.getInfoBox().getPolicyNumber()))  {
					srtPolicy = PccPolicyFactory.getCategorizedListPolicy(webDriverContainer.getWebDriver(), policy,
							PolicyCategoryType.valueOf(categoryType));
				}
			}
			Assert.assertTrue(srtPolicy != null);
		});

		Then("user sees all required {string} policy detail with SRT tool features", (String categoryType) -> {

			categoryType = PropsUtil.getPropertyValue(categoryType);
			DetailPolicy detailPolicy = srtPolicy.clickPolicyDetail();
			PccSrtDetailPolicy categoriedSrtPolicy = new PccSrtDetailPolicy(webDriverContainer.getWebDriver(), detailPolicy);
			
			Assert.assertTrue(categoriedSrtPolicy.checkSrtPolicy());

			
		});
		
		/**
		 * Scenario: User consults his AAI <category> policy detail with clear
		 * language coverages
		 */

		When("user consults his AAI {string} policy detail with clear language coverages on dashboard page",
				(String categoryType) -> {
					// TODO clear languages
				});

		Then("user sees all required {string} policy detail with clear language coverages fields",
				(String categoryType) -> {
					// TODO clear languages
				});
	}

}
