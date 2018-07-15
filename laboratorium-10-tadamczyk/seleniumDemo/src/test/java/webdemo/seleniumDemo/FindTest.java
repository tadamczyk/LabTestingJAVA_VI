package webdemo.seleniumDemo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FindTest {
	private static WebDriver driver;

	@BeforeEach
	void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.google.pl");
	}

	@AfterEach
	void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void findResultByIdForMateuszMiotkInfo() {
		WebElement element = driver.findElement(By.id("lst-ib"));
		element.sendKeys("Mateusz Miotk UG");
		element.submit();
		driver.findElement(By.xpath("(//h3)[1]/a")).click();
		assertNotNull(element);
	}

	@Test
	public void findResultByIdForDumpExample() {
		WebElement element = driver.findElement(By.id("lst-ib"));
		element.sendKeys("asdasdajkfajksfbjafgjhafghjagfjhagfjhavfjhahfj");
		element.submit();
		WebElement body = driver.findElement(By.tagName("body"));
		String bodyText = body.getText();
		boolean isPresent = bodyText.contains("nie");
		assertTrue(isPresent);
	}

	@Test
	public void findById() {
		WebElement element = driver.findElement(By.id("lst-ib"));
		assertNotNull(element);
	}

	@Test
	public void findByName() {
		WebElement element = driver.findElement(By.name("q"));
		assertNotNull(element);
	}

	@Test
	public void findByClass() {
		WebElement element = driver.findElement(By.className("gsfi"));
		assertNotNull(element);
	}

	@Test
	public void findBylinkText() {
		WebElement element = driver.findElement(By.linkText("Gmail"));
		assertNotNull(element);
	}

	@Test
	public void findByTagName() {
		WebElement element = driver.findElement(By.tagName("div"));
		assertNotNull(element);
	}

	@Test
	public void findByCssSelector() {
		WebElement element = driver.findElement(By.cssSelector("input.gsfi"));
		assertNotNull(element);
	}
}