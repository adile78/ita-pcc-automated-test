package pageobjects;

import java.util.Map;

import enums.RegistrationFieldType;

public interface RegistrationPage {

	
	/**
	 * set privacy flags
	 */
	public void setPrivacy();

	/**
	 * @param registrationParams 
	 */
	public void setPersonalInformationPerson(Map<RegistrationFieldType, String> registrationParams);

	public void setPersonalInformationOrganization(Map<RegistrationFieldType, String> registrationParams);

	
	public void firstStepRegistrationPerson(Map<RegistrationFieldType, String> registrationParams);

	/* insert password to register a person */
	public void secondStepRegistrationPerson(Map<RegistrationFieldType, String> registrationParams);

	/* insert fields to register organization */
	public void firstStepRegistrationOrganization(Map<RegistrationFieldType, String> registrationParams);

	/* insert password to register organization */
	public void secondStepRegistrationOrganization(Map<RegistrationFieldType, String> registrationParams);

}
