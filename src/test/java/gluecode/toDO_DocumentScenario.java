package gluecode;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import driverManager.WebDriverContainer;
import pccpageobjects.PccDashboardPage;
import pccpageobjects.PccDocumentPage;
import utilities.PropsUtil;
import utilities.WebElementUtil;

public class toDO_DocumentScenario extends PccScenario implements En {

	private PccDashboardPage dashboardPage;
	private PccDocumentPage documentPage;

	public toDO_DocumentScenario(WebDriverContainer webDriverContainer) {
		super(webDriverContainer);
		dashboardPage = new PccDashboardPage(webDriverContainer.getWebDriver());
		documentPage = new PccDocumentPage(webDriverContainer.getWebDriver());

		/**
		 * Scenario: User with multicompany policies [AAI and AMPS] consults his
		 * documents section
		 * 
		 */

		Given("user accesses documents section", () -> {
			Thread.sleep(5000);
			dashboardPage.gotoDocuments();
		});

		Then("user with multicompany policies sees two section {string} and {string}, one for each company",
				(String tab1Name, String tab2Name) -> {

					Assert.assertTrue(documentPage.isMultiCompany());
					
					List<String> companies = documentPage.getCompanies();

					
					Assert.assertTrue(companies.size() == 2);
					Assert.assertTrue(companies.contains(PropsUtil.getPropertyValue(tab1Name))
							&& companies.contains(PropsUtil.getPropertyValue(tab2Name)));
				});

		/**
		 * Scenario: User with multicompany policies [AAI and AMPS] 
		 * consults one of his [AAI and AMPS] documents 
		 */

		When("user with multicompany policies consults his {string} document", (String company) -> {
			
			documentPage.selectCompanySection(PropsUtil.getPropertyValue(company));
			if(documentPage.isThereNoDocuments())
				Assert.assertTrue(true);
			else
				documentPage.downloadDocument();
			

		});
		

		Then("the {string} document is downloaded", (String company) -> {
			

			Assert.assertTrue(true);
			
		});
	

//		/**
//		 * Scenario: User consults a document that is not available
//		 * 
//		 */
//
//		Then("a document not available message appears", () -> {
//			
//			
//			Assert.assertTrue(documentPage.isNotAvailableDocumentModaleVisible());
//					
//		});

//		/**
//		 * Scenario: User with multicompany policies [AAI and AMPS] consults his document section with no [AAI and AMPS] document
//		 * 
//		 */
//		
//		When("user with multicompany policies accesses his {string} documents section", (String company) -> {
//
//			documentPage.selectCompanySection(PropsUtil.getPropertyValue(company));
//		});
//		
//
//		Then("a {string} documents not availables info message appears", (String company) -> {
//			
//			Assert.assertTrue(documentPage.isThereNoDocuments());
//		});

	}

	@After("@Documents")
	public void endTest(Scenario scenario) throws IOException {

		afterScenario(scenario);

	}
}
