package pccpageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import enums.CallToActionType;
import enums.CompanyType;
import enums.PolicyFieldLabelType;
import pageobjects.GenericListPolicy;
import pageobjects.ListPolicy;

public class PccWorkersProtectionListPolicy  extends GenericListPolicy {

	public PccWorkersProtectionListPolicy(WebDriver driver, ListPolicy policy) {
		super(driver, policy);

	}

	@Override
	public List<String> getRequiredFieldList() {

		List<String> requiredFieldList = new ArrayList<String>();
		requiredFieldList.add(PolicyFieldLabelType.VALIDITA.getValue());
		//TODO: check se va bene lasciare premio pagato
		requiredFieldList.add(PolicyFieldLabelType.PREMIO_TOTALE_PAGATO.getValue());
		requiredFieldList.add(PolicyFieldLabelType.FRAZIONAMENTO.getValue());
		requiredFieldList.add(PolicyFieldLabelType.CONTRAENTI.getValue());
	//	requiredFieldList.add(PolicyFieldLabelType.ASSICURATI.getValue());
		
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
