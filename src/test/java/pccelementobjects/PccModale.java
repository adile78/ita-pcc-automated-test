package pccelementobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageobjects.GenericModale;

public class PccModale extends GenericModale {

	// By btn = By.xpath("//*[text()[contains(.,'CHIUDI')]]");

	public PccModale(WebDriver driver, String id, Boolean multipleButtons) {
		super(driver, id, By.className("modal-header"), By.className("modal-body"), By.className("modal-footer"),
				multipleButtons);
		this.btn = By.tagName("button");
	}

	@Override
	public List<WebElement> getButtons() {

		return getModaleFooter().findElements(By.tagName("button"));

	}

}
