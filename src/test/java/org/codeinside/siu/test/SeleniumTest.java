package org.codeinside.siu.test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {
     @Test
    public void exampleSelenium() throws InterruptedException {
//         HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
//         driver.setJavascriptEnabled(true);
//         java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
//         java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
//         driver.get("https://localhost/web-client/ui/");
//         WebElement element = driver.findElement(By.xpath("/html/body/center/form/table/tbody/tr/td/table/tbody/tr/th"));
//         Assert.assertEquals(element.getText(), "Система исполнения услуг");
//         driver.findElement(By.name("j_username")).sendKeys("demomanager");
//         driver.findElement(By.name("j_password")).sendKeys("demomanager");
//         driver.findElement(By.xpath("/html/body/center/form/table/tbody/tr/td/table/tbody/tr[4]/td[2]/input")).submit();
//         Assert.assertEquals(driver.getTitle(), "СИУ :: demomanager");
//         WebDriverWait wait = new WebDriverWait(driver, 10);
//         wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Новая процедура')]")));
//         driver.findElement(By.xpath("//span[contains(text(),'Новая процедура')]")).click();
//         Thread.sleep(1000);
//         Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class, 'v-caption')]/span[contains(text(),'Услуга')]")).getText(),"Услуга");
//         driver.quit();

     }
}
