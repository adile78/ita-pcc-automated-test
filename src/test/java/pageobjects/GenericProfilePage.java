package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import enums.ProfileFieldType;

public class GenericProfilePage implements ProfilePage {

	protected WebDriver driver;

	protected static Logger logger = LogManager.getLogger();

	protected By profiloClass;

	public GenericProfilePage(WebDriver driver) {
		this.driver = driver;
	}

	public List<String> getRequiredAccessData() {

		List<String> requiredActionList = new ArrayList<String>();
		requiredActionList.add(ProfileFieldType.EMAIL.getValue());
		requiredActionList.add(ProfileFieldType.PASSWORD.getValue());
		return requiredActionList;
	}

	public List<String> getRequiredContactsData() {

		List<String> requiredActionList = new ArrayList<String>();
		requiredActionList.add(ProfileFieldType.EMAIL.getValue());
		return requiredActionList;
	}
}
