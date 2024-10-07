package com.Miles.SanityScripts;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
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
import com.miles.PageLibRepo.LoginPageLib;
import com.miles.PageLibRepo.WorkoutDetailsPageLib;
import com.miles.Utilities.MilesUtilities;

public class Fx_BeatCount_Workflow extends MilesSettings
{	
	 WebDriver driver = null ;
	 LoginPageLib loginObj ;
	 HomePageLib homeObj ;
	 //AdminPageLib adminLib;
	 AdminPageLib AdminPageObj;
	// WorkoutDetailsPageLib wd ;
	 WorkoutDetailsPageLib workoutObj ;
	 //AdminPageLib AdminPageObj;
	 String expectedInfoTxt = "User's information updated successfully." ;
	 String ClassName = this.getClass().getSimpleName().toString();
	 
	
	 String NormalBeatValue = "Normal: 99.54%";
	 
	 String TotalBeats = "Total Beats: 14120";
	 String NormalBeat = "Normal : 14056";
	 String AbnormalBeat = "Abnormal : 63";
	 String NoisyBeat = "Noisy : 1";
	 
	 String NormalBeatPercentage = "99.55%";
	 String AbnormalBeatPercentage = "0.45%";
	 String NoisyBeatPercentage = "0.01%";
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
		 {							//PROD//
			 homeObj = loginObj.login("quality+adminProd@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
			 System.out.println("Logging in as Quality : Serverless Production user");
		 }
		 
		 else
		 {   // Regular Prod User				//STAGE//
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
	
@Test (description = "Verify UserPage BeatCount")

	public void Verify_User_BeatCount() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+automationProd@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+automation@fourthfrontier.com";
	}
	//String Info = "quality+automation@fourthfrontier.com";
	AdminPageObj.EnterUserInfo(Info);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	VerifyBeatCountValue(NormalBeatValue);
	
}
@Test (description = "Purpose of this to check the Total BeatsCount ")
public void Verify_Total_BeatsCount() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	Thread.sleep(2000);
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+automationProd@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+automation@fourthfrontier.com";
	}
	AdminPageObj.EnterUserInfo(Info);
	Thread.sleep(2000);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(2000);
	driver.findElement(By.className("w-title")).click();
	Thread.sleep(2000);
	VerifyWorkoutBeatcountParameters();
	System.out.println("Actual Total Count Is:"+getTotalbeatscount());
	System.out.println("Expected Total Count Is:"+TotalBeats);
	Assert.assertEquals(getTotalbeatscount(),TotalBeats);	
}

@Test (description = "Purpose of this to check the Normal Beat Value")
public void Verify_BeatCount_NormalBeatValue() throws InterruptedException

{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	Thread.sleep(2000);
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+automationProd@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+automation@fourthfrontier.com";
	}
	AdminPageObj.EnterUserInfo(Info);
	Thread.sleep(2000);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(2000);
	driver.findElement(By.className("w-title")).click();
	VerifyWorkoutBeatcountParameters();
//System.out.println("Expected String "+NormalBeat);
	
	System.out.println("Actual Count Is:"+getBeatcount());
	System.out.println("Expected Count Is:"+NormalBeat);
	Thread.sleep(1000);
	Assert.assertEquals(getBeatcount(),NormalBeat);
}


@Test (description = "Purpose of this to check the Abnormal Beat Value")
public void Verify_BeatCount_AbnormalBeatValue() throws InterruptedException

{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	Thread.sleep(2000);
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+automationProd@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+automation@fourthfrontier.com";
	}
	AdminPageObj.EnterUserInfo(Info);
	Thread.sleep(2000);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(2000);
	driver.findElement(By.className("w-title")).click();
	Thread.sleep(2000);
	VerifyWorkoutBeatcountParameters();
	System.out.println("Actual Abnormal Count Is:"+getBeatAbnormal());
	System.out.println("Expected Abnormal count Is:"+AbnormalBeat);
	Assert.assertEquals(getBeatAbnormal(),AbnormalBeat);
}

@Test (description = "Purpose of this to check the Noisy Beat Value")
public void Verify_BeatCount_NoisyValue() throws InterruptedException

{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	Thread.sleep(2000);
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+automationProd@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+automation@fourthfrontier.com";
	}
	AdminPageObj.EnterUserInfo(Info);
	Thread.sleep(2000);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(2000);
	driver.findElement(By.className("w-title")).click();
	Thread.sleep(2000);
	VerifyWorkoutBeatcountParameters();
	System.out.println("Actual Noisy Count Is:"+getBeatNoisy());
	System.out.println("Expected Noisy count Is:"+NoisyBeat);
	Assert.assertEquals(getBeatNoisy(),NoisyBeat);
	
}

@Test (description = "Purpose of this to check the Beat Burdon Value")
public void Verify_BeatCount_BurdenValue() throws InterruptedException

{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	Thread.sleep(2000);
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+automationProd@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+automation@fourthfrontier.com";
	}
	AdminPageObj.EnterUserInfo(Info);
	Thread.sleep(2000);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(2000);
	driver.findElement(By.className("w-title")).click();
	Thread.sleep(2000);
	VerifyWorkoutBeatcountParameters();
	
//	List <WebElement> s = driver.findElements(By.className("row"));
//	for (int i = 0 ; i<s.size();i++)
//	{
//		System.out.println(i+" elemet text is"+s.get(i).getText());
//	}
//	
	getAbnormalBeatBurden();

}

@Test (description = "Purpose of this to check the Normal Beat Value Percentage")
public void Verify_Beat_NormalValue_Percentage() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	Thread.sleep(2000);
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+automationProd@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+automation@fourthfrontier.com";
	}
	AdminPageObj.EnterUserInfo(Info);
	Thread.sleep(2000);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(2000);
	driver.findElement(By.className("w-title")).click();
	Thread.sleep(2000);
	VerifyWorkoutBeatcountParameters();

	System.out.println("Actual Normalbeat percentage Is:"+getBeatNormalPercentage());
	System.out.println("Expected Normalbeat percentage Is:"+NormalBeatPercentage);
	Assert.assertEquals(getBeatNormalPercentage(),NormalBeatPercentage);
}

@Test (description = "Purpose of this to check the Abnormal Beat Value Percentage")
public void Verify_Beat_Abnormal_Percentage() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	Thread.sleep(2000);
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+automationProd@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+automation@fourthfrontier.com";
	}
	AdminPageObj.EnterUserInfo(Info);
	Thread.sleep(2000);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(2000);
	driver.findElement(By.className("w-title")).click();
	Thread.sleep(2000);
	VerifyWorkoutBeatcountParameters();

	System.out.println("Actual Abnormal percentage Is:"+getBeatAbnormalPercentage());
	System.out.println("Expected Abnormal percentage Is:"+AbnormalBeatPercentage);
	Assert.assertEquals(getBeatAbnormalPercentage(),AbnormalBeatPercentage);
}

@Test (description = "Purpose of this to check the Noisy Beat Value Percentage")
public void Verify_Beat_Noisy_Percentage() throws InterruptedException
{
	homeObj.VerifyOpenAdminPanel();
	InitateAdminPage();
	Thread.sleep(2000);
	String Info;
	if (EneEnv.contains("prod"))
	{				//Prod
		 Info = "quality+automationProd@fourthfrontier.com";
	}
	else
	{			//Stage
		 Info = "quality+automation@fourthfrontier.com";
	}
	AdminPageObj.EnterUserInfo(Info);
	Thread.sleep(2000);
	AdminPageObj.SelectDisplayedUser();
	UserdashBoardPage();
	Thread.sleep(2000);
	driver.findElement(By.className("w-title")).click();
	Thread.sleep(2000);
	VerifyWorkoutBeatcountParameters();

	System.out.println("Actual Noisy percentage Is:"+getBeatNoisyPercentage());
	System.out.println("Expected Noisy percentage Is:"+NoisyBeatPercentage);
	Assert.assertEquals(getBeatNoisyPercentage(),NoisyBeatPercentage);
}


	/*
	 * Helper Methods
	 */

	
public void UserdashBoardPage()
{
	MilesUtilities.SwitchTab(1, driver);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("current-user-dashboard")));
	//System.out.println("Home Dashboard User Name is "+getUserNameOnDashboard());
}

public void InitateAdminPage() throws InterruptedException
{	 
	List <WebElement> Options = driver.findElements(By.className("listbrdr"));
	Options.get(1).click();
	Thread.sleep(6000);
	AdminPageObj = new AdminPageLib(driver);
}

public void VerifyBeatCountValue (String NBV)
{
	Actions act = new Actions(driver);
	act.moveToElement(driver.findElement(By.id("pie-chart"))).perform();
	String NormalBeatCount = driver.findElement(By.id("chartjs-tooltip")).getText();
	System.out.println("NBC value in Pie-Chart Is = "+NormalBeatCount );
	
	if( NormalBeatCount.contains("Noise: 0%") || NormalBeatCount.contains("Normal: 99.54%"))
	{
		System.out.println("Values are Expected");
	}
else
{
Assert.fail("Pie Chart Value is neither Noise: 0.09% or Normal: 99.9% But Value Is "+NormalBeatCount);
}
	Assert.assertEquals(NormalBeatCount, NBV);
}

public void VerifyWorkoutBeatcountParameters()
{
	MilesUtilities.SwitchTab(2, driver);
	MilesUtilities.WaitForVisibilityOfElement(driver, "id", "wd-heart-rate-value");
	 workoutObj = new WorkoutDetailsPageLib(driver);

}

public String getTotalbeatscount()
{
	return driver.findElements(By.className("col-12")).get(3).getText();
}
public String normalbeatcount()
{
	return driver.findElement(By.className("beat-pie-chart")).getText();	
}

public String getBeatcount()
{
	return driver.findElements(By.className("col-10")).get(4).getText();
}

public String getBeatAbnormal()
{
	return driver.findElements(By.className("col-10")).get(5).getText();
}

public String getBeatNoisy()
{
	return driver.findElements(By.className("col-10")).get(6).getText();
}

public String getUserNameOnDashboard()
{
	
	return driver.findElement(By.className("current-user-dashboard")).getText();
}

public String getAbnormalBeatBurden()
{
	String text = driver.findElements(By.className("row")).get(42).getText();
	Pattern pattern = Pattern.compile("\\d+\\.\\d+");
    Matcher matcher = pattern.matcher(text);
    if (matcher.find()) 
    {
    	System.out.println("Abnormal Burden value is "+matcher.group());
    	return matcher.group();
    }
    else
    {
    	return null;
    }
}

public String getBeatNormalPercentage()
{
	return driver.findElements(By.className("col-1")).get(4).getText();
}

public String getBeatAbnormalPercentage()
{
	return driver.findElements(By.className("col-1")).get(5).getText();
}

public String getBeatNoisyPercentage()
{
	return driver.findElements(By.className("col-1")).get(6).getText();
}


}
	