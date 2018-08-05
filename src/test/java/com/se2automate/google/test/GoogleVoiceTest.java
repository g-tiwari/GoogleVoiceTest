package com.se2automate.google.test;

import com.se2automate.google.pages.GoogleSearchPage;
import com.se2automate.google.pages.GoogleSearchResultsPage;
import com.se2automate.util.VoiceUtil;
import com.se2automate.voice.client.Language;
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
                {"football world cup live",Language.ENGLISH_INDIA},
                {"temperature in delhi",Language.ENGLISH_UK},
                {"Amazon stock price",Language.ENGLISH_US},
                {"Why Kattappa killed Bahubali",Language.ENGLISH_INDIA}
        };
    }

    @BeforeClass
    public void setup() {
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
    public void googleVoiceSearchTest(String searchText,Language language) throws InterruptedException {
        GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver);
        googleSearchPage.startListening();

        VoiceUtil.speak(searchText, language);

        googleSearchPage.stopListening();
        //added this wait so that user can see voice recognised during test execution
        Thread.sleep(5000L);
        Assert.assertEquals(searchText.toLowerCase(), googleSearchPage.getVoiceSearchText().toLowerCase());
        GoogleSearchResultsPage googleSearchResultsPage = new GoogleSearchResultsPage(driver);
        googleSearchResultsPage.show();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
