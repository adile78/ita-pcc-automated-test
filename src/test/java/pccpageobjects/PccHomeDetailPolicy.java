package pccpageobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import enums.PolicyCategoryType;
import enums.PolicyFieldLabelType;
import pageobjects.DetailPolicy;
import pageobjects.GenericDetailPolicy;

public class PccHomeDetailPolicy extends GenericDetailPolicy {

	public PccHomeDetailPolicy(WebDriver driver, DetailPolicy policy) {

		super(driver, policy);
	}

	@Override
	public List<String> getRequiredPolicyGenericDetails() {

		List<String> requiredFieldList = new ArrayList<String>();
		requiredFieldList.add(PolicyFieldLabelType.NUMERO_POLIZZA.getValue());
		requiredFieldList.add(PolicyFieldLabelType.PREMIO_ANNUO.getValue());
		requiredFieldList.add(PolicyFieldLabelType.TIPO_PREMIO.getValue());

		return requiredFieldList;
	}

	@Override
	public boolean checkCategoryInfo(Map<String, String> parameters) {

		if (checkCoverages())
			if (checkMovements())
				return true;
		return false;
	}

	@Override
	public void setCategoryPolicyDetailClass(String categoryPolicyClass) {

		this.categoryPolicyClass = PolicyCategoryType.HOME.getPolicyDetailClass();
	}
}
