package com.allipo.page;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.*;
import com.allipo.utils.Log;
import com.allipo.utils.ScrollScreen;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SMEIPODetailPage {

	AndroidDriver<AndroidElement> driver = null;
	
	//--Price
	@AndroidFindBy(id="com.appbootup.ipo.news:id/sme_faceValuePrice")
	WebElement faceValuePriceSME;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/sme_issuePrice")
	WebElement issuePriceSME;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/sme_qibSupply")
	WebElement qibSupplySME;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/sme_niiSupply")
	WebElement niiSupplySME;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/sme_riiSupply")
	WebElement riiSupplySME;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/sme_qibDemand")
	WebElement qibDemandSME;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/sme_niiDemand")
	WebElement niiDemandSME;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/sme_riiDemand")
	WebElement riiDemandSME;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/sme_nse_total")
	WebElement NSETotalSubscriptionSME;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/sme_bse_total")
	WebElement BSETotalSubscriptionSME;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/sme_total")
	WebElement TotalSubscriptionSME;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/sme_qib")
	WebElement qibSubscriptionSME;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/sme_nii")
	WebElement niiSubscriptionSME;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/sme_rii")
	WebElement riiSubscriptionSME;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/txtSummaryContent")
	WebElement summarySME;
	
	public SMEIPODetailPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}
	
	
	public void validateSMEIPODetails() throws InterruptedException{
		
		validateSMEIPOHeaderDetails();
		validateSMEIPOPrice();
		validateSMEIPOBuyer();
		ScrollScreen.getScreenHeight(driver);
		validateSMEIPOSubscription();
		if(driver.findElementsById("com.appbootup.ipo.news:id/sme_qibDetailSubscription").size()>0)
			validateSMEIPOTableValues("com.appbootup.ipo.news:id/sme_qibDetailSubscription");
		
		validateSMEIPOTableValues("com.appbootup.ipo.news:id/sme_riiDetailSubscription");
		validateSMEIPOTableValues("com.appbootup.ipo.news:id/sme_niiDetailSubscription");
		
		validateSMEIPOSumary();
	}
	
	public void validateSMEIPODetailDownloadDoc(AndroidElement downlaodImg) throws InterruptedException{
	
			Log.info(downlaodImg.toString());
		
			downlaodImg.click();
			Assert.assertFalse(driver.findElementsByXPath("//android.widget.TextView[contains(@text,'Check later')]").size()>0,"Downloading-Check-later message is displayed, document is not downlaoded");
			
			int count=0;
			while(driver.findElementsById("com.appbootup.ipo.news:id/snackProgressBar_txt_message").size()>0)
			{
				Log.info("Downlaoding and waiting for "+ count+" sec"+ downlaodImg.toString() +"dcoument");
				Thread.sleep(1000);
				count++;
				if(count==120)
				{
					Assert.assertTrue(false,"Download not complete after 120 sec");
					break;
				}
			}
			
			count=0;
			while(driver.findElementsByXPath("//android.widget.TextView[contains(@resource-id,'com.appbootup.ipo.news:id/snackbar_text') and contains(@text,'Download Completed')]").size()>0)
			 {
				 Log.info("Waiting for Download Completed message to dissappear");
				 Thread.sleep(1000);
				 count++;
				 if(count==120)
					{
						Assert.assertTrue(false,"Download  complete message is not disappearing even after 120 sec");
						break;
					}
			 }
			 
//			Assert.assertTrue(driver.findElementsByXPath("//android.widget.TextView[contains(@text,'"+Text_Msg+"')]").size()==0);
//			Assert.assertTrue(driver.findElementsByXPath("//android.widget.TextView[contains(@resource-id,'com.appbootup.ipo.news:id/snackbar_text') and contains(@text,'Download Completed')]").size()==0);
	}
	
	
	
	public void validateSMEIPOHeaderDetails() throws InterruptedException{
		Assert.assertTrue(driver.findElementsById("com.appbootup.ipo.news:id/imgSMEIPO").size()>0);
		printLog(driver.findElementById("com.appbootup.ipo.news:id/sme_ipo_title").getText());
		printLog(driver.findElementById("com.appbootup.ipo.news:id/sme_exchangeTime").getText());
		printLog(driver.findElementById("com.appbootup.ipo.news:id/issueSize").getText());
		printLog(driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Issue Size\"))").getText());
		printLog(driver.findElementById("com.appbootup.ipo.news:id/faceValue").getText());
		printLog(driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Face Value\"))").getText());
		printLog(driver.findElementById("com.appbootup.ipo.news:id/priceBand").getText());
		printLog(driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Price Band\"))").getText());
		printLog(driver.findElementById("com.appbootup.ipo.news:id/issuePeriod").getText());
		printLog(driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Issue Period\"))").getText());
		printLog(driver.findElementById("com.appbootup.ipo.news:id/marketLot").getText());
		printLog(driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Lot Size\"))").getText());
		
	}
	

	
	public void validateSMEIPOSubscription(){
		AndroidElement ele = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(textStartsWith(\"QIB\"))");
		printLog("Validating SME IPO subscription in details page.");
		if(driver.findElementsById("com.appbootup.ipo.news:id/sme_nse_total").size()>0)
			printLog(NSETotalSubscriptionSME.getText());
		else if(driver.findElementsById("com.appbootup.ipo.news:id/sme_bse_total").size()>0)
			printLog(BSETotalSubscriptionSME.getText());
		
			printLog(TotalSubscriptionSME.getText());
		
	
	if(driver.findElementsById("com.appbootup.ipo.news:id/sme_qib").size()==0)
		ScrollScreen.scrollScreenUp(driver,0.8,0.7);
	
		printLog(qibSubscriptionSME.getText());
		printLog(niiSubscriptionSME.getText());
		printLog(riiSubscriptionSME.getText());
		
	}
	
	
	
	public void validateSMEIPOBuyer(){
		printLog("Validating subscription in SME details page.");
		AndroidElement ele = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(textStartsWith(\"Who is Buying\"))");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//AndroidElement buyerDetails = driver.findElementByXPath("//android.widget.TextView[@text='Who is Buying? Demand/Supply']/following-sibling::android.widget.FrameLayout");
		printLog("qibSupply-" + qibSupplySME.getText());
		printLog("nilSupply-" + niiSupplySME.getText());
		printLog("riiSupply-" + riiSupplySME.getText());
		printLog("qibDemand-" + qibDemandSME.getText());
		printLog("niiDemand-" + niiDemandSME.getText());
		printLog("riiDemand-" + riiDemandSME.getText());
	}
	
	
	
	public void validateSMEIPOPrice(){
		printLog("Validating SME IPO Price.");
		printLog("faceValue-" + faceValuePriceSME.getText());
		printLog("issuePrice-" + issuePriceSME.getText());
	}
	
	
	public void validateSMEIPOTableValues(String tableID){
		printLog("Validating Investor Table-" + tableID);
		AndroidElement table = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\"" +tableID+ "\"))");
		
		List<MobileElement> totalRows = table.findElementsByXPath("//android.widget.TableRow");
		
		for (MobileElement mobileElement : totalRows) {
			List<MobileElement> values = mobileElement.findElementsByXPath("//android.widget.TextView");
			for (MobileElement mobileElement2 : values) {
				
				printLog("Table Values-"  + mobileElement2.getText());
			}	
		}	
	}
	
	public void validateSMEIPOSumary(){
		AndroidElement ele = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(textStartsWith(\"IPO Summary\"))");
		printLog(summarySME.getText());
	}
	
	public void printLog(String message){
		Assert.assertTrue(message.length()>0);
		Log.info("[INFO] - " + message);
	}
}
