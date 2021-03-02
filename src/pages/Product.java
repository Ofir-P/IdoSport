package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Product extends Base {

	public Product(WebDriver driver) {
		super(driver);
	}

	
	public void selectDate() {
		click(By.id("shipping_delivery_date"));
		click(By.linkText("17"));
		String defaultDate = getText(By.id("shipping_delivery_date"));
		System.out.println(defaultDate);
		
	}
}
