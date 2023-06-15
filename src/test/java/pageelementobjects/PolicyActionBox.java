package pageelementobjects;

import java.util.List;

import org.openqa.selenium.WebElement;

/**
 * @author barbetti_be
 *
 */
public interface PolicyActionBox {

	/**
	 * The list of all the policy CTAs available on dashboard page
	 * 
	 * @return the list of a tag WebElement available on dashboard page
	 */
	List<WebElement> getCtaList();

}
