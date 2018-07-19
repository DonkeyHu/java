package thread.sxt.info;

public class InfoDemo01 {
     public static void main(String[] args) throws InterruptedException {
		MyThread m=new MyThread();
		Thread t=new Thread(m,"IT");
		t.setName("hahaha");
		System.out.println(Thread.currentThread().getName());//main
		t.start();
		System.out.println("启动之后线程的状态："+t.isAlive());
		//Thread.sleep(200);
		m.stop();
		Thread.sleep(200);
		System.out.println("停止之后线程的状态："+t.isAlive());
	}
}
