package com.camera.test;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.appium.utils.AndroidDriverBase;
import com.appium.utils.ScreenshotListener;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
@Listeners({ScreenshotListener.class})
public class CameraTest {
	private AndroidDriver<MobileElement> driver;
	
	public AndroidDriverBase AB;
	
  @Test
  public void f()   {
	   
	  
	    
	   
	 
	 
	  try{
	 for(int i=0;i<=15;i++){
		 MobileElement menu=driver.findElementByName("菜单按钮");
		
		 menu.click();
		 MobileElement px0=driver.findElementByName("照片尺寸");   
		 px0.click();
	       
		  List<MobileElement> px= driver.findElementsByClassName("android.widget.LinearLayout");
		  String name = px.get(i).getText();
		  MobileElement SVGA=driver.findElementByName("SVGA");
		if(px.get(i) != null){
			System.out.println("选择尺寸"+name+"拍摄照片");
			  
			  px.get(i).click();
			 
		}else{
			
			AB.swipeOnElement(SVGA, "Up", 10);
			 System.out.println("选择尺寸"+name+"拍摄照片");
			  px.get(i).click();
		}
		//obileElement a=  px.get(i);
		//driver.scrollTo(a);
		 
		  
	  driver.sendKeyEvent(4);
	  
	  
	  WebDriverWait wait = new WebDriverWait(driver, 60);
	    WebElement e= wait.until(new  ExpectedCondition<WebElement>() {
	            @Override
	            public WebElement apply(WebDriver d) {
	                return d.findElement(By.name("快门"));
	            }
	        });
	 
	  
	  e.click();
	 
	  
	  
	 }
	  }catch(Exception e){
		 AB.snapshot((TakesScreenshot) driver, "zhihu_showClose.png");
		  
		 e.printStackTrace();
		
	 }
	 
  }
  @BeforeTest
  public void beforeTest() throws Exception{
	  DesiredCapabilities Capabilities =new DesiredCapabilities();
  	Capabilities.setCapability("platformName", "Android");
  	Capabilities.setCapability("platformVersion","5.1");
  	Capabilities.setCapability("deviceName","1f83c26a");//可以谁便填
  	//APP最重要的
  	Capabilities.setCapability("appPackage","com.android.camera2");
  	 Capabilities.setCapability("appActivity","com.android.camera.CameraLauncher");
  	 try{
  		 driver =new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), Capabilities);
  		// driver.sendKeyEvent(4);
  		 System.out.println("连接成功");
  		
  		 }catch(Exception e)
  		 {
  			 e.printStackTrace();
  			 driver.quit();
  		 }
  }

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
