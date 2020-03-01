package com.automation.tests.day1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class myfirstSeleniumScript {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        ChromeDriver driver =new ChromeDriver();
        driver.get("hhtp://google.com");
    }
}
