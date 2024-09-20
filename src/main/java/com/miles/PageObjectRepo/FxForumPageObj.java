package com.miles.PageObjectRepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import com.miles.BaseSettings.MilesBasePage;

public class FxForumPageObj extends MilesBasePage
{

	public FxForumPageObj(WebDriver driver)
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
	

	//protected WebElement SaveAnnotation = AnnotationWindow.findElement(By.className("btn save-button apply"));
	
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
	
	@FindBy(xpath = "//button[@class='add-notes']")//button[@class='add-notes'])
	protected WebElement AddNotes ;
	
	@FindBy(id = "note-title")
	protected WebElement NoteTextArea ;
	
	//@FindBy (className = "btn save-button")
	protected WebElement SaveButton;


}
