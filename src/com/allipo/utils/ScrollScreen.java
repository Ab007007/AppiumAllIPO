package com.allipo.utils;

import org.openqa.selenium.Dimension;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class ScrollScreen {

	public static void scrollScreenUp(AndroidDriver driver )
	{
		Dimension size = driver.manage().window().getSize();
		int starty=(int)(size.height*0.7);
		int endy=(int)(size.height*0.2);
		int startx=size.width/2;
		Log.info("Start y :"+starty +" endy : "+endy +" startx :"+startx);
		TouchAction ts = new TouchAction(driver);
		ts.longPress(startx, starty).moveTo(startx, endy).release().perform();
	}
	
	
	public static void scrollScreenDown(AndroidDriver driver )
	{
		Dimension size = driver.manage().window().getSize();
		int starty=(int)(size.height*0.2);
		int endy=(int)(size.height*0.7);
		int startx=size.width/2;
		Log.info("Start y :"+starty +" endy : "+endy +" startx :"+startx);
		TouchAction ts = new TouchAction(driver);
		ts.longPress(startx, starty).moveTo(startx, endy).release().perform();
	}
}
