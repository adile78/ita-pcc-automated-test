package pageelementobjects;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enums.PolicyCategoryType;
import enums.PolicyFieldLabelType;
import utilities.WebElementUtil;

/**
 * @author barbetti_be
 *
 */
public abstract class GenericPolicyInfoBox implements PolicyInfoBox {

	protected WebDriver driver;

	protected static Logger logger = LogManager.getLogger();

	protected WebElement infoBox;
	protected By productCompanyBy;
	protected String productName;
	protected String companyName;
	protected PolicyCategoryType category;
	private By boxIconBy;
	private Map<String, String> policyFieldLabelValueList;

	public GenericPolicyInfoBox(WebDriver driver, WebElement infoBox) {

		this.driver = driver;
		this.infoBox = infoBox;
		boxIconBy = By.className("box-main__icon");
		// productCompanyBy = By.className("text-left");
		productCompanyBy = By.tagName("h2");
	}

	@Override
	public String getProductName() {

		if (productName == null) {
			setProductAndCompanyName();
		}
		return productName;
	}

	@Override
	public String getCompanyName() {

		if (companyName == null) {
			setProductAndCompanyName();
		}
		return companyName;
	}

	private void setProductAndCompanyName() {

		WebElement productCompanyName = infoBox.findElement(productCompanyBy);
		if (productCompanyName != null && Strings.isNotBlank(productCompanyName.getText())) {
			String[] productCompanyNameValue = productCompanyName.getText().split("-");
			productName = "";
			for (int i = 0; i < productCompanyNameValue.length - 1; i++) {
				productName = productName + productCompanyNameValue[i] + " ";
			}
			productName = productName.trim();
			companyName = productCompanyNameValue[productCompanyNameValue.length - 1].trim();
		}

	}

	@Override
	public PolicyCategoryType getCategoryType() {

		if (category == null) {

			WebElement boxIcon = getBoxIcon();
			
			for (PolicyCategoryType policyCategory: PolicyCategoryType.values()){
				if(WebElementUtil.hasClass(boxIcon, policyCategory.getIconType()))
					category = policyCategory;
			}
		}

		return category;
	}

	/**
	 * @return the box icon
	 */
	private WebElement getBoxIcon() {

		return infoBox.findElement(boxIconBy);
	}

	@Override
	public Map<String, String> getPolicyFieldLabelValueList() {

		if (policyFieldLabelValueList == null) {
			policyFieldLabelValueList = WebElementUtil.getLabelValueMap(infoBox);
		}
		return policyFieldLabelValueList;
	}
	
	public String getPolicyNumber(){
	
		Map<String, String> policyFieldList = getPolicyFieldLabelValueList();
		String policyNumber = policyFieldList.get(PolicyFieldLabelType.NUMERO_POLIZZA.getValue());
		return policyNumber;		
	}

	@Override
	public void clickPolicyDetail() {

		WebElementUtil.waitForPresenceOf(driver, By.tagName("h2"));
		WebElement productCompanyName = infoBox.findElement(By.tagName("h2"));
		WebElementUtil.clickElement(driver, productCompanyName);
		// productCompanyName.click();

	}

}
