package pccpageobjects;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import enums.RedemptionType;
import enums.RedemptionValueType;
import pageobjects.Modale;
import pccelementobjects.PccModale;
import utilities.WebElementUtil;

public class PccRedemptionPage {

	protected WebDriver driver;

	protected static Logger logger = LogManager.getLogger();

	private Modale redemtionErrorModale;

	public PccRedemptionPage(WebDriver driver) {

		this.driver = driver;
		String modaleId = "ModalErrorRiscatto";
		redemtionErrorModale = new PccModale(driver, modaleId, false);
	}

	private WebElement getContainer() {

		return driver.findElement(By.className("step-container--body"));
	}

	// importo riscatto input text
	private WebElement getInputAmount() {

		return getContainer().findElement(By.id("inputValoreRiscatto"));
	}

	// valore della soglia di riscatto minima
	private String getThreshold() {

		WebElement thresholdContainer = getContainer().findElement(By.id("soglia_container"));
		return thresholdContainer.findElement(By.tagName("p")).getText();
	}

	// valore della soglia di riscatto minima
	private String getRedemptionMaxValue() {

		WebElement maxValueContainer = getContainer().findElement(By.id("sogliaTot_container"));
		return maxValueContainer.findElements(By.tagName("p")).get(1).getText();
	}

	private WebElement getAlertTotalRedemption() {

		return getContainer().findElement(By.className("soglia-alert-container"));
	}

	private WebElement getRowInfoContainer() {

		return getContainer().findElement(By.className("row-info-container"));
	}

	// messaggio finale prima del conferma per riscatti totali
	private WebElement getFinalTotalRedemptionMessage() {

		return getContainer().findElement(By.className("row-message-parziale-totale"));
	}

	private void escRedemptionErrorModale() {

		redemtionErrorModale.clickBtn();
	}

	private WebElement getFinalPageBox(String boxType) {

		WebElement infoBox = getContainer().findElement(By.className("row-info-parziale-totale"));
		List<WebElement> infoBoxList = infoBox.findElements(By.className("info-box"));
		for (WebElement el : infoBoxList) {
			if (el.getText() != null && el.getText().contains(boxType))
				return el;
		}
		return null;
	}

	public void insertAmount(String value) {

		// String js =
		// "document.getElementById('inputValoreRiscatto').classList.remove('ng-hide')";
		String js = "angular.element(document.getElementsByClassName('box-number')).triggerHandler('click')";
		((JavascriptExecutor) driver).executeScript(js);
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement inputAmount = getInputAmount();
		inputAmount.sendKeys(value);
	}

	public String checkRedemptionRequestType() {

		WebElement inputAmount = getInputAmount();
		String amountValue = inputAmount.getText();
		String threshold = getThreshold();
		return null;
	}

	public void goToNextStep() {

		WebElement nextButton = getContainer().findElement(By.id("submit-prosegui"));
		nextButton.click();
	}

	public void confirmChekbox() {

		WebElement nextButton = getContainer().findElement(By.id("checkbox"));
		nextButton.click();
	}

	public void submitRequest() {

		WebElement submitButton = getContainer().findElement(By.id("submit-conferma"));
		submitButton.click();
	}

	public void closeRequestProcess() {

		WebElement closeButton = getContainer().findElement(By.id("submit-chiudi"));
		closeButton.click();
	}

	public boolean isRedemptionErrorModaleVisibile() {

		return redemtionErrorModale.isModaleVisible();
	}

	public boolean isTotalRedemptionRequest() {

		// check alert riscatto totale
		WebElement thresholdCrossedAlert = getAlertTotalRedemption();
		Boolean isThresholdCrossedAlert = thresholdCrossedAlert.isDisplayed();

		// check import in input equal import on slide
		String inputAmount = getContainer().findElement(By.className("input-box")).getText();
		String redemptionMaxValue = getRedemptionMaxValue();
		Boolean equalAmount = StringUtils.equals(inputAmount, redemptionMaxValue);

		// check label riscatto totale
		String redemptionType = getRedemptionType();
		Boolean isTotalRedemptionLabel = RedemptionType.TOTAL.getLongValue().equals(redemptionType);

		return isThresholdCrossedAlert && equalAmount && isTotalRedemptionLabel;
	}

	public String getRedemptionType() {

		WebElement footerRedemptionBox = getRowInfoContainer();
		List<WebElement> footerRedemptionBoxElements = footerRedemptionBox.findElements(By.className("row-info"));
		List<WebElement> redemptionTypeList = footerRedemptionBoxElements.get(0).findElements(By.tagName("p"));
		redemptionTypeList.remove(0);
		for (WebElement el : redemptionTypeList)
			if (!WebElementUtil.hasClass(el, "ng-hide"))
				return el.getText();
		return null;
	}

	public String getFinalRedemptionType() {

		WebElement footerRedemptionBox = getFinalPageBox(RedemptionValueType.TIPO_RISCATTO.getValue());
		List<WebElement> redemptionTypeList = footerRedemptionBox.findElements(By.tagName("p"));
		redemptionTypeList.remove(0);
		for (WebElement el : redemptionTypeList)
			if (!WebElementUtil.hasClass(el, "ng-hide"))
				return el.getText();
		return null;
	}

	public String getFinalAmount() {

		WebElement amountBox = getFinalPageBox(RedemptionValueType.IMPORTO.getValue());
		List<WebElement> values = amountBox.findElements(By.tagName("p"));
		return values.get(1).getText();
	}

	public boolean checkFinalAmount(String redemptionValue) {

		String finalAmount = getFinalAmount().replace(" €", "");
		return redemptionValue.equals(finalAmount);
	}

	public boolean checkFinalRedemptionType(String redemptionType) {

		String finalRedemptionType = getFinalRedemptionType();
		return finalRedemptionType.equals(redemptionType);
	}

	public String getRedemptionPolicyType() {

		List<WebElement> inputRiscatti = getContainer().findElements(By.className("input-riscatto"));
		if (inputRiscatti.size() != 0)
			return RedemptionType.PARTIAL.getValue();
		return RedemptionType.TOTAL.getValue();
	}

	public boolean isRequestBeingTaken() {

		List<WebElement> titleConferma = getContainer().findElements(By.className("row-title-conferma"));
		List<WebElement> textConferma = getContainer().findElements(By.className("row-text-conferma"));
		List<WebElement> helpConferma = getContainer().findElements(By.className("row-help-conferma"));
		List<WebElement> checkOk = getContainer().findElements(By.className("row-check-ok"));

		if (checkOk.size() != 0 && titleConferma.size() != 0 && textConferma.size() != 0 && helpConferma.size() != 0)
			return true;
		return false;
	}

	public boolean checkRenewalPolicyBox() {

		List<WebElement> renewBoxContainer = getContainer().findElements(By.className("box-riscatti"));

		if (renewBoxContainer.size() != 0) {
			List<WebElement> alertBox = renewBoxContainer.get(0).findElements(By.className("alert_riscatti"));
			List<WebElement> title = renewBoxContainer.get(0).findElements(By.className("title"));
			List<WebElement> description = renewBoxContainer.get(0).findElements(By.className("description"));
			List<WebElement> icon = renewBoxContainer.get(0).findElements(By.className("icon"));

			if (alertBox.size() != 0 && title.size() != 0 && description.size() != 0 && icon.size() != 0)
				return true;
		}
		return false;
	}

}
