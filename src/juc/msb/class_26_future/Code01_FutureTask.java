package juc.msb.class_26_future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Code01_FutureTask {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        FutureTask<String> task = new FutureTask<>(() -> {
            System.out.println("Future task start to execute!");
            Thread.sleep(2000);
            System.out.println("Future task executed end!");
            return "OK";
        });

        ExecutorService service = Executors.newFixedThreadPool(3);

        service.execute(task);

        System.out.println("task is done? "+ task.isDone());
        Thread.sleep(1000);
        System.out.println("task is done? "+ task.isDone());
        Thread.sleep(1000);
        System.out.println("task is done? "+ task.isDone());
        System.out.println("task result is: "+ task.get());

        service.shutdown();
    }
}
