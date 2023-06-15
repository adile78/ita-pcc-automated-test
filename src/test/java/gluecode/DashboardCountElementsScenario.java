package gluecode;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import driverManager.WebDriverContainer;
import enums.PolicyCategoryType;
import factories.PccPolicyFactory;
import pageobjects.DetailPolicy;
import pageobjects.LifeAndSavingsDetailPolicy;
import pageobjects.ListPolicy;
import pccpageobjects.PccDashboardPage;
import pccpageobjects.PccListPolicy;
import utilities.PropsUtil;
import utilities.InputPropsUtil;

public class DashboardCountElementsScenario extends PccScenario implements En {

	private PccDashboardPage dashboardPage;
	private ListPolicy categoryPolicy;
	private DetailPolicy categoriedPolicy;

	public DashboardCountElementsScenario(WebDriverContainer webDriverContainer) {

		super(webDriverContainer);
		dashboardPage = new PccDashboardPage(webDriverContainer.getWebDriver());

		/**
		 * all scenarios
		 * */
		
		When("user consults his {string} {string} policy detail on dashboard page",
				(String categoryType, String policyNumber) -> {

					categoryType = PropsUtil.getPropertyValue(categoryType);
					policyNumber = InputPropsUtil.getPropertyValue(policyNumber);
					Thread.sleep(9000); 
					List<PccListPolicy> policyList = dashboardPage.getPolicies();
					Assert.assertTrue(policyList.size() > 0);
					for (PccListPolicy policy : policyList) {
						if (PolicyCategoryType.valueOf(categoryType).getCategory().equals(policy.getCategoryType().getCategory())
								&& policyNumber.equals(policy.getInfoBox().getPolicyNumber())) {
							categoryPolicy = PccPolicyFactory
									.getCategorizedListPolicy(webDriverContainer.getWebDriver(), policy, PolicyCategoryType.valueOf(categoryType));
						}
					}
					Assert.assertTrue(categoryPolicy != null);
					DetailPolicy detailPolicy = categoryPolicy.clickPolicyDetail();
					categoriedPolicy = PccPolicyFactory.getCategorizedDetailPolicy(webDriverContainer.getWebDriver(),
							detailPolicy, PolicyCategoryType.valueOf(categoryType));
				});
		
		/**
		 * Scenario: User consults his <category> policy insureds
		 * */
		
		Then("user sees all {int} of insureds of his {string} policy",
				(Integer insuredsNumber, String categoryType) -> {

					List<String> insureds = categoriedPolicy.getInsuredsDetails();
					Assert.assertTrue(insuredsNumber == insureds.size());
				});

		/**
		 * Scenario: User consults his <category> policy coverages
		 * */
		
		Then("user sees all {int} of coverages of his {string} policy",
				(Integer coveragesNumber, String categoryType) -> {

					List<String> coverages = categoriedPolicy.getCoveragesDetails();
					Assert.assertTrue(coveragesNumber == coverages.size());
				});

		/**
		 * Scenario: User consults his <category> policy beneficiaries
		 * */
		
		Then("user sees all {int} of beneficiaries of his {string} policy",
				(Integer beneficiariesNumber, String categoryType) -> {

					List<String> beneficiaries = categoriedPolicy.getBeneficiariValueDetails();
					Assert.assertTrue(beneficiariesNumber == beneficiaries.size());
				});

		/**
		 * Scenario: User consults his <category> policy movements
		 * */
		
		Then("user sees all {int} of movements of his {string} policy",
				(Integer movementsNumber, String categoryType) -> {

					List<String> movements = categoriedPolicy.getMovementsHistoryDetails();
					Assert.assertTrue(movementsNumber == movements.size());
				});
		
		Then("user sees all {int} of his redemption requests history", (Integer redemptionRequestNumber) -> {
			List<String> redemptionReques = ((LifeAndSavingsDetailPolicy)categoriedPolicy).geRedemptionRequestHistoryDetails();
			Assert.assertTrue(redemptionRequestNumber == redemptionReques.size());
		});
		
		

	}

	@After({ "@DashboardCountElements" })
	public void endTest(Scenario scenario) throws IOException {

		logger.debug("in " + "DashboardCountElementsScenario.after");

		afterScenario(scenario);

		logger.debug("out " + "DashboardCountElementsScenario.after");

	}

}
