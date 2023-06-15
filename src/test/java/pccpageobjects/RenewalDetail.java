package pccpageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RenewalDetail {

		private WebDriver driver;
		private WebElement mainBox;
		private By titleBy;
		private By recapBy;
		private By bodyBy;
		
		private By renewBtnBy;
		
		public RenewalDetail(WebDriver driver, WebElement mainBox){
			this.driver = driver;
			this.mainBox = mainBox;
			
			renewBtnBy = By.id("rinnovaPolizza2");
			bodyBy = By.className("body-rinnovi-container");
		}
	
		
		public void rinnovaPolizza(){
			WebElement renewBtn = mainBox.findElement(renewBtnBy);
			renewBtn.click();
		}
		
}
