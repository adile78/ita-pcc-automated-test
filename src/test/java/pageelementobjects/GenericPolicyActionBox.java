package pageelementobjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class GenericPolicyActionBox implements PolicyActionBox {

	protected WebDriver driver;

	protected static Logger logger = LogManager.getLogger();
	
	protected WebElement actionBox;	

	public GenericPolicyActionBox(WebDriver driver, WebElement actionBox){
		
		this.driver = driver;
		this.actionBox = actionBox;
	}
	
	public List<WebElement> getCtaList(){
		
		List<WebElement> ctaList = actionBox.findElements(By.tagName("a"));		
		return ctaList;
	}
	
}
