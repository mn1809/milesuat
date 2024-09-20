package com.miles.Utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.miles.Utilities.FxEnums.FxParms;
 
/*
 * @Author : RK
 */
public class JsonIO 
{
	Gson gson = new GsonBuilder()
	        .setLenient()
	        .create();
	 FXParameters fxParams ;
	 
	 String JsonFilePath = "D:\\JsonFile\\FxParameters.json";
	 
	public File jsonFile = new File(JsonFilePath);
	  
		
	/**
	 * We need to add all these parameters
	 * 
	 * HRValue,
		HRUnit,
		Duriation,
		CardicSt,
		CardicStUnit,
		HRV,
		HRVUnit,
		TrainingLoad,
		TrainingLoadUnit,
		BR,
		BRUnit,
		BodyShock,
		BodyShockUnit,
		StepCadence,
		StepCadenceUnit	
	 * @param fxp
	 * @return 
	 * @throws FileNotFoundException 
	 */
	
	
	
	/*
	 * 
	 * this methods parameters and units from json file
	 * 
	 */
	public String GetValueOf(FxParms fxp) throws FileNotFoundException
	{
		 BufferedReader br = new BufferedReader(
			     new FileReader(JsonFilePath));
			 
			    //convert the json string back to object
		  fxParams = gson.fromJson(br, FXParameters.class);
		
		  
		  
		  
		  
		  	switch(fxp)
		{
		
		case HRValue :
		{
			
			return fxParams.getHRValue();
			
		}
		case HRUnit:
		{
			return fxParams.getHRUnit();
		}
		
		
		case Duriation:
		{
			return fxParams.getDuriation();
		}
			
		case HRV:
		{
			return fxParams.getHRV();
		}
		
		 default: 
		 { // I strongly doubt this will ever get executed unless we have added enum and not added here.
		      throw new IllegalArgumentException("No Such Units Bro !! ");
		
		}
			
		}
		
	}
	
/*	
	public void SetJsonValue(String HRVal , String HRUnit , String duriation,String HRV) throws IOException
	{
		fxParams = new FXParameters(HRVal,HRUnit,duriation,HRV);
		
		String json = gson.toJson(fxParams);
		//write converted json data to a file
		
		//jsonFile.createNewFile();
			   FileWriter writer = new FileWriter(JsonFilePath);
			   writer.write(json);
			   writer.close();
	}
	
	
	*/
	
	
}