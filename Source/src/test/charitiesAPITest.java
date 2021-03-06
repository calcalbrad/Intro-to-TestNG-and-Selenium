package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Goes through the charities API call as they all contain the desired attributes
 */
public class charitiesAPITest {
    String baseUrl = "https://api.trademe.co.nz/v1/Charities.XML";
    public WebDriver driver;

    @BeforeTest
    public void setupTests() {
        // Sets up the Selenium driver to automate Chrome testing
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        // Open TradeMe Sandbox
        driver.get(baseUrl);
    }

    @Test
    public void isStJohnsThere() {
        // Set the variables we are testing
        boolean isStJohnFound = false;

        // Get the elements
        WebElement bodyElement = driver.findElement(By.tagName("body"));
        String body = bodyElement.getText();

        // VVV Tried to parse as an XML document but had issues so had to iterate and VVV
        //     check as a String, which I know is a bit smelly

        // Create a regex pattern to only take info in between description tags
        String pattern1 = "<Description>";
        String pattern2 = "</Description>";
        String regexString = Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2);
        Pattern pattern = Pattern.compile(regexString);
        Matcher matcher = pattern.matcher(body);

        ArrayList<String> descriptionElements = new ArrayList<>();
        while (matcher.find()) {
            descriptionElements.add(matcher.group(1));
        }

        // Check the list to see if St Johns exists
        for (String element : descriptionElements) {
            if (element.equals("St John")) {
                isStJohnFound = true;
            }
        }

        // Results of test
        Assert.assertEquals(isStJohnFound, true);
    }

    @AfterTest
    public void closeTest() {
        // Close any reference to driver
        driver.close();
    }
}
