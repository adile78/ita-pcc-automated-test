package pccpageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageobjects.GenericContactsPage;

public class PccContactsPage extends GenericContactsPage {

	private By boxContactsBy;
	
	public PccContactsPage(WebDriver driver) {
		super(driver);
		
		boxContactsBy = By.className("axa-mail");		
	}

	private WebElement getBoxContacts(){
		return driver.findElement(boxContactsBy);
	}
	
	
	/**
	 * @return list of a tags with axa assistance's link by company
	 */
	public List<WebElement> getContacts(){
		
		return getBoxContacts().findElements(By.tagName("a"));
	}
	
	public boolean isSingleCompany() {

		List<WebElement> companies = getContacts();
		if (companies.size() == 1)
			return true;
		return false;
	}

	public boolean isMultiCompany() {

		List<WebElement> companies = getContacts();
		if (companies.size() > 1)
			return true;
		return false;
	}
	
	public List<String> getContactsType(){
		
		List<String> contactsType =  new ArrayList<String>();
		for (WebElement contact: getContacts()){
			
			contactsType.add(contact.getText());
		}		
		return contactsType;
	}
	

}
