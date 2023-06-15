package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class GenericRegistrationPage implements RegistrationPage{

	protected WebDriver driver;
	
	protected static Logger logger = LogManager.getLogger();
	
	protected By userNameBy;
	protected By passwordBy;
	protected By passwordConfirmationBy;
	protected By confirmBtnBy;

	protected By fiscalCodeBy;
	protected By vatNumberBy;
	protected By policyNumberBy;
	
	public GenericRegistrationPage(WebDriver driver){
		this.driver = driver;
	}
	
	
	public void setUsername(String userName) {

		WebElement txbxUsernName = driver.findElement(userNameBy);
		txbxUsernName.sendKeys(userName);
	}
	
	public void setPassword(String password) {

		WebElement txbxUsernName = driver.findElement(passwordBy);
		txbxUsernName.sendKeys(password);
	}
	
	public void setPasswordConfirmation(String rePassword) {

		WebElement txbxUsernName = driver.findElement(passwordConfirmationBy);
		txbxUsernName.sendKeys(rePassword);
	}

	public void clickBtnConfirm() {

		WebElement btnRegister = driver.findElement(confirmBtnBy);
		btnRegister.click();
	}
	
	public void setFiscalCode(String fiscalCode) {

		WebElement txbxFiscalCode = driver.findElement(fiscalCodeBy);
		txbxFiscalCode.sendKeys(fiscalCode);
	}

	public void setVatNumber(String vatNumber) {

		WebElement txbxVatNumber = driver.findElement(vatNumberBy);
		txbxVatNumber.sendKeys(vatNumber);
	}

	public void setPolicyNumber(String policyNumber) {

		WebElement txbxPolicyNumber = driver.findElement(policyNumberBy);
		txbxPolicyNumber.sendKeys(policyNumber);
	}
	
	public abstract void setPerson(String personType);
	
	
}
