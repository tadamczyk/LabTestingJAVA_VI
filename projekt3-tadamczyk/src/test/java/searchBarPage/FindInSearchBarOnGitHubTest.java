package searchBarPage;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import mainPackage.SystemProperties;

public class FindInSearchBarOnGitHubTest {
  private static WebDriver webDriver;
  private static FindInSearchBarOnGitHub gitHubPage;

  @BeforeAll
  public static void setUp() throws Exception {
    SystemProperties.setSystemPropertyForChrome();
    webDriver = new ChromeDriver();
    webDriver.manage().window().maximize();
    gitHubPage = PageFactory.initElements(webDriver, FindInSearchBarOnGitHub.class);
  }

  @Test
  public void shouldCorrectSearchInSearchBarOnGitHubTest() throws Exception {
    gitHubPage.search("tborzyszkowski/Xamarin");
    assertTrue(gitHubPage.assertTitle());
  }

  @AfterAll
  public static void tearDown() throws Exception {
    webDriver.quit();
  }
}