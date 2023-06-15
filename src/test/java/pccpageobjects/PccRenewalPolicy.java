package pccpageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enums.PolicyCategoryType;
import pageobjects.GenericListPolicy;
import pageobjects.Modale;
import pageobjects.ListPolicy;
import pccelementobjects.PccModale;
import utilities.WebElementUtil;

public class PccRenewalPolicy extends GenericListPolicy {

	// box with renewal info
	private WebElement renewalPolicyBox;

	private By renewalPolicyBoxDueDate;
	private By renewalPolicyBoxBtn;
	private By renewalPolicyBoxTitle;
	private String waitBoxClass;
	private RenewalDetail retail;

	private Modale genericErrorModale;
	private Modale maxPaymentAttempsModale;

	// renewal policy detail

	public PccRenewalPolicy(WebDriver driver, ListPolicy policy) {
		super(driver, policy);
		renewalPolicyBox = driver.findElement(By.className("dashboard-alert-box-policy"));
		renewalPolicyBoxBtn = By.className("description-text");
		renewalPolicyBoxDueDate = By.className("description-date");
		renewalPolicyBoxTitle = By.className("introduction-text");
		waitBoxClass = "rinnovi-wait";
		
		// modale
		String modaleId_genErr = "rinnovaPaymentGenericError";
		genericErrorModale = new PccModale(driver, modaleId_genErr, false);
		
		String modaleId_maxPay= "maxPaymentAttemps";		
		maxPaymentAttempsModale = new PccModale(driver, modaleId_maxPay, false);
		
	}

	public WebElement getRenewalPolicyBox() {
		return renewalPolicyBox;
	}

	public void setRenewalPolicyBox(WebElement renewalPolicyBox) {
		this.renewalPolicyBox = renewalPolicyBox;
	}

	public WebElement getRenewalPolicyBoxBtn() {
		return renewalPolicyBox.findElement(renewalPolicyBoxBtn);
	}

	public WebElement getRenewalPolicyBoxTitle() {
		return renewalPolicyBox.findElement(renewalPolicyBoxTitle);
	}

	public WebElement getRenewalPolicyBoxDueDate() {
		return renewalPolicyBox.findElement(renewalPolicyBoxDueDate);
	}

	// title, dueDate, button
	public boolean hasRequiredInfo() {

		WebElement titleElement = getRenewalPolicyBoxTitle();
		WebElement dueDateElement = getRenewalPolicyBoxDueDate();
		WebElement buttonElement = getRenewalPolicyBoxBtn();

		if (titleElement != null && dueDateElement != null && buttonElement != null)
			return true;

		return false;
	}

	public void clickRenewalBoxBtn() {
		WebElement buttonElement = getRenewalPolicyBoxBtn();
		if (buttonElement != null)
			buttonElement.click();
	}
	
	public RenewalDetail getRetail() {
		List<WebElement> retailBox = driver.findElements(By.className(PolicyCategoryType.AUTO.getPolicyDetailClass()));
		if (retailBox.size() == 0)
			return null;
		// TODO: check if it's correct
		retail = new RenewalDetail(driver, renewalPolicyBox.findElement(By.className("mainSection")));
		return retail;

	}

	public boolean isWaitingBoxVisibile() {

		return WebElementUtil.hasClass(renewalPolicyBox, waitBoxClass);
	}
	
	
	/* when trying to access Bassilichi page */
	public boolean isGenericErrorModaleVisibile(){
		
		return genericErrorModale.isModaleVisible();
	}	
	
	
	private void escGenericErrorModale(){
		
		genericErrorModale.clickBtn();
	}
	
	/* when trying to access Bassilichi page after 3 times*/
	public boolean isMaxPaymentAttempsModaleVisibile(){
		
		return maxPaymentAttempsModale.isModaleVisible();
	}	
	

	private void escMaxPaymentAttempsModale(){
		
		maxPaymentAttempsModale.clickBtn();
	}
	
	public void returnToHomePage(){
		if (isMaxPaymentAttempsModaleVisibile())
			escMaxPaymentAttempsModale();
		else if (isGenericErrorModaleVisibile())
			escGenericErrorModale();
	}

	@Override
	public List<String> getRequiredFieldList() {
		// TODO getRequiredFieldList renewal
		return null;
	}

	@Override
	public List<String> getRequiredActionList() {
		// TODO getRequiredActionList renewal
		return null;
	}
	
}
