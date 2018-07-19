package com.allipo;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.omg.PortableInterceptor.LOCATION_FORWARD;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gaana.base;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class LoginToAllipo extends base {

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
		TouchAction act = new TouchAction(driver);
		act.tap(710, 563).perform();
		System.out.println("no element found");
		System.out.println("Clicking on Google button");
	
		Thread.sleep(2000);
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"android:id/content\")).getChildByText(new UiSelector().className(\"android.widget.TextView\"), \"Raj Shekar\")").click();
			
		Thread.sleep(15000);
		act.tap(367, 153).perform();
//		System.out.println(driver.findElementByXPath("//com.appbootup.ipo.news[@text='ALL IPO News']").getText());
		driver.close();
	}
	
	
	public static void allowAppPermission(AndroidDriver<AndroidElement> driver) throws InterruptedException{
		System.out.println(driver.findElements(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).size());
		for (int i = 0; i < 4; i++) {
			driver.findElement(MobileBy.xpath("//*[@class='android.widget.Button'][2]")).click();
			Thread.sleep(1000);
		}
		System.out.println("ended");
		}

	public static void waitForElementVisibleAndClick(AndroidDriver  driver, AndroidElement locator, int seconds)
    {
		System.out.println("--- waiting for element to be visible for " + seconds + " seconds");

            WebDriverWait wait = new WebDriverWait(driver,seconds);
            wait.until(ExpectedConditions.visibilityOf(locator));
            System.out.println("Clicking on element");
            locator.click();
      }

	
}
