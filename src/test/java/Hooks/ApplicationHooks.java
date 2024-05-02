package Hooks;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import DriverFactory.DriverFactory;
import Utilities.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;


public class ApplicationHooks {

	private static DriverFactory driverFactory;
	private static WebDriver driver;
	private static ConfigReader configReader;
	static Properties prop;
	
	  public static Logger log = LogManager.getLogger();
	
	@BeforeAll(order=0)
	public static void getProperty()
	{
		configReader = new ConfigReader();
		prop = configReader.init_prop();
	}
	@BeforeAll(order=1)
	public  static void launchBrowser()
	{
		String browserName = prop.getProperty("browser");
		System.out.println("browserName"+ browserName);
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browserName);
	}
	
	
//	@AfterAll(order=1)
//	public  static void quitBrowser() {
//		driver.quit();
//	}
	
	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed()) {
			//take screenshot:
			String screenshotName = scenario.getName().replaceAll(" ","_");
			byte [] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
			
		}
	}
}
