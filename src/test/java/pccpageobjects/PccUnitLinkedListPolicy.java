package pccpageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import enums.CallToActionType;
import enums.CompanyType;
import enums.PolicyFieldLabelType;
import pageobjects.GenericListPolicy;
import pageobjects.ListPolicy;

public class PccUnitLinkedListPolicy extends GenericListPolicy {

	public PccUnitLinkedListPolicy(WebDriver driver, ListPolicy policy) {
		super(driver, policy);

	}

	@Override
	public List<String> getRequiredFieldList() {

		List<String> requiredFieldList = new ArrayList<String>();
		requiredFieldList.add(PolicyFieldLabelType.DATA_ISCRIZIONE_FONDO.getValue());
		requiredFieldList.add(PolicyFieldLabelType.TOTALE_CONTRIBUTI_VERSATI.getValue());
		requiredFieldList.add(PolicyFieldLabelType.SALDO_POSIZIONE.getValue());
		requiredFieldList.add(PolicyFieldLabelType.DATA_AGGIORNAMENTO.getValue());
		//indirizzo e assicurato non ci sono sempre
		
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
