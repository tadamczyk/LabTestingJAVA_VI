package zad4;

import org.openqa.selenium.WebDriver;

public class FacebookFormPage {
	public WebDriver driver;

	public FacebookFormPage(WebDriver driver) {
		this.driver = driver;
		driver.get("https://www.facebook.com/");
	}
}