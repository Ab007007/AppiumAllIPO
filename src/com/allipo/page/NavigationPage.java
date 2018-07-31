package com.allipo.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.allipo.utils.Log;
import com.allipo.utils.DriverUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class NavigationPage {
	
	AndroidDriver<AndroidElement> driver = null;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Open Navigation']")
	WebElement openNavigation;
	
	public static final String NewsLoadingMask="//android.widget.ImageView[@index='1']";
	public static final String Navigation="//android.view.View[@content-desc='Open Navigation']";
	public static final String NAvigation_Feedback="//android.widget.CheckedTextView[@text='Feedback']";
	public static final String NAvigation_Saved="//android.widget.CheckedTextView[@text='Saved']";
	public static final String NAvigation_Home="//android.widget.CheckedTextView[@text='Home']";
	public static final String NAvigation_Allotment="//android.widget.CheckedTextView[@text='Allotment']";
	public static final String NAvigation_News="//android.widget.CheckedTextView[@text='News']";
	public static final String NAvigation_Settings="//android.widget.CheckedTextView[@text='Settings']";
	public static final String NewsPanel_Content="com.appbootup.ipo.news:id/short_content";
	public static final String News_INDIA="//android.widget.TextView[@text='INDIA']";
	public static final String News_OTHERS="//android.widget.TextView[@text='OTHERS']";
	
	public static final String News_INDIA_Small="//android.widget.TextView[@text='India']";
	public static final String News_OTHERS_Small="//android.widget.TextView[@text='Others']";
	
	public static final String Allotment_KARVY_TAB="//android.widget.TextView[@text='KARVY']";
	public static final String Allotment_LINKTIME_TAB="//android.widget.TextView[@text='LINKINTIME']";
	
	public static final String Allotment_Karvy_IPODropDownID="ddl_ipo";
	public static final String Allotment_Karvy_TypeDropDownID="ddl_appl_type";
	public static final String Allotment_Karvy_Header="//android.view.View[@text='IPO Allotment Status']";
	public static final String Allotment_Karvy_AppRadio="//android.view.View[@text=' Application No']";
	public static final String Allotment_Karvy_AppRadioID="rad_type_0";
	public static final String Allotment_Karvy_PANRadio="//android.view.View[@text=' PAN']";
	public static final String Allotment_Karvy_PANRadioID="rad_type_1";
	public static final String Allotment_Karvy_DPIDRadio="//android.view.View[@text=' DPID/Client ID']";
	public static final String Allotment_Karvy_DPIDRadioID="rad_type_2";
	
	public static final String Allotment_LinkTime_CopanyDropID="ddlCompany";
	public static final String Allotment_LinkTime_PANTxtID="txtStat";
	public static final String Allotment_LinkTime_SubmitBtnID="btnsearc";
	
	public static final String Feedback_Help="//android.widget.TextView[@text='How can we help you?']";
	public static final String Feedback_Problem="//android.widget.TextView[@text='Report a problem']";
	public static final String Feedback_Suggest="//android.widget.TextView[@text='Suggest an improvement']";
	public static final String Feedback_Instabug= "//android.widget.TextView[@text='Powered by Instabug']";
	
	public static final String Feedback_Suggest_Textbox="//android.widget.EditText[@text='How can we improve?']";
	public static final String Feedback_Suggest_ImgAttachID="com.appbootup.ipo.news:id/instabug_img_attachment";
	public static final String Feedback_Suggest_DiscardBtn="android:id/button1";

	public NavigationPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}
	
	public  void openNavigationAndVrfOptions() throws InterruptedException
	{
		openNavigation.click();
		Log.info(driver.findElementByXPath(NAvigation_Allotment).getText());
		Log.info(driver.findElementByXPath(NAvigation_Feedback).getText());
		Log.info(driver.findElementByXPath(NAvigation_Home).getText());
		Log.info(driver.findElementByXPath(NAvigation_News).getText());
		Log.info(driver.findElementByXPath(NAvigation_Saved).getText());
		Log.info(driver.findElementByXPath(NAvigation_Settings).getText());
		
		Log.info("Verifying Navigation to news");
		driver.findElementByXPath(NAvigation_News).click();
		int i=0;
		while(driver.findElementsById(NewsPanel_Content).size()==0)
		{
			Log.info("Waiting for news page to load for "+i+" Sec");
			Thread.sleep(1000);
			i++;
			if(i==120)	
				break;
		}
		Assert.assertTrue(driver.findElementsById(NewsPanel_Content).size()>0);
		Thread.sleep(2000);
		if(driver.findElementsByXPath(News_INDIA_Small).size()==0)
		{
			Assert.assertTrue(driver.findElementsByXPath(News_INDIA).size()>0);
			Log.info(driver.findElementByXPath(News_INDIA).getText());
		}
		else
		{
			Assert.assertTrue(driver.findElementsByXPath(News_INDIA_Small).size()>0);
			Log.info(driver.findElementByXPath(News_INDIA_Small).getText());
		}
		
		if(driver.findElementsByXPath(News_OTHERS_Small).size()==0)
		{
			Assert.assertTrue(driver.findElementsByXPath(News_OTHERS).size()>0);
			Log.info(driver.findElementByXPath(News_OTHERS).getText());
		}
		else
		{
			Assert.assertTrue(driver.findElementsByXPath(News_OTHERS_Small).size()>0);
			Log.info(driver.findElementByXPath(News_OTHERS_Small).getText());
		}
		
		openNavigation.click();
		Log.info("Verifying Navigation to Allotment");
		driver.findElementByXPath(NAvigation_Allotment).click();
		Thread.sleep(10000);
		Log.info("Verifying Allotment page");
		
		driver.pressKeyCode(AndroidKeyCode.BACK);
		openNavigation.click();
		
		Log.info("Verifying Feedback page");
		driver.findElementByXPath(NAvigation_Feedback).click();
		
		int j=0;
		while(driver.findElementsByXPath(Feedback_Help).size()==0)
		{
			Thread.sleep(1000);
			Log.info("Waiting for Feedback page to load for "+j+" Sec");
			j++;
			if(j==120)
				break;
		}
		Assert.assertTrue(driver.findElementsByXPath(Feedback_Help).size()>0);
		Log.info(driver.findElementByXPath(Feedback_Help).getText());
		
		Assert.assertTrue(driver.findElementsByXPath(Feedback_Instabug).size()>0);
		Log.info(driver.findElementByXPath(Feedback_Instabug).getText());
		
		driver.findElementByXPath(Feedback_Suggest).click();
		Log.info(driver.findElementByXPath(Feedback_Suggest).getText());
		
		int k=0;
		while(driver.findElementsByXPath(Feedback_Suggest_Textbox).size()==0)
		{
			Thread.sleep(1000);
			Log.info("Waiting for Feedback->Suggestion page to load for "+k+" Sec");
			k++;
			if(k==120)
				break;
		}
		Assert.assertTrue(driver.findElementsByXPath(Feedback_Suggest_Textbox).size()>0);
		Assert.assertTrue(driver.findElementsById(Feedback_Suggest_ImgAttachID).size()>0);
		
		driver.pressKeyCode(AndroidKeyCode.BACK);
		driver.findElementById(Feedback_Suggest_DiscardBtn).click();
	}
	
}
