package pageobjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

/**
 * @author barbetti_be
 *
 */
public interface DetailPolicy {

	/**
	 * @return the name of the policy's company
	 */
	String getCompanyName();

	/**
	 * @return the name of the policy's product
	 */
	String getProductName();

	/**
	 * Set the category class value for the corresponding category policy
	 * container
	 * 
	 * @param categoryPolicyClass
	 *            string value representing the class value of the html tag for
	 *            the container
	 */
	void setCategoryPolicyDetailClass(String categoryPolicyClass);

	/**
	 * @return main section containing all
	 */
	public WebElement getPolicyDetailContainer();
	
	/**
	 * @param boxTitle
	 *            the title of the required container box
	 * @return the <code>WebElement</code> container for the specific box
	 */
	WebElement getBox(String boxTitle);

	/**
	 * @return a map of (label, value) of the generic policy fields
	 */
	Map<String, String> getPolicyGenericDetails();

	/**
	 * @returna map of (label, value) of the generic movements fields
	 */
	Map<String, String> getMovementsGenericDetails();
	
	/**
	 * @return the movements history as a list of the WebElements' text
	 */
	List<String> getMovementsHistoryDetails();

	/**
	 * @return the list of policy's insureds as WebElements' text
	 */
	List<String> getInsuredsDetails();

	/**
	 * @return the list of policy's coverages as WebElements' text
	 */
	List<String> getCoveragesDetails();

	/**
	 * @return the list of policy's beneficiaries as WebElements' text
	 */
	List<String> getBeneficiariValueDetails();

	/**
	 * @param parameters
	 *            optional values to perform specific tests on generic policy
	 *            information
	 * @return true if all checks on generic policy information are good
	 */
	boolean checkGenericInfo(Map<String, String> parameters);

	/**
	 * @param parameters
	 *            optional values to perform specific tests on specific category
	 *            policy information
	 * @return true if all checks on category policy information are good
	 */
	boolean checkCategoryInfo(Map<String, String> parameters);
}
