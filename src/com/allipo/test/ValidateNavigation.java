package com.allipo.test;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.allipo.page.AllIPOHomePage;
import com.allipo.page.InitialScreenPage;
import com.allipo.page.NavigationPage;
import com.allipo.utils.AfterMethodUtils;
import com.allipo.utils.DriverUtils;
import com.allipo.utils.Log;

import io.appium.java_client.android.AndroidDriver;

public class ValidateNavigation {
	AndroidDriver driver = null;
	InitialScreenPage iSp = null;
	AllIPOHomePage ipo = null;
	NavigationPage np=null;
		
	@BeforeMethod
 	public void preConfig(){
 		Log.configureReport();
 		Log.startReport("setup");
 		driver = DriverUtils.allIPOCapsWithPermission();
		//Create Page Objects
 		
		iSp = new InitialScreenPage(driver);
		ipo = new AllIPOHomePage(driver);
		np = new NavigationPage(driver);
		
 	}
	@Test(groups={"smoke"},testName="IPO List Page")
	public void validateNavigationPage() throws InterruptedException{
		Log.info("Running Navigation options page test.");
		iSp.clickOnNextButton();
		iSp.clickOnNextButton();
		iSp.clickOnGoogleLogin();
		iSp.selectFirstAccount();
 		ipo.validateHomeScreen();
 		np.openNavigationAndVrfOptions();
 		
	}
	
	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {
		AfterMethodUtils.afterMethod(testResult, driver);
		Log.endReport();
		driver.closeApp();
	}
}

