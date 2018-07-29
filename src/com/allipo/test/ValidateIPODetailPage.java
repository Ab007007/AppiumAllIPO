package com.allipo.test;
import java.io.IOException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.allipo.page.AllIPOHomePage;
import com.allipo.page.IPODetailPage;
import com.allipo.page.InitialScreenPage;
import com.allipo.utils.AfterMethodUtils;
import com.allipo.utils.DriverUtils;
import com.allipo.utils.ExtentReportFactory;
import com.allipo.utils.Log;
import com.allipo.utils.Screenshots;

import io.appium.java_client.android.AndroidDriver;

public class ValidateIPODetailPage extends DriverUtils {

	AndroidDriver driver = null;
	InitialScreenPage iSp = null;
	AllIPOHomePage ipo = null;
	IPODetailPage ipoDetails = null;
	
	@BeforeMethod
 	public void preConfig(){
 		Log.configureReport();
 		Log.startReport("setup");
 		driver = allIPOCapsWithPermission();
 		
 		Log.info("Creating Page Objects");
		iSp = new InitialScreenPage(driver);
		ipo = new AllIPOHomePage(driver);
		ipoDetails = new IPODetailPage(driver);
 	}
	@Test(groups={"IPO"},testName="IPO Detail Page")
	public void validateIPODetailPage() throws InterruptedException{
		
		Log.info("Running IPO detail page test.");
		iSp.clickOnNextButton();
		iSp.clickOnNextButton();
		iSp.clickOnGoogleLogin();
		iSp.selectFirstAccount();
 		ipo.validateHomeScreen();
 		ipo.selectFirstIPO();
 		Log.info("Waiting for IPO detail page values to load");
 		Thread.sleep(60000);
 		ipoDetails.validateIPODetails();
	}
	
	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {
		AfterMethodUtils.afterMethod(testResult, driver);
		
		Log.endReport();
		driver.closeApp();
	}
	
	

}