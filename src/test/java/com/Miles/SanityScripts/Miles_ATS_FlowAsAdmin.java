package com.Miles.SanityScripts;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.miles.BaseSettings.MilesSettings;
import com.miles.PageLibRepo.ATSPageLib;
import com.miles.PageLibRepo.AdminPageLib;
import com.miles.PageLibRepo.HomePageLib;
import com.miles.PageLibRepo.LiveUserPageLib;
import com.miles.PageLibRepo.LoginPageLib;
import com.miles.PageLibRepo.OPTPageLib;
import com.miles.PageLibRepo.WorkoutDetailsPageLib;
import com.miles.PageObjectRepo.ATSPageObj;
import com.miles.PageObjectRepo.HomePageObj;
import com.miles.Utilities.MilesUtilities;
import com.miles.Utilities.MilesUtilities;

public class Miles_ATS_FlowAsAdmin extends MilesSettings
{	
	 WebDriver driver = null ;
	 LoginPageLib loginObj ;
	 HomePageLib homeObj ;
	 //AdminPageLib adminLib;
	 OPTPageLib OPTPageObj;
	 
	 
	 LocalDate currentDate = LocalDate.now();
		Locale locale = Locale.getDefault();
		String currentMonthAsString = currentDate.getMonth().getDisplayName(
          TextStyle.FULL, 
          Locale.getDefault()
  );
	String weekAbbreviation = currentDate.format(DateTimeFormatter.ofPattern("Eee", locale)).substring(0, 3);
	String CurrentMonth = MilesUtilities.GetShortFormOfMonth(currentMonthAsString.toUpperCase());
	int currentDate1 = currentDate.getDayOfMonth();
	
	 String expectedInfoTxt = "User's information updated successfully." ;
	 String ClassName = this.getClass().getSimpleName().toString();
	 
	 String GeneralInfoContains = "Male";
	 String SerialInfoContains = "Serial Number";
	 
	 String Zone1_Duration = "4h 42m";
	 String Zone2_Duration = "16h 7m";
	 String Zone3_Duration = "1h 19m";
	 String Zone4_Duration = "20 sec";
	 String Zone5_Duration = "1h 21m";
	 
	 String Zone1_Percentage = "20%";
	 String Zone2_Percentage = "69%";
	 String Zone3_Percentage = "6%";
	 String Zone4_Percentage = "<1%";
	 String Zone5_Percentage = "6%";
	 
	 String Added_Recommendation = "Adding Recommendation Through Automation Script By QATeam On- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1;
	 String Editted_Recommendation = "Edited Add Recommendation Through Automation Script By QATeam On- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1;
	 String EneEnv;
	 
	 // String ActivityNotes = "Activity Notes added From Automation Script by QATeam On- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1;
	 @Parameters({ "enivironment" }) 
	 @BeforeMethod
	 
	 private void Initialize(String env)
    {
    try
	 {
		 this.driver = DecideEnvironment(env);
		 
		 loginObj = new LoginPageLib(driver);
		 OPTPageObj = new OPTPageLib(driver);
		 
		 EneEnv = env;
		 if(env.contains("prod"))			//Prod//
		 {
			// homeObj = loginObj.login("manoj.hr@mileseducation.com", "12341234"); // Use the decrypted version temporarily
			homeObj = loginObj.login("manoj.hr@mileseducation.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
			 //MTIzNDEyMzQ=
			 System.out.println("Logging in as Quality : Serverless Production user");
		 }
		 
		 else
		 {   // Regular Prod User		//Stage//
			 //homeObj = loginObj.login("manoj.hr@mileseducation.com", "12341234"); // Use the decrypted version temporarily
			 homeObj = loginObj.login("manoj.hr@mileseducation.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
			 
			 System.out.println("Logging in as Quality user : Regular Stage user");
		 }
		 

	 }
	 catch (Exception e)
	 {
		 System.out.println("Error during login: " + e.getMessage());
		    e.printStackTrace(); // Print the full stack trace
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
		 
		//driver.quit();
	 }
	 
	

@BeforeClass
private void SetEvidenceDir()
{
	MilesUtilities.createDateBasedDirectory();
	// For Jenkins Logs
	System.out.println("******User Level Sanity Test cases will be executed now..******");
	// Read JSON File from Backend Automation here TO-DO
}
	
//@Test(description = "Verify Admin Login")
public void ClearingHomePage() throws InterruptedException

{
	ClearMyCandidateFilter();
}

//@Test(description = "Verify Admin Dropdown Options")
public void AdminDropdownOptions() throws InterruptedException

{
	
	ClearMyCandidateFilter();
	
	driver.findElement(By.className("dropdown-toggle")).click();
	List <WebElement> Options = driver.findElements((By.xpath("//*[contains(@class, 'dropdown-item o_app')]")));
	Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'dropdown-item o_app')]")).isDisplayed());
	Assert.assertEquals(Options.get(0).getText(), "USP Eligibility");
	Assert.assertEquals(Options.get(1).getText(), "Miles ATS");
	Assert.assertEquals(Options.get(2).getText(), "Miles Recruitments");
	Assert.assertEquals(Options.get(3).getText(), "Helpdesk");
	Assert.assertEquals(Options.get(4).getText(), "Discuss");
	Assert.assertEquals(Options.get(5).getText(), "Calendar");
	Assert.assertEquals(Options.get(6).getText(), "My Dashboard");
	Assert.assertEquals(Options.get(7).getText(), "Job Queue");
	Assert.assertEquals(Options.get(8).getText(), "Contacts");
	Assert.assertEquals(Options.get(9).getText(), "Dashboards");
	Assert.assertEquals(Options.get(10).getText(), "Surveys");
	Assert.assertEquals(Options.get(11).getText(), "Employees");
	Assert.assertEquals(Options.get(12).getText(), "Apps");
	Assert.assertEquals(Options.get(13).getText(), "Settings");

}


@Test(description = "Verify Admin Can Enter to Miles Requirement as ATS Module")
public void EntireingtoMilesRequirementATSModule() throws InterruptedException

{
	ClearMyCandidateFilter();
	VerifyInitiateATSPage();
}

	/*
	 * Helper Methods
	 */



public void VerifyInitiateATSPage() throws InterruptedException
{
	driver.findElement(By.className("dropdown-toggle")).click();
	List <WebElement> Options = driver.findElements(By.xpath("//*[contains(@class, 'dropdown-item o_app')]"));
	Options.get(1).click();
	
	Thread.sleep(4000);
}
public void ClearMyCandidateFilter() throws InterruptedException
{
	Thread.sleep(3);
	driver.findElement((By.xpath("//*[contains(@class, 'o_facet_remove oi oi-close btn btn-link opacity-50 opacity-100-hover text-900')]"))).click();
}
public void VerifyEditRecommendation()
{
	driver.findElement(By.xpath("//img[@src='/images/Dropdown_change.png?1954b4fed9b0b4f0befe41fc6c9f36fb']")).click();
	driver.findElement((By.xpath("//*[contains(@class, 'dropdown-item w-time download-wtext')]"))).click();
	driver.findElements(By.id("note-title")).get(1).clear();
	driver.findElements(By.id("note-title")).get(1).sendKeys(Editted_Recommendation);
	driver.findElements(By.xpath("//*[contains(@class, 'btn save-button')]")).get(5).click();
}

public void VerifyAddRecommendationInfoPopUp(String expectedInfoTxt)
{
	Assert.assertTrue(driver.findElement(By.className("mini-toastr-notification__message")).getText().contains(expectedInfoTxt));
}

public void VerifyEditRecommendationInfoPopUp(String editInfoTxt)
{
	Assert.assertTrue(driver.findElement(By.className("mini-toaster__notification -success")).getText().contains(editInfoTxt));
}

public void ViewRecommendation() throws InterruptedException
{
	driver.findElement(By.id("recommendation-view-btn")).click();
	Thread.sleep(3000);

}

public String Recommendation()
{
	return  driver.findElements((By.className("w-title"))).get(0).getText();
}

public void AddRecommendation() throws InterruptedException
{
	 driver.findElement(By.id("recommendation-add-btn")).click();
	 driver.findElements(By.id("note-title")).get(2).click();
	 driver.findElements(By.id("note-title")).get(2).sendKeys(Added_Recommendation);
	 Thread.sleep(3000);
	 driver.findElement(By.id("add-recommendation-popup-submit")).click();
}


public void ScrollToHeartRateZones()
{
	Actions act = new Actions(driver);
	act.moveToElement(driver.findElement((By.xpath("//*[contains(@class, 'hr-zone-distribution')]")))).perform();
	//driver.findElement(By.className("fa fa-plus")).click();
}

public String ReadGeneralInfo() throws InterruptedException
{
	Thread.sleep(2000);
	return driver.findElements(By.xpath("//*[contains(@class,'table table-bordered')]")).get(0).getText();
}

public String ReadSerialNumber() throws InterruptedException
{
	Thread.sleep(2000);
	return driver.findElements(By.xpath("//*[contains(@class,'table table-bordered')]")).get(1).getText();
}

public String ReadBinfileData() throws InterruptedException
{
	Thread.sleep(2000);
	return driver.findElements(By.xpath("//*[contains(@class,'table table-bordered')]")).get(2).getText();
}


public void CloseSideAdminPannel()
{
	driver.findElement((By.xpath("//*[contains(@class, 'sidebar-toggle')]"))).click();
	  
}
	
public void UserBoardLiveUserDashboard()
{
	driver.findElement(By.id("liveUserDashboardBtn")).click();
	MilesUtilities.SwitchTab(1, driver);
}

//protected void WaitForWorkoutDetailsPagetoLoad()
//{
//	MilesUtilities.SwitchTab(3, driver);
//	MilesUtilities.WaitForVisibilityOfElement(driver, "id", "wd-heart-rate-value");
//	wd = new WorkoutDetailsPageLib(driver);
//}

public void UserdashBoardPage()
{
	MilesUtilities.SwitchTab(1, driver);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("current-user-dashboard")));
	System.out.println("Home Dashboard User Name is "+getUserNameOnDashboard());
}

public void InitateAdminPage() throws InterruptedException
{	 
	List <WebElement> Options = driver.findElements(By.className("listbrdr"));
	Options.get(1).click();
	Thread.sleep(6000);
	OPTPageObj = new OPTPageLib(driver);
	
	
}

public String getUserNameOnDashboard()
{
	return driver.findElement(By.className("current-user-dashboard")).getText();
}
//Check12345//

}
	