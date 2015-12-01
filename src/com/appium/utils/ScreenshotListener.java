package com.appium.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class ScreenshotListener extends TestListenerAdapter{
	public AndroidDriver driver;
@Override
public void onTestFailure(ITestResult tr){
	AppiumDriver driver=ScreenshotException.getDriverName();// getting driver instance
	// getting driver instancefrom TestApplication class
File location=new File("screenshots");// it will create screenshotsfolder in the project
String screenShotName = location.getAbsolutePath()+File.separator+tr.getMethod().getMethodName()+".png";
// Getting failure method name using ITestResult
File screenShot=driver.getScreenshotAs(OutputType.FILE);

try {
FileUtils.copyFile(screenShot,new File(screenShotName));

} catch (IOException e) {
e.printStackTrace();
}}}

