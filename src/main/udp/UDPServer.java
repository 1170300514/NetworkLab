package main.udp;

/**
 * Created by xyzhang on 2021/10/20 8:43
 * Email : xyzhang.top@foxmail.com
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPServer {
    public static void main(String args[]) throws Exception {
        // 初始化Socket
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[10];
        byte[] sendData = new byte[10];
        while(true) {
            // 初始化接收数据包
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            // 监听指定端口号上的UDP数据包
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            System.out.printf("Data accept from client: %s, port: %d\n", sentence, port);
            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();
            // 组装response数据包返回客户端
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAddress,port);
            serverSocket.send(sendPacket);
            System.out.printf("Data response from server: %s", capitalizedSentence);
        }
    }
}
