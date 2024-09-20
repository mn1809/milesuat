package com.Miles.SanityScripts;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.miles.BaseSettings.MilesSettings;
import com.miles.PageLibRepo.HeartHealthPageLib;
import com.miles.PageLibRepo.HomePageLib;
import com.miles.PageLibRepo.LoginPageLib;
import com.miles.Utilities.MilesUtilities;

public class FxSanity_Coach extends MilesSettings
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
		 try
		 {
			 this.driver = DecideEnvironment(env);
			 login = new LoginPageLib(driver);
			 homeObj = login.login("trainer2@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
		 } catch (Exception e)
		 {
			 System.out.println("Failed to Initialize due to exception "+e.getMessage());
			 
		 }
	 
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
		 
		 if(result.getStatus() == ITestResult.SUCCESS)
		 {
			 System.out.println("<-------------Passed Test case is -> " +result.getName()+"-------------->");
		 }
		 driver.quit();
	 }
	
	 
@BeforeClass
private void SetEvidenceDir()
{
	MilesUtilities.createDateBasedDirectory();
	
}


	 @Test (description = "Purpose of this test is to Verify that Fx Coach can Login to FX WebApp")
		public void VerifyCoachCanLoginToFxWebApp() throws Exception
		{
		
			justSleepFor(4);
			homeObj.VerifyLoggedInUserName(UserLevel.Trainer.toString());
		}
		
	 @Test(description = "Purpose of this test is to Verify Fx Coach platform name is displayed ")
		public void VerifyFCPNameOnAdminPanel() throws Exception
		{		
			justSleepFor(3);
			ClickOnAdminMenu();
			justSleepFor(1);
			homeObj.VerifyFCPName();
		}
	 
	 @Test (description = "Purpose of this test is to Verify that Health Treand Web Page opens and displays related Charts for Trainer level ")
		public void VerifyHealthTreandPageDisplays_TrainerLevel() throws Exception
		{		
			HeartHealthPageLib heartHealthObj = new HeartHealthPageLib(driver);
						homeObj.ClickHealthTrendBtn();
						heartHealthObj.VerifyChartsAreDisplayed();
		} 
	 
	 
	 @Test(description = "Purpose of this test is to Verify Elements in Web App for FCP User")
		public void VerifyHomePageElements_FCPUser() throws Exception
		{		
						justSleepFor(4);
						homeObj.VerifyHomePageElements();
		}
	 

		@Test (description = "Purpose of this test to verify Add Report button and Section is working as expected for FCP User")
		public void VerifyAddReportSection_FCPUser() throws Exception
		{		
			Thread.sleep(3000);
			homeObj.ClickAddReportBtn();
			Thread.sleep(3000);
			homeObj.VerifyAddReportScreen();
		}
	 
		
/*
 * 
 * Healper Methods
 */
	 
		
		public void ClickOnAdminMenu()
		{
			driver.findElement(By.className("sidebar-toggle")).click();
			
		}
		
		public void clickOnAdminPanel()
		{
			List <WebElement> AdminOptions = driver.findElements(By.className("listbrdr"));
			
			//WebElement MyDasboard = AdminOptions.get(0);
			 AdminOptions.get(1).click();
			
			//WebElement LiveUserDashBoard = AdminOptions.get(2);
		}
		
		
}
