package mainPackage;

import org.junit.jupiter.api.condition.OS;

public final class OperatingSystemType {
  public static OS getOperatingSystem() {
    OS operatingSystem = null;
    String operatingSystemName = System.getProperty("os.name").toLowerCase();
    if (operatingSystemName.contains("win")) {
      operatingSystem = OS.WINDOWS;
    } else if (operatingSystemName.contains("nix") || operatingSystemName.contains("nux")
        || operatingSystemName.contains("aix")) {
      operatingSystem = OS.LINUX;
    }
    return operatingSystem;
  }

  public static boolean isWindows() {
    return getOperatingSystem() == OS.WINDOWS;
  }

  public static boolean isLinux() {
    return getOperatingSystem() == OS.LINUX;
  }
}