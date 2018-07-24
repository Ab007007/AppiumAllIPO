package com.allipo.test;
import java.net.MalformedURLException;

import com.allipo.utils.DriverUtils;
import com.allipo.utils.Log;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class LoginToAllipo extends DriverUtils {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		AndroidDriver<AndroidElement> driver = allIPOCaps();
		
		Thread.sleep(10000);
		//waitForElementVisible(driver,driver.findElementById("com.android.packageinstaller:id/permission_allow_button"),120);
		System.out.println("clicking on next");
		driver.findElementById("com.appbootup.ipo.news:id/btn_next").click();
		Thread.sleep(2000);
		System.out.println("clicking on Got It..");
		driver.findElementById("com.appbootup.ipo.news:id/btn_next").click();
		Thread.sleep(2000);
		allowAppPermission(driver);
		Thread.sleep(2000);
		driver.findElementByAccessibilityId("Google Login").click();
		//act.tap(driver.findElementByXPath("//android.widget.ImageView[@id='com.appbootup.ipo.news:id/imgGoogleLogin']")).perform();
		System.out.println("no element found");
		System.out.println("Clicking on Google button");
	
		Thread.sleep(2000);
//		driver.findElementByAndroidUIAutomator(
//				"new UiScrollable(new UiSelector().resourceId(\"android:id/content\")).getChildByText(new UiSelector().className(\"android.widget.TextView\"), \"Raj Shekar\")").click();
//	
		driver.findElementByXPath("//android.widget.LinearLayout[@index='0']").click();
		Thread.sleep(15000);
		System.out.println();
		verifyIPOListPage(driver);
		System.out.println("---END---");
	}
	
	
	

	
}