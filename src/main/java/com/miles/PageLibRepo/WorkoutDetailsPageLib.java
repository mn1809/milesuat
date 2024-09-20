package com.miles.PageLibRepo;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.miles.PageObjectRepo.WorkoutDetailsPageObj;
import com.miles.Utilities.MilesUtilities;

public class WorkoutDetailsPageLib extends WorkoutDetailsPageObj
{

	List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
	
	//private String insightText = null;
	
	////div[@class='insights-notes']
	//	Constructor
	public WorkoutDetailsPageLib(WebDriver driver) 
	{
		super(driver);
	}
	
	/*
	 * Method to Scroll till Actvity Notes Section 
	 * Click on Add Note + Icon
	 */
	public void clickAddNotes() throws InterruptedException
	{
		ScrollTillElement(ECGBlock2);
		Thread.sleep(1000);
		AddNotes.click();
	}
	
	/*
	 * Method to Enter Notes in Add Note section 
	 * 
	 */
	public void EnterNote(String Note) throws InterruptedException
	{
		ScrollTillElement(ECGBlock2);
		Thread.sleep(2000);
		AddNotes.click();
		driver.findElement(By.id("note-title")).click();
		driver.findElement(By.id("note-title")).sendKeys(Note);
		driver.findElements((By.xpath("//*[contains(@class, 'btn save-button')]"))).get(0).click();
		Thread.sleep(3000);
	}
	
	/*
	 * Method to click on Save button in Add Notes section 
	 * 
	 */
	public void clickSaveButton()
	{
		System.out.println("Button1 :" + SaveButton.get(0).getText());
		System.out.println("Button2 :" + SaveButton.get(1).getText());
		//SaveButton.get(1).click();
	}
	
	public void VerifyECGGraphIsDisplayed() throws InterruptedException
	{
		//Thread.sleep(7000);
		//get window handlers as list
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		//switch to new tab
		driver.switchTo().window(browserTabs .get(1));
		
		Assert.assertTrue(ECGBlock1.isDisplayed(),"ECG Block 1 is displayed");
		Assert.assertTrue(ECGBlock2.isDisplayed(),"ECG BLock 2 is diaplayed");
	}

	
	public void ClickOnInfoButtons() throws InterruptedException
	{
		Thread.sleep(5000);
		System.out.println("There are---------------------------------------> "+ECGInfoButtons.size());
		ECGInfoButtons.get(1).click();
		
	}
	
	
	
	public void VerifyECGGraphIsDisplayed_Admin() throws InterruptedException
	{
		Thread.sleep(7000);
		//get window handlers as list
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		//switch to new tab
		driver.switchTo().window(browserTabs .get(2));
		Assert.assertTrue(ECGBlock1.isDisplayed(),"ECG Block 1 is displayed");
		Assert.assertTrue(ECGBlock2.isDisplayed(),"ECG BLock 2 is diaplayed");
		driver.close();
		driver.switchTo().window(browserTabs.get(0));
		
	}
	
	public void ClickOnHeartRateGraph() throws InterruptedException
	{
		
		Thread.sleep(3000);
		Thread.sleep(7000);
		//get window handlers as list
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		//switch to new tab
		driver.switchTo().window(browserTabs .get(2));
		ScrollTillElement(ECGBlock2);
		HeartRateGraph.click();
	}
	
	public void SelectAnnotation(String annotation)
	{
		switch(annotation)
		{
		case "Normal" :
			AnnotationWindow.findElement(By.id("radCreateMode")).click();
			break;
			
		case "AFib" :
			AnnotationWindow.findElement(By.id("radCreateMode1")).click();
			break;
		
		case "NSVT" :
		AnnotationWindow.findElement(By.id("radCreateMode4")).click();
		break;
			
		default:
			System.out.println("Invalid Annotation");
		
		
		}
	}
	

	
	public void VerifyHeartRateBlockIsDisplayed() throws InterruptedException
	{
		Thread.sleep(7000);
		//get window handlers as list
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		//switch to new tab
		driver.switchTo().window(browserTabs .get(1));
		Assert.assertTrue(HeartRateBlock.isDisplayed(),"Heart Rate Block is displayed");
		
		driver.close();
		driver.switchTo().window(browserTabs.get(0));
	}
	

	public void VerifyBreathingRateIsDisplayed() throws InterruptedException
	{
		Thread.sleep(7000);
		//get window handlers as list
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		//switch to new tab
		driver.switchTo().window(browserTabs .get(1));
		Assert.assertTrue(HeartRateBlock.isDisplayed(),"Heart Rate Block is displayed");
		
		driver.close();
		driver.switchTo().window(browserTabs.get(0));
	}
	
	public void VerifyBodyShockBlockIsDisplayed() throws InterruptedException
	{
		Thread.sleep(7000);
		ScrollTillElement(HRVBlock);
		//get window handlers as list
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		//switch to new tab
		driver.switchTo().window(browserTabs .get(1));
		Assert.assertTrue(BodyShock.isDisplayed(),"Body Shock section is displayed");
		
		driver.close();
		driver.switchTo().window(browserTabs.get(0));
	}
	
	public void VerifyStepCadenceBlockIsDisplayed() throws InterruptedException
	{
		Thread.sleep(7000);
		ScrollTillElement(HRVBlock);
		//get window handlers as list
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		//switch to new tab
		driver.switchTo().window(browserTabs .get(1));
		Assert.assertTrue(StepCadence.isDisplayed(),"Step Cadence section is displayed");
		
		driver.close();
		driver.switchTo().window(browserTabs.get(0));
	}
	
	
	
	public void VerifyCardiacStrainBlockIsDisplayed() throws InterruptedException
	{
		Thread.sleep(1000);
		ScrollTillElement(CardiacStrain);
		//get window handlers as list
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		//switch to new tab
		driver.switchTo().window(browserTabs .get(1));
		Assert.assertTrue(CardiacStrain.isDisplayed(),"Cardiac block is displayed");
		
		driver.close();
		driver.switchTo().window(browserTabs.get(0));
	}
	
	public void VerifyAddActivityNote(String ActivityNotes) throws InterruptedException
	{
		AddNotes.click();
		SaveNoteButton.click();
		driver.findElement(By.id("note-title")).sendKeys(ActivityNotes);
		
		Thread.sleep(3000);
	}
	
	
	
	public void VerifyHRVBlockIsDisplayed() throws InterruptedException
	{
		Thread.sleep(7000);
		ScrollTillElement(HRVBlock);
		//get window handlers as list
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		//switch to new tab
		driver.switchTo().window(browserTabs .get(1));
		Assert.assertTrue(HRVBlock.isDisplayed(),"HRV Block is displayed");
		
		driver.close();
		driver.switchTo().window(browserTabs.get(0));
	}
	
	public void WaitForWorkoutDetailsPageToLoad()
	{
		// MilesUtilities.timeoutInSeconds = Duration.ofSeconds(40);
		(new WebDriverWait(driver, MilesUtilities.timeoutInSeconds)).until(new ExpectedCondition<Boolean>() 
		{
            public Boolean apply(WebDriver d) {
                return driver.findElement(By.className("training-title")).getText().length() != 0;
            }
        });
		//MilesUtilities.WaitForVisibilityOfElement(driver, "className", "training-title");
		System.out.println("Title of Activity is "+driver.findElement(By.className("training-title")).getText());
	}
	
	public void ClickOnDisclaimerLink()
	{
		DisclaimerLink.click();
	}
	
	public void EnableCaliper()
	{
		CaliperTool.click();
	}
	public int GetTrainingLoadValue()
	{
		MilesUtilities.SwitchTab(1,driver);
		List<WebElement> allChildElements = TrainingLoadCard.findElements(By.xpath("*"));
		// Extracting just number from the string that contains training load and parsing it to int for further assertions
		return Integer.parseInt(allChildElements.get(1).getText().replaceAll("[^0-9]", ""));
	}
	/*
	 * Downloads CSV file
	 */
	public void DownloadCSVFile() throws InterruptedException
	{
		Duration timeoutInSeconds = Duration.ofSeconds(40); 
	WebElement	CSVFileLink = driver.findElement(By.id("wd-download-csv-file"));
		new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(CSVFileLink));
		CSVFileLink.click();
	}
	
	public boolean VerifyAddNotePopUpDisplays() throws InterruptedException
	{
		Thread.sleep(4000);
		ScrollTillElement(AddNotePopUp);
		Thread.sleep(4000);
		return AddNotePopUp.isDisplayed();
	}
	

	
	public void SwitchTab()
	{
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		//switch to new tab
		driver.switchTo().window(browserTabs .get(1));
		//Thread.sleep(7000);
	}
	
	private void CloseTab(WebDriver driver,List<String> tabs)
	{
		driver.switchTo().window(tabs.get(1)).close();;
	}
	
	
	public String  GetDuriation()
	{
		MilesUtilities.SwitchTab(1,driver);
		//Thread.sleep(7000);
		List<WebElement> DuriantionCard = duriation.findElements(By.xpath("./child::*"));
		String durationVal =  DuriantionCard.get(1).getText();
		
		CloseTab(driver,browserTabs);
		return durationVal;
	}
	
	
	public String GetWorkOutDetails11()
	{
		MilesUtilities.SwitchTab(1,driver);
		List<WebElement> DuriantionCard = duriation.findElements(By.xpath("./child::*"));
		String durationVal =  DuriantionCard.get(1).getText();
		 
		
		return durationVal;
		
	}
	
	/*
	 * 
	 * this is RnD method.. 
	 */
	
	
	public void ScrollTillElement(WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		
		
	}
	
	public void AssertInsightInfo() throws InterruptedException
	{
		
		ScrollTillElement(InsightNotes);
		Thread.sleep(2000);
		String MarkerInfo = "Find the vertical black markers on your workout graph to better understand your body's response to your activity at that moment.";
		Assert.assertTrue(InsightNotes.getText().contains(GetDateAsPerInsightTitle()), "Insights are displayed for today's workout");
		Assert.assertTrue(InsightNotes.getText().contains(MarkerInfo),"Marking info is available");
	}
	
	
	public String GetDateAsPerInsightTitle()
	{
		String dayOfWeek = new SimpleDateFormat("EEEE").format(new Date());
		String CurrentDate= new SimpleDateFormat("dd").format(new Date());
		String CurrentMonth= new SimpleDateFormat("MMMM").format(new Date());
		String CurrentYear= new SimpleDateFormat("YYYY").format(new Date());
		
		return "Summary for workout on "+dayOfWeek+","+" "+CurrentDate+" "+CurrentMonth+" "+CurrentYear+" at";
	
	}
	
	public void AssertInsightNoiseInfo() throws InterruptedException
	{
		
		ScrollTillElement(InsightNotes);
		Thread.sleep(2000);
		String MarkerInfo = "Unfortunately, we cannot analyse your data due to poor signal quality as this may affect the accuracy and validity of our insights. We recommend that you improve your signal quality by following ";
		Assert.assertTrue(InsightNotes.getText().contains(GetDateAsPerNoiseInsightTitle()), "Insights are displayed for today's workout");
		Assert.assertTrue(InsightNotes.getText().contains(MarkerInfo),"Marking info is available");
	}
	
	
	public String GetDateAsPerNoiseInsightTitle()
	{
		String dayOfWeek = new SimpleDateFormat("EEEE").format(new Date());
		String CurrentDate= new SimpleDateFormat("dd").format(new Date());
		String CurrentMonth= new SimpleDateFormat("MMMM").format(new Date());
		String CurrentYear= new SimpleDateFormat("YYYY").format(new Date());
		
		return "Summary for workout on "+dayOfWeek+","+" "+CurrentDate+" "+CurrentMonth+" "+CurrentYear+" at";
	
	}
	
	
	
	
	public void VerifyTLandKCALData(String workoutTrainingload, String expectedWorkoutKCAL) throws InterruptedException
	
	{ 
		//System.out.println("Expected String "+workoutTrainingload);
		System.out.println("Actual Total Training Load is :"+getTrainingValue());
		System.out.println("Expected Total Training Load is:"+workoutTrainingload);
		Assert.assertEquals(getTrainingValue(),workoutTrainingload);
		
		System.out.println("Actual Total KCAL is :"+getDurationValue());
		System.out.println("Expected Total KCAL is:"+expectedWorkoutKCAL);
		Assert.assertEquals(getDurationValue(), expectedWorkoutKCAL);
		justSleepFor(3);
	}
	
	
		//Assert.assertEquals(WorkoutTrainingload().getText(),WorkoutTrainingload);
//		System.out.println("Total KCAL Value is :"+KCAL.getText());
//		System.out.println("Average HR Value is :"+HRValue.getText());
//		System.out.println("Average BR Value is :"+BRValue.getText());
//		System.out.println("Average Heart Stain Value is :"+HrStValue.getText());
//		System.out.println("Average HRV Value is :"+HRVValue.getText());
//		System.out.println("Average Body Shock Value is :"+BodyShockValue.getText());
//		System.out.println("Average Step Cadence Value is :"+CadenceValue.getText());
		
		
//		
//		List<WebElement> averagedatas = duriation.findElements(By.className("training-info"));
//		for(WebElement duriation : averagedatas )
//		{
//			System.out.println("Text: " + duriation.getText());
//			Thread.sleep(7000);
//			
//		//String strAvgData = avgdata.getText();
////			System.out.println(strAvgData);
//		}
	
	
private void justSleepFor(int i) {
		// TODO Auto-generated method stub
		
	}

//	public void VerifyTodaysTrainingLoad (String Title,String HomeTrainingLoad)
//	{
//		//System.out.println("Expected String "+HomeTrainingLoad);
//		//VerifyTodaysActivityIsDisplayed(Title);
//		System.out.println("Actual Duration Is:"+hometrainingLoad());
//		System.out.println("Expected Duration Is:"+HomeTrainingLoad);
//		Assert.assertEquals(hometrainingLoad(),HomeTrainingLoad);
//	}
//	

//	private WebElement WorkoutTrainingload() {
//		// TODO Auto-generated method stub
//		return null;
//	}


public String getSleepActDuration()
{
	return driver.findElement(By.className("training-dur-dynamic")).getText();
}

	public String getDurationValue()
	{
		return driver.findElement(By.id("wd-duration-value")).getText();
	}
	
	public String getTrainingValue()
	{
		return driver.findElement(By.id("wd-training-load-value")).getText();
	}
	
	public String getHeartRateValue()
	{
		return HRValue.getText();
	}
	
	public String getBRValue()
	{
		return BRValue.getText();
	}
	public String getStrainValue()
	{
	return driver.findElement(By.id("wd-heart-strain-value")).getText();
	}
	
	public String getHRVValue()
	{
		return driver.findElement(By.id("wd-hrv-value")).getText();
	}
	
	public String getStressValue()
	{
		return driver.findElement(By.id("wd-stress-value")).getText();
	}

	public String getBodyShock()
	{
		return driver.findElement(By.id("wd-body-shock-value")).getText();
	}
	
	/*
	 *  Reason for not initializing WebElement in Obj Repo is 
	 *  If Activity type is Rest , sleep , Yoga etc , Cadence will not captured and web element is not available in 
	 *  Web page and will fail to initialize
	 *
	 */
	public String getCadenceValue()
	{
		 return driver.findElement(By.id("wd-step-cadence-value")).getText();
	}
	
	public void ClickOnAvgTab()
	{
		AvgTab.click();
	}
	
	public void ClickOnMaxTab()
	{
		MaxTab.click();
	}
	public void ClickOnMinTab()
	{
		MinTab.click();
	}
	
	public String getTrainingTime()
	{
		return TrainingTime.getText();
	}
	
	
	public String getHRZoneDuration(int zone)
	{
		return HRZones.get(zone-1).findElement(By.className("duration")).getText();
		
	}
	
	public String getHRZonePercentage(int zone) throws InterruptedException
	{
		Thread.sleep(2000);
		return HRZonePercentage.get(zone-1).getText();
		
	}
	
	
	
	
}
