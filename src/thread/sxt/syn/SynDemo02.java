package thread.sxt.syn;

/**
 * 单例设计模式：
 *  懒汉式：
 * @author 东东
 *
 */
public class SynDemo02 {
	public static void main(String[] args) {
//		Jvm jvm1=Jvm.getInstance();	
//		Jvm jvm2=Jvm.getInstance();
//		System.out.println(jvm1);
//		System.out.println(jvm2);
		ThreadJvm tj1=new ThreadJvm(100);
		ThreadJvm tj2=new ThreadJvm(500);
		tj1.setName("tj1");
		tj2.setName("tj2");
		tj1.start();
		tj2.start();
	}
}
class ThreadJvm extends Thread{
	private long time;
	public ThreadJvm(){
		
	}
	public ThreadJvm(long time){
		this.time=time;
	}

	@Override
	public void run() {
        System.out.println(Thread.currentThread().getName()+"创建--->"+Jvm.getInstance3(time));
	}
}
class Jvm{
	private static Jvm instance=null;
	private Jvm(){
		
	}

	/*
	 * double-checking,与下面方法有何不同？该如何理解？
	 * 你看看，大部分情况下，instance都是有对象的，所以不经过synchronize块，效率高啊！
	 */
	public static Jvm getInstance(long time) {
		if (null == instance) {
			//a,b
			synchronized (Jvm.class) {
				if (null == instance) {
					try {
						Thread.sleep(time);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					instance = new Jvm();
				}
			}
		}
		return instance;
	}
	/*
	 * 同步块，但效率低
	 */
	public static Jvm getInstance4(long time) {
		synchronized (Jvm.class) {
			if (null == instance) {
				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				instance = new Jvm();
			}
			return instance;
		}
	}
	/*
	 * 锁
	 */
	public static synchronized Jvm getInstance3(long time) {
		if(null==instance){
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			instance=new Jvm();
		}
		
		return instance;
	}
	/*
	 * 用来判断多线程时，就不能保证一个对象了
	 */
	public static Jvm getInstance2(long time) {
		if(null==instance){
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			instance=new Jvm();
		}
		
		return instance;
	}
	/*
	 * 下面是用来提供方法来访问该变量，变量没有对象则创建对象（这句话该如何理解？）；
	 * 一个静态变量--->去创建对象，为什么要这样做？
	 */
	public static Jvm getInstance1(){
		if(null==instance){
			instance=new Jvm();
		}
		
		return instance;
	}
}
