package enums;

public enum ClaimFieldType {

	DATE("date"),
	TIME("time"),
	CITY("city"),
	CITY_FIRST_LETTERS("cityFirstLetters"),
	ADDRESS("address"),
	CITY_AVAILABILITY("cityAv"),
	CITY_AV_FIRST_LETTERS("cityAvFirstLetters"),
	ADDRESS_AVAILABILITY("addressAv"),
	POLICY_NUMBER("policyNumber"),
	PHONE_NUMBER("phoneNumber"),
	DOCUMENT_PATH("documentPathList"),
	DOCUMENT_CATEGORY_LIST("documentCategoryList"),
	EVENT_CATEGORY("eventCategory"),
	DAMAGE_GOOD("damageGood"),
	IS_OWNER("isOwner"),
	IS_DRIVER("isDriver"),
	DRIVER_GENDER("driverGender"),
	DRIVER_NAME("driverName"),
	DRIVER_SURNAME("driverSurname"),
	DRIVER_BIRTHDAY("driverBirthday"),
	DRIVER_BIRTHDAY_CITY_FIRST_LETTERS("driverBirthdayCityFirstLetters"),
	DRIVER_BIRTHDAY_CITY("driverBirthdayCity"),
	DRIVER_ADDRESS("driverAddress"),
	DRIVER_CITY_FIRST_LETTERS("driverCityFirstLetters"),
	DRIVER_CITY("driverCity"),
	DRIVER_CAP("driverCap"),
	DRIVER_PHONE("driverPhone"),
	IS_INJURY("isInjury"),
	INJURY_DESCRIPTION("injuryDescription"),
	EVENT_DESCRIPTION("eventDescription"),
	IBAN("iban");
	
	ClaimFieldType(String value){
		
		this.value = value;
	}
	
	String value;
	
	public String getValue(){
		
		return value;
	}
	
}
