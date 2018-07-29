package com.allipo.page;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.allipo.utils.DriverUtils;
import com.allipo.utils.Log;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class InitialScreenPage {
	AndroidDriver<AndroidElement> driver = null;
	
	public InitialScreenPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/btn_next")
	WebElement nextButton; 
	
	@AndroidFindBy(xpath="//*[@class='android.widget.Button'][2]")
	WebElement allowPopUpButton;
	
	@AndroidFindBy(xpath="//*[@class='android.widget.Button'][1]")
	WebElement denyPopUpButton;
	
	@AndroidFindBy(accessibility="Google Login")
	WebElement GoogleLogin;
	
//	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']")
	@AndroidFindBy(id="com.google.android.gms:id/account_display_name")
	WebElement firstGoogleAccount;
	
	public static final String catLoadingMask="com.appbootup.ipo.news:id/eye_right";
	public static final String SMETab="//android.widget.TextView[contains(@text,'SME')]";
	
	public void allowAllNotificaiton(){
		Log.info("Allowing all notifications");
		try {
			DriverUtils.allowAppPermission(driver);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.info("Allowed all Notificaitons");
		
	}
	public void clickOnNextButton(){
		Log.info("Clicking on Next Button");
			DriverUtils.getVisibleElement(driver,nextButton);
			nextButton.click();
		Log.info("Click  successfull on next button");
	}
	
	public void clickOnAllowPopUp(){
		Log.info("Clicking on Allow Button");
			DriverUtils.getVisibleElement(driver,allowPopUpButton);
			allowPopUpButton.click();
		Log.info("Click successfull on Allow popup");
	}
	
	
	public void clickOnGoogleLogin(){
		Log.info("Clicking on Google Login Button");
			DriverUtils.getVisibleElement(driver,GoogleLogin);
			GoogleLogin.click();
		Log.info("Click successfull on gogle login");
	}
	
	public void selectFirstAccount() throws InterruptedException{
		Log.info("Selecting First Google Account.");
			DriverUtils.getVisibleElement(driver,firstGoogleAccount);
			firstGoogleAccount.click();
		Log.info("Click  successfull on First Google account");
		Log.info("Waiting for Loading mask to disappear");
		Thread.sleep(2000);
		while(driver.findElementsById(catLoadingMask).size()>0)
		{
			Log.info("Waiting for Loading mask to disappear");
			Thread.sleep(1000);
		}
	}
	
	public void selectSMETab() throws InterruptedException{
		Log.info("Selecting SME Tab.");
		while(driver.findElementsByXPath(SMETab).size()==0)
			Thread.sleep(1000);
		MobileElement sme = (MobileElement)driver.findElementByAndroidUIAutomator("new UiSelector().text(\"SME\")");
		sme.click();
		Log.info("SME Tab Click successfull");
	}
	
	
}
