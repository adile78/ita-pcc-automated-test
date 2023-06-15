package driverManager;

import java.io.File;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import enums.WebDriverType;
import utilities.PropsUtil;

public class FirefoxWebDriverManager extends WebDriverManager {

	private GeckoDriverService gkService;

	@Override
	public void startService() {
		if (null == gkService) {
			driverPath = System.getProperty("user.dir") + PropsUtil.getPropertyValue("firefox.driver.path"); //System.getProperty("user.dir") + 
			try {
				gkService = new GeckoDriverService.Builder()
						.usingDriverExecutable(new File(driverPath)).usingAnyFreePort().build();
				gkService.start();
				gkService.getUrl();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void stopService() {
		if (null != gkService && gkService.isRunning())
			gkService.stop();
	}
	
	@Override
	public void createDriver() {
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("test-type");
		capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
		options.merge(capabilities);
		driver = new FirefoxDriver(gkService, options);
	}
	
	@Override
	public String getWebDriverType(){
		
		return WebDriverType.FIREFOX.getType();
				
	}
}
