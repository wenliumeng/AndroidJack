package com.cn.seymour.androidjack.tcpudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * ...
 * <br>Created by seyMour on 2016/4/22.</br>
 * <br>☞120465271@qq.com☜</br>
 */
public class UdpClientSocket {
    private DatagramSocket datagramSocket = null;

    private byte[] buffer = new byte[1024];

    public UdpClientSocket() throws SocketException {
        datagramSocket = new DatagramSocket();
    }

    public DatagramPacket send() throws Exception{
        DatagramPacket dp = new DatagramPacket("info".getBytes(), "info".getBytes().length, InetAddress.getByName("host"),8080);
        datagramSocket.send(dp);
        return dp;
    }

    public String receive() throws Exception{
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
        datagramSocket.receive(dp);
        return new String(dp.getData(), 0, dp.getLength());
    }

    public static void main(String[] args) throws Exception {
        UdpClientSocket u = new UdpClientSocket();
        u.send();
        u.receive();
    }
}
