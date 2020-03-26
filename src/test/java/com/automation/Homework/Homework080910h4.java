package com.automation.Homework;

import com.automation.utilities.BrowUtill;
import com.automation.utilities.DriverFactory1;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Homework080910h4 {

    private WebDriver driver;
    private int random ;


    @BeforeMethod
    public void setup(){
        driver = DriverFactory1.newDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon",Keys.ENTER);
    }


    @Test
    public void cart(){
        List<WebElement> allItems = driver.findElements(By.xpath("(//div[@class='a-section a-spacing-medium'])"));
        random = BrowUtill.getRandom(allItems.size());

        String xpath_forItems= "(//div[@class='a-section a-spacing-medium'])["+random+"]";
        String xpath_forItems_price = "(//div[@class='a-section a-spacing-medium'])["+random+"]//span[@class='a-price-whole']";
        String xpath_forItems_price2 = "(//div[@class='a-section a-spacing-medium'])["+random+"]//span[@class='a-price-fraction']";
        String xpath_forItems_name ="(//div[@class='a-section a-spacing-medium'])["+random+"]//span[@class='a-size-base-plus a-color-base a-text-normal']";
        String priceOfRandomItem = driver.findElement(By.xpath(xpath_forItems_price)).getText()+"."+driver.findElement(By.xpath(xpath_forItems_price2)).getText();
        String nameOfRandomItem = driver.findElement(By.xpath(xpath_forItems_name)).getText();

        WebElement theRandomItem = driver.findElement(By.xpath(xpath_forItems));
        theRandomItem.click();

        String quantity = driver.findElement(By.xpath("//span[@id='a-autoid-0-announce']//span[@class='a-dropdown-prompt']")).getText();

        Assert.assertEquals(quantity,"1");

        String priceOfRandomItemInOwnPage = driver.findElement(By.xpath("//span[contains(@id,'priceblock')]")).getText();
        String nameOfRandomItemInOwnPage = driver.findElement(By.id("productTitle")).getText();
        Assert.assertTrue(priceOfRandomItemInOwnPage.contains(priceOfRandomItem));
        Assert.assertEquals(nameOfRandomItem,nameOfRandomItemInOwnPage);
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Add to Cart']")).isDisplayed());
    }


    @Test
    public void prime(){

        String xpath_for_theFirst_prime_item_name = "(//i[@aria-label=\"Amazon Prime\"])[1]//ancestor::div//h2";
        String firstPrimeItemsName = driver.findElement(By.xpath(xpath_for_theFirst_prime_item_name)).getText();
         driver.findElement(By.xpath("//li[@aria-label=\"Prime Eligible\"]//i[@class='a-icon a-icon-checkbox']")).click();
             String fistNameAfterSelectPrime = driver.findElement(By.xpath("(//div//h2)[1]")).getText();
        Assert.assertEquals(firstPrimeItemsName,fistNameAfterSelectPrime);
            List<WebElement> allCheckBoxesInBrand = driver.findElements(By.xpath("//div[@id='brandsRefinements']//li//label"));
        allCheckBoxesInBrand.get(allCheckBoxesInBrand.size()-1).click();
        BrowUtill.wait(2);
        String afterClickingBrandFirstItem = driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[1]")).getText();
              Assert.assertNotEquals(afterClickingBrandFirstItem, fistNameAfterSelectPrime);

    }


    @Test
    public void moreSpoons(){

        List<WebElement> allNamesInBrand = driver.findElements(By.xpath("//div[@id='brandsRefinements']//li"));
        List<String> allNamesBeforeClickingPrime = new ArrayList<>();
        allNamesInBrand.forEach(each-> allNamesBeforeClickingPrime.add(each.getText()));
        System.out.println(allNamesBeforeClickingPrime);
        driver.findElement(By.xpath("//li[@aria-label=\"Prime Eligible\"]//i[@class='a-icon a-icon-checkbox']")).click();
        List<WebElement> allNamesInBrandAfter = driver.findElements(By.xpath("//div[@id='brandsRefinements']//li"));
        List<String> allNamesAfterClickingPrime = new ArrayList<>();
        allNamesInBrandAfter.forEach(each-> allNamesAfterClickingPrime.add(each.getText()));
        System.out.println(allNamesAfterClickingPrime);
        Assert.assertEquals(allNamesBeforeClickingPrime,allNamesAfterClickingPrime);
    }


    @Test
    public void cheapSpoons(){


        driver.findElement(By.xpath("(//div[@id='priceRefinements']//span[@class='a-size-base a-color-base'])[1]")).click();

        BrowUtill.wait(10);
        List<WebElement> allPrice = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
        System.out.println(allPrice.size());
        for (WebElement each:allPrice) {
            System.out.println(each.getText());
            int price = Integer.parseInt(each.getText());
            Assert.assertTrue(price<25);
        }
    }


    @AfterMethod
    public void teardown(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
}