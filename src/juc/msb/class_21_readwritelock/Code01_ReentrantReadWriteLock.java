package juc.msb.class_21_readwritelock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Code01_ReentrantReadWriteLock {
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {
        new Thread(() -> {
            readLock.lock();
            try {
                System.out.println("sub thread start!");
                TimeUnit.SECONDS.sleep(20);
                System.out.println("sub thread end!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                readLock.unlock();
            }
        }).start();

        try {
            Thread.sleep(1000);
            writeLock.lock();
            System.out.println("main thread start!");
            Thread.sleep(5000);
            System.out.println("main thread end!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            writeLock.unlock();
        }



    }

}
