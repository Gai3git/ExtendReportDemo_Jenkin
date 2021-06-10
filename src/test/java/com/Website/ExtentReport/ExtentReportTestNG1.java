package com.Website.ExtentReport;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportTestNG1
{
	ExtentReports extent;
	
	
	@BeforeTest
	public void ERconfig()
	{
		String path=System.getProperty("user.dir") + "\\extend-reportsTNG\\extendReportTNG1.html";
		//ExtentSparkReporter eReporter = new ExtentSparkReporter(path);
		ExtentSparkReporter eReporter = new ExtentSparkReporter("./extend-reportsTNG/extendReportTNG1.html");
		eReporter.config().setReportName("Web Automation Results");
		eReporter.config().setDocumentTitle("ExtendReport for TestNG1 class");
		
		extent = new ExtentReports();
		extent.attachReporter(eReporter);
		extent.setSystemInfo("Tester", "Gayathri");
	}
	
	@Test
	public void checktitle()
	{   ExtentTest test=null ;
		WebDriver driver = null;
		
		try {
		test =extent.createTest("ER TestNG1 Demo Result","Learn how extentreports looks"); //Give name of test,description of test
		System.setProperty("webdriver.chrome.driver","C:\\Drivers\\ChromeDriver\\chromedriver.exe");
		 driver = new ChromeDriver();
		driver.get("https://www.facebook.com");
		System.out.println(driver.getTitle());
		String actualTitle = driver.getTitle();
		String expectedTitlefb="Facebook - Log In or Sign Up";
		Assert.assertEquals(actualTitle, expectedTitlefb);
		}
		catch (Exception e)
		{}
		finally {
			//Using Extenttest status like test.fail,test.pass,test.info etc will create log events with the status, timestamp and corresponding message details


			/*
			 Status -fail   		-->Both test and log event will be marked in red .Tests passed =0;Tests failed=1
			 Status -pass    		-->Both test and log event will be marked in green.Tests passed =1;Tests failed=0
			 Status -info   		-->Test will be marked in green ,but log event will be marked in blue .Tests passed =1;Tests failed=0
			 Status -warning        -->Both test and log event will be marked in light Orange.Tests passed =0;Tests failed=0
			 Status -skip           -->Both test and log event will be marked in dark Orange.Tests passed =0;Tests failed=0
			 */
			test.info("Checking titles,Demo to display info log");
			//test.fail("Titles do not match,Demo to show failed on purpose. will display fail log");
			//test.pass("Titles are matching, Demo to show success on purpose.will display pass log "); 
			//test.warning("Titles may not be matching,Demo to show warning on purpose. will display warning log");
			//test.skip("Skip this step of Checking titles,Demo to display skip log");
			driver.close();
			extent.flush();
			
		}

		
	}

}
