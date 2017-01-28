package com.masteringselenium;

import com.masteringselenium.config.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

import static com.masteringselenium.config.DriverType.CHROME;
import static com.masteringselenium.config.DriverType.FIREFOX;
import static com.masteringselenium.config.DriverType.valueOf;


public class WebDriverThread {

    private WebDriver webDriver;
    private DriverType selectedDriverType;

    private final DriverType defaultDriverType = CHROME;

    private final String browser = System.getProperty("browser").toUpperCase();
    private final String operationSystem = System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture = System.getProperty("os.arch").toUpperCase();


    public WebDriver getDriver() throws Exception{

        if(webDriver == null){
            selectedDriverType = determineEffectiveDriverType();
            DesiredCapabilities desiredCapabilities = selectedDriverType.getDesiredCapabilities();
            instantiateWebDriver(desiredCapabilities);
        }
        return webDriver;
    }

    private DriverType determineEffectiveDriverType(){
        DriverType driverType = defaultDriverType;
        try{
            driverType = valueOf(browser);
        }catch (IllegalStateException e){
            System.err.println("Wrong name");
        }catch (IllegalArgumentException e){
            System.err.println("Uknown driver specified");
        }catch (NullPointerException e){
            System.err.println("No driver specified");
        }
        return driverType;
    }

   private void  instantiateWebDriver(DesiredCapabilities desiredCapabilities) throws MalformedURLException{

       System.out.println("Current OS: " + operationSystem);
       System.out.println("Current Archetecture: " + systemArchitecture);
       System.out.println("Current Browser: " + browser);

       webDriver = selectedDriverType.getWebDriverObject(desiredCapabilities);
   }

    public void quitDriver(){
        if(webDriver != null){
            webDriver.quit();
            webDriver = null;
        }

    }

}
