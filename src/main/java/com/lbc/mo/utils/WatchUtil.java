package com.lbc.mo.utils;


public class WatchUtil {

    private WatchUtil() {
    }

    private static void doSomeThing() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        Stopwatch started = Stopwatch.createStarted();
//        WatchUtil.doSomeThing();
//        WatchUtil.doSomeThing();
//        long elapsed = started.elapsed(TimeUnit.SECONDS);
//        System.out.println(elapsed);
//        started.stop().start();
//        WatchUtil.doSomeThing();
//        WatchUtil.doSomeThing();
//        System.out.println(started.elapsed(TimeUnit.SECONDS));
        System.out.println(System.nanoTime());
    }
}
