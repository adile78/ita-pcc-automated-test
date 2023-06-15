package pccpageobjects;

import org.openqa.selenium.By;

public class SrtTemplate {

	private By policyTotalAmount;
	
	public SrtTemplate(){
		
		policyTotalAmount = By.id("totaleValorePolizza");
	}
}
