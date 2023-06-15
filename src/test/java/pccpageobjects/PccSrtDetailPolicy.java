package pccpageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageobjects.DetailPolicy;

/**
 * @author barbetti_be
 *
 */
public class PccSrtDetailPolicy extends PccSavingsDetailPolicy {

	private By srtClass;
	private By myProductsBy;
	private By totaleValorePolizzaBoxBy;
	private By portfolioDatatableSRTBy;
	private By summaryGraphsBy;
	private By myPolicyBoxBy;
	private By boxGraficoBy;
	private By polizzeAccordionBy;
	private By accordionGroupBy;

	public PccSrtDetailPolicy(WebDriver driver, DetailPolicy policy) {

		super(driver, policy);
		srtClass = By.className("protezionerisparmio_srt");
		myProductsBy = By.className("my_products");
		totaleValorePolizzaBoxBy = By.id("totaleValorePolizza");
		portfolioDatatableSRTBy = By.id("portfolioDatatableSRT");
		summaryGraphsBy = By.className("summary_graphs");
		myPolicyBoxBy = By.className("le-mie-polizze");
		boxGraficoBy = By.className("box-grafico");
		polizzeAccordionBy = By.id("polizzeAccordion");
		accordionGroupBy = By.className("accordion-group");
	}

	public boolean checkSrtPolicy() {

		boolean result = checkTotValPolizzaBox() && checkPortfolioDatatableSRT() && checkBoxGrafico()
				&& checkPolizzeAccordion();
		return result;
	}

	public boolean checkTotValPolizzaBox() {

		List<WebElement> totaleValorePolizza = getMyProductsBox().findElements(totaleValorePolizzaBoxBy);
		if (totaleValorePolizza.size() == 0)
			return false;
		return true;
	}

	public boolean checkPortfolioDatatableSRT() {

		List<WebElement> portfolioDatatableSRT = getMyProductsBox().findElements(portfolioDatatableSRTBy);
		if (portfolioDatatableSRT.size() == 0)
			return false;
		return true;
	}

	public boolean checkBoxGrafico() {

		List<WebElement> boxGrafici = getSummaryGraphs().findElements(boxGraficoBy);
		if (boxGrafici.size() < 2)
			return false;
		return true;
	}

	public boolean checkPolizzeAccordion() {

		WebElement accordion = getSrtBox().findElement(polizzeAccordionBy);
		//TODO accordion-toggle collapsed
		return true;
	}

	public WebElement getSrtBox() {

		return driver.findElement(srtClass);
	}

	public WebElement getMyProductsBox() {

		return getSrtBox().findElement(myProductsBy);
	}

	public WebElement getSummaryGraphs() {

		return getSrtBox().findElement(summaryGraphsBy);
	}

	public WebElement getMyPolicyBox() {

		return getSrtBox().findElement(myPolicyBoxBy);
	}
}
