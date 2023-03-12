package com.bjpowernode.server;

import com.bjpowernode.bean.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    public static HashMap<String, User> userMap = new HashMap<>();
    public static HashSet<ObjectOutputStream> writers = new HashSet<>();
    //线程池对象
    public static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(16, 32, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(16));

    public static void main(String[] args) {
        try(ServerSocket listener = new ServerSocket(9001)) {
            while (true) {
                poolExecutor.execute(new Handler(listener.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
