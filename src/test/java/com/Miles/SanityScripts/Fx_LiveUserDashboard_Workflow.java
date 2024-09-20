package com.Miles.SanityScripts;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.miles.BaseSettings.MilesSettings;
import com.miles.PageLibRepo.AdminPageLib;
import com.miles.PageLibRepo.HomePageLib;
import com.miles.PageLibRepo.LiveUserPageLib;
import com.miles.PageLibRepo.LoginPageLib;
import com.miles.PageLibRepo.WorkoutDetailsPageLib;
import com.miles.Utilities.MilesUtilities;

public class Fx_LiveUserDashboard_Workflow extends MilesSettings
{	
	 WebDriver driver = null ;
	 LoginPageLib loginObj ;
	 HomePageLib homeObj ;
	 AdminPageLib AdminPageObj;
	 WorkoutDetailsPageLib wd ;
	 LiveUserPageLib LiveUserObj;
	 String expectedInfoTxt = "User's information updated successfully." ;
	 String ClassName = this.getClass().getSimpleName().toString();
	 String EneEnv;
	 @Parameters({ "enivironment" }) 
	 @BeforeMethod
	 
    private void Initialize(String env)
    {
    try
	 {
		 this.driver = DecideEnvironment(env);
		 
		 loginObj = new LoginPageLib(driver);
		// AdminPageObj = new AdminPageLib(driver);
		 
		 EneEnv = env;
		 if(env.contains("prod")) 
		 {								//PROD//
			 homeObj = loginObj.login("quality+adminProd@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
			 System.out.println("Logging in as Quality : Serverless Production user");
		 }
		 
		 else
		 {   							//STAGE//
			 homeObj = loginObj.login("quality+admin@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
			 System.out.println("Logging in as Quality user : Regular Stage user");
		 }
		 
	 }
	 catch (Exception e)
	 {
		 System.out.println("Failed to Initialize due to exception "+e.getMessage());
		 System.out.println("\n Full trace : "+e.getCause());
		 
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
	// For Jenkins Logs
	System.out.println("******User Level Sanity Test cases will be executed now..******");
	// Read JSON File from Backend Automation here TO-DO
}
	

@Test (description = "Verify Admin Panel Icon")
public void VerifyAdminPanel() throws Exception
{				
	homeObj.VerifyOpenAdminPanel();
}

@Test (description = "Verify Admin Panel Options")
public void VerifyAdminPanelOptions()
{
	homeObj.VerifyOpenAdminPanel();
	List <WebElement> Options = driver.findElements(By.className("listbrdr"));
	Assert.assertTrue(driver.findElement(By.id("menu")).isDisplayed());
	Assert.assertEquals(Options.get(0).getText(), "My Dashboard");
	Assert.assertEquals(Options.get(1).getText(), "Admin Panel");
	Assert.assertEquals(Options.get(2).getText(), "Live User Dashboard");
	Assert.assertEquals(Options.get(3).getText(), "Approve Reports");
}
	
@Test (description = "Verify Intiate LiveUserDashboard")
public void Verify_Intiate_LiveUserDashboardPage() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateLiveDashboard();
	CloseSideAdminPannel();
	LiveUserObj.VerifyLiveUserScreenElements();
	
}

@Test (description = "Verify HideMap Dashboard")
public void Verify_HideMap_window() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateLiveDashboard();
	CloseSideAdminPannel();
	LiveUserObj.clickOnHideMap();
}

@Test (description = "Verify LiveECGs Dashboard")
public void Verify_LiveECGs_Window() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateLiveDashboard();
	CloseSideAdminPannel();
	LiveUserObj.clickOnGroupLiveECG();
	LiveECG_dashBoardPage();
	Assert.assertTrue(driver.findElement(By.className("form-search")).isDisplayed(),"Search Box is Diplayed");
}

	/*
	 * Helper Methods
	 */

public void CloseSideAdminPannel()
{
	driver.findElement((By.xpath("//*[contains(@class, 'sidebar-toggle')]"))).click();
	  
}

public void LiveECG_dashBoardPage() throws InterruptedException
{
	Thread.sleep(5000);
	MilesUtilities.SwitchTab(1, driver);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
}
	
public void InitateLiveDashboard() throws InterruptedException
{	 
	List <WebElement> Options = driver.findElements(By.className("listbrdr"));
	Options.get(2).click();
	Thread.sleep(6000);
	//AdminPageObj = new AdminPageLib(driver);
	LiveUserObj = new LiveUserPageLib(driver);
}

}
	