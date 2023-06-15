package pageobjects;

import java.util.List;

import enums.PolicyCategoryType;
import pageelementobjects.PolicyActionBox;
import pageelementobjects.PolicyInfoBox;

/**
 * @author barbetti_be
 *
 */
public interface ListPolicy {

	/**
	 * The category type of the policy
	 * 
	 * @return the category field of element PolicyCategoryType of the policy
	 */
	public PolicyCategoryType getCategoryType();

	/**
	 * The box with all available policy ctas
	 * 
	 * @return the PolicyActionBox element
	 */
	PolicyActionBox getActionBox();

	/**
	 * The box with all information on policy
	 * 
	 * @return the PolicyInfoBox element
	 */
	PolicyInfoBox getInfoBox();

	/**
	 * @return a list of required common fields of policy list
	 */
	List<String> getRequiredFieldList();

	/**
	 * For each fields label of the policy box looks for required fields and
	 * values. Every line has one or more rows that has two divs one with label
	 * one with value
	 * 
	 * @return false if a field's label is null
	 */
	boolean checkListLabelsAndValues();

	/**
	 * For each category of policy looks for all required ctas
	 * 
	 * @return false if a cta is not found
	 */
	boolean checkDashboardListCtas();

	/**
	 * @return a list of required common ctas of policy list
	 */
	List<String> getRequiredActionList();

	/**
	 * Click on policy detail on dashboard page
	 * 
	 * @return the DetailPolicy object
	 */
	DetailPolicy clickPolicyDetail();

	/**
	 * @param ctaType click the ctaType call to action
	 * @return the DetailPolicy object for specific actions
	 */
	DetailPolicy clickCtaByType(String ctaType);

}
