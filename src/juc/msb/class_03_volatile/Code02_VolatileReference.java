package juc.msb.class_03_volatile;

import java.util.concurrent.TimeUnit;
/**
 * volatile 引用类型（包括数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 */
public class Code02_VolatileReference {

    boolean running = true;

    volatile static Code02_VolatileReference T = new Code02_VolatileReference();


    void m() {
        System.out.println("m start");
        while(running) {
			/*
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
        }
        System.out.println("m end!");
    }

    public static void main(String[] args) {
        new Thread(T::m, "t1").start();

        //lambda表达式 new Thread(new Runnable( run() {m()}

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        T.running = false;
    }

}
