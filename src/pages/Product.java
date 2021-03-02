package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Product extends Base {
	Actions action;
	public Product(WebDriver driver) {
		super(driver);
	}

	
	public void chooseWeight() {
		click(By.id("%d7%91%d7%97%d7%a8-%d7%99-%d7%9e%d7%a9%d7%a7%d7%9c"));
		action = new Actions(driver);
		action.sendKeys(Keys.ARROW_DOWN).build().perform();
		action.sendKeys(Keys.ENTER).build().perform();
				
	}
	
	public void addToCart() {
		click(By.id("%d7%91%d7%97%d7%a8-%d7%99-%d7%9e%d7%a9%d7%a7%d7%9c"));
		action = new Actions(driver);
		action.sendKeys(Keys.ARROW_DOWN).build().perform();
		action.sendKeys(Keys.ENTER).build().perform();
				
	}
}
