package pccpageobjects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import enums.PaymentFieldType;

public class BassilichiPage {
	
	private WebDriver driver;

	private By cardNumberId = By.id("Ecom_Payment_Card_Number");
	private By ownerNameId = By.id("Ecom_Payment_Card_Name");
	private By monthNumerId = By.id("Ecom_Payment_Card_ExpDate_Month");
	private By expiredYearId = By.id("Ecom_Payment_Card_ExpDate_Year");
	private By cvvId = By.id("Ecom_Payment_Card_Verification");
	private By emailId = By.id("Ecom_Consumer_EMail_Address");
	
	private By privacyChkBxId = By.id("privacycheck");
	
	private By payBtnId = By.id("proceedPay");
	private By cancelBtnId = By.id("cancelPay");
	
	
	public BassilichiPage(WebDriver driver){
		this.driver = driver;
	}
	
	
	private void setCardNumber(String cardNumber) {
		WebElement txtCardNumber = driver.findElement(cardNumberId);
		txtCardNumber.sendKeys(cardNumber);
	}


	private void setOwnerName(String ownerName) {
		WebElement txtOwnerName = driver.findElement(ownerNameId);
		txtOwnerName.sendKeys(ownerName);
	}

	private void setMonthNumer(String monthNumer) {
		Select slctMonthNumer = new Select(driver.findElement(monthNumerId));
		slctMonthNumer.selectByVisibleText(monthNumer);
	}

	private void setExpiredYear(String expiredYear) {
		Select slctExpiredYear = new Select(driver.findElement(expiredYearId));
		slctExpiredYear.selectByVisibleText(expiredYear);
	}


	private void setCvv(String cvv) {
		WebElement txtCvv = driver.findElement(cvvId);
		txtCvv.sendKeys(cvv);
	}


	private void setEmail(String email) {
		WebElement txtEmail = driver.findElement(emailId);
		txtEmail.sendKeys(email);
	}


	private void setPrivacyChkBx() {
		WebElement privacyChkBx = driver.findElement(privacyChkBxId);
		privacyChkBx.click();
	}


	private void clickPayBtn() {
		WebElement payBtn = driver.findElement(payBtnId);
		payBtn.click();
	}


	private void clickCancelBtn() {
		WebElement cancelBtn = driver.findElement(cancelBtnId);
		cancelBtn.click();
	}
	
	
	public void payTitle(Map<PaymentFieldType,String> paymentParams){
		
		setCardNumber(paymentParams.get(PaymentFieldType.CARD_NUMBER));
		setOwnerName(paymentParams.get(PaymentFieldType.OWNER_NAME));
		setMonthNumer(paymentParams.get(PaymentFieldType.MONTH_NUMBER));
		setExpiredYear(paymentParams.get(PaymentFieldType.EXPIRED_YEAR));
		setCvv(paymentParams.get(PaymentFieldType.CVV));
		setEmail(paymentParams.get(PaymentFieldType.EMAIL));
		setPrivacyChkBx();
		
		clickPayBtn();
	}
	
	
	public void cancelPayment(){
		clickCancelBtn();
	}
	
	
}
