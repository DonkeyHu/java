package juc.msb.class_02_synchronized;

public class Code06 implements Runnable{

    private /*volatile*/ int count = 100;

    // 对比打开注解看看
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        Code06 t = new Code06();
        for(int i=0; i<100; i++) {
            new Thread(t, "THREAD: " + i).start();
        }
    }
}
