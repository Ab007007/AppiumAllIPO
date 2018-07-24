package com.allipo.test;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.allipo.page.AllIPOHomePage;
import com.allipo.page.IPODetailPage;
import com.allipo.page.InitialScreenPage;
import com.allipo.utils.DriverUtils;
import com.allipo.utils.ExtentReportFactory;
import com.allipo.utils.Log;
import com.allipo.utils.Screenshots;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ValidateIPOListPageTest extends DriverUtils {

AndroidDriver driver = null;
	InitialScreenPage iSp = null;
	AllIPOHomePage ipo = null;
		
	@BeforeClass
 	public void preConfig(){
 		Log.configureReport();
 		Log.startReport("setup");
 		driver = allIPOCaps();
		//Create Page Objects
 		
		iSp = new InitialScreenPage(driver);
		ipo = new AllIPOHomePage(driver);
		
 	}
	@Test(groups={"smoke"})
	public void validateLoginTest() throws InterruptedException{
		//Test Logic
		Log.info("--Completeds Executing Test - validateLoginTest");
		Thread.sleep(10000);
		iSp.clickOnNextButton();
		iSp.clickOnNextButton();
		iSp.allowAllNotificaiton();
		iSp.clickOnGoogleLogin();
		iSp.selectFirstAccount();
		Thread.sleep(15000);
 		ipo.validateHomeScreen();
 		ipo.verifyIPOListPage();
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
	

}