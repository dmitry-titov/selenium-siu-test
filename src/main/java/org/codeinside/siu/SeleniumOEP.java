package org.codeinside.siu;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;

public class SeleniumOEP {
    public static void main(String[] args) throws InterruptedException {
        HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
        driver.setJavascriptEnabled(true);

        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http").setLevel(Level.FINE);

        driver.get("https://localhost/web-client/ui/");
        WebElement element = driver.findElement(By.xpath("/html/body/center/form/table/tbody/tr/td/table/tbody/tr/th"));
        Assert.assertEquals(element.getText(), "Система исполнения услуг");
        login(driver, "demomanager", "demomanager");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Новая процедура')]")));
        driver.findElement(By.xpath("//span[contains(text(),'Новая процедура')]")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class, 'v-caption')]/span[contains(text(),'Услуга')]")).getText(), "Услуга");
        Thread.sleep(1000);
        transitionTo(driver , "Услуги");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Список доступных услуг')]")));
        Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Список доступных услуг')]")).getText(), "Список доступных услуг");
        driver.quit();
        System.out.println("It's test main OK");
    }

public static void transitionTo(HtmlUnitDriver driver, String name){
    driver.findElement(By.xpath("//span[contains(@class, 'v-menubar-menuitem v-menubar-more-menuitem')]")).click();
    driver.findElement(By.xpath("//span[contains(@class, 'v-menubar-menuitem-caption')][contains(text(), '" + name + "')]")).click();
}

public static void login(HtmlUnitDriver driver, String loginUser, String passwordUser){
    driver.findElement(By.name("j_username")).sendKeys(loginUser);
    driver.findElement(By.name("j_password")).sendKeys(passwordUser);
    driver.findElement(By.xpath("//input[contains(@type,'submit')]")).submit();
    Assert.assertEquals(driver.getTitle(), "СИУ :: " + loginUser);

}
}

