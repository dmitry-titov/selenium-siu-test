package org.codeinside.siu;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class SeleniumOEP {
  public static void main(String[] args) throws InterruptedException {
//    smokeTestManager();
//    smokeTestAdmin();
//    editUserTest();
    smokeTestRequester();
    System.out.println("Complete!");
  }

  public static void smokeTestManager() throws InterruptedException {
    long start = System.currentTimeMillis();

    HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
    driver.setJavascriptEnabled(true);
    WebDriverWait wait = new WebDriverWait(driver, 10);
    java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
    java.util.logging.Logger.getLogger("org.apache.http").setLevel(Level.OFF);
    driver.get("https://localhost/web-client/ui/");
    WebElement element = driver.findElement(By.xpath("/html/body/center/form/table/tbody/tr/td/table/tbody/tr/th"));
    Assert.assertEquals(element.getText(), "Система исполнения услуг");

    Utils.login(driver, "demomanager", "demomanager");
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Новая процедура')]")));
    Utils.transitionTo(driver, "Услуги");
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Список доступных услуг')]")));
    driver.findElement(By.xpath("//span[contains(text(),'Создать')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'gwt-HTML')][contains(text(),'Наименование')]")));

    Utils.transitionTo(driver, "Административные процедуры");
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Новая процедура')]")));
    driver.findElement(By.xpath("//span[contains(text(),'Новая процедура')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'v-caption')]/span[contains(text(),'Услуга')]")));
    driver.findElement(By.xpath("//span[contains(text(),'Создать')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'gwt-HTML')][contains(text(),'Название')]")));

    Utils.transitionTo(driver, "Ведение справочников");
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Создание справочника')]")));
    Random random = new Random();
    String declarant = "Справочник"+random.nextInt(1000);

    driver.findElement(By.xpath("//input[contains(@class,'v-textfield v-textfield-error v-textfield-required')]")).sendKeys(declarant);
    driver.findElement(By.xpath("//span[contains(text(),'Сохранить')]")).click();
    driver.findElement(By.xpath("//div[contains(@class,'v-table-cell-wrapper')][contains(text(),"+declarant+")]")).click();
//    System.out.println(driver.getPageSource());
//    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Ключ')]")));

//    Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'Добавление значения в справочник " + declarant + "')]")));
//    Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'Ключ')]")));
//    Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'Значение')]")));
    driver.findElement(By.xpath("//span[contains(@class,'v-tabsheet-caption-close')]")).click();
    driver.quit();
    long estimatedTime = System.currentTimeMillis() - start;
    System.out.println(estimatedTime);
  }

  public static void smokeTestAdmin() throws InterruptedException {
    HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
    driver.setJavascriptEnabled(true);
    WebDriverWait wait = new WebDriverWait(driver, 10);
    java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
    java.util.logging.Logger.getLogger("org.apache.http").setLevel(Level.OFF);
    driver.get("https://localhost/web-client/ui/");
    WebElement element = driver.findElement(By.xpath("/html/body/center/form/table/tbody/tr/td/table/tbody/tr/th"));
    Assert.assertEquals(element.getText(), "Система исполнения услуг");

    Utils.login(driver, "demoadmin", "demoadmin");
    if (driver.getTitle().equals("Отказано в доступе")){
      driver.get("https://localhost/web-client/admin/");
    }
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Создание организации')]")));
    driver.findElement(By.xpath("//span[contains(text(),'Создание организации')]")).click();

    Random random = new Random();
    String organization = "Организация"+random.nextInt(1000);
    String testKey = "Код"+random.nextInt(1000);
    String testName = "Название"+random.nextInt(1000);
    String[] user = {"login"+random.nextInt(1000),"pass"+random.nextInt(1000), "User"+random.nextInt(1000)};
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@class,'v-textfield v-textfield-error v-textfield-required')]")));

    driver.findElement(By.xpath("//input[contains(@class,'v-textfield v-textfield-error v-textfield-required')]")).sendKeys(organization);
    driver.findElement(By.xpath("//span[contains(text(),'Создать')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Добавить пользователя')]")));
    driver.findElement(By.xpath("//span[contains(text(),'Добавить пользователя')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'v-checkbox v-select-option')]")));
//    System.out.println(driver.getPageSource());

    driver.findElement(By.xpath("//div[1]/div/div/div/div[2]/div/input")).sendKeys(user[0]);
    driver.findElement(By.xpath("//div[2]/div/div/div/div[2]/div/input")).sendKeys(user[1]);
    driver.findElement(By.xpath("//div[3]/div/div/div/div[2]/div/input")).sendKeys(user[1]);
    driver.findElement(By.xpath("//div[4]/div/div/div/div[2]/div/input")).sendKeys(user[2]);
    driver.findElement(By.xpath("//div[11]/div/div/div/div/div/div/span/span[contains(text(),'Добавить')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'v-table-cell-wrapper')][contains(text(),"+user[0]+")]")));

    driver.findElement(By.xpath("//div[contains(text(),'Группы')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Создание группы')]")));

    driver.findElement(By.xpath("//span[contains(text(),'Создание группы')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Код группы')]")));
    Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'Код группы')]")));
    Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'Название группы')]")));
    driver.findElement(By.xpath("//span[contains(text(),'Отменить')]")).click();

    driver.findElement(By.xpath("//div[contains(text(),'Сотрудники')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Создание группы')]")));

    driver.findElement(By.xpath("//div[contains(text(),'Информационные системы')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'8201')]")));
    driver.findElement(By.xpath("//div[contains(text(),'8201')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Очистить')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[1]/td/input[contains(@class,'v-textfield v-textfield-required')]")));
    driver.findElement(By.xpath("//tr[1]/td/input[contains(@class,'v-textfield v-textfield-required')]")).sendKeys(testKey);
    driver.findElement(By.xpath("//tr[2]/td/input[contains(@class,'v-textfield v-textfield-required')]")).sendKeys(testName);
    driver.findElement(By.xpath("//span[contains(text(),'Сохранить')]")).click();

    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text()," + testKey + ")]")));
    driver.findElement(By.xpath("//div[contains(text(),'Сервисы информационных систем')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Зарегистрированные в системе сервисы')]")));
    driver.findElement(By.xpath("//tr[contains(@class,'v-table-row')]/td/div[contains(text(),'mvd3456')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Очистить')]")).click();
    driver.findElement(By.xpath("//tr[contains(@class,'v-table-row')]/td/div[contains(text(),'Подача заявления')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Сохранить')]")).click();

    driver.findElement(By.xpath("//div[contains(text(),'Сертификаты')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Привязка сертификатов')]")));
    Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'Изменить')]")));

    driver.findElement(By.xpath("//div[contains(text(),'Логи')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Субъект')]")));

    driver.findElement(By.xpath("//div[contains(text(),'Новости')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Список новостей')]")));
    driver.findElement(By.xpath("//input[contains(@class,'v-textfield v-textfield-required')]")).sendKeys(testKey);
//    driver.findElement(By.xpath("//iframe[contains(@class, 'gwt-RichTextArea')]")).sendKeys(testName);
    driver.findElement(By.xpath("//span[contains(text(),'Создать')]")).click();
//    Thread.sleep(2000);
//    driver.findElement(By.xpath("//span[contains(text(),'Сбросить')]")).click();
//    Thread.sleep(2000);
//    Assert.assertNull(driver.findElement(By.xpath("//input[contains(@class,'v-textfield v-textfield-required')]")));
    driver.findElement(By.xpath("//span[contains(@class,'v-tabsheet-caption-close')]")).click();
    driver.quit();

     }

  public static void editUserTest() throws InterruptedException {
    HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
    driver.setJavascriptEnabled(true);
    WebDriverWait wait = new WebDriverWait(driver, 10);
    java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
    java.util.logging.Logger.getLogger("org.apache.http").setLevel(Level.OFF);
    driver.get("https://localhost/web-client/ui/");
    WebElement element = driver.findElement(By.xpath("/html/body/center/form/table/tbody/tr/td/table/tbody/tr/th"));
    Assert.assertEquals(element.getText(), "Система исполнения услуг");

    Utils.login(driver, "demoadmin", "demoadmin");
    if (driver.getTitle().equals("Отказано в доступе")){
      driver.get("https://localhost/web-client/admin/");
    }
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Организация')]")));
    driver.findElement(By.xpath("//div[contains(text(),'Организация')]")).click();


    Random random = new Random();
    String[] user = {"login"+random.nextInt(1000),"pass"+random.nextInt(1000), "User"+random.nextInt(1000)};
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Добавить пользователя')]")));

    driver.findElement(By.xpath("//span[contains(text(),'Добавить пользователя')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'v-checkbox v-select-option')]")));


    driver.findElement(By.xpath("//div[1]/div/div/div/div[2]/div/input")).sendKeys(user[0]);
    driver.findElement(By.xpath("//div[2]/div/div/div/div[2]/div/input")).sendKeys(user[1]);
    driver.findElement(By.xpath("//div[3]/div/div/div/div[2]/div/input")).sendKeys(user[1]);
    driver.findElement(By.xpath("//div[4]/div/div/div/div[2]/div/input")).sendKeys(user[2]);
    driver.findElement(By.xpath("//div[11]/div/div/div/div/div/div/span/span[contains(text(),'Добавить')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'v-table-cell-wrapper')][contains(text(),"+user[0]+")]")));

    driver.findElement(By.xpath("//div[contains(text(),'Сертификаты')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Привязка сертификатов')]")));

    driver.findElement(By.xpath("//div[contains(text(),'Пользователи')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(text(),"+user[0]+")]")));
    driver.findElement(By.xpath("//input[contains(text(),"+ user[0] +")]"));

    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Редактирование')]")));
    driver.findElement(By.xpath("//span[contains(text(),'Редактирование')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Права')]")));
    driver.findElement(By.xpath("//span[contains(text(),'Общий контролер')]")).click();
    driver.findElement(By.xpath("//span[contains(text(),'Применить')]")).click();

    driver.findElement(By.xpath("//span[contains(@class,'v-tabsheet-caption-close')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text()," + user[0] + ")]")));

    Utils.login(driver, user[0],user[1]);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Состояние исполнения')]")));
    driver.findElement(By.xpath("//span[contains(@class,'v-tabsheet-caption-close')]")).click();
    driver.quit();
  }

  public static void smokeTestRequester() throws InterruptedException {
    HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
    driver.setJavascriptEnabled(true);
    WebDriverWait wait = new WebDriverWait(driver, 15);
    java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
    java.util.logging.Logger.getLogger("org.apache.http").setLevel(Level.OFF);
    driver.get("https://localhost/web-client/ui/");
    WebElement element = driver.findElement(By.xpath("/html/body/center/form/table/tbody/tr/td/table/tbody/tr/th"));
    Assert.assertEquals(element.getText(), "Система исполнения услуг");

    Utils.login(driver, "demorequester", "demorequester");
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Процедура')]")));


    driver.findElement(By.xpath("//tr[2]/td/div/div[contains(@class,'v-filterselect-button')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'v2 Сведения о неполучении пособия')]")));
    driver.findElement(By.xpath("//span[contains(text(),'v2 Сведения о неполучении пособия')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Завершить')]")));
    driver.findElement(By.xpath("//span[contains(text(),'Завершить')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Cледующая заявка...')]")));

    driver.findElement(By.xpath("//div[contains(text(),'Исполнение')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Обновить списки заявок')]")));
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.findElement(By.xpath("//span[contains(text(),'Обновить списки заявок')]")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody/tr[1]/td/div/div/span/span[contains(text(),'Забрать')]")));
    driver.findElement(By.xpath("//tbody/tr[1]/td/div/div/span/span[contains(text(),'Забрать')]")).click();
//    long startTime= System.currentTimeMillis();
//    do {
//      driver.findElement(By.xpath("//span[contains(text(),'Обновить списки заявок')]")).click();
//      Thread.sleep(5000);
//    } while (!(driver.findElement(By.xpath("//div[contains(text(),'Забрать')]")).isDisplayed()) || startTime<=60000 );
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Обработать')]")));
    driver.findElement(By.xpath("//span[contains(text(),'Обработать')]")).click();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    System.out.println(driver.getPageSource());
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Завершить')]")));
    driver.findElement(By.xpath("//span[contains(text(),'Завершить')]")).click();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Процедура')]")));
    driver.findElement(By.xpath("//span[contains(@class,'v-tabsheet-caption-close')]")).click();
    driver.quit();
  }
}

