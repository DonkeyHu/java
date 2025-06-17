package juc.msb.class_01_thread;

public class Code04_ThreadState {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread1: "+ this.getState());

            for(int i=0; i<10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new MyThread();
        System.out.println("MyThread2: "+ t.getState());
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("MyThread3: "+t.getState());

    }
}
