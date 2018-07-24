package com.allipo.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.openqa.selenium.OutputType;

public class Screenshots {
	
	public static String takeScreenshot(WebDriver driver, String fileName){
		fileName = fileName + ".png";
		String directory = "reports\\ss\\";
		File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(sourceFile, new File(directory + fileName));
		} catch (IOException e) {
			Log.info("Error while taking screenshot...");
			e.printStackTrace();
		}
		String destination = directory + fileName;
		return destination;
	}
}