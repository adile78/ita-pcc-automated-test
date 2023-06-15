package pccpageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.util.Strings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enums.PolicyCategoryType;
import enums.PolicyDetailType;
import enums.PolicyFieldLabelType;
import pageobjects.DetailPolicy;
import pageobjects.GenericDetailPolicy;
import utilities.WebElementUtil;

public class PccAutoDetailPolicy extends GenericDetailPolicy {

	public PccAutoDetailPolicy(WebDriver driver, DetailPolicy policy) {

		super(driver, policy);
	}

	@Override
	public List<String> getRequiredPolicyGenericDetails() {

		List<String> requiredFieldList = new ArrayList<String>();
		requiredFieldList.add(PolicyFieldLabelType.NUMERO_POLIZZA.getValue());
		requiredFieldList.add(PolicyFieldLabelType.CONTRATTA_IL.getValue());
		requiredFieldList.add(PolicyFieldLabelType.DATA_SCADENZA.getValue());
		requiredFieldList.add(PolicyFieldLabelType.ASSICURATO.getValue());
		requiredFieldList.add(PolicyFieldLabelType.CONTRAENTE.getValue());

		return requiredFieldList;
	}

	public boolean checkDatiVeicolo() {

		for (String requiredValue : getRequiredDatiVeicoloValues()) {
			if (Strings.isBlank(getVeicoloDetails().get(requiredValue))) {
				logger.error(policy.toString() + " " + requiredValue + " not found");
				return false;
			}
		}
		return true;
	}

	public Map<String, String> getVeicoloDetails() {

		WebElement policyBox = policy.getBox(PolicyDetailType.DATI_VEICOLO.getValue());
		Map<String, String> veicoloDetails = WebElementUtil.getLabelValueMap(policyBox);
		return veicoloDetails;
	}

	private List<String> getRequiredDatiVeicoloValues() {

		List<String> requiredFieldList = new ArrayList<String>();
		// requiredFieldList.add(PolicyFieldLabelType.VEICOLO.getValue());
		requiredFieldList.add(PolicyFieldLabelType.TARGA.getValue());
		requiredFieldList.add(PolicyFieldLabelType.IMMATRICOLATO_IL.getValue());

		return requiredFieldList;
	}

	@Override
	public boolean checkCategoryInfo(Map<String, String> parameters) {

		if (checkDatiVeicolo())
			if (checkCoverages())
				if (checkMovements())
					return true;
		return false;
	}

	@Override
	public void setCategoryPolicyDetailClass(String categoryPolicyClass) {
		
		this.categoryPolicyClass = PolicyCategoryType.AUTO.getPolicyDetailClass();
	}

}
