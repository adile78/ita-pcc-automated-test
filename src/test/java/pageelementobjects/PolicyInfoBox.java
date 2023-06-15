package pageelementobjects;

import java.util.Map;

import enums.PolicyCategoryType;

/**
 * @author barbetti_be
 *
 */
public interface PolicyInfoBox {

	
	/**
	 * The product name of the policy
	 * 
	 * @return the product name of the policy
	 */
	String getProductName();
	
	
	/**
	 * The company name of the policy
	 * 
	 * @return the company name of the policy
	 */
	String getCompanyName();
	
	/**
	 * The category type of the policy
	 * 
	 * @return the category field of element PolicyCategoryType of the policy
	 */
	PolicyCategoryType getCategoryType();
	
	/**
	 * The number of the policy
	 * 
	 * @return the number of the policy
	 */
	String getPolicyNumber();

	/**
	 * The map of policy fields labels and value shown in the policy box
	 * 
	 * @return a map of texts of div tags WebElement
	 */
	Map<String, String> getPolicyFieldLabelValueList();
		
	/**
	 * Open the policy detail box on browser
	 */
	void clickPolicyDetail();

}
