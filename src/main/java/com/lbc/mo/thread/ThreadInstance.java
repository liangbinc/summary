package com.lbc.mo.thread;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadInstance {
    private ThreadInstance() {
    }

    static class ExtThread extends Thread {
        private final int id;

        ExtThread(Integer id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("Extends %s thread class do something", id));
        }
    }

    static class RunThread implements Runnable {
        private final int id;

        RunThread(Integer id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(String.format("implements %s runnable class do something", id));

        }
    }

    static class CallThread implements Callable {
        private final int id;

        CallThread(Integer id) {
            this.id = id;
        }

        @Override
        public Object call() throws Exception {

            return String.format("as a %s result", id);
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5,
                60L, TimeUnit.SECONDS, new LinkedBlockingQueue(100),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

        ArrayList<Future<String>> futures = Lists.newArrayList();
        IntStream.range(0, 3).forEach(i -> {
//            new ExtThread(i).start();
//            new Thread(new RunThread(i)).start();
            futures.add(threadPoolExecutor.submit(new CallThread(i)));

        });

        threadPoolExecutor.shutdown();
        futures.stream().forEach(future -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

    }
}
