//package com.Miles.TestScripts;
//import static org.testng.Assert.assertEquals;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.ITestResult;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//import com.miles.BaseSettings.MilesSettings;
//import com.miles.PageLibRepo.HeartHealthPageLib;
//import com.miles.PageLibRepo.HomePageLib;
//import com.miles.PageLibRepo.LoginPageLib;
//import com.miles.PageLibRepo.WorkoutDetailsPageLib;
//import com.miles.Utilities.MilesUtilities;
//
//public class FxWebApp_Regression extends MilesSettings 
//{	
//	 WebDriver driver = null ;
//	 LoginPageLib loginObj ;
//	 HomePageLib homeObj ;
//	
//	 String DeleteTagNotificationText = "Health Entry Deleted: 1";
//	 String ClassName = this.getClass().getSimpleName().toString();
//	 String env = "prod";
//	// @Parameters({ "enivironment" })
//	 @BeforeMethod
//    private void Initialize(String env)
//    {
//	 this.driver = DecideEnvironment(env);
//	 loginObj = new LoginPageLib(driver);
//	 homeObj = loginObj.login("ravikiran@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
//	
//    }
//	
//	 	@BeforeClass
//	 	private void SetEvidenceDir()
//		{
//			MilesUtilities.createDateBasedDirectory();
//			
//		}	 
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
//		 
//		 if (result.getStatus() == ITestResult.SUCCESS && result.getMethod().getMethodName().equals("VerifyHealthEntryAddedAndDisplayed"))
//		 {
//			System.out.println("Deleting the added HeathTag as Clean up for next run");
//			DeleteHealthTag();
//		 } 
//		 
//		 if (result.getStatus() == ITestResult.SUCCESS && result.getMethod().getMethodName().equals("VerifyDeleteTagPopUpText"))
//		 {
//			//System.out.println("Deleting the added HeathTag as Clean up for next run");
//			//homeObj.ClickOnDeleteBtn_Tag();
//		 } 
//		 
//			 driver.quit();
//			 Thread.sleep(2500);
//	 }
//	 
//	 @Test (description = "Purpose of this test is to Verify that user can Login to FX WebApp")
//		public void VerifyUserCanLoginToFxWebApp() throws Exception
//		{
//		
//			    String expectedText= "User Logged In Successfully";
//					
//			    homeObj.VerifyUserInfoPopUp(expectedText);	
//			    justSleepFor(4);
//				homeObj.VerifyLoggedInUserName(UserLevel.RegularUser.toString());
//		}
//		
//	 @Test (description = "Purpose of this test is to Verify User will be notified if password is incorrect ")
//		public void VerifyIncorrectPasswordMsgDisplays() throws Exception
//		{		Thread.sleep(5000);
//			 String expectedText= "Incorrect password, Please try again";
//			 homeObj.logout();
//			 driver.navigate().to("https://app.fourthfrontier.com/#/login");
//			 loginObj = new LoginPageLib(driver);
//			 
//			
//			 loginObj.TryLogin("ravikiran@fourthfrontier.com", "1234123");
//			 loginObj.VerifyUserInfoPopUp(expectedText);
//		
//		}
//
//	 @Test(description = "Purpose of this test is to Verify Elements in Web App for Premium User")
//		public void VerifyHomePageElements_RegularUser() throws Exception
//		{		
//						justSleepFor(4);
//						homeObj.VerifyHomePageElements();
//		}
//		
//	 
//	 @Test (description = "Purpose of this test is to Verify User will be notified if user name is incorrect ")
//		public void VerifyUserNotFoundErrorMsg() throws Exception
//		{		
//			 String expectedText= "User not found";
//			 justSleepFor(3);
//			 homeObj.logout();
//			 justSleepFor(3);
//			 driver.navigate().to("https://app.fourthfrontier.com/#/login");
//			 loginObj = new LoginPageLib(driver);
//			 loginObj.TryLogin("Random@fourthfrontier.com", "12341234");
//			 
//			 loginObj.VerifyUserInfoPopUp(expectedText);	
//		}
//
//
//	 @Test (description = "Purpose of this test is to Verify List of Health Tags displayed")
//		public void VerifyHealthEntryList() throws Exception
//		{				
//				 List<String> HealthTagList = Arrays.asList("Vitals", "Symptom", "Sleep","Medication","Beverage","Food","Supplement","Body Status","Mental Status","Wellness Activity","Environment");
//				 Thread.sleep(6000);
//				 homeObj.ClickOnAddHealtEntry();
//				 homeObj.VerifyHealthTags(HealthTagList);
//				 Thread.sleep(1000);
//		} 
//
//	 @Test (description = "Purpose of this test is to Verify that User Can Add Health Tag and Verifies if its displayed on Web App and Delete the same")
//		public void VerifyHealthEntryAddedAndDeleted() throws Exception
//		{				
//				String ExpectedVal = "SpO2 - 98 %";
//				 Thread.sleep(6000);
//						homeObj.ClickOnAddHealtEntry();
//						homeObj.SelectVitals();
//						homeObj.ClickOnsPO2Tag();
//						homeObj.EnterHealthTagDetails("98","Test");
//						Thread.sleep(4000);
//						homeObj.VerifyHealthTagIsDisplayed(ExpectedVal);
//						
//						// Delete the tag and verify the same
//						Thread.sleep(3000);
//						homeObj.clickOnActivityButton();
//						homeObj.ClickOnDeleteTag();
//						Thread.sleep(2000);
//						//homeObj.VerifyDeletePopUpText();
//						Thread.sleep(3000);
//					//	homeObj.ClickOnDeleteBtn_Tag();
//					//	Assert.assertEquals(homeObj.GetNotificationText(),DeleteTagNotificationText);
//		}
//	 
//		@Test (description = "Purpose of this test is to Verify that user can Download FIT File of lenght 40K and 10K of GPS Data")
//		public void downloadOlderFITFiles() throws Exception
//		{		
//			Map<String, String> FitFiles = new HashMap<>();
//			
//			FitFiles.put("40K","253097.fit");// 253097 is FIT file name which has ~40K GPS Data
//			FitFiles.put("10K","215728.fit");// 215728 is FIT file name which has ~10K GPS Data
//			FitFiles.put("50K", "277803.fit");//277803 is FIT file name which as ~50K GPS Data
//			
//			
//			for (Map.Entry<String,String> FxFit : FitFiles.entrySet())
//			{
//				
//						justSleepFor(4);
//					
//						homeObj.enterWorkout(FxFit.getKey());
//					//	justSleepFor(6);
//						homeObj.clickOnActivityButton();
//						homeObj.clickOnDownloadFIT();
//						justSleepFor(3);
//						Assert.assertTrue(MilesUtilities.isFxFileDownloaded(FxFit.getValue())," Fit File "+FxFit.getValue()+ " of size "+FxFit.getKey()+" downloaded");	
//			}
//		 }
//		/*
//		 * 
//		 * Healper Method
//		 * 
//		 */
//		
//		public void DeleteHealthTag() throws InterruptedException
//		{
//			homeObj.clickOnActivityButton();
//			homeObj.ClickOnDeleteTag();
//			homeObj.ClickOnDeleteBtn_Tag();
//			Thread.sleep(2500);	
//		}
//		
//}
