package com.Miles.TestScripts;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.miles.BaseSettings.MilesSettings;

import com.miles.PageLibRepo.HomePageLib;
import com.miles.PageLibRepo.LoginPageLib;
import com.miles.Utilities.MilesUtilities;

public class FxPremiumUserWorkFlows extends MilesSettings
{	
	 WebDriver driver = null ;
	 LoginPageLib login ;
	 HomePageLib homeObj ;
	 String expectedInfoTxt = "User's information updated successfully." ;
	 String ClassName = this.getClass().getSimpleName().toString();
	 
	 @Parameters({ "enivironment" })
	 @BeforeMethod
    private void Initialize(String env)
    {
	 this.driver = DecideEnvironment(env);
	 login = new LoginPageLib(driver);
	 homeObj = login.login("premium@fourthfrontier.com",MilesUtilities.DecryptPass("YXV0b21hdGlvbjRm"));
    }
	 
	 @AfterMethod
	 private void CloseDriverSession(ITestResult result) throws Exception
	 {
		 if (result.getStatus() == ITestResult.FAILURE) 
		 {
			 MilesUtilities.createWorkFlowFolder(ClassName);
			 MilesUtilities.CaptureEvidance(driver,result.getMethod().getMethodName(),result,ClassName);
			 System.out.println(result.getMethod().getMethodName()+" Test Failed Due to the reason\n"+result.getThrowable().getMessage());
		 }        
		 driver.quit();
	 }
	
	 
@BeforeClass
private void SetEvidenceDir()
{
	MilesUtilities.createDateBasedDirectory();
	
}
	
//	 @Test (description = "Purpose of this test is to Verify that premium user can Login to FX WebApp")
//		public void VerifyPremiumUsersCanLoginToFxWebApp() throws Exception
//		{
//		
//			    String expectedText= "User Logged In Successfully";
//					
//			    homeObj.VerifyUserInfoPopUp(expectedText);	
//			    justSleepFor(4);
//				homeObj.VerifyLoggedInUserName(UserLevel.Premium.toString());
//		}
//		
//	 @Test (description = "Purpose of this test is to Verify that ECG has been synced and Green Tick mark is visble for Premium User")
//		public void VerifyECGSyncIsSuccessfullForPremiumUser() throws Exception
//		{		
//			WorkoutDetailsPageLib workoutObj = new WorkoutDetailsPageLib(this.driver);
//					
//			//	homeObj.VerifyTodaysECGIsSynced();
//				homeObj.clickOnGreenTick();
//				workoutObj.VerifyECGGraphIsDisplayed();
//		 }
//		    
//		@Test (description = "Purpose of this test is to Verify that user can Download and verify Current Day's FIT File and verifies if its valid or not based on length of file")
//		public void VerifyPremiumUserCanDownloadLatestFITFiles() throws Exception
//		{
//		    String ext = ".fit";
//		
//					MilesUtilities.deleteFiles(ext);
//					justSleepFor(6);
//					
//						homeObj.clickOnActivityButton();
//						homeObj.clickOnDownloadFIT();
//						System.out.println("FIT File option was clicked for user");
//						justSleepFor(4);
//						Assert.assertTrue(MilesUtilities.isCurrentDaysFileDownloaded(ext),"Fit File downloaded");
//					driver.close();	
//		}
//		
//		
//		@Test (description = "Purpose of this test is to Verify if Insights are displayed for Premium User ")
//		public void VerifyInsightInfoAreDisplayedForPremiumUser() throws Exception
//		{		
//			WorkoutDetailsPageLib wd = new WorkoutDetailsPageLib(driver);
//					
//					//	homeObj.VerifyTodaysActivityIsDisplayed();
//						homeObj.clickOnGreenTick();
//						justSleepFor(7);
//						wd.AssertInsightInfo();
//		}
//		
//		
//		@Test (description = "Purpose of this test is to Verify that Premium User can Sync his activity and it is being displayed")
//		public void VerifyActivitySyncForPremiumUser() throws Exception
//		{				
//					//	homeObj.VerifyTodaysActivityIsDisplayed();
//						
//		}
//		
//		
//		@Test(description = "Purpose of this test is to Verify ECG PDF Pop Up for Premium Users")
//		public void VerifyECGPDFPopUp_PremiumUser() throws Exception
//		{		
//						homeObj.clickOnActivityButton();
//						justSleepFor(3);
//						homeObj.ClickgenerateECGPDF();
//						justSleepFor(5);
//						homeObj.VerifyECGPDFPop();
//		}
//		
}
