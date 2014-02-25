package org.codeinside.siu;

import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Utils {

  public static void transitionTo(HtmlUnitDriver driver, String name) {
    driver.findElement(By.xpath("//span[contains(@class, 'v-menubar-menuitem v-menubar-more-menuitem')]")).click();
    driver.findElement(By.xpath("//span[contains(@class, 'v-menubar-menuitem-caption')][contains(text(), '" + name + "')]")).click();
  }

  public static void login(HtmlUnitDriver driver, String loginUser, String passwordUser) {
    driver.findElement(By.name("j_username")).sendKeys(loginUser);
    driver.findElement(By.name("j_password")).sendKeys(passwordUser);
    driver.findElement(By.xpath("//input[contains(@type,'submit')]")).submit();
  }
}
