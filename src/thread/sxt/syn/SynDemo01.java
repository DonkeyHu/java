package thread.sxt.syn;



public class SynDemo01 {
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
	private int num = 10;
	private boolean flag = true;
	@Override
	public void run() {
		while (flag) {
           test4();
		}
	}
	//只锁定一个属性而不是对象
	public void test4() {
		synchronized ((Integer)num) {
			if (num <= 0) {
				flag = false;
				return;
			}
			try {
				Thread.sleep(500);// 这里这知识点是：静态方法：类名.方法名
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "抢到票_" + num--);
		}
	}
	//锁定范围过小
	public void test3() {
		synchronized (this) {
			if (num <= 0) {
				flag = false;
				return;
			}
		}
		try {
			Thread.sleep(500);// 这里这知识点是：静态方法：类名.方法名
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "抢到票_" + num--);
	}
	
	/**
	 * 同步块
	 */
	public void test2() {
		synchronized (this) {
			if (num <= 0) {
				flag = false;
				return;
			}
			try {
				Thread.sleep(500);// 这里这知识点是：静态方法：类名.方法名
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "抢到票_" + num--);
		}
	}
    /**
     * 同步方法
     */
	public synchronized void test1() {
		if (num <= 0) {
			flag=false;
			return;
		}
		try {
			Thread.sleep(500);// 这里这知识点是：静态方法：类名.方法名
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "抢到票_" + num--);

	}
	public  void test() {
		if (num <= 0) {
			flag=false;
			return;
		}
		try {
			Thread.sleep(500);//这哥们睡觉醒来不知道num的值早已经发生天翻地覆的差别了！
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "抢到票_" + num--);

	}
}