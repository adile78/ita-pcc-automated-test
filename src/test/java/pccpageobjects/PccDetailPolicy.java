package pccpageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enums.PolicyDetailType;
import pageobjects.DetailPolicy;
import utilities.PropsUtil;
import utilities.WebElementUtil;

/**
 * @author barbetti_be
 *
 */
public class PccDetailPolicy implements DetailPolicy {

	private WebDriver driver;
	protected static Logger logger = LogManager.getLogger();

	private By policyDetailContainerBy;
	private String detailsItemBy;

	private Map<String, String> policyGenericDetails;
	private Map<String, String> movementsGenericDetails;
	private List<String> coveragesDetails;
	private List<String> insuredsDetails;
	private String categoryPolicyClass;

	private String companyName;
	private String productName;

	public PccDetailPolicy(WebDriver driver, String companyName, String productName) {

		this.driver = driver;
		WebElementUtil.waitForPresenceOf(driver,
				By.className(PropsUtil.getPropertyValue("policy.detail.main.section")));
		this.policyDetailContainerBy = By.className(PropsUtil.getPropertyValue("policy.detail.main.section"));
		detailsItemBy = "__details-item";

		this.companyName = companyName;
		this.productName = productName;

	}

	public String getCompanyName() {
		return companyName;
	}

	public String getProductName() {
		return productName;
	}

	public WebElement getPolicyDetailContainer() {
		return driver.findElement(policyDetailContainerBy);
	}

	private List<WebElement> filterVisibilePolicyDetails(List<WebElement> policyDetailsBox) {

		List<WebElement> visibilePolicyDetailsBox = new ArrayList<WebElement>();
		for (WebElement detailBox : policyDetailsBox) {
			if (!WebElementUtil.hasClass(detailBox, PropsUtil.getPropertyValue("hide.class"))) {
				visibilePolicyDetailsBox.add(detailBox);
			}
		}
		return visibilePolicyDetailsBox;
	}

	public List<WebElement> getDetailsBoxList() {

		List<WebElement> policyDetailsBoxList = getPolicyDetailContainer()
				.findElements(By.className(categoryPolicyClass + detailsItemBy));
		List<WebElement> visibilePolicyDetailsBoxList = filterVisibilePolicyDetails(policyDetailsBoxList);
		return visibilePolicyDetailsBoxList;
	}

	public Map<String, String> getPolicyGenericDetails() {

		if (policyGenericDetails == null) {
			WebElement policy = getBox(PolicyDetailType.POLIZZA_IN_BREVE.getValue());
			policyGenericDetails = WebElementUtil.getLabelValueMap(policy);
		}
		return policyGenericDetails;
	}

	public Map<String, String> getMovementsGenericDetails() {

		if (movementsGenericDetails == null) {
			WebElement movementsBox = getBox(PolicyDetailType.MOVIMENTI.getValue());
			movementsGenericDetails = WebElementUtil.getLabelValueMap(movementsBox);
		}
		return movementsGenericDetails;
	}

	public List<String> getMovementsHistoryDetails() {

		WebElement movementsBox = getBox(PolicyDetailType.MOVIMENTI.getValue());
		List<String> movementsHistoryList = WebElementUtil.getAccordionElementList(movementsBox);
		return movementsHistoryList;
	}

	public List<String> getRecentMovementsHistoryDetails() {

		WebElement recentMovementsBox = getBox(PolicyDetailType.MOVIMENTI_RECENTI.getValue());
		List<String> recentMovementsHistoryList = WebElementUtil.getAccordionElementList(recentMovementsBox);
		return recentMovementsHistoryList;
	}

	public List<String> getInsuredsDetails() {

		WebElement insuredsBox = getBox(PolicyDetailType.DETTAGLI.getValue());
		insuredsDetails = WebElementUtil.getValueList(insuredsBox);

		/* first element is always the contraente */
		insuredsDetails.remove(0);

		return insuredsDetails;
	}

	public List<String> getCoveragesDetails() {

		WebElement coveragesBox = getBox(PolicyDetailType.GARANZIE.getValue());
		// check coverages in case of multiple insureds
		coveragesDetails = WebElementUtil.getAccordionElementList(coveragesBox);
		if (coveragesDetails.size() == 0) {
			// check coverages for
			coveragesDetails = findCoveragesSingleInsured(coveragesBox);
		}
		return coveragesDetails;
	}

	public WebElement getBox(String boxTitle) {

		try {
			for (WebElement detailsBox : getDetailsBoxList()) {
				WebElement title = detailsBox.findElement(By.tagName("h2"));
				if (boxTitle.equals(title.getText()))
					return detailsBox;
			}
		} catch (NoSuchElementException e) {
			logger.error(boxTitle + " not found for " + toString());
			throw new NoSuchElementException(e.getMessage());
		}
		return null;
	}

	private List<String> findCoveragesSingleInsured(WebElement coveragesBox) {

		List<String> coveragesSingleInsured = new ArrayList<String>();
		// coverages with restrictions
		List<WebElement> covList = coveragesBox
				.findElements(By.className(PropsUtil.getPropertyValue("accordion.toggle")));
		// coverages simple
		covList.addAll(coveragesBox.findElements(By.className(PropsUtil.getPropertyValue("accordion.toggle.empty"))));
		for (WebElement cov : covList) {
			coveragesSingleInsured.add(cov.getText());
		}
		return coveragesSingleInsured;
	}

	@Override
	public boolean checkCategoryInfo(Map<String, String> parameters) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void setCategoryPolicyDetailClass(String categoryPolicyClass) {

		this.categoryPolicyClass = categoryPolicyClass;
	}

	@Override
	public boolean checkGenericInfo(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {

		return "Product " + productName + " - " + companyName;
	}

	@Override
	public List<String> getBeneficiariValueDetails() {
		// TODO Auto-generated method stub
		return null;
	}


}
