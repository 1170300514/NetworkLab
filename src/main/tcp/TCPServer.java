package main.tcp;

/**
 * Created by xyzhang on 2021/10/20 8:36
 * Email : xyzhang.top@foxmail.com
 */
import java.io.*;
import java.net.*;
class TCPServer{
    public static void main(String argv[]) throws Exception {
        String ClientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);
        while (true){
            // 监听本机6789端口号
            Socket connectionSocket = welcomeSocket.accept();
            // 获取网络接收到的字节流
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket. getInputStream()));
            // 输出字节流
            DataOutputStream outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());
            ClientSentence = inFromClient.readLine();
            System.out.println("Data from client:" + ClientSentence);
            // 将接收到的字符串转为大写
            capitalizedSentence = ClientSentence.toUpperCase() + '\n';
            System.out.println("Data response" + capitalizedSentence);
            outToClient.writeBytes(capitalizedSentence);
        }
    }
}
