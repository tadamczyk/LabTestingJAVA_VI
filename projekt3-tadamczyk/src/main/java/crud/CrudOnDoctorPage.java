package crud;

import static mainPackage.PagesUrl.ADD_NEW_DOCTOR_URL;
import static mainPackage.PagesUrl.DOCTORS_URL;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrudOnDoctorPage {
  private WebDriver webDriver;
  private final Wait<WebDriver> waitWebDriver;

  public CrudOnDoctorPage(WebDriver webDriver) {
    this.webDriver = webDriver;
    waitWebDriver = new WebDriverWait(webDriver, 10);
    webDriver.get(ADD_NEW_DOCTOR_URL);
  }

  public void addDoctor(String firstname, String lastname, String occupation, String pesel) throws Exception {
    waitWebDriver.until(ExpectedConditions.presenceOfElementLocated(By.name("doctor[firstname]")));
    webDriver.findElement(By.name("doctor[firstname]")).clear();
    webDriver.findElement(By.name("doctor[firstname]")).sendKeys(firstname);
    waitWebDriver.until(ExpectedConditions.presenceOfElementLocated(By.name("doctor[lastname]")));
    webDriver.findElement(By.name("doctor[lastname]")).clear();
    webDriver.findElement(By.name("doctor[lastname]")).sendKeys(lastname);
    waitWebDriver.until(ExpectedConditions.presenceOfElementLocated(By.name("doctor[occupation]")));
    webDriver.findElement(By.name("doctor[occupation]")).clear();
    webDriver.findElement(By.name("doctor[occupation]")).sendKeys(occupation);
    waitWebDriver.until(ExpectedConditions.presenceOfElementLocated(By.name("doctor[pesel]")));
    webDriver.findElement(By.name("doctor[pesel]")).clear();
    webDriver.findElement(By.name("doctor[pesel]")).sendKeys(pesel);
    webDriver.findElement(By.name("commit")).submit();
    waitWebDriver.until(ExpectedConditions.urlMatches(DOCTORS_URL + "\\d+"));
  }

  public void editDoctor(String firstname, String lastname, String occupation, String pesel) throws Exception {
    webDriver.get(webDriver.getCurrentUrl() + "/edit");
    addDoctor(firstname, lastname, occupation, pesel);
  }

  public void deleteDoctor() {
    webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    webDriver.findElement(By.xpath("/html/body/div/a[2]")).click();
    waitWebDriver.until(ExpectedConditions.alertIsPresent());
    Alert alert = webDriver.switchTo().alert();
    alert.accept();
    waitWebDriver.until(ExpectedConditions.urlMatches(DOCTORS_URL));
  }

  public boolean assertUrl() throws Exception {
    return webDriver.getCurrentUrl().startsWith(DOCTORS_URL);
  }
}