package com.se2automate.google.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleSearchResultsPage {

    private WebDriver driver;
    private WebDriverWait wait;


    private By resultHeaderLocator = By.cssSelector("h3 a");
    private By resultTextLocator = By.cssSelector("span.st");
    private By resultsListLocator = By.cssSelector(".rc");

    public GoogleSearchResultsPage(WebDriver driver){
        this.driver =driver;
        wait = new WebDriverWait(driver,10);
    }

    public void show(){
        System.out.println("\nResults:\n");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(resultHeaderLocator));
        List<WebElement> results = driver.findElements(resultsListLocator);

        for(WebElement result: results)
            System.out.println(result.findElement(resultHeaderLocator).getText()+ "-->");
    }
}
