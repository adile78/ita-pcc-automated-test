package pccpageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import enums.CallToActionType;
import enums.CompanyType;
import enums.PolicyFieldLabelType;
import pageobjects.GenericListPolicy;
import pageobjects.ListPolicy;

public class PccProtectionListPolicy extends GenericListPolicy {

	public PccProtectionListPolicy(WebDriver driver, ListPolicy policy) {
		super(driver, policy);

	}

	@Override
	public List<String> getRequiredFieldList() {

		List<String> requiredFieldList = new ArrayList<String>();
		requiredFieldList.add(PolicyFieldLabelType.VALIDITA.getValue());
		requiredFieldList.add(PolicyFieldLabelType.PREMIO.getValue());
		requiredFieldList.add(PolicyFieldLabelType.FRAZIONAMENTO.getValue());
		requiredFieldList.add(PolicyFieldLabelType.ASSICURATI.getValue());

		return requiredFieldList;
	}

	@Override
	public List<String> getRequiredActionList() {

		List<String> requiredActionList = new ArrayList<String>();
		if (CompanyType.AAI.getCompanyName().equals(policy.getInfoBox().getCompanyName())) {

			requiredActionList.add(CallToActionType.CONTATTA_AGENZIA.getValue());
		}

		return requiredActionList;
	}

}
