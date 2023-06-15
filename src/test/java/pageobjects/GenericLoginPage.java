package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.WebElementUtil;

public abstract class GenericLoginPage implements LoginPage {

	protected WebDriver driver;

	protected static Logger logger = LogManager.getLogger();

	protected By usernameBy;
	protected By passwordBy;
	protected By logInBtnBy;

	public GenericLoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public void setUsername(String username) {

		WebElementUtil.waitForPresenceOf(driver, usernameBy);
		WebElement txtbxUserName = driver.findElement(usernameBy);
		txtbxUserName.sendKeys(username);
	}

	@Override
	public void setPassword(String password) {

		WebElementUtil.waitForPresenceOf(driver, passwordBy);
		WebElement txtbxPassword = driver.findElement(passwordBy);
		txtbxPassword.sendKeys(password);
	}

	@Override
	public void clickBtnLogin() {

		WebElementUtil.waitForPresenceOf(driver, logInBtnBy);
		WebElement btnLogIn = driver.findElement(logInBtnBy);
		btnLogIn.click();
	}

	@Override
	public void loginAction(String username, String password) {
		checkRemoveBanner();
		setUsername(username);
		setPassword(password);
		clickBtnLogin();
	};

	//remove banner promo MyAxa
	protected void checkRemoveBanner() {

		if (driver.findElements(By.className("banner")).size() > 0) {
			JavascriptExecutor js = null;
			if (driver instanceof JavascriptExecutor) {
				js = (JavascriptExecutor) driver;
			}
			js.executeScript("return document.getElementsByClassName('banner')[0].remove();");
		}
	}

}
