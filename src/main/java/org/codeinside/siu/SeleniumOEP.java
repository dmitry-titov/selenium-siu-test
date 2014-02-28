package org.codeinside.siu;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SeleniumOEP {
  public static int DEFAULT_IMPLICIT_WAIT = 5;

  public static void main(String[] args) throws InterruptedException {
    Random random = new Random();

    final String adminTest = "adminTest" + random.nextInt(1000);
    final String managerTest = "managerTest" + random.nextInt(1000);
    final String requesterTest = "requesterTest" + random.nextInt(1000);
    final String executorTest = "executorTest" + random.nextInt(1000);
    final String[] userLogin = {adminTest, managerTest, requesterTest, executorTest};
    beforeTest(userLogin, args[0]);
    smokeTestAdmin(userLogin[0], args[0]);
    smokeTestManager(userLogin[1], args[0]);
    smokeTestRequester(userLogin[2], userLogin[3], args[0]);
    viewUserTest(userLogin[0], args[0]);
  }

  public static void beforeTest(String[] userLogin, String server) throws InterruptedException {
    HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
    driver.setJavascriptEnabled(true);
    WebDriverWait wait = new WebDriverWait(driver, 30);
    driver.get("https://" + server + "/web-client/ui/");
    WebElement element = getElementByLocator(By.xpath("//th[contains(text(),'Система исполнения услуг')]"), driver);
    Assert.assertEquals(element.getText(), "Система исполнения услуг");

    login(driver, "demoadmin", "demoadmin");
    if (driver.getTitle().equals("Отказано в доступе")) {
      driver.get("https://localhost/web-client/admin/");
    }
    getElementByLocator(By.xpath("//div[contains(text(),'Демонстрационная организация')]"), driver);
    clickElement(By.xpath("//div[contains(text(),'Демонстрационная организация')]"), driver);

    clickElement(By.xpath("//span[contains(text(),'Добавить пользователя')]"), driver);
    getElementByLocator(By.xpath("//div[11]/div/div/div/div/div/div/span/span[contains(text(),'Добавить')]"), driver);
    driver.navigate().refresh();
    final String loginInput = "//div[1]/div/div/div/div[2]/div/input";
    final String passwordInput = "//div[2]/div/div/div/div[2]/div/input";
    final String repeatPasswordInput = "//div[3]/div/div/div/div[2]/div/input";
    final String fioInput = "//div[4]/div/div/div/div[2]/div/input";

    sendKeysToField(By.xpath(loginInput), driver, userLogin[0]);
    sendKeysToField(By.xpath(passwordInput), driver, "1");
    sendKeysToField(By.xpath(repeatPasswordInput), driver, "1");
    sendKeysToField(By.xpath(fioInput), driver, userLogin[0]);
    element = getElementByLocator(By.xpath("//label[contains(text(),'Администратор')]"), driver);
    String locatorId = element.getAttribute("for");
    clickElement(By.xpath("//*[@id='" + locatorId + "']"), driver);
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    clickElement(By.xpath("//div[11]/div/div/div/div/div/div/span/span[contains(text(),'Добавить')]"), driver);
    getElementByLocator(By.xpath("//div[contains(@class,'v-table-cell-wrapper')][contains(text()," + userLogin[0] + ")]"), driver);

    driver.navigate().refresh();
    wait.withTimeout(5, TimeUnit.SECONDS);
    clickElement(By.xpath("//span[contains(text(),'Добавить пользователя')]"), driver);
    wait.withTimeout(5, TimeUnit.SECONDS);
    getElementByLocator(By.xpath("//div[11]/div/div/div/div/div/div/span/span[contains(text(),'Добавить')]"), driver);

    sendKeysToField(By.xpath(loginInput), driver, userLogin[1]);
    sendKeysToField(By.xpath(passwordInput), driver, "1");
    sendKeysToField(By.xpath(repeatPasswordInput), driver, "1");
    sendKeysToField(By.xpath(fioInput), driver, userLogin[1]);
    element = getElementByLocator(By.xpath("//label[contains(text(),'Менеджер')]"), driver);
    locatorId = element.getAttribute("for");
    clickElement(By.xpath("//*[@id='" + locatorId + "']"), driver);
    clickElement(By.xpath("//div[11]/div/div/div/div/div/div/span/span[contains(text(),'Добавить')]"), driver);
    getElementByLocator(By.xpath("//div[contains(@class,'v-table-cell-wrapper')][contains(text()," + userLogin[1] + ")]"), driver);

    driver.navigate().refresh();
    clickElement(By.xpath("//span[contains(text(),'Добавить пользователя')]"), driver);
    getElementByLocator(By.xpath("//div[11]/div/div/div/div/div/div/span/span[contains(text(),'Добавить')]"), driver);

    sendKeysToField(By.xpath(loginInput), driver, userLogin[2]);
    sendKeysToField(By.xpath(passwordInput), driver, "1");
    sendKeysToField(By.xpath(repeatPasswordInput), driver, "1");
    sendKeysToField(By.xpath(fioInput), driver, userLogin[2]);
    element = driver.findElement(By.xpath("//label[contains(text(),'Заявитель')]"));
    locatorId = element.getAttribute("for");
    clickElement((By.xpath("//*[@id='" + locatorId + "']")), driver);
    element = getElementByLocator(By.xpath("//label[contains(text(),'Исполнитель')]"), driver);
    locatorId = element.getAttribute("for");
    clickElement(By.xpath("//*[@id='" + locatorId + "']"), driver);
    getElementByLocator(By.xpath("//div[contains(text(),'test')]"), driver);
    clickElement(By.xpath("//div[contains(text(),'test')]"), driver);
    element = getElementByLocator(By.xpath("//label[contains(text(),'Исполнитель')]"), driver);
    locatorId = element.getAttribute("for");
    clickElement(By.xpath("//*[@id='" + locatorId + "']"), driver);
    clickElement(By.xpath("//div[11]/div/div/div/div/div/div/span/span[contains(text(),'Добавить')]"), driver);
    getElementByLocator(By.xpath("//div[contains(@class,'v-table-cell-wrapper')][contains(text()," + userLogin[2] + ")]"), driver);

    driver.navigate().refresh();
    clickElement(By.xpath("//span[contains(text(),'Добавить пользователя')]"), driver);
    sendKeysToField(By.xpath(loginInput), driver, userLogin[3]);
    sendKeysToField(By.xpath(passwordInput), driver, "1");
    sendKeysToField(By.xpath(repeatPasswordInput), driver, "1");
    sendKeysToField(By.xpath(fioInput), driver, userLogin[3]);

    element = getElementByLocator(By.xpath("//label[contains(text(),'Исполнитель')]"), driver);
    locatorId = element.getAttribute("for");
    clickElement(By.xpath("//*[@id='" + locatorId + "']"), driver);
    clickElement(By.xpath("//div[contains(text(),'test')]"), driver);
    clickElement(By.xpath("//div[11]/div/div/div/div/div/div/span/span[contains(text(),'Добавить')]"), driver);
    getElementByLocator(By.xpath("//div[contains(@class,'v-table-cell-wrapper')][contains(text()," + userLogin[3] + ")]"), driver);
    final String logoutButton = "//span[contains(@class,'v-tabsheet-caption-close')]";
    clickElement(By.xpath(logoutButton), driver);
    driver.quit();
  }

  public static void smokeTestManager(String loginUser, String server) {
    HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
    driver.setJavascriptEnabled(true);

    driver.get("https://" + server + "/web-client/ui/");
    WebElement element = driver.findElement(By.xpath("//th[contains(text(),'Система исполнения услуг')]"));
    Assert.assertEquals(element.getText(), "Система исполнения услуг");

    login(driver, loginUser, "1");
    if (driver.getTitle().equals("Отказано в доступе")) {
      driver.get("https://localhost/web-client/admin/");
    }
    getElementByLocator(By.xpath("//span[contains(text(),'Новая процедура')]"), driver);
    transitionTo(driver, "Услуги");
    clickElement(By.xpath("//span[contains(text(),'Создать')]"), driver);
    getElementByLocator(By.xpath("//div[contains(@class, 'gwt-HTML')][contains(text(),'Наименование')]"), driver);

    transitionTo(driver, "Административные процедуры");
    clickElement(By.xpath("//span[contains(text(),'Новая процедура')]"), driver);
    clickElement(By.xpath("//span[contains(text(),'Создать')]"), driver);
    getElementByLocator(By.xpath("//div[contains(@class, 'gwt-HTML')][contains(text(),'Название')]"), driver);

    transitionTo(driver, "Ведение справочников");
    Random random = new Random();
    String declarant = "Справочник" + random.nextInt(1000);
    final String declarantInput = "//input[contains(@class,'v-textfield v-textfield-error v-textfield-required')]";
    sendKeysToField(By.xpath(declarantInput), driver, declarant);
    clickElement(By.xpath("//span[contains(text(),'Сохранить')]"), driver);
    clickElement(By.xpath("//div[contains(@class,'v-table-cell-wrapper')][contains(text()," + declarant + ")]"), driver);

    final String logoutButton = "//span[contains(@class,'v-tabsheet-caption-close')]";
    clickElement(By.xpath(logoutButton), driver);
    driver.quit();
  }

  public static void smokeTestAdmin(String loginUser, String server) {
    HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
    driver.setJavascriptEnabled(true);
    driver.get("https://" + server + "/web-client/ui/");
    WebElement element = driver.findElement(By.xpath("//th[contains(text(),'Система исполнения услуг')]"));
    Assert.assertEquals(element.getText(), "Система исполнения услуг");

    login(driver, loginUser, "1");
    if (driver.getTitle().equals("Отказано в доступе")) {
      driver.get("https://localhost/web-client/admin/");
    }
    clickElement(By.xpath("//span[contains(text(),'Создание организации')]"), driver);

    Random random = new Random();
    String organization = "Организация" + random.nextInt(1000);
    String testKey = "Код" + random.nextInt(1000);
    String testName = "Название" + random.nextInt(1000);
    String[] user = {"login" + random.nextInt(1000), "pass" + random.nextInt(1000), "User" + random.nextInt(1000)};

    sendKeysToField(By.xpath("//input[contains(@class,'v-textfield v-textfield-error v-textfield-required')]"), driver, organization);
    clickElement(By.xpath("//span[contains(text(),'Создать')]"), driver);
    clickElement(By.xpath("//span[contains(text(),'Добавить пользователя')]"), driver);

    getElementByLocator(By.xpath("//div[11]/div/div/div/div/div/div/span/span[contains(text(),'Добавить')]"), driver);

    final String loginInput = "//div[1]/div/div/div/div[2]/div/input";
    final String passwordInput = "//div[2]/div/div/div/div[2]/div/input";
    final String repeatPasswordInput = "//div[3]/div/div/div/div[2]/div/input";
    final String fioInput = "//div[4]/div/div/div/div[2]/div/input";
    sendKeysToField(By.xpath(loginInput), driver, user[0]);
    sendKeysToField(By.xpath(passwordInput), driver, user[1]);
    sendKeysToField(By.xpath(repeatPasswordInput), driver, user[1]);
    sendKeysToField(By.xpath(fioInput), driver, user[2]);
    clickElement(By.xpath("//div[11]/div/div/div/div/div/div/span/span[contains(text(),'Добавить')]"), driver);
    getElementByLocator(By.xpath("//div[contains(@class,'v-table-cell-wrapper')][contains(text()," + user[0] + ")]"), driver);

    clickElement(By.xpath("//div[contains(text(),'Группы')]"), driver);

    clickElement(By.xpath("//span[contains(text(),'Создание группы')]"), driver);
    getElementByLocator(By.xpath("//span[contains(text(),'Код группы')]"), driver);
    Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'Код группы')]")));
    Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'Название группы')]")));
    clickElement(By.xpath("//span[contains(text(),'Отменить')]"), driver);

    clickElement(By.xpath("//div[contains(text(),'Сотрудники')]"), driver);
    getElementByLocator(By.xpath("//span[contains(text(),'Создание группы')]"), driver);

    clickElement(By.xpath("//div[contains(text(),'Информационные системы')]"), driver);
    clickElement(By.xpath("//div[contains(text(),'8201')]"), driver);
    clickElement(By.xpath("//span[contains(text(),'Очистить')]"), driver);

    final String codeInput = "//tr[1]/td/input[contains(@class,'v-textfield v-textfield-required')]";
    final String nameInput = "//tr[2]/td/input[contains(@class,'v-textfield v-textfield-required')]";

    sendKeysToField(By.xpath(codeInput), driver, testKey);
    sendKeysToField(By.xpath(nameInput), driver, testName);
    clickElement(By.xpath("//span[contains(text(),'Сохранить')]"), driver);

    getElementByLocator(By.xpath("//div[contains(text()," + testKey + ")]"), driver);
    clickElement(By.xpath("//div[contains(text(),'Сервисы информационных систем')]"), driver);
    getElementByLocator(By.xpath("//div[contains(text(),'Зарегистрированные в системе сервисы')]"), driver);
    clickElement(By.xpath("//tr[contains(@class,'v-table-row')]/td/div[contains(text(),'mvd3456')]"), driver);
    clickElement(By.xpath("//span[contains(text(),'Очистить')]"), driver);
    clickElement(By.xpath("//tr[contains(@class,'v-table-row')]/td/div[contains(text(),'Подача заявления')]"), driver);
    clickElement(By.xpath("//span[contains(text(),'Сохранить')]"), driver);

    clickElement(By.xpath("//div[contains(text(),'Сертификаты')]"), driver);
    getElementByLocator(By.xpath("//span[contains(text(),'Привязка сертификатов')]"), driver);
    Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'Изменить')]")));

    clickElement(By.xpath("//div[contains(text(),'Логи')]"), driver);
    getElementByLocator(By.xpath("//div[contains(text(),'Субъект')]"), driver);
    clickElement(By.xpath("//div[contains(text(),'Новости')]"), driver);
    getElementByLocator(By.xpath("//div[contains(text(),'Список новостей')]"), driver);

    final String headerInput = "//tr[1]/td[3]/input[contains(@class,'v-textfield v-textfield-required')]";
    sendKeysToField(By.xpath(headerInput), driver, testKey);
    clickElement(By.xpath("//span[contains(text(),'Создать')]"), driver);
    final String logoutButton = "//span[contains(@class,'v-tabsheet-caption-close')]";
    clickElement(By.xpath(logoutButton), driver);
    driver.quit();

  }

  public static void viewUserTest(String loginuser, String server) {
    HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
    driver.setJavascriptEnabled(true);
    driver.get("https://" + server + "/web-client/ui/");
    WebElement element = driver.findElement(By.xpath("//th[contains(text(),'Система исполнения услуг')]"));
    Assert.assertEquals(element.getText(), "Система исполнения услуг");

    login(driver, loginuser, "1");
    if (driver.getTitle().equals("Отказано в доступе")) {
      driver.get("https://localhost/web-client/admin/");
    }
    clickElement(By.xpath("//div[contains(text(),'Пользователи')]"), driver);
    final String rowTable = "//table[contains(@class, 'v-table-table')]/tbody/tr[2]/td[2]";
    clickElement(By.xpath(rowTable), driver);

    clickElement(By.xpath("//span[contains(text(),'Просмотр')]"), driver);
    getElementByLocator(By.xpath("//span[contains(text(),'Назад')]"), driver);
    Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'ФИО:')]")));
    Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'Роль:')]")));
    Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'Организация:')]")));
    Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'Состоит в группах:')]")));
    Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'Имеет доступ к группам исполнителей:')]")));
    Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'Имеет доступ к группам организаций:')]")));
    Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'Дата создания:')]")));
    Assert.assertNotNull(driver.findElement(By.xpath("//span[contains(text(),'Создатель:')]")));
    clickElement(By.xpath("//span[contains(text(),'Назад')]"), driver);
    getElementByLocator(By.xpath(rowTable), driver);
    final String logoutButton = "//span[contains(@class,'v-tabsheet-caption-close')]";
    clickElement(By.xpath(logoutButton), driver);
    driver.quit();
  }

  public static void smokeTestRequester(String loginRequester, String loginExecutor, String server) {
    HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
    driver.setJavascriptEnabled(true);
    WebDriverWait wait = new WebDriverWait(driver, 30);
    driver.get("https://" + server + "/web-client/ui/");
    WebElement element = getElementByLocator(By.xpath("//th[contains(text(),'Система исполнения услуг')]"), driver);
    Assert.assertEquals(element.getText(), "Система исполнения услуг");

    login(driver, loginRequester, "1");
    getElementByLocator(By.xpath("//span[contains(text(),'Процедура')]"), driver);

    clickElement(By.xpath("//tr[2]/td/div/div[contains(@class,'v-filterselect-button')]"), driver);
    getElementByLocator(By.xpath("//span[contains(text(),'v2 Сведения о неполучении пособия')]"), driver);
    clickElement(By.xpath("//span[contains(text(),'v2 Сведения о неполучении пособия')]"), driver);
    getElementByLocator(By.xpath("//span[contains(text(),'Завершить')]"), driver);
    clickElement(By.xpath("//span[contains(text(),'Завершить')]"), driver);
    getElementByLocator(By.xpath("//span[contains(text(),'Cледующая заявка...')]"), driver);
    driver.navigate().refresh();
    final String logoutButton = "//span[contains(@class,'v-tabsheet-caption-close')]";
    clickElement(By.xpath(logoutButton), driver);
    getElementByLocator(By.xpath("//th[contains(text(),'Система исполнения услуг')]"), driver);
    wait.withTimeout(60, TimeUnit.SECONDS);
    login(driver, loginExecutor, "1");
    getElementByLocator(By.xpath("//div[contains(text(),'Исполнение')]"), driver);
    driver.navigate().refresh();
    getElementByLocator(By.xpath("//span[contains(text(),'Обновить списки заявок')]"), driver);

    clickElement(By.xpath("//tbody/tr[1]/td/div/div/span/span[contains(text(),'Забрать')]"), driver);
    getElementByLocator(By.xpath("//tbody/tr[1]/td/div/div/span/span[contains(text(),'Обработать')]"), driver);
    clickElement(By.xpath("//tbody/tr[1]/td/div/div/span/span[contains(text(),'Обработать')]"), driver);

    getElementByLocator(By.xpath("//div[contains(text(),'Проверка результатов')]"), driver);
    clickElement(By.xpath("//span[contains(text(),'Завершить')]"), driver);

    getElementByLocator(By.xpath("//span[contains(text(),'Процедура')]"), driver);
    clickElement(By.xpath(logoutButton), driver);
    driver.quit();
  }

  public static void transitionTo(HtmlUnitDriver driver, String name) {
    final String listTab = "//span[contains(@class, 'v-menubar-menuitem v-menubar-more-menuitem')]";
    final String nameTab = "//span[contains(@class, 'v-menubar-menuitem-caption')][contains(text(), '" + name + "')]";
    driver.findElement(By.xpath(listTab)).click();
    driver.findElement(By.xpath(nameTab)).click();
  }

  public static void login(HtmlUnitDriver driver, String loginUser, String passwordUser) {
    driver.findElement(By.name("j_username")).sendKeys(loginUser);
    driver.findElement(By.name("j_password")).sendKeys(passwordUser);
    driver.findElement(By.xpath("//input[contains(@type,'submit')]")).submit();
  }

  public static WebElement getElementByLocator(By locator, WebDriver driver) {
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    WebElement we = null;
    boolean unfound = true;
    int tries = 0;
    while (unfound && tries < 5) {
      tries += 1;
      try {
        we = driver.findElement(locator);
        unfound = false; // FOUND IT
      } catch (StaleElementReferenceException ser) {
        unfound = true;
      } catch (NoSuchElementException nse) {
        unfound = true;
      } catch (Exception e) {
        System.out.println("Unknown error");
      }
    }
    driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT, TimeUnit.SECONDS);
    return we;
  }

  public static void sendKeysToField(By locator, WebDriver driver, String value) {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    WebElement we = null;
    boolean unfound = true;
    int tries = 0;
    while (unfound && tries < 10) {
      tries += 1;
      try {
        we = driver.findElement(locator);
        we.sendKeys(value);
        unfound = false; // FOUND IT
      } catch (StaleElementReferenceException ser) {
        unfound = true;
      } catch (NoSuchElementException nse) {
        unfound = true;
      } catch (Exception e) {
        System.out.println("Unknown error");
      }
    }
    driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT, TimeUnit.SECONDS);
  }

  public static void clickElement(By locator, WebDriver driver) {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    WebElement we = null;
    boolean unfound = true;
    int tries = 0;
    while (unfound && tries < 10) {
      tries += 1;
      try {
        we = driver.findElement(locator);
        we.click();
        unfound = false; // FOUND IT
      } catch (StaleElementReferenceException ser) {
        unfound = true;
      } catch (NoSuchElementException nse) {
        unfound = true;
      } catch (Exception e) {
        System.out.println("Unknown error");
      }
    }
    driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT, TimeUnit.SECONDS);
  }

}

