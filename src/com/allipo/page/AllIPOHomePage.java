package com.allipo.page;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.allipo.utils.DriverUtils;
import com.allipo.utils.Log;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AllIPOHomePage {

AndroidDriver<AndroidElement> driver = null;
	
	public AllIPOHomePage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	public void validateHomeScreen(){
		Log.info("---Validating Home Screen ---");
		DriverUtils.validateHomePage(driver);
		
	}
	
	public  void verifyIPOListPage() throws InterruptedException {
		Log.info("--- Validating the details Page ---");
		List<AndroidElement> listOfIPOs = driver.findElementsById("com.appbootup.ipo.news:id/ipo_title");
		Log.info("---Total number of ipo's displayed on screen " + listOfIPOs.size());
		String title, date, shortnotes;
		
		for(int i=1;i<listOfIPOs.size()-1;i++) {
			title = null;
			date = null;
			shortnotes = null;
			AndroidElement frameElement = driver.findElementByXPath("//android.widget.FrameLayout[@index='" + i + "']");

			title = frameElement.findElementById("com.appbootup.ipo.news:id/ipo_title").getText();
			date = frameElement.findElementById("com.appbootup.ipo.news:id/date").getText();
			shortnotes = frameElement.findElementById("com.appbootup.ipo.news:id/short_content").getText();
			Thread.sleep(2000);
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + title + "\"))");
			Log.info("--Title of "+i+" ---" + title);
			Log.info("---Date of "+i+"---" + date);
			Log.info("---Short notes of "+i+" ---" + shortnotes);
		}

	}
	
	
	public  void selectFirstIPO() throws InterruptedException {
		Log.info("--- Selecting First IPO in the Home Page ---");
		String title, date, shortnotes;
		
			title = null;
			date = null;
			shortnotes = null;
			AndroidElement frameElement = driver.findElementByXPath("//android.widget.FrameLayout[@index='1']");

			title = frameElement.findElementById("com.appbootup.ipo.news:id/ipo_title").getText();
			date = frameElement.findElementById("com.appbootup.ipo.news:id/date").getText();
			shortnotes = frameElement.findElementById("com.appbootup.ipo.news:id/short_content").getText();
			Thread.sleep(2000);
			driver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + title + "\"))").click();;

			Log.info("--Title ---" + title);
			Log.info("---Date---" + date);
			Log.info("---Short notes ---" + shortnotes);
		

	}
	

}
