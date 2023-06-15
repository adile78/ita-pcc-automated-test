package gluecode;

/*
@author Di Lembo Annalisa
*/

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import driverManager.WebDriverContainer;
import io.cucumber.datatable.DataTable;
import pccpageobjects.PccTpaPage;
import utilities.PropsUtil;

public class TpaFunctionalityScenario extends PccScenario implements En {

	private PccTpaPage tpaPage;

	public TpaFunctionalityScenario(WebDriverContainer webDriverContainer) {
		super(webDriverContainer);
		webDriverContainer.getWebDriver().manage().window().maximize();

		tpaPage = new PccTpaPage(webDriverContainer.getWebDriver());

		/*
		 * Scenario: Verify Refund Request Tpa Functionality.
		 */
		When("user fills out refund first form:", (DataTable valueDt) -> {
			Map<String, String> refundMap = mapDatatableToMap(valueDt);
			tpaPage.setRefundFirstForm(refundMap);
			Thread.sleep(6000);
		});

		When("user fills out refund second form:", (DataTable valueDt) -> {
			Map<String, String> refundMap = mapDatatableToMap(valueDt);
			tpaPage.setRefundSecondForm(refundMap);
			Thread.sleep(6000);
		});

		/*
		 * Scenario: Verify Request A Reservation Functionality.
		 * 
		 */
		When("user fills out reservation first form:", (DataTable valueDt) -> {
			Map<String, String> reservationMap = mapDatatableToMap(valueDt);
			tpaPage.setReservationFirstForm(reservationMap);
			Thread.sleep(6000);
		});

		When("user fills out reservation second form:", (DataTable valueDt) -> {
			Map<String, String> dailyMap = mapDatatableToMap(valueDt);
			tpaPage.setReservationSecondForm(dailyMap);
		});

		/*
		 * Scenario: Verify Request Check Up Functionality.
		 */

		Given("user wants to require a {string} for health policy", (String string) -> {

			Thread.sleep(5000);
			tpaPage.gotoAreaSalute();
			tpaPage.gotoApriRichiesta();

			tpaPage.clickBox(string);

		});

		When("user fills out check up form:", (DataTable valueDt) -> {

			Map<String, String> checkUpMap = mapDatatableToMap(valueDt);
			tpaPage.setCheckUpForm(checkUpMap);
			Thread.sleep(6000);

		});

		Then("user sees thank you page for {string}", (String typeRequest) -> {

			Assert.assertTrue(tpaPage.isThankYouPage(typeRequest));
			Thread.sleep(5000);
			tpaPage.returnToHomePage(typeRequest);
		});

		/*
		 * Scenario: Verify Request Daily Allowance Functionality
		 */

		When("user fills out daily allowance first tier form:", (DataTable valueDt) -> {

			Map<String, String> dailyMap = mapDatatableToMap(valueDt);
			tpaPage.setDailyAllFirstForm(dailyMap);
			Thread.sleep(5000);

		});

		When("user fills out daily allowance second tier form:", (DataTable valueDt) -> {

			Map<String, String> dailyMap = mapDatatableToMap(valueDt);
			tpaPage.setDailyAllSecondForm(dailyMap);

		});

		When("user sees summary page", () -> {
			Assert.assertTrue(tpaPage.isSummaryPage());
		});

	}

	public Map<String, String> mapDatatableToMap(DataTable in) {
		Map<String, String> inMap = in.asMap(String.class, String.class);
		Map<String, String> out = new LinkedHashMap<String, String>();
		// keySet(): returns a set containing keys of the specified map
		for (String key : inMap.keySet()) {
			String value = PropsUtil.getPropertyValue(inMap.get(key));
			out.put(key, value);
		}
		return out;
	}

	@After("@TpaFunctionality")
	public void endTest(Scenario scenario) throws IOException {

		afterScenario(scenario);
	}

}
