package com.allipo.utils;

import org.testng.ITestResult;

import io.appium.java_client.android.AndroidDriver;

public class AfterMethodUtils {
	
	public static void afterMethod(ITestResult testResult,AndroidDriver driver)
	{
		String testName=testResult.getMethod().getMethodName();
		switch(testResult.getStatus())
		{
		case ITestResult.SUCCESS:
			Log.info("Inside AfterMethod block with test "+testName+" having status =  SUCCESS");
			break;
			
		case ITestResult.FAILURE:
			Log.info("Inside AfterMethod block with test "+testName+" having status = FAIL ");
//			String path = Screenshots.takeScreenshot(driver, testResult.getName()+ExtentReportFactory.getCurrentDateAndTime());
//			Log.info("Path " + path);
//			Log.ssPath.add(path);
//			Log.attachScreenShot(path);
//			break;
			
		case ITestResult.SKIP:
			Log.info("Inside AfterMethod block with test "+testName+" having status = SKIPPED");
			break;
		
		case ITestResult.STARTED:
			Log.info("Inside AfterMethod block with test "+testName+" having status = STARTED");
			break;

		default:
			Log.info("Inside AfterMethod block with test "+testName+"  and Status code"+testResult.getStatus());
		}
	  }
	}


