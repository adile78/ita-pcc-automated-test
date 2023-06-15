package enums;

/**
 * @author barbetti_be
 *
 */
public enum PolicyCategoryType {

	AUTO("auto", "car", "prodottoauto"),
	HOME("home", "shelter", "protezionecasapersona"),
	PROTECTION("protection", "umbrella", "protezionevita"),
	SAVINGS("savings", "piggybank", "protezionerisparmio"),
	UNIT_LINKED("unit linked", "hands", "protezioneprevidenza"),
	WORKERS_PROTECTION("workers protection", "worker", "protezioneazienda");	
	
	PolicyCategoryType(String category, String iconType, String policyDetailClass){
		
		this.category = category;
		this.iconType = iconType;
		this.policyDetailClass = policyDetailClass;
	}
	
	private String category;
	private String iconType;
	private String policyDetailClass;
	
	public String getCategory(){
		return category;
	}
	
	public String getIconType(){
		return iconType;
	}
	
	public String getPolicyDetailClass(){
		return policyDetailClass;
	}
	
}
