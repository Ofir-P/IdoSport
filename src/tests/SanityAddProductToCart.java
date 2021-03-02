package tests;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.Main;
import pages.Product;
import utilites.GetDriver;
import utilites.Utilities;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;


public class SanityAddProductToCart {

	// Global variables 
	// Add extent reports
	private ExtentReports extent;
	private ExtentTest myTest;
	private static String reportPaht = System.getProperty("user.dir") + "\\test-output\\idosport.html";

	private WebDriver driver;
	private String baseUrl;
	
	
	//pages
	private Main main;
	private Product product;
	
	

	@BeforeClass
	public void beforeClass() {
		extent = new ExtentReports(reportPaht);
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\resources\\idosport-extent-config.xml"));
		baseUrl = "https://www.idosport.co.il/";
		driver = GetDriver.getDriver("chrome", baseUrl);

		main = new Main(driver);
		product = new Product(driver);
	}

	
	
	@BeforeMethod
	public void beforeMethod(Method method) throws IOException {
		myTest = extent.startTest(method.getName());
		myTest.log(LogStatus.INFO, "Starting test", "Start test");
	}
	
	
	
	/*  Prerequisite: Getting into "https://www.idosport.co.il/"
	/*  	Given: client is in site  main page
	 * 		When: Performing a text-search
	 *  	Then: Show search results
	 *  Ofir, 02/03/2021
	 */
	
	@Test(priority = 1, enabled = true, description = "Search for specific product")
	public void serachProduct() throws InterruptedException, IOException {	
		Assert.assertTrue(main.search("משקולות יד דאמבלס משושה עם אחיזה נוחה לידיים"));
	}
	
	/*  Prerequisite: Getting into "https://www.idosport.co.il/"
	/*  	Given: Client is on site  search page
	 * 		When: Clicking on product
	 *  	Then: Show product page
	 *  Ofir, 02/03/2021
	 */
	
	@Test(priority = 2, enabled = true, description = "Click on specific product")
	public void getIntoProduct() throws InterruptedException, IOException {	
		Assert.assertTrue(main.getIntoProduct("משקולות יד דאמבלס משושה עם אחיזה נוחה לידיים"));
	}
	
	/*  Prerequisite: Getting into product page
	/*  	Given: Client is on a product page
	 * 		When: Clicking on weight selector option and choosing the first weight
	 *  	Then: Show weight selection and being able to add to cart the item
	 *  Ofir, 02/03/2021
	 */
	
	@Test(priority = 3, enabled = true, description = "Click on product weight and add to cart")
	public void editProduct() throws InterruptedException, IOException {	
		product.chooseWeight();
	}
	
	
	/*  Prerequisite: Getting into "https://www.idosport.co.il/"
	/*  	Given: Client is on site  search page
	 * 		When: Clicking on product
	 *  	Then: Show product page
	 *  Ofir, 02/03/2021
	 */
	
	@Test(priority = 4, enabled = true, description = "Click on specific product")
	public void addProductToCart() throws InterruptedException, IOException {	
		
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			myTest.log(LogStatus.FAIL, "Test failed: " + result.getName());
			myTest.log(LogStatus.FAIL, "Test failed reason: " + result.getThrowable());
			myTest.log(LogStatus.FAIL, myTest.addScreenCapture(Utilities.takeScreenShot(driver)));
		}
		else {
			myTest.log(LogStatus.PASS, result.getName(), "Verify successful ");
			myTest.log(LogStatus.PASS, myTest.addScreenCapture(Utilities.takeScreenShot(driver)));

		}

		myTest.log(LogStatus.INFO, "Finish test", "Finish test ");
		extent.endTest(myTest);
	
		//return to base URL 
		//driver.get(baseUrl);
	}

	@AfterClass
	public void afterClass() {
		extent.flush();
		extent.close();
		driver.quit();

	}

	
}
