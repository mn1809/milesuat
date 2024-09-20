package com.Miles.SanityScripts;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class Fx_FHPDoctor_WorkFlow extends MilesSettings
{	
	 WebDriver driver = null ;
	 LoginPageLib loginObj ;
	 HomePageLib homeObj ;
	 //AdminPageLib adminLib;
	 AdminPageLib AdminPageObj;
	 WorkoutDetailsPageLib wd ;
	 LiveUserPageLib LiveUserObj;
	 String expectedInfoTxt = "User's information updated successfully." ;
	 String ClassName = this.getClass().getSimpleName().toString();
	 String ActivityProgreesNotes = "Adding activity progress notes as part of Automation testing";
	
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
	 
	 String Added_Recommendation = "Adding Recommendation Through Automation Script";
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
			 homeObj = loginObj.login("quality+FHPProd@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
			 System.out.println("Logging in as Quality : Serverless Production user");
		 }
		 
		 else
		 {  							//STAGE//
			 homeObj = loginObj.login("quality+FHP@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
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
public void Verify_FHP_AdminPanel() throws Exception
{				
	homeObj.VerifyOpenAdminPanel();
}

@Test (description = "Verify Admin Panel Options")
public void Verify_FHPAdminPanel_Options()
{
	homeObj.VerifyOpenAdminPanel();
	List <WebElement> Options = driver.findElements(By.className("listbrdr"));
	Assert.assertTrue(driver.findElement(By.id("menu")).isDisplayed());
	Assert.assertEquals(Options.get(0).getText(), "My Dashboard");
	Assert.assertEquals(Options.get(1).getText(), "Admin Panel");
	Assert.assertEquals(Options.get(3).getText(), "Approve Reports");
}

@Test (description = "Verify Admin Panel Options")
public void Verify_FHP_AdminPanel_Element() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	AdminPageObj.Verify_FHP_AdminPanelElement();
}	

@Test (description = "Verify Admin Search User")
public void Verify_FHP_AdminSearch() throws InterruptedException
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
	Assert.assertEquals(AdminPageObj.getEmailFeildText(), Info);
}

@Test (description = "Verify Search User Dashboard")
public void Verify_FHP_UserSearch() throws InterruptedException
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
	AdminPageObj.Enter_FHP_UserInfo(Info);
	Thread.sleep(2000);	
	HitSearchedName();	
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


@Test (description = "Verify Activity Progress Notes")
public void Verify_FHPCanAdd_ActivityProgressNote() throws InterruptedException
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
	HitSearchedName();
	Thread.sleep(2000);
	UserdashBoardPage();
	
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(2, driver);
	Thread.sleep(2000);
	
	ScrollToActivityProgressNote();
	Thread.sleep(2000);
	driver.findElement((By.xpath("//*[contains(@class, 'fa fa-plus')]"))).click();
	Thread.sleep(1000);
	
	AddActivityProgressNotes();
	
}

@Test (priority = 2, description = "Verify Edited progress Activity")
public void Verify_Editted_ActivityProgressNote() throws InterruptedException
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
	HitSearchedName();
	Thread.sleep(2000);
	UserdashBoardPage();
	
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(2, driver);
	
	Actions act = new Actions(driver);
	act.moveToElement(driver.findElement(By.className("activity-notes")));
	
	System.out.println("Activity Progress Notes Text Area Contains :"+ReadActivityProgressNotes());
	System.out.println("Expected Note Is:"+ActivityProgreesNotes);
	Thread.sleep(4000);
	Assert.assertTrue(ReadActivityProgressNotes().contains(ActivityProgreesNotes),"Asserting notes to be true");
}

@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone1 in Workout page")
public void Verify_HeartRateZone1_AsFHP() throws InterruptedException
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
	HitSearchedName();
	//AdminPageObj.SelectDisplayedUser();
	Thread.sleep(2000);
	UserdashBoardPage();

	
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(2,driver);
	wd = new WorkoutDetailsPageLib(driver);
	wd.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone1 Duration Is:"+wd.getHRZoneDuration(1));
	System.out.println("Expected Zone1 Duration Is:"+Zone1_Duration);
	Assert.assertEquals(wd.getHRZoneDuration(1),Zone1_Duration);
	
}

@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone2 in Workout page")
public void Verify_HeartRateZone2_AsFHP() throws InterruptedException
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
	HitSearchedName();
	//AdminPageObj.SelectDisplayedUser();
	Thread.sleep(2000);
	UserdashBoardPage();

	
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(2,driver);
	wd = new WorkoutDetailsPageLib(driver);
	wd.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone2 Duration Is:"+wd.getHRZoneDuration(2));
	System.out.println("Expected Zone2 Duration Is:"+Zone2_Duration);
	Assert.assertEquals(wd.getHRZoneDuration(2),Zone2_Duration);
	
}

@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone3 in Workout page")
public void Verify_HeartRateZone3_AsFHP() throws InterruptedException
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
	HitSearchedName();
	//AdminPageObj.SelectDisplayedUser();
	Thread.sleep(2000);
	UserdashBoardPage();

	
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(2,driver);
	wd = new WorkoutDetailsPageLib(driver);
	wd.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone3 Duration Is:"+wd.getHRZoneDuration(3));
	System.out.println("Expected Zone3 Duration Is:"+Zone3_Duration);
	Assert.assertEquals(wd.getHRZoneDuration(3),Zone3_Duration);
	
}

@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone4 in Workout page")
public void Verify_HeartRateZone4_AsFHP() throws InterruptedException
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
	HitSearchedName();
	//AdminPageObj.SelectDisplayedUser();
	Thread.sleep(2000);
	UserdashBoardPage();

	
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(2,driver);
	wd = new WorkoutDetailsPageLib(driver);
	wd.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone4 Duration Is:"+wd.getHRZoneDuration(4));
	System.out.println("Expected Zone4 Duration Is:"+Zone4_Duration);
	Assert.assertEquals(wd.getHRZoneDuration(4),Zone4_Duration);
	
}

@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone5 in Workout page")
public void Verify_HeartRateZone5_AsFHP() throws InterruptedException
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
	HitSearchedName();
	//AdminPageObj.SelectDisplayedUser();
	Thread.sleep(2000);
	UserdashBoardPage();

	
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(2,driver);
	wd = new WorkoutDetailsPageLib(driver);
	wd.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone5 Duration Is:"+wd.getHRZoneDuration(5));
	System.out.println("Expected Zone5 Duration Is:"+Zone5_Duration);
	Assert.assertEquals(wd.getHRZoneDuration(5),Zone5_Duration);
	
}

@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone1 Percentage in Workout page")
public void Verify_HeartRateZonePercentage1_AsFHP() throws InterruptedException
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
	HitSearchedName();
	//AdminPageObj.SelectDisplayedUser();
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

@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone2 Percentage in Workout page")
public void Verify_HeartRateZonePercentage2_AsFHP() throws InterruptedException
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
	HitSearchedName();
	//AdminPageObj.SelectDisplayedUser();
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

@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone3 Percentage in Workout page")
public void Verify_HeartRateZonePercentage3_AsFHP() throws InterruptedException
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
	HitSearchedName();
	//AdminPageObj.SelectDisplayedUser();
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

@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone4 Percentage in Workout page")
public void Verify_HeartRateZonePercentage4_AsFHP() throws InterruptedException
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
	HitSearchedName();
	//AdminPageObj.SelectDisplayedUser();
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

@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone5 Percentage in Workout page")
public void Verify_HeartRateZonePercentage5_AsFHP() throws InterruptedException
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
	HitSearchedName();
	//AdminPageObj.SelectDisplayedUser();
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

@Test (description = "Verify Generate Combined EDF")
public void Verify_FHP_GenerateCombinedEDF() throws InterruptedException
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
	HitSearchedName();
	Thread.sleep(3000);
	UserdashBoardPage();
	CombinedEDF();
}


@Test (description = "Verify Intiate LiveUserDashboard")
public void Verify_FHP_Intiate_LiveUserDashboardPage() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateLiveDashboard();
	CloseSideAdminPannel();
	LiveUserObj.VerifyLiveUserScreenElements();
	
}

@Test (description = "Verify LiveECGs Dashboard")
public void Verify_FHP_LiveECGs_Window() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateLiveDashboard();
	CloseSideAdminPannel();
	LiveUserObj.clickOnGroupLiveECG();
	LiveECG_dashBoardPage();
}

@Test (description = "Verify HideMap Dashboard")
public void Verify_FHP_HideMap_window() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateLiveDashboard();
	CloseSideAdminPannel();
	LiveUserObj.clickOnHideMap();
}

@Test (description = "Purpose of this test is to Verfiy AddRecommendation As FHPAdmin")
public void Verify_AddRecommendation_AsFHPAdmin() throws InterruptedException

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
	HitSearchedName();
	Thread.sleep(3000);
	UserdashBoardPage();
	Thread.sleep(3000);
	AddRecommendation();
	String expectedText= "New recommendation added!";
	VerifyAddRecommendationInfoPopUp(expectedText);
	
}

@Test (description = "Purpose of this test is to Verfiy View Recommendation As FHPAdmin")
public void Verify_ViewRecommendation_AsFHPAdmin() throws InterruptedException

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
	HitSearchedName();
	Thread.sleep(3000);
	UserdashBoardPage();
	Thread.sleep(3000);
	ViewRecommendation();
	//System.out.println("Activity Progress Notes Text Area Contains :"+ driver.findElements((By.className("w-title"))).get(0));
	System.out.println("Expected Added Recommendation is:"+Added_Recommendation);
	Assert.assertEquals(Recommendation(),Added_Recommendation);	
}

@Test (priority = 3,description = "Purpose of this test is to Verfiy Edit Recommendation As FHPAdmin")
public void Verify_EditRecommendation_AsFHPAdmin() throws InterruptedException

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
	HitSearchedName();
	Thread.sleep(3000);
	UserdashBoardPage();
	Thread.sleep(3000);

	ViewRecommendation();
	VerifyEditRecommendation();
	String expectedText= "Recommendation updated successfully!";
	VerifyAddRecommendationInfoPopUp(expectedText);
}

@Test (description = "Purpose of this test is to Search UserLevel DropDown as FHP Doctor")
public void VerifyFHP_CanNotSee_UserLevelDropDown() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	MilesUtilities.VerifyInvisiblity("User Level",driver,"For FHP User Element User Level");
}

@Test (description = "Purpose of this test is to Search Cloud Platform as FHP Doctor")
public void VerifyFHP_CanNotSee_CloudPlatformOfUser() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	MilesUtilities.VerifyInvisiblity("Cloud Platform",driver,"For FHP User Element Cloud Platform");
}

@Test (description = "Purpose of this test is to Search More Details as FHP Doctor")
public void VerifyFHP_CanNotSee_MoreDetailsOfUser() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	MilesUtilities.VerifyInvisiblity("More Details",driver,"For FHP User Element More Details");
}

@Test (description = "Purpose of this test is to Search Reports as FHP Doctor")
public void VerifyFHP_CanNotEnableReports() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	MilesUtilities.VerifyInvisiblity("Report",driver,"For FHP User Element Reports");
}

	/*
	 * Helper Methods
	 */


public void VerifyEditRecommendation()
{
	driver.findElement(By.xpath("//img[@src='/images/Dropdown_change.png?1954b4fed9b0b4f0befe41fc6c9f36fb']")).click();
	driver.findElement((By.xpath("//*[contains(@class, 'dropdown-item w-time download-wtext')]"))).click();
	driver.findElements(By.id("note-title")).get(1).clear();
	driver.findElements(By.id("note-title")).get(1).sendKeys("Edited Adding Recommendation Through Automation Script");
	driver.findElements(By.xpath("//*[contains(@class, 'btn save-button')]")).get(5).click();
}

public String Recommendation()
{
	return  driver.findElements((By.className("w-title"))).get(0).getText();
}
public void VerifyAddRecommendationInfoPopUp(String expectedInfoTxt)
{
	Assert.assertTrue(driver.findElement(By.className("mini-toastr-notification__message")).getText().contains(expectedInfoTxt));
}

public void ViewRecommendation() throws InterruptedException
{
	driver.findElement(By.id("recommendation-view-btn")).click();
	Thread.sleep(3000);

}
public void AddRecommendation() throws InterruptedException
{
	 driver.findElement(By.id("recommendation-add-btn")).click();
	 driver.findElements(By.id("note-title")).get(2).click();
	 driver.findElements(By.id("note-title")).get(2).sendKeys("Adding Recommendation Through Automation Script");
	 Thread.sleep(3000);
	 driver.findElement(By.id("add-recommendation-popup-submit")).click();
}



public void ScrollToActivityProgressNote()
{
	Actions act = new Actions(driver);
	act.moveToElement(driver.findElement((By.xpath("//*[contains(@class, 'fa fa-plus')]")))).perform();
	//driver.findElement(By.className("fa fa-plus")).click();
	
}
public void AddActivityProgressNotes() throws InterruptedException
{
	WebElement Editnote = driver.findElement(By.xpath("//*[contains(@class, 'ql-editor ql-blank')]"));
	Editnote.click();
	int counter = 0 ;
	Thread.sleep(2000);
	Editnote.sendKeys(ActivityProgreesNotes);
	Thread.sleep(3000);
	//MilesUtilities.ScrollTillElement(driver.findElement(By.className("button-wrapper")),driver);
	while(!driver.findElement(By.className("button-wrapper")).isDisplayed())
	{
		counter++;
		try
		{
		  MilesUtilities.ScrollTillElement(driver.findElement(By.className("button-wrapper")),driver);	
		  System.out.println("isDisplayed : Save button ?" +driver.findElement(By.className("button-wrapper")).isDisplayed());
		}
		catch (Exception e)
		{
			System.out.println("Attaempt :"+counter+" issues is "+e.getMessage());
		}
		
		Thread.sleep(1000);
		if (counter == 10)
		{
			System.out.println("Tried 10 attempts to scroll to save button but failed ");
			break;
		}
	}
	
	driver.findElement(By.xpath("//*[contains(@class,'btn save-button')]")).click();
}


public String ReadActivityProgressNotes() throws InterruptedException
{
	Thread.sleep(10000);
	return driver.findElement(By.className("activity-notes")).getText();
}

public void CloseSideAdminPannel()
{
	driver.findElement((By.xpath("//*[contains(@class, 'sidebar-toggle')]")));  
}

public void LiveECG_dashBoardPage()
{
	MilesUtilities.SwitchTab(1, driver);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
}

public void HitSearchedName() throws InterruptedException
{		Thread.sleep(2000);	
	driver.findElements(By.className("col-10")).get(0).click();
}

public String CombinedEDF()
{
	return driver.findElement(By.className("btn-download-log")).getText();
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

public void InitateLiveDashboard() throws InterruptedException
{	 
	List <WebElement> Options = driver.findElements(By.className("listbrdr"));
	Options.get(2).click();
	Thread.sleep(6000);
	//AdminPageObj = new AdminPageLib(driver);
	LiveUserObj = new LiveUserPageLib(driver);
}

public String getUserNameOnDashboard()
{
	return driver.findElement(By.className("current-user-dashboard")).getText();
}

public void ScrollToHeartRateZones()
{
	Actions act = new Actions(driver);
	act.moveToElement(driver.findElement((By.xpath("//*[contains(@class, 'hr-zone-distribution')]")))).perform();
	//driver.findElement(By.className("fa fa-plus")).click();
	
}


}
	