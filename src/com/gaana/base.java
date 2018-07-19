package com.gaana;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class base {
	
	
	public static  AndroidDriver<AndroidElement> allIPOCaps() throws MalformedURLException
	{
	
	AndroidDriver<AndroidElement>  driver;
	// TODO Auto-generated method stub
	// File app = new File(appName);
     DesiredCapabilities capabilities = new DesiredCapabilities();
     
     capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");
     //capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
     capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "50000");
    // capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, "true");
     capabilities.setCapability("appPackage", "com.appbootup.ipo.news");
	 capabilities.setCapability("appActivity", "com.appbootup.ipo.news.ActivitySplashScreen");
     //System.out.println(app.getAbsolutePath());
     driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
     driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);  
	    return driver;
	}
	
	
	

}
