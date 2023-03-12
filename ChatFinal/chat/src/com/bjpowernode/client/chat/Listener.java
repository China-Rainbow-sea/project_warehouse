package com.bjpowernode.client.chat;

import com.bjpowernode.bean.Message;
import com.bjpowernode.bean.MessageType;
import com.bjpowernode.client.login.LoginController;

import java.io.*;
import java.net.Socket;

/*
    监听客户端和服务器端的消息
 */
public class Listener implements Runnable {

    private String hostname;
    private int port;
    private String username;
    private String picture;
    private Socket socket;

    private ObjectInputStream ois;
    private InputStream inputStream;
    private ObjectOutputStream oos;
    private OutputStream outputStream;

    private ChatController chatController;

    public static Listener instance;

    public Listener(String hostname, int port, String username, String picture,ChatController chatController) {
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.picture = picture;
        this.chatController = chatController;
        instance = this;
    }

    @Override
    public void run() {
        //获取socket对象
        try {
            socket = new Socket(hostname, port);
            outputStream = socket.getOutputStream();
            oos = new ObjectOutputStream(outputStream);
            inputStream = socket.getInputStream();
            ois = new ObjectInputStream(inputStream);

            connect();

            while (socket.isConnected()) {
                Message message = (Message)ois.readObject();
                if (message != null) {
                    switch (message.getType()) {
                        case NOTIFICATION:
                            LoginController.getInstance().showScene();
                            chatController.notify(message.getName() + "加入聊天",message.getPicture(),"新朋友加入","sounds/Global.wav");
                            break;
                        case ERROR:
                            chatController.notify(message.getMsg(),message.getPicture(),"出问题了","sounds/system.wav");
                            break;
                        case JOINED:
                        case DISCONNECTED:
                            chatController.setUserList(message);
                            break;
                        case TEXT:
                            chatController.showMsg(message);
                            break;
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        发送文字消息
     */
    public void send(String msg) throws IOException {
        Message message = new Message();
        message.setName(username);
        message.setType(MessageType.TEXT);
        message.setMsg(msg);
        message.setPicture(picture);
        oos.writeObject(message);
        oos.flush();
    }

    /*
        连接
     */
    public void connect() throws IOException {
        Message message = new Message();
        message.setName(username);
        message.setType(MessageType.JOINED);
        message.setPicture(picture);
        message.setMsg("已连接");

        oos.writeObject(message);
    }
}
