package com.se2automate.google.test;

import com.se2automate.google.pages.GoogleSearchPage;
import com.se2automate.google.pages.GoogleSearchResultsPage;
import com.se2automate.util.VoiceUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * created by Gaurav Tiwari
 */
public class GoogleVoiceTest {
    private WebDriver driver;

    @DataProvider(name = "voiceSearch")
    public static Object[][] voiceSearchTestData() {
        return new Object[][]{
                {"temperature in Delhi"},
                {"show me the direction to Mumbai"},
                {"Avengers Infinity war show timings"},
                {"amazon stock price"}
        };
    }

    @BeforeClass
    public void setup() {
        //allocate memory for voice synthesizer
        VoiceUtil.allocate();
        WebDriverManager.chromedriver().setup();

    }

    @BeforeMethod
    public void methodSetUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //chrome options for always allowing microphone access
        chromeOptions.addArguments("--use-fake-ui-for-media-stream=1");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.google.com");
    }

    @Test(dataProvider = "voiceSearch")
    public void googleVoiceSearchTest(String searchText) throws InterruptedException {
        GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver);
        googleSearchPage.startListening();
        VoiceUtil.speak(searchText);
        googleSearchPage.stopListening();
        //added this wait so that user can see voice recognised during test execution
        Thread.sleep(5000L);
        Assert.assertEquals(searchText, googleSearchPage.getVoiceSearchText().toLowerCase());
        GoogleSearchResultsPage googleSearchResultsPage = new GoogleSearchResultsPage(driver);
        googleSearchResultsPage.show();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @AfterClass
    public void deallocate() {
        VoiceUtil.deallocate();
    }
}
