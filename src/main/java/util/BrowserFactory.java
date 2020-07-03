package util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {
	static WebDriver driver; // making it static bcoz we will call it by claa name

	// creating a non-void method to call chrome browser and go to destination site
	// and return a WebDriver
	public static WebDriver launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe"); // locate chrome .exe files
		driver = new ChromeDriver(); // driver is a chrome driver
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// wait for 10 sec before timeout
		driver.get("http://techfios.com/test/101/");
		return driver;

	}
	
	public static void tearDown() {
		driver.close();
		driver.quit();
}

}