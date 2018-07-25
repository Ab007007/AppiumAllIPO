package com.allipo.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportFactory {

	static String path =null;
	public static ExtentReports getInstance() {
		ExtentReports reports;
		path = getReportName();// "reports//RegressionTestResults.html";
		setPath(path);
		reports = new ExtentReports(path, false);
		return reports;

	}

	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		ExtentReportFactory.path = path;
	}

	public static String getCurrentDateAndTime() {
		Date date = new Date();
		String date1 = date.toString().replaceAll(" ", "_").replaceAll(":", "_");
		// display time and date using toString()
		//System.out.println(date1);

		return date1;
	}

	public static String getReportName() {
		String name = "reports//report_" + getCurrentDateAndTime() + ".html";
		return name;
	}

	public static String getLogName(String logName) {
		String name = "reports//" + logName + "_" + getCurrentDateAndTime() + ".txt";
		return name;
	}

	/**
	 * 
	 * @param from
	 * @param password
	 * @param to
	 * @param subject
	 * @param body
	 * @throws EmailException 
	 */
	
}
