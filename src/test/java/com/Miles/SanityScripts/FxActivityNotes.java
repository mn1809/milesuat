package com.Miles.SanityScripts;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import com.miles.PageLibRepo.WorkoutDetailsPageLib;
import com.miles.Utilities.MilesUtilities;
public class FxActivityNotes extends MilesSettings 
{	
	 WebDriver driver = null ;
	 LoginPageLib loginObj ;
	 HomePageLib homeObj ;
	 WorkoutDetailsPageLib workoutObj ;
	 HashMap<String, String> Average_Data;
	 HashMap<String, String> Maximum_Data;
	 HashMap<String, String> Minimum_Data;
	 LocalDate currentDate = LocalDate.now();
		Locale locale = Locale.getDefault();
		String currentMonthAsString = currentDate.getMonth().getDisplayName(
             TextStyle.FULL, 
             Locale.getDefault()
     );		
				
		String weekAbbreviation = currentDate.format(DateTimeFormatter.ofPattern("Eee", locale)).substring(0, 3);
   		String CurrentMonth = MilesUtilities.GetShortFormOfMonth(currentMonthAsString.toUpperCase());
		int currentDate1 = currentDate.getDayOfMonth();
		String ExpectedActivityName = "Rest on "+ weekAbbreviation+", "+ CurrentMonth+" "+currentDate1+" at 03:33 PM";
		String ClassName = this.getClass().getSimpleName().toString();

	//--------------------------------BELOW DETAILS ARE INDIVIUAL WORKOUT DETAILS-------------------//	
				
		String ActivityNotes = "Activity Notes added From Automation Script by QATeam On- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1;
		String EneEnv;

	 @Parameters({ "enivironment" })
	 @BeforeMethod
	 
    private void Initialize(String env)
    {
		 
		 try
		 {
			 this.driver = DecideEnvironment(env);
			 
			 loginObj = new LoginPageLib(driver);
			
			 
			 
			 EneEnv = env;
			 if(env.contains("prod"))
			 {							//PROD//
				 homeObj = loginObj.login("quality+24Prod@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
				 System.out.println("Logging in as Quality : Serverless Production user");
			 }
			 
			 else
			 {  						 //STAGE//
				 homeObj = loginObj.login("quality+24@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
				 System.out.println("Logging in as Quality user : Regular Stage user");
			 }
			 

		 }
		 catch (Exception e)
		 {
			 System.out.println("Failed to Initialize due to exception "+e.getMessage());
			 System.out.println("\n Full trace : "+e.getCause());
			 
		 }
	 	 
    }
	 
	/*
	  * After Every Method script will hit this section of code
	  * Where it'll decide to take screenshot and save on failure 
	  * or
	  * prints the name of passed test case as log (jenkin's console)
	  */

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
		 System.out.println("driver.quit being called");
		 driver.quit();
	 }
	
	 
@BeforeClass
private void SetEvidenceDir()
{
	MilesUtilities.createDateBasedDirectory();
	// For Jenkins Logs
	System.out.println("******24 hours Activity verification Test cases will be executed now..******");
	// Read JSON File from Backend Automation here TO-DO
}
	
	@Test (priority = 1,description = "Purpose to Test verifiy Activity Notes is Visible")
	public void VerifyActivityNotesSection()
	{
		homeObj.OpenFirstActivity();
		WaitForWorkoutDetailsPagetoLoad();
		ScrollToActitivyNotes();
		Assert.assertTrue(driver.findElements((By.xpath("//*[contains(@class, 'activity-notes')]"))).get(0).isDisplayed(),"Activity text area");
		
	}
	
	@Test (priority = 2, description = "Purpose to Test verifiy Activity Notes Screen")
	public void VerifyActivityNotesTextAreaAndElements() throws InterruptedException
	{
		homeObj.OpenFirstActivity();
		WaitForWorkoutDetailsPagetoLoad();
		ScrollToActitivyNotes();
		Thread.sleep(2000);
		driver.findElement(By.className("add-notes")).click();
		Assert.assertTrue(driver.findElements(By.className("form-modal")).get(0).isDisplayed(),"Activity section displayed");
		Assert.assertFalse(driver.findElements((By.xpath("//*[contains(@class, 'btn save-button')]"))).get(0).isEnabled(),"Save button is disabled");
	}
	
	@Test (priority = 3,description  = "Purpose to Test Add Activity Notes")
	public void VerifyAddActivityNotes() throws InterruptedException
	{
		homeObj.OpenFirstActivity();
		WaitForWorkoutDetailsPagetoLoad();
		ScrollToActitivyNotes();
		Thread.sleep(2000);
		driver.findElement(By.className("add-notes")).click();
		driver.findElement(By.id("note-title")).click();
		driver.findElement(By.id("note-title")).sendKeys(ActivityNotes);
		driver.findElements((By.xpath("//*[contains(@class, 'btn save-button')]"))).get(0).click();
		Thread.sleep(3000);		
		System.out.println("Added notes is "+driver.findElement(By.className("activity-notes")).getText());
	}
	
	@Test (dependsOnMethods = "VerifyAddActivityNotes",description = "Purpose to Verify Added Activity Note in Home Page")
	public void VerifyReaddActivityNotes()
	{
		Assert.assertEquals(driver.findElement(By.className("workout_notes")).getText(), ActivityNotes);	
	}
	
	
	/*
	 * Helper Methods
	 */

	public void ScrollToActitivyNotes()
	{
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement((By.xpath("//*[contains(@class, 'activity-notes')]")))).perform();
		
	}
	protected void WaitForWorkoutDetailsPagetoLoad()
	{
		MilesUtilities.SwitchTab(1, driver);
		MilesUtilities.WaitForVisibilityOfElement(driver, "id", "wd-heart-rate-value");
		workoutObj = new WorkoutDetailsPageLib(driver);
	}
	
}
	
//CHECKING GIT//


