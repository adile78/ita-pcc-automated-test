package pageelementobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class GenericPolicyBox implements PolicyInfoBox {

	protected WebDriver driver;
	
	protected PolicyInfoBox infoBox;
	protected PolicyActionBox actionBox;
	

	protected static Logger logger = LogManager.getLogger();
	
	public GenericPolicyBox(WebDriver driver){

		this.driver = driver;
	}

}
