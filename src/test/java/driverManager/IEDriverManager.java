package driverManager;

import java.io.File;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import enums.WebDriverType;
import utilities.PropsUtil;

public class IEDriverManager extends WebDriverManager {

	private InternetExplorerDriverService ieService;

	@Override
	protected void startService() {
		if (null == ieService) {
			driverPath = System.getProperty("user.dir") + PropsUtil.getPropertyValue("ie.driver.path");
			try {
				ieService = new InternetExplorerDriverService.Builder()
						.usingDriverExecutable(new File(driverPath)).usingAnyFreePort()
						.build();
				ieService.start();
				ieService.getUrl();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void stopService() {
		if (null != ieService && ieService.isRunning())
			ieService.stop();
	}
	
	@Override
	public void createDriver() {
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		InternetExplorerOptions options = new InternetExplorerOptions();
		options.merge(capabilities);
		driver = new InternetExplorerDriver(ieService, options);
	}
	
	@Override
	public String getWebDriverType(){
		
		return WebDriverType.IE.getType();
				
	}
}