package com.miles.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v102.network.Network;

public class MilesAPI 
{	
	public static String ECGGenerateAPI = "https://api.fourthfrontier.com/workout/generate-ecg-pdf";
	
	public static String ECGGenrateAPI_SL = "https://dashapi.frontierxapp.com/generate_ecg_pdf?";
	public static String StatusOK = "OK";
	
	/*
	 * Method will access the Network Console Logs 
	 * returns 
	 * Response URL and
	 * Response Text (Ex : OK) 
	 */
	public static List<String> GetAPIResponse(WebDriver driver)
	{
		
		List<String> Response = new ArrayList<String> (); 
		
		
		DevTools devTool = ((ChromeDriver) driver).getDevTools();

	    devTool.createSession();

	    devTool.send(Network.enable
	    		(
	    		Optional.empty(),
	    		Optional.empty(),
	    		Optional.empty())
	    		);

	    devTool.addListener(Network.responseReceived(),
	    		responseReceieved -> 
	    {		
	          Response.add(responseReceieved.getResponse().getUrl());
	          Response.add(responseReceieved.getResponse().getStatusText());
	          return ;
	     });
		return Response;
	}
	
}
