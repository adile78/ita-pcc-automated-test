package gluecode;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import driverManager.WebDriverContainer;
import factories.PccClaimPageFactory;
import io.cucumber.datatable.DataTable;
import pageobjects.ClaimPage;
import pccpageobjects.PccDashboardPage;
import utilities.PropsUtil;
import utilities.InputPropsUtil;

public class KascoOpeningScenario extends PccScenario implements En {

	private PccDashboardPage dashboardPage;
	private ClaimPage claimPage;

	public KascoOpeningScenario(WebDriverContainer webDriverContainer) {
		super(webDriverContainer);

		dashboardPage = new PccDashboardPage(webDriverContainer.getWebDriver());

		Given("user wants to open a {string} claim", (String claimType) -> {

			claimType = PropsUtil.getPropertyValue(claimType);

			claimPage = PccClaimPageFactory.getCategorizedListPolicy(webDriverContainer.getWebDriver(), claimType);
			dashboardPage.gotoAreaSinistri();
			claimPage.startClaimOpening();
		});

		When("user inserts all {string} claim first step details:", (String claimType, DataTable claimValueDt) -> {

			claimType = PropsUtil.getPropertyValue(claimType);
			
			Map<String, String> claimValueMap =  mapDatatableToMap(claimValueDt);
			claimPage.setClaimFirstDetails(claimValueMap);
			
			Assert.assertTrue(claimPage.isPlateBoxVisibile());

			claimPage.setPolicyAndCoverageType(claimValueMap, claimType);

		});

		When("user selects documents and events details validating them:", (DataTable claimValueDt) -> {

			Map<String, String> claimValueMap =  mapDatatableToMap(claimValueDt);
			claimPage.secondStepClaimOpening(claimValueMap);
		});

		When("user inserts claim last details:", (DataTable claimValueDt) -> {
			
			Map<String, String> claimValueMap =  mapDatatableToMap(claimValueDt);
			claimPage.setLastDetailsClaimOpening(claimValueMap);
		});

		Then("user save his claim", () -> {

			claimPage.saveFinalClaim();
			Assert.assertFalse(claimPage.isClaimOpeningSucceded());
		});
	}

	private Map<String, String> mapDatatableToMap(DataTable in) {
		Map<String, String> inMap = in.asMap(String.class, String.class);
		Map<String, String> out = new LinkedHashMap<String, String>();
		for (String key : inMap.keySet()) {
			String value =  InputPropsUtil.getPropertyValue(inMap.get(key));
			out.put(key, value);
		}
		return out;
	}

	@After({ "@KaskoOpening, @RcaOpening" })
	public void endTest(Scenario scenario) throws IOException {

		afterScenario(scenario);
	}
}
