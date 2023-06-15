package pccpageobjects;

/*
@author Di Lembo Annalisa
*/

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import enums.TpaType;
import utilities.PropsUtil;
import utilities.WebElementUtil;

public class PccTpaPage {

	private WebDriver driver;
	private String awfConnectorPortlet;
	private String webFormDisplayPortlet;
	private String claimsProcessPortlet;

	public PccTpaPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickBox(String requestType) {

		String reqType = PropsUtil.getPropertyValue(requestType);
		switch (reqType) {
		case "refund":
			WebElement elem1 = driver.findElement(By.className("button-apertura-pratica"));
			elem1.click();
			break;
		case "reservation":
			WebElement elem2 = driver.findElement(By.className("button-conferma-prenotazione"));
			elem2.click();
			break;
		case "checkup":
			WebElement elem3 = driver.findElement(By.className("button-check-up"));
			elem3.click();
			break;
		case "daily allowance":
			WebElement elem4 = driver.findElement(By.className("button-ottenere-diaria"));
			elem4.click();
			break;
		}
	}

	public void setRefundFirstForm(Map<String, String> params) {
		String day = params.get(TpaType.CALENDAR_DAY.getValue());
		String month = params.get(TpaType.CALENDAR_MONTH.getValue());
		String year = params.get(TpaType.CALENDAR_YEAR.getValue());
		WebElement calendar = driver.findElement(By.id(getClaimsProcessPortlet() + "calendar"));
		calendar.click();
		setDate(day, month, year);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement container_polizze = driver.findElement(By.id(getClaimsProcessPortlet() + "container-polizze"));
		List<WebElement> card_polizza = container_polizze.findElements(By.className("card-polizza"));
		for (WebElement card : card_polizza) {
			String policy_number = card.findElement(By.className("policy-number")).getText();
			String refundPolicyNumber = PropsUtil.getPropertyValue("refund.policyNumber");
			if (StringUtils.equals(refundPolicyNumber, policy_number)) {
				card.click();
				break;
			}

		}

		Select insuredFiscalCode = new Select(
				driver.findElement(By.id(getClaimsProcessPortlet() + "insuredFiscalCode")));
		insuredFiscalCode.selectByVisibleText(params.get(TpaType.IS_ASSISTED.getValue()));

		Select damageTypeId = new Select(driver.findElement(By.id(getClaimsProcessPortlet() + "damageTypeId")));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		damageTypeId.selectByVisibleText(params.get(TpaType.SERVICE.getValue()));

		WebElement submit = driver.findElement(By.id(getClaimsProcessPortlet() + "submit-prosegui"));
		submit.click();

		String tpaService = params.get(TpaType.SERVICE.getValue());
		if (StringUtils.equals(tpaService, "Medicinali")) {
			WebElement submit_medicinal = driver
					.findElement(By.id(getClaimsProcessPortlet() + "submit-medicinal-prosegui"));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			submit_medicinal.click();
		}

		String tpaUsername = PropsUtil.getPropertyValue("tpa.username");
		if (StringUtils.equals(tpaUsername, "tpa_retail_01@test.it")
				|| StringUtils.equals(tpaUsername, "tpa_retail_001@test.it")
				|| StringUtils.equals(tpaUsername, "tpa_retail_002@test.it")) {
			WebElement modCf = driver.findElement(By.className("modal-footer"));
			modCf.findElement(By.tagName("button")).click();

		}

	}

	public void setReservationFirstForm(Map<String, String> params) {
		String day = params.get(TpaType.CALENDAR_DAY.getValue());
		String month = params.get(TpaType.CALENDAR_MONTH.getValue());
		String year = params.get(TpaType.CALENDAR_YEAR.getValue());
		WebElement calendar = driver.findElement(By.id(getClaimsProcessPortlet() + "calendar"));
		calendar.click();
		setDate(day, month, year);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement container_polizze = driver.findElement(By.id(getClaimsProcessPortlet() + "container-polizze"));
		List<WebElement> card_polizza = container_polizze.findElements(By.className("card-polizza"));
		for (WebElement card : card_polizza) {
			String policy_number = card.findElement(By.className("policy-number")).getText();
			String reservationPolicyNumber = PropsUtil.getPropertyValue("reservation.policyNumber");
			if (StringUtils.equals(reservationPolicyNumber, policy_number)) {
				card.click();
				break;
			}
		}

		Select insuredFiscalCode = new Select(
				driver.findElement(By.id(getClaimsProcessPortlet() + "insuredFiscalCode")));
		insuredFiscalCode.selectByVisibleText(params.get(TpaType.IS_ASSISTED.getValue()));

		Select damageTypeId = new Select(driver.findElement(By.id(getClaimsProcessPortlet() + "damageTypeId")));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		damageTypeId.selectByVisibleText(params.get(TpaType.SERVICE.getValue()));

		WebElement submit = driver.findElement(By.id(getClaimsProcessPortlet() + "submit-prosegui"));
		submit.click();

	}

	public void setRefundSecondForm(Map<String, String> params) {
		Select eventCategories = new Select(
				driver.findElement(By.id(getClaimsProcessPortlet() + "eventCategories_43_EXO_5_input")));
		eventCategories.selectByVisibleText(params.get(TpaType.EVENT_CATEGORIES.getValue()));

		WebElement chkbox = driver.findElement(By.name(getClaimsProcessPortlet() + "asset_338_checkbox"));
		chkbox.click();

		WebElement radio_container = driver.findElement(By.className("radio-container"));
		List<WebElement> radio_list = radio_container.findElements(By.className("axa-radio-button"));
		radio_list.get(1).click();

		Select structureType = new Select(
				driver.findElement(By.id(getClaimsProcessPortlet() + "claim_structureType_input")));
		structureType.selectByVisibleText(params.get(TpaType.STRUCTURE_TYPE.getValue()));

		Select documentType = new Select(
				driver.findElement(By.id(getClaimsProcessPortlet() + "document_container_medical_documentType_input")));
		documentType.selectByVisibleText(params.get(TpaType.DOCUMENT_TYPE.getValue()));

		WebElement documentFile = driver
				.findElement(By.id(getClaimsProcessPortlet() + "document_container_medical_documentType_file"));
		documentFile.sendKeys(params.get(TpaType.FILE_UPLOAD.getValue()));

		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Select expense_document = new Select(
				driver.findElement(By.id(getClaimsProcessPortlet() + "document_container_expense_documentType_input")));
		expense_document.selectByVisibleText(params.get(TpaType.DOCUMENT_EXPENSE.getValue()));

		WebElement expense_amount = driver
				.findElement(By.id(getClaimsProcessPortlet() + "document_container_expense_amount_input"));
		expense_amount.sendKeys(params.get(TpaType.AMOUNT_EXPENSE.getValue()));

		WebElement documentFileExp = driver
				.findElement(By.id(getClaimsProcessPortlet() + "document_container_expense_documentType_file"));
		documentFileExp.sendKeys(params.get(TpaType.FILE_UPLOAD.getValue()));

		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		WebElement numberExp = driver
				.findElement(By.id(getClaimsProcessPortlet() + "document_container_expense_number_input"));
		numberExp.sendKeys(params.get(TpaType.NUMBER_EXPENSE.getValue()));

		String dayExp = params.get(TpaType.DAY_EXPENSE.getValue());
		String monthExp = params.get(TpaType.MONTH_EXPENSE.getValue());
		String yearExp = params.get(TpaType.YEAR_EXPENSE.getValue());
		WebElement expense_date = driver
				.findElement(By.id(getClaimsProcessPortlet() + "document_container_expense_date_input"));
		expense_date.click();
		setDate(dayExp, monthExp, yearExp);

		WebElement iban = driver.findElement(By.id(getClaimsProcessPortlet() + "claim_iban_input"));
		iban.sendKeys(params.get(TpaType.IBAN.getValue()));

		WebElement phone = driver.findElement(By.id(getClaimsProcessPortlet() + "claim_phone_phone"));
		phone.sendKeys(params.get(TpaType.PHONE.getValue()));

		driver.findElement(By.id(getClaimsProcessPortlet() + "submit-prosegui")).click();
	}

	public void setReservationSecondForm(Map<String, String> params) {
		Select eventCategories = new Select(
				driver.findElement(By.id(getClaimsProcessPortlet() + "eventCategories_34_RI_4_input")));
		eventCategories.selectByVisibleText(params.get(TpaType.EVENT_CATEGORIES.getValue()));

		WebElement checkbox = driver.findElement(By.name(getClaimsProcessPortlet() + "asset_318_checkbox"));
		checkbox.click();

		Select structureType = new Select(
				driver.findElement(By.id(getClaimsProcessPortlet() + "claim_structureType_input")));
		structureType.selectByVisibleText(params.get(TpaType.STRUCTURE_TYPE.getValue()));

		WebElement structure = driver.findElement(By.id(getClaimsProcessPortlet() + "claim_structure_3_autocomplete"));
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		structure.sendKeys("istit");
		WebElement autoOptions = driver.findElement(By.id("undefinedautocomplete-list"));
		List<WebElement> option = autoOptions.findElements(By.tagName("div"));
		option.get(2).click();

		WebElement doctor = driver.findElement(By.id(getClaimsProcessPortlet() + "claim_doctor_input"));
		doctor.sendKeys(params.get(TpaType.DOCTOR.getValue()));

		Select document_container = new Select(
				driver.findElement(By.id(getClaimsProcessPortlet() + "document_container_documentType_input")));
		document_container.selectByVisibleText(params.get(TpaType.DOCUMENT_TYPE.getValue()));

		WebElement documentType = driver
				.findElement(By.id(getClaimsProcessPortlet() + "document_container_documentType_file"));
		documentType.sendKeys(params.get(TpaType.FILE_UPLOAD.getValue()));

		WebElement phone = driver.findElement(By.id(getClaimsProcessPortlet() + "claim_phone_phone"));
		phone.sendKeys(params.get(TpaType.PHONE.getValue()));

		WebElement mail_input = driver.findElement(By.id(getClaimsProcessPortlet() + "claim_mail_input"));
		String reservationMail = PropsUtil.getPropertyValue("reservation.mail");
		String actualMail = mail_input.getAttribute("value");
		StringUtils.equals(reservationMail, actualMail);

		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id(getClaimsProcessPortlet() + "submit-prosegui")).click();

	}

	public void setCheckUpForm(Map<String, String> params) {

		Select policyId = new Select(driver.findElement(By.id(getAwfConnectorPortlet() + "policyId")));
		policyId.selectByVisibleText(params.get(TpaType.POLICY_ID.getValue()));

		WebElement fileUpload = driver.findElement(By.id(getWebFormDisplayPortlet() + "_file"));
		fileUpload.sendKeys(params.get(TpaType.FILE_UPLOAD.getValue()));

		WebElement description = driver.findElement(By.id(getWebFormDisplayPortlet() + "description"));
		description.sendKeys(params.get(TpaType.DESCRIPTION.getValue()));

		// WebElement email =
		// driver.findElement(By.id(getWebFormDisplayPortlet() +
		// "emailaddress"));
		// PropsUtil.getPropertyValue("checkup.emailaddress").equals(email.getAttribute("value"));

		// WebElement phone =
		// driver.findElement(By.id(getWebFormDisplayPortlet() + "phone"));
		// phone.sendKeys(params.get(TpaType.PHONE.getValue()));

		String day = params.get(TpaType.RECONTACT_DAY.getValue());
		String month = params.get(TpaType.RECONTACT_MONTH.getValue());
		String year = params.get(TpaType.RECONTACT_YEAR.getValue());
		WebElement recontactDate = driver.findElement(By.id(getWebFormDisplayPortlet() + "recontactdate"));
		recontactDate.click();
		setDate(day, month, year);
		setTime(params.get(TpaType.TIME.getValue()));

		driver.findElement(By.tagName("button")).submit();

	}

	public void setDailyAllFirstForm(Map<String, String> params) {

		// Data inizio diaria
		String start_day = params.get(TpaType.CALENDAR_START_DAY.getValue());
		String start_month = params.get(TpaType.CALENDAR_START_MONTH.getValue());
		String start_year = params.get(TpaType.CALENDAR_START_YEAR.getValue());
		WebElement calendarStart = driver.findElement(By.id(getClaimsProcessPortlet() + "calendarStart"));
		calendarStart.click();
		setDate(start_day, start_month, start_year);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		// Data fine diaria
		String end_day = params.get(TpaType.CALENDAR_END_DAY.getValue());
		String end_month = params.get(TpaType.CALENDAR_END_MONTH.getValue());
		String end_year = params.get(TpaType.CALENDAR_END_YEAR.getValue());
		WebElement calendarEnd = driver.findElement(By.id(getClaimsProcessPortlet() + "calendarEnd"));
		calendarEnd.click();
		setDate(end_day, end_month, end_year);

		// Select policy = new
		// Select(driver.findElement(By.id(getClaimsProcessPortlet() +
		// "policy")));
		// policy.selectByVisibleText(params.get(TpaType.POLICY_ID.getValue()));

		WebElement container_polizze = driver.findElement(By.id(getClaimsProcessPortlet() + "container-polizze"));
		List<WebElement> card_polizza = container_polizze.findElements(By.className("card-polizza"));
		for (WebElement card : card_polizza) {
			String policy_number = card.findElement(By.className("policy-number")).getText();
			String dailyPolicyNumber = PropsUtil.getPropertyValue("daily.policyNumber");
			if (StringUtils.equals(dailyPolicyNumber, policy_number)) {
				card.click();
			}
		}

		Select insuredFiscalCode = new Select(
				driver.findElement(By.id(getClaimsProcessPortlet() + "insuredFiscalCode")));
		insuredFiscalCode.selectByVisibleText(params.get(TpaType.IS_ASSISTED.getValue()));

		Select damageTypeId = new Select(driver.findElement(By.id(getClaimsProcessPortlet() + "damageTypeId")));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		damageTypeId.selectByVisibleText(params.get(TpaType.SERVICE.getValue()));

		WebElement submit = driver.findElement(By.id(getClaimsProcessPortlet() + "submit-prosegui"));
		submit.click();

		String dailyMail = PropsUtil.getPropertyValue("daily.email");
		if (StringUtils.equals(dailyMail, "tpa_retail_01@test.it")
				|| StringUtils.equals(dailyMail, "tpa_retail_001@test.it")
				|| StringUtils.equals(dailyMail, "tpa_retail_002@test.it")) {
			WebElement modFooter = driver.findElement(By.className("modal-footer"));
			WebElement btn = modFooter.findElement(By.tagName("button"));
			btn.click();
		}

	}

	public void setDailyAllSecondForm(Map<String, String> params) {

		Select eventCategories = new Select(
				driver.findElement(By.id(getClaimsProcessPortlet() + "eventCategories_51_EXO_6_input")));
		eventCategories.selectByVisibleText(params.get(TpaType.EVENT_CATEGORIES.getValue()));

		Select assetInput = new Select(driver.findElement(By.id(getClaimsProcessPortlet() + "asset_359_input")));
		assetInput.selectByVisibleText(params.get(TpaType.ASSET_INPUT.getValue()));

		Select documentType = new Select(
				driver.findElement(By.id(getClaimsProcessPortlet() + "document_container_medical_documentType_input")));
		documentType.selectByVisibleText(params.get(TpaType.DOCUMENT_TYPE.getValue()));

		WebElement fileUpload = driver
				.findElement(By.id(getClaimsProcessPortlet() + "document_container_medical_documentType_file"));
		fileUpload.sendKeys(params.get(TpaType.FILE_UPLOAD.getValue()));

		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement claim_iban = driver.findElement(By.id(getClaimsProcessPortlet() + "claim_iban_input"));
		claim_iban.sendKeys(params.get(TpaType.IBAN.getValue()));

		WebElement phone = driver.findElement(By.id(getClaimsProcessPortlet() + "claim_phone_phone"));
		phone.sendKeys(params.get(TpaType.PHONE.getValue()));

		WebElement submit = driver.findElement(By.id(getClaimsProcessPortlet() + "submit-prosegui"));
		submit.click();

	}

	public void gotoAreaSalute() {

		driver.get(PropsUtil.getPropertyValue(System.getProperty("environment"))
				+ PropsUtil.getPropertyValue("url.page.health"));
	}

	public void gotoApriRichiesta() {

		driver.get(PropsUtil.getPropertyValue(System.getProperty("environment"))
				+ PropsUtil.getPropertyValue("url.page.health.open.request"));
	}

	public void setDate(String dateDay, String dateMonth, String dateYear) {

		WebElement nextMonth_arrow = driver.findElement(By.className("yui3-calendarnav-nextmonth"));
		String textMonth = driver.findElement(By.className("yui3-calendar-header-label")).getText();
		textMonth = textMonth.replace(" 2020", "");

		while (!textMonth.equals(dateMonth)) {
			nextMonth_arrow.click();
			textMonth = driver.findElement(By.className("yui3-calendar-header-label")).getText();
			textMonth = textMonth.replace(" 2020", "");
		}

		findDay(dateDay).click();
	}

	public WebElement findDay(String inDay) {

		WebElement table = driver.findElement(By.className("yui3-calendar-grid"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		for (int i = 1; i < rows.size(); i++) {
			List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
			for (int j = 0; j < columns.size(); j++) {
				String day = columns.get(j).getText();
				if (day.equals(inDay))
					return columns.get(j);
			}

		}
		return null;

	}

	public void setTime(String timeValue) {

		WebElement time = driver.findElement(By.id(webFormDisplayPortlet + "recontacttime"));
		time.sendKeys(timeValue);
	}

	private WebElement getContainer(String typeReq) {

		String typeRequest = PropsUtil.getPropertyValue(typeReq);
		if (StringUtils.equals(typeRequest, "checkup"))
			return driver.findElement(By.className("checkup-status-OK"));
		else if (StringUtils.equals(typeRequest, "daily allowance") || StringUtils.equals(typeRequest, "reservation"))
			return driver.findElement(By.className("icona-check"));
		return null;

	}

	public boolean isThankYouPage(String typeReq) {

		return getContainer(typeReq) != null ? true : false;
	}

	public void returnToHomePage(String typeReq) {

		if ("checkup".equals(PropsUtil.getPropertyValue(typeReq))) {
			WebElement homePage = driver.findElement(By.linkText("HOMEPAGE"));
			homePage.click();
		}

		else if ("daily allowance".equals(PropsUtil.getPropertyValue(typeReq))
				|| "reservation".equals(PropsUtil.getPropertyValue(typeReq))){
			WebElement homePage = driver.findElement(By.linkText("AREA SALUTE"));
			homePage.click();
		}
	}

	public String getAwfConnectorPortlet() {

		if (StringUtils.isBlank(awfConnectorPortlet)) {

			WebElement awfConn = driver
					.findElement(By.className("portlet-boundary_com_axa_pcc_awfconnector_AWFConnectorPortlet_"));
			awfConnectorPortlet = awfConn.getAttribute("id").replace("p_p_id", "");
		}
		return awfConnectorPortlet;
	}

	public String getWebFormDisplayPortlet() {

		if (StringUtils.isBlank(webFormDisplayPortlet)) {

			WebElement awfConn = driver
					.findElement(By.className("portlet-boundary_it_smc_awf_AdvancedWebFormDisplayPortlet_"));
			webFormDisplayPortlet = awfConn.getAttribute("id").replace("p_p_id", "");
		}
		return webFormDisplayPortlet;

	}

	public String getClaimsProcessPortlet() {

		if (StringUtils.isBlank(claimsProcessPortlet)) {

			WebElement portletSection = driver.findElement(
					By.className("portlet-boundary_com_axa_portal_ita_pcc_claims_process_ClaimsProcessPortlet_"));
			claimsProcessPortlet = portletSection.getAttribute("id").replace("p_p_id", "");
		}
		return claimsProcessPortlet;
	}

	public boolean isSummaryPage() {

		WebElement roadmap_container = driver.findElement(By.className("roadmap-container"));
		List<WebElement> list_circle = roadmap_container.findElements(By.className("roadmap--circle"));
		if (WebElementUtil.hasClass(list_circle.get(2), "activated")) {
			WebElement submit = driver.findElement(By.id(getClaimsProcessPortlet() + "submit-prosegui"));
			submit.click();
			return true;
		}

		return false;
	}

}
