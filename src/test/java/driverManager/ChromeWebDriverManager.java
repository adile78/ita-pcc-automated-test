package driverManager;

import java.io.File;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import enums.WebDriverType;

public class ChromeWebDriverManager extends WebDriverManager {

	private ChromeDriverService chService;

	/* chromedriver version 2.46 */

	@Override
	public void startService() {
		if (null == chService) {
			try {
				driverPath = System.getProperty("chromeDriverPath");
				chService = new ChromeDriverService.Builder().usingDriverExecutable(new File(driverPath))
						.usingAnyFreePort().build();
				chService.start();
				chService.getUrl();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void stopService() {
		if (null != chService && chService.isRunning())
			chService.stop();
	}
	
	@Override
	public void createDriver() {
		ChromeOptions chromeOptions = new ChromeOptions();
		String options = System.getProperty("chromeOptions");

		if (options != null) {
			String[] optionsList = options.split(",");
			for (String option : optionsList) {
				chromeOptions.addArguments(option);
			}
		}
		chromeOptions.addArguments("--incognito");
		driver = new ChromeDriver(chService, chromeOptions);
	}

	@Override
	public String getWebDriverType() {

		return WebDriverType.CHROME.getType();
	}

}
