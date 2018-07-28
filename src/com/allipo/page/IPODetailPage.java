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

public class IPODetailPage {

	AndroidDriver<AndroidElement> driver = null;
	@AndroidFindBy(id="com.appbootup.ipo.news:id/txtAnchorReserve")
	WebElement buyerTextAnchor;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/qibSupply")
	WebElement qibSupply;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/niiSupply")
	WebElement niiSupply;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/riiSupply")
	WebElement riiSupply;
	

	@AndroidFindBy(xpath="//*[@id='com.appbootup.ipo.news:id/qibDemand']/android.widget.TextView")
	WebElement qibDemand;

	@AndroidFindBy(xpath="//*[@id='com.appbootup.ipo.news:id/niiDemand']/android.widget.TextView")
	WebElement niiDemand;

	@AndroidFindBy(xpath="//*[@id='com.appbootup.ipo.news:id/riiDemand']/android.widget.TextView")
	WebElement riiDemand;
	
	//--Price
	@AndroidFindBy(id="com.appbootup.ipo.news:id/faceValuePrice")
	WebElement faceValuePrice;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/issuePrice")
	WebElement issuePrice;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/premiumPrice")
	WebElement premiumPrice;
	
	//---Bids & Allotment
	@AndroidFindBy(id="com.appbootup.ipo.news:id/txtOverSubscriptionComment")
	WebElement SubscriptionComment;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/riiApplicationCount")
	WebElement riiApplicationCount;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/riiApplicationCountMax")
	WebElement riiApplicationMaxCount;
	
	@AndroidFindBy(id="com.appbootup.ipo.news:id/riiApplicationCountPrediction")
	WebElement riiApplicationCountPrediction;
	
	
	
	public IPODetailPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}
	
	
	public void validateIPODetails(){
		validateHeaderDetails();
		validatePrice();
		validateBidsAndAllotments();
		//validateBuyer();
		validateSubscription();
		validateTableValues("com.appbootup.ipo.news:id/ipoExchangeDetailSubscription");
		validateTableValues("com.appbootup.ipo.news:id/qibDetailSubscription");
		validateTableValues("com.appbootup.ipo.news:id/niiDetailSubscription");
		validateCompanyFinancials();
		
	}
	
	public void validateIPODetailDownloadDoc(AndroidElement downlaodImg) throws InterruptedException{
	
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
	
	public void validateHeaderDetails(){
		printLog(driver.findElementById("com.appbootup.ipo.news:id/ipo_title").getText());
		printLog(driver.findElementById("com.appbootup.ipo.news:id/exchangeTime").getText());
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
	public void validateCompanyFinancials(){
		printLog("Validating Company Financials in details page.");
		AndroidElement element = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\"com.appbootup.ipo.news:id/lblCompanyFinancials\"))");
		//AndroidElement element = driver.findElementByXPath("//android.widget.TextView[@id='com.appbootup.ipo.news:id/lblCompanyFinancials']/following-sibling::android.widget.FrameLayout");
		
		List<MobileElement> totalRows = element.findElementsByXPath("//android.widget.TableRow");
		
		for (MobileElement mobileElement : totalRows) {
			List<MobileElement> values = mobileElement.findElementsByXPath("//android.widget.TextView");
			for (MobileElement mobileElement2 : values) {
				
				printLog("Table Values -"  + mobileElement2.getText());
			}
			
		}
		
		
	}
	public void validateSubscription(){
		printLog("Validating subscription in details page.");
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Subscription\"))");
		AndroidElement subscriptionDetsils = driver.findElementByXPath("//android.widget.TextView[@text='Subscription']/following-sibling::android.widget.FrameLayout");
		
		printLog(subscriptionDetsils.findElementById("com.appbootup.ipo.news:id/qib").getText());
		printLog(driver.findElementById("com.appbootup.ipo.news:id/nii").getText());
		printLog(driver.findElementById("com.appbootup.ipo.news:id/rii").getText());
		printLog("Subscription NSE-" + driver.findElementByXPath("//android.widget.TextView[@text='NSE']/preceding-sibling::android.widget.TextSwitcher/android.widget.TextView[@index='0']").getText());
		printLog("Subscription BSE-" +  driver.findElementByXPath("//android.widget.TextView[@text='BSE']/preceding-sibling::android.widget.TextSwitcher/android.widget.TextView[@index='0']").getText());
		printLog("Subscription TOTAL-"+ driver.findElementByXPath("//android.widget.TextView[@text='TOTAL']/preceding-sibling::android.widget.TextSwitcher/android.widget.TextView[@index='0']").getText());
	}
	
	public void validateBuyer(){
		printLog("Validating subscription in details page.");
		AndroidElement ele = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(textStartsWith(\"Who is Buying\"))");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//AndroidElement buyerDetails = driver.findElementByXPath("//android.widget.TextView[@text='Who is Buying? Demand/Supply']/following-sibling::android.widget.FrameLayout");
		printLog("qibSupply-" + driver.findElementById("com.appbootup.ipo.news:id/qibSupply").getText());
		printLog("nilSupply-" + driver.findElementById("com.appbootup.ipo.news:id/niiSupply").getText());
		printLog("riiSupply-" + driver.findElementById("com.appbootup.ipo.news:id/riiSupply").getText());
		printLog("qibDemand-" + driver.findElementByXPath("//*[@id='com.appbootup.ipo.news:id/qibDemand']/android.widget.TextView").getText());
		printLog("niiDemand-" + driver.findElementByXPath("//*[@id='com.appbootup.ipo.news:id/niiDemand']/android.widget.TextView").getText());
		printLog("riiDemand-" + driver.findElementByXPath("//*[@id='com.appbootup.ipo.news:id/riiDemand']/android.widget.TextView").getText());
		
		
	}
	
	public void validatePrice(){
		printLog("Validating Price.");
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Price\"))");
		AndroidElement price = driver.findElementByXPath("//android.widget.TextView[@text='Price']/following-sibling::android.widget.FrameLayout");
		
		printLog("faceValue-" + faceValuePrice.getText());
		printLog("issuePrice-" + issuePrice.getText());
		printLog("premiumPrice-" + premiumPrice.getText());
		
	}
	
	public void validateBidsAndAllotments(){
		printLog("Validating Price.");
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bids & Allotment\"))");
		AndroidElement bidsAndAlotments = driver.findElementByXPath("//android.widget.TextView[@text='Bids & Allotment']/following-sibling::android.widget.FrameLayout");
		
		printLog("SubscriptionComment-" + SubscriptionComment.getText());
		printLog("Rii ApplicationCount-" + riiApplicationCount.getText());
		printLog("Rii ApplicationMaxCount-" + riiApplicationMaxCount.getText());
		printLog("Rii ApplicationCountPrediction-" + riiApplicationCountPrediction.getText());
			
	}
	
	public void validateTableValues(String tableID){
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
	
	
	public void printLog(String message){
		Log.info("[INFO] - " + message);
	}
}
