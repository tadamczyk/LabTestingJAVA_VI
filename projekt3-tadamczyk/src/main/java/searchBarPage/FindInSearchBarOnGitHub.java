package searchBarPage;

import static mainPackage.PagesSubtitles.GITHUB_SUBTITLE;
import static mainPackage.PagesUrl.GITHUB_URL;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FindInSearchBarOnGitHub {
  private WebElement q;
  private WebDriver webDriver;

  public FindInSearchBarOnGitHub(WebDriver webDriver) {
    this.webDriver = webDriver;
    webDriver.get(GITHUB_URL);
  }

  public void search(String text) throws Exception {
    q.sendKeys(text);
    q.submit();
    webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
  }

  public boolean assertTitle() throws Exception {
    return webDriver.getTitle().contains(GITHUB_SUBTITLE);
  }
}