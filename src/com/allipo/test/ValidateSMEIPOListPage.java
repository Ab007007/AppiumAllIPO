package com.allipo.test;
import java.io.IOException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.allipo.page.AllIPOHomePage;
import com.allipo.page.InitialScreenPage;
import com.allipo.utils.AfterMethodUtils;
import com.allipo.utils.DriverUtils;
import com.allipo.utils.ExtentReportFactory;
import com.allipo.utils.Log;
import com.allipo.utils.Screenshots;

import io.appium.java_client.android.AndroidDriver;

public class ValidateSMEIPOListPage extends DriverUtils {

AndroidDriver driver = null;
	InitialScreenPage iSp = null;
	AllIPOHomePage ipo = null;
		
	@BeforeMethod
 	public void preConfig(){
 		Log.configureReport();
 		Log.startReport("validateSMEIPOListPage");
 		driver = allIPOCapsWithPermission();
		//Create Page Objects
 		
		iSp = new InitialScreenPage(driver);
		ipo = new AllIPOHomePage(driver);
		
 	}
	@Test(groups={"smoke"},testName="IPO List Page")
	public void validateSMEIPOListPage() throws InterruptedException{
		Log.info("Running SME IPO list page test.");
		iSp.clickOnNextButton();
		iSp.clickOnNextButton();
		iSp.clickOnGoogleLogin();
		iSp.selectFirstAccount();
 		ipo.validateHomeScreen();
 		iSp.selectSMETab();
 		ipo.verifyIPOListPage("SME");
	}
	
	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {
		AfterMethodUtils.afterMethod(testResult, driver);
		Log.endReport();
		driver.closeApp();
	}
}