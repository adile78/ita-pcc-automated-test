package pccpageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageobjects.GenericLoginPage;
import pageobjects.GenericModale;
import pccelementobjects.PccModale;
import utilities.PropsUtil;
import utilities.WebElementUtil;

public class PccLoginPage extends GenericLoginPage {

	private static String portletName = "_com_axa_portal_ita_pcc_LoginWebPortlet_";
	private By registerBtnBy;
	private By retrievePasswordBtnBy;
	private By retrieveUsernameBtnBy;
	private GenericModale badCredentialsModale;

	public PccLoginPage(WebDriver driver) {

		super(driver);

		usernameBy = By.id(portletName + "login");
		passwordBy = By.id(portletName + "password");
		logInBtnBy = By.id(portletName + "sign-in");
		// FIXME inserire un id negli elementi a
		registerBtnBy = By.xpath("//*[text()[contains(.,'Registrati')]]");  
		retrievePasswordBtnBy = By.xpath("//*[text()[contains(.,'Recupera la Password')]]");
		retrieveUsernameBtnBy = By.xpath("//*[text()[contains(.,'Recupera la Username')]]");

		// modale
		String modaleId = portletName + "bad-credentials-view";
		badCredentialsModale = new PccModale(driver, modaleId, false);
	}

	public boolean isBadCredentialsModaleVisible() {

		return badCredentialsModale.isModaleVisible();
	}

	public void clickBtnRegister() {
		checkRemoveBanner();
		WebElement registerBtn = driver.findElement(registerBtnBy);
		registerBtn.click();
	}

	public boolean isPasswordError() {

		WebElement password = driver.findElement(passwordBy);
		return WebElementUtil.hasClass(password, PropsUtil.getPropertyValue("error.field"));

	}

	public boolean isUsernameError() {

		WebElement username = driver.findElement(usernameBy);
		return WebElementUtil.hasClass(username, PropsUtil.getPropertyValue("error.field"));
	}

	public void clickBtnRetrievePassword() {

		WebElement retrievePswdBtn = driver.findElement(retrievePasswordBtnBy);
		retrievePswdBtn.click();
	}

	public void clickBtnRetrieveUsername() {

		WebElement retrieveUsrnmBtn = driver.findElement(retrieveUsernameBtnBy);
		retrieveUsrnmBtn.click();
	}
	
	public boolean isLoginPage(){
		
		WebElement title = driver.findElement(By.tagName("h1"));
		if ("Area clienti del Gruppo AXA Italia".equals(title.getText()))
				return true;
		return true;
	}

}
