package pccelementobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageelementobjects.GenericPolicyInfoBox;

/**
 * @author barbetti_be
 *
 */
public class PccPolicyInfoBox extends GenericPolicyInfoBox {

	public PccPolicyInfoBox(WebDriver driver, WebElement mainBox) {
		
		super(driver, mainBox);		
	}
	
}
