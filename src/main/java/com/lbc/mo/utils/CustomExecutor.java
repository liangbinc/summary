package com.lbc.mo.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.core.task.TaskDecorator;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.scheduling.concurrent.ExecutorConfigurationSupport;
import org.springframework.util.Assert;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureTask;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomExecutor extends ExecutorConfigurationSupport
        implements AsyncListenableTaskExecutor, SchedulingTaskExecutor {
    private static final Log LOG = LogFactory.getLog(CustomExecutor.class);

    private final Object poolSizeMonitor = new Object();
    private int corePoolSize = 1;
    private int maxPoolSize = 2147483647;
    private int keepAliveSeconds = 60;
    private int queueCapacity = 2147483647;
    private boolean allowCoreThreadTimeOut = false;
    private TaskDecorator taskDecorator;
    private ThreadPoolExecutor threadPoolExecutor;

    public CustomExecutor() {
    }

    private final Map<Runnable, Object> decoratedTaskMap =
            new ConcurrentReferenceHashMap<>(16, ConcurrentReferenceHashMap.ReferenceType.WEAK);

    public void setCorePoolSize(int corePoolSize) {
        Object var2 = this.poolSizeMonitor;
        synchronized (this.poolSizeMonitor) {
            this.corePoolSize = corePoolSize;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setCorePoolSize(corePoolSize);
            }

        }
    }

    public int getCorePoolSize() {
        Object var1 = this.poolSizeMonitor;
        synchronized (this.poolSizeMonitor) {
            return this.corePoolSize;
        }
    }

    public void setMaxPoolSize(int maxPoolSize) {
        Object var2 = this.poolSizeMonitor;
        synchronized (this.poolSizeMonitor) {
            this.maxPoolSize = maxPoolSize;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setMaximumPoolSize(maxPoolSize);
            }

        }
    }

    public int getMaxPoolSize() {
        Object var1 = this.poolSizeMonitor;
        synchronized (this.poolSizeMonitor) {
            return this.maxPoolSize;
        }
    }

    public void setKeepAliveSeconds(int keepAliveSeconds) {
        Object var2 = this.poolSizeMonitor;
        synchronized (this.poolSizeMonitor) {
            this.keepAliveSeconds = keepAliveSeconds;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setKeepAliveTime((long) keepAliveSeconds, TimeUnit.SECONDS);
            }

        }
    }

    public int getKeepAliveSeconds() {
        Object var1 = this.poolSizeMonitor;
        synchronized (this.poolSizeMonitor) {
            return this.keepAliveSeconds;
        }
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
    }

    public void setTaskDecorator(TaskDecorator taskDecorator) {
        this.taskDecorator = taskDecorator;
    }

    protected ExecutorService initializeExecutor(ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        BlockingQueue<Runnable> queue = this.createQueue(this.queueCapacity);
        ThreadPoolExecutor executor;
        if (this.taskDecorator != null) {
            executor = new ThreadPoolExecutor(this.corePoolSize, this.maxPoolSize, (long) this.keepAliveSeconds, TimeUnit.SECONDS, queue, threadFactory, rejectedExecutionHandler) {
                public void execute(Runnable command) {
                    Runnable decorated = taskDecorator.decorate(command);
                    if (decorated != command) {
                        decoratedTaskMap.put(decorated, command);
                    }
                    super.execute(decorated);
                }
            };
        } else {
            executor = new ThreadPoolExecutor(this.corePoolSize, this.maxPoolSize, (long) this.keepAliveSeconds, TimeUnit.SECONDS, queue, threadFactory, rejectedExecutionHandler) {
                @Override
                public void beforeExecute(Thread t, Runnable r) {
                    LOG.info("线程开始......");
                    LOG.info(String.format("当前线程池的线程数量:{%s}", this.getPoolSize()));
                    LOG.info(String.format("线程需要执行的任务个数:{%s}", getTaskCount()));
                }

                @Override
                public void afterExecute(Runnable r, Throwable t) {
                    LOG.info(String.format("活动的线程的数量:{%s}", this.getActiveCount()));
                    LOG.info(String.format("线程池在运行过程中已完成的任务数:{%s}", this.getCompletedTaskCount()));
                }
            };
        }

        if (this.allowCoreThreadTimeOut) {
            executor.allowCoreThreadTimeOut(true);
        }

        this.threadPoolExecutor = executor;
        return executor;
    }

    protected BlockingQueue<Runnable> createQueue(int queueCapacity) {
        return (BlockingQueue) (queueCapacity > 0 ? new LinkedBlockingQueue(queueCapacity) : new SynchronousQueue());
    }

    public ThreadPoolExecutor getThreadPoolExecutor() throws IllegalStateException {
        Assert.state(this.threadPoolExecutor != null, "ThreadPoolTaskExecutor not initialized");
        return this.threadPoolExecutor;
    }

    public int getPoolSize() {
        return this.threadPoolExecutor == null ? this.corePoolSize : this.threadPoolExecutor.getPoolSize();
    }

    public int getActiveCount() {
        return this.threadPoolExecutor == null ? 0 : this.threadPoolExecutor.getActiveCount();
    }

    public void execute(Runnable task) {
        ThreadPoolExecutor executor = this.getThreadPoolExecutor();

        try {
            executor.execute(task);
        } catch (RejectedExecutionException var4) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, var4);
        }
    }

    public void execute(Runnable task, long startTimeout) {
        this.execute(task);
    }

    public Future<?> submit(Runnable task) {
        ThreadPoolExecutor executor = this.getThreadPoolExecutor();

        try {
            return executor.submit(task);
        } catch (RejectedExecutionException var4) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, var4);
        }
    }

    public <T> Future<T> submit(Callable<T> task) {
        ThreadPoolExecutor executor = this.getThreadPoolExecutor();

        try {
            return executor.submit(task);
        } catch (RejectedExecutionException var4) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, var4);
        }
    }

    public ListenableFuture<?> submitListenable(Runnable task) {
        ThreadPoolExecutor executor = this.getThreadPoolExecutor();

        try {
            ListenableFutureTask<Object> future = new ListenableFutureTask(task, (Object) null);
            executor.execute(future);
            return future;
        } catch (RejectedExecutionException var4) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, var4);
        }
    }

    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        ThreadPoolExecutor executor = this.getThreadPoolExecutor();

        try {
            ListenableFutureTask<T> future = new ListenableFutureTask(task);
            executor.execute(future);
            return future;
        } catch (RejectedExecutionException var4) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, var4);
        }
    }

    public boolean prefersShortLivedTasks() {
        return true;
    }
}
