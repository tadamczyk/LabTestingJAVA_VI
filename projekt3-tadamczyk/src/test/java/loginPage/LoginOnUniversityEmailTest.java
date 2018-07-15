package loginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class LoginOnUniversityEmailTest {
  private static WebDriver webDriver;
  private static LoginOnUniversityEmail loginPage;

  @Test
  @EnabledOnOs(OS.WINDOWS)
  public void shouldCorrectLoginOnUniversityEmailTestOnWindows() throws Exception {
    arrangeTestForWindows();
    actAndAssertForTest();
  }

  @Test
  @EnabledOnOs(OS.LINUX)
  public void shouldCorrectLoginOnUniversityEmailTestOnLinux() throws Exception {
    arrangeTestForLinux();
    actAndAssertForTest();
  }

  private void arrangeTestForWindows() {
    System.setProperty("webdriver.ie.driver", "resources/IEDriverServer.exe");
    webDriver = new InternetExplorerDriver();
  }

  private void arrangeTestForLinux() {
    System.setProperty("webdriver.opera.driver", "resources/operadriver");
    webDriver = new OperaDriver();
  }

  private void actAndAssertForTest() throws Exception {
    loginPage = new LoginOnUniversityEmail(webDriver);
    loginPage.login("tadamczyk", "TestJava1");
    assertTrue(loginPage.assertTitle());
  }

  @AfterAll
  public static void tearDown() throws Exception {
    webDriver.quit();
  }
}