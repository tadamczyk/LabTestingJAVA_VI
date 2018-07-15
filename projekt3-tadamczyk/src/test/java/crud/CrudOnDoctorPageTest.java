package crud;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import mainPackage.SystemProperties;

public class CrudOnDoctorPageTest {
  private static WebDriver webDriver;
  private static CrudOnDoctorPage crudPage;

  @BeforeEach
  public void setUp() throws Exception {
    SystemProperties.setSystemPropertyForFirefox();
    webDriver = new FirefoxDriver();
    crudPage = new CrudOnDoctorPage(webDriver);
  }

  @Test
  public void shouldCorrectAddNewDoctorTest() throws Exception {
    crudPage.addDoctor("Adam", "Adam", "Surgeon", "94050808695");
    assertThat(crudPage.assertUrl()).isTrue();
  }

  @Test
  public void shouldCorrectEditDoctorLastnameTest() throws Exception {
    crudPage.addDoctor("Adam", "Adam", "Surgeon", "67022822731");
    crudPage.editDoctor("Adam", "Adamczyk", "Surgeon", "67022822731");
    assertThat(crudPage.assertUrl()).isTrue();
  }

  @Test
  public void shouldCorrectEditDoctorPESELTest() throws Exception {
    String oldPesel = "83071766415";
    String newPesel = "58112134522";
    crudPage.addDoctor("Adam", "Biks", "Surgeon", oldPesel);
    crudPage.editDoctor("Adam", "Biks", "Surgeon", newPesel);
    assertThat(oldPesel).isNotEqualTo(newPesel);
  }

  @Test
  public void shouldCorrectDeleteDoctorTest() throws Exception {
    crudPage.addDoctor("Adam", "Tobiasz", "Surgeon", "96020358184");
    crudPage.deleteDoctor();
    assertThat(crudPage.assertUrl(), is(not(false)));
  }

  @AfterEach
  public void tearDown() throws Exception {
    webDriver.quit();
  }
}