package main.udp;

/**
 * Created by xyzhang on 2021/10/20 8:42
 * Email : xyzhang.top@foxmail.com
 */
import java.io.*;
import java.net.*;
class UDPClient {
    public static void main(String args[]) throws Exception {
        // 获取标准IO输入
        BufferedReader infromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[10];
        byte[] receiveData = new byte[10];
        String sentence = infromUser.readLine();
        sendData = sentence.getBytes();
        // 组装IO数据包
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,9876);
        clientSocket.send(sendPacket);
        // 接收数据包
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("FROM SERVER:" + modifiedSentence);
        clientSocket.close();
    }
}
