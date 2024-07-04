package juc.msb.class_22_semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Code01_Semaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("sub thread get the semaphore!");
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                semaphore.release();
                System.out.println("sub thread release the semaphore!");
            }
        }).start();

        try {
            TimeUnit.MICROSECONDS.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println("main thread start to get the semaphore!");
            semaphore.acquire();
            System.out.println("main thread get the semaphore!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            semaphore.release();
            System.out.println("main thread release the semaphore!");
        }
    }
}
