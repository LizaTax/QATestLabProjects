import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Homework {

    private static final String EMAIL = "webinar.test@gmail.com";
    private static final String PASSWORD = "Xcg7299bnSmMuRLp9ITw";

    public static void main(String[] args){
        WebDriver driver = getInitFirefoxDriver();
        // скрипт №1
        login(driver, EMAIL, PASSWORD);
        waitForLoad();
        logout(driver);

        // скрипт №2
        login(driver, EMAIL, PASSWORD);
        waitForLoad();

        // TODO: добавить в массив id для элементов "Каталог" и "Modules" после устранения багов верстки на сайте
        // После открытия элементов меню "Каталог" и "Modules" исчезают id всех других элементов меню, также для этих элементов отличается верстка
        // заголовка страницы, что делает невозможным написать подходящие локаторы для всех элементов меню, чтобы проверить их последовательно в цикле

        String [] tabId = {"tab-AdminDashboard", "subtab-AdminParentOrders", "subtab-AdminParentCustomer", "subtab-AdminParentCustomerThreads",
                "subtab-AdminStats", "subtab-AdminParentThemes", "subtab-AdminParentShipping",
                "subtab-AdminParentPayment", "subtab-AdminInternational", "subtab-ShopParameters", "subtab-AdminAdvancedParameters"};
        for (String menuTab : tabId) {
            driver.findElement(By.id(menuTab)).click();
            String s = driver.findElement(By.className("page-title")).getText();
            System.out.println(s);
            driver.navigate().refresh();
            waitForLoad();
            boolean result = driver.findElement(By.className("page-title")).getText().equals(s);
            if (!result) {
                throw new RuntimeException("Element with text " + s + " not found!");
            }
        }
        driver.close();
    }

    private static void login(WebDriver driver, String email, String password) {
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.name("submitLogin")).click();
    }

    private static void logout(WebDriver driver) {
        driver.findElement(By.className("employee_avatar_small")).click();
        driver.findElement(By.id("header_logout")).click();
    }

    private static WebDriver getInitFirefoxDriver(){
        System.setProperty("webdriver.gecko.driver",  Homework.class.getResource("geckodriver.exe").getPath());
        return new FirefoxDriver();
    }

    private static void waitForLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
