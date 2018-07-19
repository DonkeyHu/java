package thread.sxt.status;

/**
 * 模拟网络延迟
 * @author 东东
 *
 */
public class SleepDemo02 {
	public static void main(String[] args) {
		Web12306 w = new Web12306();
		Thread t1 = new Thread(w, "路人甲");
		Thread t2 = new Thread(w, "黄牛乙");
		Thread t3 = new Thread(w, "工程师");
		t1.start();
		t2.start();
		t3.start();
	}
}

class Web12306 implements Runnable {
	private int num = 50;

	@Override
	public void run() {
		while (true) {
			if(num<=0){
				break;
			}
			try {
				Thread.sleep(500);//这里这知识点是：静态方法：类名.方法名
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "抢到票_" + num--);
		}
	}
}