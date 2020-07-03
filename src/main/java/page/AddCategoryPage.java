package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import util.BasePage;

//Test 1: Validate a user is able to add a category and once the category is added it should display.

//Test 2: Validate a user is not able to add a duplicated category. If it does then 
//the following message will display: "The category you want to add already exists: <duplicated category name>."

//Test 3: Validate the month drop down has all the months (jan, feb, mar ...) in the Due Date dropdown section.

public class AddCategoryPage extends BasePage {

	WebDriver driver;
	boolean status;
	boolean check;
	int rand = BasePage.randomNumber();
	String newValue = "MyTest" + rand;
	

	public AddCategoryPage(WebDriver driver) {
		this.driver = driver;
	}

	// locate element
	// category data input box
	@FindBy(how = How.NAME, using = "categorydata")
	WebElement CategoryDataInputBox_LOCATOR;

	// add category button
	@FindBy(how = How.XPATH, using = "//input[@value = 'Add category']")
	WebElement AddCategory_LOCATOR;

	// category dropdown menu
	@FindBy(how = How.NAME, using = "category")
	WebElement CategoryDropDown_LOCATOR;

	// adding duplicate category alert message element
	@FindBy(how = How.XPATH, using = "//body//child::span//preceding::text()[1]")
	WebElement VerifyMsg_LOCATOR;

	// month dropdown element
	@FindBy(how = How.NAME, using = "due_month")
	WebElement MonthDropDown_LOCATOR;

	// methods
	public void AddCategory() throws InterruptedException {

		// added new dropdown item
		CategoryDataInputBox_LOCATOR.sendKeys(newValue);
		AddCategory_LOCATOR.click();

		// created dropdown object using Select class
		Select drpCategory = new Select(CategoryDropDown_LOCATOR);
		Thread.sleep(10);

		// created List using dropdown box's element
		List<WebElement> dropdownItems = drpCategory.getOptions();

		// checking which item in list has new added item using for each loop
		for (WebElement dropdowncategory : dropdownItems) {

			if (dropdowncategory.getText().equals(newValue)) {
				Assert.assertTrue(true);
				status = true;
				System.out.println("New added category is " + dropdowncategory.getText());
			}

		}

		// printing status from checking drop down element
		if (status == true) {

		} else {
			System.out.println("New added category is NOT found");

		}

		// now selecting new item from dropdown list
		drpCategory.selectByVisibleText(newValue);

		WebElement option = drpCategory.getFirstSelectedOption();
		String defaultItem = option.getText();
		System.out.println("Value selected in dropdown now is " + defaultItem);

		// checking if new item is displayed on top of the page on right side
				
		String newCat = driver.findElement(By.xpath("//span[contains (text(), '" + newValue + "')]")).getText();
		//"//a[contains(text(), '" + name_final + "')]"
		if (newCat.equalsIgnoreCase(newValue)) {
			System.out.println("New item is Displayed to be Removed");
		} else {
			System.out.println("New item Missing!!!");
		}
	}

	public void UnableToAddDouble() throws InterruptedException {

		String expectedString = "The category you want to add already exists: " + newValue;
		System.out.println(expectedString);
		// added new dropdown item
		CategoryDataInputBox_LOCATOR.sendKeys(newValue);
		AddCategory_LOCATOR.click();

		WebElement element = driver.findElement(By.cssSelector("body"));
		//boolean feedBack = driver.findElement(By.cssSelector("body")).getText().contains(expectedString);
		boolean feedbackVisible = element.isDisplayed();
		System.out.println(feedbackVisible);
		System.out.println(driver.findElement(By.cssSelector("body")).getText().substring(0, 55));
		Thread.sleep(5000);
		//driver.findElement(By.xpath("//a[contains (text(), 'Yes')]")).click();
		WebElement YesElement = driver.findElement(By.xpath("//a[contains (text(), 'Yes')]"));
		YesElement.click();
	
	}

	// The category you want to add already exists:

	public void validateMonthDropDown() throws InterruptedException {
		String[] Expectedarr = { "None", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		
		Thread.sleep(5000);
		Select select = new Select(MonthDropDown_LOCATOR);
		List<WebElement> options = select.getOptions();

		//check = false;
		for (WebElement we : options) {
			for (int i = 0; i < Expectedarr.length; i++) {
				if (we.getText().equals(Expectedarr[i])) {
					System.out.println(we.getText());
					//check = true;
					//i = Expectedarr.length;

				}

			}
		} // end for loop
	}// end for-each loop

}
