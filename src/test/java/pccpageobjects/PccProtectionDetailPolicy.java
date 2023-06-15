package pccpageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import enums.CompanyType;
import enums.PolicyCategoryType;
import enums.PolicyFieldLabelType;
import pageobjects.DetailPolicy;
import pageobjects.LifeAndSavingsDetailPolicy;

/**
 * @author barbetti_be
 *
 */
public class PccProtectionDetailPolicy extends LifeAndSavingsDetailPolicy {

	public PccProtectionDetailPolicy(WebDriver driver, DetailPolicy policy) {

		super(driver, policy);
	}

	@Override
	public List<String> getRequiredPolicyGenericDetails() {

		List<String> requiredFieldList = new ArrayList<String>();
		requiredFieldList.add(PolicyFieldLabelType.NUMERO_POLIZZA.getValue());
		requiredFieldList.add(PolicyFieldLabelType.CONTRAENTE.getValue());
		requiredFieldList.add(PolicyFieldLabelType.ASSICURATO.getValue());
		if (CompanyType.AAI.getCompanyName().equals(policy.getCompanyName())) {
			requiredFieldList.add(PolicyFieldLabelType.PREMIO_VERSATO.getValue());
			requiredFieldList.add(PolicyFieldLabelType.TIPO_PREMIO.getValue());
		}
		return requiredFieldList;
	}

	@Override
	public boolean checkCategoryInfo(Map<String, String> parameters) {

		return true;
	}

	@Override
	public void setCategoryPolicyDetailClass(String categoryPolicyClass) {

		this.categoryPolicyClass = PolicyCategoryType.PROTECTION.getPolicyDetailClass();
	}

}
