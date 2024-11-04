//package com.Miles.TestScripts;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.testng.Assert;
//import org.testng.ITestResult;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//import com.miles.BaseSettings.MilesSettings;
//import com.miles.PageLibRepo.AdminPageLib;
//import com.miles.PageLibRepo.HeartHealthPageLib;
//import com.miles.PageLibRepo.HomePageLib;
//import com.miles.PageLibRepo.LoginPageLib;
//import com.miles.Utilities.MilesUtilities;
//
//public class FxDoctorsWorkFlows extends MilesSettings
//{	
//	 WebDriver driver = null ;
//	 LoginPageLib login ;
//	 HomePageLib homeObj ;
//	 AdminPageLib adminLib;
//	 String expectedInfoTxt = "User's information updated successfully." ;
//	 String ClassName = this.getClass().getSimpleName().toString();
//	 @Parameters({ "enivironment" })
//	 @BeforeMethod
//    private void Initialize(String env)
//    {
//	 this.driver = DecideEnvironment(env);
//	 login = new LoginPageLib(driver);
//	 homeObj = login.login("doctor@fourthfrontier.com",MilesUtilities.DecryptPass("YXV0b21hdGlvbjRm"));
//	 adminLib = new AdminPageLib(driver);
//    }
//	 
//	 @AfterMethod
//	 private void CloseDriverSession(ITestResult result) throws Exception
//	 {
//		 if (result.getStatus() == ITestResult.FAILURE) 
//		 {
//			 MilesUtilities.createWorkFlowFolder(ClassName);
//			 MilesUtilities.CaptureEvidance(driver,result.getMethod().getMethodName(),result,ClassName);
//			 System.out.println(result.getMethod().getMethodName()+" Test Failed Due to the reason\n"+result.getThrowable().getMessage());
//		 }        
//		 driver.quit();
//	 }
//	
//	 
//@BeforeClass
//private void SetEvidenceDir()
//{
//	MilesUtilities.createDateBasedDirectory();
//	
//}
//	 @Test (description = "Purpose of this test is to Verify that Doctor can Login to FX WebApp")
//		public void VerifyDoctorsCanLoginToFxWebApp() throws Exception
//		{
//		
//			    String expectedText= "User Logged In Successfully";
//					
//			    homeObj.VerifyUserInfoPopUp(expectedText);	
//		}
//		
//	
//		@Test (description = "Purpose of this test is to Verify Doctors can search any user in our system")
//		
//		public void VerifyDoctrsCanSearchPatients()
//		{			
//					justSleepFor(3);
//					ClickOnAdminMenu();
//					justSleepFor(1);
//					clickOnAdminPanel();
//					justSleepFor(4);
//					
//					homeObj.SearchUsers("premium@fourthfrontier.com");
//					justSleepFor(2);
//					adminLib.VerifyIfSearchedUserAppeared("premium@fourthfrontier.com");
//		 }
//		
//	
//		
//		
//		
//		
//		@Test (description = "Purpose of this test is to Verify that current user's name is displayed when Doctors access any user's dashboard")
//		public void VerifyUserNameDisplayedOnDashboard_Doctors()
//		{			
//					String UserName = "Ravikiran";
//					justSleepFor(3);
//					ClickOnAdminMenu();
//					justSleepFor(1);
//					clickOnAdminPanel();
//					justSleepFor(4);
//					homeObj.SearchUsers("ravikiran@fourthfrontier.com");
//					justSleepFor(2);
//					adminLib.VerifyIfSearchedUserAppeared("ravikiran@fourthfrontier.com");
//					adminLib.SelectDisplyedUser();
//					justSleepFor(6);
//					homeObj.VerifyUserNameIsDisplayedForUser(UserName);			
//		 }
//		
//		
//	//	@Test (description = "Purpose of this test is to Verify that current user's name is displayed when doctors access any user's dashboard")
//		public void VerifyECGPDFPopUp()
//		{			
//					String UserName = "Ravikiran";
//					justSleepFor(3);
//					ClickOnAdminMenu();
//					justSleepFor(1);
//					clickOnAdminPanel();
//					justSleepFor(4);
//					homeObj.SearchUsers("ravikiran@fourthfrontier.com");
//					justSleepFor(2);
//					adminLib.VerifyIfSearchedUserAppeared("ravikiran@fourthfrontier.com");
//					adminLib.SelectDisplyedUser();
//					justSleepFor(6);
//					homeObj.VerifyUserNameIsDisplayedForUser(UserName);	
//					homeObj.clickOnActivityButton();
//					homeObj.ClickgenerateECGPDF();
//					homeObj.VerifyECGPDFPopUp();
//		 }
//		
//		@Test(description = "Purpose of this test is to Verify Doctors can log in ")
//		public void VerifyLoggedInUser_DoctorUser() throws Exception
//		{		
//						justSleepFor(4);
//						homeObj.VerifyLoggedInUserName(UserLevel.Doctor.toString());
//		}		
//
//		/*
//		 * 
//		 * Helper Methods
//		 */
//		
//		
//		public void ClickOnAdminMenu()
//		{
//			driver.findElement(By.className("sidebar-toggle")).click();
//			
//		}
//		
//		public void clickOnAdminPanel()
//		{
//			List <WebElement> AdminOptions = driver.findElements(By.className("listbrdr"));
//			
//			//WebElement MyDasboard = AdminOptions.get(0);
//			 AdminOptions.get(1).click();
//			
//			//WebElement LiveUserDashBoard = AdminOptions.get(2);
//		}
//		
//}
