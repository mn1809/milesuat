package com.Miles.SanityScripts;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.miles.BaseSettings.MilesSettings;
import com.miles.PageLibRepo.LoginPageLib;
import com.miles.Utilities.MilesUtilities;

public class FxSanity_LoginPage extends MilesSettings
{	
	 WebDriver driver = null ;
	 LoginPageLib login ;
	// HomePageLib homeObj ;
	 String expectedInfoTxt = "User's information updated successfully." ;
	 String ClassName = this.getClass().getSimpleName().toString();
	 @Parameters({ "enivironment" })
	 @BeforeMethod
    private void Initialize(String env)
    {
	 this.driver = DecideEnvironment(env);
	 login = new LoginPageLib(driver);
	// homeObj = login.login("trainer2@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
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


	 @Test (description = "Purpose of this test is to Verify that Login Page Elements")
		public void VerifyLoginPageElements()
		{
		
			justSleepFor(2);
			login.VerifyLoginPageElements();
		}
		
	 
	@Test (description = "Purpose of this test is to Verify User will be notified if password is incorrect ")
		public void VerifyIncorrectPasswordMsgDisplays() throws Exception
		{		Thread.sleep(5000);
			 String expectedText= "Email or password is incorrect, your 4 remaining attempts left";
			
			 login.TryLogin("ravikiran@fourthfrontier.com", "1234123");
			 login.VerifyUserInfoPopUp(expectedText);
		
		}

		
		@Test (description = "Purpose of this test is to Verify User will be notified if user name is incorrect ")
		public void VerifyUserNotFoundErrorMsg() throws Exception
		{		
			 String expectedText= "Email or password is incorrect";
			 justSleepFor(3);
			 
			 login.TryLogin("Random@fourthfrontier.com", "12341234");
			 
			 login.VerifyUserInfoPopUp(expectedText);	
		}

		
}
