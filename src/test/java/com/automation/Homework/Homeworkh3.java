package com.automation.Homework;
import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Homeworkh3 {
    private WebDriver driver;
    private String URL = "https://practice-cybertekschool.herokuapp.com/registration_form";

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
    }

    /*
    Test Case #1
     */
    @Test
    public void test1(){
        driver.get(URL);
        driver.manage().window().maximize();

        WebElement birthday =driver.findElement(By.name("birthday"));
        birthday.sendKeys("wrong_dob");
        WebElement invalidBtdyMessage=driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[8]/div/small[2]"));
        Assert.assertTrue(invalidBtdyMessage.isDisplayed());
    }

    /*
   Test Case #2
     */
    @Test
    public void test2(){
        driver.get(URL);
        driver.manage().window().maximize();
        WebElement cPlusPlus=driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[11]/div/div[1]/label"));
        WebElement java = driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[11]/div/div[2]/label"));
        WebElement javaScript= driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[11]/div/div[3]/label"));
        Assert.assertTrue(cPlusPlus.isDisplayed());
        Assert.assertTrue(java.isDisplayed());
        Assert.assertTrue(javaScript.isDisplayed());
        BrowserUtils.wait(2);
    }

    /*
  Test Case #3
     */
    @Test
    public void test3(){
        driver.get(URL);
        driver.manage().window().maximize();
        WebElement firstname =driver.findElement(By.name("firstname"));
        firstname.sendKeys("t");
        WebElement invalidFNameMessage=driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[1]/div/small[2]"));
        Assert.assertTrue(invalidFNameMessage.isDisplayed());
    }

    /*
    Test case #4
        */
    @Test
    public void test4(){
        driver.get(URL);
        driver.manage().window().maximize();
        WebElement lastName =driver.findElement(By.name("lastname"));
        lastName.sendKeys("t");
        WebElement invalidLNameMessage=driver.findElement(By.xpath("//*[@id=\"registrationForm\"]/div[2]/div/small[2]"));
        Assert.assertTrue(invalidLNameMessage.isDisplayed());
    }

    /*
    Test Case #5
     */
    @Test
    public void test5() {
        driver.get(URL);
        driver.manage().window().maximize();
        driver.findElement(By.name("firstname")).sendKeys("astrit");
        driver.findElement(By.name("lastname")).sendKeys("mah");
        driver.findElement(By.name("username")).sendKeys("astritm");
        driver.findElement(By.name("email")).sendKeys("m_astrit@cyber.com");
        driver.findElement(By.name("password")).sendKeys("1112223Am");
        driver.findElement(By.name("phone")).sendKeys("123-456-7890");
        driver.findElement(By.cssSelector("input[value='male']")).click();
        driver.findElement(By.name("birthday")).sendKeys("04/21/2000");
        Select departmentSelect = new Select(driver.findElement(By.name("department")));
        departmentSelect.selectByVisibleText("MPDC");
        Select jobTitleSelect = new Select(driver.findElement(By.name("job_title")));
        jobTitleSelect.selectByVisibleText("SDET");
        driver.findElement(By.xpath("//label[text()='Java']/preceding-sibling::input")).click();
        driver.findElement(By.id("wooden_spoon")).click();

        BrowserUtils.wait(4);

        String expected = "You've successfully completed registration!";
        String actual = driver.findElement(By.tagName("p")).getText();
        BrowserUtils.wait(4);

        Assert.assertEquals(actual, expected);
    }
    /*
   Test Case #6
    */
    @Test

    public void test6() {

        driver.get("https://www.tempmailaddress.com/");
        driver.manage().window().maximize();
        BrowserUtils.wait(3);
        String emailAddress = driver.findElement(By.id("email")).getText();
        driver.navigate().to("http://practice.cybertekschool.com/");
        BrowserUtils.wait(5);
        WebElement signUpForMailingList = driver.findElement(By.linkText("Sign Up For Mailing List"));
        signUpForMailingList.click();
        BrowserUtils.wait(2);
        WebElement fullName = driver.findElement(By.name("full_name"));
        fullName.sendKeys("Francesco Totti");
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys(emailAddress);
        WebElement signupBtn = driver.findElement(By.name("wooden_spoon"));
        signupBtn.click();
        WebElement signupMessage = driver.findElement(By.name("signup_message"));
        Assert.assertTrue(signupMessage.isDisplayed());
        driver.navigate().back();
        driver.navigate().back();
        BrowserUtils.wait(5);
        driver.navigate().back();
        BrowserUtils.wait(5);
        WebElement emailAd = driver.findElement(By.xpath("//table/tbody/tr[1]/td"));
        System.out.println("displayed :" + emailAd.isDisplayed());
        System.out.println("emailAd :" + emailAd.getText());
        String displayedEmail = emailAd.getText().trim();
        Assert.assertEquals(displayedEmail, "do-not-reply@practice.cybertekschool.com");
        emailAd.click();
        BrowserUtils.wait(3);
        WebElement from= driver.findElement(By.id("odesilatel"));
        String expectedFrom= "do-not-reply@practice.cybertekschool.com";
        String actualFrom = from.getText();
        Assert.assertEquals(actualFrom,expectedFrom);
        WebElement subject = driver.findElement(By.id("predmet"));
        String expectedSubject= "Thanks for subscribing to practice.cybertekschool.com!";
        String actualSubject=subject.getText();
        Assert.assertEquals(actualSubject,expectedSubject);
    }
    /*
    Test Case #7
     */
    @Test
    public void test7(){
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("File Upload")).click();
        BrowserUtils.wait(2);
        WebElement choseFile=driver.findElement(By.id("file-upload"));
        choseFile.sendKeys("C:/Users/Il Capitano/Documents/Poly.txt");
        BrowserUtils.wait(2);
        driver.findElement(By.id("file-submit")).click();
        WebElement fileUploadedMessage=driver.findElement(By.xpath("//*[.='File Uploaded!']"));
        String expected= "File Uploaded!";
        String actual=fileUploadedMessage.getText();
        Assert.assertEquals(actual,expected);

        WebElement uploadedFileName = driver.findElement(By.id("uploaded-files"));

        Assert.assertTrue(uploadedFileName.isDisplayed());
    }

    /*
    Test Case #8
     */
    @Test
    public void test8(){
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("Autocomplete")).click();
        driver.findElement(By.id("myCountry")).sendKeys("United States of America");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/input")).click();
        Assert.assertTrue(driver.findElement(By.id("result")).isDisplayed());
    }
    /*
        Test Case #9
         */
    @Test
    public void test9(){
        driver.get("https://practice-cybertekschool.herokuapp.com");
       driver.findElement(By.linkText("Status Codes")).click();
       driver.findElement(By.linkText("200")).click();
      driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/input")).click();
       Assert.assertTrue(driver.findElement(By.id("This page returned a 200 status code")).isDisplayed());
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}