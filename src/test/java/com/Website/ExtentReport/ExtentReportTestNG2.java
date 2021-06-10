package com.Website.ExtentReport;

/*
 * 
 * https://medium.com/test-automation-hub/how-to-use-extent-reports-with-testng-in-selenium-java-c892cf256737
 * This code will go to https://www.ebay.com, validate the site by comparing Title and enter “mobile” in the search box and hit enter.
 * This code will go to https://www.ebay.com, validate the site by comparing Title and enter “mobile” in the search box and hit enter.
 * Create extentReport with the name extentReport.html in specific folder called extent-reports under the main Project
 * 
 * @BeforeSuite — Initializing ExtentSparkReporter and ChromeDriver
   @Test — 2 tests 
   1) Go to Daraz.lk and the Title will be checked with the actual title. Then it will insert “mobile” inside the textbox and hit enter.”
   2) Go to ebay.com and the Title will be checked with the actual title. Then it will insert “mobile” inside the textbox and hit enter.”
   @AfterSuite — In here browser will be closed and flush()
   Create extentReport with the name extendReportTNG2.html in specific folder called extent-reportsTNG under the main Project and display both tests in the report
 */

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportTestNG2 
{

	//creating driver object
	 private static WebDriver driver = null;
	 //setting expected title from the ebay.com and for Daraz.lk
	 static String expectedTitleebay ="Electronics, Cars, Fashion, Collectibles & More | eBay";
	 static String expectedTitledaraz ="Online Shopping Sri Lanka: Clothes, Electronics & Phones | Daraz.lk";
	//create the htmlReporter object
	 ExtentSparkReporter htmlReporter;
	 ExtentReports extent;
	 ExtentTest test1,test2;

	 @BeforeSuite
	 public void setup() {
	 htmlReporter = new ExtentSparkReporter("./extend-reportsTNG/extendReportTNG2.html");
	//create ExtentReports and attach reporter(s)
	 extent = new ExtentReports();
	 extent.attachReporter(htmlReporter);
	 extent.setSystemInfo("Tester", "Gayathri");
	  
	  //initializing and starting the browser
	  WebDriverManager.chromedriver().setup();
	  driver = new ChromeDriver();
	 
	 }
	 
	 
	 @Test (priority = 1)
	 public void test1() throws InterruptedException {
	//creates a toggle for the given test, add all log events under it
	  test1 = extent.createTest("ebay Search Test", "test to validate search box ");//Give name of test,description of test
	  test1.log(Status.INFO, "Starting test case");
	  
	  //maximize the window 
	  driver.manage().window().maximize();
	  test1.pass("maximize has done");
	//Navigate to Ebay.com
	  driver.get("https://www.ebay.com");
	  Thread.sleep(1000);
	  test1.pass("Navigate to Ebay.com");
	//compare whether the title id matching
	  String actualTitle = driver.getTitle();
	  Assert.assertEquals(actualTitle, expectedTitleebay);
	  test1.pass("title is correct");
	//enter in the TextBox
	  driver.findElement(By.xpath("//*[@id=\"gh-ac\"]")).sendKeys("Mobile");
	  test1.pass("Entered text in the text box");
	  //hit enter
	  driver.findElement(By.xpath("//*[@id=\"gh-btn\"]")).sendKeys(Keys.RETURN); 
	  test1.pass("Press keyboard enter key");
	 }
	 
	 @Test (priority = 0) //this test will be executed first
	 public void test2() throws InterruptedException {
	//creates a toggle for the given test, add all log events under it
	  test2 = extent.createTest("Daraz Search Test", "test to validate search box ");
	  test2.log(Status.INFO, "Starting test case");
	//maximize the window 
	  driver.manage().window().maximize();
	  test2.pass("maximize has done");
	  
	  //Navigate to daraz.lk
	  driver.get("https://www.daraz.lk/#");
	  Thread.sleep(1000);
	  test2.pass("Navigate to Daraz.lk");
	//compare whether the title id matching
	  String actualTitle = driver.getTitle();
	  Assert.assertEquals(actualTitle, expectedTitledaraz);
	  test2.pass("title is correct");
	//enter in the TextBox
	  driver.findElement(By.xpath("//*[@id=\"q\"]")).sendKeys("Mobile");
	  test2.pass("Entered thex in the text box");
	  //hit enter
	  driver.findElement(By.xpath("//*[@id=\"topActionHeader\"]/div/div[2]/div/div[2]/form/div/div[2]/button")).sendKeys(Keys.RETURN); 
	  test2.pass("Press keyboard enter key");
	  
	 }
	 
	 @AfterSuite
	 public void tearDown() {
	driver.quit();
	test1.log(Status.INFO, "Done!");
	test1.pass("closed the browser");
	test2.pass("closed the browser");
	test1.info("test completed");
	test2.info("test completed");
	
	  
	  //write results into the html file
	  extent.flush();
	}
	 
	 
}
