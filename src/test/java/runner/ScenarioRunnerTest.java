package runner;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
/*
 * NON COMMITTARE MAI
 * */

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features", glue = { "gluecode" }, plugin = { "usage",
		"json:src/test/cucumber-reports/report.json" }, monochrome = true, tags = {"@PCC_TPA.04"})
public class ScenarioRunnerTest {

	@BeforeClass
	public static void setup() {
		
		//set the system property "Running test on Chrome Browser" to the path of your ChromeDriver.exe file
		System.setProperty("chromeDriverPath","C:/seleniumDriver/windows/79/chromedriver.exe");
		System.setProperty("browser", "chrome");
		//code to navigate to the desired URL
		System.setProperty("environment", "int");		
	}
	
	 /*
	  * rename report file with timestamp
	  * 
	  * */
	
}
//, tags = { "@Login and not @ignore" }
//tags = { "@Login or @ValidationRegistration and not @not" }