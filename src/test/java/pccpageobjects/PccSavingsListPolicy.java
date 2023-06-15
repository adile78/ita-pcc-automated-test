package pccpageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import enums.CallToActionType;
import enums.CompanyType;
import enums.PolicyFieldLabelType;
import pageobjects.GenericListPolicy;
import pageobjects.ListPolicy;

public class PccSavingsListPolicy  extends GenericListPolicy {

	public PccSavingsListPolicy(WebDriver driver, ListPolicy policy) {
		super(driver, policy);

	}

	@Override
	public List<String> getRequiredFieldList() {
		
		List<String> requiredFieldList = new ArrayList<String>();
		//FIXME: chiedere al team di BE + PO di uniformare la label del campo
		requiredFieldList.add(PolicyFieldLabelType.VALIDITA.getValue() + ":");
		if (CompanyType.AAI.getCompanyName().equals(policy.getInfoBox().getCompanyName())){
			requiredFieldList.add(PolicyFieldLabelType.PREMI_VERSATI.getValue());
			requiredFieldList.add(PolicyFieldLabelType.VALORE_LORDO_POLIZZA.getValue());
			requiredFieldList.add(PolicyFieldLabelType.DATA_AGGIORNAMENTO.getValue());
		}
		
		return requiredFieldList;
	}

	@Override
	public List<String> getRequiredActionList() {
		
		List<String> requiredActionList = new ArrayList<String>();		
		if (CompanyType.AAI.getCompanyName().equals(policy.getInfoBox().getCompanyName())){
			requiredActionList.add(CallToActionType.CONTATTA_AGENZIA.getValue());
		}
		
		return requiredActionList;
	}

}
