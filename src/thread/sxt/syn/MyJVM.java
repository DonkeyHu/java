package thread.sxt.syn;

/**
 * 单例设计模式：饿汉式
 * 类在使用时才被加载，这该如何理解？//哈哈，类加载的初始化的主动引用问题
 * 延缓加载时间有何作用
 * @author 东东
 *
 */
public class MyJVM {
	private static MyJVM instance = new MyJVM();

	private MyJVM() {

	}

	public static MyJVM getInstance() {
		return instance;
	}
}

class MyJVM2 {
	private static class JvmHolder {
		private static MyJVM2 instance = new MyJVM2();
	}
	private MyJVM2() {

	}

	public static MyJVM2 getInstance() {
		return JvmHolder.instance;
	}
}
