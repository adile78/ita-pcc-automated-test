package pccelementobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.PropsUtil;
import utilities.WebElementUtil;

public class PccNavigationTabBar {

	protected WebDriver driver;

	protected static Logger logger = LogManager.getLogger();
	
	
	/**
	 * the tab navigator container
	 */
	private WebElement navContainer;
	/**
	 * the tab-panes container
	 */
	private WebElement tabContent;

	private By tabPaneBy;
//	private By frameContentBy;

	private List<WebElement> tabPanes;
	private List<WebElement> navigationTabs;

	public PccNavigationTabBar(WebDriver driver) {
		this.driver = driver;
		
		
		WebElementUtil.waitForPresenceOf(driver, By.className("nav-container"));
		
		
		navContainer = driver.findElement(By.className("nav-container"));
		tabContent = driver.findElement(By.className("tab-content"));
		tabPaneBy = By.className("tab-pane");
//		frameContentBy = By.className("frame-content");
	}

	/**
	 * @return the a-tag elements list of tab navigator bar
	 */
	public List<WebElement> getNavigationLinks() {

		return navContainer.findElements(By.tagName("a"));
	}
	
	/**
	 * @return the li-tag elements list of tab navigator bar
	 */
	public List<WebElement> getNavigationTabs() {

		if (navigationTabs == null)
			navigationTabs = navContainer.findElements(By.tagName("li"));
		return navigationTabs;
	}

	/**
	 * @return the active a-tag(li-tag) element of tab navigator bar
	 */
	public WebElement getActiveNavigationTab() {

//	for (WebElement navTab : getNavigationTabs()) {
	for (WebElement navTab : getNavigationLinks()) {
	
			//hasClass: check if a web element has a certain value in his class attribute
			if (WebElementUtil.hasClass(navTab,PropsUtil.getPropertyValue("active")))
				return navTab;
		}
		return null;
	}

	/**
	 * @return all tabs content
	 */
	public List<WebElement> getTabPanes() {

		tabPanes = tabContent.findElements(tabPaneBy);
		return tabPanes;
	}

	/**
	 * @return active tab pane of tab-content
	 */
	public WebElement getActiveTabPane() {

		for (WebElement tabPane : getTabPanes()) {		
			if (WebElementUtil.hasClass(tabPane, PropsUtil.getPropertyValue("active")))
				return tabPane;
		}
		return null;
	}

	// TODO: aggiungere attributo company anche nei tab de "il mio profilo"
	public List<String> getCompanies() {

		List<String> companies = new ArrayList<String>();

		List<WebElement> tabsLink = getNavigationLinks();
		for (WebElement link : tabsLink) {
			String company = link.getAttribute("company");
			companies.add(company);
		}
		return companies;
	}
	
	public String getActiveTabCompany(){
		
		WebElementUtil.waitForPresenceOf(driver, By.tagName("a"));
		WebElement activeTab = getActiveNavigationTab();
//		WebElementUtil.waitForPresenceOf(driver, By.tagName("a"));
//		String company = activeTab.findElement(By.tagName("a")).getAttribute("company");
		String company = activeTab.getAttribute("company");
		return company;
	}
	
	// timeout is needed
	public void selectTabByCompany(String companyType){
		
		List<WebElement> navTabs = getNavigationLinks();
//		WebElementUtil.contains:Find the element in a list that contains a certain value in a certain attribute
		WebElement companyTab = WebElementUtil.contains(navTabs, "company", companyType);	
		try {			
			Thread.sleep(9000);
		} catch (InterruptedException e) {	
			logger.error("Timeout exception during select tab by company", e);
			e.printStackTrace();
		}		
		companyTab.click();
	}

}
