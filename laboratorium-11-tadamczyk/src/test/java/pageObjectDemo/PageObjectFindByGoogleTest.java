package pageObjectDemo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObjectFindByGoogleTest {
	private static WebDriver driver;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
		driver = new FirefoxDriver();
	}

	@Test
	public void test() throws Exception {
		PageFactoryGoogle googlePage = PageFactory.initElements(driver, PageFactoryGoogle.class);
		googlePage.search("Mateusz Miotk");
		assertTrue(googlePage.assertTitle());
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}
}