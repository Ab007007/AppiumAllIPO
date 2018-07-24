package com.allipo.test;

import java.io.IOException;


import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.allipo.utils.DriverUtils;
import com.allipo.utils.ExtentReportFactory;
import com.allipo.utils.Log;
import com.allipo.utils.Screenshots;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ValidateListPageTest {

	AndroidDriver driver = null;
	
	@BeforeClass
 	public void preConfig(){
 		Log.configureReport();
 		Log.startReport("setup");
 		//driver = DriverUtils.getWebDriver();
		//Create Page Objects
		
 	}
	@Test(groups={"smoke"})
	public void validateLoginTest(){
		//Test Logic
		Log.info("--Completeds Executing Test - validateLoginTest");
 		//Log.endReport("validateLoginTest");
	}
	
	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {
		Log.info("inside after method with " +testResult.getStatus());
		Log.info(String.valueOf(ITestResult.FAILURE));
		if (testResult.getStatus() == ITestResult.FAILURE) {
			String path = Screenshots.takeScreenshot(driver, testResult.getName()+ExtentReportFactory.getCurrentDateAndTime());
			Log.info("Path " + path);
			Log.ssPath.add(path);
			Log.attachScreenShot(path);
		}
		
		Log.endReport();
	}
	
	
	@AfterClass
	public void tearDown(){
		
			if(driver!=null){
					driver.close();
					driver = null;
		
		}
	}
}
