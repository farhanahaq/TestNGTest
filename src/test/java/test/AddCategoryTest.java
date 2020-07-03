package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

import page.AddCategoryPage;
import util.BrowserFactory;

public class AddCategoryTest {
	
	WebDriver driver;
	
	
	@BeforeTest
	public void init() {
		driver = BrowserFactory.launchBrowser();
	}
	
	
	@Test(priority = 1)
	public void UnableToAddDoubleItem() throws InterruptedException {
		AddCategoryPage addcatpage = PageFactory.initElements(driver, AddCategoryPage.class);
		addcatpage.AddCategory();
		addcatpage.UnableToAddDouble();
					
	}
	
	
	@Test(priority = 2)
	public void validateMonthDropDownBox() throws InterruptedException {
		AddCategoryPage addcatpage = PageFactory.initElements(driver, AddCategoryPage.class);
		addcatpage.validateMonthDropDown();
		
				
	}
	
	@AfterTest
	public void closing() {
		BrowserFactory.tearDown();
	
}


}
