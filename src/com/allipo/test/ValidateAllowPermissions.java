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
import com.allipo.utils.Log;
import com.allipo.utils.AppInteractionUtils;
import io.appium.java_client.android.AndroidDriver;

public class ValidateAllowPermissions extends DriverUtils {

	AndroidDriver driver = null;
	InitialScreenPage iSp = null;
	AllIPOHomePage ipo = null;
	IPODetailPage ipoDetails = null;
	
	@BeforeMethod
 	public void preConfig(){
 		Log.configureReport();
 		Log.startReport("validateLoginPermissionTest");
 		driver = allIPOCaps();
		//Create Page Objects
 		
		iSp = new InitialScreenPage(driver);
		ipo = new AllIPOHomePage(driver);
		ipoDetails = new IPODetailPage(driver);
 	}
	@Test(groups={"IPO"},testName="Allow Permission Test")
	public void validateLoginPermissionTest() throws InterruptedException{
		Log.info("Running Allow Permissions test");
		iSp.clickOnNextButton();
		iSp.clickOnNextButton();
		Thread.sleep(5000);
		iSp.allowAllNotificaiton();
		
		AppInteractionUtils.makeAppBkAndFrGround(driver);
		iSp.clickOnGoogleLogin();
		iSp.selectFirstAccount();
 		ipo.validateHomeScreen();
		
	}
	
	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {
		AfterMethodUtils.afterMethod(testResult, driver);
		Log.endReport();
		driver.closeApp();
	}
}