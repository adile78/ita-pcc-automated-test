package pccpageobjects;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import enums.ClaimFieldType;
import enums.ClaimType;
import pageobjects.ClaimPage;
import utilities.DocumentUtil;
import utilities.PropsUtil;
import utilities.WebElementUtil;

public abstract class PccAutoClaimPage implements ClaimPage {

	protected String portletName;

	protected static Logger logger = LogManager.getLogger();

	protected WebDriver driver;

	public PccAutoClaimPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param claimType
	 *            type of claim user wants to open
	 */
	public void startClaimOpening() {

		selectClaimType(ClaimType.AUTO.getClaimType());
		clickOpenClaimBtn();
	}

	protected void selectClaimType(String claimType) {

		Select selectClaim = new Select(driver.findElement(By.id(getPortletName() + "_categoria")));
		selectClaim.selectByVisibleText(claimType);
	}

	protected void clickOpenClaimBtn() {

		WebElement openClaimBtn = driver.findElement(By.id("submit-1a"));
		openClaimBtn.click();
	}

	/**
	 * @return portlet name
	 */
	protected String getPortletName() {

		if (StringUtils.isBlank(portletName)) {
			WebElement portletSection = driver.findElement(By.tagName("section"));
			portletName = portletSection.getAttribute("id").replace("portlet", "");
		}
		return portletName;
	}

	protected WebElement getDamageTypeBox() {

		WebElementUtil.waitForPresenceOf(driver, By.id("tida-item1"));
		WebElement tidaBox = driver.findElement(By.id("tida-item1"));
		return tidaBox;
	}

	protected WebElement getClaimTypeBox() {

		WebElementUtil.waitForPresenceOf(driver, By.id("tida-item2"));
		WebElement tidaBox = driver.findElement(By.id("tida-item2"));
		return tidaBox;
	}

	protected void setDate(String dateValue) {

		WebElementUtil.waitForPresenceOf(driver, By.id(getPortletName() + "_calendar"));
		WebElement date = driver.findElement(By.id(getPortletName() + "_calendar"));
		date.clear();
		date.sendKeys(dateValue);
	}

	protected void setTime(String timeValue) {

		WebElementUtil.waitForPresenceOf(driver, By.id(getPortletName() + "_dateStart_2d__2d_Time"));
		WebElement time = driver.findElement(By.id(getPortletName() + "_dateStart_2d__2d_Time"));
		time.clear();
		time.sendKeys(timeValue);
	}

	protected List<WebElement> getCityList(String identifier) {

		WebElement cityListContainer = driver.findElement(By.className(identifier));
		List<WebElement> cityList = cityListContainer.findElements(By.tagName("li"));
		return cityList;
	}

	protected void setCity(String cityInitialValue, String cityValue) {

		WebElement city = driver.findElement(By.id(getPortletName() + "_city"));
		city.sendKeys(cityInitialValue);
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			logger.error("Timeout exception during city availability input wait for values", e);
			e.printStackTrace();
		}
		List<WebElement> cityList = getCityList("luogo-evento");
		for (WebElement c : cityList) {
			if ((cityValue).equals(c.getText()))
				c.click();
		}
	}

	protected void setAddress(String addressValue) {

		WebElementUtil.waitForPresenceOf(driver, By.id(getPortletName() + "_address"));
		WebElement address = driver.findElement(By.id(getPortletName() + "_address"));
		address.sendKeys(addressValue);
	}

	protected void setCityAvailability(String cityAvInitialValue, String cityAvValue) {

		WebElement city = driver.findElement(By.id(getPortletName() + "_city_availability"));
		city.sendKeys(cityAvInitialValue);
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			logger.error("Timeout exception during city availability input wait for values", e);
			e.printStackTrace();
		}
		List<WebElement> cityList = getCityList("luogo-evento");
		for (WebElement c : cityList) {
			if ((cityAvValue).equals(c.getText()))
				c.click();
		}
	}

	protected void setAddressAvailability(String addressAvValue) {

		WebElementUtil.waitForPresenceOf(driver, By.id(getPortletName() + "_address_availability"));
		WebElement addressAv = driver.findElement(By.id(getPortletName() + "_address_availability"));
		addressAv.sendKeys(addressAvValue);
	}

	protected void clickFirstDetailSaveBtn() {

		WebElement firstDetailSaveBtn = driver.findElement(By.id("submit-data-accadimento"));
		firstDetailSaveBtn.click();
	}

	/**
	 * @param params
	 *            a list of ClaimFieldType values for claim opening first
	 *            details
	 */
	public void setClaimFirstDetails(Map<String, String> params) {

		setDate(params.get(ClaimFieldType.DATE.getValue()));
		setTime(params.get(ClaimFieldType.TIME.getValue()));
		setCity(params.get(ClaimFieldType.CITY_FIRST_LETTERS.getValue()), params.get(ClaimFieldType.CITY.getValue()));
		setAddress(params.get(ClaimFieldType.ADDRESS.getValue()));
		setCityAvailability(params.get(ClaimFieldType.CITY_AV_FIRST_LETTERS.getValue()),
				params.get(ClaimFieldType.CITY_AVAILABILITY.getValue()));
		setAddressAvailability(params.get(ClaimFieldType.ADDRESS_AVAILABILITY.getValue()));

		clickFirstDetailSaveBtn();
	}

	protected WebElement getPlateBox() {

		WebElement plateBox = driver.findElement(By.className("row-targa"));
		return plateBox;
	}

	public boolean isPlateBoxVisibile() {

		return getPlateBox() == null ? false : true;
	}

	protected void selectLicensePlate(String policyNumber) {

		List<WebElement> licensePlateList = getPlateBox().findElements(By.tagName("input"));
		List<WebElement> policyNumberList = getPlateBox().findElements(By.className("numero-polizza"));
		int i = 0;
		while (i < policyNumberList.size() && !policyNumber.equals(policyNumberList.get(i).getText())) {
			i++;
		}
		licensePlateList.get(i).click();
	}

	protected void clickLicensePlateChoice() {

		WebElement policyChoiceBtn = driver.findElement(By.id("submit-scelta-polizza"));
		policyChoiceBtn.click();
	}

	protected void setLicensePlate(String policyNumber) {

		selectLicensePlate(policyNumber);
		clickLicensePlateChoice();
	}

	protected abstract void selectCoverage(String claimType);

	protected void clickSaveAndProceedBtn() {

		try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			logger.error("Timeout exception during city input wait for values", e);
			e.printStackTrace();
		}
		WebElement saveAndProceedBtn = driver.findElement(By.id("apesin-submit-next"));
		saveAndProceedBtn.click();
	}

	public void setPolicyAndCoverageType(Map<String, String> params, String claimType) {

		setLicensePlate(params.get(ClaimFieldType.POLICY_NUMBER.getValue()));
		selectCoverage(claimType);
		clickSaveAndProceedBtn();
	}

	//TODO: spostare implementazione nelle sottoclassi
	public void secondStepClaimOpening(Map<String, String> params) {

		setDocuments(params.get(ClaimFieldType.DOCUMENT_CATEGORY_LIST.getValue()),
				params.get(ClaimFieldType.DOCUMENT_PATH.getValue()));
		selectEventCategory(params.get(ClaimFieldType.EVENT_CATEGORY.getValue()));
		selectDamagedGoods(params.get(ClaimFieldType.DAMAGE_GOOD.getValue()));
		setCarDriver(params);
	}

	protected void setDocuments(String categoryList, String documentPathList) {

		Select docType = new Select(driver.findElement(By.id(getPortletName() + "_categoriaDocumento")));
		Map<String, String> catDoctMap = DocumentUtil.getDocumentCategoryMap(categoryList, documentPathList);
		for (String cat : catDoctMap.keySet()) {
			docType.selectByVisibleText(cat);
			WebElement upload = driver.findElement(By.id(getPortletName() + "_file"));
			upload.sendKeys(System.getProperty("user.dir") + catDoctMap.get(cat));
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				logger.error("Timeout exception during set documents", e);
				e.printStackTrace();
			}
		}
	}

	protected abstract void selectEventCategory(String eventCategory);

	protected void selectDamagedGoods(String damageGood) {
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			logger.error("Timeout exception during city availability input wait for values", e);
			e.printStackTrace();
		}
		Select eventCat = new Select(driver.findElement(By.id("beniDanneggiati0")));
		WebElementUtil.waitForPresenceOf(driver, By.tagName("option"));
		eventCat.selectByVisibleText(damageGood);
	}

	protected void setCarDriver(Map<String, String> params) {

		String carDriverBy = null;
		String driverOwnerBy = null;
		if (Boolean.parseBoolean(params.get(ClaimFieldType.IS_DRIVER.getValue()))) {
			carDriverBy = "apesin-nomeConducente1";
			WebElement carDriver = driver.findElement(By.id(carDriverBy));
			carDriver.click();
		} else {
			carDriverBy = "apesin-nomeConducente2";
			WebElement carDriver = driver.findElement(By.id(carDriverBy));
			carDriver.click();
			if (Boolean.parseBoolean(params.get(ClaimFieldType.IS_OWNER.getValue()))) {
				driverOwnerBy = "apesin-proprietarioVeicolo1";
			} else {
				driverOwnerBy = "apesin-proprietarioVeicolo2";
				WebElement driverOwner = driver.findElement(By.id(driverOwnerBy));
				driverOwner.click();
				setCarDriverInformation(params);
			}
		}
	}

	protected void setDriverCity(By webElementBy, String cityInitialValue, String cityValue) {

		WebElement city = driver.findElement(webElementBy);
		city.sendKeys(cityInitialValue);
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			logger.error("Timeout exception during city availability input wait for values", e);
			e.printStackTrace();
		}
		List<WebElement> cityList = getCityList("apesin-bgTabBody");
		for (WebElement c : cityList) {
			if ((cityValue).equals(c.getText()))
				c.click();
		}
	}

	protected abstract void setCarDriverInformation(Map<String, String> params);

	protected void setPhoneNumber(String phoneNumber) {
		WebElementUtil.waitForPresenceOf(driver, By.id(portletName + "_input-telefono"));
		WebElement telephone = driver.findElement(By.id(portletName + "_input-telefono"));
		telephone.sendKeys(phoneNumber);
	}

	public void setLastDetailsClaimOpening(Map<String, String> params) {

		setPhoneNumber(params.get(ClaimFieldType.PHONE_NUMBER.getValue()));
	}

	public void saveFinalClaim() {

		WebElement saveBtn = driver.findElement(By.id("apesin-submit-next"));
		saveBtn.click();

	}

	public boolean isClaimOpeningSucceded() {

		WebElement sinistroN = driver.findElement(By.tagName("h3"));
		return StringUtils.containsIgnoreCase(PropsUtil.getPropertyValue("sinistro.n"), sinistroN.getText());
	}
}
