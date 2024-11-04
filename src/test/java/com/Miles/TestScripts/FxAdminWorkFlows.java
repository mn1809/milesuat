//package com.Miles.TestScripts;
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
//import com.miles.PageLibRepo.HomePageLib;
//import com.miles.PageLibRepo.LoginPageLib;
//import com.miles.Utilities.MilesUtilities;
//
//public class FxAdminWorkFlows extends MilesSettings
//{	
//	 WebDriver driver = null ;
//	 LoginPageLib login ;
//	 HomePageLib homeObj ;
//	 
//	 AdminPageLib adminLib;
//	 String expectedInfoTxt = "User's information updated successfully." ;
//	 String ClassName = this.getClass().getSimpleName().toString();
//	 
//	 @Parameters({ "enivironment" })
//	 @BeforeMethod
//    private void Initialize(String env)
//    {
//	 this.driver = DecideEnvironment(env);
//	 login = new LoginPageLib(driver);
//	 homeObj = login.login("admin@fourthfrontier.com", MilesUtilities.DecryptPass("YXV0b21hdGlvbjRm"));
//	
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
//	  
//		@Test (description = "Purpose of this test is to Verify that admin can change the User Level and enable Arrhythmia and that change will remain intact")
//		public void VerifyUserLevelChangesAreSaved()
//		{
//				ChangeUserSettings(homeObj);
//				// Log out after changes and log in back
//				homeObj.logout();
//				homeObj = login.login("admin@fourthfrontier.com",MilesUtilities.DecryptPass("YXV0b21hdGlvbjRm"));
//				
//				VerifyChangedUserSettingsAreStillIntact(homeObj);
//			
//				justSleepFor(3);
//			// Revert the user back to non premium user for next run
//				RevertChangesForNextRun(homeObj);
//		}
//		
//		@Test (description = "Purpose of this test is to Verify admin can search any user in our system")
//		public void VerifyAdminSearch()
//		{			
//					justSleepFor(3);
//					ClickOnAdminMenu();
//					justSleepFor(1);
//					clickOnAdminPanel();
//					justSleepFor(4);
//					homeObj.SearchUsers("ravikiran@fourthfrontier.com");
//					justSleepFor(2);
//					adminLib.VerifyIfSearchedUserAppeared("ravikiran@fourthfrontier.com");		
//		 }
//		
//		@Test (description = "Purpose of this test is to Verify admin can search any user in our system")
//		public void VerifyUserLevelsAvailable()
//		{			
//					justSleepFor(3);
//					ClickOnAdminMenu();
//					justSleepFor(1);
//					clickOnAdminPanel();
//					justSleepFor(4);
//					homeObj.SearchUsers("ravikiran@fourthfrontier.com");
//					justSleepFor(2);
//					adminLib.VerifyIfSearchedUserAppeared("ravikiran@fourthfrontier.com");
//					
//		 }
//		
//		@Test (description = "Purpose of this test is to Verify that current user's name is displayed when admin access any user's dashboard")
//		public void VerifyUserNameDisplayedOnDashboard()
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
//		 @Test(description = "Purpose of this test is to Verify Levels of users in our system")
//			public void VerifyUserLevelsDropDown() throws InterruptedException 
//			{		
//				justSleepFor(3);
//				ClickOnAdminMenu();
//				justSleepFor(1);
//				clickOnAdminPanel();
//				justSleepFor(4);
//				homeObj.SearchUsers("ravikiran@fourthfrontier.com");
//				justSleepFor(2);
//				adminLib.VerifyIfSearchedUserAppeared("ravikiran@fourthfrontier.com");
//				homeObj.VerifyUserLevelOptions();
//			}
//		 
//	
//			
//			@Test(description = "Purpose of this test is to Verify if Admin can login in")
//			public void VerifyAdminMenuOptions_AdminUser() throws Exception
//			{		
//							justSleepFor(4);
//							ClickOnAdminMenu();
//							justSleepFor(2);
//							VerifyAdminMenuOptions();
//							ClickOnAdminMenu(); // Close the menu 
//							
//			}
//		 
//		
//		@Test(description = "Purpose of this test is to Verify if Admin can login in")
//		public void VerifyLoggedInUser_AdminUser() throws Exception
//		{		
//						justSleepFor(4);
//						homeObj.VerifyLoggedInUserName(UserLevel.Admin.toString());
//		}
///*
// * 
// * 
// * Healper Methods
// */
//	 public void ChangeUserSettings(HomePageLib hp)
//		{
//			
//			justSleepFor(3);
//			ClickOnAdminMenu();
//			justSleepFor(1);
//			clickOnAdminPanel();
//			justSleepFor(4);
//			adminLib = new AdminPageLib(driver);
//			hp.SearchUsers("ravikiran@fourthfrontier.com");
//			justSleepFor(2);
//			adminLib.VerifyIfSearchedUserAppeared("ravikiran@fourthfrontier.com");
//			justSleepFor(4);
//			hp.SelectUserLevel(1);
//			justSleepFor(3);
//			hp.GetNotificationText();
//			hp.VerifyUserInfoPopUp(expectedInfoTxt);
//			hp.EnableArrhytmia();
//			justSleepFor(3);
//			Assert.assertTrue(hp.CheckIfArrhytmiaIsEnabled(), "Arrhytmia is Enabled");
//		}
//		public void VerifyChangedUserSettingsAreStillIntact(HomePageLib hp)
//		{
//			ClickOnAdminMenu();
//			justSleepFor(1);
//			clickOnAdminPanel();
//			justSleepFor(4);
//			adminLib = new AdminPageLib(driver);
//			hp.SearchUsers("ravikiran@fourthfrontier.com");
//			justSleepFor(2);
//			//smartWait()
//			adminLib.VerifyIfSearchedUserAppeared("ravikiran@fourthfrontier.com");
//			hp.VerifyUserLevel("Premium User");
//			Assert.assertTrue(hp.CheckIfArrhytmiaIsEnabled(), "Arrhytmia is Enabled");
//		}
//		public void RevertChangesForNextRun(HomePageLib hp)
//		{
//			justSleepFor(2);
//			hp.SelectUserLevel(0);
//			hp.VerifyUserInfoPopUp(expectedInfoTxt);
//			hp.DisableArrhytmia();
//			Assert.assertFalse(hp.CheckIfArrhytmiaIsEnabled(), "Arrhytmia is Disabled");
//			justSleepFor(5);
//		}
//		
//		/*
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
//		public void VerifyAdminMenuOptions()
//		{
//			List <WebElement> AdminOptions = driver.findElements(By.className("listbrdr"));
//			
//			Assert.assertEquals(AdminOptions.get(0).getText(), "My Dashboard");
//			Assert.assertEquals(AdminOptions.get(1).getText(), "Admin Panel");
//			Assert.assertEquals(AdminOptions.get(2).getText(), "Live User Dashboard");
//		}
//		
//}
