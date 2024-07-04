package juc.msb.class_25_threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Code01_Executors {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
    }

}
