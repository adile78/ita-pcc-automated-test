package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class GenericContactsPage implements ContactsPage {

	protected WebDriver driver;

	protected static Logger logger = LogManager.getLogger();

	protected GenericContactsPage(WebDriver driver) {
		this.driver = driver;
	}
	
}
