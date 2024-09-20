package com.miles.Utilities;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.internal.BaseTestMethod;
import org.testng.internal.TestResult;

import java.lang.reflect.Field;

/**
 * @author Ravikiran 
 */
public class TestNameListener extends TestListenerAdapter 
{

    private void setTestNameInXml(ITestResult testResult) 
    {
        try 
        {
            Field mMethod = TestResult.class.getDeclaredField("m_method");
            mMethod.setAccessible(true);
            mMethod.set(testResult, testResult.getMethod().clone());
            Field mMethodName = BaseTestMethod.class.getDeclaredField("m_methodName");
            mMethodName.setAccessible(true);
            mMethodName.set(testResult.getMethod(), testResult.getTestName());
        }
        catch (IllegalAccessException exp)
        {
            Reporter.log(exp.getLocalizedMessage(), true);
        } catch (NoSuchFieldException exp) 
        {
            Reporter.log(exp.getLocalizedMessage(), true);
        }
    }

    @Override
    public void onTestSuccess(ITestResult testResult)
    {
        setTestNameInXml(testResult);
        super.onTestSuccess(testResult);
    }

    @Override
    public void onTestFailure(ITestResult testResult) 
    {
        setTestNameInXml(testResult);
        super.onTestFailure(testResult);
    }

    @Override
    public void onTestSkipped(ITestResult testResult) 
    {
        setTestNameInXml(testResult);
        super.onTestSkipped(testResult);
    }

}