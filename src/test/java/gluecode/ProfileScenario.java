package gluecode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java8.En;
import driverManager.WebDriverContainer;
import enums.PrivacyType;
import pccpageobjects.PccDashboardPage;
import pccpageobjects.PccProfilePage;
import utilities.InputPropsUtil;

public class ProfileScenario extends PccScenario implements En {

	private PccDashboardPage dashboardPage;
	private PccProfilePage profilePage;

	public ProfileScenario(WebDriverContainer webDriverContainer) {
		super(webDriverContainer);

		dashboardPage = new PccDashboardPage(webDriverContainer.getWebDriver());
		profilePage = new PccProfilePage(webDriverContainer.getWebDriver());

		When("user accesses his profile section", () -> {

			dashboardPage.gotoProfilo();
		});

		/**
		 * Scenario Outline: <companyName> user consults his access data in the
		 * profile section
		 */

		/**
		 * Scenario Outline: Multi company, not AAI, <companyName1>
		 * <companyName2> user consults his access data in the profile section
		 */

		Then("user consults his access data", () -> {

			Assert.assertTrue(profilePage.checkProgressBar());
			Assert.assertTrue(profilePage.checkAccesDataDetails());

		});

		/**
		 * Scenario Outline: <companyName> user modifies his password in the
		 * profile section
		 */

		/**
		 * Scenario Outline: Multi company, not AAI, <companyName1>
		 * <companyName2> user modifies his password in the single profile
		 * section
		 */

		Then("user inserts a {string} and succesfully modifies his {string}",
				(String newPassword, String oldPassword) -> {

					newPassword = InputPropsUtil.getPropertyValue(newPassword);
					oldPassword = InputPropsUtil.getPropertyValue(oldPassword);

					profilePage.setNewPasswordProcess(oldPassword, newPassword, newPassword);

					Assert.assertTrue(profilePage.isSetPswdOkModaleVisible());
				});

		/**
		 * Scenario Outline: AAI company user consults his personal contacts
		 * data in the profile section
		 */

		/**
		 * Scenario Outline: Multi company AAI <companyName> user consults his
		 * personal contacts data in the AAI profile section
		 */

		Then("user consults his personal contacts data", () -> {

			profilePage.checkContactsDetails();
		});

		/**
		 * Scenario Outline: Mono NDG AAI company user modifies his contacts
		 * data in the profile section
		 */

		/**
		 * Scenario Outline: Multi company mono NDG AAI <companyName> user
		 * modifies his contacts data in the AAI profile section
		 */

		Then("user modifies his contacts data {string} {string} {string}",
				(String newNumber, String mail, String mailConfirmation) -> {

					profilePage.setNewContactDataProcess(newNumber, mail, mailConfirmation);

					Assert.assertTrue(profilePage.isSetContactDataOKModaleVisible());
				});

		/**
		 * Scenario Outline: Multi NDG AAI company user can not view his
		 * contacts data in the profile section
		 */

		/**
		 * Scenario Outline: Multi company multi NDG AAI <companyName> user can
		 * not modify his contacts data in the AAI profile section
		 */

		Then("user can not view his contacts data", () -> {

			Assert.assertFalse(profilePage.isContactsSectionVisibile());
		});

		/**
		 * Scenario Outline: AAI company user consults his agreements in the
		 * profile section
		 */

		/**
		 * Scenario Outline: Multi company AAI <companyName> user consults his
		 * agreements in the AAI profile section
		 */

		Then("user consults his agreements", () -> {
			
			Assert.assertTrue(profilePage.checkAgreements());
		});

		/**
		 * Scenario Outline: Mono NDG AAI company user modifies his agreements
		 * in the profile section
		 */

		/**
		 * Scenario Outline: Multi company mono NDG AAI <companyName> user
		 * modifies his agreements in the AAI profile section
		 */

		/**
		 * Scenario Outline: Multi company multi NDG AAI <companyName> user can
		 * not modify his agreements in the profile section
		 */

		Then("user modifies his agreements", () -> {
			
			Assert.assertTrue(profilePage.checkAgreements());
			
			Map<PrivacyType, Integer> agreementsList = new HashMap<PrivacyType, Integer>();
			agreementsList.put(PrivacyType.PROMOZIONI, 0);
			agreementsList.put(PrivacyType.RICERCHE_MERCATO, 1);
			agreementsList.put(PrivacyType.VENDITA_RICERCHE_MERCATO, 1);
			agreementsList.put(PrivacyType.FIRMA_GRAFOMETRICA, 0);
			
			profilePage.modifyAgreements(agreementsList);
			//TODO
		});

		/**
		 * Scenario Outline: Multi NDG AAI company user can not modify his
		 * agreements in the profile section
		 */

		Then("user can not modify his agreements", () -> {

			Assert.assertTrue(profilePage.checkAgreements());
			
			Assert.assertFalse(profilePage.isSaveBtnVisibile());
		});

		/**
		 * Scenario Outline: Not AAI company user can only consults his access
		 * data in the profile section
		 */

		Then("user can only consults his access data", () -> {
			
			Assert.assertTrue(profilePage.checkAccesDataDetails());
			
			Assert.assertFalse(profilePage.isContactsSectionVisibile());

			Assert.assertFalse(profilePage.isAgreementsSectionVisibile());
		});

		/**
		 * Scenario Outline: Multi company AAI <companyName> user consults his
		 * access data in the AAI profile section
		 */

		When("user accesses his AAI profile section", () -> {
			// Write code here that turns the phrase above into concrete actions
			throw new cucumber.api.PendingException();
		});

		/**
		 * Scenario Outline: Multi company AAI <companyName> user modifies his
		 * password in the AAI profile section
		 */

		When("user accesses his {string} profile section", (String string) -> {
			// Write code here that turns the phrase above into concrete actions
			throw new cucumber.api.PendingException();
		});

		Then("user sees only a paragraph with information about his profile", () -> {
			// Write code here that turns the phrase above into concrete actions
			throw new cucumber.api.PendingException();
		});

		/**
		 * Scenario Outline: Multi company AAI <companyName> user consults his
		 * personal contacts data in the AAI profile section
		 */

		/**
		 * Scenario Outline: Multi company AAI <companyName> user consults his
		 * <companyName> profile section with only an information paragraph
		 */

		Then("user modifies his password", () -> {
			// Write code here that turns the phrase above into concrete actions
			throw new cucumber.api.PendingException();
		});

	}

	@After("@Profile")
	public void endTest(Scenario scenario) throws IOException {

		afterScenario(scenario);
	}
}
