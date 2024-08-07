package juc.msb.class_20_reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock用于替代synchronized
 * 由于m1锁定this,只有m1执行完毕的时候,m2才能执行
 * 这里是复习synchronized最原始的语义
 *
 * 使用reentrantlock可以完成同样的功能
 * 需要注意的是，必须要必须要必须要手动释放锁（重要的事情说三遍）
 * 使用syn锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 */
public class Code02_ReentrantLock {
    ReentrantLock lock = new ReentrantLock();

    public void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("m1 get the lock -> " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void m2() {
        try {
            lock.lock();
            System.out.println("m2 get the lock");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Code02_ReentrantLock obj = new Code02_ReentrantLock();
        new Thread(obj::m1).start();
        new Thread(obj::m2).start();
    }

}
