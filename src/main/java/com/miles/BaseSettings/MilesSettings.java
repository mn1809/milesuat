package com.miles.BaseSettings;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import com.miles.Utilities.FxEnums;
import com.miles.Utilities.MilesUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class MilesSettings
{
	protected WebDriver driver ;
	protected static String StageURL = "https://usp-uat.mileseducation.com/web/login";

	protected static String ProdURL = "https://uspathway.mileseducation.com/web/login";
	
	protected static String Environment = MilesUtilities.getOperatingSystemSystemUtils();
	static ChromeOptions options = new ChromeOptions();
	
	/*
	 * This Logic will reads the OS , 
	 * if Windows specific to win chrome driver will launch 
	 * else Chrome driver comptible to Ubuntu will launch
	 */
	static 
	{
		if (Environment.contains("Win"))
		{
			//String chromeKey = "webdriver.chrome.driver";
			//String chromeDriverPath = "./drivers/chromedriver.exe";
			//System.setProperty(chromeKey, chromeDriverPath);
			//System.out.println("Launching Chrome in Environment : " +MilesUtilities.getOperatingSystemSystemUtils());
		}
		else
		{
			String chromeKey = "webdriver.chrome.driver";
			String chromeDriverPath = "/usr/lib/chromium-browser/chromedriver";
			System.setProperty(chromeKey, chromeDriverPath);
			//System.out.println("Launching Chrome in Environment : " +MilesUtilities.getOperatingSystemSystemUtils());
		}
		
	}
	
	
	//@BeforeSuite
	public void VerifyChromePathInEc2() throws IOException
	{
		getChromeVersion();
		
		if (Environment.contains("Win"))
		{
			System.out.println("Running in Windows Environment ");
		}
		
		else
		{
		
		//String chromeDriverPath = "/usr/bin/chromedriver"; ///usr/lib/chromium-browser
		String chromeDriverPath = "/usr/lib/chromium-browser/chromedriver";
		File ChromeDriver = new File(chromeDriverPath);
		System.out.println("Does file exists ? " + ChromeDriver.exists());
	    System.out.println("Check whether file is executable : " + ChromeDriver.canExecute());
	    System.out.println("Check whether file is readable : " + ChromeDriver.canRead());
	    
	    if ( ChromeDriver.exists() == true && ChromeDriver.canExecute() == true && ChromeDriver.canRead() == true )
	    {
	    	System.out.println("All set to launch Automation scripts in Unix/Ubuntu");
	    }
	    else
	    {
	    	System.out.println("Chrome Driver has some issues");
	    }
	}
	
	}
	
	
public static void getChromeVersion() throws IOException
{
        
        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec("reg query " + "HKEY_CURRENT_USER\\Software\\Google\\Chrome\\BLBeacon " +  "/v version");
        BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(proc.getErrorStream()));

            // Read the output from the command
            System.out.println("Installed Chrome Version is :\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) 
            {
                System.out.println(s);
            }

            // Read any errors from the attempted command
            System.out.println("Standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null)
            {
                System.out.println(s);
            }
    }
	
	
	
	public static long justSleepFor(long seconds)
	{
		
		long millisecond = seconds * 1000 ;
		try {
			Thread.sleep(millisecond);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seconds;	
	}
	/*
	 * This Method will decide if the script should run in Win or Linux or Stage or
	 * Production based on the Parameter Passed in XML file
	 * If in XML we pass Stage , Scripts will run in Stage , but before that we also check if script is running in Windows environment or ASW EC2
	 * Based on Execution Environment , we initialize ChromeDriver
	 */
	
	//@SuppressWarnings("deprecation")
	public static WebDriver DecideEnvironment(String env)
	{
		
		WebDriver driver = null;
	    
	    if (env.equalsIgnoreCase("stage"))
	    {				
	    	if (Environment.contains("Win"))
			{
	    		options.addArguments("--remote-allow-origins=*");
	    		WebDriverManager.chromedriver().setup(); // Automating Driver management :) 
				System.out.println("WebDriverManager will take care of Driver management from here");
				driver = new ChromeDriver(options);
				driver.manage().window().maximize();
				((JavascriptExecutor) driver).executeScript("window.resizeTo(2560,1440);"); // Set a custom size
				driver.get(MilesUtilities.GetURLs(FxEnums.URLs.ServerlessStage));
				//System.out.println("Launching Prod Fx Web Page in Win Env");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				
			}
			else
			{
				
				//System.out.println("Chrome options are being set for "+MilesUtilities.getOperatingSystemSystemUtils()+" Environment");
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--no-sandbox");
				options.addArguments("start-maximized"); // open Browser in maximized mode
				options.addArguments("--window-size=2560,1440"); //1920x1080 
				options.addArguments("disable-infobars"); // disabling infobars
				options.addArguments("--disable-extensions"); // disabling extensions
				options.addArguments("--headless"); // to run in headless mode on ec2 os only
				//options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
				 // Bypass OS security model
				WebDriverManager.chromedriver().setup(); // Automating Driver management :) 
				System.out.println("WebDriver Manager will contine from here");
				driver = new ChromeDriver(options);
				driver.get(StageURL);
				System.out.println("Launching UAT Stage Miles Web Page in ubuntu ec2");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				
			}
		}
		
	    else if (env.equalsIgnoreCase("prod"))
		{
			
			if (Environment.contains("Win"))
			{
				options.addArguments("--remote-allow-origins=*");
	    		WebDriverManager.chromedriver().setup(); // Automating Driver management :) 
				System.out.println("WebDriverManager will take care of Driver management from here");
				driver = new ChromeDriver(options);
				driver.manage().window().maximize();
				((JavascriptExecutor) driver).executeScript("window.resizeTo(2560,1440);"); // Set a custom size
				driver.get(ProdURL);
				//System.out.println("Launching Prod Fx Web Page in Win Env");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			}
			else
			{
				options.addArguments("--no-sandbox"); 
				options.addArguments("start-maximized"); // open Browser in maximized mode
				options.addArguments("--window-size=2560,1440"); //1920x1080 2560,1440
				options.addArguments("--no-default-browser-check");
				options.addArguments("--no-first-run");
				options.addArguments("--disable-gpu");
				options.addArguments("disable-infobars"); // disabling infobars
				options.addArguments("--disable-extensions"); // disabling extensions
				options.addArguments("--headless");// to run in headless mode on ec2 os only
				options.addArguments("--remote-allow-origins=*");
				options.addArguments("--disable-dev-shm-usage");// overcome limited resource problems
				options.addArguments("--ignore-certificate-errors");
				options.addArguments("--allow-running-insecure-content");
				WebDriverManager.chromedriver().setup(); // Automating Driver management :) 
				driver = new ChromeDriver(options);
				driver.get(ProdURL);
				//System.out.println("Launching Prod Fx Web Page in ubuntu ec2");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				
			}
			
		}
		
		else 
		{
			Assert.fail("Environment not executable");
		}
		System.out.println(driver);
		return driver;
	}
	
	
	public enum UserLevel
	{
		  Admin
		  {
		      public String toString()
		      {
		          return "ADMIN";
		      }
		  },

		  Premium
		  {
		      public String toString()
		      {
		          return "PREMIUM";
		      }
		  },
		  
		  Doctor
		  {
		      public String toString()
		      {
		          return "DOCTOR";
		      }
		  },
		  
		  RegularUser
		  {
		      public String toString()
		      {
		          return "Ravi Kiran 07";
		      }
		  },
		  Trainer
		  {
		      public String toString()
		      {
		          return "TRAINER2";
		      }
		  },
		}
	
	
	public enum Locator
	{
		  ID
		  {
		      public String toString()
		      {
		          return "id";
		      }
		  },

		  ClassName
		  {
		      public String toString()
		      {
		          return "ClassName";
		      }
		  },
		  
		  Xpath
		  {
		      public String toString()
		      {
		          return "Xpath";
		      }
		  },
		  
		  RegularUser
		  {
		      public String toString()
		      {
		          return "Ravi Kiran 07";
		      }
		  },
		  Trainer
		  {
		      public String toString()
		      {
		          return "TRAINER2";
		      }
		  },
		}
	
	
	
}
