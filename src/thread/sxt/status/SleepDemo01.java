package thread.sxt.status;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SleepDemo01 {
      public static void main(String[] args) throws InterruptedException {
		 Date endTime=new Date(System.currentTimeMillis()+10*1000);
		 long end=endTime.getTime();
		 while(true){
			 System.out.println(new SimpleDateFormat("mm:ss").format(endTime));
			 endTime=new Date(endTime.getTime()-1000);
			 Thread.sleep(1000);
			 if(end-endTime.getTime()>10000){
				 break;
			 }
		 }
		 SleepDemo01 s=new SleepDemo01();
		 s.test1();
	}
      /**
       * 倒计时
       * @throws InterruptedException
       */
      public void test1() throws InterruptedException{
    	  int  num=10;
		  while(true){
			  System.out.println(num--);
			  Thread.sleep(1000);
			  if(num<0){
				  break;
			  }
		  }
      }
}
