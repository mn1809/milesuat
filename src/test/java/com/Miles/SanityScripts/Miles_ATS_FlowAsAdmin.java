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

public class Miles_ATS_FlowAsAdmin extends MilesSettings
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
	
	 String Name = "Automation-User1";
	 String expectedInfoTxt = "Uni";

	// String ExpectedCanID = "B-810987678";
	 String ExpectedEmail = "milesautomation@mileseducation.com";
	 
//	 String ExpectedEligibilityCode = "Eligibility/B/24/09/115";
//	 String ExpectedEnrollmentStatus = "New Student";
//	 String ExpectedEligibilityType = "Eligibility not done";
	 
	 
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
		 this.driver = DecideEnvironment(env);

		 
		 loginObj = new LoginPageLib(driver);
		 
		
		 
		 EneEnv = env;
		 if(env.contains("prod"))			//Prod//
		 {
			homeObj = loginObj.login("manoj.hr@mileseducation.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
			 //MTIzNDEyMzQ=
			 System.out.println("Logging in as Quality : Serverless Production user");
		 }
		 
		 else
		 {   // Regular Prod User		//Stage//
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

@Test(priority = 2,description = "Verify Admin Dropdown Options")
public void AdminDropdownOptions() throws InterruptedException

{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();

	if (EneEnv.contains("prod"))
	{
		ATS.VerifyHomeMenuOptionsProd();
	}
	else
	{
		ATS.VerifyHomeMenuOptionsStage();
	}
}

@Test(priority = 3,description = "Verify Admin Can Enter to ATS Module")
public void EntireingtoMilesRequirementATSModule() throws InterruptedException

{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
}

@Test (priority = 4,description = "Verify ATS Module Configuration Options")
public void ATSConfigurationDropdownOptions () throws InterruptedException

{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	if (EneEnv.contains("prod"))
	{
		ATS.VerifyATSCOnfigurationOptionsProd();
	}
	else
	{
		ATS.VerifyATSCOnfigurationOptionsStage();
	}
	
}


@Test (priority = 5,description = "Verify ALLOCATING GM  in U7A Bucket.")
public void U7AAllocatingGMCandidate() throws InterruptedException

{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	SearchU7ACnadidate();
	
	driver.findElement(By.xpath("//*[contains(@title, 'Lead Allocation')]")).click();
	Thread.sleep(4000);
	
	driver.findElements(By.xpath("//*[contains(@role, 'menuitem')]")).get(1).click();
	Thread.sleep(2000);
	driver.findElement(By.className("o_searchview_input")).click();
	Thread.sleep(2000);
	driver.findElement(By.className("o_searchview_input")).sendKeys("Automation-User1");
	Thread.sleep(4000);
//	List <WebElement> Options = driver.findElements((By.xpath("//b[normalize-space()='Student']")));
//	Options.get(0).click();
	
	driver.findElement((By.xpath("//b[normalize-space()='Student']"))).click();
	 Thread.sleep(4000);
		driver.findElement(By.id("checkbox-comp-1")).click();
		 Thread.sleep(3000);
		 
		driver.findElement(By.xpath("//*[contains(@name, 'open_wiz_allocate_gm_spoc_to_lead')]")).click();
		Thread.sleep(3000);
		
		WebElement Gm =	driver.findElements(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).get(0);   //------------ADDING GENERAL MANAGER-------------//
		
		Gm.click();
		Thread.sleep(2000);
		Gm.sendKeys("Manoj Expert");
		Thread.sleep(2000);
		List <WebElement> Options1 = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
		Options1.get(0).click();
		Thread.sleep(2000);
	
		
		WebElement Spoc = driver.findElement(By.id("gm_spoc_id"));        //--------------ADDING SPOC------------//
		Spoc.click();
		Thread.sleep(2000);
		Spoc.sendKeys("Manoj Spoc");
		Thread.sleep(2000);
		List <WebElement> Options2 = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
		Options2.get(0).click();
		Thread.sleep(2000);
		
		driver.findElement((By.xpath("//*[contains(@name, 'action_allocate_gm_to_leads')]"))).click();  //-----------------------Final OK ALLOCTING---------------------//
		Thread.sleep(10000);
}

@Test (priority = 6,description = "Verify ATS Module Entering UG Education Details.")
public void U7UGCandidate() throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	SearchU7ACnadidate();
	Thread.sleep(3000);
	EnteringUGGraduationDetails();										//--------------------------------Entering UG Graduation Details Mind it-------------------------------//
}


@Test (priority = 7,description = "Verify ATS Module Entering PG Education Details.")
public void U7PGCandidate() throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	SearchU7ACnadidate();
	EnteringPGGraduationDetails();										//--------------------------------Entering PG Graduation Details Mind it-------------------------------//
}

@Test (priority = 8, description = "Verify Adding Certifications for Candidate")

public void U7ACertifications() throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();										//----------------------------------------Entering Certifications Details----------------------------------//
	SearchU7ACnadidate();
	ATS.EnteringCertificationDetails();
}

@Test (priority = 9, description = "Verify Adding Work Expecrience for Candidate")
public void U7AWorkExperience() throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();										//----------------------------------------Entering Work Experience Details----------------------------------//
	SearchU7ACnadidate();
	ATS.EnteringWorkExperience();
}


@Test (priority = 10, description = "Veridy Auto University Recommendation For university")
public void U7PRecommendUniversity() throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();										//----------------------------------------Entering Auto Recommend University----------------------------------//
	SearchU7ACnadidate();
	ATS.AutoRecommendUniversity();

}

// (priority = 8,description = "Verify ATS Module U7A Candidate Bucket")
//public void U7ACandidateWindow() throws InterruptedException
//{
//	
//	ATSPageLib ATS = new ATSPageLib(driver);					//-------------------------DONT SUe this------------------------//
//	ATS.ClearMyCandidateFilter();
//	ATS.VerifyInitiateATSPage();
//	SearchU7ACnadidate();
//	CandidateU7ADetails();
//}
	
@Test (priority = 11,description  = "Verify ATS Module Candidate Allocation For GM")

public void U7EnrolledLeadAllocation() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(3000);								//--------------------NEED TO BE ADDRESSED-----------------//
	SearchU7ACnadidate();
	Thread.sleep(3000);
	//CandidateU7AllocationProcess();
	
}

@Test (priority = 12,description = "Verify ATS Module Candidate U7 Enrolled Bucket")

public void U7EnrolledBucket()throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(3000);
	SearchU7EnrolledCandidate();

}

@Test (priority = 13,description = "Verify ATS Module Candidate U7 Reallocate")

public void CheckingU7Reallocate() throws InterruptedException
{	
	
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(3000);
	ATS.TabsbuttonOnU7Enrolled();
	Thread.sleep(3000);
	ATS.ReAllocate();
}

@Test (priority = 14, description = "Verify Candidate Basic Details in U7 Enrolled Bucket")

public void CandidateBasicDetails() throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(3000);
	ATS.TabsbuttonOnU7Enrolled();
	ATS.ScrollToBasicDetails();
	//BasicCandidateDetails();

}

@Test (priority = 15, description = "Verify Updating Candidate Communication Test Result")

public void UpdatingCommunicationTestResult() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	ATS.TabsbuttonOnU7Enrolled();
	Thread.sleep(3000);
//	CommunicationTestFlow();				//---------------------------------------------------Communication Test Flow------------------------//
	
}

@Test (priority = 16, description = "Booking Expert Session From SPOC to Candidate")
public void VerifyAllocatingBookingExpertSession() throws InterruptedException, AWTException
{
	
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	ATS.TabsbuttonOnU7Enrolled();
	Thread.sleep(3000);
	if (EneEnv.contains("prod"))
	{
		ATS.TooBookExpertSessionProd();
	}
	else
	{
		ATS.TooBookExpertSessionStage();
	}
	Thread.sleep(3000);
	ATS.TooBookTimeSlot();
	Thread.sleep(3000);

	
}
//------------------------Needed to be added the Recommend university--------------------//

@Test (priority = 17, description = "Booking Expert Session U7 window")
public void VerifyAllocatingBookingExpert() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.TabsbuttonOnU7Enrolled();
	Thread.sleep(2000);
	BookExpertSessionU7Window();
	Thread.sleep(10000);
}


@Test (priority = 18, description = "U7+ Expert Session Booked")

public void VerifyU7PlusExpertSeesionBooked() throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	ATS.TabsbuttonOnU7PlusEnrolled();
	Thread.sleep(3000);
	
	ATS.ScrollToMeetingTab();
	Thread.sleep(3000);
	ATS.StartMeeting();
	Thread.sleep(3000);
	ATS.SwitchtoBLueButton();
	Thread.sleep(3000);
	ATS.EndMeeting();
	

}

@Test (priority = 19, description = "U7+ Expert Session Booked Eligible Candidate")

public void VerifyU7PlusExpertSeesionEligibleCandidate() throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(3000);
	ATS.TabsbuttonOnU7PlusEnrolled();
	Thread.sleep(3000);

	ATS.RecommendationCompleted();
	Thread.sleep(3000);
	
	
}

@Test (priority = 20, description = "U7+ Batch Intake with Elgible")
public void VerifyU7PlusBatchIntake() throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	ATS.TabsbuttonOnU7PlusEnrolled();
	Thread.sleep(3000);
	
	ATS.U7PLusEligibleTheCandidate();  //---------------------EVEN With INtake has been added ---------------------//
}

@Test (priority = 21, description = "U7+ Recommend University To Candidate")
public void VerifyU7PlusRecommendUniversity() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	ATS.TabsbuttonOnU7PlusEnrolled();
	Thread.sleep(3000);
	ATS.ScrollToMeetingTab();
	Thread.sleep(3000);
//	ATS.Recommenduniversity();
//	Thread.sleep(3000);
//	ATS.U7PLusEligibleTheCandidate();

}

@Test (priority = 22, description = "U8 Expert Session Done Bucket")

public void VerifyU8Bucket() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);

	ATS.U8bucket();
	Thread.sleep(3000);
	ATS.MSASigned();
}

@Test (priority = 23, description = "U9 MSA SIGNED")

public void VerifyU9Stage1Bucket() throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U9bucketStage1();
	Thread.sleep(2000);
	ATS.UniversitySelection();
	
}

@Test (priority = 24, description = "U9 MSA SIGNED LOR and SOP")

public void VerifyU9Stage2LORandSOPBucket() throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	Thread.sleep(2000);
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	
	ATS.U9bucketStage2LOR();
	Thread.sleep(2000);
	ATS.ScrollToLORatU9();
	Thread.sleep(4000);
	LoRResponses();
	Thread.sleep(5000);
	ATS.StudentLORandSOP();

}

@Test (priority = 25, description = "U9 MSA SIGNED")

public void VerifyU9Stage3ApplicationProofBucket() throws InterruptedException, AWTException

{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	
	ATS.U9Stage3();
	Thread.sleep(2000);
	ATS.UploadationOfApplicationProof();
}

@Test (priority = 26, description = "U9+ Application Submitted Process")

public void VerifyU9plusStage1Bucket() throws InterruptedException, AWTException

{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U9PlusBucket();
	Thread.sleep(2000);
	ATS.UPPlusReuploading(); 
	Thread.sleep(5000);
	ATS.U9VerifyDocuments();
	
}

@Test (priority = 27, description= "U9+ Application Initiated")

public void VerifyU9plusStage2Bucket() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U9plusVerifyApplicationSubmitted();
	Thread.sleep(5000);
	ATS.U9PlusAddingMOI();

}

@Test (priority = 28, description= "U10 Upload Offer Letter")

public void VerifyU10Bucket() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(3000);
	ATS.U10BucketStage1();

}

@Test (priority = 29, description= "U10 Approve Offer Letter")

public void VerifyU10Stage2Bucket() throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(3000);	
	ATS.U10BucketStage2();

}

@Test (priority = 30, description= "U10 Final Approver of Offer Letter")

public void VerifyU10Stage3Bucket() throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U10BucketStage3();
	Thread.sleep(3000);
}

@Test (priority = 31, description = "U11 Miles Pathway Funding")

public void VerifyU11Stage1Bucket()throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U11BucketStage1();
}

@Test (priority = 32, description = "U11 Miles Pathway Fee Received")
public void verifyU11Stage2Bucket() throws InterruptedException

{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U11BucketStage2();	
}

@Test (priority = 33, description = "U12 Miles US Pathway Funding Type")
public void verifyU12Bucket() throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U12BucketUSFundingType();
	
}

@Test (priority = 34, description = "U13A Miles US Funding Bucket Stage1")

public void VerifyU13ABucketStage1() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	
	ATS.U13ABucketStage1USFunding();
	Thread.sleep(3000);
	ATS.DocumentCollectionforUSFundingDocuments();

}

@Test (priority = 35, description = "U13A Miles US Funding Bucket Stage2 Approving US Funding Documnents")

public void VerifyU13ABucketStage2() throws InterruptedException, AWTException
{
	
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U13ABucketStage2USFunding();
	Thread.sleep(2000);
	ATS.ApprovingCollectedDocumentsU13A();

}

@Test (priority = 36, description = "U13B Miles US Funding Financial Skips Bucket Stage 1")

public void VerifyU13BBucketStage1() throws InterruptedException

{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U13BBucketSkipFinancialStage1();//----Stage1----//

}

@Test (priority = 37, description = "U13B Miles US Funding Uploading Funding Proof Bucket Stage 2")
public void VerifyU13BBucketStage2() throws InterruptedException, AWTException

{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U13BBucketStage2(); //----------Stage2---Uploading Funding Proof------------//
}



@Test (priority = 38, description = "U13B Miles US Funding Approvving US Finace Proof Bucket Stage 3")
public void VerifyU13BBucketStage3() throws InterruptedException, AWTException

{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U13BBucketStage3(); //---------Approvving US Finace Proof-------------//
}

@Test (priority = 39, description = "U13C Miles Fincial Submitted to US Unversity Stage1")
public void VerifyU13CBucketStage1() throws InterruptedException, AWTException

{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U13CBucketStage1(); //-----------------------------Uploading Copy Of I20 Documents-----------------//
}

@Test (priority = 40, description = "U13C Miles Fincial Submitted to US Unversity Stage2")
public void VerifyU13CBucketStage2() throws InterruptedException

{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U13CBucketStage2();//------------------------Approving I20 Documents----------------------//
}

@Test (priority = 41, description = "U14 Miles Fincial Submitted to US Unversity Stage1")
public void VerifyU14BucketSatge1() throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U14BucketStage1();
}

@Test (priority = 42, description = "U14 Miles Fincial Submitted to US Unversity Stage2")
public void VerifyU14BucketStage2() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U14BucketStage2();//-----------------------------Uploading DS160 Documents--------------------//
}

@Test (priority = 43, description = "U14 Miles Fincial Submitted to US Unversity Stage3")
public void VerifyU14BucketStage3() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U14BucketStage3();//-------------------------------------Approving DS160--------------------------//
}	


@Test (priority = 44, description = "U15 Miles DS160 Submitted")
public void VerifyU15BucketStage1() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U15BucketStage1MandateFeilds();//---------------------------------Negative FLow For Mandate Feilds---------------//
}

@Test (priority = 45, description = "U15 Miles DS160 Submitted Submitting VISA Slot Details ")
public void VerifyU15BucketStage2VISASlotDetails() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U15BucketStage2UploadingVISADetails();	//-------------------------Uploading VISA SLOT Details and Documents----------------------//
}	


@Test (priority = 46, description = "U15 Miles DS160 Submitted Approving VISA Slot Details ")
public void VerifyU15BucketStage3VISASlotDetails() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U15BucketStage3ApprovingVISADetails();//------------------------------Approving VISA Slot Details and Documents--------------------//
}

@Test (priority = 47, description = "U16 Miles Too Book VISA Slot")
public void VerifyU16BucketStage1() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U16Bucket();
	Thread.sleep(3000);
	ATS.TooBookVISASlot();				//----------------------VISA Booking Slot---------------------//
}

@Test (priority = 48, description = "U16 Miles Book Visa Mock Interview")
public void VerifyU16BucketStage2() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	U16TooBookVISAMock();
}


@Test (priority = 49, description = "U16 Miles Attending Visa Mock Interview")
public void VerifyU16BucketStage3() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	U16ToAttendingMeeting();
}

@Test (priority = 50, description = "U16 Miles Book Visa Received")
public void VerifyU16BucketStage4() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U16VisaRecevied();
}

@Test (priority = 51, description = "U17 Miles Bucket Uploading Visa Proof")
public void VerifyU17BucketStage1() throws InterruptedException, AWTException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage();
	Thread.sleep(2000);
	ATS.U17Stage1UploadingVISA();	 //-------------------Uploading VISA Proof--------------------//
}

@Test (priority = 52, description = "U17 Miles Bucket Approving Visa Proof")
public void VerifyU17BucketStage2() throws InterruptedException, AWTException
{	
	
	ATSPageLib ATS = new ATSPageLib(driver);
	ATS.ClearMyCandidateFilter();
	ATS.VerifyInitiateATSPage(); 
	Thread.sleep(2000);
	ATS.U17Stage2ApprovingVISA();		//-------------------------------------Approving VISA--------------------------//	
}

	/*
	 * Helper Methods
	 */

/*
 * Helper Methods
 */

public void CandidateData() throws InterruptedException
{
	driver.findElement(By.className("o_searchview_input")).click();
	driver.findElement(By.className("o_searchview_input")).sendKeys("Automation-User1");
	Thread.sleep(3000);
}

public void CandidateData1() throws InterruptedException

{
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-light o_switch_view o_list oi oi-view-list')]")).click();
	Thread.sleep(2000);
	driver.findElement(By.className("o_searchview_input")).click();
	Thread.sleep(2000);
	driver.findElement(By.className("o_searchview_input")).sendKeys("Automation-User1");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@name, 'can_id')]")).click();
}


public void EnteringUGGraduationDetails() throws InterruptedException
{
	driver.findElement(By.xpath("//*[contains(@name, 'education_details')]")).click();
	Actions act1 = new Actions(driver);
	act1.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'telephony_call_logs')]"))).perform();
	Thread.sleep(2000);
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn oe_subtotal_footer btn-primary')]")).click();
	
	driver.findElement(By.id("graduation_level")).click();
	
	//Alternative way to select the other options//
		WebElement paymentDropdown = driver.findElement(By.id("graduation_level"));

	    // Create a Select object for the dropdown
	    Select select = new Select(paymentDropdown);

	    // Select the option by value
	    select.selectByValue("\"ug\""); //Loan Support in edit Miles Pathway Funding // Ensure to include the exact value here

	    // OR: Select the option by visible text     // select.selectByVisibleText("Self support");

	    WebElement selectedOption = select.getFirstSelectedOption();
	    System.out.println("Selected option is: " + selectedOption.getText());
	    Thread.sleep(3000);
	    //-------------------------Graduation--------------------//
	    driver.findElement(By.id("graduation_id")).click();
	    driver.findElement(By.id("graduation_id")).sendKeys("Bachelor of Engineering");
	    Thread.sleep(2000);
	    List <WebElement> Graduation = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
	    Graduation.get(0).click();
	    
		//-------------------------Unversity--------------------------//
		
		 driver.findElement(By.id("university_id")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.id("university_id")).sendKeys("Acharya Nagarjuna University, Guntur (Id: U-0003)");
		 Thread.sleep(2000);
		  List <WebElement> Unversity = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
		  Unversity.get(0).click();
		    
		//----------------------------------------Years of Graduation-------------------------------//    
		  
		  driver.findElement(By.id("year_of_graduation_date")).click();
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("//*[contains(@class, 'year old')]")).click();
		     Thread.sleep(2000);
		     driver.findElement(By.xpath("//span[@data-action='selectMonth' and @class='month']")).click();
		     Thread.sleep(2000); 
		     driver.findElement(By.xpath("//*[contains(@data-day, '01/18/2019')]")).click();
		     Thread.sleep(2000); 
		   //----------------------------------------------NAAC Grade------------------------//  
		     
		     driver.findElement(By.id("naac_grade")).click();
		     Thread.sleep(2000);
		 		WebElement NAAC = driver.findElement(By.id("naac_grade"));
		 	    Select select1 = new Select(NAAC);

		 	    select1.selectByValue("\"app\""); //Loan Support in edit Miles Pathway Funding // Ensure to include the exact value here
		 	    WebElement selectedOption1 = select1.getFirstSelectedOption();
		 	    System.out.println("Selected option is: " + selectedOption1.getText());
		 	    Thread.sleep(3000);
	//-------------------------------------------------GraduatioStatus---------------------------//	     
		 	   driver.findElement(By.id("graduation_status")).click();
		 	  Thread.sleep(2000);
		 		WebElement GraduatioStatus = driver.findElement(By.id("graduation_status"));
		 	    Select select2 = new Select(GraduatioStatus);

		 	    select2.selectByValue("\"2\""); 				 // Ensure to include the exact value here
		 	    WebElement selectedOption2 = select2.getFirstSelectedOption();
		 	    System.out.println("Selected option is: " + selectedOption2.getText());
		 	    Thread.sleep(3000);   
		 	    
	//----------------------------------------Rank------------------------------//
		 	    
		 	   driver.findElement(By.id("graduation_rank")).click();
		 	  Thread.sleep(2000);
		 		WebElement Rank = driver.findElement(By.id("graduation_rank"));
		 	    Select select3 = new Select(Rank);

		 	    select3.selectByValue("\"1\""); //Loan Support in edit Miles Pathway Funding // Ensure to include the exact value here
		 	    WebElement selectedOption3 = select3.getFirstSelectedOption();
		 	    System.out.println("Selected option is: " + selectedOption3.getText());
		 	    Thread.sleep(3000);  

		 	//------------------------------------------------------Division-------------------//
		 	    
		 	   driver.findElement(By.id("graduation_rank_id")).click();
		 	  Thread.sleep(2000);
		 	 driver.findElement(By.id("graduation_rank_id")).sendKeys("First Division");
		 	   Thread.sleep(3000); 
		 	List <WebElement> Division = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
		 	Division.get(0).click();
		    Thread.sleep(3000); 
		
		    //-----------------------------------------------------Overall Percentage%-------------------------------
		WebElement Percentage = driver.findElement(By.id("graduation_percentage"));
		Percentage.click();
		 Thread.sleep(2000);
		Percentage.clear();
		
		Percentage.sendKeys("85");
		    
		    
		 	//---------------------------------NAACSTATUS---------------------------//    
		 	   driver.findElement(By.id("naac_status")).click();
		 	  Thread.sleep(2000);
		 		WebElement NAACSTATUS = driver.findElement(By.id("naac_status"));
		 	    Select select5 = new Select(NAACSTATUS);

		 	    select5.selectByValue("\"yes\""); //Loan Support in edit Miles Pathway Funding // Ensure to include the exact value here
		 	    WebElement selectedOption5 = select5.getFirstSelectedOption();
		 	    System.out.println("Selected option is: " + selectedOption5.getText());
		 	    Thread.sleep(3000);    
		 	    
		 	   
		 	 driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary o_form_button_save')]")).click();  
}

public void EnteringPGGraduationDetails() throws InterruptedException
{
	driver.findElement(By.xpath("//*[contains(@name, 'education_details')]")).click();
	Actions act1 = new Actions(driver);
	act1.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'telephony_call_logs')]"))).perform();
	Thread.sleep(2000);
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn oe_subtotal_footer btn-primary')]")).click();
	 Thread.sleep(2000);
	driver.findElement(By.id("graduation_level")).click();
	
	//Alternative way to select the other options//
		WebElement paymentDropdown = driver.findElement(By.id("graduation_level"));

	    // Create a Select object for the dropdown
	    Select select = new Select(paymentDropdown);

	    // Select the option by value
	    select.selectByValue("\"pg\""); //Loan Support in edit Miles Pathway Funding // Ensure to include the exact value here

	    // OR: Select the option by visible text     // select.selectByVisibleText("Self support");

	    WebElement selectedOption = select.getFirstSelectedOption();
	    System.out.println("Selected option is: " + selectedOption.getText());
	    Thread.sleep(3000);
	    //-------------------------Graduation--------------------//
	    driver.findElement(By.id("graduation_id")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.id("graduation_id")).sendKeys("Master of Engineering");
	    Thread.sleep(2000);
	    List <WebElement> Graduation = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
	    Graduation.get(0).click();
	    
		//-------------------------Unversity--------------------------//
		
		 driver.findElement(By.id("university_id")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.id("university_id")).sendKeys("Acharya Nagarjuna University, Guntur (Id: U-0003)");
		 Thread.sleep(2000);
		  List <WebElement> Unversity = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
		  Unversity.get(0).click();
		    
		//----------------------------------------Years of Graduation-------------------------------//    
		  
		  driver.findElement(By.id("year_of_graduation_date")).click();
		  Thread.sleep(2000);
		//  driver.findElement(By.xpath("//*[contains(@class, 'year old')]")).click();//span[normalize-space()='2021']
		   driver.findElement(By.xpath("//span[normalize-space()='2021']")).click(); 
		  Thread.sleep(2000);
		     driver.findElement(By.xpath("//span[@data-action='selectMonth' and @class='month']")).click();
		     Thread.sleep(2000); 
		     driver.findElement(By.xpath("//*[contains(@data-day, '01/18/2021')]")).click();
		     Thread.sleep(2000); 
		   //----------------------------------------------NAAC Grade------------------------//  
		     
		     driver.findElement(By.id("naac_grade")).click();
		     Thread.sleep(2000);
		 		WebElement NAAC = driver.findElement(By.id("naac_grade"));
		 	    Select select1 = new Select(NAAC);

		 	    select1.selectByValue("\"app\""); //Loan Support in edit Miles Pathway Funding // Ensure to include the exact value here
		 	    WebElement selectedOption1 = select1.getFirstSelectedOption();
		 	    System.out.println("Selected option is: " + selectedOption1.getText());
		 	    Thread.sleep(3000);
	//-------------------------------------------------GraduatioStatus---------------------------//	     
		 	   driver.findElement(By.id("graduation_status")).click();
		 	  Thread.sleep(2000);
		 		WebElement GraduatioStatus = driver.findElement(By.id("graduation_status"));
		 	    Select select2 = new Select(GraduatioStatus);

		 	    select2.selectByValue("\"2\""); //Loan Support in edit Miles Pathway Funding // Ensure to include the exact value here
		 	    WebElement selectedOption2 = select2.getFirstSelectedOption();
		 	    System.out.println("Selected option is: " + selectedOption2.getText());
		 	    Thread.sleep(3000);   
		 	    
	//----------------------------------------Rank------------------------------//
		 	    
		 	   driver.findElement(By.id("graduation_rank")).click();
		 	  Thread.sleep(2000);
		 		WebElement Rank = driver.findElement(By.id("graduation_rank"));
		 	    Select select3 = new Select(Rank);

		 	    select3.selectByValue("\"1\""); //Loan Support in edit Miles Pathway Funding // Ensure to include the exact value here
		 	    WebElement selectedOption3 = select3.getFirstSelectedOption();
		 	    System.out.println("Selected option is: " + selectedOption3.getText());
		 	    Thread.sleep(3000);  

		 	//------------------------------------------------------Division-------------------//
		 	    
		 	   driver.findElement(By.id("graduation_rank_id")).click();
		 	  Thread.sleep(2000);
		 	 driver.findElement(By.id("graduation_rank_id")).sendKeys("First Division");
		 	   Thread.sleep(3000); 
		 	List <WebElement> Division = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
		 	Division.get(0).click();
		    Thread.sleep(3000); 
		
		    //-----------------------------------------------------Overall Percentage%-------------------------------
		WebElement Percentage = driver.findElement(By.id("graduation_percentage"));
		Percentage.click();
		 Thread.sleep(2000);
		Percentage.clear();
		
		Percentage.sendKeys("85");
		    
		 	//---------------------------------NAACSTATUS---------------------------//    
		 	   driver.findElement(By.id("naac_status")).click();
		 		WebElement NAACSTATUS = driver.findElement(By.id("naac_status"));
		 	    Select select5 = new Select(NAACSTATUS);

		 	    select5.selectByValue("\"yes\""); //Loan Support in edit Miles Pathway Funding // Ensure to include the exact value here
		 	    WebElement selectedOption5 = select5.getFirstSelectedOption();
		 	    System.out.println("Selected option is: " + selectedOption5.getText());
		 	    Thread.sleep(3000);    
		 	    
		 	   
		 	 driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary o_form_button_save')]")).click();  
}

public void CandidateU7AllocationProcess() throws InterruptedException, AWTException

{
	driver.findElement(By.xpath("//*[contains(@title, 'Lead Allocation')]")).click();
	Thread.sleep(2000);
	driver.findElements(By.xpath("//*[contains(@role, 'menuitem')]")).get(1).click();

	driver.findElement(By.className("o_searchview_input")).click();
	
	driver.findElement(By.className("o_searchview_input")).sendKeys("Automation-User1");
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'form-check-input')]")).click();
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-secondary')]")).click();

	System.out.println("Actual Can id is "+getcandidateId());
//Assert.assertTrue(getcandidateId().contains(ExpectedCanID));

	driver.findElement(By.id("general_manager_id")).click();
	Thread.sleep(5000);
	driver.findElement(By.id("general_manager_id")).sendKeys("Manoj Expert");
	Thread.sleep(3000);
	List <WebElement> Options1 = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
	Options1.get(0).click();
//Robot robot = new Robot();
//robot.mouseMove(100, 200); // Adjust the coordinates as needed
//robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

	Thread.sleep(2000);
	driver.findElement(By.id("gm_spoc_id")).click();
	Thread.sleep(5000);
	driver.findElement(By.id("gm_spoc_id")).sendKeys("Manoj Spoc");
	Thread.sleep(3000);
	List <WebElement> Options2 = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
	Options2.get(0).click();

	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@name, 'action_allocate_gm_to_leads')]")).click();

}

public void CandidateU7ADetails() throws InterruptedException
{
	String ExpectedDashBoardUserName = "Automation-User1";
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_title kanban_tiles_title truncate-text-name')]")).click();
	Thread.sleep(3000);
	System.out.println("Actual User Name According To Passport is "+getCandidateName());
	Assert.assertTrue(getCandidateName().contains(ExpectedDashBoardUserName));

	System.out.println("Actual Can id is "+getcandidateId());
//Assert.assertTrue(getcandidateId().contains(ExpectedCanID));

}


public String GetCandidateEligibilityType()
{
	return driver.findElements(By.xpath("//*[contains(@name, 'eligibility_done_status')]")).get(0).getText();
}
public String GetCandidateEligibilityCode()
{
	return driver.findElements(By.xpath("//*[contains(@name, 'eligibility_code')]")).get(0).getText();
}

public String GetCandidateEnrollmentStatus()
{
	return driver.findElements(By.xpath("//*[contains(@class, 'o_cell o_wrap_input flex-grow-1 flex-sm-grow-0 text-break')]")).get(14).getText();
}

public String getcandidateId()
{
	return driver.findElement(By.xpath("//*[contains(@name, 'can_id')]")).getText();
}
public String getCandidateName()
{
	return driver.findElement(By.xpath("//*[contains(@class, 'o_field_widget o_readonly_modifier o_required_modifier o_field_char')]")).getText();
}

public void SearchU7ACnadidate() throws InterruptedException
{
	CandidateData();
	Thread.sleep(4000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	Thread.sleep(4000);
	//Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_title kanban_tiles_title truncate-text-name')]")).getText().contains(expectedInfoTxt));

	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_title kanban_tiles_title truncate-text-name')]")).click();
	
	
}


public void CommunicationTestFlow() throws AWTException, InterruptedException
{
	
	Thread.sleep(4000);
	driver.findElements(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).get(1).click();
	Thread.sleep(2000);
	
	driver.findElements(By.xpath("//*[contains(@class, 'o_cell o_wrap_input flex-grow-1 flex-sm-grow-0')]")).get(15).click();
	Thread.sleep(1000);

     driver.findElement(By.xpath("//*[contains(@class, 'day today')]")).click();
     Thread.sleep(3000);
     
     driver.findElement(By.xpath("//*[contains(@title, 'Close the picker')]")).click();
     Thread.sleep(2000);
   System.out.println("Date selected is "+driver.findElements(By.xpath("//*[contains(@class, 'o_cell o_wrap_input flex-grow-1 flex-sm-grow-0')]")).get(15).getText());
   RandomClickonScreen();
   
   
   driver.findElement(By.id("ats_communication_test_status")).click();
   Thread.sleep(2000);
   Select dropdown = new Select(driver.findElement(By.id("ats_communication_test_status")));
   dropdown.selectByValue("\"pass\""); // For "Passed"
   // dropdown.selectByValue("\"fail\""); // For "Failed"
   // dropdown.selectByValue("\"conditional\""); // For "Conditional Pass"
   driver.findElements(By.id("ats_communication_test_remark")).get(0).sendKeys(Adding_Commentson_CommunicationTEST);
   driver.findElements(By.id("ats_communication_test_link")).get(0).sendKeys(Adding_Interview_Recording_Link);
   driver.findElements(By.id("ats_communication_skill_domain")).get(0).sendKeys(Adding_Skill_Domain);
   driver.findElements(By.id("ats_communication_test_score")).get(0).sendKeys(Adding_BasicCommunication_TestScore);
   
   driver.findElements(By.id("ats_communication_tested_by")).get(0).sendKeys("Manoj Coach");
   Thread.sleep(2000);
   List <WebElement> Options = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
	Options.get(0).click();

   
    Thread.sleep(2000);
   driver.findElement(By.xpath("//*[contains(@name, 'action_submit_enrollment_rejection')]")).click();
}

public void RandomClickonScreen() throws AWTException
{
	Robot robot = new Robot();
	robot.mouseMove(100, 200); // Adjust the coordinates as needed
   robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
   robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
}

//
//public void BasicCandidateDetails()
//{
//	System.out.println("Actual Eligibility Code is "+GetCandidateEligibilityCode());
//	Assert.assertTrue(GetCandidateEligibilityCode().contains(ExpectedEligibilityCode));
//	
//	System.out.println("Actual Enrollment Status is "+GetCandidateEnrollmentStatus());
//	Assert.assertTrue(GetCandidateEnrollmentStatus().contains(ExpectedEnrollmentStatus));
//	
//	System.out.println("Actual Eligibility Type is "+GetCandidateEligibilityType());
//	Assert.assertTrue(GetCandidateEligibilityType().contains(ExpectedEligibilityType));
//}

public void LoRResponses() throws InterruptedException

{
	WebElement button1 = driver.findElement(By.xpath("//a[@role='button' and text()='Add a line']"));
    button1.click();
    Thread.sleep(2500);
    driver.findElements(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).get(2).sendKeys(Adding_LOR_Question1);
    Thread.sleep(2000);
	List <WebElement> Options1 = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
	Options1.get(0).click();
	Thread.sleep(2500);
    driver.findElement(By.xpath("//textarea[@class='o_input']")).sendKeys(Adding_Answer1);
    Thread.sleep(3000);
    
    driver.findElement(By.xpath("//*[contains(@class, 'o_form_button_save btn btn-light py-0')]")).click();   //--------------------TO SAVE-----------//
    
    driver.findElement(By.className("o_field_x2many_list_row_add")).click();
    Thread.sleep(1000);
	WebElement button2 = driver.findElement(By.xpath("//a[@role='button' and text()='Add a line']"));
    button2.click();
    Thread.sleep(2500);
    driver.findElements(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).get(2).sendKeys(Adding_LOR_Question2);
    Thread.sleep(2000);
    List <WebElement> Options2 = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
   	Options2.get(0).click();
   	Thread.sleep(2500);
    driver.findElement(By.xpath("//textarea[@class='o_input']")).sendKeys(Adding_Answer2);
    Thread.sleep(3000);
    
    driver.findElement(By.xpath("//*[contains(@class, 'o_form_button_save btn btn-light py-0')]")).click();    //--------------------TO SAVE-----------//
    
    driver.findElement(By.className("o_field_x2many_list_row_add")).click();
    Thread.sleep(1000);
	WebElement button3 = driver.findElement(By.xpath("//a[@role='button' and text()='Add a line']"));
    button3.click();
    Thread.sleep(2500);
    driver.findElements(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).get(2).sendKeys(Adding_LOR_Question3);
    Thread.sleep(2000);
    List <WebElement> Options3 = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
   	Options3.get(0).click();
   	Thread.sleep(2500);
    driver.findElement(By.xpath("//textarea[@class='o_input']")).sendKeys(Adding_Answer3);
    Thread.sleep(3000);
    
    driver.findElement(By.xpath("//*[contains(@class, 'o_form_button_save btn btn-light py-0')]")).click();    //--------------------TO SAVE-----------//
    
    
    driver.findElement(By.className("o_field_x2many_list_row_add")).click();
    Thread.sleep(1000);
    WebElement button4 = driver.findElement(By.xpath("//a[@role='button' and text()='Add a line']"));
    button4.click();
    Thread.sleep(2500);
    driver.findElements(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).get(2).sendKeys(Adding_LOR_Question4);
    Thread.sleep(2000);
    List <WebElement> Options4 = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
    Options4.get(0).click();
   	Thread.sleep(2500);
    driver.findElement(By.xpath("//textarea[@class='o_input']")).sendKeys(Adding_Answer4);
    Thread.sleep(3000);
    driver.findElement(By.xpath("//*[contains(@class, 'o_form_button_save btn btn-light py-0')]")).click();    //--------------------TO SAVE-----------//
    
    driver.findElement(By.className("o_field_x2many_list_row_add")).click();
    Thread.sleep(1000);
    WebElement button5 = driver.findElement(By.xpath("//a[@role='button' and text()='Add a line']"));
    button5.click();
    Thread.sleep(2500);
    driver.findElements(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).get(2).sendKeys(Adding_LOR_Question5);
    Thread.sleep(2000);
    List <WebElement> Options5 = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
    Options5.get(0).click();
   	Thread.sleep(2500);
    driver.findElement(By.xpath("//textarea[@class='o_input']")).sendKeys(Adding_Answer5);
    Thread.sleep(3000);
    driver.findElement(By.xpath("//*[contains(@class, 'o_form_button_save btn btn-light py-0')]")).click();    //--------------------TO SAVE-----------//

    driver.findElement(By.xpath("//*[contains(@name, 'action_generate_lor')]")).click();
 
    
}


public void BookExpertSessionU7Window() throws InterruptedException
{
		driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).click();
		Thread.sleep(2000);
//		System.out.println("Booking Session is "+driver.findElements(By.xpath("//*[contains(@class, 'o_cell o_wrap_input flex-grow-1 flex-sm-grow-0 text-break')]")).get(15).getText());
//		System.out.println("Counselor is "+driver.findElements(By.xpath("//*[contains(@class, 'o_cell o_wrap_input flex-grow-1 flex-sm-grow-0 text-break')]")).get(16).getText());
//		System.out.println("Student is "+driver.findElements(By.xpath("//*[contains(@class, 'o_cell o_wrap_input flex-grow-1 flex-sm-grow-0 text-break')]")).get(17).getText());
		Thread.sleep(2000);
		
		

driver.findElement(By.id("booked_date")).click();
Thread.sleep(1000);
		// Get tomorrow's date
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String tomorrowDate = tomorrow.format(formatter);
		
		//XPath using the calculated date
		String xpath = String.format("//td[@data-action='selectDay' and @data-day='%s']", tomorrowDate);
		
		
		WebElement dateElement = driver.findElement(By.xpath(xpath));
		dateElement.click();
		
		
		driver.findElement(By.xpath("//*[contains(@class, 'btn oe_subtotal_footer btn-primary')]")).click();
		
		System.out.println("Booked Date for Selected Slot is "+driver.findElement(By.xpath("//*[contains(@class, 'o_field_widget o_readonly_modifier o_field_date')]")).getText());
		System.out.println("Start Date and Time of Selected Slot is "+driver.findElement(By.xpath("//*[contains(@name, 'start_date')]")).getText());
		System.out.println("End Date and Time of Selected Slot is "+driver.findElement(By.xpath("//*[contains(@name, 'end_date')]")).getText());
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[contains(@name, 'confirm_slot')]")).click();
		Thread.sleep(2000);
		
		driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(3).click();
		Thread.sleep(2000);

		driver.findElement(By.id("agenda")).sendKeys(Adding_CommentsTo_Agenda);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@name, 'action_book_session')]")).click();
		Thread.sleep(2000);
		driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(2).click();
 
		Thread.sleep(5000);
}



public void U16ToAttendingMeeting() throws InterruptedException
{
	ATSPageLib ATS = new ATSPageLib(driver);
	CandidateData1();
	Thread.sleep(5000);
	ATS.ScrollToMeetingTab();
	Thread.sleep(3000);
	
	System.out.println("Meeting Info is "+driver.findElements(By.xpath("//*[contains(@class, 'o_data_cell cursor-pointer o_field_cell o_list_char o_readonly_modifier')]")).get(1).getText());

	
	driver.findElements(By.xpath("//*[contains(@name, 'action_goto_booking')]")).get(1).click(); //Click on Go to Booking Tab.//
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@name, 'action_start_meeting')]")).click(); //Click Operation for Start Meeting. //
	Thread.sleep(2000);
	
	
		driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();///Click to OK Button before Joining Meeting.//
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//*[contains(@class, 'btn btn-success')]")).click(); //Click Operation for Join Meeting.//
		Thread.sleep(5000);
		
		ATS.SwitchtoBLueButton();
		Thread.sleep(4000);
		ATS.EndMeeting();
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//*[contains(@name, 'action_goto_lead')]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.id("interview_feedback")).sendKeys(Adding_CommentsTo_VISAInterviewFeedback);
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[contains(@name, 'action_submit_visa_slot_feedback')]")).click();
		Thread.sleep(5000);
}

public void U16TooBookVISAMock() throws InterruptedException
{
	CandidateData1();
	Thread.sleep(5000);
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).click();
	
	Thread.sleep(2000);
	System.out.println("Booking Session is "+driver.findElements(By.xpath("//*[contains(@class, 'o_cell o_wrap_input flex-grow-1 flex-sm-grow-0 text-break')]")).get(13).getText());
	Thread.sleep(2000);
	System.out.println("Counselor is "+driver.findElements(By.xpath("//*[contains(@class, 'o_cell o_wrap_input flex-grow-1 flex-sm-grow-0 text-break')]")).get(14).getText());
	Thread.sleep(2000);
	System.out.println("Student is "+driver.findElements(By.xpath("//*[contains(@class, 'o_cell o_wrap_input flex-grow-1 flex-sm-grow-0 text-break')]")).get(15).getText());
	Thread.sleep(2000);
	
	//driver.findElements(By.xpath("//*[contains(@class, 'o_cell o_wrap_input flex-grow-1 flex-sm-grow-0 text-break')]")).get(18).click();
driver.findElement(By.id("booked_date")).click();
Thread.sleep(2000);
	// Get tomorrow's date
	LocalDate tomorrow = LocalDate.now().plusDays(1);
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	String tomorrowDate = tomorrow.format(formatter);
	
	//XPath using the calculated date
	String xpath = String.format("//td[@data-action='selectDay' and @data-day='%s']", tomorrowDate);
	
	Thread.sleep(2000);
	WebElement dateElement = driver.findElement(By.xpath(xpath));
	dateElement.click();
	
	Thread.sleep(2000);
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn oe_subtotal_footer btn-primary')]")).click();
	
	System.out.println("Booked Date for Selected Slot is "+driver.findElement(By.xpath("//*[contains(@class, 'o_field_widget o_readonly_modifier o_field_date')]")).getText());
	Thread.sleep(1000);
	System.out.println("Start Date and Time of Selected Slot is "+driver.findElement(By.xpath("//*[contains(@name, 'start_date')]")).getText());
	Thread.sleep(1000);
	System.out.println("End Date and Time of Selected Slot is "+driver.findElement(By.xpath("//*[contains(@name, 'end_date')]")).getText());
	Thread.sleep(2000);
	
	driver.findElements(By.xpath("//*[contains(@name, 'confirm_slot')]")).get(0).click();
	Thread.sleep(2000);
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(6).click();
	//driver.findElement(By.xpath("//button[@class='btn btn-primary'][normalize-space()='Ok']")).click();
	Thread.sleep(2000);

	driver.findElement(By.id("agenda")).click();
	Thread.sleep(1000);
	driver.findElement(By.id("agenda")).sendKeys(Adding_CommentsTo_Agenda);
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@name, 'action_book_session')]")).click();
Thread.sleep(2000);
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(2).click();
	
	Thread.sleep(10000);
	
}
public void SearchU7EnrolledCandidate() throws InterruptedException
{
	String ExpectedDashBoardUserName = "Automation-User1";
	Thread.sleep(3000);
	CandidateData();
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_subtitle  kanban_tiles_subtitle')]")).click();
	
	System.out.println("Actual Can id is "+getcandidateId());
//	Assert.assertTrue(getcandidateId().contains(ExpectedCanID));
	
	System.out.println("Actual User Name According To Passport is "+getCandidateName());
	Assert.assertTrue(getCandidateName().contains(ExpectedDashBoardUserName));

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

//public void AddRecommendation() throws InterruptedException
//{
//	 driver.findElement(By.id("recommendation-add-btn")).click();
//	 driver.findElements(By.id("note-title")).get(2).click();
//	 driver.findElements(By.id("note-title")).get(2).sendKeys(Added_Recommendation);
//	 Thread.sleep(3000);
//	 driver.findElement(By.id("add-recommendation-popup-submit")).click();
//}


//public void ScrollToHeartRateZones()
//{
//	Actions act = new Actions(driver);
//	act.moveToElement(driver.findElement((By.xpath("//*[contains(@class, 'hr-zone-distribution')]")))).perform();
//	//driver.findElement(By.className("fa fa-plus")).click();
//}

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

//public void InitateAdminPage() throws InterruptedException
//{	 
//	List <WebElement> Options = driver.findElements(By.className("listbrdr"));
//	Options.get(1).click();
//	Thread.sleep(6000);
//	OPTPageObj = new OPTPageLib(driver);
//	
//	
//}

public String getUserNameOnDashboard()
{
	return driver.findElement(By.className("current-user-dashboard")).getText();
}

}
	