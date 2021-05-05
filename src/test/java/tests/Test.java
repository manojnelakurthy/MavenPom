package tests;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.LogStatus;

import constants.ExcelData;
import constants.Webdriver;
import pages.DeveloperHomePage;
import pages.LoginPage;

public class Test extends Webdriver{
	ExcelData data=new ExcelData();
    ReportClass report=new ReportClass();
	LoginPage lp;
	DeveloperHomePage dh;
	
	@BeforeTest
	public void launch() throws IOException{
		data.read();
		String browser=ExcelData.browser;
		String url=ExcelData.url;
		
	  Webdriver.launch(browser);	
	  driver.get(url);
	}
	
	@org.testng.annotations.Test
	public void A_login() throws Exception{
	  report.startTest();
		data.read();
		
		String username=ExcelData.uname;
		String password=ExcelData.pword;
		
		lp=PageFactory.initElements(driver, LoginPage.class);
		lp.checklogin(username, password);
		report.test.log(LogStatus.PASS,"pass" );
		report.test.log(LogStatus.FAIL,"fail" );
        report.endTest();
		
	}
	
	@org.testng.annotations.Test
	public void B_checklogout() throws Exception{
		 report.startTest();
		dh=PageFactory.initElements(driver, DeveloperHomePage.class);
		dh.clicklogout();
		 lp=PageFactory.initElements(driver, LoginPage.class);
		WebElement ele=lp.checklogout();
		Assert.assertTrue(ele.isDisplayed());
		if(ele.isDisplayed()){
			Reporter.log("Logout Successfull");
		}else{
			Reporter.log("Logout Failed");
		}
		report.test.log(LogStatus.PASS,"pass" );
		report.test.log(LogStatus.FAIL,"fail" );
        report.endTest();
	}
	
	
}