package com.Miles.TestScripts;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.idealized.log.Log;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.miles.BaseSettings.MilesSettings;

import com.miles.PageLibRepo.HomePageLib;
import com.miles.PageLibRepo.LoginPageLib;
import com.miles.Utilities.MilesUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.devtools.DevTools;


public class LiveECG 
{	
	 WebDriver driver = null ;
	 
	 LogEntries logEntries ;
	 protected static String Environment = MilesUtilities.getOperatingSystemSystemUtils();
	 static LoggingPreferences logPrefs = new LoggingPreferences();
	 static ChromeOptions options = new ChromeOptions();
	 String ClassName = this.getClass().getSimpleName().toString();
	 Matcher matcher;
	 Pattern pattern;
	 String LiveECG_Pkt = "(displaying packet no:|Recieved Packet No:)\\s(\\d+)";
	 
	 int FirstPKTWaitingTime = 20;
	 
	 static String StageLiveECGURL = "https://liveecg.frontierxs.com/1398/Ravikiran";
	 static String ProdLiveECGURL = "https://liveecg.frontierxapp.com/5398/Ravikiran";
	 String extractedPKTInfo;
	 
	@Parameters({ "enivironment" })
	 @BeforeMethod
    private void Initialize(String env)
    {
		
	 this.driver = DecideEnvironment(env);
	 
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
		 driver.quit();
	 }
	
	 
@BeforeClass
private void SetEvidenceDir()
{
	MilesUtilities.createDateBasedDirectory();
	
}
	
	@Test (description = "Purpose of this test Verify that live ECG will be running for 2 mins")
	public void VerifyLiveECG() throws Exception
	{		
		long startTime = System.currentTimeMillis();
		long endTime = startTime + 1 * 60 * 1000; 
		int RunTimeInSec ;
		
		
		for (RunTimeInSec = 1; System.currentTimeMillis() <=endTime; RunTimeInSec++) 
		{
			 System.out.println(RunTimeInSec+" Sec");
			 logEntries = driver.manage().logs().get(LogType.BROWSER);
			 
	        for (LogEntry entry : logEntries) 
	        {	
	        	
	        	pattern = Pattern.compile(LiveECG_Pkt);
	        	matcher = pattern.matcher(entry.getMessage());
	        	
	        	
	        	 if (matcher.find()) 
	        	 {
	        		 extractedPKTInfo = matcher.group();
	                // System.out.println("Packets Received on Sec" +i +" is "+countLines(extractedPKTInfo));
	                 System.out.println(extractedPKTInfo); // Output: "displaying packet no: " or "Received Packet No:"
	             } 
	        	 else
	             {
	        		 
	                 System.out.println("No Packets Received");
	                 
	             }
	        }
	        Thread.sleep(1000); // Simulating a pause of 1 sec
	        
	        if (RunTimeInSec==FirstPKTWaitingTime)
	        {
	        	Assert.assertEquals(extractedPKTInfo, matcher.group());
	        }
		}
		
		System.out.println("Live ECG test Ran for "+RunTimeInSec+" Seconds");
		
	}	
	
	
	
	
	
	static int countLines(String input) throws IOException 
	{
	    LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(input));
	    lineNumberReader.skip(Long.MAX_VALUE);
	    return lineNumberReader.getLineNumber();
	}
	
	
	
	
	public static WebDriver DecideEnvironment(String env)
	{
		
		WebDriver driver = null;
	    
	    if (env.equalsIgnoreCase("prod"))
	    {				
	    	if (Environment.contains("Win"))
			{
	    		//SetupWindows(driver , ProdLiveECGURL);
	    		
	    		
	    		logPrefs.enable(LogType.BROWSER, Level.INFO);
	    	    options.setCapability(ChromeOptions.LOGGING_PREFS, logPrefs);
	    		options.addArguments("--remote-allow-origins=*");
	    		WebDriverManager.chromedriver().setup(); // Automating Driver management :) 
	    		System.out.println("WebDriverManager will take care of Driver management from here");
	    		driver = new ChromeDriver(options);
	    		driver.manage().window().maximize();
	    		driver.get(ProdLiveECGURL);
	    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    		
				
			}
			else
			{	
				logPrefs.enable(LogType.BROWSER, Level.INFO);
		    	options.setCapability(ChromeOptions.LOGGING_PREFS, logPrefs);
				options.addArguments("--no-sandbox");
				options.addArguments("start-maximized"); // open Browser in maximized mode
				options.addArguments("disable-infobars"); // disabling infobars
				options.addArguments("--disable-extensions"); // disabling extensions
				options.addArguments("--headless"); // to run in headless mode on ec2 os only
				//options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
				 // Bypass OS security model
				WebDriverManager.chromedriver().setup(); // Automating Driver management :) 
				driver = new ChromeDriver(options);
				driver.get(ProdLiveECGURL);
				//System.out.println("Launching Prod Fx Web Page in ubuntu ec2");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
			}
		}
		
		else if (env.equalsIgnoreCase("stage"))
		{
			
			if (Environment.contains("Win"))
			{

				//SetupWindows(driver , StageLiveECGURL);
				
				logPrefs.enable(LogType.BROWSER, Level.INFO);
	    	    options.setCapability(ChromeOptions.LOGGING_PREFS, logPrefs);
	    		options.addArguments("--remote-allow-origins=*");
	    		WebDriverManager.chromedriver().setup(); // Automating Driver management :) 
	    		System.out.println("WebDriverManager will take care of Driver management from here");
	    		driver = new ChromeDriver(options);
	    		driver.manage().window().maximize();
	    		driver.get(StageLiveECGURL);
	    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    		
			}
			else
			{
				logPrefs.enable(LogType.BROWSER, Level.INFO);
		    	options.setCapability(ChromeOptions.LOGGING_PREFS, logPrefs);
				options.addArguments("--no-sandbox");
				options.addArguments("start-maximized"); // open Browser in maximized mode
				options.addArguments("disable-infobars"); // disabling infobars
				options.addArguments("--disable-extensions"); // disabling extensions
				options.addArguments("--headless"); // to run in headless mode on ec2 os only
				//options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
				 // Bypass OS security model
				WebDriverManager.chromedriver().setup(); // Automating Driver management :) 
				driver = new ChromeDriver(options);
				driver.get(StageLiveECGURL);
				//System.out.println("Launching Prod Fx Web Page in ubuntu ec2");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			}
		}
		
		else 
		{
			Assert.fail("Environment not executable");
		}
		
		return driver;
	}
	
	
}
	    

	

