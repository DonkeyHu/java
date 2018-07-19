package thread.sxt.info;

public class InfoDemo02 {
      public static void main(String[] args) throws InterruptedException {
		MyThread m = new MyThread();
		Thread t = new Thread(m, "IT1");
		MyThread m2 = new MyThread();
		Thread t2 = new Thread(m2, "IT2");

		t.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MIN_PRIORITY);
		t.start();
		t2.start();
		Thread.sleep(2000);
		m.stop();
		m2.stop();

	}
}
