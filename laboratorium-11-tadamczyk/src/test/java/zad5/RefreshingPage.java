package zad5;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class RefreshingPage {
	public WebDriver driver;
	public Actions actions;

	public RefreshingPage(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
		driver.get("https://inf.ug.edu.pl/sq/src/login.php");
	}

	public void refresh() throws Exception {
		driver.navigate().refresh();
		Thread.sleep(1000);

		driver.navigate().to(driver.getCurrentUrl());
		Thread.sleep(1000);

		driver.get(driver.getCurrentUrl());
		Thread.sleep(1000);

		driver.findElement(By.xpath("//body")).sendKeys(Keys.F5);
		Thread.sleep(1000);

		driver.findElement(By.xpath("//body")).sendKeys("\uE035");
		Thread.sleep(1000);

		actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
		Thread.sleep(1000);
	}
}