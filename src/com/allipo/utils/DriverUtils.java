package com.allipo.utils;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverUtils {

	public static AndroidDriver<AndroidElement> allIPOCaps()  {

		AndroidDriver driver = null;
		// TODO Auto-generated method stub
		// File app = new File(appName);
		DesiredCapabilities capabilities = new DesiredCapabilities();

		try{
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");
			// capabilities.setCapability(MobileCapabilityType.APP,
			// app.getAbsolutePath());
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "50000");
			// capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,
			// "true");
			capabilities.setCapability("appPackage", "com.appbootup.ipo.news");
			capabilities.setCapability("appActivity", "com.appbootup.ipo.news.ActivitySplashScreen");
			// Log.info(app.getAbsolutePath());
			driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		}
		catch(Exception ex){
			Log.info("---Failed to create Driver---\n"
					+"Possible reason could be appium server is NOT up, please start appium server"
					+ex.getMessage());
		}
		
			return driver;
	}

	public static AndroidDriver<AndroidElement> allIPOCapsWithPermission()  {

		AndroidDriver driver = null;
		// TODO Auto-generated method stub
		// File app = new File(appName);
		DesiredCapabilities capabilities = new DesiredCapabilities();

		try{
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");
			// capabilities.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "50000");
			capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,"true");
			capabilities.setCapability("appPackage", "com.appbootup.ipo.news");
			capabilities.setCapability("appActivity", "com.appbootup.ipo.news.ActivitySplashScreen");
			// Log.info(app.getAbsolutePath());
			driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		}
		catch(Exception ex){
			Log.info("---Failed to create Driver---\n"+ex.getMessage());
		}
		
			return driver;
	}
	
	public static WebElement getVisibleElement(AndroidDriver driver, WebElement ele) {
		//	Log.info("Getting element By " + type  + "-" + value);
			WebDriverWait wait = null;
			try{
				wait = new WebDriverWait(driver, 120);
				wait.until(ExpectedConditions.visibilityOf(ele));
			}catch(Exception ex){
				Log.info("----getVisiblieElement---" + ex.getMessage());
				Log.writeToFailFile(ex.toString());
			}
			return ele;	
			
		}
	
	public static void waitForElementVisibleAndClick(AndroidDriver driver, AndroidElement locator, int seconds) {
		Log.info("--- waiting for element to be visible for " + seconds + " seconds");

		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOf(locator));
		Log.info("Clicking on element");
		locator.click();
	}

	public static void verifyIPOListPage(AndroidDriver<AndroidElement> driver) throws InterruptedException {
		Log.info("--- Validating the details Page ---");
		List<AndroidElement> listOfIPOs = driver.findElementsById("com.appbootup.ipo.news:id/ipo_title");
		Log.info("---Total number of ipo's displayed on screen " + listOfIPOs.size());
		String title, date, shortnotes;
		for(int i=1;i<listOfIPOs.size();i++) {

			title = null;
			date = null;
			shortnotes = null;
			AndroidElement frameElement = driver.findElementByXPath("//android.widget.FrameLayout[@index='" + i + "']");

			title = frameElement.findElementById("com.appbootup.ipo.news:id/ipo_title").getText();
			date = frameElement.findElementById("com.appbootup.ipo.news:id/date").getText();
			shortnotes = frameElement.findElementById("com.appbootup.ipo.news:id/short_content").getText();
			Thread.sleep(2000);
			driver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + title + "\"))");

			Log.info( title + date + shortnotes);
			i++;
		}

	}

	public static void allowAppPermission(AndroidDriver driver) throws InterruptedException {
		//getVisibleElement(driver, driver.findElement(MobileBy.xpath("//*[@class='android.widget.Button'][2]")));
		Thread.sleep(5000);
		for (int i = 0; i < 4; i++) {
			driver.findElement(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).click();
			Thread.sleep(1000);
		}
		Log.info("ended");
	}
	
	
	public static void validateHomePage(AndroidDriver driver){
		Log.info("--- Validating Home Page---");
		if(driver.findElementByXPath("//android.widget.TextView[@text='ALL IPO News']").getText().equals("ALL IPO News")){
			Assert.assertTrue(true, "Test PAssed ");
			Log.pass("Test Passed With Home Screen Validation");
		}else
		{
			Log.fail("Home Screen not displayed " );
		}
	}
}
