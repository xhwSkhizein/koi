package com.hongv.koi.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuhongwei5
 * @since 2018/12/7 18:19
 */
public class CompletableFutureTests {

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    private static AtomicInteger INC = new AtomicInteger(0);

    private static String sendMsg(int receiverId) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        } catch (InterruptedException e) {
            // ignore, wake early
        }
        int inc = INC.getAndIncrement();
        System.out.println("send done. " + inc);

        return "msg:" + inc + ";to:" + receiverId;

    }


    public static void main(String[] args) {
        String msg = "wola";
        CompletableFuture
                .supplyAsync(CompletableFutureTests::findReceiver)
                .thenApply(CompletableFutureTests::sendMsg)
                .thenAccept(result -> {
                    System.out.println(result);
                });

    }

    private static int findReceiver() {
        return ID_GENERATOR.getAndIncrement();
    }


}
