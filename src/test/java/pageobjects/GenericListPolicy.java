package pageobjects;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enums.PolicyCategoryType;
import pageelementobjects.PolicyActionBox;
import pageelementobjects.PolicyInfoBox;
import utilities.WebElementUtil;

/**
 * @author barbetti_be
 *
 */
public abstract class GenericListPolicy implements ListPolicy {

	protected WebDriver driver;

	protected static Logger logger = LogManager.getLogger();
	protected ListPolicy policy;

	public GenericListPolicy(WebDriver driver, ListPolicy policy) {

		this.driver = driver;
		this.policy = policy;
	}

	public PolicyCategoryType getCategoryType() {

		return policy.getCategoryType();
	}

	public PolicyActionBox getActionBox() {
		return policy.getActionBox();
	}

	public PolicyInfoBox getInfoBox() {
		return policy.getInfoBox();
	}

	public boolean checkListLabelsAndValues() {

		Map<String, String> fieldLabelList = policy.getInfoBox().getPolicyFieldLabelValueList();
		List<String> requiredFieldList = policy.getRequiredFieldList();
		requiredFieldList.addAll(getRequiredFieldList());
		for (String value : requiredFieldList) {
			if (!fieldLabelList.containsKey(value) || fieldLabelList.get(value) == null) {
				logger.error("For product " + policy.getInfoBox().getProductName() + " Field " + value + " not found");
				return false;
			}
		}
		return true;
	}

	public boolean checkDashboardListCtas() {

		List<WebElement> ctaList = getActionBox().getCtaList();
		List<String> ctaTextList = WebElementUtil.getTextList(ctaList);
		List<String> requiredActionList = policy.getRequiredActionList();
		requiredActionList.addAll(getRequiredActionList());
		for (String cta : requiredActionList) {
			if (!ctaTextList.contains(cta)) {
				logger.error("For product " + policy.getInfoBox().getProductName() + " CTA " + cta + " not found");
				return false;
			}
		}
		return true;
	}

	public DetailPolicy clickPolicyDetail() {
		return policy.clickPolicyDetail();
	}

	@Override
	public DetailPolicy clickCtaByType(String ctaType) {

		return policy.clickCtaByType(ctaType);		
	}

}
