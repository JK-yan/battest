package battest;

import java.io.IOException;

public class testbat {
 
	    public void runbat(String timeFortmat) {
	        String cmd = "cmd /c start C:/log/"+timeFortmat+".bat";

	        try {
	            Process ps = Runtime.getRuntime().exec(cmd);
	           System.out.println(ps.getInputStream());
	        } catch(IOException ioe) {
	            ioe.printStackTrace();
	        }
	    }
	    public void closebat() {
	        //String cmd = "cmd /c start C:/log/"+timeFortmat+".bat";

	    	try {  
                //关闭bat  
                Process ps = Runtime.getRuntime().exec("cmd /k start C:/log/close.bat");  
                ps.waitFor();  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       //String cmd="cmd /c start D:/ScheduleRun/data/"+timeFortmat+".bat" ;
		testbat test1 = new testbat();
	test1.runbat("logcat");
	test1.closebat();
	
	}
	 
}
