package com.miles.Utilities;

import org.testng.annotations.DataProvider;

public class Data
{

    @DataProvider( name = "dp" )
    public static Object[][] createData1() 
    {
        return new Object[][] 
        {
                { 
                	"Iteration1", 
                	Integer.valueOf(36)
                },
                { 
                	"Iteration2", 
                	 Integer.valueOf(37)
                },
        };
    }

}
