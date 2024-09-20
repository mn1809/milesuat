package com.miles.Utilities;

 
public class FXParameters 
{
	
	
    String HRValue;
    String HRUnit;
    String duriation;
    String HRV;
    
    //getter and setter methods
 
    
    
    /*
     * Sets Value to all the parameters
     */
    public FXParameters(String HRValue,String HRUnit , String dur,String HRV )
    {
    	this.duriation = dur;
    	this.HRValue = HRValue;
    	this.HRUnit = HRUnit;
    	this.HRV = HRV;
    }
    
    
    /*
     * 
     * Getters 
     */
  
    public String getHRValue() 
    {
        return HRValue;
    }
    
    public String getHRUnit()
    {
    	return HRUnit;
    }
 
    public String getDuriation() 
    {
        return duriation;
    }
 
    public String getHRV()
    {
		return HRV;
    	
    }
 
}





 