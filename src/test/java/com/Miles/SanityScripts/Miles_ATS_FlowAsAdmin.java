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
import com.miles.PageLibRepo.LiveUserPageLib;
import com.miles.PageLibRepo.LoginPageLib;
import com.miles.PageLibRepo.OPTPageLib;
import com.miles.PageLibRepo.WorkoutDetailsPageLib;
import com.miles.PageObjectRepo.atspageObj;
import com.miles.PageObjectRepo.HomePageObj;
import com.miles.PageObjectRepo.OPTPageObj;
import com.miles.Utilities.MilesUtilities;
import com.miles.Utilities.MilesUtilities;

public class Miles_ATS_FlowAsAdmin extends MilesSettings
{	
	 WebDriver driver = null ;
	 
	protected LoginPageLib loginObj ;
	
	protected HomePageLib homeObj ;
	
	protected ATSPageLib atspageObj;
	 
	// atspageObj = new ATSPageLib(driver);
	 
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
	
	 String Name = "Automation-User";
	 String expectedInfoTxt = "Automation-User";
	 String ExpectedCanID = "Test-659531";
	 String ExpectedEmail = "milesautomation@mileseducation.com";
	 String ExpectedEligibilityCode = "Eligibility/B/24/09/115";
	 String ExpectedEnrollmentStatus = "New Student";
	
	 String ExpectedEligibilityType = "Eligibility not done";
	 
	 
	 String ClassName = this.getClass().getSimpleName().toString(); 
	 
	 String GeneralInfoContains = "Male";
	 String SerialInfoContains = "Serial Number";
	 
	 String Adding_Commentson_CommunicationTEST = "Adding Comments/Feedback Through Automation Script By QATeam On- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1; 
	 String Adding_Interview_Recording_Link = "Adding Interview Recording Link By QATeam on- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1+"www.YouTube.com";
	 String Adding_CommentsTo_Agenda = "Adding Comments to Agenda By Automation Script For Booked Slots";
//	 LocalDate tomorrow = LocalDate.now().plusDays(1);
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
		 
	//	System.out.println();
		 
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
	
//@Test(priority = 1,description = "Verify Admin Login")
public void ClearingHomePage() throws InterruptedException

{
	ClearMyCandidateFilter();
}

//@Test(priority = 2,description = "Verify Admin Dropdown Options")
public void AdminDropdownOptions() throws InterruptedException

{
	ClearMyCandidateFilter();
	VerifyHomeMenuOptions();
}

//@Test(priority = 3,description = "Verify Admin Can Enter to ATS Module")
public void EntireingtoMilesRequirementATSModule() throws InterruptedException

{
	ClearMyCandidateFilter();
	VerifyInitiateATSPage();
}


//@Test (priority = 4,description = "Verify ATS Module Configuration Options")
public void ATSConfigurationDropdownOptions () throws InterruptedException

{
	ClearMyCandidateFilter();
	VerifyInitiateATSPage();
	VerifyATSCOnfigurationOptions();
}

//@Test (priority = 5,description = "Verify ATS Module Search Candidate")
public void U7ASearachCandidate() throws InterruptedException
{
	ClearMyCandidateFilter();
	VerifyInitiateATSPage();
	SearchU7ACnadidate();
}

//@Test (priority = 6,description = "Verify ATS Module U7A Candidate Bucket")
public void U7ACandidateWindow() throws InterruptedException
{
	
	ClearMyCandidateFilter();
	VerifyInitiateATSPage();
	SearchU7ACnadidate();
	CandidateU7ADetails();
}
	
//@Test (priority = 7,description  = "Verify ATS Module Candidate Allocation For GM")

public void U7EnrolledLeadAllocation() throws InterruptedException, AWTException
{
	ClearMyCandidateFilter();
	VerifyInitiateATSPage();
	Thread.sleep(3000);
	SearchU7ACnadidate();
	Thread.sleep(3000);
	CandidateU7AllocationProcess();
	
}

//@Test (priority = 8,description = "Verify ATS Module Candidate U7 Enrolled Bucket")

public void U7EnrolledBucket()throws InterruptedException
{
	ClearMyCandidateFilter();
	VerifyInitiateATSPage();
	Thread.sleep(3000);
	SearchU7EnrolledCandidate();
	
	
}

//@Test (priority = 9,description = "Verify ATS Module Candidate U7 Enrolled Bucket")

public void CheckingU7Tabs() throws InterruptedException
{	
	
	ClearMyCandidateFilter();
	VerifyInitiateATSPage();
	Thread.sleep(3000);
	TabsbuttonOnU7Enrolled();
}

//@Test (priority = 10, description = "Verify Candidate Basic Details in U7 Enrolled Bucket")

public void CandidateBasicDetails() throws InterruptedException
{
	ClearMyCandidateFilter();
	VerifyInitiateATSPage();
	Thread.sleep(3000);
	TabsbuttonOnU7Enrolled();
	ScrollToBasicDetails();
	BasicCandidateDetails();

}

//@Test (priority = 11, description = "Verify Updating Candidate Communication Test Result")

public void UpdatingCommunicationTestResult() throws InterruptedException, AWTException
{
	ClearMyCandidateFilter();
	VerifyInitiateATSPage();
	TabsbuttonOnU7Enrolled();
	Thread.sleep(3000);
	CommunicationTestFlow();
	
}

//@Test (priority = 12, description = "Booking Expert Session From SPOC to Candidate")
public void VerifyAllocatingBookingExpertSession() throws InterruptedException, AWTException
{
	
	ClearMyCandidateFilter();
	VerifyInitiateATSPage();
	TabsbuttonOnU7Enrolled();
	Thread.sleep(3000);
	TooBookExpertSession();
	TooBookTimeSlot();
	BookExpertSessionU7Window();
	Thread.sleep(10000);
}

//@Test (priority = 13, description = "U7+ Expert Session Booked")

public void VerifyU7PlusExpertSeesionBooked() throws InterruptedException
{
	ClearMyCandidateFilter();
	VerifyInitiateATSPage();
	TabsbuttonOnU7PlusEnrolled();
	Thread.sleep(3000);
	
	ScrollToMeetingTabU7Plus();
	
	StartMeeting();
	SwitchtoBLueButton();
	EndMeeting();

	
	U7PLusEligibleTheCandidate();

}

//@Test (priority = 14, description = "U7+ Recommend University To Candidate")
public void VerifyU7PlusRecommendUniversity() throws InterruptedException, AWTException
{
	ClearMyCandidateFilter();
	VerifyInitiateATSPage();
	TabsbuttonOnU7PlusEnrolled();
	Thread.sleep(3000);
	ScrollToMeetingTabU7Plus();
	
	Recommenduniversity();
	
	U7PLusEligibleTheCandidate();

}

//@Test (priority = 15, description = "U8 Expert Session Done Bucket")

public void VerifyU8Bucket() throws InterruptedException, AWTException
{
	ClearMyCandidateFilter();
	VerifyInitiateATSPage();
	Thread.sleep(2000);
	//atspageObj.U8bucket();
	U8bucket();
	MSASigned();
}

@Test (priority = 16, description = "U9 MSA SIGNED")

public void VerifyU9Bucket() throws InterruptedException
{
	ClearMyCandidateFilter();
	VerifyInitiateATSPage();
	Thread.sleep(2000);
	U9bucket();
	UniversitySelection();
	
	
	
}



	/*
	 * Helper Methods
	 */

public void UniversitySelection() throws InterruptedException
{
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).click();
	//selected_university_id
	driver.findElement(By.id("selected_university_id")).sendKeys("Michigan State University, Masters in Accounting");
	List <WebElement> Options = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
	Options.get(0).click();
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//button[@class='btn btn-primary'][normalize-space()='Ok']")).click();
	
	

}


public void U9bucket() throws InterruptedException
{
	driver.findElement(By.className("o_searchview_input")).click();
	driver.findElement(By.className("o_searchview_input")).sendKeys("AUTOMAYUON987");
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_subtitle  kanban_tiles_subtitle')]")).click();
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercase')]")).get(0).isDisplayed();
	System.out.println("Current Candidate is in "+driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercas')]")).get(0).getText());

	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).isDisplayed();
	System.out.println("Light Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).isDisplayed();
	System.out.println("Red Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).isDisplayed();
	System.out.println("Yellow Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).isDisplayed();
	System.out.println("Brown Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());

}


public void MSASigned() throws InterruptedException, AWTException
{
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).click();
	
	 driver.findElements(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).get(0).sendKeys("JAGSoM (Opt-IN)");
	 Thread.sleep(2000);

		
		driver.findElement(By.xpath("//a[@class='dropdown-item ui-menu-item-wrapper text-truncate ui-state-active']")).click();		
		
//	driver.findElement(By.xpath("//*[contains(@class, 'oe_fileupload')]")).click();
//	 Thread.sleep(5000);
//	 
//	 String Attachements = System.getProperty("user.dir");
//	 driver.findElement(By.xpath("//*[contains(@name, 'o_field_widget o_field_many2many_binary')]")).sendKeys(Attachements+"\\Attachements\\MASATTACHEMENT.png");
//	 
//	 WebElement fileInput = driver.findElement(By.xpath("//*[contains(@class, 'oe_fileupload')]"));
//	 
////	 WebElement fileInput = driver.findElement(By.xpath("//*[contains(@class, 'oe_fileupload')]"));
////	 fileInput.sendKeys("C:\\Users\\MANOJ.HR\\Pictures\\Screenshots\\Screenshot (10).png");
//	
//	 Thread.sleep(5000);
//	driver.findElement(By.xpath("//*[contains(@name, 'action_sign_msa_agreement_submit')]")).click();
		Thread.sleep(5000);
        WebElement uploadButton = driver.findElement(By.className("o_file_input_trigger"));
        uploadButton.click();
        Thread.sleep(5000);
        
        // Use the Robot class to handle the file upload dialog
        Robot robot = new Robot();

        // Copy the file path to the clipboard
        StringSelection filePath = new StringSelection("C:\\Users\\MANOJ.HR\\Pictures\\Screenshots\\MASATTACHEMENT.png");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
        // Simulate pressing CTRL + V (paste)
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // Simulate pressing Enter to confirm the file selection
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Add any further actions if needed, such as submitting the form
	Thread.sleep(10000);

	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
}





public void U8bucket() throws InterruptedException
{
	driver.findElement(By.className("o_searchview_input")).click();
	driver.findElement(By.className("o_searchview_input")).sendKeys("AUTOMAYUON987");
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_subtitle  kanban_tiles_subtitle')]")).click();
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercase')]")).get(0).isDisplayed();
	System.out.println("Current Candidate is in "+driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercas')]")).get(0).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).isDisplayed();
	System.out.println("Light Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_blue_color btn-secondary')]")).isDisplayed();
	System.out.println("Blue Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_blue_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).isDisplayed();
	System.out.println("Red Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).isDisplayed();
	System.out.println("Yellow Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).isDisplayed();
	System.out.println("Brown Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());
	
	
	
	
}

public void Recommenduniversity() throws InterruptedException, AWTException
{
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary btn-sm')]")).click();
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-success btn-sm')]")).isDisplayed();
	System.out.println("Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn btn-success btn-sm')]")).getText());
	
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-danger btn-sm')]")).isDisplayed();
	System.out.println("Red Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn btn-danger btn-sm')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-warning btn-sm')]")).isDisplayed();
	System.out.println("Yellow Blue Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn btn-warning btn-sm')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-info btn-sm')]")).isDisplayed();
	System.out.println("Light Blue Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn btn-info btn-sm')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-danger btn-sm')]")).isDisplayed();
	System.out.println("Red2 Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn btn-danger btn-sm')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary btn-sm')]")).isDisplayed();
	System.out.println("Purple Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary btn-sm')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@name, 'action_recommend_university')]")).click();
	
	Thread.sleep(1500);
	
    WebElement button = driver.findElement(By.xpath("//a[@role='button' and text()='Add a line']"));
    button.click();
 
    Thread.sleep(3000);
    
   driver.findElements(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).get(0).sendKeys("Michigan State University");
   
   Thread.sleep(4000);
   
   driver.findElements(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).get(1).sendKeys("Masters in Accounting");

   Thread.sleep(4000);
   driver.findElements(By.xpath("//*[contains(@name, 'action_recommend_university')]")).get(1).click();
   
   Thread.sleep(2000);
   driver.findElement(By.xpath("//*[contains(@name, 'action_goto_lead')]")).click();
   
   
}


public void EndMeeting() throws InterruptedException
{
	List <WebElement> Options = driver.findElements(By.xpath("//*[contains(@class, 'btn btn-danger')]"));
	Options.get(0).click();
	Thread.sleep(2000);
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
	
	
}

public void StartMeeting() throws InterruptedException

{
	System.out.println("Meeting Info is "+driver.findElement(By.xpath("//*[contains(@class, 'o_data_cell cursor-pointer o_field_cell o_list_char o_readonly_modifier')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary btn-sm')]")).click();
	
	driver.findElement(By.xpath("//*[contains(@class, 'action_start_meeting')]")).click(); //Click Operation for Start Meeting. //
	
		driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary')]")).click();///Click to OK Button before Joining Meeting.//
		
		
		driver.findElement(By.xpath("//*[contains(@class, 'btn btn-success')]")).click(); //Click Operation for Join Meeting.//
		Thread.sleep(5000);
}




public void SwitchtoBLueButton() throws InterruptedException
{
	MilesUtilities.SwitchTab(1, driver); //BIg BLUE WINDOW.//
	Thread.sleep(7000);

	
	driver.findElement(By.id("tippy-21")).click();

	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'sc-dJjYzT gbVgVx md buttonWrapper sc-bUKjYF kULnRS')]")).click();	
		
	
	List <WebElement> Options = driver.findElements((By.xpath("//*[contains(@class, 'MuiButtonBase-root MuiMenuItem-root MuiMenuItem-root sc-lheXJl fwmlKu css-1vsvrdy')]")));
	Options.get(8).click();
	
	driver.findElement(By.xpath("//*[contains(@class, 'sc-dlVxhl jQxUMv sc-jnbWvw eysmcT')]")).click();
	MilesUtilities.SwitchTab(0, driver);
}


public void U7PLusEligibleTheCandidate() throws InterruptedException

{
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).click();
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary')]")).click();
	Thread.sleep(3000);
	driver.findElements(By.xpath("//*[contains(@class, 'o_cell o_wrap_input flex-grow-1 flex-sm-grow-0 text-break')]")).get(15).click();
	
	Thread.sleep(3000);
	
	List <WebElement> Options = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
	Options.get(0).click();
	
	Thread.sleep(4000);
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
	Thread.sleep(3000);
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(2).click();
	
}

public void TabsbuttonOnU7PlusEnrolled() throws InterruptedException
{
	driver.findElement(By.className("o_searchview_input")).click();
	driver.findElement(By.className("o_searchview_input")).sendKeys("AUTOMAYUON987");
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_subtitle  kanban_tiles_subtitle')]")).click();
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercase')]")).get(0).isDisplayed();
	System.out.println("Current Candidate is in "+driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercas')]")).get(0).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).isDisplayed();
	System.out.println("Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_blue_color btn-secondary')]")).isDisplayed();
	System.out.println("Blue Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_blue_color btn-secondary')]")).getText());
	
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn button_blue_color btn-secondary')]")).get(1).isDisplayed();
	System.out.println("Blue Button 2 is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_blue_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).isDisplayed();
	System.out.println("Red Button  is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).isDisplayed();
	System.out.println("Yellow Button  is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).isDisplayed();
	System.out.println("Brown Button  is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());

}


public void BookExpertSessionU7Window() throws InterruptedException
{
		driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).click();
		Thread.sleep(2000);
		System.out.println("Booking Session is "+driver.findElements(By.xpath("//*[contains(@class, 'o_cell o_wrap_input flex-grow-1 flex-sm-grow-0 text-break')]")).get(15).getText());
		System.out.println("Counselor is "+driver.findElements(By.xpath("//*[contains(@class, 'o_cell o_wrap_input flex-grow-1 flex-sm-grow-0 text-break')]")).get(16).getText());
		System.out.println("Student is "+driver.findElements(By.xpath("//*[contains(@class, 'o_cell o_wrap_input flex-grow-1 flex-sm-grow-0 text-break')]")).get(17).getText());
		Thread.sleep(2000);
		driver.findElements(By.xpath("//*[contains(@class, 'o_cell o_wrap_input flex-grow-1 flex-sm-grow-0 text-break')]")).get(18).click();

		// Get tomorrow's date
		LocalDate tomorrow = LocalDate.now().plusDays(2);
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
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//*[contains(@name, 'confirm_slot')]")).click();
		Thread.sleep(2000);
		
		driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(12).click();
		Thread.sleep(2000);

		driver.findElement(By.id("agenda")).sendKeys(Adding_CommentsTo_Agenda);
 
		driver.findElement(By.xpath("//*[contains(@name, 'action_book_session')]")).click();
 
		driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(11).click();
 
 
}

public void TooBookExpertSession() throws InterruptedException
{
	driver.findElement(By.xpath("//*[contains(@title, 'Meetings')]")).click();
	Thread.sleep(2000);
	driver.findElements(By.xpath("//*[contains(@role, 'menuitem')]")).get(2).click();
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary o_list_button_add')]")).click();

	driver.findElement(By.id("emp_id")).click();
	Thread.sleep(3000);
	driver.findElement(By.id("emp_id")).sendKeys("Manoj Expert");
	Thread.sleep(3000);
	
	List <WebElement> Options = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
	Options.get(0).click();

	
	driver.findElement((By.xpath("//*[contains(@class, 'o_datepicker_input o_input datetimepicker-input')]"))).click();
	Thread.sleep(2000);


 // Get tomorrow's date
    LocalDate tomorrow = LocalDate.now().plusDays(1);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    String tomorrowDate = tomorrow.format(formatter);

    //XPath using the calculated date
    String xpath = String.format("//td[@data-action='selectDay' and @data-day='%s']", tomorrowDate);


    WebElement dateElement = driver.findElement(By.xpath(xpath));
    dateElement.click();
    
    
}

public void TooBookTimeSlot() throws InterruptedException, AWTException
{  
	WebElement button = driver.findElement(By.xpath("//a[@role='button' and text()='Add a line']"));
    button.click();
    
    WebElement session =  driver.findElement((By.xpath("//*[contains(@class, 'o_data_cell cursor-pointer o_field_cell o_list_many2one o_required_modifier')]")));
    session.click();
    Thread.sleep(3000);
 
    driver.findElements(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).get(2).sendKeys("Expert Counselling");
    RandomClickonScreen();
    driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary')]")).click(); //
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[contains(@class, 'o_form_button_save btn btn-light py-0')]")).click();//To Save Button//
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[contains(@class, 'o_form_button_save btn btn-light py-0')]")).click(); //To confimring Allocation//
    Thread.sleep(2000);
    driver.findElements(By.xpath("//*[contains(@class, 'o_form_button_save btn btn-light py-0')]")).get(1).click(); //Final Confirmation //
    
    
}


public void RandomClickonScreen() throws AWTException
{
	Robot robot = new Robot();
	robot.mouseMove(100, 200); // Adjust the coordinates as needed
   robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
   robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
}


public void CommunicationTestFlow() throws AWTException, InterruptedException
{
	driver.findElements(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).get(1).click();
	Thread.sleep(2000);
	
	driver.findElements(By.xpath("//*[contains(@class, 'o_cell o_wrap_input flex-grow-1 flex-sm-grow-0')]")).get(15).click();
	Thread.sleep(1000);

     driver.findElement(By.xpath("//*[contains(@class, 'day today')]")).click();
     Thread.sleep(3000);
     
     driver.findElement(By.xpath("//*[contains(@title, 'Close the picker')]")).click();
     Thread.sleep(1000);
   System.out.println("Date selected is "+driver.findElements(By.xpath("//*[contains(@class, 'o_cell o_wrap_input flex-grow-1 flex-sm-grow-0')]")).get(15).getText());
   RandomClickonScreen();
   
   
   driver.findElement(By.id("ats_communication_test_status")).click();
   
   Select dropdown = new Select(driver.findElement(By.id("ats_communication_test_status")));
   dropdown.selectByValue("\"pass\""); // For "Passed"
   // dropdown.selectByValue("\"fail\""); // For "Failed"
   // dropdown.selectByValue("\"conditional\""); // For "Conditional Pass"
   driver.findElements(By.id("ats_communication_test_remark")).get(0).sendKeys(Adding_Commentson_CommunicationTEST);
   driver.findElements(By.id("ats_communication_test_link")).get(0).sendKeys(Adding_Interview_Recording_Link);
   driver.findElements(By.id("ats_communication_skill_domain")).get(0).sendKeys(Adding_Skill_Domain);
   driver.findElements(By.id("ats_communication_test_score")).get(0).sendKeys(Adding_BasicCommunication_TestScore);
   
   driver.findElements(By.id("ats_communication_tested_by")).get(0).sendKeys("Manoj Coach");
   
   RandomClickonScreen();
   
    Thread.sleep(2000);
   driver.findElement(By.xpath("//*[contains(@name, 'action_submit_enrollment_rejection')]")).click();
}



public void BasicCandidateDetails()
{
	System.out.println("Actual Eligibility Code is "+GetCandidateEligibilityCode());
	Assert.assertTrue(GetCandidateEligibilityCode().contains(ExpectedEligibilityCode));
	
	System.out.println("Actual Enrollment Status is "+GetCandidateEnrollmentStatus());
	Assert.assertTrue(GetCandidateEnrollmentStatus().contains(ExpectedEnrollmentStatus));
	
	System.out.println("Actual Eligibility Type is "+GetCandidateEligibilityType());
	Assert.assertTrue(GetCandidateEligibilityType().contains(ExpectedEligibilityType));
}


public void ScrollToMeetingTabU7Plus() throws InterruptedException 
{
	Actions act = new Actions(driver);
	act.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'student_meeting_line')]"))).perform();
	driver.findElement(By.xpath("//*[contains(@name, 'student_meeting_line')]")).click();
	Thread.sleep(1000);
	act.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'telephony_call_logs')]"))).perform();
	//driver.findElement(By.className("fa fa-plus")).click();
}




public void ScrollTillElement(WebElement element)
{
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true);", element);

}


public void ScrollToBasicDetails() 
{
	Actions act = new Actions(driver);
	act.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'student_card')]"))).perform();
	//driver.findElement(By.className("fa fa-plus")).click();
}

public void SearchU7EnrolledCandidate() throws InterruptedException
{
	String ExpectedDashBoardUserName = "Automation-User";
	driver.findElement(By.className("o_searchview_input")).click();
	driver.findElement(By.className("o_searchview_input")).sendKeys("Automation-User");
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_subtitle  kanban_tiles_subtitle')]")).click();
	
	System.out.println("Actual Can id is "+getcandidateId());
	Assert.assertTrue(getcandidateId().contains(ExpectedCanID));
	
	System.out.println("Actual User Name According To Passport is "+getCandidateName());
	Assert.assertTrue(getCandidateName().contains(ExpectedDashBoardUserName));
	
	
	
}
	
public void TabsbuttonOnU7Enrolled() throws InterruptedException
{
	
	driver.findElement(By.className("o_searchview_input")).click();
	driver.findElement(By.className("o_searchview_input")).sendKeys("Automation-User");
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_subtitle  kanban_tiles_subtitle')]")).click();
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercase')]")).get(0).isDisplayed();
	System.out.println("Current Candidate is in "+driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercas')]")).get(0).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).isDisplayed();
	System.out.println("Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).isDisplayed();
	System.out.println("Red Button 1 is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).isDisplayed();
	System.out.println("Yellow Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).getText());
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).get(1).isDisplayed();
	System.out.println("Red Button 2 is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).isDisplayed();
	System.out.println("Brown Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());
	
}
	
	



public void CandidateU7AllocationProcess() throws InterruptedException, AWTException
{
	driver.findElement(By.xpath("//*[contains(@title, 'Lead Allocation')]")).click();
	Thread.sleep(2000);
	driver.findElements(By.xpath("//*[contains(@role, 'menuitem')]")).get(1).click();
	
	driver.findElement(By.className("o_searchview_input")).click();
	driver.findElement(By.className("o_searchview_input")).sendKeys("Automation-User");
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'form-check-input')]")).click();
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-secondary')]")).click();
	
	System.out.println("Actual Can id is "+getcandidateId());
	Assert.assertTrue(getcandidateId().contains(ExpectedCanID));
	
	driver.findElement(By.id("general_manager_id")).click();
	driver.findElement(By.id("general_manager_id")).sendKeys("Manoj Expert");
	Thread.sleep(2000);
	Robot robot = new Robot();
	robot.mouseMove(100, 200); // Adjust the coordinates as needed
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    
    Thread.sleep(2000);
	driver.findElement(By.id("gm_spoc_id")).click();
	driver.findElement(By.id("gm_spoc_id")).sendKeys("Manoj Spoc");
	
	Robot robot1 = new Robot();
	robot1.mouseMove(300, 400); // Adjust the coordinates as needed
    robot1.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    robot1.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    //action_allocate_gm_to_leads
    Thread.sleep(2000);
driver.findElement(By.xpath("//*[contains(@name, 'action_allocate_gm_to_leads')]")).click();
	
}

public void CandidateU7ADetails() throws InterruptedException
{
	String ExpectedDashBoardUserName = "Automation-User";
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_title kanban_tiles_title truncate-text-name')]")).click();
	Thread.sleep(3000);
	System.out.println("Actual User Name According To Passport is "+getCandidateName());
	Assert.assertTrue(getCandidateName().contains(ExpectedDashBoardUserName));
	
	System.out.println("Actual Can id is "+getcandidateId());
	Assert.assertTrue(getcandidateId().contains(ExpectedCanID));
	
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
	driver.findElement(By.className("o_searchview_input")).click();
	driver.findElement(By.className("o_searchview_input")).sendKeys("Automation-User");
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_title kanban_tiles_title truncate-text-name')]")).getText().contains(expectedInfoTxt));
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_title kanban_tiles_title truncate-text-name')]")).click();
}




public void VerifyHomeMenuOptions()
{
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
	Assert.assertEquals(Options.get(10).getText(), "Employees");
	Assert.assertEquals(Options.get(11).getText(), "Apps");
	Assert.assertEquals(Options.get(12).getText(), "Settings");
}


public void VerifyInitiateATSPage() throws InterruptedException
{
	driver.findElement(By.className("dropdown-toggle")).click();
	List <WebElement> Options = driver.findElements(By.xpath("//*[contains(@class, 'dropdown-item o_app')]"));
	Options.get(1).click();
	
	
	Thread.sleep(4000);
}
public void VerifyATSCOnfigurationOptions()
{
	driver.findElement(By.xpath("//*[contains(@title, 'Configuration')]")).click();
	// Expected options
	List<String> expectedOptions = Arrays.asList(
		    "Allocation Configuration",
		    "Enrollment Batches",
		    "Enrollment University",
		    "Buckets",
		    "Enrollment Course",
		    "ATS Journey",
		    "LOR Question",
		    "Graduation Division",
		    "Pathway College",
		    "Sessions",
		    "Document Type",
		    "MSA Document Type",
		    "DS-160 Step",
		    "Ineligible Reason",
		    "Loan Provider",
		    "Bank",
		    "Synopsis",
		    "Telephony call Reason",
		    "Visa Slot City",
		    "Questions",
		    "Category",
		    "Journey Decks",
		    "MF Migration",
		    "NAAC Grade",
		    "University Grade Matrix",
		    "University Recommendation Combination"
		);
	
 WebElement configOptions = driver.findElement(By.xpath("//*[contains(@class, 'o-dropdown--menu dropdown-menu d-block')]"));
 List<WebElement>OptionsIteam = configOptions.findElements(By.className("dropdown-item"));
 
 for (int i = 0; i < OptionsIteam.size();i++)
 {
	 System.out.println(OptionsIteam.get(i).getText());
	 Assert.assertEquals(OptionsIteam.get(i).getText(), expectedOptions.get(i));
	 
 }
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
	