package wechat;

import java.io.File;
import java.net.URL;


import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class asdw {
	private  AppiumDriver driver;
	@BeforeTest
public  void SetUp()throws Exception{
		  

	DesiredCapabilities Capabilities =new DesiredCapabilities();
	
	//Appium 基础设置
	//获取APP
//	Capabilities.setCapability("app", app.getAbsolutePath());
	Capabilities.setCapability(CapabilityType.BROWSER_NAME, "");//测APP时可以不要
	Capabilities.setCapability("platformName", "Android");
	Capabilities.setCapability("platformVersion","4.4");
	Capabilities.setCapability("deviceName","1f83c26a");//可以谁便填
	//APP最重要的
	Capabilities.setCapability("appPackage","com.tencent.mm");
	 Capabilities.setCapability("appActivity",".ui.LauncherUI");
	 //支持中文输入
	 Capabilities.setCapability("unicodeKeyboard","True");
	 //重置输入法为系统默认
	 Capabilities.setCapability("resetKeyboard","True");
	 //安装APK是不对APK进行重签名，很有必要，有些APK重签名后不能正常使用
	 Capabilities.setCapability("noSign","True");
	 //获取appium服务器
	 try{
	 driver =new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), Capabilities);
	 
	 }catch(Exception e)
	 {
		 e.printStackTrace();
	 }
}
@AfterTest
public void tearDown()  throws Exception{
	
	driver.quit();
}
@Test
public void Wechat(){
	
	try{
		
		WebDriverWait wait = new WebDriverWait(driver,10);  
        wait.until(new ExpectedCondition<WebElement>(){  
            @Override  
            public WebElement apply(WebDriver d) {  
                return d.findElement(By.name("登录")); 
            }}).click();  
	
	}catch(Exception e){
		e.printStackTrace();
		//driver.getScreenshotAs(OutputType.FILE);
		
		
	}finally{
		
	}
	WebElement element = driver.findElementByName("登录");
	

	/*
	boolean el = false;
	WebElement element = null;
	while(!el){
		
		if(driver.findElementByName("登录")){
			element.click();
			el = true;
			}
		WebElement element = driver.findElementByName("登录");
	}
	*/
	
	/*try
	// WebElement el = driver.findElementByName("登录");
	 boolean el = false;
	 int i=0;
	while(i++<=100){
		if (el=driver.findElementByName("登录").equals(false)){
		 continue;
		 }
		System.out.print(i+",");
		
	}
	
	WebElement element =  driver.findElementByName("登录");
			element.click();
	*/
	driver.findElementByName("你的手机号码").sendKeys("17717518326");
	driver.findElementById("com.tencent.mm:id/eu").sendKeys("qwerty");
	driver.findElementByName("登录").click();
	
	
}
private void assertArrayEquals() {
	// TODO Auto-generated method stub
	
}

}
