package gluecode;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import driverManager.WebDriverContainer;
import pccpageobjects.PccContactsPage;
import pccpageobjects.PccDashboardPage;
import utilities.PropsUtil;

public class ContactsScenario extends PccScenario implements En {

	private PccDashboardPage dashboardPage;
	private PccContactsPage contactsPage;

	public ContactsScenario(WebDriverContainer webDriverContainer) {
		super(webDriverContainer);

		dashboardPage = new PccDashboardPage(webDriverContainer.getWebDriver());
		contactsPage = new PccContactsPage(webDriverContainer.getWebDriver());
		
		When("user accesses his contacts section", () -> {
			dashboardPage.gotoContatti();
		});

		/**
		 * Scenario: Single company person user sees only his company link to the webform in Contatti page
		 * 
		 */
		
		Then("single company person user sees only his {string} link to the webform", (String company) -> {
			
			Assert.assertTrue(contactsPage.isSingleCompany());			
			
			List<String> contactsType = contactsPage.getContactsType();
			Assert.assertTrue(contactsType.size() == 1);			
			Assert.assertTrue(contactsType.contains(PropsUtil.getPropertyValue(company)));
		});

		/**
		 * Scenario: Single company organization sees only his company link to the webform in Contatti page
		 * 
		 */
		
		Then("single company organization sees only his {string} link to the webform", (String company) -> {
			
			Assert.assertTrue(contactsPage.isSingleCompany());
			
			List<String> contactsType = contactsPage.getContactsType();			
			Assert.assertTrue(contactsType.size() == 1);
			Assert.assertTrue(contactsType.contains(PropsUtil.getPropertyValue(company)));
		});

		/**
		 * Scenario: Multi company person user sees only his company links to the webforms in Contatti page
		 * 
		 */
		
		Then("multi company person user sees only his {string} links to the webforms", (String companies) -> {
			
			Assert.assertTrue(contactsPage.isMultiCompany());
			
			List<String> contactsType = contactsPage.getContactsType();
			List<String> companiesList = PropsUtil.getPropertyValues(companies);
			Assert.assertTrue(contactsType.size() == companiesList.size());
			for (String company: companiesList)
				Assert.assertTrue(contactsType.contains(company));
		});

		/**
		 * Scenario: Multi company organization sees only his company links to the webforms in Contatti page
		 * 
		 */
		
		Then("multi company organization sees only his {string} links to the webforms", (String companies) -> {
			
			Assert.assertTrue(contactsPage.isMultiCompany());
			
			List<String> contactsType = contactsPage.getContactsType();
			List<String> companiesList = PropsUtil.getPropertyValues(companies);
			Assert.assertTrue(contactsType.size() > companiesList.size());
			for (String company: companiesList)
				Assert.assertTrue(contactsType.contains(company));
		});
	}

	@After({ "@Contacts" })
	public void endTest(Scenario scenario) throws IOException {

		afterScenario(scenario);
	}
}
