package zad4;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FacebookFormPageTest {
	private static WebDriver driver;
	public FacebookFormPage formPage;

	@BeforeClass
	public static void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
		driver = new FirefoxDriver();
	}

	@Test
	public void writeElementsSizes() throws Exception {
		formPage = new FacebookFormPage(driver);
		try {
			List<WebElement> selectElements = driver.findElements(By.className("_5dba"));
			List<WebElement> inputElements = driver.findElements(By.className("inputtext"));
			System.out.println("Liczba pól typu select: " + selectElements.size());
			System.out.println("Liczba pól typu input text: " + inputElements.size());
		} finally {
		}
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}
}