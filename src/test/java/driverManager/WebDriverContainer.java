package driverManager;

import org.openqa.selenium.WebDriver;

public class WebDriverContainer {
	
	private WebDriverManager driverManager;
	protected WebDriver driver;
	
	public WebDriverContainer() {

		driverManager = WebDriverManagerFactory.getManager(); 
		
	}	
	
	public WebDriver getWebDriver(){
		return driverManager.getDriver();	
		//	driver.manage().deleteAllCookies();
		//	driver.manage().window().fullscreen(); // maximize();	
	}
}
