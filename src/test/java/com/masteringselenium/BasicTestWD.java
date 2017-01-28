package com.masteringselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class BasicTestWD extends DriverFactory {

    private void searchInGoogleFor(String searchValue) throws Exception{

        WebDriver driver = DriverFactory.getDriver();

        driver.get("http://www.google.com");

        WebElement searchField = driver.findElement(By.name("q"));

        searchField.clear();

        searchField.sendKeys(searchValue);

        System.out.println("Page title is: " + driver.getTitle());

        searchField.submit();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {

        public Boolean apply(WebDriver driverObject){

            return driverObject.getTitle().toLowerCase().startsWith(searchValue.toLowerCase());
        }


        });

        System.out.println("Page title is: " + driver.getTitle());



    }


    @Test
    public void googleCheeseExample() throws Exception{

        searchInGoogleFor("Cheese");
    }

    @Test
    public void googleWineExample() throws Exception{

        searchInGoogleFor("Wine");
    }

    @Test
    public void googleMilkExample() throws Exception{

        searchInGoogleFor("Milk");
    }

    @Test
    public void googleCupExample() throws Exception{

        searchInGoogleFor("Cup");
    }


}
