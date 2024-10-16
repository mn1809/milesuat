package com.miles.PageLibRepo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.miles.PageObjectRepo.atspageObj;
import com.miles.PageObjectRepo.AdminPageObj;
import com.miles.PageObjectRepo.OPTPageObj;
import com.miles.PageObjectRepo.atspageObj;
import com.miles.Utilities.MilesUtilities;
import com.miles.Utilities.MilesUtilities;


public class ATSPageLib extends atspageObj

{
	
	public ATSPageLib(WebDriver driver) 
	{
		super(driver);
	//	PageFactory.initElements(driver, this); 
		// TODO Auto-generated constructor stub
	}
		
	public void ClearMyCandidateFilter() throws InterruptedException
	{
		Thread.sleep(3);
		driver.findElement((By.xpath("//*[contains(@class, 'o_facet_remove oi oi-close btn btn-link opacity-50 opacity-100-hover text-900')]"))).click();
	}
	
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
	
	public void TabsbuttonOnU7Enrolled() throws InterruptedException
	{
		
		CandidateData();
		
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
	
	
	public void TooBookExpertSession() throws InterruptedException
	{
		driver.findElement(By.xpath("//*[contains(@title, 'Meetings')]")).click();
		Thread.sleep(2000);
		driver.findElements(By.xpath("//*[contains(@role, 'menuitem')]")).get(2).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary o_list_button_add')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("emp_id")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("emp_id")).sendKeys("Manoj Expert");
		Thread.sleep(3000);
		
		List <WebElement> Options = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
		Options.get(0).click();

		Thread.sleep(2000);
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
    Thread.sleep(2000);
    WebElement session =  driver.findElement((By.xpath("//*[contains(@class, 'o_data_cell cursor-pointer o_field_cell o_list_many2one o_required_modifier')]")));
    session.click();
    Thread.sleep(3000);
 
    driver.findElements(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).get(2).sendKeys("Expert Counselling");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//a[@class='dropdown-item ui-menu-item-wrapper text-truncate ui-state-active']")).click();

    //	List <WebElement> Options = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
//	Options.get(0).click();
	
	//a[@class='dropdown-item ui-menu-item-wrapper text-truncate ui-state-active']
    //driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary')]")).click(); //
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[contains(@class, 'o_form_button_save btn btn-light py-0')]")).click();//To Save Button//
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[contains(@name, 'action_confirm_allocation')]")).click(); //To confimring Allocation//
    Thread.sleep(2000);
    driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click(); //Final Confirmation //
    
    
}


public void RandomClickonScreen() throws AWTException
{
	Robot robot = new Robot();
	robot.mouseMove(100, 200); // Adjust the coordinates as needed
   robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
   robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
}

public void TabsbuttonOnU7PlusEnrolled() throws InterruptedException
{
		CandidateData() ;
	
	
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

public void ScrollToMeetingTab() throws InterruptedException 
{
	Actions act = new Actions(driver);
	act.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'student_meeting_line')]"))).perform();
	driver.findElement(By.xpath("//*[contains(@name, 'student_meeting_line')]")).click();
	Thread.sleep(1000);
	act.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'telephony_call_logs')]"))).perform();
	//driver.findElement(By.className("fa fa-plus")).click();
	
}

public void ScrollToBasicDetails() 
{
	Actions act = new Actions(driver);
	act.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'student_card')]"))).perform();
	//driver.findElement(By.className("fa fa-plus")).click();
}

public void ScrollTillElement(WebElement element)
{
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true);", element);

}
	

public void StartMeeting() throws InterruptedException

{
	System.out.println("Meeting Info is "+driver.findElement(By.xpath("//*[contains(@class, 'o_data_cell cursor-pointer o_field_cell o_list_char o_readonly_modifier')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary btn-sm')]")).click();
	Thread.sleep(3000);
	//driver.findElement(By.xpath("//*[contains(@name, 'action_start_meeting')]")).click(); //Click Operation for Start Meeting. //
	Thread.sleep(6000);
	driver.findElement(By.xpath("//*[contains(@name, 'action_join_meeting')]")).click();///Click to OK Button before Joining Meeting.//
		
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//*[contains(@class, 'btn btn-success')]")).click(); //Click Operation for Join Meeting.//
		Thread.sleep(5000);
}

public void SwitchtoBLueButton() throws InterruptedException
{
	MilesUtilities.SwitchTab(1, driver); //BIg BLUE WINDOW.//
	Thread.sleep(7000);

	
	//driver.findElement(By.id("tippy-20")).click();
	driver.findElement(By.xpath("//*[contains(@class, 'sc-dJjYzT gbVgVx md buttonWrapper sc-eJwWfJ wxIrW')]")).click();	
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'sc-dJjYzT gbVgVx md buttonWrapper sc-bUKjYF kULnRS')]")).click();	
		
	
	List <WebElement> Options = driver.findElements((By.xpath("//*[contains(@class, 'MuiButtonBase-root MuiMenuItem-root MuiMenuItem-root sc-lheXJl fwmlKu css-1vsvrdy')]")));
	Options.get(8).click();
	
	driver.findElement(By.xpath("//*[contains(@class, 'sc-dlVxhl jQxUMv sc-jnbWvw eysmcT')]")).click();
	MilesUtilities.SwitchTab(0, driver);
}


public void EndMeeting() throws InterruptedException
{
	List <WebElement> Options = driver.findElements(By.xpath("//*[contains(@class, 'btn btn-danger')]"));
	Options.get(0).click();
	Thread.sleep(2000);
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();	
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
	
	Thread.sleep(3500);
	
    WebElement button = driver.findElement(By.xpath("//a[@role='button' and text()='Add a line']"));
    button.click();
 
    Thread.sleep(3000);
    
   driver.findElements(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).get(0).sendKeys("Michigan State University");
   
   Thread.sleep(4000);
   
   driver.findElements(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).get(1).sendKeys("Masters in Accounting");

   Thread.sleep(4000);
   driver.findElements(By.xpath("//*[contains(@name, 'action_recommend_university')]")).get(1).click();
   
   Thread.sleep(3000);
   driver.findElement(By.xpath("//*[contains(@name, 'action_goto_lead')]")).click();
   
   
}

public void U8bucket() throws InterruptedException
{
	CandidateData();
	
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
	


public void MSASigned() throws InterruptedException, AWTException
{
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).click();
	
	 driver.findElements(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).get(0).sendKeys("JAGSoM (Opt-IN)");//---------Giving MAS Documnet Input----------//
	 Thread.sleep(2000);

		
		driver.findElement(By.xpath("//a[@class='dropdown-item ui-menu-item-wrapper text-truncate ui-state-active']")).click();		
		
		//	driver.findElement(By.xpath("//*[contains(@class, 'oe_fileupload')]")).click();
		//	 Thread.sleep(5000);
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


public void U9bucketStage1() throws InterruptedException
{
	CandidateData();
	
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

public void UniversitySelection() throws InterruptedException
{
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).click();
	//selected_university_id
	driver.findElement(By.id("selected_university_id")).sendKeys("Michigan State University, Masters in Accounting");
	List <WebElement> Options = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
	Options.get(0).click();
	Thread.sleep(3000);
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//button[@class='btn btn-primary'][normalize-space()='Ok']")).click();
	Thread.sleep(3000);

}


public void U9bucketStage2LOR() throws InterruptedException
{
	CandidateData();
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_subtitle  kanban_tiles_subtitle')]")).click();
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercase')]")).get(0).isDisplayed();
	System.out.println("Current Candidate is in "+driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercas')]")).get(0).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn oe_subtotal_footer button_green_color btn-secondary')]")).isDisplayed();
	System.out.println("Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn oe_subtotal_footer button_green_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).isDisplayed();
	System.out.println("Brown Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).isDisplayed();
	System.out.println("Red Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).isDisplayed();
	System.out.println("Yellow Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).getText());
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).get(1).isDisplayed();
	System.out.println("Brown2 Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());

}


public void ScrollToLORatU9() throws InterruptedException 
{
	Actions act = new Actions(driver);
	act.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'student_lor')]"))).perform();
	driver.findElement(By.xpath("//*[contains(@name, 'student_lor')]")).click();
	Thread.sleep(3000);
	act.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'action_generate_lor')]"))).perform();
	//driver.findElement(By.className("fa fa-plus")).click();
}



public void StudentLORandSOP() throws InterruptedException
{
	
	Actions act = new Actions(driver);
	act.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'telephony_call_logs')]"))).perform();
	Thread.sleep(2000);
	
	String text = driver.findElement(By.id("lor_updated")).getText();

	if (text != null && !text.trim().isEmpty()) 
	{
	    System.out.println(text);
	}
	else 
	{
	    System.out.println("The text is empty or not found.");
	    throw new AssertionError("The text is empty or not found.");
	}
	
	//System.out.println(driver.findElement(By.id("lor_updated")).getText());

	driver.findElement(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).sendKeys("Akhila Chandrashekar");
	Thread.sleep(2000);
	
	List <WebElement> Options1 = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
	Options1.get(0).click();
	Thread.sleep(2000);
    driver.findElement(By.xpath("//*[contains(@name, 'action_update_lor')]")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary')]")).click();
    Thread.sleep(8000);

	
    ///----------Entrieing Student SOP------------------//
    
	driver.findElement(By.id("sop_updated")).click();
	driver.findElement(By.id("sop_updated")).sendKeys("Automated Script SOP for Candidate.");
	Thread.sleep(2000);
	 driver.findElements(By.xpath("//*[contains(@name, 'action_update_sop')]")).get(1).click();
	  
	  Thread.sleep(6000);
	

    
	Actions act1 = new Actions(driver);
	act1.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'action_lor_submit_wizard')]"))).perform();
	Thread.sleep(2000);
	
	
	
	driver.findElement(By.xpath("//*[contains(@name, 'action_lor_submit_wizard')]")).click();
	
	Thread.sleep(3000);
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
	Thread.sleep(3000);
//	
//	driver.findElement(By.xpath("//*[contains(@class, 'btn oe_subtotal_footer button_green_color btn-secondary')]")).click();
//	Thread.sleep(3000);
//	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary')]")).click();
//	Thread.sleep(3000);
}


public void U9Stage3() throws InterruptedException, AWTException
{
	CandidateData();
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_subtitle  kanban_tiles_subtitle')]")).click();
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercase')]")).get(0).isDisplayed();
	System.out.println("Current Candidate is in "+driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercas')]")).get(0).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn oe_subtotal_footer button_green_color btn-secondary')]")).isDisplayed();
	System.out.println("Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn oe_subtotal_footer button_green_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).isDisplayed();
	System.out.println("Brown Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).isDisplayed();
	System.out.println("Red Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).isDisplayed();
	System.out.println("Yellow Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).getText());
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).get(1).isDisplayed();
	System.out.println("Brown2 Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());

}


public void UploadationOfApplicationProof() throws InterruptedException, AWTException

{
	driver.findElement(By.xpath("//*[contains(@class, 'btn oe_subtotal_footer button_green_color btn-secondary')]")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary')]")).click();
	Thread.sleep(3000);
	//--------------------------------------------------------------------------------------------------------//
		driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).click();

		WebElement SubmissionScreenshot = driver.findElements(By.className("o_file_input_trigger")).get(0);
		SubmissionScreenshot.click();
	    Thread.sleep(5000);
	    Robot robot1 = new Robot();
	    StringSelection filePath1 = new StringSelection("C:\\Users\\MANOJ.HR\\Pictures\\Screenshots\\MASATTACHEMENT.png");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath1, null);
	    robot1.keyPress(KeyEvent.VK_CONTROL);
	    robot1.keyPress(KeyEvent.VK_V);
	    robot1.keyRelease(KeyEvent.VK_V);
	    robot1.keyRelease(KeyEvent.VK_CONTROL);
	    robot1.keyPress(KeyEvent.VK_ENTER);
	    robot1.keyRelease(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
	    
		//---------------------------------------------------------------------------------------------------------//
		WebElement EmailScreenshot = driver.findElements(By.className("o_file_input_trigger")).get(1);
		EmailScreenshot.click();
	    Thread.sleep(5000);
	    Robot robot2 = new Robot();
	    StringSelection filePath2 = new StringSelection("C:\\Users\\MANOJ.HR\\Pictures\\Screenshots\\MASATTACHEMENT.png");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath2, null);
	    robot2.keyPress(KeyEvent.VK_CONTROL);
	    robot2.keyPress(KeyEvent.VK_V);	    
	    robot2.keyRelease(KeyEvent.VK_V);
	    robot2.keyRelease(KeyEvent.VK_CONTROL);
	    robot2.keyPress(KeyEvent.VK_ENTER);
	    robot2.keyRelease(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
		//-------------------------------------------------------------------------------------------------------------------//
		WebElement PaymentScreenshot = driver.findElements(By.className("o_file_input_trigger")).get(2);
		PaymentScreenshot.click();
	    Thread.sleep(5000);  
	    Robot robot3 = new Robot();
	    StringSelection filePath3 = new StringSelection("C:\\Users\\MANOJ.HR\\Pictures\\Screenshots\\MASATTACHEMENT.png");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath3, null);
	    robot3.keyPress(KeyEvent.VK_CONTROL);
	    robot3.keyPress(KeyEvent.VK_V);
	    robot3.keyRelease(KeyEvent.VK_V);
	    robot3.keyRelease(KeyEvent.VK_CONTROL);
	    robot3.keyPress(KeyEvent.VK_ENTER);
	    robot3.keyRelease(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("//*[contains(@name, 'action_selected_enrolled_university')]")).click();  
	    Thread.sleep(1500);
	    driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(2).click();
	    Thread.sleep(7000);
	    
}

public void U9PlusBucket() throws InterruptedException

{
	CandidateData();
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_subtitle  kanban_tiles_subtitle')]")).click();
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercase')]")).get(0).isDisplayed();
	System.out.println("Current Candidate is in "+driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercas')]")).get(0).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).isDisplayed();
	System.out.println("Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).isDisplayed();
	System.out.println("Brown Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).isDisplayed();
	System.out.println("Red Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).isDisplayed();
	System.out.println("Yellow Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).getText());
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).get(1).isDisplayed();
	System.out.println("Brown2 Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());
}



public void UPPlusReuploading() throws InterruptedException, AWTException
{
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).isDisplayed();
	
	driver.findElement(By.xpath("//*[contains(@name, 'action_student_application_list')]")).click();
	Thread.sleep(1500);
	driver.findElement(By.xpath("//*[contains(@name, 'action_open_document_attachment_state_wizard')]")).click();
	
	driver.findElements(By.xpath("//*[contains(@class, 'o_data_cell cursor-pointer o_field_cell o_list_char text-info')]")).get(0).isDisplayed();
	System.out.println("Confirmation Screenshot is "+driver.findElement(By.xpath("//*[contains(@class, 'o_data_cell cursor-pointer o_field_cell o_list_char text-info')]")).getText());
	
	driver.findElements(By.xpath("//*[contains(@class, 'o_data_cell cursor-pointer o_field_cell o_list_char text-info')]")).get(1).isDisplayed();
	System.out.println("Email Screenshot is "+driver.findElement(By.xpath("//*[contains(@class, 'o_data_cell cursor-pointer o_field_cell o_list_char text-info')]")).getText());
	
	driver.findElements(By.xpath("//*[contains(@class, 'o_data_cell cursor-pointer o_field_cell o_list_char text-info')]")).get(2).isDisplayed();
	System.out.println("Payment Screenshot is "+driver.findElement(By.xpath("//*[contains(@class, 'o_data_cell cursor-pointer o_field_cell o_list_char text-info')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@name, 'action_reupload')]")).click();//Adding Remark for COnfirmation Screenshot status.//
	Thread.sleep(2000);
	
	driver.findElement(By.id("remark")).sendKeys("Adding Remark to Check Reupload");
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@name, 'action_submit_remark')]")).click();
	Thread.sleep(2000);
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-secondary')]")).get(2).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-sm btn-info')]")).click();
	
	WebElement SubmissionScreenshot = driver.findElements(By.className("o_file_input_trigger")).get(0);
	SubmissionScreenshot.click();
    Thread.sleep(5000);
    Robot robot1 = new Robot();
    StringSelection filePath1 = new StringSelection("C:\\Users\\MANOJ.HR\\Pictures\\Screenshots\\MASATTACHEMENT.png");
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath1, null);
    robot1.keyPress(KeyEvent.VK_CONTROL);
    robot1.keyPress(KeyEvent.VK_V);
    robot1.keyRelease(KeyEvent.VK_V);
    robot1.keyRelease(KeyEvent.VK_CONTROL);
    robot1.keyPress(KeyEvent.VK_ENTER);
    robot1.keyRelease(KeyEvent.VK_ENTER);
    Thread.sleep(10000);
    
    driver.findElement(By.xpath("//*[contains(@name, 'action_update_line')]")).click();
    Thread.sleep(5000);
}

public void U9VerifyDocuments() throws InterruptedException
{
//---------------------------------Verifying the Documents----------------------//
    
    driver.findElement(By.xpath("//*[contains(@name, 'action_open_document_attachment_state_wizard')]")).click();
    
    driver.findElements(By.xpath("//*[contains(@name, 'action_approve')]")).get(0).click();
    Thread.sleep(2000);
    
    driver.findElements(By.xpath("//*[contains(@name, 'action_approve')]")).get(0).click();
    Thread.sleep(2000);
    
    driver.findElements(By.xpath("//*[contains(@name, 'action_approve')]")).get(0).click();
    Thread.sleep(2000);
   
    driver.findElements(By.xpath("//*[contains(@class, 'btn btn-secondary')]")).get(2).click();
	Thread.sleep(1500);
}

public void U9plusVerifyApplicationSubmitted() throws InterruptedException
{
	CandidateData();
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_subtitle  kanban_tiles_subtitle')]")).click();
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercase')]")).get(0).isDisplayed();
	System.out.println("Current Candidate is in "+driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercas')]")).get(0).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).isDisplayed();
	System.out.println("Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).isDisplayed();
	System.out.println("Brown Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).isDisplayed();
	System.out.println("Red Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).isDisplayed();
	System.out.println("Yellow Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).getText());
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).get(1).isDisplayed();
	System.out.println("Brown2 Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());
}



public void U9PlusAddingMOI()throws InterruptedException, AWTException
{
	//--------------------------------------ADDING MOI IN EDUCATION ---------------------------//
	
	
		driver.findElement(By.xpath("//*[contains(@name, 'education_details')]")).click();
		
		Actions act1 = new Actions(driver);
		act1.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'telephony_call_logs')]"))).perform();
		Thread.sleep(2000);
		
		
		Actions act2 = new Actions(driver);
		act2.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'action_edit_graduation_line_wizard')]"))).perform();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[contains(@name, 'action_edit_graduation_line_wizard')]")).click();
		
		WebElement SubmissionScreenshot = driver.findElements(By.className("o_file_input_trigger")).get(2);
		SubmissionScreenshot.click();
	    Thread.sleep(5000);
	    Robot robot1 = new Robot();
	    StringSelection filePath1 = new StringSelection("C:\\Users\\MANOJ.HR\\Pictures\\Screenshots\\MASATTACHEMENT.png");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath1, null);
	    robot1.keyPress(KeyEvent.VK_CONTROL);
	    robot1.keyPress(KeyEvent.VK_V);
	    robot1.keyRelease(KeyEvent.VK_V);
	    robot1.keyRelease(KeyEvent.VK_CONTROL);
	    robot1.keyPress(KeyEvent.VK_ENTER);
	    robot1.keyRelease(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
	    
		driver.findElement(By.xpath("//*[contains(@name, 'action_update_line')]")).click();
		
		driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@name, 'action_selected_enrolled_university')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='btn btn-primary'][normalize-space()='Ok']")).click();
		Thread.sleep(3000);
}


public void U10BucketStage1() throws InterruptedException, AWTException

{
	CandidateData();
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_subtitle  kanban_tiles_subtitle')]")).click();
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercase')]")).get(0).isDisplayed();
	System.out.println("Current Candidate is in "+driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercas')]")).get(0).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).isDisplayed();
	System.out.println("Light Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).isDisplayed();
	System.out.println("Brown Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_blue_color btn-secondary')]")).isDisplayed();
	System.out.println("Blue Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_blue_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).isDisplayed();
	System.out.println("Red Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).isDisplayed();
	System.out.println("Yellow Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).getText());
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).get(1).isDisplayed();
	System.out.println("Brown2 Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());
	
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).click();	
	Thread.sleep(1000);
	WebElement SubmissionScreenshot = driver.findElement(By.className("o_file_input_trigger"));
	SubmissionScreenshot.click();
    Thread.sleep(5000);
    Robot robot1 = new Robot();
    StringSelection filePath1 = new StringSelection("C:\\Users\\MANOJ.HR\\Pictures\\Screenshots\\MASATTACHEMENT.png");
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath1, null);
    robot1.keyPress(KeyEvent.VK_CONTROL);
    robot1.keyPress(KeyEvent.VK_V);
    robot1.keyRelease(KeyEvent.VK_V);
    robot1.keyRelease(KeyEvent.VK_CONTROL);
    robot1.keyPress(KeyEvent.VK_ENTER);
    robot1.keyRelease(KeyEvent.VK_ENTER);
    Thread.sleep(10000);
    
    
    driver.findElement(By.xpath("//*[contains(@name, 'action_selected_enrolled_university')]")).click();
    Thread.sleep(2500);
    driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(2).click();
  	Thread.sleep(2500);
  	
}

public void U10BucketStage2() throws InterruptedException
{
	CandidateData();
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_subtitle  kanban_tiles_subtitle')]")).click();
	
	Thread.sleep(1500);
	 driver.findElement(By.xpath("//*[contains(@name, 'action_student_application_list')]")).click();
	 //----------------------Approveing the documents----------------------------------//
	 
	 driver.findElement(By.xpath("//*[contains(@name, 'action_open_document_attachment_state_wizard')]")).click();
	    
	    driver.findElements(By.xpath("//*[contains(@name, 'action_approve')]")).get(0).click();
	    Thread.sleep(5000);
	    
	    driver.findElement(By.xpath("//span[normalize-space()='Cancel']")).click();
		Thread.sleep(1500);
	
}

public void U10BucketStage3() throws InterruptedException
{
	CandidateData();
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_subtitle  kanban_tiles_subtitle')]")).click();
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).click();
	Thread.sleep(1000);
	
    driver.findElement(By.xpath("//*[contains(@name, 'action_selected_enrolled_university')]")).click();
    Thread.sleep(1000);
    
    driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(2).click();
  	Thread.sleep(1500); 
}



public void U11BucketStage1() throws InterruptedException
{
	CandidateData();
	
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

	//funding_type
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).click();
	Thread.sleep(2000);
	
	  WebElement paymentDropdown = driver.findElement(By.id("funding_type"));

      // Create a Select object for the dropdown
      Select select = new Select(paymentDropdown);

      // Select the option by value
      select.selectByValue("\"0\""); // Ensure to include the exact value here

      // OR: Select the option by visible text
      // select.selectByVisibleText("Self Payment");

      // Optional: Print selected option to verify
      WebElement selectedOption = select.getFirstSelectedOption();
      System.out.println("Selected option is: " + selectedOption.getText());
  
      driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
    Thread.sleep(1500); 
    
}

public void ScrollToVisaSlotU15() throws InterruptedException 
{

	Actions act = new Actions(driver);
	act.moveToElement(driver.findElements(By.xpath("//*[contains(@name, 'visa_slot')]")).get(1)).perform();
	driver.findElements(By.xpath("//*[contains(@name, 'visa_slot')]")).get(1).click();
	Thread.sleep(2000);
	
	Actions act1 = new Actions(driver);
	act1.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'telephony_call_logs')]"))).perform();
	Thread.sleep(2000);
}

public void ScrollToFundingU11() throws InterruptedException 
{
	Actions act = new Actions(driver);
	act.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'funding')]"))).perform();
	driver.findElement(By.xpath("//*[contains(@name, 'funding')]")).click();
	Thread.sleep(2000);
	act.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'action_open_payment_wizard')]"))).perform();
	//driver.findElement(By.className("fa fa-plus")).click();
}


public void U11BucketStage2() throws InterruptedException
{
	CandidateData();
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_subtitle  kanban_tiles_subtitle')]")).click();
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercase')]")).get(0).isDisplayed();
	System.out.println("Current Candidate is in "+driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercas')]")).get(0).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).isDisplayed();
	System.out.println("Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).getText());

	driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).isDisplayed();
	System.out.println("Red Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).isDisplayed();
	System.out.println("Yellow Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).isDisplayed();
	System.out.println("Brown Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());

	ScrollToFundingU11();
	Thread.sleep(2000);
	driver.findElements(By.xpath("//*[contains(@name, 'action_open_select_finance_fee_wizard')]")).get(1).click();
	Thread.sleep(2000);
	
	//Alternative way to select the other options//
	WebElement paymentDropdown = driver.findElement(By.id("funding_type"));

    // Create a Select object for the dropdown
    Select select = new Select(paymentDropdown);

    // Select the option by value
    select.selectByValue("\"1\""); //Loan Support in edit Miles Pathway Funding // Ensure to include the exact value here

    // OR: Select the option by visible text     // select.selectByVisibleText("Self support");

    WebElement selectedOption = select.getFirstSelectedOption();
    System.out.println("Selected option is: " + selectedOption.getText());

    ///---------------------Fee Recevied Status----------------------------------------//

	//Alternative way to select the other options//
	WebElement feeReceivedstatus = driver.findElement(By.id("fee_received_status"));

    // Create a Select object for the dropdown
    Select selectstatus = new Select(feeReceivedstatus);

    // Select the option by value
    selectstatus.selectByValue("\"2\""); //Loan Support in edit Miles Pathway Funding // Ensure to include the exact value here

    // OR: Select the option by visible text     // select.selectByVisibleText("Loan support");

    WebElement selectedStatus = select.getFirstSelectedOption();
    System.out.println("Selected option is: " + selectedStatus.getText());
    Thread.sleep(2000);
    
    driver.findElement(By.xpath("//*[contains(@name, 'action_finance_fee_manage_submit')]")).click();
	Thread.sleep(2000);
	
	//------------------------Final Confirmation of Stage2 U11 Bucket--------------//
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).click();
	
	driver.findElement(By.xpath("//*[contains(@class, 'o_field_widget o_readonly_modifier o_required_modifier o_field_selection')]")).isDisplayed();
	System.out.println("Final Funding Type selected by Automation script is "+driver.findElement(By.xpath("//*[contains(@class, 'o_field_widget o_readonly_modifier o_required_modifier o_field_selection')]")).getText());
	Thread.sleep(2000);
	
	//--------------------------------Fee collect Date-------------------------//
	driver.findElements(By.xpath("//*[contains(@class, 'o_datepicker_input o_input datetimepicker-input')]")).get(0).click();
	Thread.sleep(1000);

     driver.findElement(By.xpath("//*[contains(@class, 'day today')]")).click();
     Thread.sleep(2000);
     
     //--------------------------------Loan Sanction Date--------------------//
     	driver.findElements(By.xpath("//*[contains(@class, 'o_datepicker_input o_input datetimepicker-input')]")).get(1).click();
 		Thread.sleep(1000);

 		driver.findElement(By.xpath("//*[contains(@class, 'day today')]")).click();
 		Thread.sleep(2000);
 
 		driver.findElement(By.xpath("//*[contains(@name, 'action_finance_fee_manage_submit')]")).click();
 		
}

public void U15BucketStage3ApprovingVISADetails() throws InterruptedException
{
	CandidateData1();
	Thread.sleep(5000);

	ScrollToVisaSlotU15();
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-sm btn-warning')]")).get(0).click();
	Thread.sleep(2000);
	
	driver.findElement(By.xpath("//*[contains(@name, 'action_approve')]")).click();
	Thread.sleep(1500);
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-secondary')]")).get(2).click();
	Thread.sleep(3000);
	
	Actions act = new Actions(driver);
	act.moveToElement(driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]"))).perform();
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).click();
	Thread.sleep(2000);
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
    Thread.sleep(1500);
	
	
}

public void U12BucketUSFundingType() throws InterruptedException
{	
	CandidateData();
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_subtitle  kanban_tiles_subtitle')]")).click();
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercase')]")).get(0).isDisplayed();
	System.out.println("Current Candidate is in "+driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercas')]")).get(0).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).isDisplayed();
	System.out.println("Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).getText());

	driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).isDisplayed();
	System.out.println("Red Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).isDisplayed();
	System.out.println("Yellow Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).isDisplayed();
	System.out.println("Brown Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());

	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).click();
	
	
	 WebElement paymentDropdown = driver.findElement(By.id("funding_type"));

     // Create a Select object for the dropdown
     Select select = new Select(paymentDropdown);

     // Select the option by value
     select.selectByValue("\"0\""); // Ensure to include the exact value here
     // select.selectByVisibleText("Self Payment");

     // Optional: Print selected option to verify
     WebElement selectedOption = select.getFirstSelectedOption();
     System.out.println("Selected option is: " + selectedOption.getText());
     
     //--------------------US Funding Status Selections----------------//
     
     WebElement USFundingStatus = driver.findElement(By.id("fee_received_status_us"));

     // Create a Select object for the dropdown
     Select selectdropdown = new Select(USFundingStatus);

     // Select the option by value
     selectdropdown.selectByValue("\"2\""); // Ensure to include the exact value here
     // select.selectByVisibleText("Payment Completed");

     // Optional: Print selected option to verify
     WebElement selectedropoption = selectdropdown.getFirstSelectedOption();
     System.out.println("Selected option is: " + selectedropoption.getText());
     
    //------------------------Selecting Bank Name---------------------//
     
     driver.findElement(By.id("bank_id")).click();
     
     driver.findElement(By.id("bank_id")).sendKeys("SBI");
     Thread.sleep(2000);
 	List <WebElement> Options = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
 	Options.get(0).click();
 	
 	 driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
     Thread.sleep(1500); 
     
}

public void U13ABucketStage1USFunding() throws InterruptedException, AWTException 
{
	CandidateData();
	
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


public void DocumentCollectionforUSFundingDocuments() throws InterruptedException, AWTException

{	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).click();

//--------------------------------------Uploading Documents for Passport---------------------//
	driver.findElements(By.xpath("//*[contains(@name, 'doc_attachment_ids')]")).get(0).click();	
	
	WebElement passportDocument = driver.findElement(By.className("o_file_input_trigger"));
	passportDocument.click();
	Thread.sleep(5000);
	Robot robot = new Robot();
	StringSelection filePath = new StringSelection("C:\\Users\\MANOJ.HR\\Pictures\\Screenshots\\MASATTACHEMENT.png");
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(10000);
	    
		//-----------------------------------------Loan Sanction Letter----------------------------------------------------------------//
	driver.findElements(By.xpath("//*[contains(@name, 'doc_attachment_ids')]")).get(2).click();	
	WebElement LoanSanctionLetter = driver.findElements(By.className("o_file_input_trigger")).get(0);
	LoanSanctionLetter.click();
	    Thread.sleep(5000);
	    Robot robot2 = new Robot();
	    StringSelection filePath2 = new StringSelection("C:\\Users\\MANOJ.HR\\Pictures\\Screenshots\\MASATTACHEMENT.png");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath2, null);
	    robot2.keyPress(KeyEvent.VK_CONTROL);
	    robot2.keyPress(KeyEvent.VK_V);	    
	    robot2.keyRelease(KeyEvent.VK_V);
	    robot2.keyRelease(KeyEvent.VK_CONTROL);
	    robot2.keyPress(KeyEvent.VK_ENTER);
	    robot2.keyRelease(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
	    
		//---------------------------------------------------Bank Balance Certificate-----------------------------------------------//
	    driver.findElements(By.xpath("//*[contains(@name, 'doc_attachment_ids')]")).get(4).click();	
	    WebElement BankBalanceCertificate = driver.findElements(By.className("o_file_input_trigger")).get(0);
	    BankBalanceCertificate.click();
	    Thread.sleep(5000);  
	    Robot robot3 = new Robot();
	    StringSelection filePath3 = new StringSelection("C:\\Users\\MANOJ.HR\\Pictures\\Screenshots\\MASATTACHEMENT.png");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath3, null);
	    robot3.keyPress(KeyEvent.VK_CONTROL);
	    robot3.keyPress(KeyEvent.VK_V);
	    robot3.keyRelease(KeyEvent.VK_V);
	    robot3.keyRelease(KeyEvent.VK_CONTROL);
	    robot3.keyPress(KeyEvent.VK_ENTER);
	    robot3.keyRelease(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
	    
	//---------------------------------------------Financial Affidavit---------------------------------------------------------//
		
	    driver.findElements(By.xpath("//*[contains(@name, 'doc_attachment_ids')]")).get(6).click();	
	    WebElement FinancialAffidavit = driver.findElements(By.className("o_file_input_trigger")).get(0);
	    FinancialAffidavit.click();
	    Thread.sleep(5000);  
	    Robot robot4 = new Robot();
	    StringSelection filePath4 = new StringSelection("C:\\Users\\MANOJ.HR\\Pictures\\Screenshots\\MASATTACHEMENT.png");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath4, null);
	    robot4.keyPress(KeyEvent.VK_CONTROL);
	    robot4.keyPress(KeyEvent.VK_V);
	    robot4.keyRelease(KeyEvent.VK_V);
	    robot4.keyRelease(KeyEvent.VK_CONTROL);
	    robot4.keyPress(KeyEvent.VK_ENTER);
	    robot4.keyRelease(KeyEvent.VK_ENTER);
	    Thread.sleep(10000);
	    
	    
	    driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
	     Thread.sleep(1500);   
	    
}


public void U13ABucketStage2USFunding() throws InterruptedException, AWTException
{
	CandidateData();
	
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
	
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_subtitle  kanban_tiles_subtitle')]")).click();
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercase')]")).get(0).isDisplayed();
	System.out.println("Current Candidate is in "+driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercas')]")).get(0).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).isDisplayed();
	System.out.println("Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).getText());

	driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).isDisplayed();
	System.out.println("Red Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).isDisplayed();
	System.out.println("Yellow Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).isDisplayed();
	System.out.println("Brown Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());
     
}

public void ApprovingCollectedDocumentsU13A() throws InterruptedException
{
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).click();

	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
    Thread.sleep(1500); 

    		//--------------------Negative Flow where not able to save without Verifying the Documents------------------------//
	driver.findElements(By.xpath("//*[contains(@class, 'modal-body')]")).get(1).isDisplayed();
	System.out.println("Error Message is "+driver.findElements(By.xpath("//*[contains(@class, 'modal-body')]")).get(1).getText());
	Thread.sleep(1000);
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary o-default-button')]")).get(1).click();
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-secondary')]")).get(2).click();
    Thread.sleep(1500); 
    
    //----------------------------------Back to Positive Flow where approving the Documents--------------------//
  
    driver.findElement(By.xpath("//*[contains(@name, 'student_document_line')]")).click();
   Thread.sleep(2000);
   
    Actions act1 = new Actions(driver);
	act1.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'telephony_call_logs')]"))).perform();
	Thread.sleep(2000);
	
	//--------------------------Verifying Uploaded BankBalance--------------------------//
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-sm btn-warning')]")).get(0).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@name, 'action_approve')]")).click();
	Thread.sleep(1500);
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-secondary')]")).get(2).click();
	Thread.sleep(3000);
	
	//--------------------------Verifying Uploaded Passport--------------------------//
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-sm btn-warning')]")).get(0).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@name, 'action_approve')]")).click();
	Thread.sleep(1500);
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-secondary')]")).get(2).click();
	Thread.sleep(3000);
	
	//--------------------------Verifying Uploaded Loan Sanction--------------------------//
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-sm btn-warning')]")).get(0).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@name, 'action_approve')]")).click();
	Thread.sleep(1500);
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-secondary')]")).get(2).click();
	Thread.sleep(3000);
	
	//--------------------------Verifying Uploaded Financial Affidavit--------------------------//
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-sm btn-warning')]")).get(0).click();
	Thread.sleep(2000); 
	driver.findElement(By.xpath("//*[contains(@name, 'action_approve')]")).click();
	Thread.sleep(1500);
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-secondary')]")).get(2).click();
	Thread.sleep(3000);
 
    Actions act2 = new Actions(driver);
   	act2.moveToElement(driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]"))).perform();
   	Thread.sleep(2000);
 
   	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).click();
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
    Thread.sleep(1500);
   	
}
public void U13BBucketSkipFinancialStage1() throws InterruptedException

{

	CandidateData1();
		
	Thread.sleep(2000);
	driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercase')]")).get(0).isDisplayed();
	System.out.println("Current Candidate is in "+driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercas')]")).get(0).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn oe_subtotal_footer button_orange_color btn-secondary')]")).isDisplayed();
	System.out.println("Light Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn oe_subtotal_footer button_orange_color btn-secondary')]")).getText());

	driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).isDisplayed();
	System.out.println("Red Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).isDisplayed();
	System.out.println("Yellow Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).isDisplayed();
	System.out.println("Brown Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn oe_subtotal_footer button_orange_color btn-secondary')]")).click();
	
    Thread.sleep(1500);
}

public void U13BBucketStage2()throws InterruptedException, AWTException
{
	CandidateData1();
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).isDisplayed();
	System.out.println("Stage is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).click();
	
	driver.findElements(By.xpath("//*[contains(@name, 'doc_attachment_ids')]")).get(0).click();	
	
	WebElement passportDocument = driver.findElement(By.className("o_file_input_trigger"));
	passportDocument.click();
	Thread.sleep(5000);
	Robot robot = new Robot();
	StringSelection filePath = new StringSelection("C:\\Users\\MANOJ.HR\\Pictures\\Screenshots\\MASATTACHEMENT.png");
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(10000);
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
    Thread.sleep(1500); 
}

public void U13BBucketStage3() throws InterruptedException
{

	CandidateData1();
	
	Thread.sleep(3000);
	
			//----------------------------------Approvving US Finace Proof------------------------//
	driver.findElement(By.xpath("//*[contains(@name, 'student_document_line')]")).click();
	Thread.sleep(2000);
	   
	    Actions act1 = new Actions(driver);
		act1.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'telephony_call_logs')]"))).perform();
		Thread.sleep(2000);
	
		
		driver.findElements(By.xpath("//*[contains(@class, 'btn btn-sm btn-warning')]")).get(0).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@name, 'action_approve')]")).click();
		Thread.sleep(1500);
		driver.findElements(By.xpath("//*[contains(@class, 'btn btn-secondary')]")).get(2).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).isDisplayed();
		System.out.println("Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).getText());
		
		
		driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).click();
		
		driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
		Thread.sleep(3000);	
}


public void U13CBucketStage1() throws InterruptedException, AWTException
{

	CandidateData1();
	Thread.sleep(3000);
	
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
	
	//-----------------------------Uploading Copy Of I20 Documents-----------------//
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).click();
	Thread.sleep(1000);
	
	driver.findElements(By.xpath("//*[contains(@name, 'doc_attachment_ids')]")).get(0).click();	
	
	WebElement passportDocument = driver.findElement(By.className("o_file_input_trigger"));
	passportDocument.click();
	Thread.sleep(5000);
	Robot robot = new Robot();
	StringSelection filePath = new StringSelection("C:\\Users\\MANOJ.HR\\Pictures\\Screenshots\\MASATTACHEMENT.png");
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(10000);
	
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
    Thread.sleep(1500); 
}
public void U13CBucketStage2() throws InterruptedException

{	
	CandidateData1();
	Thread.sleep(3000);
	
	//----------------------------------Approvving US I20 Proof------------------------//
driver.findElement(By.xpath("//*[contains(@name, 'student_document_line')]")).click();
Thread.sleep(2000);
   
    Actions act1 = new Actions(driver);
	act1.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'telephony_call_logs')]"))).perform();
	Thread.sleep(2000);

	
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-sm btn-warning')]")).get(0).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@name, 'action_approve')]")).click();
	Thread.sleep(1500);
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-secondary')]")).get(2).click();
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).isDisplayed();
	System.out.println("Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).getText());
	
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).click();
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
	Thread.sleep(3000);	
	
}

public void U14BucketStage1() throws InterruptedException
{
	CandidateData1();
	Thread.sleep(3000);
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercase')]")).get(0).isDisplayed();
	System.out.println("Current Candidate is in "+driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercas')]")).get(0).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn oe_subtotal_footer button_orange_color btn-secondary')]")).isDisplayed();
	System.out.println("Light Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn oe_subtotal_footer button_orange_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).isDisplayed();
	System.out.println("Red Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).isDisplayed();
	System.out.println("Yellow Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).isDisplayed();
	System.out.println("Brown Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());
	
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn oe_subtotal_footer button_orange_color btn-secondary')]")).click();
	
	
}

public void U14BucketStage2() throws InterruptedException, AWTException
{
	CandidateData1();
	Thread.sleep(3000);
	
	//-----------------------------Uploading DS160 Documents--------------------//
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).click();
	Thread.sleep(1000);
	
	driver.findElements(By.xpath("//*[contains(@name, 'doc_attachment_ids')]")).get(0).click();	
	
	WebElement passportDocument = driver.findElement(By.className("o_file_input_trigger"));
	passportDocument.click();
	Thread.sleep(5000);
	Robot robot = new Robot();
	StringSelection filePath = new StringSelection("C:\\Users\\MANOJ.HR\\Pictures\\Screenshots\\MASATTACHEMENT.png");
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(10000);
	
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
    Thread.sleep(1500); 	
	
	
}

public void U14BucketStage3() throws InterruptedException
{
	CandidateData1();
	Thread.sleep(3000);
			//-------------------------------------Approving DS160--------------------------//
	driver.findElement(By.xpath("//*[contains(@name, 'student_document_line')]")).click();
	Thread.sleep(2000);
	   
	    Actions act1 = new Actions(driver);
		act1.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'telephony_call_logs')]"))).perform();
		Thread.sleep(2000);
	
		driver.findElements(By.xpath("//*[contains(@class, 'btn btn-sm btn-warning')]")).get(0).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@name, 'action_approve')]")).click();
		Thread.sleep(1500);
		driver.findElements(By.xpath("//*[contains(@class, 'btn btn-secondary')]")).get(2).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).isDisplayed();
		System.out.println("Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).getText());
		
		
		driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).click();
		Thread.sleep(3000);
		driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
		Thread.sleep(3000);	
}

public void U15BucketStage1MandateFeilds() throws InterruptedException
{
	CandidateData1();
	Thread.sleep(3000);

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

	//---------------------------------Negative FLow For Mandate Feilds---------------//
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).click();

	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
	
	driver.findElement(By.xpath("//*[contains(@class, 'o_notification o_notification_fade border border-danger bg-white mb-2 position-relative o_notification_fade-enter-active')]")).isDisplayed();
	System.out.println("Error Accored for Mandate Feilds "+driver.findElement(By.xpath("//*[contains(@class, 'o_notification o_notification_fade border border-danger bg-white mb-2 position-relative o_notification_fade-enter-active')]")).getText());
}

public void TooBookVISASlot() throws InterruptedException, AWTException
{
	driver.findElement(By.xpath("//*[contains(@title, 'Meetings')]")).click();
	Thread.sleep(2000);
	driver.findElements(By.xpath("//*[contains(@role, 'menuitem')]")).get(2).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary o_list_button_add')]")).click();

	driver.findElement(By.id("job_position")).click();
	Thread.sleep(2000);
	driver.findElement(By.id("job_position")).clear();
	Thread.sleep(2000);
	
	driver.findElements(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).get(0).sendKeys("Visa Support Expert");
	Thread.sleep(2000);

	List <WebElement> Options1 = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
	Options1.get(0).click();
	Thread.sleep(2000);
	
		driver.findElement(By.id("emp_id")).click();
	Thread.sleep(3000);
	driver.findElement(By.id("emp_id")).sendKeys("Syed Pasha");
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
  //----------------------------------------------------------------------------------//
    Thread.sleep(2000);
	WebElement button = driver.findElement(By.xpath("//a[@role='button' and text()='Add a line']"));
    button.click();
    Thread.sleep(2000);
    WebElement session =  driver.findElement((By.xpath("//*[contains(@class, 'o_data_cell cursor-pointer o_field_cell o_list_many2one o_required_modifier')]")));
    session.click();
    Thread.sleep(3000);
 
    driver.findElements(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).get(2).sendKeys("VISA Mock Session");

    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[contains(@class, 'o_form_button_save btn btn-light py-0')]")).click();//To Save Button//
    Thread.sleep(2000);
    driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary')]")).click(); //To confimring Allocation//
    Thread.sleep(2000);
    driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click(); 
    Thread.sleep(2000);
 
}


public void U16Bucket() throws InterruptedException
{
	CandidateData1();
	Thread.sleep(5000);
	
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercase')]")).get(0).isDisplayed();
	System.out.println("Current Candidate is in "+driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercas')]")).get(0).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).isDisplayed();
	System.out.println("Light Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_blue_color btn-secondary')]")).isDisplayed();
	System.out.println("Blue Button 1 is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_blue_color btn-secondary')]")).getText());
	
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).isDisplayed();
	System.out.println("Red Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).isDisplayed();
	System.out.println("Yellow Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).isDisplayed();
	System.out.println("Brown Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());

}
public void U15BucketStage2UploadingVISADetails() throws InterruptedException, AWTException

{
	CandidateData1();
	Thread.sleep(3000);
	
		////-------------------------Uploading VISA SLOT Details and Documents----------------------//
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).click();
	
	driver.findElement(By.id("visa_city_id")).click();
	Thread.sleep(2000);
	driver.findElements(By.xpath("//*[contains(@class, 'o-autocomplete--input o_input')]")).get(0).sendKeys("Dubai");
	Thread.sleep(2000);
	List <WebElement> Options1 = driver.findElements((By.xpath("//*[contains(@class, 'o-autocomplete--dropdown-menu dropdown-menu ui-widget ui-autocomplete show')]")));
	Options1.get(0).click();
	
	
	
		//--------------------------------VISA Slot Date-------------------------//
		driver.findElements(By.xpath("//*[contains(@class, 'o_datepicker_input o_input datetimepicker-input')]")).get(1).click();
		Thread.sleep(1000);

	     driver.findElement(By.xpath("//*[contains(@class, 'day today')]")).click();
	     Thread.sleep(2000);
	
	     driver.findElement(By.xpath("//*[contains(@title, 'Close the picker')]")).click(); 
	     Thread.sleep(1000);
	     //---------------------------VISA SLOT PROOF---------------------------//
	     
	 	WebElement VisaSlotDocument = driver.findElement(By.className("o_file_input_trigger"));
	 	VisaSlotDocument.click();
		Thread.sleep(5000);
		Robot robot = new Robot();
		StringSelection filePath = new StringSelection("C:\\Users\\MANOJ.HR\\Pictures\\Screenshots\\MASATTACHEMENT.png");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(10000);
		
		
		driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
	    Thread.sleep(1500); 
	     
}


public void U16VisaRecevied() throws InterruptedException
{
	CandidateData1();
	Thread.sleep(5000);
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercase')]")).get(0).isDisplayed();
	System.out.println("Current Candidate is in "+driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercas')]")).get(0).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).isDisplayed();
	System.out.println("Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).isDisplayed();
	System.out.println("Red Button 1 is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).getText());
	
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).get(1).isDisplayed();
	System.out.println("Red Button 2 is "+driver.findElements(By.xpath("//*[contains(@class, 'btn button_red_color btn-secondary')]")).get(1).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).isDisplayed();
	System.out.println("Yellow Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).isDisplayed();
	System.out.println("Brown Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());
	
	//---------------------------------------------Visa Received---------------------//
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).click();
	
}

public void U17Stage1UploadingVISA() throws InterruptedException, AWTException
{
	CandidateData1();
	Thread.sleep(5000);
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercase')]")).get(0).isDisplayed();
	System.out.println("Current Candidate is in "+driver.findElements(By.xpath("//*[contains(@class, 'btn o_arrow_button_current o_arrow_button disabled text-uppercas')]")).get(0).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).isDisplayed();
	System.out.println("Light Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-danger btn-primary')]")).isDisplayed();
	System.out.println("Red Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn btn-danger btn-primary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).isDisplayed();
	System.out.println("Yellow Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_yellow_color btn-secondary')]")).getText());
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).isDisplayed();
	System.out.println("Brown Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_brown_color btn-secondary')]")).getText());
	
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_orange_color btn-secondary')]")).click();
	
	
driver.findElements(By.xpath("//*[contains(@name, 'doc_attachment_ids')]")).get(0).click();	
	
	WebElement passportDocument = driver.findElement(By.className("o_file_input_trigger"));
	passportDocument.click();
	Thread.sleep(5000);
	Robot robot = new Robot();
	StringSelection filePath = new StringSelection("C:\\Users\\MANOJ.HR\\Pictures\\Screenshots\\MASATTACHEMENT.png");
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	Thread.sleep(10000);
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
    Thread.sleep(1500); 
}

public void U17Stage2ApprovingVISA() throws InterruptedException
{

	CandidateData1();
	Thread.sleep(5000);
	
	//-------------------------------------Approving VISA--------------------------//
driver.findElement(By.xpath("//*[contains(@name, 'student_document_line')]")).click();
Thread.sleep(2000);
   
    Actions act1 = new Actions(driver);
	act1.moveToElement(driver.findElement(By.xpath("//*[contains(@name, 'telephony_call_logs')]"))).perform();
	Thread.sleep(2000);

	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-sm btn-warning')]")).get(0).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[contains(@name, 'action_approve')]")).click();
	Thread.sleep(1500);
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-secondary')]")).get(2).click();
	Thread.sleep(3000);
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).isDisplayed();
	System.out.println("Green Button is "+driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).getText());
	
	
	driver.findElement(By.xpath("//*[contains(@class, 'btn button_green_color btn-secondary')]")).click();
	
	driver.findElements(By.xpath("//*[contains(@class, 'btn btn-primary')]")).get(1).click();
	Thread.sleep(3000);
	
}
	public void U17Test() throws InterruptedException
	{
		//driver.findElement(By.xpath("//*[contains(@class, 'btn btn-light o_switch_view o_list oi oi-view-list')]")).click();
		ListView.click();
		
		
		Thread.sleep(2000);
		driver.findElement(By.className("o_searchview_input")).click();
		Thread.sleep(2000);
		driver.findElement(By.className("o_searchview_input")).sendKeys("AUTOMAYUON987");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[contains(@name, 'can_id')]")).click();
		Thread.sleep(5000);
	}
	
	
	
	
//	public void VerifyAdminDropdownoptions()
//	{
//		
//		driver.findElement(By.className("dropdown-toggle")).click();
//		List <WebElement> Options = driver.findElements((By.xpath("//*[contains(@class, 'dropdown-item o_app')]")));
//		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'dropdown-item o_app')]")).isDisplayed());
//		
//		Assert.assertEquals(Options.get(0).getText(), "USP Eligibility");
//		Assert.assertEquals(Options.get(1).getText(), "Miles ATS");
//		Assert.assertEquals(Options.get(2).getText(), "Miles Recruitments");
//		Assert.assertEquals(Options.get(3).getText(), "Helpdesk");
//		Assert.assertEquals(Options.get(4).getText(), "Discuss");
//		Assert.assertEquals(Options.get(5).getText(), "Calendar");
//		Assert.assertEquals(Options.get(6).getText(), "My Dashboard");
//		Assert.assertEquals(Options.get(7).getText(), "Job Queue");
//		Assert.assertEquals(Options.get(8).getText(), "Contacts");
//		Assert.assertEquals(Options.get(9).getText(), "Dashboards");
//		Assert.assertEquals(Options.get(10).getText(), "Surveys");
//		Assert.assertEquals(Options.get(11).getText(), "Employees");
//		Assert.assertEquals(Options.get(12).getText(), "Apps");
//		Assert.assertEquals(Options.get(13).getText(), "Settings");
//	}
	/*
	 * Method to click on Add Coach button
	 */
	
//	 public void VerifyUserLevelOptions() throws InterruptedException
//		{
//			Select UserLevelDropDown = new Select(driver.findElement(By.xpath("//*[contains(@title, 'Configuration')]")));
//			
//		
//			List<String> ConvertedList = new ArrayList<String>();
//			
//			
//			List<WebElement> ActualLevelsList=UserLevelDropDown.getOptions();
//			Thread.sleep(2000);
//				
//			for(int i = 0 ;i<ActualLevelsList.size();i++)
//			{
//				// Just print this for Debug purpose
//				System.out.println("Debug : -> Item "+i+" : "+ActualLevelsList.get(i).getText());
//			//	System.out.println("Debug : -> Expected List Item "+i+" : "+ExpectedUserList.get(i));
//				ConvertedList.add(ActualLevelsList.get(i).getText());
//				
//			//Assert.assertTrue(ConvertedList.get(i).equals(ExpectedUserList.get(i)),"Expected is "+ExpectedUserList.get(i)+" Actual is "+ConvertedList.get(i));
//
//			}
//			
//			//Assert.assertTrue(ConvertedList.equals(ExpectedUserList),"Not Equal");
//		
//			for(int j = 0 ;j<ActualLevelsList.size();j++)
//			{
//				Assert.assertTrue(ConvertedList.get(j).equals(ExpectedUserList.get(j)),"Expected is "+ExpectedUserList.get(j)+" Actual is "+ConvertedList.get(j));
//			}	
//		}
//
//	private List<String> ExpectedUserList = Arrays.asList("Allocation Configurations",
//            "Enrollment Batches",
//            "Enrollment University",
//            "Buckets",
//            "Enrollment Course",
//            "ATS Journey",
//            "LOR Question",
//            "Graduation Division",
//            "Pathway College",
//            "Sessions",
//            "Document Type",
//            "MSA Document Type",
//            "DS-160 Step",
//            "Ineligible Reason");
	
//	public void ClickOnAddUserButton()
//	{
//		AddNewUserBtn.click();
//	}
//		
//	
//	
//	public void VerifyIfSearchedUserAppeared(String email)
//	{
//		Assert.assertEquals(UserRecords.get(1) .getText(), email,"User Found");
//	}
//	
//	public void VerifyEmailIdPopUpisDisplayed()
//	{
//		Assert.assertEquals(EmailIdPopUp.getText(),"Email Address","Email ID Pop Up is Displayed");
//	}
//	
//	public void WaitForAdminPageToLoad()
//	{
//		MilesUtilities.WaitForVisibilityOfElement(driver, "id", "addNewTrainerBtn");
//	}
//	
//	public void SelectDisplyedUser()
//	{
//		UserRecords.get(0).click();
//	}
//	public void EnterUserInfo(String info) throws InterruptedException
//	{
//		UserSearchBox.click();
//		UserSearchBox.sendKeys(info);
//		Thread.sleep(1000);	
//		ButtonSearch.click();
//		Thread.sleep(3000);	
//	}
//	public void Enter_FHP_UserInfo(String info) throws InterruptedException
//	{
//		UserSearchBox.click();
//		UserSearchBox.sendKeys(info);
//		Thread.sleep(2000);	
//		
//	}
//	
//	
//	public void SelectDisplayedUser()
//	{
//		EmailFeild.click();
//	}
//	
//	public String getEmailFeildText()
//	{
//		return EmailFeild.getText();
//	}
//	
//	public void GenerateCombinedEDF()
//	{
//		GenerateCombinedEDF.getText();
//	}
//	
//	public void VerifyAdminPanelElement() throws InterruptedException
//	{ 
//		
//		
//	//  Assert.assertTrue(SearchBox.isDisplayed(),"Search box not displayed"); 
//	  Assert.assertTrue(AddNewUserBtn.isDisplayed(),"Add user");
//	  Thread.sleep(2000);
//	  Assert.assertTrue(Tableheader.isDisplayed(),"Tab header"); 
//	  
//	  driver.findElement((By.xpath("//*[contains(@class, 'sidebar-toggle')]"))).click();
//	  Thread.sleep(2000);
//	  
//	  
//	  String LUD = driver.findElement(By.id("liveUserDashboardBtn")).getText();
//	  System.out.println("The Button Text is "+LUD);
//	  Assert.assertEquals(driver.findElement(By.id("liveUserDashboardBtn")).getText(),"Live User Dashboard");
//	  
//	  String CP = driver.findElement(By.xpath("//div[contains(text(),'Cloud Platform')]")).getText(); 
//	  System.out.println ("Text is "+CP); 
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Cloud Platform')]")).getText(),"Cloud Platform");
//	  
//	  String SrlNo = driver.findElement(By.xpath("//div[contains(text(),'S. No.')]")).getText();
//	  System.out.println("Text is "+SrlNo);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'S. No.')]")).getText(),"S. No.");
//	  
//	  String Name = driver.findElement(By.xpath("//div[contains(text(),'Name')]")).getText();
//	  System.out.println("Text is "+Name);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Name')]")).getText(),"Name");
//	  
//	  String Email = driver.findElement(By.xpath("//div[contains(text(),'Email')]")).getText();
//	  System.out.println("Text is "+Email);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Email')]")).getText(),"Email");
//	  
//	  String UL = driver.findElement(By.xpath("//div[contains(text(),'User Level')]")).getText();
//	  System.out.println("Text is "+UL);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'User Level')]")).getText(),"User Level");
//	  
//	  String Report = driver.findElement(By.xpath("//div[contains(text(),'Report')]")).getText();
//	  System.out.println("Text is "+Report);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Report')]")).getText(),"Report");
//	  
//	  String LiveECG = driver.findElement(By.xpath("//div[contains(text(),'Live ECG')]")).getText();
//	  System.out.println("Text is "+LiveECG);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Live ECG')]")).getText(),"Live ECG");
//	  
//	  String MD = driver.findElement(By.xpath("//div[contains(text(),'More Details')]")).getText();
//	  System.out.println("Text is "+MD);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'More Details')]")).getText(),"More Details");
//	  
//	 }	
//	
//	
//	public void Verify_FHP_AdminPanelElement() throws InterruptedException
//	{ 
//	 // Assert.assertTrue(SearchBox.isDisplayed(),"Search box not displayed"); 
//	  Assert.assertTrue(AddNewUserBtn.isDisplayed(),"Add user");
//	  Thread.sleep(2000);
//	 // Assert.assertTrue(LiveUserDashboardBtn.isDisplayed(),"Live user dash board button"); 
//	  
//	  Assert.assertTrue(Tableheader.isDisplayed(),"Tab header"); 
	  
//	  String LUD = driver.findElement(By.id("liveUserDashboardBtn")).getText();
//	  System.out.println("The Button Text is "+LUD);
//	  Assert.assertEquals(driver.findElement(By.id("liveUserDashboardBtn")).getText(),"Live User Dashboard");
//	  
//	 
//	  
//	  String SrlNo = driver.findElement(By.xpath("//div[contains(text(),'S. No.')]")).getText();
//	  System.out.println("Text is "+SrlNo);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'S. No.')]")).getText(),"S. No.");
//	  
//	  String Name = driver.findElement(By.xpath("//div[contains(text(),'Name')]")).getText();
//	  System.out.println("Text is "+Name);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Name')]")).getText(),"Name");
//	  
//	  String Email = driver.findElement(By.xpath("//div[contains(text(),'Email')]")).getText();
//	  System.out.println("Text is "+Email);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Email')]")).getText(),"Email");
//	  
//	  
//	  String LiveECG = driver.findElement(By.xpath("//div[contains(text(),'Live ECG')]")).getText();
//	  System.out.println("Text is  "+LiveECG);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Live ECG')]")).getText(),"Live ECG");
//	  
//	 }


	

	
}
