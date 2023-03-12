package com.bjpowernode.server;

import com.bjpowernode.bean.Message;
import com.bjpowernode.bean.MessageType;
import com.bjpowernode.bean.User;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/*
    服务器端的处理器
 */
public class Handler implements Runnable {

    private Socket socket;

    private ObjectInputStream ois;
    private InputStream inputStream;
    private ObjectOutputStream oos;
    private OutputStream outputStream;

    private User user;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            inputStream = socket.getInputStream();
            ois = new ObjectInputStream(inputStream);
            outputStream = socket.getOutputStream();
            oos = new ObjectOutputStream(outputStream);

            //将新加入的用户的输出流对象放入到set中
            Server.writers.add(oos);

            //获取客户端第一次登录的消息
            Message firstMessage = (Message) ois.readObject();

            if (!checkDuplicateUsername(firstMessage)) {
                return;
            }

            sendNotification(firstMessage);

            showOnlineUser();

            while (socket.isConnected()) {
                Message message = (Message) ois.readObject();
                switch (message.getType()) {
                    case TEXT:
                        write(message);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    /*
        校验用户昵称是否重复
        要同步
     */
    private synchronized boolean checkDuplicateUsername(Message message) throws IOException {
        if (!Server.userMap.containsKey(message.getName())) {
            user = new User();
            user.setName(message.getName());
            user.setPicture(message.getPicture());
            Server.userMap.put(message.getName(), user);
            return true;
        } else {
            message.setMsg("用户名重复");
            message.setType(MessageType.ERROR);
            //将消息返回给当前登录者
            this.oos.writeObject(message);
            return false;
        }
    }


    /*
        客户端显示加入群聊消息
     */
    private void sendNotification(Message msg) throws IOException {
        msg.setMsg("加入群聊");
        msg.setType(MessageType.NOTIFICATION);

        write(msg);
    }

    /*
        向客户端展示当前在线用户
     */
    private void showOnlineUser() throws IOException {
        Message msg = new Message();
        msg.setMsg("欢迎加入聊天");
        msg.setName("SERVER");
        msg.setType(MessageType.JOINED);
        write(msg);
    }

    /*
        向客户端发送消息
     */
    private void write(Message msg) throws IOException {

        //设置在线用户
        msg.setOnlineUsers(new ArrayList<>(Server.userMap.values()));

        for (ObjectOutputStream writer : Server.writers) {
            writer.writeObject(msg);
        }
    }

    /*
        关闭链接
     */
    private void closeConnection() {
        try {
            //从用户map中移除退出的用户
            if (user.getName() != null) {
                Server.userMap.remove(user.getName());
            }

            if (oos != null) {
                Server.writers.remove(oos);
            }

            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (ois != null) {
                ois.close();
            }
            if (oos != null) {
                oos.close();
            }

            //通知其他在线用户，从在线用户列表中移除当前用户信息
            Message message = new Message();
            message.setMsg("离开聊天");
            message.setType(MessageType.DISCONNECTED);
            message.setName("SERVER");
            write(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
