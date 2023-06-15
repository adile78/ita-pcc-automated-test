package pccpageobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enums.PrivacyType;
import pageobjects.GenericProfilePage;
import pageobjects.Modale;
import pccelementobjects.PccModale;
import utilities.PropsUtil;

public class PccProfilePage extends GenericProfilePage {

	private static String portletName = "_1_WAR_ITAAOIPCCaxamodifycustomerdataportlet_";
	// FIXME: remove dependency from instance
	private static String portletNameContactsData = "_com_axa_portal_ita_customerdata_ModifyUserDataPortlet_INSTANCE_mCjqai5Lg4A6_";
	private static String portletNameAgreements = "_com_axa_portal_ita_customerdata_ModifyUserDataPortlet_INSTANCE_vppNs8KD750R_";

	private By progressBarBy;

	// access
	private By accessDataBoxBy;
	private By accessDetailsBy;
	private By accessDetailTitleBy;
	private By accessDataDetailsBy;

	private By accessDataEditingBoxBy;
	private By oldPswdId;
	private By newPswdId;
	private By newPswdRetId;

	private By confirmBtnId;
	private By cancelBtnBy;
	private By viewBtnBy;
	private By editingBtnBy;
	private By saveBtnBy;

	// contacts
	private By contactsAgreementsContainerBy;
	private By contactsDetailsBy;
	private By contactsDataEditingBtnBy;
	private By contactsDataEditingBoxBy;
	private By contactDataTelephoneBy;
	private By contactDataChkbxBy;
	private By contactDataMailBy;
	private By contactDataMailConfimBy;

	// agreements
	private By agreementsBox;
	private By agreementsListBy;
	private By privacyCheckElementBy;

	// links
	private By linkListBy;

	// modale
	private Modale setPswdOkModale;
	private Modale setContactDataOKModale;
	private Modale setContactDataKOModale;
	private Modale contactDataModale;

	// consensi_edit__item

	public PccProfilePage(WebDriver driver) {
		super(driver);

		profiloClass = By.className("profilo-new");
		progressBarBy = By.className("progress-bar-container");
		accessDataBoxBy = By.tagName("section");
		accessDetailTitleBy = By.tagName("h2");
		accessDataDetailsBy = By.className("control-group");
		editingBtnBy = By.className("btn-edit");

		// access data
		accessDataEditingBoxBy = By.id("accesso_edit");
		oldPswdId = By.id("passwordAttuale");
		newPswdId = By.id("passwordNew");
		newPswdRetId = By.id("passwordNewRet");

		confirmBtnId = By.id("confirm");
		cancelBtnBy = By.className("btn-cancel");
		viewBtnBy = By.className("btn-view");
		saveBtnBy = By.className("btn-save");

		// contacts data
		contactsAgreementsContainerBy = By.className("input-container");
		contactsDetailsBy = By.className("header-details");
		contactsDataEditingBtnBy = By.id("edit-contact-toggle");
		contactsDataEditingBoxBy = By.className("contatto_edit");
		contactDataTelephoneBy = By.id(portletNameContactsData + "contactDataTelephone");
		contactDataChkbxBy = By.id(portletNameContactsData + "contactDataRemoteCommunication");
		contactDataMailBy = By.id(portletNameContactsData + "contactDataRemoteCommunicationMail");
		contactDataMailConfimBy = By.id(portletNameContactsData + "contactDataRemoteCommunicationMailRe");

		// agreements
		agreementsBox = By.id("consensi_edit");
		agreementsListBy = By.className("consensi_edit__item");
		// FIXME: insert value for each privacy
		// agreementsListBy = By.id(portletNameAgreements +
		// "consensoComunicazioniPromoz");
		// agreementsListBy = By.id(portletNameAgreements +
		// "consensoRicercheMercatoRilevStat");
		privacyCheckElementBy = By.className("radio");

		// links
		linkListBy = By.className("links-list-container");

		// modale
		String modaleId_setPswdOk = portletNameContactsData + "setPasswordOK";
		setPswdOkModale = new PccModale(driver, modaleId_setPswdOk, false);

		String modaleId_setContactDataKO = portletNameContactsData + "putDataContactKO";
		setContactDataKOModale = new PccModale(driver, modaleId_setContactDataKO, false);
		setPswdOkModale = new PccModale(driver, modaleId_setPswdOk, false);

		String modaleId_setContactDataOK = portletNameContactsData + "putDataContactOK";
		setContactDataOKModale = new PccModale(driver, modaleId_setContactDataOK, false);

		String modaleId_contactData = portletNameContactsData + "contactDataModal";
		contactDataModale = new PccModale(driver, modaleId_contactData, true);
	}

	public WebElement getProfileBox() {

		return driver.findElement(profiloClass);
	}

	// progress bar
	public boolean checkProgressBar() {

		if (getProfileBox().findElement(progressBarBy) != null)
			return true;
		return false;
	}

	// access data
	public WebElement getAccessDataBox() {

		return getProfileBox().findElement(accessDataBoxBy);
	}

	public String getTitleAccessDataBox() {

		return getAccessDataBox().findElement(accessDetailTitleBy).getText();
	}

	public WebElement getAccessDetails() {

		return getAccessDataBox().findElement(accessDetailsBy);
	}

	public Map<String, String> getAccessDataDetails() {

		Map<String, String> accessDataDetails = new HashMap<String, String>();
		List<WebElement> detailList = getAccessDetails().findElements(accessDataDetailsBy);
		for (WebElement detail : detailList) {
			WebElement label = detail.findElement(By.className(PropsUtil.getPropertyValue("control.label")));
			WebElement value = detail.findElement(By.className(PropsUtil.getPropertyValue("field")));
			accessDataDetails.put(label.getText(), value.getText());
		}
		return accessDataDetails;
	}

	public boolean checkAccesDataDetails() {

		Map<String, String> accessDataDetails = getAccessDataDetails();
		List<String> accessDataList = getRequiredAccessData();
		for (String accessData : accessDataList) {
			if (!accessDataDetails.containsKey(accessData) || accessDataDetails.get(accessData) == null) {
				logger.error("Access data " + accessData + " not found");
				return false;
			}
		}
		return true;
	}

	// form access data editing

	private void clickAccessDataEditing() {

		WebElement accessDataEditingBtn = getAccessDetails().findElement(editingBtnBy);
		accessDataEditingBtn.click();
	}

	public WebElement getFormAccessDataEditing() {

		return getAccessDetails().findElement(accessDataEditingBoxBy);

	}

	private void setOldPassowrd(String oldPassword) {

		WebElement oldPswd = getFormAccessDataEditing().findElement(oldPswdId);
		oldPswd.sendKeys(oldPassword);
	}

	private void setNewPassowrd(String newPassword) {

		WebElement newPswd = getFormAccessDataEditing().findElement(newPswdId);
		newPswd.sendKeys(newPassword);
	}

	private void setNewPassowrdRet(String newPasswordRet) {

		WebElement newPswdRet = getFormAccessDataEditing().findElement(newPswdRetId);
		newPswdRet.sendKeys(newPasswordRet);
	}

	private void clickAccessDataEditConfirm() {

		WebElement accessDataEditingBtn = getFormAccessDataEditing().findElement(confirmBtnId);
		accessDataEditingBtn.click();
	}

	private void clickCancelAccessDataEdit() {

		WebElement accessDataEditCancelBtn = getFormAccessDataEditing().findElement(cancelBtnBy);
		accessDataEditCancelBtn.click();
	}

	public void setNewPasswordProcess(String oldPassword, String newPassword, String newPasswordRet) {

		clickAccessDataEditing();
		setOldPassowrd(oldPassword);
		setNewPassowrd(newPassword);
		setNewPassowrdRet(newPasswordRet);
		clickAccessDataEditConfirm();
	}

	public void cancelSetNewPasswordProcess(String oldPassword, String newPassword, String newPasswordRet) {

		clickAccessDataEditing();
		setOldPassowrd(oldPassword);
		setNewPassowrd(newPassword);
		setNewPassowrdRet(newPasswordRet);
		clickCancelAccessDataEdit();
	}

	public boolean isSetPswdOkModaleVisible() {

		return setPswdOkModale.isModaleVisible();
	}

	// contact data

	private WebElement getContactsAgreementsContainer() {

		return getProfileBox().findElement(contactsAgreementsContainerBy);
	}

	private WebElement getContactsSection() {

		return getContactsAgreementsContainer().findElements(By.tagName("section")).get(0);
	}

	private WebElement getContactsDetails() {

		return getContactsSection().findElement(contactsDetailsBy);
	}

	public Map<String, String> getContactsDataDetails() {

		Map<String, String> contactsDataDetails = new HashMap<String, String>();
		List<WebElement> detailList = getContactsDetails().findElements(accessDataDetailsBy);
		for (WebElement detail : detailList) {
			WebElement label = detail.findElement(By.className(PropsUtil.getPropertyValue("control.label")));
			WebElement value = detail.findElement(By.className(PropsUtil.getPropertyValue("field")));
			contactsDataDetails.put(label.getText(), value.getText());
		}

		return contactsDataDetails;
	}

	public boolean checkContactsDetails() {

		Map<String, String> contactsDataDetails = getContactsDataDetails();
		List<String> contactsDataList = getRequiredContactsData();
		for (String contactsData : contactsDataList) {
			if (!contactsDataDetails.containsKey(contactsData) || contactsDataDetails.get(contactsData) == null) {
				logger.error("Contacts data " + contactsData + " not found");
				return false;
			}
		}
		return true;
	}

	// form access data editing

	private void clickContactsDataEditing() {

		WebElement contactsDataEditingBtn = getContactsSection().findElement(contactsDataEditingBtnBy);
		contactsDataEditingBtn.click();
	}

	public WebElement getContactDataEditingBox() {

		WebElement contactDataEditingBox = getContactsSection().findElement(contactsDataEditingBoxBy);
		return contactDataEditingBox;
	}

	private void setNewNumber(String newNumber) {

		WebElement contactDataTelephone = getContactDataEditingBox().findElement(contactDataTelephoneBy);
		contactDataTelephone.sendKeys(newNumber);
	}

	private void setRemoteCommunication(String mail, String mailConfirmation) {
		clickRemoteCommunication();
		setMail(mail);
		setMailConfirmation(mailConfirmation);
	}

	private void clickRemoteCommunication() {

		WebElement contactDataChbx = getContactDataEditingBox().findElement(contactDataChkbxBy);
		contactDataChbx.click();
	}

	private void setMail(String mail) {

		WebElement contactDataEmail = getContactDataEditingBox().findElement(contactDataMailBy);
		contactDataEmail.sendKeys(mail);
	}

	private void setMailConfirmation(String mailConfirmation) {

		WebElement contactDataEmailConfirm = getContactDataEditingBox().findElement(contactDataMailConfimBy);
		contactDataEmailConfirm.sendKeys(mailConfirmation);
	}

	private void clickContactDataEditConfirm() {

		WebElement contactDataEditingBtn = getContactDataEditingBox().findElement(confirmBtnId);
		contactDataEditingBtn.click();
	}

	private void clickContactDataEditCancel() {

		WebElement contactDataEditCancelBtn = getContactDataEditingBox().findElement(cancelBtnBy);
		contactDataEditCancelBtn.click();
	}

	public void setNewContactDataProcess(String newNumber, String mail, String mailConfirmation) {

		clickContactsDataEditing();
		setNewNumber(newNumber);
		setRemoteCommunication(mail, mailConfirmation);

		clickContactDataEditConfirm();
	}

	public void cancelSetNewContactDataProcess(String newNumber, String mail, String mailConfirmation) {

		clickContactsDataEditing();
		setNewNumber(newNumber);
		setRemoteCommunication(mail, mailConfirmation);

		clickContactDataEditCancel();
	}

	public boolean isSetContactDataOKModaleVisible() {

		return setContactDataOKModale.isModaleVisible();
	}

	public boolean isSetContactDataKOModaleVisible() {

		return setContactDataKOModale.isModaleVisible();
	}

	public boolean isContactsSectionVisibile() {

		if (getContactsSection() != null)
			return true;
		return false;
	}

	// agreements

	private WebElement getAgreementsSection() {

		return getContactsAgreementsContainer().findElements(By.tagName("section")).get(1);
	}

	private void clickViewAgreements() {

		WebElement viewBtn = getAgreementsSection().findElement(viewBtnBy);
		viewBtn.click();
	}

	public boolean checkAgreements() {

		clickViewAgreements();
		WebElement agreementsListBox = getAgreementsSection().findElement(agreementsBox);
		List<WebElement> agreementsList = agreementsListBox.findElements(agreementsListBy);
		if (agreementsList.size() != 4)
			return false;
		return true;
	}

	public boolean isAgreementsSectionVisibile() {

		if (getAgreementsSection() != null)
			return true;
		return false;
	}

	public void setAgreements(Map<PrivacyType, Integer> agreements) {

		WebElement agreementsListBox = getAgreementsSection().findElement(agreementsBox);
		List<WebElement> agreementsList = agreementsListBox.findElements(privacyCheckElementBy);
		if (agreementsList.size() == 8) {

			Integer venditaRicercheMercato = agreements.get(PrivacyType.VENDITA_RICERCHE_MERCATO);
			Integer firmaGrafometrica = agreements.get(PrivacyType.FIRMA_GRAFOMETRICA);
			Integer promozioni = agreements.get(PrivacyType.PROMOZIONI);
			Integer ricercheMercato = agreements.get(PrivacyType.RICERCHE_MERCATO);

			agreementsList.get(PrivacyType.VENDITA_RICERCHE_MERCATO.getIndex() - venditaRicercheMercato);
			agreementsList.get(PrivacyType.FIRMA_GRAFOMETRICA.getIndex() - venditaRicercheMercato);
			agreementsList.get(PrivacyType.PROMOZIONI.getIndex() - venditaRicercheMercato);
			agreementsList.get(PrivacyType.RICERCHE_MERCATO.getIndex() - venditaRicercheMercato);

		}
	}

	private void clickSaveAgreements() {
		WebElement saveBtn = getAgreementsSection().findElement(saveBtnBy);
		saveBtn.click();
	}

	private void clickConfirmChange() {

		contactDataModale.clickDxBtn();
	}

	public void modifyAgreements(Map<PrivacyType, Integer> agreements) {

		setAgreements(agreements);
		clickSaveAgreements();
		clickConfirmChange();
	}

	public boolean isSaveBtnVisibile() {

		List<WebElement> saveBtn = getAgreementsSection().findElements(saveBtnBy);
		if (saveBtn.size() == 0)
			return false;
		return true;
	}
	// link list

	public List<String> getInformativeLinkList() {

		List<String> links = new ArrayList<String>();
		WebElement linkListBox = getProfileBox().findElement(linkListBy);
		List<WebElement> linkList = linkListBox.findElements(By.tagName("a"));
		for (WebElement link : linkList) {
			links.add(link.getText());
		}
		return links;
	}

}
