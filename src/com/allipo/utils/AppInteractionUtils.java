package com.allipo.utils;

import java.time.Duration;

import io.appium.java_client.android.AndroidDriver;

public class AppInteractionUtils {

	public static void makeAppBkAndFrGround(AndroidDriver driver)
	{
		Log.info("Making App to run background for 5 sec");
		driver.runAppInBackground(Duration.ofSeconds(5));
		Log.info("Bringing App foreground");
		driver.currentActivity();
	}
}
