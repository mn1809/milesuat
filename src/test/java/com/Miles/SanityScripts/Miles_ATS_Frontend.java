package com.Miles.SanityScripts;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import com.miles.BaseSettings.MilesSettings;
import com.miles.PageLibRepo.ATSPageLib;
import com.miles.PageLibRepo.AdminPageLib;
import com.miles.PageLibRepo.HomePageLib;

import com.miles.PageLibRepo.LoginPageLib;
import com.miles.PageLibRepo.OPTPageLib;

import com.miles.PageObjectRepo.atspageObj;
import com.miles.PageObjectRepo.HomePageObj;
import com.miles.PageObjectRepo.OPTPageObj;
import com.miles.Utilities.MilesUtilities;
import com.miles.Utilities.MilesUtilities;

public class Miles_ATS_Frontend extends MilesSettings
{	
	 WebDriver driver = null ;
	 
LoginPageLib loginObj ;
	
 HomePageLib homeObj ;
	
//	protected ATSPageLib atspageObj;
	 
	atspageObj ATSPageLib;
	
	String env;
	
//	ShareActivityPageLib ShareActivityPageObj;
//	 atspageObj = new ATSPageLib(driver);
	 
	 LocalDate currentDate = LocalDate.now();
	 LocalDate tomaroDate = LocalDate.now().plusDays(1);
	 
		Locale locale = Locale.getDefault();
		String currentMonthAsString = currentDate.getMonth().getDisplayName(
          TextStyle.FULL, 
          Locale.getDefault()
  );
	String weekAbbreviation = currentDate.format(DateTimeFormatter.ofPattern("Eee", locale)).substring(0, 3);
	String CurrentMonth = MilesUtilities.GetShortFormOfMonth(currentMonthAsString.toUpperCase());
	int currentDate1 = currentDate.getDayOfMonth();
	//int tomaroDate1 = tomaroDate.getDayOfMonth();


	 
	 
	 String ClassName = this.getClass().getSimpleName().toString(); 


	 
	 String Adding_Commentson_CommunicationTEST = "Adding Comments/Feedback Through Automation Script By QATeam On- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1; 
	 String Adding_Interview_Recording_Link = "Adding Interview Recording Link By QATeam on- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1+"www.YouTube.com";
	 String Adding_CommentsTo_Agenda = "Adding Comments to Agenda By Automation Script For Booked Slots";
	 String Adding_CommentsTo_VISAInterviewFeedback = "Adding Comments to VISA Interview Feedback By Automation Script For Booked VISA Slots";
	 
	 String Adding_LOR_Question1 = "Can you share details about your academic background, major, and any significant achievements?";
	 String Adding_Answer1 = "Adding Answer to 1st Question Through Automation Script By QATeam On- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1;
	 
	 String Adding_LOR_Question2 = "What inspired you to pursue this particular program?";
	 String Adding_Answer2 = "Adding Answer to 2nd Question Through Automation Script By QATeam On- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1;
	 
	 String Adding_LOR_Question3 = "Describe instances where you demonstrated effective communication, collaboration, and leadership in academic or group settings.";
	 String Adding_Answer3 = "Adding Answer to 3rd Question Through Automation Script By QATeam On- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1;
	
	 String Adding_LOR_Question4 = "What skills or qualities do you believe are your strengths, and how have you demonstrated them in the past?";
	 String Adding_Answer4 = "Adding Answer to 4th Question Through Automation Script By QATeam On- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1;
	 
	 String Adding_LOR_Question5 = "Can you share any experiences where you've demonstrated leadership or teamwork skills?";
	 String Adding_Answer5 = "Adding Answer to 5th Question Through Automation Script By QATeam On- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1;
	 
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     
     DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d");
	String Adding_Tomaro_Date = "tomorrow.format(formatter1)";
	
	 String Adding_Skill_Domain = "Adding Skill Domain By QATeam on- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1+"Noun,Pronoun,Verb,Adverb";
	 String Adding_BasicCommunication_TestScore = "95";
	// String Added_Recommendation = "Adding Recommendation Through Automation Script By QATeam On- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1;
	 String Editted_Recommendation = "Edited Add Recommendation Through Automation Script By QATeam On- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1;
	 String EneEnv;
	 
	 // String ActivityNotes = "Activity Notes added From Automation Script by QATeam On- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1;
	 @Parameters({ "enivironment" }) 
	 @BeforeMethod
	 
	 private void Initialize(String env)
    {
    try
	 {
		 this.driver = DecideFrontendEnvironment(env);
		 
	//	System.out.println();
		 
		 loginObj = new LoginPageLib(driver);

		 EneEnv = env;
		 if(env.contains("prod"))			//Prod//
		 {
			
			 System.out.println("Logging in as Quality : Serverless Production user");
		 }
		 
		 else
		 {   // Regular Prod User		//Stage//
			
			 
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

//String Info;
//if (EneEnv.contains("prod"))
//{				//Prod
//	 Info = "manoj.hr@mileseducation.com";
//}
//else
//{			//Stage
//	 Info = "manoj.hr@mileseducation.com";
//}
	
@Test(priority = 1,description = "Verify Admin Login")
public void ClearingHomePage() throws InterruptedException

{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
}

}	