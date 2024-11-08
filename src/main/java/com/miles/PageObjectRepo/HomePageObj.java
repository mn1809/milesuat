package com.miles.PageObjectRepo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import com.miles.BaseSettings.MilesBasePage;




public class HomePageObj extends MilesBasePage
{
	public HomePageObj(WebDriver driver) 
{
	super(driver);
}

	   private Date date = new Date();
	   private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	  
	   String strDate = formatter.format(date);
	   
	   String SmokeTestString = "Smoke Test on "+strDate;
	   

   
//	//	Creating WebElements
	   
		@FindBy(xpath = "//a[normalize-space()='USP Eligibility']")
		protected WebElement UspEligibilityTab ;
		
		
}




//	@FindBy(xpath = "//input[@placeholder='Search by Title']")
//	protected WebElement searchTextField ;
//	
//	//protected WebElement UserIcon = driver.findElement(By.className("fa-user-o")) ;
//	
//	//@FindAll(@FindBy(className = "row w-list"))
//	protected List<WebElement> ActivityRows;
//	
//	
//	
//	//protected WebElement ClearMyCandidatesWindow = driver.findElement((By.xpath("//*[contains(@class, 'o_facet_remove oi oi-close btn btn-link opacity-50 opacity-100-hover text-900')]")));
//	
//	@FindBy(xpath = "//button[@class='search-button']")
//	protected WebElement searchButton ;
//	
//	//@FindBy(css = "//i[@class='fa fa-power-off']") //.fa.fa-power-off
////	protected WebElement logoutButton = driver.findElement(By.className("fa-power-off"));
//	
//	@FindBy(xpath = "//i[@class='fa fa-refresh']")
//	protected WebElement refreshButton ;
//	
//	@FindBy(xpath = "//div[@class='donut-inner']") 
//	protected WebElement trainingLoad ;
//	
//	@FindBy (className = "mini-toastr-notification__message")
//	protected WebElement NotificationInfoPopUp;
//	
//	@FindBy(xpath = "//span[@class='alert_new_dl_1']")
//	protected WebElement trainingLoad_ActivitySection ;
//	
//	@FindBy(xpath = "//div[@class='data alert_new_dl_1']")
//	protected WebElement trainingLoad_RunPage ;
//	
//	@FindBy(xpath = "//div[@class='training-percentage']")
//	protected WebElement trainingLoadPercent ; 
//	
//	@FindBy(xpath = "//i[@class='fa fa-chevron-down']")
//	protected WebElement trainingLoadDownArrow ;
//	
//	
//	@FindBy(xpath = "//span[contains(text(),'Frontier Coach Platform')]")
//	protected WebElement FCP;
//	
//	@FindBy(xpath = "//input[@placeholder='Search by Name']")
//	protected WebElement SearchUserTextBox ;
//	
//	//@FindBy(xpath = "//div[@class='col-12 col-md-4']") //longContentControl
//	//@FindBy(className = "col-12 col-md-4")
//	
//	
//	//@FindBy(xpath = "//div[@class='col-md-2 col-10 w-name-info']")
//	protected WebElement UserRecord_UserName ;
//	
//	//@FindBy(xpath = "//i[@class='fa fa-chevron-up']")
//	//protected WebElement trainingLoadUpArrow = driver.findElement(By.className("training-load-toggle"));
//	
//	@FindBy(xpath = "//img[@src='/images/Dropdown_change.png?1954b4fed9b0b4f0befe41fc6c9f36fb']")
//	protected WebElement activityButton;
//	
//	@FindBy(id = "menu-share-activity-link")
//	protected WebElement shareActivityLink ;
//	
//	@FindBy(id = "menu-download-summary-data")
//	protected WebElement downloadCSVFile ;
//	
//	//protected WebElement downloadCSVFile_Doc_Admin = driver.findElement(By.partialLinkText("Download Summary Data (CSV)"));
//	
//	@FindBy(id = "menu-download-ecg-data-zip")
//	protected WebElement downloadZIPFile;
//	
//	@FindBy(id = "menu-generate-ecg-pdf")
//	protected WebElement ecgPDF ;
//	
//	@FindBy(id = "menu-download-fit-file")
//	protected WebElement downloadFITFile ;
//	
//	@FindBy(className = "current-user-dashboard")
//	protected WebElement CurrentUserName;
//	
//	@FindBy(className = "edit-name")
//	protected WebElement ECGPDFPopUpTitle;
//	
//	@FindBy(className = "sectionGenerate")
//	protected WebElement TimeIntervelInstrunction;
//	
//	@FindBy(partialLinkText = "Tag") 
//	protected WebElement DeletTag ;
//	
//	//@FindBy(xpath = "//span[@class='btn live-ecg-dashboard']")
//	protected WebElement liveECG ;
//	
//	@FindBy(xpath = "//span[.='Live User Dashboard']")
//	protected WebElement liveUserDashboard ;
//	
//	@FindBy(id = "menu-edit-activity")
//	protected WebElement editActivity ;
//	
//	@FindBy(id = "edit-activity-popup-cancel")
//	protected WebElement cancelButtonEdit ;
//	
//	@FindBy(xpath = "//button[@class='search-button']")
//	protected WebElement searchButton_Admin ;
//	
//	@FindBy(id = "edit-activity-popup-submit")
//	protected WebElement saveButtonEdit ;
//	
//	@FindBy(id = "menu-delete-activity")
//	protected WebElement deleteActivity ;
//	
//	//@FindBy(xpath = "(//button[@class='btn cancel-button") //btn cancel-button 
//	protected WebElement keepButton;
//	
//	//@FindBy(xpath = "(//button[@class='btn cancel-button'])[3]")
//	protected WebElement cancelShareActivity;
//	
//	//protected WebElement HomeTrainingLoad = driver.findElement(By.className("training-load-val-qa"));
//	//button[@type='button']
//	protected WebElement HomeTrainingLoad = driver.findElements(By.xpath("//button[@type='button']")).get(0);
//	
//	protected WebElement HomeMaxStrain = driver.findElement(By.className("max-strain-val-qa"));
//	
//	protected WebElement MouseoverRhythmPieChart = driver.findElement(By.id("pie-chart"));
//	//protected WebElement ToolTipMessage = driver.findElement(null)
//	
//	
//	protected WebElement WorkoutDuration = driver.findElement(By.className("duration-val-qa"));
//	
//	protected List<WebElement> FirstRecords = driver.findElements(By.className("w-title"));
//	
//	protected List<WebElement> RecordSubTitle = driver.findElements(By.className("w-time"));
//	
//	protected List<WebElement> Last30DaysStatsRow = driver.findElement(By.className("statistic-box-row")).findElements(By.className("item-label"));
//	
//
//	//protected List<WebElement> Last30DaysStatsValues = driver.findElement(By.className("statistic-box-row")).findElements(By.className("item-number"));
//	//protected WebElement Last30DaysActivities = Last30DaysStatsRow.findElements(By.className(SmokeTestString))
//	
//	//protected WebElement FirstRecord = FirstRecords.get(0);
//	
//
//
//	//@FindBy(xpath = "//span[@class='col-lg-4 col-11 w-name-info']") 
//	protected WebElement HealthEntryInfoBtn;
//	
//
//	//@FindBy(xpath = "//div[@class='col-lg-10 col-6 w-name-info']") 
//	protected List<WebElement> FirstHealthTag = driver.findElements(By.className("w-title"));
//	
//	protected WebElement ECGSyncMarker = driver.findElements(By.xpath("//*[starts-with(@id,'ecg-sync-')]")).get(0);
//	
//	//protected 	
//	@FindBy(id = "workout-title-edit")
//	protected WebElement EditTextBox;
//	
//	@FindBy(id = "__BVID__141_")
//	protected WebElement ActivityDropDown;
//	
//	@FindBy(xpath = "//*[contains(text(), 'ECG PDF Generated')]")
//	protected WebElement ECGPDFPopUp;
//	//
//	//@FindBy(xpath = "(//button[@class='btn cancel-button']")
//	protected WebElement ECGPDFPopUp_OKBtn;
//	
//	protected WebElement GenerateEmailPDFButton = driver.findElement(By.id("ecg-pdf-popup-submit"));
//	
//	protected WebElement CancelGenerateEmailPDFButton = driver.findElement(By.id("ecg-pdf-popup-cancel"));
//	
//	protected WebElement TimeRange = driver.findElement(By.id("time-range"));
//	
//	//@FindBy(xpath = "(//div[@class='v-switch-core'])")
//	protected WebElement Arrhythmia ;
//	
//	//@FindBy(xpath = "(//span[@class='v-switch-label v-left'])")
//	protected WebElement ArrhythmiaEnabled ;
//	
////	@FindBy(xpath = "(//span[@class='v-switch-label v-right'])")
//	protected WebElement  ArrhythmiaDisabled;
//	
//	@FindBy(className = "btn-health-entry")
//	protected WebElement AddHealthEntryBtn;
//	
//	@FindAll(@FindBy(className = "lb_item"))
//	protected List<WebElement> HealthTags;
//	
//	@FindBy(id = "wrap")
//	protected WebElement sPOxygen;
//	
//	@FindBy(className = "search-tag")
//	protected WebElement sPO2Value;
//	
//	@FindBy(id = "note-title")
//	protected WebElement ShareActivityURLTextBox;
//	
//	
//	//@FindBy(xpath = "//div[@class='vs-component con-vs-popup vs-popup-primary']//div[@class='form-modal']//div[2]//button[2]")
//	protected WebElement HealthEntrySaveBtn;
//	protected WebElement HelthTreandBtn = driver.findElement(By.className("user_forum_max"));
//	
//	protected WebElement ECGPDF_Title = driver.findElement(By.className("edit-name"));
//	
//	protected WebElement slider = driver.findElement(By.id("slider-range"));
//	
//	protected WebElement ECGPDFTextSection = driver.findElement(By.className("sectionGenerate"));
//	
//	protected WebElement ActivityHistoryHeader = driver.findElement(By.className("table-board-header"));
//	
//	protected WebElement CalanderIcon = driver.findElement(By.className("calendar-icon"));
//	
//	
//	
//	protected WebElement RecoSection = driver.findElement(By.className("statistic-box-header"));
//	
//	protected WebElement UserNameSection = driver.findElement(By.className("user_name_max"));
//	
//	
//	protected WebElement AddReportBtn = driver.findElement(By.xpath("//button[normalize-space()='Add Report']"));
//	
//	protected WebElement ViewReportBtn = driver.findElement(By.xpath("//button[normalize-space()='Add Report']"));
//	protected WebElement UploadBtn = driver.findElement(By.xpath("//button[normalize-space()='Upload']"));
//	
//	protected WebElement FileChooseArea = driver.findElement(By.xpath("//input[@id='file']"));
//
//	protected WebElement CancelBtn_AR = driver.findElement(By.xpath("//body/div/div/div/div/div[2]/button[1]"));
//	
//	protected WebElement ActivitySerialNum = driver.findElement(By.id("activity-header-serial-number"));
//	
//	protected WebElement ActivityTitle = driver.findElement(By.id("activity-header-title"));
//	protected WebElement ActivityStatus = driver.findElement(By.id("activity-header-status"));
//	protected WebElement ActivityDuration = driver.findElement(By.id("activity-header-duration"));
//	protected WebElement ActivityTrainingLoad = driver.findElement(By.id("activity-header-training-load"));
//	protected WebElement ActivityMaxStrain = driver.findElement(By.id("activity-header-max-strain"));
//	protected WebElement ActivityRhythm = driver.findElement(By.id("activity-header-rhythm"));
//	
//	protected List<WebElement> InfoBtns = driver.findElements(By.className("fa-info-circle"));
//	
//	protected WebElement ActivityToolTip = driver.findElement(By.className("w-tooltip-text"));
//	protected WebElement PopUpTextBox = driver.findElement(By.className("vs-popup"));
//	
//	protected WebElement PopUpContent = PopUpTextBox.findElement(By.className("vs-popup--content"));
//	protected List<WebElement> PopUpContentSections = PopUpContent.findElements(By.className("col-md-10"));
//	protected WebElement ECGPDFContent = driver.findElement(By.className("sectionGenerate"));
//	
//	protected WebElement StatusInfoTitle = driver.findElement(By.xpath("//div[contains(text(),'Status Indicators')]"));
//	
//	protected WebElement MaxStrainInfoTitle = driver.findElement(By.xpath("//div[contains(text(),'Max Heart Strain')]"));
//	protected WebElement TrainingInfoTitle = driver.findElement(By.xpath("//div[@class='row']//div[contains(text(),'Training Load')]"));
//	
//	protected WebElement RefreshRing = driver.findElement(By.className("lds-dual-ring"));
//	
//	protected WebElement TermsOfUseLink = driver.findElement(By.partialLinkText("Terms of Use"));
//	protected WebElement DisclaimersLink = driver.findElement(By.partialLinkText("Disclaimers"));
//	protected WebElement PrivacyPolicyLink = driver.findElement(By.partialLinkText("Privacy Policy"));
//	protected WebElement RightPgination = driver.findElement((By.xpath("//*[contains(@class, 'fa-angle-right')]")));
//	protected WebElement LeftPgination = driver.findElement((By.xpath("//*[contains(@class, 'fa-angle-left')]")));
//	protected WebElement WorkOutSN = driver.findElement(By.className("workout-number"));
//	protected WebElement CurrentVersion = driver.findElement(By.className("version-info"));
//	protected WebElement PaginationText = driver.findElement(By.className("pagination-info-lg"));
//	
	
	
	
	
	/*
	protected WebElement AddCoachBtn = driver.findElement(By.xpath("//button[@id='addNewTrainerBtn']"));
	protected WebElement Coach_FirstName = driver.findElement(By.id("first_name"));
	
	protected WebElement Coach_LastName = driver.findElement(By.id("last_name"));
	
	protected WebElement Coach_email = driver.findElement(By.id("trainer_email"));
	
	protected WebElement Coach_deviceLink = driver.findElement(By.id("device_purchase_link"));
	*/
	//	Constructor
	
	
	
	

