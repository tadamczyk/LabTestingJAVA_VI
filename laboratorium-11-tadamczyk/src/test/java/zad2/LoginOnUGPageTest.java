package zad2;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginOnUGPageTest {
	private static WebDriver driver;
	public LoginOnUGPage loginPage;

	@BeforeClass
	public static void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
		driver = new FirefoxDriver();
	}

	@Test
	public void loginAsTAdamczyk() throws Exception {
		loginPage = new LoginOnUGPage(driver);
		loginPage.login();
		assertTrue(loginPage.assertTitle());
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}
}