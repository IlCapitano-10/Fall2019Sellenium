package com.automation.Homework;

import com.automation.utilities.BrowUtill;
import com.automation.utilities.DriverFactory1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Homework07h4 {
    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = DriverFactory1.newDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.selenium.dev/documentation/en/");
    }

    @Test
    public void test(){
        List<WebElement> allATags = driver.findElements(By.tagName("a"));
        System.out.println(allATags.size());
        List<String> allLinks = new ArrayList<>();
        BrowUtill.wait(2);
        allATags.forEach(each-> allLinks.add(each.getAttribute("href")));
        System.out.println(allLinks);
        Assert.assertTrue(!allLinks.contains(null),"there is one invalid link");


    }

    @AfterMethod
    public void teardown(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}