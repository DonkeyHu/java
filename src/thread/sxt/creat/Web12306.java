package thread.sxt.creat;

public class Web12306 implements Runnable{
     private int num=50;
	@Override
	public void run() {
		while(num>0){
         System.out.println(Thread.currentThread().getName()+"抢到票_"+num--);		
	}
		}
     public static void main(String[] args) {
		Web12306 w=new Web12306();
		Thread t1=new Thread(w,"路人甲");
		Thread t2=new Thread(w,"黄牛乙");
		Thread t3=new Thread(w,"工程师");
		t1.start();
		t2.start();
		t3.start();
	}
}
