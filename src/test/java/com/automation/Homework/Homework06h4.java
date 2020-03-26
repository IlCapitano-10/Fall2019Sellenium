package com.automation.Homework;

import com.automation.utilities.DriverFactory1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Homework06h4 {
    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory1.newDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.w3schools.com/");
    }

    @Test
    public void test(){
        List<WebElement> allATags = driver.findElements(By.tagName("a"));
        System.out.println(allATags.size());
        allATags.forEach(each-> System.out.println(each.isDisplayed()? each.getText() +"\n"+ each.getAttribute("href"):""));
    }

    @AfterMethod
    public void teardown(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}