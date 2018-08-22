import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Homework {
    public static void main(String[] args){
        WebDriver driver = getInitFirefoxDriver();
        // скрипт №1
        login(driver, "webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw");
        waitForLoad();
        logout(driver);

        // скрипт №2
        login(driver, "webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw");
        waitForLoad();

        String [] menuTabs = {"AdminParentOrders", "AdminParentCustomer", "AdminParentCustomerThreads", "AdminStats",
                "AdminParentThemes", "AdminParentShipping", "AdminParentPayment", "AdminInternational", "ShopParameters", "AdminAdvancedParameters"};
        for(int i = 0; i < menuTabs.length; i++) {
            driver.findElement(By.id(String.format("subtab-%s",menuTabs[i]))).click();
            String s = driver.findElement(By.className("page-title")).getText();
            System.out.println(s);
            driver.navigate().refresh();
            waitForLoad();
            if (!(driver.findElement(By.className("page-title")).getText().equals(s))) {
                throw new RuntimeException("Element with text " + s + " not found!");
            }
        }

        driver.findElement(By.id("subtab-AdminCatalog")).click();
        String s = driver.findElement(By.cssSelector(".header-toolbar > h2")).getText();
        System.out.println(s);
        driver.navigate().refresh();
        waitForLoad();
        if (!(driver.findElement(By.cssSelector(".header-toolbar > h2")).getText().equals(s))) {
            throw new RuntimeException("Element with text " + s + " not found!");
        }

        driver.findElement(By.cssSelector(".main-menu > li:nth-child(9)")).click();
        String str = driver.findElement(By.cssSelector(".header-toolbar > h2")).getText();
        System.out.println(str);
        driver.navigate().refresh();
        waitForLoad();
        if (!(driver.findElement(By.cssSelector(".header-toolbar > h2")).getText().equals(str))) {
            throw new RuntimeException("Element with text " + s + " not found!");
        }
        driver.close();
    }

    public static void login(WebDriver driver, String email, String password) {
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.name("submitLogin")).click();
    }

    public static void logout(WebDriver driver) {
        driver.findElement(By.className("employee_avatar_small")).click();
        driver.findElement(By.id("header_logout")).click();
    }

    public static WebDriver getInitFirefoxDriver(){
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
