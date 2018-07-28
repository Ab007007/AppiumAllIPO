package com.allipo.test;
import java.io.IOException;
import java.util.List;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.allipo.page.AllIPOHomePage;
import com.allipo.page.IPODetailPage;
import com.allipo.page.InitialScreenPage;
import com.allipo.utils.DriverUtils;
import com.allipo.utils.ExtentReportFactory;
import com.allipo.utils.FileSystem;
import com.allipo.utils.Log;
import com.allipo.utils.Screenshots;
import com.allipo.utils.ScrollScreen;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ValidateIPODetailDownloadDoc extends DriverUtils {

	AndroidDriver driver = null;
	InitialScreenPage iSp = null;
	AllIPOHomePage ipo = null;
	IPODetailPage ipoDetails = null;
	
	@BeforeClass
 	public void preConfig(){
 		Log.configureReport();
 		Log.startReport("setup");
 		driver = allIPOCapsWithPermission();
 		
 		Log.info("Creating Page Objects");
		iSp = new InitialScreenPage(driver);
		ipo = new AllIPOHomePage(driver);
		ipoDetails = new IPODetailPage(driver);
 	}
	
	
	@DataProvider(name="DownloadDocDataProvider")
	public  Object[][] downDocDataProvider() throws InterruptedException
	{	
		Log.info("---Running Download report in IPO details test---");
		iSp.clickOnNextButton();
		iSp.clickOnNextButton();
		iSp.clickOnGoogleLogin();
		iSp.selectFirstAccount();
		Thread.sleep(15000);
		ipo.validateHomeScreen();
		ipo.selectFirstIPO();
		Thread.sleep(40000);
		
		for(int i=0;i<4;i++)
		{
			Log.info("Scrolling Up in IPO detail page");
			ScrollScreen.scrollScreenUp(driver);
		}
		
		List<AndroidElement> downloadImgs = driver.findElementsByXPath("//android.widget.ImageView[@index='0']");
		Log.info("Number of downlaod Img "+Integer.toString(downloadImgs.size()));
		
		AndroidElement downlaodImg[][]= new AndroidElement[downloadImgs.size()][1];
		for(int i=0;i<downloadImgs.size();i++)
		{
			downlaodImg[i][0]=downloadImgs.get(i);
		}
		return downlaodImg;
	}
	
	@Test(groups={"IPO"},dataProvider="DownloadDocDataProvider")
	public void validateDownloadDocInIPODetailPage(AndroidElement downlaodImg) throws InterruptedException
	{
 		ipoDetails.validateIPODetailDownloadDoc(downlaodImg);
 		
	}
	
	@AfterMethod
	public void repostGen(ITestResult testResult) throws IOException {
		
		Log.info("inside after method with " +testResult.getStatus());
		Log.info(String.valueOf(ITestResult.FAILURE));
			if (testResult.getStatus() == ITestResult.FAILURE) {
				String path = Screenshots.takeScreenshot(driver, testResult.getName()+ExtentReportFactory.getCurrentDateAndTime());
				Log.info("Path " + path);
				Log.ssPath.add(path);
				Log.attachScreenShot(path);
			}
		}
		
		@AfterClass
		public void tearDown()
		{
			Log.endReport();
			Log.info("Closing the App");
			driver.closeApp();
		}
	}
