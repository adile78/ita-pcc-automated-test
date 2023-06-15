package pccpageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PccRightShoulder {

	private WebDriver driver;
	
	private By containerBy;
	private By containerTitleBy;
	private By containerBodyBy;

	public PccRightShoulder(WebDriver driver) {

		this.driver = driver;

		containerBy = By.id("barContainer");
		containerTitleBy = By.className("barTitle");
		containerBodyBy = By.className("barBody");
	}

	private WebElement getContainer() {

		return driver.findElement(containerBy);
	}

	public WebElement getTitle() {

		return getContainer().findElement(containerTitleBy);
	}

	public WebElement getBody() {

		return getContainer().findElement(containerBodyBy);
	}

	public WebElement getShoulderContent() {
		return getBody();
	}

}
