package juc.msb.class_01_thread;

import java.util.concurrent.TimeUnit;

public class Code01_WhatIsThread {

    public static class T1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1: "+i);
            }
        }
    }

    public static void main(String[] args) {
        new T1().start();
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Main: "+i);
        }
    }

}
