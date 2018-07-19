package thread.sxt.schedule;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo01 {
      public static void main(String[] args) {
		Timer timer=new Timer();
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				System.out.println("so easy....");
			}}, new Date(System.currentTimeMillis()+1000), 200);
	}
}
