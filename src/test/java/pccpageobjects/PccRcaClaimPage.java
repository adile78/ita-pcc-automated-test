package pccpageobjects;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PccRcaClaimPage extends PccAutoClaimPage {

	public PccRcaClaimPage(WebDriver driver) {

		super(driver);
	}

	@Override
	protected void selectCoverage(String claimType) {

		WebElement damageBox = getDamageTypeBox();
		List<WebElement> damageElementList = damageBox.findElements(By.tagName("input"));

		damageElementList.get(0).click();
		WebElement claimTypeBox = getClaimTypeBox();
		List<WebElement> claimElementList = claimTypeBox.findElements(By.tagName("label"));
		// select radio button based on label containing claim type
		for (WebElement claimEl : claimElementList) {
			if (StringUtils.containsIgnoreCase(claimEl.getText(), claimType)) {
				claimEl.click();
			}
		}
	}

	protected void selectEventCategory(String eventCategory) {

		Select eventCat = new Select(driver.findElement(By.id(portletName + "_categoriaEvento")));
		eventCat.selectByVisibleText(eventCategory);
	}



	@Override
	protected void setCarDriverInformation(Map<String, String> params) {
		// TODO Auto-generated method stub

	}

}
