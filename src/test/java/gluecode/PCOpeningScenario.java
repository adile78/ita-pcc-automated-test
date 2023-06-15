package gluecode;

import java.io.IOException;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import driverManager.WebDriverContainer;

public class PCOpeningScenario extends PccScenario implements En {

	public PCOpeningScenario(WebDriverContainer webDriverContainer) {
		super(webDriverContainer);

		Given("user with P&C policy wants to open a {string} claim", (String string) -> {
			// Write code here that turns the phrase above into concrete actions
			throw new cucumber.api.PendingException();
		});

		When("user inserts all {string} claim details:", (String string, io.cucumber.datatable.DataTable dataTable) -> {
			// Write code here that turns the phrase above into concrete actions
			// For automatic transformation, change DataTable to one of
			// E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
			// Map<K, List<V>>. E,K,V must be a String, Integer, Float,
			// Double, Byte, Short, Long, BigInteger or BigDecimal.
			//
			// For other transformations you can register a DataTableType.
			throw new cucumber.api.PendingException();
		});

		Then("user saves his claim", () -> {
			// Write code here that turns the phrase above into concrete actions
			throw new cucumber.api.PendingException();
		});
	}

	@After({ "@P&CClaimOpening" })
	public void endTest(Scenario scenario) throws IOException {

		afterScenario(scenario);
	}

}
