package com.appium.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class AndroidDriverBase{
	private static AndroidDriver<WebElement> driver;
	private static WebDriver drivers;

	public AndroidDriverBase(URL remoteAddress, Capabilities desiredCapabilities) {
		driver=new AndroidDriver<WebElement>(remoteAddress, desiredCapabilities);
	}
	
	 public static void snapshot(TakesScreenshot drivername, String filename) {
		      // this method will take screen shot ,require two parameters ,one is
		       // driver name, another is file name
	
	       String currentPath = System.getProperty("user.dir"); // get current work
		                                                              // folder
	       File scrFile = drivername.getScreenshotAs(OutputType.FILE);
	        // Now you can do whatever you need to do with it, for example copy
	        // somewhere
	         try {
	            System.out.println("save snapshot path is:" + currentPath + "/"
	                   + filename);
		            FileUtils
	                   .copyFile(scrFile, new File(currentPath + "\\" + filename));
	       } catch (IOException e) {
		             System.out.println("Can't save screenshot");
		            e.printStackTrace();
		         } finally {
		             System.out.println("screen shot finished, it's in " + currentPath
		                    + " folder");
		         }
		    }
	
	//元素是否存在
	public boolean isElementExist(By by){
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	//捕捉toast
	public static WebElement waitForElement(By by, int timeout, WebDriver driver) {
	    WebDriverWait wait = new WebDriverWait(driver, timeout);
	    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
       return element;
	  }

	//判断是否按好APP
	public boolean isAppInstalled(String by){
		try{
			driver.isAppInstalled(by);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//在指定超时时间内元素是否存在，如存在则立即返回结果，如不存在则在超时后返回结果
	public boolean isElementExist(By by,int timeout){
		try {
			//super.findElement(by);
			new WebDriverWait(driver,timeout).until(ExpectedConditions.presenceOfElementLocated(by));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	//判断是否锁屏
	public boolean isLocked(){
		try {
			driver.isLocked();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	//硬件输入方法
	public void sendKeyEvent(int id){
		driver.sendKeyEvent(id);
	}
	//滑动到指定
	public void scrollToExact(String by){//scrollotExact
		driver.scrollToExact(by); 
	}
	//九宫格解
	public static Point[] getGesturePoints(WebElement lock_view) {

		int start_x = lock_view.getLocation().x;
		int start_y = lock_view.getLocation().y;

		int viewWidth = lock_view.getSize().width;
		int viewHeight = lock_view.getSize().height;

		Point[] centerCxCy = new Point[9];

		int w = viewWidth / 3;
		int h = viewHeight / 3;
		for (int i = 0; i < 3; i++) {
		for (int j = 0; j < 3; j++) {
		Point p = new Point((int) ((i * w) + w / 2 + start_x),
		(int) ((j * h) + h / 2 + start_y));
		centerCxCy[j * 3 + i] = p;
		}
		}

		return centerCxCy;
		}
	
	//滑动解锁
	public static void HandGesture(Point[] points) {
		TouchAction gesture = null;
		if (points.length > 0) {
		gesture = new TouchAction(driver).press(points[0].x,points[0].y);

		try {
		gesture.waitAction(100);
		} catch (Exception e) {
		e.printStackTrace();
		}

		}

		for (int i = 1; i < points.length; i++) {
		Point p = points[i];
		gesture.moveTo(p.x, p.y);

		try {
		gesture.waitAction(100);
		} catch (Exception e) {
		e.printStackTrace();
		}

		}
		gesture.release();
		gesture.perform();

		}
	//向左滑动
	public void swipeToLight(int duration){
		int startx=this.appScreen()[0]*4/5;
		int endx=this.appScreen()[0]*1/5;
		int y=this.appScreen()[1]*1/2;
		try {
			driver.swipe(startx,y,endx,y,duration);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//向右滑动
	public void swipeToRight(int duration){
		int startx=this.appScreen()[0]*1/5;
		int endx=this.appScreen()[0]*4/5;
		int y=this.appScreen()[1]*1/2;
		try {
			driver.swipe(startx,y,endx,y,duration);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//向上滑动
	public void swipeToUp(int duration){
		int starty=this.appScreen()[0]*4/5;
		int endy=this.appScreen()[0]*1/5;
		int x=this.appScreen()[0]*1/2;
		try {
			driver.swipe(x,starty,x,endy,duration);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	 /**
     * 单个手指，点击屏幕某个控件位置(左上，左下，右上，右下，中间)
     */
    public enum Location {
        UPLEFT, LOWLEFT, UPRIGHT, LOWRIGHT, CENTRE
    }
    /**
     * 点击控件某个地方
     *
     * @param step
     * @param by
     * @param location
     */
    public void clickControl( By by, Location location) {

        // 获取控件开始位置的坐标轴
        Point start = this.findElement( by).getLocation();
        int startX = start.x;
        int startY = start.y;
        // 获取控件坐标轴差
        Dimension q = this.findElement(by).getSize();
        int x = q.getWidth();
        int y = q.getHeight();
        // 计算出控件结束坐标
        int endX = x + startX;
        int endY = y + startY;

        switch (location) {
            // 左上 点击
            case UPLEFT:
                driver.tap(1, startX + 10, startY + 10, 100);
              
                break;
            // 右上 点击
            case UPRIGHT:
                driver.tap(1, endX - 10, startY + 10, 100);
              
                break;
            // 左下 点击
            case LOWLEFT:
                driver.tap(1, startX + 10, endY - 10, 100);
             
                break;
            // 右下 点击
            case LOWRIGHT:
                driver.tap(1, endX - 10, endY - 10, 100);
            
                break;
            // 中间 点击
            case CENTRE:
                driver.tap(1, (endX + startX) / 2, (endY + startY) / 2, 100);
             
                break;
        }
    }
	//向下滑动
	public void swipeToDown(int duration){
		int starty=this.appScreen()[1]*1/5;
		//System.out.println(starty);
		int endy=this.appScreen()[1]*4/5;
		int x=this.appScreen()[0]*1/2;
		try {
			driver.swipe(x,starty,x,endy,duration);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	  
	//滑动方法，通过参数实现各方向滑动
	public void swipe(String direction,int duration){
		switch(direction){
		case "Up":
			this.swipeToUp(duration);
			break;
		case "Down":
		    this.swipeToDown(duration);
		    break;
		case "Light":
			this.swipeToLight(duration);
			break;
		case "Right":
			this.swipeToRight(duration);
			break;
		default:
			break;
		}
	}
	//获取应用占屏幕大小
	public int[] appScreen(){
		int width=driver.manage().window().getSize().getWidth();
		int heightScreen=driver.manage().window().getSize().getHeight();
		int[] appWidthAndHight={width,heightScreen};
		return appWidthAndHight;
		}
	//在元素上滑动
	public void swipeOnElement(WebElement element,String direction,int duration){
		//x,y分别为元素的起始坐标点
		int x=element.getLocation().getX();//获取该元素起始x值
		int y=element.getLocation().getY();//获取该元素起始y值
		int elementWidth=element.getSize().getWidth();
		int elementHight=element.getSize().getHeight();
		switch (direction) {
		case "Up":
			int startx=x+elementWidth/2;
			int starty=y+elementHight*4/5;
			int endy=y+elementHight*1/5;
			driver.swipe(startx,starty,startx,endy,duration);
			break;
		case "Down":
			startx=x+elementWidth/2;
			starty=y+elementHight*1/5;
			endy=y+elementHight*4/5;
			driver.swipe(startx,starty,startx,endy,duration);
			break;
		case "Light":
			starty=x+elementHight/2;
			startx=y+elementWidth*4/5;
			int endx=y+elementWidth*1/5;
			driver.swipe(startx,starty,endx,starty,duration);
			break;
		case "Right":
			starty=x+elementHight/2;
			startx=y+elementWidth*1/5;
			endx=y+elementWidth*4/5;
			driver.swipe(startx,starty,endx,starty,duration);
			break;
		default:
			break;
		}
	}
	/**
     * 控制滑动方向
     */
	 /**
     * 控件内上下滑动
     * @param by      控件定位方式
     * @param heading 滑动方向 UP  DOWN
     */
	 public enum Heading {
	        UP, DOWN
	    }
	 public void swipeControl(By by, Heading heading) {
	        // 获取控件开始位置的坐标轴
	        Point start = this.findElement(by).getLocation();
	        int startX = start.x;
	        int startY = start.y;

	        // 获取控件坐标轴差
	        Dimension q = this.findElement(by).getSize();
	        int x = q.getWidth();
	        int y = q.getHeight();
	        // 计算出控件结束坐标
	        int endX = x + startX;
	        int endY = y + startY;

	        // 计算中间点坐标
	        int centreX = (endX + startX) / 2;
	        int centreY = (endY + startY) / 2;

	        switch (heading) {
	            // 向上滑动
	            case UP:
	                driver.swipe(centreX, centreY + 30, centreX, centreY - 30, 500);
	                break;
	            // 向下滑动
	            case DOWN:
	                driver.swipe(centreX, centreY - 30, centreX, centreY + 30, 500);
	                break;
	        }
	       
	    }
	
	//在某方向上滑动直至期望的元素出现
	public void swipeUtilElementAppear(By by,String direction,int duration){
		boolean flag=true;
		while(flag){
			try {
				driver.findElement(by);
				flag=false;
			} catch (Exception e) {
				// TODO: handle exception
				this.swipe(direction,duration);
			}
		}	
	}
	public void sendKeyEven(int finger){
	     driver.sendKeyEvent(finger);
	     }
	
	//控件ID定位
	public WebElement findElement(By by){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		WebElement element=driver.findElement(by);
		return element;
	}
	public List<WebElement> findElement(By by,int id,String keysToSend){
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		   List<WebElement> elements = driver.findElements(by);  
		   elements.get(id).sendKeys();;
	    	return elements;
		}
//关于没有name，没有ID的元素的定位
    public List<WebElement> getElementsByClassAndIndex(String classname,int index){
    	List<WebElement> lis =null;
    	lis = driver.findElementsByAndroidUIAutomator("new UiSelector().className("+classname+").index("+index+")");
    	return lis;
    	}
    //同一个页面有10个ImageView对象，而其中index为4的有5个，而这时我们发现我们的目标元素的是clickable的。然后review页面发现，同时满足上述条件的只有2个
    public List<WebElement> getElementsByClassAndIndexAndClickable(String classname,int index){
    	List<WebElement> lis =null;
    	lis = driver.findElementsByAndroidUIAutomator("new UiSelector().className("+classname+").index("+index+").clickable(true)");
    	return lis;
    	}
    //清除输入框默认数据方法
    public void clear(WebElement el){
    	el.click(); //选中输入框
    	driver.sendKeyEvent(123);//将光标移到最后
    	String txt = el.getText(); //获取字符串长度
    	for(int i=0;i<txt.length();i++){
    	driver.sendKeyEvent(67);//一个个的删除。。。。。
    	}
    }
    
    //用于只有list的方法
    public WebElement getElementByIndex(int index){
    	return driver.findElementByAndroidUIAutomator("new UiSelector().index("+index+")");
    	}
	public List<WebElement> findElements(By by,int id){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
		List<WebElement> element=driver.findElements(by);
		element.get(id).click();
		return element;
	}
	//跨APP
	public void startActivity (String app,String Activity){
		try {
		driver.startActivity(app, Activity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//安装APP 
	public void installApp(String app){
		//app路径
		try {
		driver.installApp(app);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//卸载APP
	public void removeApp(String app){
		try {
		driver.removeApp(app);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//滚动列表
	public void scrollTo(String id){
		try {
			driver.scrollTo(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//关闭当前应用
	public void closeApp(){
		try {
			driver.closeApp();
			}catch(Exception e){
				e.printStackTrace();
		}
	}
	//启动当前应用
	public void launchApp(){
		try {
		driver.launchApp();
		}catch(Exception e){
			e.printStackTrace();
	}
	}
	//关闭后立即再次启动 
	public void runAppInBackground(int seconds){
		try {
		driver.runAppInBackground(seconds);
		}catch(Exception e){
			e.printStackTrace();
	}
	}
	//锁屏
	public void lockScreen(int id){
		try {
			driver.lockScreen(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//打开通知栏
	public void openNotifications(){
		driver.openNotifications();
	}
	public void quit(){
			driver.quit();
		}
	
	public void swipe(int startx,int starty,int endx,int endy,int duration){
		driver.swipe(startx, starty, endx, endy, duration);
	} 
	public void tab(int fingers,WebElement element,int duration){
		driver.tap(fingers, element, duration);
	}
	//点击某元素
	public void tab(WebElement element){
		try {
			TouchAction ta=new TouchAction(driver);
			ta.tap(element);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//点击某坐标
	public void tab(int x,int y){
		try {
			TouchAction ta=new TouchAction(driver);
			ta.tap(x,y);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//在某元素上长按
	public void longPress(By by){
		try {
			WebElement element=driver.findElement(by);
			TouchAction ta=new TouchAction(driver);
			ta.longPress(element).release().perform();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	//在坐标点长按
	public void longPress(int x,int y){
		try {
			TouchAction ta=new TouchAction(driver);
			ta.longPress(x,y).release().perform();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//判断是否到预期页面
	public String currentActivity(){
	  return driver.currentActivity();   
	}
	//判断是否预期文字
	public String getPageSouce(){
		return driver.getPageSource();
	}
	public void wait(int timeout){
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
    public void ta(){
    	
    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
                String dateString = formatter.format(new Date());
			FileUtils.copyFile(scrFile, new File("D:\\Learning\\"+dateString+".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	//遍历
	public void listfindElements(By by){
	List<WebElement> label=driver.findElements(by);
	for(WebElement l:label){
	System.out.println(l.getText());
	}

  
	}}

