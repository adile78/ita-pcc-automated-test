package pccpageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import enums.CallToActionType;
import enums.CompanyType;
import enums.PolicyFieldLabelType;
import pageobjects.GenericListPolicy;
import pageobjects.ListPolicy;

public class PccHomeListPolicy extends GenericListPolicy {

	public PccHomeListPolicy(WebDriver driver, ListPolicy policy) {
		super(driver, policy);

	}

	@Override
	public List<String> getRequiredFieldList() {

		List<String> requiredFieldList = new ArrayList<String>();
		requiredFieldList.add(PolicyFieldLabelType.VALIDITA.getValue());
		requiredFieldList.add(PolicyFieldLabelType.PREMIO_TOTALE_PAGATO.getValue());
		requiredFieldList.add(PolicyFieldLabelType.FRAZIONAMENTO.getValue());
		//indirizzo e assicurato non ci sono sempre
		
		return requiredFieldList;
	}

	@Override
	public List<String> getRequiredActionList() {

		List<String> requiredActionList = new ArrayList<String>();
		requiredActionList.add(CallToActionType.APRI_SINISTRO.getValue());
		requiredActionList.add(CallToActionType.VISUALIZZA_SINISTRI.getValue());
		if (CompanyType.AAI.getCompanyName().equals(policy.getInfoBox().getCompanyName())){
			requiredActionList.add(CallToActionType.CONTATTA_AGENZIA.getValue());
		}
		
		return requiredActionList;
	}
	
}
