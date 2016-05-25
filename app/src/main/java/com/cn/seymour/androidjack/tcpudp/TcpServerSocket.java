package com.cn.seymour.androidjack.tcpudp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ...
 * <br>Created by seyMour on 2016/4/22.</br>
 * <br>☞120465271@qq.com☜</br>
 */
public class TcpServerSocket {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = serverSocket.accept();
            //读取客户端数据
            InputStream info = socket.getInputStream();
            DataInputStream dis = new DataInputStream(info);
            System.out.println(dis.readUTF());

            //向客户端输出数据
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF("Hello!");
            dos.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
