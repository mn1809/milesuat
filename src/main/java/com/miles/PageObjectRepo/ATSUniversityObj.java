package com.miles.PageObjectRepo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.miles.BaseSettings.MilesBasePage;



public class ATSUniversityObj extends MilesBasePage

{	
	
	   public ATSUniversityObj(WebDriver driver)
	   
	   {
		super(driver);
	
		// TODO Auto-generated constructor stub 
	   }

	   
	   protected WebElement ListView = driver.findElement(By.xpath("//*[contains(@class, 'btn btn-light o_switch_view o_list oi oi-view-list')]"));
	   
	   
	   protected WebElement UserName = driver.findElement(By.className("o_searchview_input"));
	  

	 
}
//Testing 123GIT//