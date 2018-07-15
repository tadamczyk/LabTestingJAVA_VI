package webdemo.seleniumDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleTest {
	private static WebDriver driver;

	@BeforeEach
	void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void testTitlePage() {
		driver.get("https://duckduckgo.com/");
		assertEquals("DuckDuckGo — Privacy, simplified.", driver.getTitle());
	}

	@Test
	public void testClick() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get("https://duckduckgo.com/");
		driver.findElement(By.id("search_form_input_homepage")).sendKeys("Mateusz Miotk");
		driver.findElement(By.id("search_button_homepage")).click();
		wait.until(ExpectedConditions.titleIs("Mateusz Miotk at DuckDuckGo"));
		assertEquals("Mateusz Miotk at DuckDuckGo", driver.getTitle());
	}

	@AfterEach
	void tearDown() throws Exception {
		driver.quit();
	}
}