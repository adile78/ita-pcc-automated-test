package driverManager;

public class WebDriverManagerFactory {

	public static WebDriverManager getManager() {

		WebDriverManager driverManager = null;
		String browser = "chrome";// System.getProperty("browser");//
		
		switch (browser) {
		case "chrome":
			driverManager = new ChromeWebDriverManager();
			break;
		case "firefox":
			driverManager = new FirefoxWebDriverManager();
			break;
		case "ie":
			driverManager = new IEDriverManager();
			break;
		}
		return driverManager;

	}

}
