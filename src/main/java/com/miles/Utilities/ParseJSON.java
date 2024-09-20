package com.miles.Utilities;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
 
/*
 * @Author : Manoj
 */

public class ParseJSON 
{

	String JsonFilePath = "./resources/JsonFile/ActivityParameterData.json";
	
	JsonObject Fxobject ;
	JsonObject FxParams ;
	JsonArray derivedParameters ;
	JsonObject Dervied;
	List<String> ActivityInfo = new ArrayList<String>();
	
	
	@SuppressWarnings("deprecation")
	private void ReadJSONFileFor(String stats) throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		Fxobject = (JsonObject) new JsonParser().parse(new FileReader(JsonFilePath));
		FxParams = (JsonObject) Fxobject.get("FxParams");
		
		switch (stats) 
		
		{ 
		
		case "24Hrs" : 

			derivedParameters = (JsonArray) FxParams.get("24Hrs");
			Dervied = derivedParameters.get(0).getAsJsonObject();
			break;
		
		case "SleepActivity" : 
			
			derivedParameters = (JsonArray) FxParams.get("SleepActivity");
			Dervied = derivedParameters.get(0).getAsJsonObject();
			
			break;
		
		case "RunActivity" :
			
			derivedParameters = (JsonArray) FxParams.get("RunActivity");
			Dervied = derivedParameters.get(0).getAsJsonObject();
			break;
			
		}
		
		
	}
	
	@SuppressWarnings("deprecation")
	public List<String> getActivityDetails() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		Fxobject = (JsonObject) new JsonParser().parse(new FileReader(JsonFilePath));
		FxParams = (JsonObject) Fxobject.get("FxParams");
		ActivityInfo.add(0,FxParams.get("Activity_Title").getAsString());
		ActivityInfo.add(1,FxParams.get("Duration").getAsString());
		ActivityInfo.add(2,FxParams.get("Training Load").getAsString());
		
		return ActivityInfo ;
	}
	
	public HashMap<String, String> getDetails(String derivedData) throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		HashMap<String, String> Details = new HashMap<>();
		ReadJSONFileFor(derivedData);
		Details.put("Activity Title",Dervied.get("Activity_Title").getAsString()); 
		Details.put("Duration ",Dervied.get("Duration").getAsString()); 
		Details.put("TrainingLoad ",Dervied.get("TrainingLoad").getAsString()); 
		
		Details.put("MaxHR ",Dervied.get("MaxHR").getAsString());
		Details.put("AvgHR ",Dervied.get("AvgHR").getAsString());
		Details.put("MinHR ",Dervied.get("MinHR").getAsString());
		
		Details.put("MaxBR ",Dervied.get("MaxBR").getAsString());
		Details.put("AvgBR ",Dervied.get("AvgBR").getAsString());
		Details.put("MinBR ",Dervied.get("MinBR").getAsString());
		
		
		Details.put("MaxHRV ",Dervied.get("MaxHRV").getAsString());
		Details.put("AvgHRV ",Dervied.get("AvgHRV").getAsString());
		Details.put("MinHRV ",Dervied.get("MinHRV").getAsString());
		
		Details.put("MaxBodyShock ",Dervied.get("MaxBodyShock").getAsString());
		Details.put("AvgBodyShock ",Dervied.get("AvgBodyShock").getAsString());
		Details.put("MinBodyShock ",Dervied.get("MinBodyShock").getAsString());
		
		Details.put("MaxCadence ",Dervied.get("MaxCadence").getAsString());
		Details.put("AvgCadence ",Dervied.get("AvgCadence").getAsString());
		Details.put("MinCadence ",Dervied.get("MinCadence").getAsString());
		
		
		
		return Details;
	}
	
	public String getHR_Max() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Max");
		return Dervied.get("LastName").getAsString(); 
	}
	
	public String getBreathRate_Max() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Max");
		return Dervied.get("Email").getAsString(); 
	}
	
	public String getStrain_Max() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Max");
		return Dervied.get("Strain").getAsString(); 
	}
	
	public String getHRV_Max() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Max");
		return Dervied.get("HRV").getAsString(); 
	}
	public String getBodyShock_Max() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Max");
		return Dervied.get("BodyShock").getAsString(); 
	}
	
	public String getStepCad_Max() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Max");
		return Dervied.get("StepCad").getAsString(); 
	}
	
	
	public String getHR_Avg() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Avg");
		return Dervied.get("HR").getAsString(); 
	}
	
	public String getBreathRate_Avg() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Avg");
		return  Dervied.get("BR").getAsString(); 
	}
	
	public String getStrain_Avg() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Avg");
		return Dervied.get("Strain").getAsString(); 
	}
	
	public String getHRV_Avg() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Avg");
		return Dervied.get("HRV").getAsString(); 
	}
	public String getBodyShock_Avg() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Avg");
		return Dervied.get("BodyShock").getAsString(); 
	}
	
	public String getStepCad_Avg() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Avg");
		return Dervied.get("StepCad").getAsString(); 
	}
	
	public String getHR_Min() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Min");
		return Dervied.get("HR").getAsString(); 
	}
	
	public String getBreathRate_Min() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Min");
		return  Dervied.get("BR").getAsString(); 
	}
	
	public String getStrain_Min() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Min");
		return Dervied.get("Strain").getAsString(); 
	}
	
	public String getHRV_Min() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Min");
		return Dervied.get("HRV").getAsString(); 
	}
	public String getBodyShock_Min() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Min");
		return Dervied.get("BodyShock").getAsString(); 
	}
	
	public String getStepCad_Min() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		ReadJSONFileFor("Min");
		return Dervied.get("StepCad").getAsString(); 
	}
	
	
}



