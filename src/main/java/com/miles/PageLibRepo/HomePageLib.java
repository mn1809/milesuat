package com.miles.PageLibRepo;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.miles.BaseSettings.MilesSettings;
import com.miles.PageObjectRepo.HomePageObj;
import com.miles.Utilities.MilesUtilities;

import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageLib extends HomePageObj
{

	public HomePageLib(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

		private Date date = new Date();  
		private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
	  
		String strDate = formatter.format(date);
		String SmokeTestString = "Regression test on "+strDate;
	
		String a = "C:\\Users\\user\\eclipse-workspace\\frontierX\\target\\surefire-reports\\testng-results.xml";
	
		String DeleteTagText = "This tag will no longer be accessible in both your app and web-app.";
		private	String HealthEntryInfoMsg = "Health entry added";
		
		private List<String> ExpectedUserList = Arrays.asList("User", "ClinicalTrialUser", "FCP User", "Premium User","FHP Premium User","Beta User","Beta2 User","Doctor","Coach","FCP Sales","FHP Doctor","Admin");
	
		private List<String> ReportsOptions = Arrays.asList("12 Lead ECG", "Angiogram", "Blood Test","Ct Scan","Discharge Summary","Echocardiogram","Holter ","MRI Scan","Nuclear Scan","Procedure Summary","Stress Test","Other");
	
		private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		
		//ExpectedUserList.
	
	//	Action Methods
		
//		public void ClearMyCandidateFilter()
//		{
//			ClearMyCandidatesWindow.click();
//		}
		
		
		
	public void clickOnActivityButton()
	{
		activityButton.click();
	}
	
	public void EnterText_EditActivity()
	{
		  
		EditTextBox.clear();
		
		EditTextBox.sendKeys(SmokeTestString);
		try 
		{
			Thread.sleep(1000);
		} catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saveButtonEdit.click();
		
	}
	
	
	public void EnableArrhytmia()
	{
		if(CheckIfArrhytmiaIsEnabled() == false)
		{
			ArrhythmiaDisabled.click();
			System.out.print("Enabled Arrhythmia");
		}
		
	}
	
	public void DisableArrhytmia()
	{
		if(CheckIfArrhytmiaIsEnabled() == true)
		{
			ArrhythmiaEnabled.click();
			System.out.print("Disabled Arrhythmia");
		}
		
	}
	
	public void VerifyLast30DaysSection()
	{
		Assert.assertEquals(Last30DaysStatsRow.get(0).getText(), "ACTIVITIES");
		Assert.assertEquals(Last30DaysStatsRow.get(1).getText(), "ACTIVE HOURS");
		Assert.assertEquals(Last30DaysStatsRow.get(2).getText(), "TRAINING LOAD");

	}
	
	
	public boolean CheckIfArrhytmiaIsEnabled()
	{
		
		if(Arrhythmia.getAttribute("style").contains("green"))
		{
		
			return true ;
			
		}
		else
		{
			return false ;
		}
	}
	
	/*
	 * Method To click on OK button on ECG PDF pop up
	 */
	
	public void ClickOnOkButton_ECGPopUp()
	{
		ECGPDFPopUp_OKBtn.click();
	}
	
	/*
	 * Method To Get text from Notification toaster
	 */
	public String GetNotificationText()
	{
		return NotificationInfoPopUp.getText();
	}
	
	/*
	 * Method To click on Health trend button on Home page
	 */
	public void ClickHealthTrendBtn()
	{
		HelthTreandBtn.click();
	}
	
	/*
	 * Method To Verify User Info Pop
	 */
	public void VerifyUserInfoPopUp(String expectedInfoTxt)
	{
		Assert.assertEquals(NotificationInfoPopUp.getText(), expectedInfoTxt,"Found Expected text is "+expectedInfoTxt);
	}
	
	/*
	 * Method To verify edited text is displayed for activity
	 */
	public void VerifyEditedTextDisplayed()
	{
		
	   Assert.assertTrue(GetActivityInfo().contains(SmokeTestString),"Edited Text is saved");
		
	}
	
	
	/*
	 * Method To return training load as Integer
	 */
	
	public int GetTrainingLoad_ActivityLevel()
	{
		return Integer.parseInt(trainingLoad_ActivitySection.getText());
	}
	
	/*
	 * Method To return training load from Runner Page as Integer
	 */
	
	public int GetTrainingLoad_RunPage()
	{
		return Integer.parseInt(trainingLoad_RunPage.getText());
		
	}
	
	/*
	 * Method To Click on Activity
	 */
	
	
	public void ClickOnActivity(int activityCount)
	{
		ActivityRows.get(activityCount).click();
	}
	
	
	/*
	 * Method To Print number of activity 
	 */
	
	public void PrintActivityRows()
	{
		System.out.println(" Fishy "+ActivityRows.size());
	}
	
	/*
	 * Method To return ECG Tick mark color
	 * Green if synced 
	 * Red if not synced
	 */
	public String GetECGMarker()
	{
		
		return ECGSyncMarker.getAttribute("style");
	}
	
	/*
	 * Method To return Map Tick mark color
	 * Green if synced 
	 * Red if not synced
	 */
	
	public String GetMapMarker()
	{
		WebElement MapMarker =driver.findElements(By.xpath("//*[starts-with(@id,'map-sync-')]")).get(0);
		return MapMarker.getAttribute("style");
	}
	
	/*
	 * Method To return Title of 1st record
	 */
	public String GetActivityInfo()
	{
		return FirstRec().getText();
	}
	
	public void ClickOnFirstRecord()
	{
		FirstRec().click();
	}
	/*
	 * Method To return Title of 1st record
	 */
	protected WebElement FirstRec()
	{
		WebElement ele = null ;
		try
		{
			ele = FirstRecords.get(0);
		}
		catch (Exception e)
		{
			System.out.println("Exception caught"+e);
		}
		
		return ele;
	}
	/*
	 * Method To return Sub Title of 1st record
	 */
	protected WebElement FirstRecSubTitle()
	{
		WebElement ele = null ;
		try
		{
			ele = RecordSubTitle.get(0);
		}
		catch (Exception e)
		{
			System.out.println("Exception caught"+e);
		}
		
		return ele;
	}
	
	
	
	
	/*
	 * Method to search for workout
	 */
	public void enterWorkout(String workout)
	{
		try
		{
			searchTextField.clear();
			searchTextField.sendKeys(workout);
			clickOnSearchWorkout();
			Thread.sleep(7000);
		}
		catch (Exception e)
		{
			System.out.print(e.getMessage());
		}
	}
		
		public void searchWorkout(String activity) throws InterruptedException 
		{
			searchTextField.click();
			searchTextField.sendKeys(activity);
			clickOnSearchWorkout();
			Thread.sleep(7000);
		}
	
	/*
	 * 
	 * Wait till till element is displayed
	 */
	
	public void WaitTillElementIsDisplayed(WebElement ele)
	{	
		
		try
		{
			//WebDriverWait wait = new WebDriverWait(driver,20);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOf(ele)); 
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	
	/*
	 * Returns Current WebApp Version
	 */
	public String getVersionInfo()
	{
		ScrollToBottomOfThePage();
		return CurrentVersion.getText();
	}
	
	public String getPaginationText()
	{
		return PaginationText.getText();
	}
	
	
	/*
	 * 
	 * Clicks on Search workout button
	 */
	
	public void clickOnSearchWorkout() 
	{
		searchButton.click();
	}
	
	/*
	 * 
	 * Returns Training load value
	 */
	
	public String trainingLoad()
	{
		return trainingLoad.getText();
	}
	
	public String hometrainingLoad()
	{
		return HomeTrainingLoad.getText();
	}
	
	
	public String homemaxstrain()
	{
		return HomeMaxStrain.getText();
	}
	/*
	 * Return Training load %
	 */
	
	public String trainingLoadPercent()
	{
		return trainingLoadPercent.getText();
	}
	
	/*
	 * Click on down arrow button in Training load area
	 */
	public void clickOnTrainingLoadDownArrow() 
	{
		trainingLoadDownArrow.click();
	}

	
	/*
	 * Verify FCP name in Admin Panel
	 */
	public void VerifyFCPName()
	{
		Assert.assertEquals(FCP.getText(), "Frontier Coach Platform");
	}
	
	/*
	 * Method to click on Add Coach button
	 */
	public void ClickOnAddCoachButton()
	{
		//AddCoachBtn.click();
	}
	
	/*
	 * Method to click on Add Coach button
	 */
	public void VerifyAddCoachPopUp()
	{
		//Assert.assertTrue(Coach_FirstName.isDisplayed());
		//Assert.assertTrue(Coach_LastName.isDisplayed());
		//Assert.assertTrue(Coach_email.isDisplayed());
		//Assert.assertTrue(Coach_deviceLink.isDisplayed());
		
	}
	
	
	/*
	 * Method to search users in Admin panel
	 */
    
	public void SearchUsers(String username)
	{
		SearchUserTextBox.click();
		SearchUserTextBox.sendKeys(username);
		searchButton_Admin.click();
	}
	
	/*
	 * Method to click on green tick mark
	 */
	public void clickOnGreenTick()
	{
		ECGSyncMarker.click();
	}
	
	
	public void clickOnGreenTickAdmin() throws InterruptedException
	{
		Thread.sleep(7000);
		//get window handlers as list
		List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
		//switch to new tab
		driver.switchTo().window(browserTabs .get(1));
		ECGSyncMarker.click();
	}
	
	/*
	 * Method to Verify if search user displayed
	 */
	
	
	/*
	 * Method to select searched user
	 */
	
	
	/*
	 * Method to verify if user name is displayed on dashboard if admin/doc/FCP is searching
	 */
	public void VerifyUserNameIsDisplayedForUser(String UserName)
	{
		
			List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
//switch to new tab
			driver.switchTo().window(browserTabs .get(1));
			System.out.println("<----"+CurrentUserName.getText()+"---->");
			Assert.assertTrue(CurrentUserName.getText().contains(UserName));
			driver.close();
			driver.switchTo().window(browserTabs.get(0));
	}
	
	
	/*
	 * Method to Verify ECG PDF POP up
	 */
	 public void VerifyECGPDFPopUp()
	 {
		 Assert.assertEquals(ECGPDFPopUpTitle.getText(), "Generate & Email ECG PDF ");
		 Assert.assertTrue(TimeIntervelInstrunction.getText().contains("SELECT TIME INTERVAL FOR ECG PDF (max 3 hours)"));
		// Assert.assertTrue(GenerateEmailBtn.isDisplayed());
	 }
	
	 /*
	  * Method to select any user level
	  */	
	public void SelectUserLevel(int userlevel)
	{
		Select UserLevelDropDown = new Select(driver.findElement(By.id("userLevel")));
		UserLevelDropDown.selectByIndex(userlevel);
	}
	
	/*
	 * Method to Verify User Level which is selected on the drop down
	 */
	public void VerifyUserLevel(String ExpectedUserLvl)
	{
		Select UserLevelDropDown = new Select(driver.findElement(By.className("user-role-select")));
		
		Assert.assertEquals(UserLevelDropDown.getFirstSelectedOption().getText(),ExpectedUserLvl,"Expected Result is matched");
	}
	
	/*
	 * Method to Verify User Level drop down list
	 */
	
	public void VerifyUserLevelOptions() throws InterruptedException
	{
		Select UserLevelDropDown = new Select(driver.findElement(By.className("user-role-select")));
		
		
		List<String> ConvertedList = new ArrayList<String>();
		
		
		List<WebElement> ActualLevelsList=UserLevelDropDown.getOptions();
		Thread.sleep(2000);
			
		for(int i = 0 ;i<ActualLevelsList.size();i++)
		{
			// Just print this for Debug purpose
			System.out.println("Debug : -> Item "+i+" : "+ActualLevelsList.get(i).getText());
		//	System.out.println("Debug : -> Expected List Item "+i+" : "+ExpectedUserList.get(i));
			ConvertedList.add(ActualLevelsList.get(i).getText());
			
		//Assert.assertTrue(ConvertedList.get(i).equals(ExpectedUserList.get(i)),"Expected is "+ExpectedUserList.get(i)+" Actual is "+ConvertedList.get(i));

		}
		
		//Assert.assertTrue(ConvertedList.equals(ExpectedUserList),"Not Equal");
	
		for(int j = 0 ;j<ActualLevelsList.size();j++)
		{
			Assert.assertTrue(ConvertedList.get(j).equals(ExpectedUserList.get(j)),"Expected is "+ExpectedUserList.get(j)+" Actual is "+ConvertedList.get(j));
		}	
	}
	
	
	/*
	 * Method to Click on Add Report Button
	 */
	public void ClickAddReportBtn()
	{
		AddReportBtn.click();
	}
	
	/*
	 * Method to Verify elements on Add Report pop up
	 */
	
	public void VerifyAddReportScreen()
	{
		
		Assert.assertTrue(!UploadBtn.isEnabled()); // Upload Button is disabled
		
		Assert.assertTrue(CancelBtn_AR.isEnabled());
		
		Assert.assertTrue(FileChooseArea.isDisplayed());
	}
	
	/*
	 * Method to select Vitals Tags
	 */
	
	public void SelectVitals() throws InterruptedException
	{
		HealthTags.get(0).click();
		Thread.sleep(3);
	}
	
	
	public void VerifyHealthTags(List<String> HTags)
	{
		int i = 0;
		for(String tag : HTags)
		{
			
			Assert.assertEquals(HealthTags.get(i).getText(), tag);
			i++;
		}
	}
	
	
	
	public void EnterHealthTagDetails(String val,String Notes) throws InterruptedException
	{
		sPO2Value.sendKeys(val);
		Thread.sleep(5);
		HealthEntrySaveBtn.click();
		Thread.sleep(2000);	
		Assert.assertEquals(GetNotificationText(), HealthEntryInfoMsg);
	
	}
	
	public void VerifyHealthTagIsDisplayed(String TagVal)
	{
		
		Assert.assertTrue(FirstHealthTag.get(0).getText().contains(TagVal), "Health Tag is Added ");
		
	}
	
	
	public void ClickOnAddHealtEntry()
	{
		AddHealthEntryBtn.click();
	}
	
	public void ClickHealthEntryInfoBtn()
	{
		HealthEntryInfoBtn.click();
	}
	
	
	
	public void ClickOnsPO2Tag()
	{
		sPOxygen.click();
	}
	
	public void clickOnTrainingLoadUpArrow() 
	{
		//trainingLoadUpArrow.click();
	}
	
	public void clickOnDownloadCSV() 
	{
		downloadCSVFile.click();
	}
	
	public void clickOnDownloadZIP()
	{
		downloadZIPFile.click();
	}
	
	public String getWorkoutDuration()
	{
		return WorkoutDuration.getText();
	}
	
	public void shareActivityLink()
	{
		shareActivityLink.click();
	}
	
	public void cancelShareActivityLink() {
		cancelShareActivity.click();
	}
	
	public void ClickgenerateECGPDF()
	{
		ecgPDF.click();
	}
	
	public void clickOnDownloadFIT()
	{
		downloadFITFile.click();
	}
	public String GetECGPDFTitle()
	{
		return ECGPDF_Title.getText();
	}
	
	public String getShareActivityLink()
	{
		return ShareActivityURLTextBox.getText();
	}
	
	
	
	public void VerifyECGPDFPop()
	{
		Assert.assertTrue(slider.isDisplayed());
		Assert.assertEquals(ECGPDF_Title.getText(), "Generate & Email ECG PDF");
		Assert.assertTrue(ECGPDFTextSection.getText().contains("SELECT TIME INTERVAL FOR ECG PDF (max 3 hours)"));
	}
	
	public void VerifyHomePageElements()
	{
		//Verifies User Icon
		//Assert.assertTrue(UserIcon.isDisplayed());
		//Verifies the Title of the Page
		Assert.assertEquals(ActivityHistoryHeader.getText(), "Activity History");
		Assert.assertTrue(searchTextField.isDisplayed());
		Assert.assertTrue(searchTextField.isEnabled());
	// Verifies Search button 
		Assert.assertTrue(searchButton.isDisplayed());
		Assert.assertTrue(searchButton.isEnabled());
		
	// Verifies calander Icon 
		Assert.assertTrue(CalanderIcon.isDisplayed());
		Assert.assertTrue(CalanderIcon.isEnabled());
		
		// Verifies Add Health Event Tag button 
		Assert.assertTrue(AddHealthEntryBtn.isDisplayed());
		Assert.assertTrue(AddHealthEntryBtn.isEnabled());
		
		// Verifies Recomendation section 
		Assert.assertTrue(RecoSection.isDisplayed());
		// Verify Table Header
		Assert.assertTrue(ActivitySerialNum.isDisplayed());
		Assert.assertTrue(ActivityTitle.isDisplayed());
		Assert.assertTrue(ActivityStatus.isDisplayed());
		Assert.assertTrue(ActivityDuration.isDisplayed());
		Assert.assertTrue(ActivityTrainingLoad.isDisplayed());
		Assert.assertTrue(ActivityMaxStrain.isDisplayed());
		Assert.assertTrue(ActivityRhythm.isDisplayed());
	}
	
	public void VerifyTableHeaders_RegularUser()
	{
		Assert.assertEquals(ActivitySerialNum.getText(), "S. NO.");
		Assert.assertEquals(ActivityTitle.getText(), "TITLE");
		Assert.assertEquals(ActivityStatus.getText(),"STATUS");
		Assert.assertEquals(ActivityDuration.getText(),"DURATION");
		Assert.assertEquals(ActivityTrainingLoad.getText(),"TRAINING LOAD");
		Assert.assertEquals(ActivityMaxStrain.getText(),"MAX STRAIN");
		Assert.assertEquals(ActivityRhythm.getText(),"RHYTHM");
		
		System.out.println("Assertions Passed for Table headers");
	}
	
	public void VerifyTableHeaders_FDAUser()
	{
		Assert.assertEquals(ActivitySerialNum.getText(), "S. NO.");
		Assert.assertEquals(ActivityTitle.getText(), "TITLE");
		Assert.assertEquals(ActivityStatus.getText(),"STATUS");
		Assert.assertEquals(ActivityDuration.getText(),"DURATION");
		Assert.assertEquals(ActivityMaxStrain.getText(),"MAX STRAIN");
		Assert.assertEquals(ActivityRhythm.getText(),"RHYTHM");
	}
	public void VerifyLoggedInUserName(String UserLvl)
	{
		Assert.assertEquals(UserNameSection.getText(),UserLvl);
	}
	
	/*
	 * Method to Verify the Contents and buttons in ECG PDF
	 */
	public void isECGPDFPopUpDisplayed()
	{
		String PopUpTitle = "ACTIVITY";
		String ActivityTitle = GetActivityInfo();
		String TimeRangeInstructions = "SELECT TIME INTERVAL FOR ECG PDF (max 3 hours)";
		Assert.assertTrue(GenerateEmailPDFButton.isDisplayed());
		Assert.assertTrue(CancelGenerateEmailPDFButton.isDisplayed());
		Assert.assertTrue(TimeRange.isDisplayed());
		Assert.assertTrue(ECGPDFContent.getText().contains(PopUpTitle));
		Assert.assertTrue(ECGPDFContent.getText().contains(ActivityTitle));
		Assert.assertTrue(ECGPDFContent.getText().contains(TimeRangeInstructions));
	}
	public void ClickOnDeleteTag()
	{
		DeletTag.click();
	}
	
	
	public void clickOnECGPDFGen()
	{
		GenerateEmailPDFButton.click();
	}
	public void clickOnLiveECG() 
	{
		liveECG.click();
	}
	
	
	/*
	 * Method to verify Calander Date Picker
	 */
	public void VerifyCalanderPickerisDisplayed()
	{
		ClickOnCalanderPicker();
		MilesSettings.justSleepFor(2);
		Assert.assertTrue(driver.findElement(By.className("mx-datepicker-body")).isDisplayed(),"Calander Is Displayed");
		
	}
	
	public void ClickOnCalanderPicker()
	{
		CalanderIcon.click();
	}
	public void VerifyDeletePopUpText()
	{
	//	System.out.println("Text = "+DeletePopUpText.getText());
	//	Assert.assertTrue(DeletePopUpText.getText().contains(DeleteTagText),"Text Matches" );
	}
	 
	
	public void VerifyLiveECGBtn_Displayed()
	{	
			Assert.assertTrue(liveECG.isDisplayed());
			Assert.assertTrue(liveECG.isEnabled());
	}
	
	public void clickOnLiveUserDashboard_AdminPanel()
	{
		liveUserDashboard.click();
	}
	
	public void editActivity() 
	{
		editActivity.click();
	}
	
	public void deleteActivity() 
	{
		deleteActivity.click();
	}
	
	public void ClickkeepButton()
	{
		keepButton.click();
	}
	
	public void ClickOnDeleteBtn_Tag()
	{
	//	DeleteTagBtn.click();
	}
	
	public void cancelButtonEdit() 
	{
		cancelButtonEdit.click();
	}
	
	public void saveButtonEdit() 
	{
		saveButtonEdit.click();
	}
	
	public void logout() 
	{
		logoutButton.click();
	}
	
	public void ClickRefresh() 
	{
		refreshButton.click();
	}
	
	public void VerifyOpenAdminPanel()
	{
		//driver.findElement(By.className("sidebar-toggle")).click();
		driver.findElement((By.xpath("//*[contains(@class, 'o-dropdown dropdown o_navbar_apps_menu o-dropdown--no-caret')]"))).click();
		//driver.findElement((By.xpath("//*[contains(@class, 'fa fa-bars')]"))).click();
		
	}
	
	
	
	public void VerifyRefreshFunctionality() 
	{
		Assert.assertTrue(RefreshRing.isDisplayed(),"Refresh icon is Displayed");
	}
	
	/*
	 * Scripts Waits till Loader to disappear for further functions
	 */
	public void WaiTillLoaderToDisappear()
	{
		wait.until(ExpectedConditions.invisibilityOf(RefreshRing));
	}
	
	public void VerifyECGPDFGeneratedPopUpDisplayed()
	{
			MilesSettings.justSleepFor(10);
			Assert.assertTrue(ECGPDFPopUp.isDisplayed(),"ECG Generated Pop Up should display");
		
	}
	
	public void VerifySerialNumber(String sn)
	{
		Assert.assertEquals(WorkOutSN.getText(), sn);
	}
	
	public void VerifyTodaysActivityIsDisplayed(String Exp)
	{
		System.out.println("Expected String "+Exp);
		System.out.println("Title of 1st Record:"+FirstRec().getText()+"\n");
		Calendar cal = Calendar.getInstance();
		System.out.println("Subtitle in 1st record : "+FirstRecSubTitle().getText()+"\n");
		Assert.assertEquals(FirstRec().getText(),Exp);
		Assert.assertTrue(FirstRecSubTitle().getText().contains(MilesUtilities.getCurrentDateInSpecificFormat(cal)),"Today's Activity is Synced");
	
	}
	
		
	
	
	
	
	public void VerifyTodaysECGIsSynced(String Exp)
	{
		VerifyTodaysActivityIsDisplayed(Exp);
		Assert.assertTrue(GetECGMarker().contains("green"),"ECG is Synced with Green Tick Mark");
	}
	

	
	
	
	
	public void VerifyTodaysActivityDuration(String Duration,String Title)
	{
		System.out.println("Expected String "+Duration);
		
		VerifyTodaysActivityIsDisplayed(Title);
		System.out.println("Actual Duration Is:"+getWorkoutDuration());
		System.out.println("Expected Duration Is:"+Duration);
		Assert.assertEquals(getWorkoutDuration(),Duration);
		
	}
	public void VerifyTodaysTrainingLoad (String Title,String HomeTrainingLoad)
	{
		//System.out.println("Expected String "+HomeTrainingLoad);
		VerifyTodaysActivityIsDisplayed(Title);
		System.out.println("Actual Duration Is:"+hometrainingLoad());
		System.out.println("Expected Duration Is:"+HomeTrainingLoad);
		Assert.assertEquals(hometrainingLoad(),HomeTrainingLoad);
	}
	
	public void VerifyTodaysMaxStrain (String HomeMaxStrain,String Title)
	{
		System.out.println("Expected String "+HomeMaxStrain);
		VerifyTodaysActivityIsDisplayed(Title);
		System.out.println("Actual Max Strain Is:"+homemaxstrain());
		System.out.println("Expected Max Strain Is:"+HomeMaxStrain);
		Assert.assertEquals(homemaxstrain(),HomeMaxStrain);
	}
	public void VerifyTodaysRhythmValue (String Title,String NRV)
	{
		String Noise = "Noise: 0.09%";
//		String Normal = "Normal: 99.9%";
		VerifyTodaysActivityIsDisplayed(Title);
		justSleepFor(5);
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.id("pie-chart"))).perform();
		String NormalRhythm = driver.findElement(By.id("chartjs-tooltip")).getText();
		System.out.println("NSR value in Pie-Chart Is = "+NormalRhythm );
		
		if( NormalRhythm.contains("Noise: 0.09%") || NormalRhythm.contains("Normal: 97.74%"))
				{
					System.out.println("Values are Expected");
				}
		else
		{
			Assert.fail("Pie Chart Value is neither Noise: 0.09% or Normal: 99.9% But Value Is "+NormalRhythm);
		}
//Assert.assertTrue(NormalRhythm.equals(NRV)  || Noise.equals(NRV),"Rhythm score"+NRV+" is as expected" );
	}
	
	public void VerifyTodaysRhythmValues (String Title,String NRV)
	{
		String Noise = "Noise: 0.09%";
//		String Normal = "Normal: 99.9%";
		VerifyTodaysActivityIsDisplayed(Title);
		justSleepFor(5);
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.id("pie-chart"))).perform();
		String NormalRhythm = driver.findElement(By.id("chartjs-tooltip")).getText();
		System.out.println("NSR value in Pie-Chart Is = "+NormalRhythm );
		
		if( NormalRhythm.contains("Noise: 0.09%") || NormalRhythm.contains("Normal: 99.9%"))
				{
					System.out.println("Values are Expected");
				}
		else
		{
			Assert.fail("Pie Chart Value is neither Noise: 0.09% or Normal: 99.9% But Value Is "+NormalRhythm);
		}
//Assert.assertTrue(NormalRhythm.equals(NRV)  || Noise.equals(NRV),"Rhythm score"+NRV+" is as expected" );
	}
	public void VerifyOpen1stActivity()
	{
		
		OpenFirstActivity();
		 justSleepFor(5);
		 List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
			//switch to new tab
			driver.switchTo().window(browserTabs .get(1));
	}
	
	public void OpenFirstActivity()
	{
		HomeTrainingLoad.click();
	}

	public void PerformEditActivity(String expectedEditedActivityName) throws InterruptedException
	{
		
		activityButton.click();
		
		justSleepFor(1);
		editActivity.click();
		justSleepFor(2);
		EditTextBox.clear();
		justSleepFor(2);
		EditTextBox.sendKeys(expectedEditedActivityName);
		justSleepFor(4);
		saveButtonEdit.click();
		Thread.sleep(7000);	        

	}
	
	public void ReadEditedActivity(String expectedEditedActivityName)
	
	{
		System.out.println("Title of Edited Record:"+FirstRec().getText()+"\n");
		Assert.assertEquals(FirstRec().getText(), expectedEditedActivityName);
		justSleepFor(5);
	}
	
public void ReadSearchedActivity(String expectedSearchedActivityName)
	
	{
		System.out.println("Title of Searched Activity:"+FirstRec().getText()+"\n");
		Assert.assertEquals(FirstRec().getText(), expectedSearchedActivityName);
		
	}
	
	
	
	
	private void justSleepFor(int i) {
		// TODO Auto-generated method stub
		
	}

	public void VerifyReportDropDownOptions() throws InterruptedException
	{
		Select ReportUploadDropDown = new Select(driver.findElement(By.className("user-role-select")));
		
		List<String> ConvertedList = new ArrayList<String>();
		
		
		List<WebElement> ActualReportsList=ReportUploadDropDown.getOptions();
		Thread.sleep(2000);
			
		for(int i = 1 ;i<=ActualReportsList.size()-1;i++)
		{
			// Just print this for Debug purpose
			System.out.println("Debug : -> Item "+i+" : "+ActualReportsList.get(i).getText());
		//	System.out.println("Debug : -> Expected List Item "+i+" : "+ExpectedUserList.get(i));
			ConvertedList.add(ActualReportsList.get(i).getText());
			
		//Assert.assertTrue(ConvertedList.get(i).equals(ExpectedUserList.get(i)),"Expected is "+ExpectedUserList.get(i)+" Actual is "+ConvertedList.get(i));

		}
		
		//Assert.assertTrue(ConvertedList.equals(ExpectedUserList),"Not Equal");
	
		for(int j = 1 ;j<=ActualReportsList.size()-1;j++)
		{
			Assert.assertTrue(ConvertedList.get(j).equals(ReportsOptions.get(j)),"Expected is "+ReportsOptions.get(j)+" Actual is "+ConvertedList.get(j));
		}
		
	}
	
	/*
	 * Method Verifies Title of Info Pop up of 
	 * Status , Training Load , Max Strain , Rhythm
	 */
	
	public void VerifyStatusInfoPopUpTitles() throws InterruptedException
	{
		String StatusTitle = "Status Indicators";
		String TrainingLoadTitle = "Training Load";
		Point location = ActivityHistoryHeader.getLocation();
		ActivityStatus.click();
		Thread.sleep(1500);
		Assert.assertEquals(StatusInfoTitle.getText(), StatusTitle);
		new Actions(driver).moveByOffset(location.x,location.y).click().perform(); // This is to dismiss info pop up by clicking anywhere on the web app
		Thread.sleep(1500);
		ActivityTrainingLoad.click();
		Thread.sleep(1500);
		Assert.assertEquals(TrainingInfoTitle.getText(), TrainingLoadTitle);
	}
	
	
	
	/*
	 * Verifies the Tool tip text is as same as Activity name
	 */
	public void VerifyToolTip()
	{
		String ActivityTitle = GetActivityInfo();
		Actions action = new Actions(driver);
		action.moveToElement(FirstRecords.get(0)).perform();
		String ActivityToolT = ActivityToolTip.getText();
		System.out.println("Activity name :"+ActivityTitle+"\n"+"Tool tip text : "+ActivityToolT);
		Assert.assertEquals(ActivityTitle, ActivityToolT);

	}
	
	/*
	 * Scrolls and wait till Bottom element is really visible
	 */
	public void ScrollToBottomOfThePage()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("version-info")));
	}
	
	/*
	 * Scrolls to the bottom and clicks on the Terms of Use Link
	 */
	public void ClickOnTermsOfUseLink()
	{
		ScrollToBottomOfThePage();
		TermsOfUseLink.click();
		MilesUtilities.SwitchTab(1, driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MainContent")));
	}
	
	/*
	 * Scrolls to the bottom and clicks on the Disclaimers link
	 */
	public void ClickOnDisclaimerLink()
	{
		ScrollToBottomOfThePage();
		DisclaimersLink.click();
		MilesUtilities.SwitchTab(1, driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MainContent")));
		
	}
	
	/*
	 * Scrolls to the bottom and clicks on the Privacy Policy link
	 */
	public void ClickOnPolicyLink()
	{
		ScrollToBottomOfThePage();
		PrivacyPolicyLink.click();
		MilesUtilities.SwitchTab(1, driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shogun-heading-component")));
	}
	
	
	public void VerifyTermsOfUseWebPageIsDisplayed() throws InterruptedException
	{
		Assert.assertTrue(driver.getCurrentUrl().toString().contains("terms-of-use"));
		Thread.sleep(1500);
		Assert.assertTrue(driver.getPageSource().contains("TERMS OF USE"));
	}
	
	public void VerifyDisclaimersWebPageIsDisplayed()
	{
		Assert.assertTrue(driver.getCurrentUrl().contains("disclaimers"));
		Assert.assertTrue(driver.getPageSource().contains("Disclaimers"));
	}

	public void VerifyPrivacyPolicyWebPageisDisplayed()
	{
		Assert.assertTrue(driver.getCurrentUrl().contains("privacy-policy"));
		Assert.assertEquals(driver.findElement(By.className("shogun-heading-component")).getText(),"Privacy Policy","Privacy Policy Web page is displayed");
	}
	
	
	public void VerifyTrainingLoadTimeLineTabs() throws InterruptedException
	{
		List<String> ExpectedTrainingLoadTabs = Arrays.asList("DAILY", "WEEKLY", "MONTHLY");
		wait.until(ExpectedConditions.visibilityOf(trainingLoad));
		// Adding this Locator here as until Visibility of trainingLoad circle , this element will not be visible so it'll fail in initialization.
		List<WebElement> ActualTrainingLoadTabs = driver.findElement(By.className("training-load-timeline-tab")).findElements(By.xpath("./child::*"));
		clickOnTrainingLoadDownArrow();
		Thread.sleep(2000);
		for (int i=0;i<ActualTrainingLoadTabs.size();i++)
		{
			Assert.assertEquals(ActualTrainingLoadTabs.get(i).getText(), ExpectedTrainingLoadTabs.get(i));
			
		}
		 
		 
//		for (int i = 0; i < ActualTrainingLoadTabs.size(); i++)
//	    {
//	        String tabText = ActualTrainingLoadTabs.get(i).getText().trim();
//	        Assert.assertTrue(!tabText.equals("0"), "Tab label should not be zero. Found zero in tab: " + ExpectedTrainingLoadTabs.get(i));
//	    }
		
	}
	
//	
//	public void WaitForUsersHomePageToLoad_Admin()
//	{
//		MilesUtilities.WaitForVisibilityOfElement(driver, "className", "current-user-dashboard");
//	}
	
	public void ClickOnRightPagination()
	{
		RightPgination.click();
	}
	
	public void ClickOnLefttPagination()
	{
		LeftPgination.click();
	}
}
