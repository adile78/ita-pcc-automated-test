package pccpageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enums.CompanyType;
import enums.PolicyCategoryType;
import enums.PolicyDetailType;
import enums.PolicyFieldLabelType;
import pageobjects.DetailPolicy;
import pageobjects.GenericDetailPolicy;
import utilities.WebElementUtil;

public class PccWorkersProtectionDetailPolicy extends GenericDetailPolicy {

	public PccWorkersProtectionDetailPolicy(WebDriver driver, DetailPolicy policy) {

		super(driver, policy);
	}

	@Override
	public void setCategoryPolicyDetailClass(String categoryPolicyClass) {

		this.categoryPolicyClass = PolicyCategoryType.WORKERS_PROTECTION.getPolicyDetailClass();
	}

	@Override
	public boolean checkCategoryInfo(Map<String, String> parameters) {
		 
		if (checkMovements())
			if (!getDetailsBox().isEmpty())
				if (CompanyType.AMPS_DANNI.getCompanyName().equals(policy.getCompanyName())) {
					 return checkCoverages();
				 } else {
					 return true;
				 }
		return false;
	}

	@Override
	public List<String> getRequiredPolicyGenericDetails() {

		List<String> requiredFieldList = new ArrayList<String>();
		requiredFieldList.add(PolicyFieldLabelType.NUMERO_POLIZZA.getValue());
		if (CompanyType.AAI.getCompanyName().equals(policy.getCompanyName())) {
			requiredFieldList.add(PolicyFieldLabelType.PREMIO_ANNUO.getValue());
			requiredFieldList.add(PolicyFieldLabelType.TIPO_PREMIO.getValue());
		}
		return requiredFieldList;
	}

	public Map<String, String> getDetailsBox() {

		WebElement detailBox = getBox(PolicyDetailType.DETTAGLI.getValue());
		Map<String, String> detailsBoxValues = WebElementUtil.getLabelValueMap(detailBox);
		return detailsBoxValues;
	}

}
