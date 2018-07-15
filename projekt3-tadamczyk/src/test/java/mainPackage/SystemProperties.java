package mainPackage;

public final class SystemProperties {
  public static void setSystemPropertyForFirefox() {
    if (OperatingSystemType.isWindows()) {
      System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
    } else if (OperatingSystemType.isLinux()) {
      System.setProperty("webdriver.gecko.driver", "resources/geckodriver");
    }
  }

  public static void setSystemPropertyForChrome() {
    if (OperatingSystemType.isWindows()) {
      System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
    } else if (OperatingSystemType.isLinux()) {
      System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
    }
  }

  public static void setSystemPropertyForOpera() {
    if (OperatingSystemType.isWindows()) {
      System.setProperty("webdriver.opera.driver", "resources/operadriver.exe");
    } else if (OperatingSystemType.isLinux()) {
      System.setProperty("webdriver.opera.driver", "resources/operadriver");
    }
  }
}