package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class charitiesAPITest {
    public static void main(String[] args) {
        // Sets up the Selenium driver to automate Chrome testing
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Open TradeMe Sandbox
        String baseUrl = "https://api.trademe.co.nz/v1/Charities.XML";
        driver.get(baseUrl);

        // Set the variables we are testing
        boolean isStJohnFound = false;

        // Tests in here
        //List<WebElement> charityDescription = driver.findElements(By.tagName("Description"));
        List<WebElement> charityDescription = driver.findElements(By.xpath("/CharityCollection/Charity/Description"));
        for (WebElement charity : charityDescription) {
            System.out.println(charity.getText());
            switch (charity.getText()) {
                case "St John":
                    isStJohnFound = true;
                    break;
            }
        }

        // Results of test
        if (isStJohnFound) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed!");
        }

        // Close any reference to driver
        driver.close();
    }
}