package pccpageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import enums.CallToActionType;
import enums.PolicyFieldLabelType;
import pageobjects.GenericListPolicy;
import pageobjects.ListPolicy;

public class PccAutoListPolicy extends GenericListPolicy {

	public PccAutoListPolicy(WebDriver driver, ListPolicy policy) {

		super(driver, policy);
	}

	@Override
	public List<String> getRequiredFieldList() {

		List<String> requiredFieldList = new ArrayList<String>();
		requiredFieldList.add(PolicyFieldLabelType.VALIDITA.getValue());
		requiredFieldList.add(PolicyFieldLabelType.VEICOLO.getValue());
		requiredFieldList.add(PolicyFieldLabelType.TARGA.getValue());
		requiredFieldList.add(PolicyFieldLabelType.PREMIO_TOTALE_PAGATO.getValue());
		
		return requiredFieldList;
	}

	@Override
	public List<String> getRequiredActionList() {

		List<String> requiredActionList = new ArrayList<String>();
		requiredActionList.add(CallToActionType.APRI_SINISTRO.getValue());
		requiredActionList.add(CallToActionType.VISUALIZZA_SINISTRI.getValue());
		requiredActionList.add(CallToActionType.SCARICA_ATTESTATO_RISCHIO.getValue());
		
		return requiredActionList;
	}

}
