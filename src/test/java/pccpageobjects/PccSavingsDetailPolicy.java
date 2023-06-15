package pccpageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.util.Strings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enums.CompanyType;
import enums.PolicyCategoryType;
import enums.PolicyDetailType;
import enums.PolicyFieldLabelType;
import pageobjects.DetailPolicy;
import pageobjects.LifeAndSavingsDetailPolicy;
import utilities.WebElementUtil;

/**
 * @author barbetti_be
 *
 */
public class PccSavingsDetailPolicy extends LifeAndSavingsDetailPolicy {

	public PccSavingsDetailPolicy(WebDriver driver, DetailPolicy policy) {

		super(driver, policy);
	}

	@Override
	public List<String> getRequiredPolicyGenericDetails() {

		List<String> requiredFieldList = new ArrayList<String>();
		requiredFieldList.add(PolicyFieldLabelType.NUMERO_POLIZZA.getValue());
		requiredFieldList.add(PolicyFieldLabelType.CONTRAENTE.getValue());
		requiredFieldList.add(PolicyFieldLabelType.ASSICURATO.getValue());
		requiredFieldList.add(PolicyFieldLabelType.DATA_DECORRENZA.getValue());
		if (CompanyType.AAI.getCompanyName().equals(policy.getCompanyName())) {
			requiredFieldList.add(PolicyFieldLabelType.PREMI_VERSATI.getValue());
			requiredFieldList.add(PolicyFieldLabelType.VALORE_LORDO_POLIZZA.getValue());
			requiredFieldList.add(PolicyFieldLabelType.DATA_VALORIZZAZIONE.getValue());
		}

		return requiredFieldList;
	}

	@Override
	public boolean checkCategoryInfo(Map<String, String> parameters) {

		boolean result = checkPerformanceInvestimento();
		return result;
	}

	public List<String> getRequiredVersamentiDetails() {

		List<String> requiredFieldList = new ArrayList<String>();
		requiredFieldList.add(PolicyFieldLabelType.DATA_ULTIMO_VERSAMENTO.getValue());
		requiredFieldList.add(PolicyFieldLabelType.ULTIMO_VERSAMENTO.getValue());
		requiredFieldList.add(PolicyFieldLabelType.STATO_VERSAMENTO.getValue());

		return requiredFieldList;
	}

	public Map<String, String> getVersamentiDetails() {

		WebElement policyBox = policy.getBox(PolicyDetailType.ULTIMI_VERSAMENTI.getValue());
		Map<String, String> versamentiDetails = WebElementUtil.getLabelValueMap(policyBox);
		return versamentiDetails;
	}

	// solo AMPS
	public boolean checkVersamenti() {

		for (String requiredValue : getRequiredVersamentiDetails()) {
			if (Strings.isBlank(getVersamentiDetails().get(requiredValue))) {
				logger.error(policy.toString() + " " + requiredValue + " not found");
				return false;
			}
		}
		return true;
	}

	
	public boolean checkPerformanceInvestimento() {

		WebElement policyBox = policy.getBox(PolicyDetailType.PERFORMANCE_INVESTIMENTO.getValue());
		WebElement chart = policyBox.findElement(By.id("chart1"));
		List<WebElement> chartElements = chart.findElements(By.tagName("g"));
		if (chartElements.size() > 0)
			return true;
		return false;
	}

	public boolean checkRipartizioneInvestimento() {

		WebElement policyBox = policy.getBox(PolicyDetailType.RIPARTIZIONE_INVESTIMENTO.getValue());
		WebElement chart = policyBox.findElement(By.id("chart4"));
		List<WebElement> chartElements = chart.findElements(By.tagName("g"));
		if (chartElements.size() > 0)
			return true;
		return false;
	}


	@Override
	public void setCategoryPolicyDetailClass(String categoryPolicyClass) {

		this.categoryPolicyClass = PolicyCategoryType.SAVINGS.getPolicyDetailClass();
	}


}
