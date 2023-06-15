package pccpageobjects;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import enums.ClaimFieldType;

/**
 * @author barbetti_be
 *
 */
public class PccKaskoClaimPage extends PccAutoClaimPage {

	public PccKaskoClaimPage(WebDriver driver) {

		super(driver);
	}

	private WebElement getContainer() {

		return driver.findElement(By.id("etracking"));
	}

	public boolean isClaimPage() {

		return getContainer() != null ? true : false;
	}

	public boolean isDamageTypeBoxVisibile() {

		return getDamageTypeBox().isDisplayed();
	}

	protected void selectCoverage(String claimType) {

		WebElement damageBox = getDamageTypeBox();
		List<WebElement> damageElementList = damageBox.findElements(By.tagName("input"));

		damageElementList.get(0).click();
		WebElement claimTypeBox = getClaimTypeBox();
		List<WebElement> claimElementList = claimTypeBox.findElements(By.tagName("label"));
		// select radio button based on label containing claim type
		for (WebElement claimEl : claimElementList) {
			if (StringUtils.containsIgnoreCase(claimEl.getText(), claimType)) {
				claimEl.click();
			}
		}

	}	

	protected void selectEventCategory(String eventCategory) {

		Select eventCat = new Select(driver.findElement(By.id(portletName + "_categoriaEvento")));
		eventCat.selectByVisibleText(eventCategory);
	}

	protected void setCarDriverInformation(Map<String, String> params) {

		Select genderSetter = new Select(driver.findElement(By.id(getPortletName() + "_input-cond-sesso")));
		genderSetter.selectByValue(params.get(ClaimFieldType.DRIVER_GENDER.getValue()));

		WebElement name = driver.findElement(By.id(portletName + "_input-cond-nome"));
		name.sendKeys(params.get(ClaimFieldType.DRIVER_NAME.getValue()));

		WebElement surname = driver.findElement(By.id(portletName + "_input-cond-cognome"));
		surname.sendKeys(params.get(ClaimFieldType.DRIVER_SURNAME.getValue()));

		WebElement birthdate = driver.findElement(By.id(portletName + "_input-cond-data-formatted"));
		birthdate.clear();
		birthdate.sendKeys(params.get(ClaimFieldType.DRIVER_BIRTHDAY.getValue()));// "11/12/1984"

		setDriverCity(By.id(getPortletName() + "_input-luogo-nascita_conducenteNascita"),
				params.get(ClaimFieldType.DRIVER_BIRTHDAY_CITY_FIRST_LETTERS.getValue()),
				params.get(ClaimFieldType.DRIVER_BIRTHDAY_CITY.getValue()));

		WebElement address = driver.findElement(By.id(portletName + "_input-cond-indirizzo"));
		address.sendKeys(params.get(ClaimFieldType.DRIVER_ADDRESS.getValue()));

		WebElement cap = driver.findElement(By.id(portletName + "_input-cond-cap"));
		cap.clear();
		cap.sendKeys(params.get(ClaimFieldType.DRIVER_CAP.getValue()));

		setDriverCity(By.id(getPortletName() + "_input-cond-comune"),
				params.get(ClaimFieldType.DRIVER_CITY_FIRST_LETTERS.getValue()),
				params.get(ClaimFieldType.DRIVER_CITY.getValue()));

		WebElement telephone = driver.findElement(By.id(portletName + "_input-cond-telefono"));
		telephone.sendKeys(params.get(ClaimFieldType.DRIVER_PHONE.getValue()));

		WebElement injury = null;
		if (Boolean.parseBoolean(params.get(ClaimFieldType.IS_INJURY.getValue()))) {
			injury = driver.findElement(By.id("apesin-conducenteLesioni1"));
			injury.click();
			WebElement injuryList = driver.findElement(By.id(portletName + "_elencoPartiLese"));
			injuryList.sendKeys(params.get(ClaimFieldType.INJURY_DESCRIPTION.getValue()));
		} else {
			injury = driver.findElement(By.id("apesin-conducenteLesioni2"));
			injury.click();
		}

		WebElement dinamicEvent = driver.findElement(By.id(portletName + "_dinamicEvent"));
		dinamicEvent.sendKeys(params.get(ClaimFieldType.EVENT_DESCRIPTION.getValue()));

		WebElement iban = driver.findElement(By.id(portletName + "_input-iban"));
		iban.sendKeys(params.get(ClaimFieldType.IBAN.getValue()));

		WebElement saveBtn = driver.findElement(By.id("apesin-submit-next"));
		saveBtn.click();

	}
}
