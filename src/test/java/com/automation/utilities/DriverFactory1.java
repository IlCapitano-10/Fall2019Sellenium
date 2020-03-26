package com.automation.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory1 {
    public static WebDriver newDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}