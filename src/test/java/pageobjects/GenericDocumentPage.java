package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;



public abstract class GenericDocumentPage implements DocumentPage {

	protected WebDriver driver;
	
	protected static Logger logger = LogManager.getLogger();

	public GenericDocumentPage(WebDriver driver) {
		this.driver = driver;
	}

}
