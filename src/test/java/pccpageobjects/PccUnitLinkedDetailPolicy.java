package pccpageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enums.CompanyType;
import enums.PolicyCategoryType;
import enums.PolicyDetailType;
import enums.PolicyFieldLabelType;
import pageobjects.DetailPolicy;
import pageobjects.GenericDetailPolicy;
import utilities.WebElementUtil;

public class PccUnitLinkedDetailPolicy extends GenericDetailPolicy {

	public PccUnitLinkedDetailPolicy(WebDriver driver, DetailPolicy policy) {

		super(driver, policy);
	}

	@Override
	public List<String> getRequiredPolicyGenericDetails() {

		List<String> requiredFieldList = new ArrayList<String>();
		requiredFieldList.add(PolicyFieldLabelType.NUMERO_POLIZZA.getValue());
		requiredFieldList.add(PolicyFieldLabelType.IMPORTO_RATA.getValue());
		requiredFieldList.add(PolicyFieldLabelType.PERIDICITA_VERSAMENTO.getValue());
		requiredFieldList.add(PolicyFieldLabelType.TOTALE_VERSAMENTI.getValue());
		requiredFieldList.add(PolicyFieldLabelType.SALDO_POSIZIONE.getValue());
		requiredFieldList.add(PolicyFieldLabelType.DATA_VALORIZZAZIONE.getValue());
		if (CompanyType.AMPS_VITA.getCompanyName().equals(policy.getCompanyName())) {
			requiredFieldList.add(PolicyFieldLabelType.DATA_PRIMA_ISCRIZIONE.getValue());
		}
		return requiredFieldList;
	}

	@Override
	public boolean checkCategoryInfo(Map<String, String> parameters) {

		if (CompanyType.AMPS_VITA.getCompanyName().equals(policy.getCompanyName())) {
			return checkRecentMovements() && checkSaldoPosizione() && checkRipartizioneInvestimento()
					&& checkGapPrevidenziale();
		}
		return true;
	}

	public boolean checkRecentMovements() {
		if (getRecentMovementsGenericDetails() == null) {
			logger.error("Recent Movements not found");
			return false;
		}
		return true;
	}

	public Map<String, String> getRecentMovementsGenericDetails() {

		WebElement movementsBox = getBox(PolicyDetailType.MOVIMENTI_RECENTI.getValue());
		return WebElementUtil.getLabelValueMap(movementsBox);

	}

	public boolean checkSaldoPosizione() {

		WebElement policyBox = policy.getBox(PolicyDetailType.SALDO_POSIZIONE.getValue());
		WebElement chart = policyBox.findElement(By.id("chart3"));
		List<WebElement> chartElements = chart.findElements(By.tagName("g"));
		if (chartElements.size() > 0)
			return true;
		logger.error(PolicyDetailType.SALDO_POSIZIONE.getValue() + " not valid");
		return false;
	}

	public boolean checkRipartizioneInvestimento() {

		WebElement policyBox = policy.getBox(PolicyDetailType.RIPARTIZIONE_INVESTIMENTO.getValue());
		WebElement chart = policyBox.findElement(By.id("chart4"));
		List<WebElement> chartElements = chart.findElements(By.tagName("g"));
		if (chartElements.size() > 0)
			return true;
		logger.error(PolicyDetailType.RIPARTIZIONE_INVESTIMENTO.getValue() + " not valid");
		return false;
	}

	public boolean checkGapPrevidenziale() {

		WebElement policyBox = policy.getBox(PolicyDetailType.STIMA_GAP_PREVIDENZA.getValue());
		WebElement chart = policyBox.findElement(By.id("chart5"));
		List<WebElement> chartElements = chart.findElements(By.tagName("g"));
		if (chartElements.size() > 0) {
			WebElement chartInfo = policyBox.findElement(By.className("chart-info"));
			WebElement form = policyBox.findElement(By.className("axa-form "));
			if (chartInfo != null && form != null)
				return true;
		}
		logger.error(PolicyDetailType.STIMA_GAP_PREVIDENZA.getValue() + " not valid");
		return false;
	}

	@Override
	public List<String> getMovementsHistoryDetails() {

		WebElement movementsBox = getBox(PolicyDetailType.MOVIMENTI_RECENTI.getValue());
		List<String> movementsHistoryList = WebElementUtil.getAccordionElementList(movementsBox);
		return movementsHistoryList;
	}

	@Override
	public void setCategoryPolicyDetailClass(String categoryPolicyClass) {

		this.categoryPolicyClass = PolicyCategoryType.UNIT_LINKED.getPolicyDetailClass();
	}

}
