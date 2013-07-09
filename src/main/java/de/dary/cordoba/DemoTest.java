package de.dary.cordoba;

import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class DemoTest {
  private SelendroidLauncher selendroidServer = null;

  @Test
  public void testShouldBeAbleToEnterTextAndClickAButton() throws Exception {
    WebDriver driver = startSelendroidServer();
    driver.switchTo().window("WEBVIEW");

    WebElement inputField = driver.findElement(By.cssSelector("input[class='test-text-input']"));
    inputField.sendKeys("Hello Selendroid");
    WebElement button = driver.findElement(By.cssSelector("button[class='test-button']"));
    button.click();
  }

  protected WebDriver startSelendroidServer() throws Exception {
    if (selendroidServer != null) {
      selendroidServer.stopSelendroid();
    }
    SelendroidConfiguration config = new SelendroidConfiguration();
    config.addSupportedApp("src/main/resources/HybridApp-debug.apk");
    config.setPort(4445);
    selendroidServer = new SelendroidLauncher(config);
    selendroidServer.lauchSelendroid();

    SelendroidCapabilities caps = SelendroidCapabilities.emulator("com.example.hybridapp:1.0");
    WebDriver driver = new SelendroidDriver("http://localhost:4445/wd/hub", caps);
    return driver;
  }

  // @AfterClass
  public void stopSelendroidServer() {
    if (selendroidServer != null) {
      selendroidServer.stopSelendroid();
    }
  }
}
