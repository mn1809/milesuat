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
import com.miles.PageLibRepo.AdminPageLib;
import com.miles.PageLibRepo.HomePageLib;
import com.miles.PageLibRepo.LiveUserPageLib;
import com.miles.PageLibRepo.LoginPageLib;
import com.miles.PageLibRepo.WorkoutDetailsPageLib;
import com.miles.Utilities.MilesUtilities;
import com.miles.Utilities.MilesUtilities;

public class Miles_AdminPage_Workflow extends MilesSettings
{	
	 WebDriver driver = null ;
	 LoginPageLib loginObj ;
	 HomePageLib homeObj ;
	 //AdminPageLib adminLib;
	 AdminPageLib AdminPageObj;
	 WorkoutDetailsPageLib wd ;
	 LiveUserPageLib LiveUserObj;
	 
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
		 AdminPageObj = new AdminPageLib(driver);
		 
		 EneEnv = env;
		 if(env.contains("prod"))			//Prod//
		 {
			 //homeObj = loginObj.login("manoj.hr@mileseducation.com", "12341234"); // Use the decrypted version temporarily
			homeObj = loginObj.login("manoj.hr@mileseducation.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
			 //MTIzNDEyMzQ=
			 System.out.println("Logging in as Quality : Serverless Production user");
		 }
		 
		 else
		 {   // Regular Prod User		//Stage//
			// homeObj = loginObj.login("manoj.hr@mileseducation.com", "12341234"); // Use the decrypted version temporarily
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
	
@Test(description = "Verify Admin Login")
public void VerifyAdminLogin()

{
//	driver.findElement(By.id("login")).click();
//	driver.findElement(By.id("login")).sendKeys("manoj.hr@mileseducation.com");
//	Thread.sleep(2000);
//	driver.findElement(By.id("password")).click();
//	driver.findElement(By.id("password")).sendKeys("12341234");
//Thread.sleep(10);
//	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary btn-block')]")).click();
//	
System.out.println("Home Dashboard User Name is");

}

//@Test (description = "Verify Admin Panel Icon")
public void VerifyAdminPanel() throws Exception
{				
	homeObj.VerifyOpenAdminPanel();
}

//@Test (description = "Verify Admin Panel Options")
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
	
//@Test (description = "Verify Intiate Admin Panel")
public void VerifyIntiateAdminPage() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.ClickOnAddUserButton();
}

	
//@Test (description = "Verify Admin Panel Options")
public void VerifyAdminPanelElement() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.VerifyAdminPanelElement();
}	


//@Test (description = "Verify Admin Search User")
public void VerifyAdminSearch() throws InterruptedException
{
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{				//Stage
		 Info = "quality+24@fourthfrontier.com";
	}
	
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	Assert.assertEquals(AdminPageObj.getEmailFeildText(), Info);
}

//@Test (description = "Verify Search User Dashboard")
public void VerifyUserSearch() throws InterruptedException
{
	
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+24@fourthfrontier.com";
	}
	String ExpectedDashBoardUserName = "TwentyFour Hours";
	String ExpectedDashBoardUserNameProd = "TwentyFour";
	
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	
	if (EneEnv.contains("prod"))
	{
		Assert.assertTrue(getUserNameOnDashboard().contains(ExpectedDashBoardUserNameProd));
	}
	else
	{
		Assert.assertTrue(getUserNameOnDashboard().contains(ExpectedDashBoardUserName));	
	}
	
	
}

//@Test (description = "Verify General Info Dashboard")
public void VerifyGeneralinfo_Details() throws InterruptedException
{
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+24@fourthfrontier.com";
	}
	
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	Thread.sleep(2000);
	driver.findElement((By.xpath("//*[contains(@class, 'fa fa-ellipsis-v')]")) ).click();
	System.out.println("General Info Text Area Contains :"+ReadGeneralInfo());
	System.out.println("Expected General Info Is:"+GeneralInfoContains);
	Assert.assertTrue(ReadGeneralInfo().contains(GeneralInfoContains),"Asserting Genral Info to be true");
}

//@Test (description = "Verify Serial Number")
public void VerifyGeneralinfo_SerialNumber() throws InterruptedException
{
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+24@fourthfrontier.com";
	}
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	Thread.sleep(2000);
	driver.findElement((By.xpath("//*[contains(@class, 'fa fa-ellipsis-v')]")) ).click();
	System.out.println("Serial Info Text Area Contains :"+ReadSerialNumber());
	System.out.println("Expected Serial Info Is:"+SerialInfoContains);
	Assert.assertTrue(ReadSerialNumber().contains(SerialInfoContains),"Asserting Serial Info to be true");
	 
}

//@Test (description = "Verify Binfile Data")	
public void VerifyGeneralInfo_BinfileDate() throws InterruptedException
{
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+24@fourthfrontier.com";
	}
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	Thread.sleep(2000);
	driver.findElement((By.xpath("//*[contains(@class, 'fa fa-ellipsis-v')]")) ).click();
 	System.out.println("Actual BinFileData Info Is:"+ReadBinfileData());
	Assert.assertTrue(ReadBinfileData() != null,"Asserting BinFileData contains some value"); 
}




//@Test (description = "Verify Generate Combined EDF")
public void VerifyGenerateCombinedEDF() throws InterruptedException
{
	
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+24@fourthfrontier.com";
	}
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	AdminPageObj.SelectDisplayedUser();
	AdminPageObj.GenerateCombinedEDF();
	Thread.sleep(3000);
}

//@Test (description = "Verify Admin Pannel Live User Dashboard Navigate through admin pannel page")
public void Verify_Admin_LiveUser_PageElements() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	CloseSideAdminPannel();
	Thread.sleep(3000);
	UserBoardLiveUserDashboard();
	
	LiveUserObj = new LiveUserPageLib(driver);
	LiveUserObj.VerifyLiveUserScreenElements();
}


//@Test (description = "Verify LiveECGs Dashboard from Live Button Side")
public void VerifyLiveECG_LiveUserDashboard() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	CloseSideAdminPannel();
	Thread.sleep(3000);
	UserBoardLiveUserDashboard();
	LiveUserObj = new LiveUserPageLib(driver);
	LiveUserObj.clickOnGroupLiveECG();
	MilesUtilities.SwitchTab(2, driver);
	Assert.assertTrue(driver.findElement(By.className("form-search")).isDisplayed(),"Search Box is Diplayed");
}

//@Test(description = "Purpose of this test is to Verify Levels of users in our system")
public void Verify_UserLevelsDropDown() throws InterruptedException 
{		
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+24@fourthfrontier.com";
	}
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	Thread.sleep(2000);
	homeObj.VerifyUserLevelOptions();
}

//@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone1 in Workout page")
public void Verify_HeartRateZone1_AsAdmin() throws InterruptedException
{
	
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+24@fourthfrontier.com";
	}
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(3000);
	
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(2,driver);
	wd = new WorkoutDetailsPageLib(driver);
	wd.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone1 Duration Is:"+wd.getHRZoneDuration(1));
	System.out.println("Expected Zone1 Duration Is:"+Zone1_Duration);
	Assert.assertEquals(wd.getHRZoneDuration(1),Zone1_Duration);
}

//@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone2 in Workout page")
public void Verify_HeartRateZone2_AsAdmin() throws InterruptedException
{
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+24@fourthfrontier.com";
	}
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(3000);
	
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(2,driver);
	wd = new WorkoutDetailsPageLib(driver);
	wd.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();	
	System.out.println("Actual Zone2 Duration Is:"+wd.getHRZoneDuration(2));
	System.out.println("Expected Zone2 Duration Is:"+Zone2_Duration);
	Assert.assertEquals(wd.getHRZoneDuration(2),Zone2_Duration);	

}

//@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone3 in Workout page")
public void Verify_HeartRateZone3_AsAdmin() throws InterruptedException
{
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+24@fourthfrontier.com";
	}
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(3000);
	
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(2,driver);
	wd = new WorkoutDetailsPageLib(driver);
	wd.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone3 Duration Is:"+wd.getHRZoneDuration(3));
	System.out.println("Expected Zone3 Duration Is:"+Zone3_Duration);
	Assert.assertEquals(wd.getHRZoneDuration(3),Zone3_Duration);	
}

//@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone4 in Workout page")
public void Verify_HeartRateZone4_AsAdmin() throws InterruptedException
{
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+24@fourthfrontier.com";
	}
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(3000);
	
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(2,driver);
	wd = new WorkoutDetailsPageLib(driver);
	wd.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone4 Duration Is:"+wd.getHRZoneDuration(4));
	System.out.println("Expected Zone4 Duration Is:"+Zone4_Duration);
	Assert.assertEquals(wd.getHRZoneDuration(4),Zone4_Duration);	
}

//@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone5 in Workout page")
public void Verify_HeartRateZone5_AsAdmin() throws InterruptedException
{
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+24@fourthfrontier.com";
	}
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(3000);
	
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(2,driver);
	wd = new WorkoutDetailsPageLib(driver);
	wd.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone5 Duration Is:"+wd.getHRZoneDuration(5));
	System.out.println("Expected Zone5 Duration Is:"+Zone5_Duration);
	Assert.assertEquals(wd.getHRZoneDuration(5),Zone5_Duration);	
}

//@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone1 Percentage in Workout page")
public void Verify_HeartRateZonePercentage1_AsAdmin() throws InterruptedException
{
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+24@fourthfrontier.com";
	}
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(3000);
	
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(2,driver);
	wd = new WorkoutDetailsPageLib(driver);
	wd.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone1 Percentage Is:"+wd.getHRZonePercentage(1));
	System.out.println("Expected Zone1 Percentage Is:"+Zone1_Percentage);
	Assert.assertEquals(wd.getHRZonePercentage(1),Zone1_Percentage);	
}

//@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone2 Percentage in Workout page")
public void Verify_HeartRateZonePercentage2_AsAdmin() throws InterruptedException
{
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+24@fourthfrontier.com";
	}
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(3000);
	
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(2,driver);
	wd = new WorkoutDetailsPageLib(driver);
	wd.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone2 Percentage Is:"+wd.getHRZonePercentage(2));
	System.out.println("Expected Zone2 Percentage Is:"+Zone2_Percentage);
	Assert.assertEquals(wd.getHRZonePercentage(2),Zone2_Percentage);	
}

//@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone3 Percentage in Workout page")
public void Verify_HeartRateZonePercentage3_AsAdmin() throws InterruptedException
{
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+24@fourthfrontier.com";
	}
	
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(3000);
	
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(2,driver);
	wd = new WorkoutDetailsPageLib(driver);
	wd.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone3 Percentage Is:"+wd.getHRZonePercentage(3));
	System.out.println("Expected Zone3 Percentage Is:"+Zone3_Percentage);
	Assert.assertEquals(wd.getHRZonePercentage(3),Zone3_Percentage);	
}

//@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone4 Percentage in Workout page")
public void Verify_HeartRateZonePercentage4_AsAdmin() throws InterruptedException
{
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+24@fourthfrontier.com";
	}
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(3000);
	
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(2,driver);
	wd = new WorkoutDetailsPageLib(driver);
	wd.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone4 Percentage Is:"+wd.getHRZonePercentage(4));
	System.out.println("Expected Zone4 Percentage Is:"+Zone4_Percentage);
	Assert.assertEquals(wd.getHRZonePercentage(4),Zone4_Percentage);	
}

//@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone5 Percentage in Workout page")
public void Verify_HeartRateZonePercentage5_AsAdmin() throws InterruptedException
{
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+24@fourthfrontier.com";
	}
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(3000);
	
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(2,driver);
	wd = new WorkoutDetailsPageLib(driver);
	wd.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone5 Percentage Is:"+wd.getHRZonePercentage(5));
	System.out.println("Expected Zone5 Percentage Is:"+Zone5_Percentage);
	Assert.assertEquals(wd.getHRZonePercentage(5),Zone5_Percentage);	
}

//@Test (description = "Purpose of this test is to Verify  Health Tags displayed")
public void Verify_HealthTag() throws Exception
{		
	
	Assert.assertTrue(driver.findElement(By.className("btn-health-entry")).isDisplayed());
}

//@Test (description = "Purpose of this to Verify Health Tags Options")
public void Verify_HealthTagEntry() throws InterruptedException
{
	 List<String> HealthTagList = Arrays.asList("Vitals", "Symptom", "Sleep","Medication","Beverage","Food","Supplement","Body Status","Mental Status","Wellness Activity","Environment");
	 homeObj.ClickOnAddHealtEntry();
	 Thread.sleep(1500);
	 homeObj.VerifyHealthTags(HealthTagList);
	 Thread.sleep(1000);
}

//@Test (description = "Purpose of this test is to Verfiy AddRecommendation AsAdmin")
public void Verify_AddRecommendation_AsAdmin() throws InterruptedException

{
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+automation@fourthfrontier.com";
	}
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(3000);
	AddRecommendation();
	String expectedText= "New recommendation added!";
	VerifyAddRecommendationInfoPopUp(expectedText);
	
}

//@Test (description = "Purpose of this test is to Verfiy View Recommendation AsAdmin")
public void Verify_ViewRecommendation_AsAdmin() throws InterruptedException

{
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+automation@fourthfrontier.com";
	}
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(3000);
	ViewRecommendation();
	//System.out.println("Activity Progress Notes Text Area Contains :"+ driver.findElements((By.className("w-title"))).get(0));
	System.out.println("Expected Added Recommendation is:"+Added_Recommendation);
	Assert.assertEquals(Recommendation(),Added_Recommendation);	
}

//@Test (priority = 2,description = "Purpose of this test is to Verfiy Edit Recommendation AsAdmin")
public void Verify_EditRecommendation_AsAdmin() throws InterruptedException

{
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+24Prod@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+automation@fourthfrontier.com";
	}
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.EnterUserInfo(Info);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(3000);
	ViewRecommendation();
	VerifyEditRecommendation();
	String expectedText= "Recommendation updated successfully!";
	VerifyAddRecommendationInfoPopUp(expectedText);
	
}


	/*
	 * Helper Methods
	 */
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

protected void WaitForWorkoutDetailsPagetoLoad()
{
	MilesUtilities.SwitchTab(3, driver);
	MilesUtilities.WaitForVisibilityOfElement(driver, "id", "wd-heart-rate-value");
	wd = new WorkoutDetailsPageLib(driver);
}

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
	AdminPageObj = new AdminPageLib(driver);
	
	
}

public String getUserNameOnDashboard()
{
	return driver.findElement(By.className("current-user-dashboard")).getText();
}
//Check12345//

}
	