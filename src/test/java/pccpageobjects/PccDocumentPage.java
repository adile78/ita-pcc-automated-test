package pccpageobjects;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageobjects.GenericDocumentPage;
import pageobjects.Modale;
import pccelementobjects.PccModale;
import pccelementobjects.PccNavigationTabBar;
import utilities.PropsUtil;
import utilities.WebElementUtil;

/**
 * @author barbetti_be
 *
 */
public class PccDocumentPage extends GenericDocumentPage {

	private By genericErrorBoxBy;
	private By genErrRetryBtn;
	private String portletName;
	

	private PccNavigationTabBar navigationTabBar;

	public PccDocumentPage(WebDriver driver) {
		super(driver);
		genericErrorBoxBy = By.className("error-retry-container");
		genErrRetryBtn = By.className("retry-button");

	}

	public boolean isMultiCompany() {

		List<WebElement> tabs = driver.findElements(By.className("nav-container"));
		if (tabs.size() != 0) {
			return true;
		}
		return false;
	}

	private PccNavigationTabBar getNavigationTabBar() {

		if (navigationTabBar == null)
			navigationTabBar = new PccNavigationTabBar(driver);
		return navigationTabBar;
	}

	public boolean isGenericError() {

		WebElement genericError = driver.findElement(genericErrorBoxBy);
		if (genericError != null)
			return true;
		return false;
	}

	/**
	 * in case of generic error
	 */
	public void retry() {

		WebElement genericError = driver.findElement(genericErrorBoxBy);
		if (genericError != null) {
			WebElement retryBtn = genericError.findElement(genErrRetryBtn);
			if (retryBtn != null) {
				retryBtn.click();
			}
		}
	}

	/**
	 * in case of multicompany user
	 */
	public List<String> getCompanies() {

		return getNavigationTabBar().getCompanies();
	}

	/**
	 * in case of multicompany user
	 */
	public void selectCompanySection(String company) {

		PccNavigationTabBar navTab = getNavigationTabBar();
		// if (!company.equals(navTab.getActiveTabCompany())) {
		if (!((navTab.getActiveTabCompany()).equals(company))) {

			navTab.selectTabByCompany(company);
		}
	}

	public String getPortletName() {

		if (StringUtils.isBlank(portletName)) {

			WebElement portletSection = driver
					.findElement(By.className("portlet-boundary_com_axa_portal_ita_mydocuments_MyDocumentsPortlet_"));
			portletName = portletSection.getAttribute("id").replace("p_p_id", "");
		}
		return portletName;
	}

	public void downloadDocument() {
		WebElement tabPaneActive = getNavigationTabBar().getActiveTabPane();
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			logger.error("Timeout exception during document download", e);
			e.printStackTrace();
		}
//    	WebElement b = tabPaneActive.findElement(docsContainerBy);
		WebElement docsContainer = tabPaneActive.findElement(By.id(getPortletName() + "docsContainer"));
		List<WebElement> anchors = docsContainer.findElements(By.tagName("a"));
		for (WebElement elem : anchors) {
			WebElement img = elem.findElement(By.tagName("img"));
			img.click();
			try {
				Thread.sleep(9000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (isNotAvailableDocumentModaleVisible()) {
				
				WebElement modal_footer = driver.findElement(By.className("modal-footer"));
				(modal_footer.findElement(By.tagName("button"))).click();
			} 
		}
	}

	public boolean isThereNoDocuments() {
		
		WebElement tabPaneActive = getNavigationTabBar().getActiveTabPane();
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			logger.error("Timeout exception during document download", e);
			e.printStackTrace();
		}
		WebElement documentsContainer = tabPaneActive.findElement(By.id(getPortletName() + "docsContainer"));
		List<WebElement> docSectionTitles = documentsContainer.findElements(By.tagName("h2"));
		if (docSectionTitles.size() == 0)
			return true;
		return false;
		
			
	}

	public boolean isNotAvailableDocumentModaleVisible() {

		WebElementUtil.waitForPresenceOf(driver, By.className("modal-wrap-container"));
		return driver.findElement(By.className("modal-wrap-container")).isDisplayed();

	}

}
