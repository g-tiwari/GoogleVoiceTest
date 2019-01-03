package com.se2automate.google.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * created by Gaurav Tiwari
 */
public class GoogleSearchPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By microphoneID = By.className("voice_search_button");
    private By enlargedMicroPhoneId = By.id("spchb");

    private By searchBoxLocator = By.name("q");
    private By searchBtnLocator = By.name("btnG");

    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public void searchFor(String searchString) {
        WebElement searchBox = driver.findElement(searchBoxLocator);
        searchBox.clear();

        //Google makes ajax calls during search
        int length = searchString.length();
        searchBox.sendKeys(searchString.substring(0, length - 1));
        searchBox.sendKeys(searchString.substring(length - 1));
    }

    public void search() {
        WebElement searchButton = driver.findElement(searchBtnLocator);
        searchButton.click();
    }

    public void startListening() {
        //wait for microphone
        WebElement microphone = wait.until(ExpectedConditions.presenceOfElementLocated(microphoneID));
        microphone.click();
        //wait for big microphone image to appear
        //this is when google starts listening
        wait.until(ExpectedConditions.presenceOfElementLocated(enlargedMicroPhoneId));
    }

    public void stopListening() {
        //wait for the microphone image to hide
        //at this point google will stop listening & start its search
        wait.until(ExpectedConditions.invisibilityOfElementLocated(enlargedMicroPhoneId));
    }

    public String getVoiceSearchText() {
        //get the value from the search text box
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBoxLocator));
        return searchBox.getAttribute("value");
    }


}
