package gluecode;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import driverManager.WebDriverContainer;
import enums.PaymentFieldType;
import enums.PolicyCategoryType;
import pccpageobjects.BassilichiPage;
import pccpageobjects.PccDashboardPage;
import pccpageobjects.PccRenewalPolicy;
import utilities.InputPropsUtil;

public class PolicyRenewalScenario extends PccScenario implements En {

	private PccDashboardPage dashboardPage;
	private BassilichiPage paymentPage;

	private PccRenewalPolicy renewalPolicy;

	public PolicyRenewalScenario(WebDriverContainer webDriverContainer) {

		super(webDriverContainer);
		dashboardPage = new PccDashboardPage(webDriverContainer.getWebDriver());
		paymentPage = new BassilichiPage(webDriverContainer.getWebDriver());

		/**
		 * Scenario: User has a motor policy that can be online renewal
		 * 
		 */

		Given("user has a motor policy that can be online renewal", () -> {

			// find renewal policies
			List<PccRenewalPolicy> renewalPolicies = dashboardPage.getRenewalPolicies();
			Assert.assertTrue(renewalPolicies != null && renewalPolicies.size() > 0);

			// save element for next step
			renewalPolicy = renewalPolicies.get(0);

			// check if auto policy
			Assert.assertTrue(PolicyCategoryType.AUTO.getCategory().equals(renewalPolicy.getCategoryType()));

		});

		Then("user sees a banner with all the informations to renew it", () -> {

			Assert.assertTrue(renewalPolicy.hasRequiredInfo());

		});

		/**
		 * Scenario: User sees the receipt information
		 * 
		 */

		When("user accesses receipt details by the policy box", () -> {

			renewalPolicy.getRenewalPolicyBoxBtn().click();

		});

		Then("user sees receipt information", () -> {

			// TODO: user sees receipt information
		});

		/**
		 * Scenario: User successfully pays the policy title and downloads the
		 * receipt
		 * 
		 */

		Given("user wants to renewal the policy", () -> {

			renewalPolicy.getRetail().rinnovaPolizza();
			// TODO: check if Bassilichi page loads on time
		});

		When("user inserts all the requiered payment fields {string} and {string} and {string} and {string} and {string} and {string} on Bassilichi page and validates them",
				(String bassilichiCardNumber, String bassilichiOwnerNumber, String bassilichiMonthNumber,
						String bassilichiExpiredYear, String bassilichiCvv, String bassilichiEmail) -> {

					/*
					 * String URL =
					 * "https://ipg-test4.constriv.com/IPGWeb/servlet/PaymentSelection.html?PaymentID=404358080191190863";
					 * webDriverContainer.getWebDriver().get(URL);
					 */

					Map<PaymentFieldType, String> paymentParams = new HashMap<PaymentFieldType, String>();
					paymentParams.put(PaymentFieldType.CARD_NUMBER,
							InputPropsUtil.getPropertyValue(bassilichiCardNumber));
					paymentParams.put(PaymentFieldType.OWNER_NAME,
							InputPropsUtil.getPropertyValue(bassilichiOwnerNumber));
					paymentParams.put(PaymentFieldType.MONTH_NUMBER,
							InputPropsUtil.getPropertyValue(bassilichiMonthNumber));
					paymentParams.put(PaymentFieldType.EXPIRED_YEAR,
							InputPropsUtil.getPropertyValue(bassilichiExpiredYear));
					paymentParams.put(PaymentFieldType.CVV, InputPropsUtil.getPropertyValue(bassilichiCvv));
					paymentParams.put(PaymentFieldType.EMAIL, InputPropsUtil.getPropertyValue(bassilichiEmail));

					paymentPage.payTitle(paymentParams);
				});

		Then("user lands on the thank you page", () -> {
			// TODO: user lands on the thank you page
		});

		Then("user downloads the receipt", () -> {
			// TODO: user downloads the receipt
		});

		Then("a generic renewal policy error message appears", () -> {
			Assert.assertTrue(renewalPolicy.isGenericErrorModaleVisibile());
		});

		Then("user returns to homepage from renewal policy detail page", () -> {
			renewalPolicy.returnToHomePage();
		});

		Then("a waiting box appears on the policy", () -> {
			Assert.assertTrue(renewalPolicy.isWaitingBoxVisibile());
		});

		/**
		 * Scenario: User cannot pay the policy title because of waiting time
		 * after max payment attempts
		 * 
		 */

		Given("user wants to renewal the policy for the third time", () -> {

			renewalPolicy.getRetail().rinnovaPolizza();
		});

		Then("a max attemps renewal policy error message appears", () -> {
			Assert.assertTrue(renewalPolicy.isMaxPaymentAttempsModaleVisibile());
		});

	}

	@After("@PolicyRenewal")
	public void endTest(Scenario scenario) throws IOException {

		afterScenario(scenario);
	}

}
