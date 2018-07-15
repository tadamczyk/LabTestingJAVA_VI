package zad5;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RefreshingPageTest {
	private static WebDriver driver;
	public RefreshingPage refreshingPage;

	@BeforeClass
	public static void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
		driver = new FirefoxDriver();
	}

	@Test
	public void refreshingTest() throws Exception {
		refreshingPage = new RefreshingPage(driver);
		refreshingPage.refresh();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}
}