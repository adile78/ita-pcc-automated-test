package pageobjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class GenericModale implements Modale{
	

	protected WebDriver driver;
	
	protected static Logger logger = LogManager.getLogger();
	
	protected By id;
	protected By modaleHeaderBy;
	protected By modaleBodyBy;
	protected By modaleFooterBy;
	protected By btn;
		
	protected Boolean multipleButtons;

	protected GenericModale(WebDriver driver, String id, By modaleHeaderBy, By modaleBodyBy, By modaleFooterBy, Boolean multipleButtons) {
		this.driver = driver;
		
		this.id = By.id(id);		
		this.multipleButtons = multipleButtons;		
	}

	public void clickBtn() {
		
		getButtons().get(0).click();
	}

	public void clickSxBtn() {

		getButtons().get(0).click();
	}
	
	public void clickDxBtn() {

		getButtons().get(1).click();
	}

	public WebElement getModale(){
		
		return driver.findElement(id);
	}

	public WebElement getModaleHeader(){
		
		return driver.findElement(modaleHeaderBy);
	}

	public WebElement getModaleBody(){
		
		return driver.findElement(modaleBodyBy);
	}
	
	public WebElement getModaleFooter(){
		
		return driver.findElement(modaleFooterBy);
	}
	
	public boolean isModaleVisible(){
		return getModale().isDisplayed();
	}
		
	public abstract List<WebElement> getButtons();

}
