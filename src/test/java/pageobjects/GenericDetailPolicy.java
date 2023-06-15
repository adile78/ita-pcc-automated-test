package pageobjects;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author barbetti_be
 *
 */
public abstract class GenericDetailPolicy implements DetailPolicy {

	protected WebDriver driver;

	protected static Logger logger = LogManager.getLogger();
	protected DetailPolicy policy;

	protected String categoryPolicyClass;

	public GenericDetailPolicy(WebDriver driver, DetailPolicy policy) {

		this.driver = driver;
		this.policy = policy;
		setCategoryPolicyDetailClass(categoryPolicyClass);
		policy.setCategoryPolicyDetailClass(categoryPolicyClass);
	}

	public WebElement getBox(String boxTitle) {
		return policy.getBox(boxTitle);
	}
	
	public String getCompanyName() {

		return policy.getCompanyName();
	}

	public String getProductName() {

		return policy.getProductName();
	}

	public abstract boolean checkCategoryInfo(Map<String, String> parameters);

	public boolean checkGenericInfo(Map<String, String> parameters) {

		Map<String, String> fieldLabelList = getPolicyGenericDetails();
		List<String> requiredFieldList = getRequiredPolicyGenericDetails();
		for (String value : requiredFieldList) {
			if (!fieldLabelList.containsKey(value) || fieldLabelList.get(value) == null) {
				logger.error(policy.toString() + " field " + value + " not found");
				return false;
			}
		}
		return true;
	}

	public boolean checkCoverages() {

		if (getCoveragesDetails() == null) {
			logger.error("Coverages not found");
			return false;
		}

		return true;
	}

	public boolean checkMovements() {

		if (getMovementsGenericDetails() == null) {
			logger.error("Movements not found");
			return false;
		}
		return true;
	}

	public Map<String, String> getPolicyGenericDetails() {

		return policy.getPolicyGenericDetails();
	}

	public Map<String, String> getMovementsGenericDetails() {

		return policy.getMovementsGenericDetails();
	}

	public List<String> getMovementsHistoryDetails() {

		return policy.getMovementsHistoryDetails();
	}
	
	public List<String> getInsuredsDetails() {

		return policy.getInsuredsDetails();
	}

	public List<String> getCoveragesDetails() {

		return policy.getCoveragesDetails();
	}

	@Override
	public WebElement getPolicyDetailContainer() {
		
		return policy.getPolicyDetailContainer();
	}

	public abstract List<String> getRequiredPolicyGenericDetails();
	
	public List<String> getBeneficiariValueDetails() {

		return null;
	}
}
