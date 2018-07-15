package zad2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginOnUGPage {
	public WebDriver driver;
	private final Wait<WebDriver> wait;
	private final String title = "(inf|mat|sigma).ug.edu.pl";

	public LoginOnUGPage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://inf.ug.edu.pl/sq/src/login.php");
		wait = new WebDriverWait(driver, 10);
	}

	public void login() throws Exception {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("login_username")));
		driver.findElement(By.name("login_username")).sendKeys("tadamczyk");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("secretkey")));
		driver.findElement(By.name("secretkey")).sendKeys("********");
		driver.findElement(By.name("secretkey")).submit();
		wait.until(ExpectedConditions.titleIs(title));
	}

	public boolean assertTitle() throws Exception {
		Boolean result = driver.getTitle().contains(title);
		System.out.println(driver.getTitle());
		return (result);
	}
}