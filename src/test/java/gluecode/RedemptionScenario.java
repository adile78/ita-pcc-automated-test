package gluecode;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import driverManager.WebDriverContainer;
import enums.CallToActionType;
import enums.PolicyCategoryType;
import enums.RedemptionStatusType;
import enums.RedemptionType;
import factories.PccPolicyFactory;
import pageobjects.DetailPolicy;
import pageobjects.LifeAndSavingsDetailPolicy;
import pccpageobjects.PccDashboardPage;
import pccpageobjects.PccListPolicy;
import pccpageobjects.PccLoginPage;
import pccpageobjects.PccRedemptionPage;
import utilities.InputPropsUtil;

public class RedemptionScenario extends PccScenario implements En {

	private PccDashboardPage dashboardPage;
	private PccListPolicy redeemablePolicy;
	private DetailPolicy detailRedeemablePolicy;
	private PccRedemptionPage redemptionPage;

	public RedemptionScenario(WebDriverContainer webDriverContainer) {
		super(webDriverContainer);

		dashboardPage = new PccDashboardPage(webDriverContainer.getWebDriver());

		Given("user has a policy {string} with an open redemption requests", (String policyNumber) -> {

			policyNumber = InputPropsUtil.getPropertyValue(policyNumber);
			Thread.sleep(6000);
			redeemablePolicy = findRedeemablePolicy(policyNumber);
			Assert.assertNotNull(redeemablePolicy);
			Assert.assertTrue(redeemablePolicy.hasCta(CallToActionType.STATO_RICHIESTA_RISCATTO.getValue()));
		});

		Then("user sees the {string} tracking", (String redeemStatus) -> {

			redeemStatus = InputPropsUtil.getPropertyValue(redeemStatus);

			if (RedemptionStatusType.PO.getValue().equals(redeemStatus)) {
				Assert.assertTrue(redeemablePolicy.hasRedeemablePOBox());
			}
			PolicyCategoryType category = redeemablePolicy.getInfoBox().getCategoryType();
			DetailPolicy detailPolicy = null;
			if (isClosedStatus(RedemptionStatusType.valueOf(redeemStatus).getValue())) {
				detailPolicy = redeemablePolicy.clickPolicyDetail();
			} else {
				detailPolicy = redeemablePolicy.clickCtaByType(CallToActionType.STATO_RICHIESTA_RISCATTO.getValue());
			}

			detailRedeemablePolicy = PccPolicyFactory.getCategorizedDetailPolicy(webDriverContainer.getWebDriver(),
					detailPolicy, category);

			Assert.assertTrue(
					((LifeAndSavingsDetailPolicy) detailRedeemablePolicy).checkReedemStatusMessage(redeemStatus));

		});

		Given("user has a policy {string} with a closed redemption requests", (String policyNumber) -> {

			policyNumber = InputPropsUtil.getPropertyValue(policyNumber);
			Thread.sleep(6000);
			redeemablePolicy = findRedeemablePolicy(policyNumber);
			Assert.assertNotNull(redeemablePolicy);
			Assert.assertFalse(redeemablePolicy.hasCta(CallToActionType.STATO_RICHIESTA_RISCATTO.getValue()));
		});

		Given("user has a redeemable policy {string} with no open redemption requests", (String policyNumber) -> {

			policyNumber = InputPropsUtil.getPropertyValue(policyNumber);
			Thread.sleep(6000);
			redeemablePolicy = findRedeemablePolicy(policyNumber);
			Assert.assertNotNull(redeemablePolicy);
			Assert.assertFalse(redeemablePolicy.hasCta(CallToActionType.STATO_RICHIESTA_RISCATTO.getValue()));
			Assert.assertTrue(redeemablePolicy.hasCta(CallToActionType.RISCATTA_POLIZZA.getValue()));
		});

		Given("user wants to open a redemption request for a {string} redemption policy", (String partialTotal) -> {

			partialTotal = InputPropsUtil.getPropertyValue(partialTotal);

			redeemablePolicy.clickCtaByType(CallToActionType.RISCATTA_POLIZZA.getValue());
			redemptionPage = new PccRedemptionPage(webDriverContainer.getWebDriver());
			Thread.sleep(6000);
			String redemptionPolicyType = redemptionPage.getRedemptionPolicyType();
			Assert.assertTrue(RedemptionType.valueOf(partialTotal).getValue().equals(redemptionPolicyType));
		});

		When("user selects the redemption partial value {string}", (String redemptionValue) -> {

			redemptionValue = InputPropsUtil.getPropertyValue(redemptionValue);
			redemptionPage.insertAmount(redemptionValue);
		});

		When("user selects the redemption partial value {string} and goes to next step", (String redemptionValue) -> {

			redemptionValue = InputPropsUtil.getPropertyValue(redemptionValue);
			redemptionPage.insertAmount(redemptionValue);
			redemptionPage.goToNextStep();
		});

		When("user checks amount {string} and {string} redemption type and confirms his will",
				(String redemptionValue, String redemptionType) -> {

					redemptionValue = InputPropsUtil.getPropertyValue(redemptionValue);
					redemptionType = InputPropsUtil.getPropertyValue(redemptionType);
					// confermo che il tipo di riscatto sia corretto, e anche il
					// valore
					Assert.assertTrue(redemptionPage.checkFinalAmount(redemptionValue));
					Assert.assertTrue(redemptionPage
							.checkFinalRedemptionType(RedemptionType.valueOf(redemptionType).getLongValue()));

					redemptionPage.confirmChekbox();
					redemptionPage.submitRequest();
				});

		Then("user succesfully opens a redemption request", () -> {

			redemptionPage.isRequestBeingTaken();
			redemptionPage.closeRequestProcess();
		});

		Then("partial redemption request becomes total", () -> {

			Assert.assertTrue(redemptionPage.isTotalRedemptionRequest());

		});

		Then("user can access the redemption request tracking of his policy {string}", (String policyNumber) -> {

			policyNumber = InputPropsUtil.getPropertyValue(policyNumber);

			Assert.assertTrue(dashboardPage.isDashboardPage());
			Thread.sleep(6000);
			redeemablePolicy = findRedeemablePolicy(policyNumber);
			Assert.assertNotNull(redeemablePolicy);
			Assert.assertTrue(redeemablePolicy.hasCta(CallToActionType.STATO_RICHIESTA_RISCATTO.getValue()));
			Assert.assertFalse(redeemablePolicy.hasCta(CallToActionType.RISCATTA_POLIZZA.getValue()));

		});

		When("user consults his renewed policy {string} on dashboard policy list", (String policyNumber) -> {

			policyNumber = InputPropsUtil.getPropertyValue(policyNumber);
			redeemablePolicy = findRedeemablePolicy(policyNumber);
			Assert.assertNotNull(redeemablePolicy);
		});

		Then("user sees the renewed policy status information", () -> {

			Assert.assertTrue(redemptionPage.checkRenewalPolicyBox());
		});

	}

	private PccListPolicy findRedeemablePolicy(String policyNumber) {
		List<PccListPolicy> policyList = dashboardPage.getPolicies();
		PccListPolicy redeemablePol = null;
		for (PccListPolicy policy : policyList) {
			if (policyNumber.equals(policy.getInfoBox().getPolicyNumber())) {
				redeemablePol = policy;
			}
		}
		return redeemablePol;
	}

	private boolean isClosedStatus(String redeemStatus) {

		if (redeemStatus.equals(RedemptionStatusType.NA.getValue())
				|| redeemStatus.equals(RedemptionStatusType.PO.getValue())
				|| redeemStatus.equals(RedemptionStatusType.PK.getValue())) {

			return true;
		}

		return false;
	}

	@After("@Redemption")
	public void endTest(Scenario scenario) throws IOException {

		afterScenario(scenario);
	}
}
