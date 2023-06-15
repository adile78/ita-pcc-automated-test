package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class GenericDashboardPage implements DashboardPage {

	protected WebDriver driver;

	protected static Logger logger = LogManager.getLogger();
	
	public GenericDashboardPage(WebDriver driver) {
		this.driver = driver;
	}

}
