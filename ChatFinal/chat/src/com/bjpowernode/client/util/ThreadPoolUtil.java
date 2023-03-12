package com.bjpowernode.client.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {
    public static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 4, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(2));
}
