package formPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import mainPackage.SystemProperties;

public class FormPageTest {
  private static WebDriver webDriver;
  private static FormPage formPage;

  @BeforeAll
  public static void setUp() throws Exception {
    SystemProperties.setSystemPropertyForChrome();
    webDriver = new ChromeDriver();
    webDriver.manage().window().maximize();
    formPage = PageFactory.initElements(webDriver, FormPage.class);
  }

  @Test
  public void shouldCorrectFillInExampleFormTest() throws Exception {
    formPage.fillIn("Adam", "Damczyk", 'M', "2018-01-01", "Automation Tester");
    assertEquals(formPage.assertUrl(), true);
  }

  @AfterAll
  public static void tearDown() throws Exception {
    webDriver.quit();
  }
}