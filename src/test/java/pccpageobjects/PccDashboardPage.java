package pccpageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageobjects.GenericDashboardPage;
import pageobjects.GenericModale;
import pccelementobjects.PccModale;
import utilities.PropsUtil;
import utilities.WebElementUtil;

/**
 * @author barbetti_be
 *
 */
public class PccDashboardPage extends GenericDashboardPage {

	private static String portletName = "dashboardpccportlet_WAR_ITAAOIPCCaxadashboadportlet";

	private By sectionBarBy;
	private By listaCategoriaPolizza;
	private By dashboardLinkPolicyDetail;
	/**
	 * mailBox: identifica il box di una polizza
	 */
	private By mainBox;
	protected By helloMsg;

	private GenericModale updateContactsModale;
	private List<PccRenewalPolicy> renewalPolicies;
	private List<PccListPolicy> policies;
	private PccRightShoulder rightShoulder;

	public PccDashboardPage(WebDriver driver) {

		super(driver);

		// FIXME inserire gli id negli elementi html sectionBar
		sectionBarBy = By.xpath("//*[@id=\"navigation\"]");
		listaCategoriaPolizza = By.xpath("//*[@id=\"column-1\"]");
		helloMsg = By.className("axa-hello");
		mainBox = By.className("box-main");
		dashboardLinkPolicyDetail = By.className("link_dashboard");

		// modale
		String modaleId = "ModalError";
		setUpdateContactsModale(new PccModale(driver, modaleId, true));

		// right shoulder
		rightShoulder = new PccRightShoulder(driver);
	}

	public WebElement getSectionBar(WebDriver driver) {

		return driver.findElement(sectionBarBy);
	}

	public WebElement getListaCategoriaPolizza(WebDriver driver) {

		return driver.findElement(listaCategoriaPolizza);
	}

	public boolean isDashboardPage() {

		String dashboardHelloMsg = PropsUtil.getPropertyValue("dashboard.hello.msg");
		WebElementUtil.waitForPresenceOf(driver, helloMsg);
		String actualHelloMsg = driver.findElement(helloMsg).getText();
		return StringUtils.equals(dashboardHelloMsg, actualHelloMsg);
	}

	public GenericModale getUpdateContactsModale() {

		return updateContactsModale;
	}

	public void setUpdateContactsModale(GenericModale updateContactsModale) {

		this.updateContactsModale = updateContactsModale;
	}

	public List<PccListPolicy> getPolicies() {

		if (policies == null) {
			policies = new ArrayList<PccListPolicy>();
			List<WebElement> mainBoxList = driver.findElements(mainBox);
			for (WebElement policyBox : mainBoxList) {
				PccListPolicy policy = new PccListPolicy(driver, policyBox);
				policies.add(policy);
			}
		}

		return policies;
	}

	public List<PccRenewalPolicy> getRenewalPolicies() {

		if (renewalPolicies == null) {
			renewalPolicies = new ArrayList<PccRenewalPolicy>();
			List<PccListPolicy> policyList = getPolicies();
			for (PccListPolicy pol : policyList) {
				if (pol.isRenewablePolicy()) {
					PccRenewalPolicy r = new PccRenewalPolicy(driver, pol);
					renewalPolicies.add(r);
				}
			}
		}

		return renewalPolicies;
	}

	public void gotoDocuments() {

		driver.get(PropsUtil.getPropertyValue(System.getProperty("environment"))
				+ PropsUtil.getPropertyValue("url.page.documents"));
	}

	public void gotoAreaSinistri() {

		driver.get(PropsUtil.getPropertyValue(System.getProperty("environment"))
				+ PropsUtil.getPropertyValue("url.page.claims"));
	}

	public void gotoAreaSalute() {

		driver.get(PropsUtil.getPropertyValue(System.getProperty("environment"))
				+ PropsUtil.getPropertyValue("url.page.health"));
	}

	public void gotoContatti() {

		driver.get(PropsUtil.getPropertyValue(System.getProperty("environment"))
				+ PropsUtil.getPropertyValue("url.page.contacts"));
	}

	public void gotoProfilo() {

		driver.get(PropsUtil.getPropertyValue(System.getProperty("environment"))
				+ PropsUtil.getPropertyValue("url.page.profile"));
	}

	public void clickDashboardLinkPolicyDetail() {

		WebElement dashBoardLink = driver.findElement(dashboardLinkPolicyDetail);
		WebElementUtil.clickElement(driver, dashBoardLink);
	}

	public boolean checkRightShoulder() {

		return (rightShoulder.getShoulderContent() != null ? true : false);
	}

	public boolean isDetailPolicyVisible() {

		return driver.findElement(dashboardLinkPolicyDetail) != null ? true : false;
	}

}
