package juc.msb.class_02_synchronized;

public class Code05 {

    private static int count = 10;

    public synchronized static void m() { //这里等同于synchronized(FineCoarseLock.class)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void mm() {
        synchronized(Code05.class) { //考虑一下这里写synchronized(this)是否可以？
            count --;
        }
    }
}
