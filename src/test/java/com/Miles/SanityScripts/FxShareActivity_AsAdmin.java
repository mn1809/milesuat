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
import com.miles.PageLibRepo.LoginPageLib;
import com.miles.PageLibRepo.ShareActivityPageLib;
import com.miles.PageLibRepo.WorkoutDetailsPageLib;
import com.miles.Utilities.MilesUtilities;

public class FxShareActivity_AsAdmin extends MilesSettings {
	WebDriver driver = null;
	LoginPageLib login;
	HomePageLib homeObj;
	 AdminPageLib AdminPageObj;
	String ClassName = this.getClass().getSimpleName().toString();

	int HRValue;
	int BRValue;
	double HrStValue;
	int HRVValue;
	int BodyShock;
	int CadenceValue;
	String EneEnv;
	@Parameters({ "enivironment" })
	@BeforeMethod
	//private void Initialize(String env)
//	{
//		this.driver = DecideEnvironment(env);
//		login = new LoginPageLib(driver);
//		homeObj = login.login("quality+admin@fourthfrontier.com", MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
//	}
	 private void Initialize(String env)
    {
		 
		 try
		 {
			 this.driver = DecideEnvironment(env);
			 
			 login = new LoginPageLib(driver);
			
			 
			 EneEnv = env;
			 if(env.contains("prod")) 
			 {									//PROD//
				 homeObj = login.login("quality+adminProd@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
				 System.out.println("Logging in as Quality : Serverless Production user");
			 }
			 
			 else
			 {   // Regular Prod User		//STAGE//
				 homeObj = login.login("quality+admin@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
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
	private void SetEvidenceDir() {
		MilesUtilities.createDateBasedDirectory();

	}
	
	@Test(description = "Purpose of this test to verify the Activity Icon")
	public void VerfiyActivity_Icon_AsAdmin() throws InterruptedException
	{
		InitateAdminPageSearchUser();
		VerifyRunIcon();
		driver.findElement(By.className("w-title")).click();
		
		MilesUtilities.SwitchTab(2, driver);
		WorkoutDetailsPageLib wd = new WorkoutDetailsPageLib(driver);
		wd.WaitForWorkoutDetailsPageToLoad();
		workoutpageIcon();
	}
	
	@Test(description = "Purpose of this test to verify that Values in workout details page is same in share actvity page")
	public void VerifyParametersComparision_InShareActivity_AsAdmin() throws Exception {

		InitateAdminPageSearchUser();
		driver.findElement(By.className("w-title")).click();
		
		MilesUtilities.SwitchTab(2, driver);
		WorkoutDetailsPageLib wd = new WorkoutDetailsPageLib(driver);
		wd.WaitForWorkoutDetailsPageToLoad();

// Read Values in Workout Details Page

		String Duriation = wd.getDurationValue();
		String TrainingLoad = wd.getTrainingValue();

		ReadParameterValues(wd);
// Switch to home page and share the activity 
		OpenShareLinkURL();
// Initialize ShareLink Page
		ShareActivityPageLib sharelink = new ShareActivityPageLib(driver);
		sharelink.WaitForShareActivityPageToLoad();
		Thread.sleep(5000);
// Compare the values 
		Assert.assertEquals(Duriation, sharelink.getDurationValue());
		Assert.assertEquals(BRValue, Integer.parseInt(sharelink.getBRValue()));
		Assert.assertEquals(HRValue, Integer.parseInt(sharelink.getHeartRateValue()));
		Assert.assertEquals(TrainingLoad, sharelink.getTrainingValue());
		Assert.assertEquals(HrStValue, Double.parseDouble(sharelink.getStrainValue()));
		Assert.assertEquals(HRVValue, Integer.parseInt(sharelink.getHRVValue()));
		Assert.assertEquals(BodyShock, Integer.parseInt(sharelink.getBodyShock()));
		Assert.assertEquals(CadenceValue, Integer.parseInt(sharelink.getCadenceValue()));
	}

	@Test(description = "Purpose of this test to verify that Min Values in workout details page is same in share actvity page")
	public void VerifyParametersComparision_MinValues_InShareActivity_AsAdmin() throws Exception 
	{
		InitateAdminPageSearchUser();
		
		driver.findElement(By.className("w-title")).click();
		MilesUtilities.SwitchTab(2, driver);
		WorkoutDetailsPageLib wd = new WorkoutDetailsPageLib(driver);
		wd.WaitForWorkoutDetailsPageToLoad();
		wd.ClickOnMinTab();

		ReadParameterValues(wd);

		OpenShareLinkURL();
		ShareActivityPageLib sharelink = new ShareActivityPageLib(driver);
		sharelink.WaitForShareActivityPageToLoad();
		Thread.sleep(5000);
		sharelink.ClickOnMinTab();
		Thread.sleep(4000);
// Compare the values 

		Assert.assertEquals(BRValue, Integer.parseInt(sharelink.getBRValue()));
		Assert.assertEquals(HRValue, Integer.parseInt(sharelink.getHeartRateValue()));
		Assert.assertEquals(HrStValue, Double.parseDouble(sharelink.getStrainValue()));
		Assert.assertEquals(HRVValue, Integer.parseInt(sharelink.getHRVValue()));
		Assert.assertEquals(BodyShock, Integer.parseInt(sharelink.getBodyShock()));
		Assert.assertEquals(CadenceValue, Integer.parseInt(sharelink.getCadenceValue()));
	}

	@Test(description = "Purpose of this test to verify that Max Values in workout details page is same in share actvity page")
	public void VerifyParametersComparision_MaxValues_InShareActivity_AsAdmin() throws Exception 
	{
		
		InitateAdminPageSearchUser();
		driver.findElement(By.className("w-title")).click();
		MilesUtilities.SwitchTab(2, driver);
		WorkoutDetailsPageLib wd = new WorkoutDetailsPageLib(driver);
		wd.WaitForWorkoutDetailsPageToLoad();
		wd.ClickOnMaxTab();

		ReadParameterValues(wd);

		OpenShareLinkURL();
		ShareActivityPageLib sharelink = new ShareActivityPageLib(driver);
		sharelink.WaitForShareActivityPageToLoad();
		Thread.sleep(5000);
		sharelink.ClickOnMaxTab();
// Compare the values 

		Assert.assertEquals(BRValue, Integer.parseInt(sharelink.getBRValue()));
		Assert.assertEquals(HRValue, Integer.parseInt(sharelink.getHeartRateValue()));
		Assert.assertEquals(HrStValue, Double.parseDouble(sharelink.getStrainValue()));
		Assert.assertEquals(HRVValue, Integer.parseInt(sharelink.getHRVValue()));
		Assert.assertEquals(BodyShock, Integer.parseInt(sharelink.getBodyShock()));
		Assert.assertEquals(CadenceValue, Integer.parseInt(sharelink.getCadenceValue()));
	}

	@Test (description = "Purpose of this test to verify that Start time of the Graph is correct as per the time recorded of the activity")
	public void VerifyStartTimeOfGraph_InShareActivity_AsAdmin() throws Exception
	{
		
		InitateAdminPageSearchUser();
		Thread.sleep(3000);
		driver.findElement(By.className("w-title")).click();
		MilesUtilities.SwitchTab(2, driver);
		WorkoutDetailsPageLib wd = new WorkoutDetailsPageLib(driver);
		wd.WaitForWorkoutDetailsPageToLoad();

	// Read Timing Values in Workout Details Page 
		String StartTimeOfActivityOfGraph = getStartTimeOnGraph(); // Reads the Starting time on HR Graph X Axis
		String TimeOfActivityInTitle = wd.getTrainingTime(); // Reads the Time of Activity (Ex :  Fri, Nov 11th, 2022, 1:25 pm )
		String ActualTimeOfActivity = TimeOfActivityInTitle.substring(21); // Removes 1st 21 characters to get the actual start time of the activity (ex : 1:25 pm)

		System.out.println("Work Out Details Page");

		System.out.println("StartTimeOfActivityOfGraph : "+StartTimeOfActivityOfGraph);
		System.out.println("TimeOfActivityInTitle : "+TimeOfActivityInTitle);
		System.out.println("ActualTimeOfActivity : "+ActualTimeOfActivity);


		System.out.println("Share Page");

		OpenShareLinkURL();

		Thread.sleep(5000);
		ShareActivityPageLib sharelink  = new ShareActivityPageLib(driver);
		Thread.sleep(5000);
		String StartTimeOfActivityOfGraph_SharePage = getStartTimeOnGraph(); // Reads the Starting time on HR Graph X Axis in share link
		System.out.println("after reading x axis");
		String TimeOfActivityInTitle_SharePage = sharelink.getTrainingTime();// Reads the Time of Activity (Ex :  Fri, Nov 11th, 2022, 1:25 pm )
		String ActualTimeOfActivity_SharePage = TimeOfActivityInTitle_SharePage.substring(21);// Removes 1st 21 characters to get the actual start time of the activity (ex : 1:25 pm)

		System.out.println("StartTimeOfActivityOfGraph_SharePage : "+StartTimeOfActivityOfGraph_SharePage);
		System.out.println("TimeOfActivityInTitle_SharePage : "+TimeOfActivityInTitle_SharePage);
		System.out.println("ActualTimeOfActivity_SharePage : "+ActualTimeOfActivity_SharePage);

	// Verifies the Workout details page info and share activity info are same.
		Assert.assertEquals(StartTimeOfActivityOfGraph, StartTimeOfActivityOfGraph_SharePage,"Start time of Activity is same on Grpahs in Workout details page and Share link page");

		Assert.assertEquals(TimeOfActivityInTitle, TimeOfActivityInTitle_SharePage," time of Activity is same in Workout details page and Share link page");

		Assert.assertEquals(TimeOfActivityInTitle, TimeOfActivityInTitle_SharePage," time of Activity is same in Workout details page and Share link page");

	}
	
	

	/*
	 * 
	 * Healper Methods
	 */

	private void ReadParameterValues(WorkoutDetailsPageLib wd)
	{
		HRValue = Integer.parseInt(wd.getHeartRateValue());
		BRValue = Integer.parseInt(wd.getBRValue());
		HrStValue = Double.parseDouble(wd.getStrainValue());
		HRVValue = Integer.parseInt(wd.getHRVValue());
		BodyShock = Integer.parseInt(wd.getBodyShock());
		CadenceValue = Integer.parseInt(wd.getCadenceValue());
	}

	protected void OpenShareLinkURL() throws InterruptedException
	{
		MilesUtilities.SwitchTab(1, driver);
		
		homeObj.clickOnActivityButton();
		Thread.sleep(1000);
		driver.findElement((By.xpath("//*[contains(@class, 'dropdown-item w-time download-wtext')]"))).click();
		Thread.sleep(1000);
		
		driver.get(driver.findElements(By.id("note-title")).get(3).getText());
	}

	public void InitateAdminPageSearchUser() throws InterruptedException
	{	 
	//This method will be used for to initateAdminPage.
		driver.findElement((By.xpath("//*[contains(@class, 'fa fa-bars')]"))).click();
	
		List <WebElement> Options = driver.findElements(By.className("listbrdr"));
		Options.get(1).click();
		Thread.sleep(6000);
		
		AdminPageObj = new AdminPageLib(driver);
		//Logining as AdminSide.
		String Info;
		if (EneEnv.contains("prod"))
		{				//Prod
			 Info = "quality+automationProd@fourthfrontier.com";
		}
		else
		{			//Stage
			 Info = "quality+automation@fourthfrontier.com";
		}
		//AdminPageObj.EnterUserInfo("quality+automation@fourthfrontier.com");
		//AdminPageObj.EnterUserInfo("quality+automationProd@fourthfrontier.com");
		AdminPageObj.EnterUserInfo(Info);
		AdminPageObj.SelectDisplayedUser();
		
		MilesUtilities.SwitchTab(1, driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("current-user-dashboard")));
		
	}
	
	protected void VerifyRunIcon()
	{		
		List <WebElement> ActivityContainer = driver.findElements((By.xpath("//*[contains(@class, 'row w-list')]")));	
		WebElement IconContainer = ActivityContainer.get(0).findElement((By.xpath("//*[contains(@class, 'col-10 w-name-info col-lg-3')]")));
		
		try
		{
			WebElement IconRun = IconContainer.findElement(By.className("icon-ic_running"));
			Assert.assertTrue(IconRun.isDisplayed());
		}
		catch (Exception e)
		{
			Assert.fail("Run icon is not diaplyed for the activity ->" +e.getMessage());
		}
	}
	
	protected void workoutpageIcon()
	{
		WebElement IconRun = driver.findElement(By.className("icon-ic_running"));
		Assert.assertTrue(IconRun.isDisplayed());
	}
	
	protected String getStartTimeOnGraph()
	{
	
		return driver.findElements(By.xpath("//*[contains(@class, 'dygraph-axis-label dygraph-axis-label-x')]")).get(10).getText();
	}
	
	
}
