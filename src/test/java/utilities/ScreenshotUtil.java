package utilities;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;

public class ScreenshotUtil {

	private static Logger logger = LogManager.getLogger();
	
	/**
	 * It will take a screenshot of the browser and embed it in the scenario
	 * 
	 * @param scenario of which to take the screenshot
	 * @param driver
	 * @throws IOException
	 */
	public static void takeScreenshot(Scenario scenario, WebDriver driver) throws IOException {
		
		try {
			
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			
			byte[] src = screenshot.getScreenshotAs(OutputType.BYTES);				
			
			scenario.embed(src, "image/png");
			logger.debug("Successfully captured a screenshot");			

		} catch (Exception e) {
			
			logger.debug("Exception while taking screenshot " + e.getMessage());
			
		}
	}

}
