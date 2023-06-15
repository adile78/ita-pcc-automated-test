package enums;

public enum TpaType {
	
	POLICY_ID("policyId"),
	CALENDAR_START_DAY("calendarStartDay"),
	CALENDAR_END_DAY("calendarEndDay"),
	CALENDAR_START_MONTH("calendarStartMonth"),
	CALENDAR_END_MONTH("calendarEndMonth"),
	CALENDAR_START_YEAR("calendarStartYear"),
	CALENDAR_END_YEAR("calendarEndYear"),
	CALENDAR_DAY("calendarDay"),
	CALENDAR_MONTH("calendarMonth"),
	CALENDAR_YEAR("calendarYear"),
	INSURED_FISCAL_CODE("insuredFiscalCode"),
	DAMAGE_TYPE_ID("damageTypeId"),
	IS_ASSISTED("isAssisted"),
	SERVICE("service"),
	EVENT_CATEGORIES("eventCategories"),
	STRUCTURE_TYPE("structureType"),
	ASSET_INPUT("assetInput"),
	DOCTOR("doctor"),
	DOCUMENT_TYPE("documentType"),
	IBAN("iban"),
	FILE_UPLOAD("fileUpload"),
	DOCUMENT_EXPENSE("documentExpense"),
	AMOUNT_EXPENSE("amountExpense"),
	NUMBER_EXPENSE("numberExpense"),
	DAY_EXPENSE("dayExpense"),
	MONTH_EXPENSE("monthExpense"),
	YEAR_EXPENSE("yearExpense"),
	DESCRIPTION("description"),
	PHONE("phone"),
	RECONTACT_DAY("recontactDay"),
	RECONTACT_MONTH("recontactMonth"),
	RECONTACT_YEAR("recontactYear"),
	TIME("time");
	
	
	TpaType(String value){
		
		this.value = value;
	}
	
	String value;
	
	public String getValue(){
		
		return value;
	}
	
}



