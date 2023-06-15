package gluecode;

import java.io.IOException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cucumber.api.Scenario;
import driverManager.WebDriverContainer;
import utilities.ScreenshotUtil;

public class GenericScenario {
	
	protected static Logger logger = LogManager.getLogger();

	protected WebDriverContainer webDriverContainer;
	protected String testEnvironment;

	public GenericScenario(WebDriverContainer webDriverContainer){
		testEnvironment = System.getProperty("environment");
		this.webDriverContainer = webDriverContainer;
	}
	
	public void afterScenario(Scenario scenario) {

		if (scenario.isFailed()) {
			String failedLines = Arrays.toString(scenario.getLines().toArray());
			
		//logger.debug("Scenario " + scenario.getName() + " has failed");
			System.out.println("Scenario " + scenario.getName() + " has failed at lines " + failedLines);
			
			try {
				ScreenshotUtil.takeScreenshot(scenario, webDriverContainer.getWebDriver());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		webDriverContainer.getWebDriver().quit();
	}
	
}