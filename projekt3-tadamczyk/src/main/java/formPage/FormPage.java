package formPage;

import static mainPackage.PagesUrl.FORMPAGE_URL;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

public class FormPage {
  private WebDriver webDriver;
  private FluentWait<WebDriver> waitWebDriver;

  @FindBy(name = "firstname")
  private WebElement firstname;

  @FindBy(name = "lastname")
  private WebElement lastname;

  @FindBy(id = "sex-0")
  private WebElement male;

  @FindBy(id = "sex-1")
  private WebElement female;

  @FindBy(id = "exp-2")
  private WebElement threeYearsExperience;

  @FindBy(id = "datepicker")
  private WebElement datepicker;

  @FindBy(name = "continents")
  private WebElement continents;

  @FindBy(name = "submit")
  private WebElement submit;

  @SuppressWarnings("deprecation")
  public FormPage(WebDriver driver) {
    this.webDriver = driver;
    waitWebDriver = new FluentWait<WebDriver>(webDriver).withTimeout(10, TimeUnit.SECONDS)
        .pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
    driver.get(FORMPAGE_URL);
  }

  public void fillIn(String _firstname, String _lastname, char _gender, String _date, String _profession)
      throws Exception {
    firstname.sendKeys(_firstname);
    lastname.sendKeys(_lastname);
    selectGenderButton(_gender);
    threeYearsExperience.click();
    datepicker.sendKeys(_date);
    selectProfessionButton(_profession);
    selectEuropeAsContinents();
    submit.click();
    waitWebDriver.until(ExpectedConditions.urlContains(_firstname));
  }

  private void selectGenderButton(char btn) {
    if (btn == 'M') {
      male.click();
    } else if (btn == 'F') {
      female.click();
    }
  }

  private void selectProfessionButton(String profession) {
    List<WebElement> professions = webDriver.findElements(By.name("profession"));
    int size = professions.size();
    for (int i = 0; i < size; i++) {
      String value = professions.get(i).getAttribute("value");
      if (value.equalsIgnoreCase(profession)) {
        professions.get(i).click();
        break;
      }
    }
  }

  private void selectEuropeAsContinents() {
    Select europe = new Select(continents);
    europe.selectByIndex(1);
  }

  public boolean assertUrl() throws Exception {
    return webDriver.getCurrentUrl().contains(FORMPAGE_URL);
  }
}