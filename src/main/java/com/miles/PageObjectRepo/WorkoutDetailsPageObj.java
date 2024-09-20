package com.miles.PageObjectRepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import com.miles.BaseSettings.MilesBasePage;

public class WorkoutDetailsPageObj extends MilesBasePage
{
	public WorkoutDetailsPageObj(WebDriver driver)
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//	Identifying WebElements
	@FindBy(id = "e1")
	protected WebElement ECGBlock1 ;
	
	@FindBy(id = "e2")
	protected WebElement ECGBlock2 ;
	
	@FindBy (id ="charthr")
	protected WebElement HeartRateBlock;
	
	@FindBy(className = "dygraph-legend")
	protected WebElement HeartRateGraph;
	
	@FindBy(className = "vs-popup")
	protected WebElement AnnotationWindow;
	
	protected WebElement TrainingLoadCard ;//= driver.findElements(By.className("training-info"));
	
	
	@FindBy (id ="chartst")
	protected WebElement CardiacStrain;
	
	@FindBy (id ="charthrv")
	protected WebElement HRVBlock;
	
	@FindBy (id ="chartbr")
	protected WebElement BreathingRate;
	//
	@FindBy (id ="mapid")
	protected WebElement MapArea;
	
	@FindBy (id ="speed")
	protected WebElement SeepChart;
	
	@FindBy (id ="altitude")
	protected WebElement Alltitude;
	
	@FindBy (id ="chartcadence")
	protected WebElement StepCadence;
	
	@FindBy (id ="chartshock")
	protected WebElement BodyShock;
	
	@FindBy(className = "add-notes")//button[@class='add-notes'])
	protected WebElement AddNotes ;
	
	//protected WebElement NoteTextArea = driver.findElement(By.id("note-title"));
	
	
	
	//@FindBy (className = "btn save-button")
	protected WebElement Buttons = driver.findElement(By.className("button-wrapper"));
	
	protected List<WebElement> SaveButton = Buttons.findElements(By.xpath("//button[contains(@class,'btn')]"));
	
	protected WebElement SaveNoteButton = driver.findElements((By.xpath("//*[contains(@class, 'btn save-button')]"))).get(0);
	@FindBy(className = "vs-popup")
	protected WebElement AddNotePopUp;
	
	@FindBy(xpath = "(//div[@class='training-info'])[2]")
	protected  WebElement TrainigLoadCard  ;//= driver.findElement(By.xpath("//div[contains(@class, ‘alert_new_dl’)]"));//input[contains(@id, ‘subscribe’)]));
	
	@FindBy(className = "insights-notes")
	protected WebElement InsightNotes;
	
	@FindBy(className = "training-info")
	protected WebElement duriation;// = driver.findElement(By.className("training-info"));//.findElement(By.className("data"));
	
	@FindAll(@FindBy(className = "training-info"))
	protected List<WebElement> TrainingInofs;
								
	//@FindBy(xpath ="//div[@class='graph ecg-graph']//button[@name='button']")
	protected List<WebElement> ECGInfoButtons = driver.findElements(By.name("button")) ;
	
//	protected WebElement CSVFileLink = ;

	protected WebElement DisclaimerLink = driver.findElement(By.className("disclaimer-style"));
	
	protected WebElement CaliperTool = driver.findElement(By.className("caliper-button"));
	
	//protected WebElement ClickonWorkout = driver.findElement(By.className("w-title"));
	
	//protected WebElement KCAL = driver.findElement(By.id("wd-duration-value"));
	//protected WebElement TrainingLoad =driver.findElement(By.id("wd-training-load-value"));
	protected WebElement HRValue = driver.findElement(By.id("wd-heart-rate-value"));
	protected WebElement BRValue = driver.findElement(By.id("wd-breathing-rate-value"));
	//protected WebElement HrStValue = driver.findElement(By.id("wd-heart-strain-value"));
	//protected WebElement HRVValue = driver.findElement(By.id("wd-hrv-value"));
	

	
	protected WebElement AvgTab = driver.findElement(By.id("wd-avg-tab"));
	protected WebElement MaxTab = driver.findElement(By.id("wd-max-tab"));
	protected WebElement MinTab = driver.findElement(By.id("wd-min-tab"));
	
	protected WebElement TrainingTitle = driver.findElement(By.className("training-title"));
	protected WebElement TrainingTime = driver.findElement(By.className("training-time"));
	
	
	protected List <WebElement> HRZones = driver.findElements(By.className("hr-zone-item"));
	
	protected List <WebElement> HRZonePercentage = driver.findElements(By.xpath("//*[contains(@class,'hr-zone-item-right percent')]"));
	
	
}
