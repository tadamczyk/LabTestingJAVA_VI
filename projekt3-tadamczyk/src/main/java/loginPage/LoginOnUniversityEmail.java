package loginPage;

import static mainPackage.PagesSubtitles.UNIVERSITY_EMAIL_LOGIN_URL_SUBTITLE;
import static mainPackage.PagesUrl.UNIVERSITY_EMAIL_LOGIN_URL;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginOnUniversityEmail {
  private WebDriver webDriver;
  private final Wait<WebDriver> waitWebDriver;

  public LoginOnUniversityEmail(WebDriver webDriver) {
    this.webDriver = webDriver;
    waitWebDriver = new WebDriverWait(webDriver, 10);
    webDriver.get(UNIVERSITY_EMAIL_LOGIN_URL);
  }

  public void login(String login, String password) throws Exception {
    webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    webDriver.findElement(By.name("login_username")).sendKeys(login);
    webDriver.findElement(By.name("secretkey")).sendKeys(password);
    webDriver.findElement(By.name("secretkey")).submit();
    waitWebDriver.until(ExpectedConditions.titleContains(UNIVERSITY_EMAIL_LOGIN_URL_SUBTITLE));
  }

  public boolean assertTitle() throws Exception {
    return webDriver.getTitle().contains(UNIVERSITY_EMAIL_LOGIN_URL_SUBTITLE);
  }
}