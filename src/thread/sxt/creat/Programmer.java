package thread.sxt.creat;

public class Programmer implements Runnable {
	@Override
	public void run() {
         for(int i=0;i<1000;i++) {
        	 System.out.println("__________编程———————");	 
         }
         
	}     
}
