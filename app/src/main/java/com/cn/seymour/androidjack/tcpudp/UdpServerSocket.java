package com.cn.seymour.androidjack.tcpudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * ...
 * <br>Created by seyMour on 2016/4/22.</br>
 * <br>☞120465271@qq.com☜</br>
 */
public class UdpServerSocket {
    private InetSocketAddress socketAddress;

    private DatagramSocket datagramSocket;

    private DatagramPacket datagramPacket;

    private byte[] buffer = new byte[1024];

    public UdpServerSocket(String host,int port) throws SocketException{
        socketAddress = new InetSocketAddress(host, port);
        datagramSocket = new DatagramSocket(socketAddress);
    }

    public String receive() throws IOException {
        datagramPacket = new DatagramPacket(buffer, buffer.length);
        datagramSocket.receive(datagramPacket);
        String info = new String(datagramPacket.getData(),0,datagramPacket.getLength());
        return info;
    }

    public void response(String info) throws IOException {
        datagramPacket = new DatagramPacket(info.getBytes(), info.getBytes().length, new InetSocketAddress("", 0));
        datagramPacket.setData(info.getBytes());
        datagramSocket.send(datagramPacket);
    }

    public static void main(String args[]) throws Exception {
        UdpServerSocket u = new UdpServerSocket("", 0);
        while(true) {
            u.receive();
            u.response("hello");
        }
    }
}
